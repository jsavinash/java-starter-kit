-- Seed: Sample Users & Roles
INSERT INTO userdb.roles (name, description) VALUES
('ROLE_ADMIN', 'System administrator with full access'),
('ROLE_USER', 'Regular user with standard permissions'),
('ROLE_MODERATOR', 'Content moderator'),
('ROLE_SELLER', 'Product seller/vendor');

INSERT INTO userdb.users (email, password_hash, first_name, last_name, email_verified, is_active) VALUES
('admin@example.com', '$2a$10$dummyhashadmin123', 'Admin', 'User', true, true),
('john.doe@example.com', '$2a$10$dummyhashjohn1234', 'John', 'Doe', true, true),
('jane.smith@example.com', '$2a$10$dummyhashjane123', 'Jane', 'Smith', true, true),
('bob.wilson@example.com', '$2a$10$dummyhashbob1234', 'Bob', 'Wilson', false, true),
('alice.johnson@example.com', '$2a$10$dummyhashalice12', 'Alice', 'Johnson', true, true);

INSERT INTO userdb.user_roles (user_id, role_id) VALUES
(1, 1), (1, 2),  -- admin has ADMIN + USER
(2, 2), (2, 4),  -- John is USER + SELLER
(3, 2),          -- Jane is USER
(4, 2),          -- Bob is USER
(5, 2), (5, 3);  -- Alice is USER + MODERATOR

INSERT INTO userdb.addresses (user_id, type, first_name, last_name, address_line1, city, state, zip_code, is_default) VALUES
(1, 'shipping', 'Admin', 'User', '123 Admin Blvd', 'San Francisco', 'CA', '94105', true),
(2, 'shipping', 'John', 'Doe', '456 Oak Avenue', 'New York', 'NY', '10001', true),
(2, 'billing', 'John', 'Doe', '456 Oak Avenue', 'New York', 'NY', '10001', true),
(3, 'shipping', 'Jane', 'Smith', '789 Pine Street', 'Los Angeles', 'CA', '90001', true),
(5, 'shipping', 'Alice', 'Johnson', '321 Elm Drive', 'Austin', 'TX', '73301', true);