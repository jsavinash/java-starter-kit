resource "aws_db_subnet_group" "db_subnet_group" {
  name        = "${var.environment}-db-subnet-group"
  description = "Subnet group for RDS database"
  subnet_ids  = var.private_subnet_ids

  tags = {
    Name        = "${var.environment}-db-subnet-group"
    Environment = var.environment
  }
}

resource "aws_db_instance" "db" {
  identifier             = "${var.environment}-postgres"
  engine                 = "postgres"
  engine_version         = "17.2"
  instance_class         = var.db_instance_class
  allocated_storage      = var.allocated_storage
  max_allocated_storage  = 100
  db_name                = var.database_name
  username               = var.database_username
  password               = var.database_password
  db_subnet_group_name   = aws_db_subnet_group.db_subnet_group.name
  vpc_security_group_ids = [var.db_security_group_id]
  skip_final_snapshot    = true

  tags = {
    Name        = "${var.environment}-postgres"
    Environment = var.environment
  }
}
