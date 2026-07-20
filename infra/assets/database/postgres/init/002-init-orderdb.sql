-- =============================================
-- Order Database Initialization
-- Services: order, cart, payment
-- =============================================

CREATE SCHEMA IF NOT EXISTS orderdb;

-- Orders
CREATE TABLE IF NOT EXISTS orderdb.orders (
    id BIGSERIAL PRIMARY KEY,
    order_number VARCHAR(50) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'pending',
    subtotal DECIMAL(10,2) NOT NULL,
    tax_amount DECIMAL(10,2) DEFAULT 0,
    shipping_amount DECIMAL(10,2) DEFAULT 0,
    discount_amount DECIMAL(10,2) DEFAULT 0,
    total_amount DECIMAL(10,2) NOT NULL,
    currency VARCHAR(3) DEFAULT 'USD',
    shipping_address JSONB,
    billing_address JSONB,
    payment_method VARCHAR(50),
    payment_status VARCHAR(50) DEFAULT 'pending',
    notes TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE orderdb.orders IS 'Customer orders';

-- Order Items
CREATE TABLE IF NOT EXISTS orderdb.order_items (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES orderdb.orders(id),
    product_id BIGINT NOT NULL,
    variant_id BIGINT,
    product_name VARCHAR(200) NOT NULL,
    sku VARCHAR(50),
    quantity INTEGER NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE orderdb.order_items IS 'Order line items';

-- Order Status History
CREATE TABLE IF NOT EXISTS orderdb.order_status_history (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES orderdb.orders(id),
    from_status VARCHAR(50),
    to_status VARCHAR(50) NOT NULL,
    changed_by VARCHAR(100),
    notes TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE orderdb.order_status_history IS 'Order status change audit trail';

-- Shipments
CREATE TABLE IF NOT EXISTS orderdb.shipments (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES orderdb.orders(id),
    tracking_number VARCHAR(100),
    carrier VARCHAR(50),
    status VARCHAR(50) DEFAULT 'pending',
    shipped_at TIMESTAMP WITH TIME ZONE,
    delivered_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE orderdb.shipments IS 'Order shipment tracking';

-- Indexes
CREATE INDEX IF NOT EXISTS idx_orders_user ON orderdb.orders(user_id);
CREATE INDEX IF NOT EXISTS idx_orders_status ON orderdb.orders(status);
CREATE INDEX IF NOT EXISTS idx_orders_number ON orderdb.orders(order_number);
CREATE INDEX IF NOT EXISTS idx_order_items_order ON orderdb.order_items(order_id);
CREATE INDEX IF NOT EXISTS idx_order_status_history_order ON orderdb.order_status_history(order_id);