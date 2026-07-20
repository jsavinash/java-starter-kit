# ALB Security Group (Public facing)
resource "aws_security_group" "alb" {
  name        = "${var.environment}-alb-sg"
  description = "Controls access to the ALB"
  vpc_id      = var.vpc_id

  ingress {
    description = "Allow HTTP"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "Allow HTTPS"
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name        = "${var.environment}-alb-sg"
    Environment = var.environment
  }
}

# ECS Tasks Security Group (Only allows traffic from ALB)
resource "aws_security_group" "ecs_tasks" {
  name        = "${var.environment}-ecs-tasks-sg"
  description = "Allows inbound access from the ALB only"
  vpc_id      = var.vpc_id

  ingress {
    description     = "Allow traffic from ALB on all container ports"
    from_port       = 0
    to_port         = 65535
    protocol        = "tcp"
    security_groups = [aws_security_group.alb.id]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name        = "${var.environment}-ecs-tasks-sg"
    Environment = var.environment
  }
}

# RDS Security Group (Only allows traffic from ECS Tasks)
resource "aws_security_group" "db" {
  name        = "${var.environment}-db-sg"
  description = "Allows access to RDS database"
  vpc_id      = var.vpc_id

  ingress {
    description     = "Allow PostgreSQL from ECS tasks"
    from_port       = 5432
    to_port         = 5432
    protocol        = "tcp"
    security_groups = [aws_security_group.ecs_tasks.id]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name        = "${var.environment}-db-sg"
    Environment = var.environment
  }
}

# Redis Security Group (Only allows traffic from ECS Tasks)
resource "aws_security_group" "redis" {
  name        = "${var.environment}-redis-sg"
  description = "Allows access to ElastiCache Redis"
  vpc_id      = var.vpc_id

  ingress {
    description     = "Allow Redis from ECS tasks"
    from_port       = 6379
    to_port         = 6379
    protocol        = "tcp"
    security_groups = [aws_security_group.ecs_tasks.id]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name        = "${var.environment}-redis-sg"
    Environment = var.environment
  }
}
