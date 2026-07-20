-- =============================================
-- Cassandra Ecommerce Keyspace Initialization
-- =============================================

-- Create keyspace
CREATE KEYSPACE IF NOT EXISTS ecommerce
WITH replication = {
    'class': 'SimpleStrategy',
    'replication_factor': 1
};

-- Use keyspace
USE ecommerce;

-- User Sessions Table (high-write, high-read)
CREATE TABLE IF NOT EXISTS user_sessions (
    user_id UUID,
    session_id UUID,
    login_time TIMESTAMP,
    ip_address INET,
    user_agent TEXT,
    is_active BOOLEAN,
    PRIMARY KEY (user_id, session_id)
);
COMMENT ON TABLE user_sessions IS 'User session data for high-write scenarios';

-- Event Log Table (time-series data)
CREATE TABLE IF NOT EXISTS event_log (
    event_type TEXT,
    event_time TIMESTAMP,
    event_id UUID,
    user_id UUID,
    payload TEXT,
    source TEXT,
    PRIMARY KEY (event_type, event_time, event_id)
) WITH CLUSTERING ORDER BY (event_time DESC);
COMMENT ON TABLE event_log IS 'Time-series event log';

-- Product Recommendations Table
CREATE TABLE IF NOT EXISTS product_recommendations (
    product_id UUID,
    recommended_product_id UUID,
    score FLOAT,
    category TEXT,
    updated_at TIMESTAMP,
    PRIMARY KEY (product_id, recommended_product_id)
);
COMMENT ON TABLE product_recommendations IS 'Product recommendation pairs';

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_event_user ON event_log (user_id);
CREATE INDEX IF NOT EXISTS idx_recommendation_category ON product_recommendations (category);