-- =============================================
-- Inventory Database Initialization
-- Services: inventory, item-management
-- =============================================

CREATE SCHEMA IF NOT EXISTS inventorydb;

-- Warehouses
CREATE TABLE IF NOT EXISTS inventorydb.warehouses (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    code VARCHAR(50) NOT NULL UNIQUE,
    address JSONB,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE inventorydb.warehouses IS 'Warehouse locations';

-- Inventory
CREATE TABLE IF NOT EXISTS inventorydb.inventory (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    variant_id BIGINT,
    warehouse_id BIGINT NOT NULL REFERENCES inventorydb.warehouses(id),
    quantity_on_hand INTEGER DEFAULT 0,
    quantity_reserved INTEGER DEFAULT 0,
    quantity_available INTEGER GENERATED ALWAYS AS (quantity_on_hand - quantity_reserved) STORED,
    reorder_point INTEGER DEFAULT 10,
    reorder_quantity INTEGER DEFAULT 50,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (product_id, COALESCE(variant_id, 0), warehouse_id)
);
COMMENT ON TABLE inventorydb.inventory IS 'Product inventory levels';

-- Inventory Transactions
CREATE TABLE IF NOT EXISTS inventorydb.inventory_transactions (
    id BIGSERIAL PRIMARY KEY,
    inventory_id BIGINT NOT NULL REFERENCES inventorydb.inventory(id),
    type VARCHAR(50) NOT NULL,
    quantity INTEGER NOT NULL,
    reference_type VARCHAR(50),
    reference_id BIGINT,
    notes TEXT,
    created_by VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE inventorydb.inventory_transactions IS 'Inventory movement audit trail';

-- Stock Alerts
CREATE TABLE IF NOT EXISTS inventorydb.stock_alerts (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    warehouse_id BIGINT NOT NULL REFERENCES inventorydb.warehouses(id),
    alert_type VARCHAR(50) NOT NULL,
    message TEXT,
    is_resolved BOOLEAN DEFAULT false,
    resolved_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE inventorydb.stock_alerts IS 'Low stock and out-of-stock alerts';

-- Indexes
CREATE INDEX IF NOT EXISTS idx_inventory_product ON inventorydb.inventory(product_id);
CREATE INDEX IF NOT EXISTS idx_inventory_warehouse ON inventorydb.inventory(warehouse_id);
CREATE INDEX IF NOT EXISTS idx_inventory_transactions_inv ON inventorydb.inventory_transactions(inventory_id);
CREATE INDEX IF NOT EXISTS idx_stock_alerts_product ON inventorydb.stock_alerts(product_id);