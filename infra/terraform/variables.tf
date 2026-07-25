variable "aws_region" {
  description = "AWS region to deploy resources"
  type        = string
  default     = "us-east-1"
}

variable "environment" {
  description = "Environment name (e.g. dev, prod)"
  type        = string
}

variable "vpc_cidr" {
  description = "CIDR block for the VPC"
  type        = string
  default     = "10.0.0.0/16"
}

variable "public_subnet_cidrs" {
  description = "CIDR blocks for public subnets"
  type        = list(string)
  default     = ["10.0.1.0/24", "10.0.2.0/24"]
}

variable "private_subnet_cidrs" {
  description = "CIDR blocks for private subnets"
  type        = list(string)
  default     = ["10.0.10.0/24", "10.0.11.0/24"]
}

variable "availability_zones" {
  description = "List of availability zones"
  type        = list(string)
  default     = ["us-east-1a", "us-east-1b"]
}

variable "database_name" {
  description = "The database name"
  type        = string
  default     = "starter_db"
}

variable "database_username" {
  description = "The database master username"
  type        = string
  default     = "postgres"
}

variable "database_password" {
  description = "The database master password"
  type        = string
  sensitive   = true
}

variable "container_image_user" {
  description = "Docker image for user microservice"
  type        = string
}

variable "container_image_gateway" {
  description = "Docker image for API gateway"
  type        = string
}

variable "container_image_config" {
  description = "Docker image for config server"
  type        = string
}

variable "container_image_discovery" {
  description = "Docker image for service discovery"
  type        = string
}
