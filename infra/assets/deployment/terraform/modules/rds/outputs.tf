output "db_endpoint" {
  description = "The connection endpoint for the RDS instance"
  value       = aws_db_instance.db.endpoint
}

output "db_address" {
  description = "The hostname of the RDS instance"
  value       = aws_db_instance.db.address
}

output "db_port" {
  description = "The database port"
  value       = aws_db_instance.db.port
}

output "db_name" {
  description = "The database name"
  value       = aws_db_instance.db.db_name
}

output "db_username" {
  description = "The database master username"
  value       = aws_db_instance.db.username
}
