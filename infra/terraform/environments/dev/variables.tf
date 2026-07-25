variable "aws_region" {
  description = "AWS region"
  type        = string
  default     = "us-east-1"
}

variable "database_password" {
  description = "RDS master password"
  type        = string
  sensitive   = true
}
