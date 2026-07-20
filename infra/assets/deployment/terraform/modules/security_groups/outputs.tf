output "alb_sg_id" {
  description = "The ID of the ALB Security Group"
  value       = aws_security_group.alb.id
}

output "ecs_tasks_sg_id" {
  description = "The ID of the ECS Tasks Security Group"
  value       = aws_security_group.ecs_tasks.id
}

output "db_sg_id" {
  description = "The ID of the Database Security Group"
  value       = aws_security_group.db.id
}

output "redis_sg_id" {
  description = "The ID of the Redis Security Group"
  value       = aws_security_group.redis.id
}
