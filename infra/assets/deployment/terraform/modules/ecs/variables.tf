variable "vpc_id" {
  description = "The VPC ID"
  type        = string
}

variable "public_subnet_ids" {
  description = "List of public subnet IDs for the ALB"
  type        = list(string)
}

variable "private_subnet_ids" {
  description = "List of private subnet IDs for ECS tasks"
  type        = list(string)
}

variable "ecs_tasks_sg_id" {
  description = "Security group ID for ECS tasks"
  type        = string
}

variable "alb_sg_id" {
  description = "Security group ID for ALB"
  type        = string
}

variable "environment" {
  description = "Environment name"
  type        = string
}

variable "db_host" {
  description = "RDS DB Hostname"
  type        = string
}

variable "db_port" {
  description = "RDS DB Port"
  type        = string
}

variable "db_name" {
  description = "RDS DB Name"
  type        = string
}

variable "db_username" {
  description = "RDS DB Username"
  type        = string
}

variable "db_password" {
  description = "RDS DB Password"
  type        = string
  sensitive   = true
}

variable "redis_endpoint" {
  description = "Redis cache endpoint"
  type        = string
}

variable "redis_port" {
  description = "Redis cache port"
  type        = string
}

variable "container_image_user" {
  description = "Docker image for user microservice"
  type        = string
  default     = "starter-user:latest"
}

variable "container_image_gateway" {
  description = "Docker image for API gateway"
  type        = string
  default     = "starter-api-gateway:latest"
}

variable "container_image_config" {
  description = "Docker image for config server"
  type        = string
  default     = "starter-config-server:latest"
}

variable "container_image_discovery" {
  description = "Docker image for service discovery"
  type        = string
  default     = "starter-service-discovery:latest"
}
