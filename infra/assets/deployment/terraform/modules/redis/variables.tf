variable "private_subnet_ids" {
  description = "List of private subnet IDs for Redis"
  type        = list(string)
}

variable "redis_security_group_id" {
  description = "The security group ID allowed to connect to Redis"
  type        = string
}

variable "environment" {
  description = "Environment name"
  type        = string
}

variable "node_type" {
  description = "The cluster node type"
  type        = string
  default     = "cache.t4g.micro"
}

variable "num_cache_nodes" {
  description = "Number of cache nodes"
  type        = number
  default     = 1
}
