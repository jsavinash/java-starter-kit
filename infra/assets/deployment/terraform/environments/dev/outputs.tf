output "vpc_id" {
  description = "The ID of the VPC"
  value       = module.dev_infrastructure.vpc_id
}

output "alb_dns_name" {
  description = "The public DNS of the Application Load Balancer"
  value       = module.dev_infrastructure.alb_dns_name
}

output "db_endpoint" {
  description = "The RDS connection endpoint"
  value       = module.dev_infrastructure.db_endpoint
}

output "redis_endpoint" {
  description = "The Redis connection endpoint"
  value       = module.dev_infrastructure.redis_endpoint
}
