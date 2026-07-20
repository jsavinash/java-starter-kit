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

module "vpc" {
  source               = "./modules/vpc"
  vpc_cidr             = var.vpc_cidr
  public_subnet_cidrs  = var.public_subnet_cidrs
  private_subnet_cidrs = var.private_subnet_cidrs
  availability_zones   = var.availability_zones
  environment          = var.environment
}

module "security_groups" {
  source      = "./modules/security_groups"
  vpc_id      = module.vpc.vpc_id
  environment = var.environment
}

module "rds" {
  source               = "./modules/rds"
  private_subnet_ids   = module.vpc.private_subnets
  db_security_group_id = module.security_groups.db_sg_id
  environment          = var.environment
  database_name        = var.database_name
  database_username    = var.database_username
  database_password    = var.database_password
}

module "redis" {
  source                  = "./modules/redis"
  private_subnet_ids      = module.vpc.private_subnets
  redis_security_group_id = module.security_groups.redis_sg_id
  environment             = var.environment
}

module "ecs" {
  source                    = "./modules/ecs"
  vpc_id                    = module.vpc.vpc_id
  public_subnet_ids         = module.vpc.public_subnets
  private_subnet_ids        = module.vpc.private_subnets
  ecs_tasks_sg_id           = module.security_groups.ecs_tasks_sg_id
  alb_sg_id                 = module.security_groups.alb_sg_id
  environment               = var.environment
  db_host                   = module.rds.db_address
  db_port                   = module.rds.db_port
  db_name                   = module.rds.db_name
  db_username               = module.rds.db_username
  db_password               = var.database_password
  redis_endpoint            = module.redis.redis_endpoint
  redis_port                = module.redis.redis_port
  container_image_user      = var.container_image_user
  container_image_gateway   = var.container_image_gateway
  container_image_config    = var.container_image_config
  container_image_discovery = var.container_image_discovery
}
