resource "aws_elasticache_subnet_group" "redis_subnet_group" {
  name        = "${var.environment}-redis-subnet-group"
  description = "Subnet group for Redis cache"
  subnet_ids  = var.private_subnet_ids

  tags = {
    Name        = "${var.environment}-redis-subnet-group"
    Environment = var.environment
  }
}

resource "aws_elasticache_cluster" "redis" {
  cluster_id           = "${var.environment}-redis"
  engine               = "redis"
  engine_version       = "7.1"
  node_type            = var.node_type
  num_cache_nodes      = var.num_cache_nodes
  parameter_group_name = "default.redis7"
  subnet_group_name    = aws_elasticache_subnet_group.redis_subnet_group.name
  security_group_ids   = [var.redis_security_group_id]
  port                 = 6379

  tags = {
    Name        = "${var.environment}-redis-cache"
    Environment = var.environment
  }
}
