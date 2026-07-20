# ECS Cluster
resource "aws_ecs_cluster" "cluster" {
  name = "${var.environment}-ecs-cluster"

  setting {
    name  = "containerInsights"
    value = "enabled"
  }

  tags = {
    Name        = "${var.environment}-ecs-cluster"
    Environment = var.environment
  }
}

# CloudWatch Log Groups
resource "aws_cloudwatch_log_group" "logs" {
  for_each          = toset(["config-server", "service-discovery", "api-gateway", "user-service"])
  name              = "/ecs/${var.environment}-${each.key}"
  retention_in_days = 7

  tags = {
    Environment = var.environment
  }
}

# IAM Roles for ECS Task Execution and Task Role
resource "aws_iam_role" "ecs_execution_role" {
  name = "${var.environment}-ecs-execution-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "ecs-tasks.amazonaws.com"
        }
      }
    ]
  })
}

resource "aws_iam_role_policy_attachment" "ecs_execution" {
  role       = aws_iam_role.ecs_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
}

resource "aws_iam_role" "ecs_task_role" {
  name = "${var.environment}-ecs-task-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "ecs-tasks.amazonaws.com"
        }
      }
    ]
  })
}

# Application Load Balancer
resource "aws_lb" "alb" {
  name               = "${var.environment}-alb"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [var.alb_sg_id]
  subnets            = var.public_subnet_ids

  tags = {
    Name        = "${var.environment}-alb"
    Environment = var.environment
  }
}

resource "aws_lb_target_group" "gateway" {
  name        = "${var.environment}-tg-gateway"
  port        = 8080
  protocol    = "HTTP"
  vpc_id      = var.vpc_id
  target_type = "ip"

  healthcheck {
    path                = "/actuator/health"
    matcher             = "200-399"
    interval            = 30
    timeout             = 5
    healthy_threshold   = 3
    unhealthy_threshold = 3
  }

  tags = {
    Environment = var.environment
  }
}

resource "aws_lb_listener" "http" {
  load_balancer_arn = aws_lb.alb.arn
  port              = 80
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.gateway.arn
  }
}

# 1. Config Server Task Definition & Service
resource "aws_ecs_task_definition" "config" {
  family                   = "${var.environment}-config-server"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"
  execution_role_arn       = aws_iam_role.ecs_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_role.arn

  container_definitions = jsonencode([
    {
      name      = "config-server"
      image     = var.container_image_config
      essential = true
      portMappings = [
        {
          containerPort = 8079
          hostPort      = 8079
        }
      ]
      environment = [
        { name = "SPRING_PROFILES_ACTIVE", value = "docker" }
      ]
      logConfiguration = {
        logDriver = "awslogs"
        options = {
          "awslogs-group"         = aws_cloudwatch_log_group.logs["config-server"].name
          "awslogs-region"        = "us-east-1"
          "awslogs-stream-prefix" = "config"
        }
      }
    }
  ])
}

resource "aws_ecs_service" "config" {
  name            = "${var.environment}-config-server"
  cluster         = aws_ecs_cluster.cluster.id
  task_definition = aws_ecs_task_definition.config.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets         = var.private_subnet_ids
    security_groups = [var.ecs_tasks_sg_id]
  }
}

# 2. Service Discovery Task Definition & Service
resource "aws_ecs_task_definition" "discovery" {
  family                   = "${var.environment}-service-discovery"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"
  execution_role_arn       = aws_iam_role.ecs_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_role.arn

  container_definitions = jsonencode([
    {
      name      = "service-discovery"
      image     = var.container_image_discovery
      essential = true
      portMappings = [
        {
          containerPort = 8761
          hostPort      = 8761
        }
      ]
      environment = [
        { name = "SPRING_PROFILES_ACTIVE", value = "docker" },
        { name = "CONFIG_SERVER_HOST", value = "config-server.local" }
      ]
      logConfiguration = {
        logDriver = "awslogs"
        options = {
          "awslogs-group"         = aws_cloudwatch_log_group.logs["service-discovery"].name
          "awslogs-region"        = "us-east-1"
          "awslogs-stream-prefix" = "discovery"
        }
      }
    }
  ])
}

resource "aws_ecs_service" "discovery" {
  name            = "${var.environment}-service-discovery"
  cluster         = aws_ecs_cluster.cluster.id
  task_definition = aws_ecs_task_definition.discovery.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets         = var.private_subnet_ids
    security_groups = [var.ecs_tasks_sg_id]
  }

  depends_on = [aws_ecs_service.config]
}

# 3. API Gateway Task Definition & Service
resource "aws_ecs_task_definition" "gateway" {
  family                   = "${var.environment}-api-gateway"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"
  execution_role_arn       = aws_iam_role.ecs_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_role.arn

  container_definitions = jsonencode([
    {
      name      = "api-gateway"
      image     = var.container_image_gateway
      essential = true
      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
        }
      ]
      environment = [
        { name = "SPRING_PROFILES_ACTIVE", value = "docker" },
        { name = "CONFIG_SERVER_HOST", value = "config-server.local" },
        { name = "EUREKA_HOST", value = "service-discovery.local" }
      ]
      logConfiguration = {
        logDriver = "awslogs"
        options = {
          "awslogs-group"         = aws_cloudwatch_log_group.logs["api-gateway"].name
          "awslogs-region"        = "us-east-1"
          "awslogs-stream-prefix" = "gateway"
        }
      }
    }
  ])
}

resource "aws_ecs_service" "gateway" {
  name            = "${var.environment}-api-gateway"
  cluster         = aws_ecs_cluster.cluster.id
  task_definition = aws_ecs_task_definition.gateway.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets         = var.private_subnet_ids
    security_groups = [var.ecs_tasks_sg_id]
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.gateway.arn
    container_name   = "api-gateway"
    container_port   = 8080
  }

  depends_on = [aws_ecs_service.discovery]
}

# 4. User Microservice Task Definition & Service
resource "aws_ecs_task_definition" "user" {
  family                   = "${var.environment}-user-service"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"
  execution_role_arn       = aws_iam_role.ecs_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_role.arn

  container_definitions = jsonencode([
    {
      name      = "user-service"
      image     = var.container_image_user
      essential = true
      portMappings = [
        {
          containerPort = 8081
          hostPort      = 8081
        }
      ]
      environment = [
        { name = "SPRING_PROFILES_ACTIVE", value = "docker" },
        { name = "CONFIG_SERVER_HOST", value = "config-server.local" },
        { name = "EUREKA_HOST", value = "service-discovery.local" },
        { name = "DB_HOST", value = var.db_host },
        { name = "DB_PORT", value = var.db_port },
        { name = "DB_NAME", value = var.db_name },
        { name = "DB_USERNAME", value = var.db_username },
        { name = "DB_PASSWORD", value = var.db_password },
        { name = "REDIS_HOST", value = var.redis_endpoint },
        { name = "REDIS_PORT", value = var.redis_port }
      ]
      logConfiguration = {
        logDriver = "awslogs"
        options = {
          "awslogs-group"         = aws_cloudwatch_log_group.logs["user-service"].name
          "awslogs-region"        = "us-east-1"
          "awslogs-stream-prefix" = "user"
        }
      }
    }
  ])
}

resource "aws_ecs_service" "user" {
  name            = "${var.environment}-user-service"
  cluster         = aws_ecs_cluster.cluster.id
  task_definition = aws_ecs_task_definition.user.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets         = var.private_subnet_ids
    security_groups = [var.ecs_tasks_sg_id]
  }

  depends_on = [aws_ecs_service.discovery]
}
