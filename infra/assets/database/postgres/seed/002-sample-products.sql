-- Seed: Sample Products
INSERT INTO productdb.products (sku, name, slug, description, category_id, brand, unit_price, is_active, is_featured) VALUES
('SKU-ELEC-001', 'Wireless Bluetooth Headphones', 'wireless-bluetooth-headphones', 'High-quality wireless headphones with noise cancellation', 1, 'SoundMax', 79.99, true, true),
('SKU-ELEC-002', 'Smartphone 128GB', 'smartphone-128gb', 'Latest generation smartphone with 128GB storage', 1, 'TechPhone', 699.99, true, true),
('SKU-ELEC-003', 'USB-C Hub 7-in-1', 'usb-c-hub-7-in-1', 'Multi-port USB-C hub with HDMI, USB 3.0, and SD card reader', 1, 'ConnectPro', 34.99, true, false),
('SKU-CLOTH-001', 'Classic Cotton T-Shirt', 'classic-cotton-tshirt', 'Comfortable 100% cotton t-shirt', 2, 'FashionBasic', 19.99, true, false),
('SKU-CLOTH-002', 'Denim Jacket', 'denim-jacket', 'Stylish denim jacket for all seasons', 2, 'UrbanWear', 89.99, true, true),
('SKU-CLOTH-003', 'Running Shoes', 'running-shoes', 'Lightweight running shoes with cushioned sole', 2, 'SportStep', 129.99, true, true),
('SKU-HOME-001', 'Stainless Steel Cookware Set', 'stainless-steel-cookware', '10-piece stainless steel cookware set', 3, 'KitchenElite', 199.99, true, false),
('SKU-HOME-002', 'Indoor Plant Pot Set', 'indoor-plant-pot-set', 'Set of 3 ceramic plant pots', 3, 'GreenHome', 39.99, true, false),
('SKU-BOOK-001', 'Java Programming Guide', 'java-programming-guide', 'Comprehensive guide to Java programming', 4, 'TechPress', 49.99, true, true),
('SKU-BOOK-002', 'Data Structures & Algorithms', 'data-structures-algorithms', 'Essential computer science concepts', 4, 'AcademicPress', 39.99, true, false),
('SKU-SPORT-001', 'Yoga Mat Premium', 'yoga-mat-premium', 'Extra thick non-slip yoga mat', 5, 'FlexFit', 29.99, true, false),
('SKU-SPORT-002', 'Resistance Bands Set', 'resistance-bands-set', 'Set of 5 resistance bands for home workouts', 5, 'PowerFit', 24.99, true, false);