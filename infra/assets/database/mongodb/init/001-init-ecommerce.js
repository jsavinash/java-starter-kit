// =============================================
// MongoDB Ecommerce Database Initialization
// =============================================

// Switch to ecommerce database
db = db.getSiblingDB('ecommerce');

// Create collections with schema validation
db.createCollection('products', {
    validator: {
        $jsonSchema: {
            bsonType: 'object',
            required: ['name', 'price'],
            properties: {
                name: { bsonType: 'string', description: 'Product name' },
                description: { bsonType: 'string' },
                category: { bsonType: 'string' },
                price: { bsonType: 'number', minimum: 0 },
                attributes: { bsonType: 'object' },
                isActive: { bsonType: 'bool' },
                createdAt: { bsonType: 'date' },
                updatedAt: { bsonType: 'date' }
            }
        }
    }
});

db.createCollection('sessions', {
    validator: {
        $jsonSchema: {
            bsonType: 'object',
            required: ['userId', 'token', 'expiresAt'],
            properties: {
                userId: { bsonType: 'string' },
                token: { bsonType: 'string' },
                expiresAt: { bsonType: 'date' },
                createdAt: { bsonType: 'date' }
            }
        }
    }
});

db.createCollection('audit_logs', {
    validator: {
        $jsonSchema: {
            bsonType: 'object',
            required: ['action', 'entity', 'entityId', 'timestamp'],
            properties: {
                action: { bsonType: 'string' },
                entity: { bsonType: 'string' },
                entityId: { bsonType: 'string' },
                changes: { bsonType: 'object' },
                performedBy: { bsonType: 'string' },
                timestamp: { bsonType: 'date' }
            }
        }
    }
});

// Create indexes
db.products.createIndex({ category: 1 });
db.products.createIndex({ name: 'text', description: 'text' });
db.products.createIndex({ price: 1 });
db.sessions.createIndex({ token: 1 }, { unique: true });
db.sessions.createIndex({ expiresAt: 1 }, { expireAfterSeconds: 0 });
db.audit_logs.createIndex({ entity: 1, entityId: 1 });
db.audit_logs.createIndex({ timestamp: -1 });

print('MongoDB ecommerce database initialized successfully');