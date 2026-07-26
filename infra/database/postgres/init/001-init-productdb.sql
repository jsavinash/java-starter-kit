-- =============================================
-- Product Database Initialization
-- Services: product, item, offers, recommendations
-- =============================================

CREATE SCHEMA IF NOT EXISTS productdb;

-- Categories
CREATE TABLE IF NOT EXISTS productdb.categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    parent_id BIGINT REFERENCES productdb.categories(id),
    image_url VARCHAR(500),
    is_active BOOLEAN DEFAULT true,
    sort_order INTEGER DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE productdb.categories IS 'Product categories hierarchy';

-- Products
CREATE TABLE IF NOT EXISTS productdb.products (
    id BIGSERIAL PRIMARY KEY,
    sku VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(200) NOT NULL,
    slug VARCHAR(200) NOT NULL UNIQUE,
    description TEXT,
    category_id BIGINT REFERENCES productdb.categories(id),
    brand VARCHAR(100),
    unit_price DECIMAL(10,2) NOT NULL,
    compare_price DECIMAL(10,2),
    cost_price DECIMAL(10,2),
    currency VARCHAR(3) DEFAULT 'USD',
    is_active BOOLEAN DEFAULT true,
    is_featured BOOLEAN DEFAULT false,
    tax_class VARCHAR(50),
    weight DECIMAL(10,2),
    weight_unit VARCHAR(10),
    meta_title VARCHAR(200),
    meta_description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE productdb.products IS 'Main product catalog';

-- Product Variants
CREATE TABLE IF NOT EXISTS productdb.product_variants (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL REFERENCES productdb.products(id),
    sku VARCHAR(50) NOT NULL,
    name VARCHAR(200) NOT NULL,
    options JSONB,
    unit_price DECIMAL(10,2),
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE productdb.product_variants IS 'Product variants (size, color, etc)';

-- Product Images
CREATE TABLE IF NOT EXISTS productdb.product_images (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL REFERENCES productdb.products(id),
    variant_id BIGINT REFERENCES productdb.product_variants(id),
    url VARCHAR(500) NOT NULL,
    alt_text VARCHAR(200),
    sort_order INTEGER DEFAULT 0,
    is_primary BOOLEAN DEFAULT false,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE productdb.product_images IS 'Product images gallery';

-- Product Tags
CREATE TABLE IF NOT EXISTS productdb.product_tags (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    slug VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE productdb.product_tags IS 'Product tags for filtering';

-- Product-Tag Mapping
CREATE TABLE IF NOT EXISTS productdb.product_tag_mapping (
    product_id BIGINT NOT NULL REFERENCES productdb.products(id),
    tag_id BIGINT NOT NULL REFERENCES productdb.product_tags(id),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (product_id, tag_id)
);
COMMENT ON TABLE productdb.product_tag_mapping IS 'Many-to-many product-tag mapping';

-- Indexes
CREATE INDEX IF NOT EXISTS idx_products_category ON productdb.products(category_id);
CREATE INDEX IF NOT EXISTS idx_products_sku ON productdb.products(sku);
CREATE INDEX IF NOT EXISTS idx_products_slug ON productdb.products(slug);
CREATE INDEX IF NOT EXISTS idx_product_variants_product ON productdb.product_variants(product_id);
CREATE INDEX IF NOT EXISTS idx_product_images_product ON productdb.product_images(product_id);