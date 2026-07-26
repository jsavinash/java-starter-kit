-- Flyway Migration: V1__create_categories
-- Adds category indexes and additional fields

ALTER TABLE productdb.categories ADD COLUMN IF NOT EXISTS icon VARCHAR(100);
ALTER TABLE productdb.categories ADD COLUMN IF NOT EXISTS banner_url VARCHAR(500);

CREATE INDEX IF NOT EXISTS idx_categories_parent ON productdb.categories(parent_id);
CREATE INDEX IF NOT EXISTS idx_categories_slug ON productdb.categories(slug);