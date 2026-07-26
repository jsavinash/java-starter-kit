output "vpc_id" {
  description = "The ID of the VPC"
  value       = module.vpc.vpc_id
}

output "alb_dns_name" {
  description = "The public DNS of the Application Load Balancer"
  value       = module.ecs.alb_dns_name
}

output "db_endpoint" {
  description = "The RDS connection endpoint"
  value       = module.rds.db_endpoint
}

output "redis_endpoint" {
  description = "The Redis connection endpoint"
  value       = module.redis.redis_endpoint
}
