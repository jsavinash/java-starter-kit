-- =============================================
-- User Database Initialization
-- Services: user, notification
-- =============================================

CREATE SCHEMA IF NOT EXISTS userdb;

-- Users
CREATE TABLE IF NOT EXISTS userdb.users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    phone VARCHAR(20),
    email_verified BOOLEAN DEFAULT false,
    is_active BOOLEAN DEFAULT true,
    last_login_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE userdb.users IS 'User accounts';

-- Addresses
CREATE TABLE IF NOT EXISTS userdb.addresses (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES userdb.users(id),
    type VARCHAR(20) DEFAULT 'shipping',
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255),
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100),
    zip_code VARCHAR(20),
    country VARCHAR(100) DEFAULT 'US',
    phone VARCHAR(20),
    is_default BOOLEAN DEFAULT false,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE userdb.addresses IS 'User addresses';

-- Roles
CREATE TABLE IF NOT EXISTS userdb.roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE userdb.roles IS 'User roles';

-- User Roles
CREATE TABLE IF NOT EXISTS userdb.user_roles (
    user_id BIGINT NOT NULL REFERENCES userdb.users(id),
    role_id BIGINT NOT NULL REFERENCES userdb.roles(id),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, role_id)
);
COMMENT ON TABLE userdb.user_roles IS 'Many-to-many user-role mapping';

-- Indexes
CREATE INDEX IF NOT EXISTS idx_users_email ON userdb.users(email);
CREATE INDEX IF NOT EXISTS idx_addresses_user ON userdb.addresses(user_id);
CREATE INDEX IF NOT EXISTS idx_user_roles_user ON userdb.user_roles(user_id);