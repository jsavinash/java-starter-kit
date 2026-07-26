variable "private_subnet_ids" {
  description = "List of private subnet IDs for the database"
  type        = list(string)
}

variable "db_security_group_id" {
  description = "The security group ID allowed to connect to the database"
  type        = string
}

variable "environment" {
  description = "Environment name"
  type        = string
}

variable "db_instance_class" {
  description = "The RDS instance class"
  type        = string
  default     = "db.t4g.micro"
}

variable "allocated_storage" {
  description = "The allocated storage in gigabytes"
  type        = number
  default     = 20
}

variable "database_name" {
  description = "The name of the database to create"
  type        = string
  default     = "starter_db"
}

variable "database_username" {
  description = "Username for the master DB user"
  type        = string
  default     = "postgres"
}

variable "database_password" {
  description = "Password for the master DB user"
  type        = string
  sensitive   = true
}
