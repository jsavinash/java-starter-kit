terraform {
  required_version = ">= 1.5.0"
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = var.aws_region
}

module "dev_infrastructure" {
  source = "../../"

  aws_region                = var.aws_region
  environment               = "dev"
  vpc_cidr                  = "10.0.0.0/16"
  public_subnet_cidrs       = ["10.0.1.0/24", "10.0.2.0/24"]
  private_subnet_cidrs      = ["10.0.10.0/24", "10.0.11.0/24"]
  availability_zones        = ["us-east-1a", "us-east-1b"]
  database_name             = "starter_dev_db"
  database_username         = "db_admin"
  database_password         = var.database_password
  container_image_user      = "123456789012.dkr.ecr.us-east-1.amazonaws.com/starter-user:latest"
  container_image_gateway   = "123456789012.dkr.ecr.us-east-1.amazonaws.com/starter-api-gateway:latest"
  container_image_config    = "123456789012.dkr.ecr.us-east-1.amazonaws.com/starter-config-server:latest"
  container_image_discovery = "123456789012.dkr.ecr.us-east-1.amazonaws.com/starter-service-discovery:latest"
}
