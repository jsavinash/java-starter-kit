package com.starter.infra.db.model;

import com.starter.infra.db.dbtype.DbType;

import java.util.List;
import java.util.Map;

public class DbSchema {
    private String name;
    private DbType dbType;
    private String databaseName;
    private List<TableDef> tables;
    private boolean systemManaged;
    private String initScript;
    private String migrationScript;

    public DbSchema() {
    }

    public DbSchema(String name, DbType dbType, String databaseName, List<TableDef> tables) {
        this.name = name;
        this.dbType = dbType;
        this.databaseName = databaseName;
        this.tables = tables;
        this.systemManaged = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DbType getDbType() {
        return dbType;
    }

    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public List<TableDef> getTables() {
        return tables;
    }

    public void setTables(List<TableDef> tables) {
        this.tables = tables;
    }

    public boolean isSystemManaged() {
        return systemManaged;
    }

    public void setSystemManaged(boolean systemManaged) {
        this.systemManaged = systemManaged;
    }

    public String getInitScript() {
        return initScript;
    }

    public void setInitScript(String initScript) {
        this.initScript = initScript;
    }

    public String getMigrationScript() {
        return migrationScript;
    }

    public void setMigrationScript(String migrationScript) {
        this.migrationScript = migrationScript;
    }

    public static class TableDef {
        private String name;
        private String comment;
        private List<ColumnDef> columns;
        private List<String> primaryKeys;
        private List<IndexDef> indexes;
        private List<ForeignKeyDef> foreignKeys;

        public TableDef() {
        }

        public TableDef(String name, List<ColumnDef> columns) {
            this.name = name;
            this.columns = columns;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public List<ColumnDef> getColumns() {
            return columns;
        }

        public void setColumns(List<ColumnDef> columns) {
            this.columns = columns;
        }

        public List<String> getPrimaryKeys() {
            return primaryKeys;
        }

        public void setPrimaryKeys(List<String> primaryKeys) {
            this.primaryKeys = primaryKeys;
        }

        public List<IndexDef> getIndexes() {
            return indexes;
        }

        public void setIndexes(List<IndexDef> indexes) {
            this.indexes = indexes;
        }

        public List<ForeignKeyDef> getForeignKeys() {
            return foreignKeys;
        }

        public void setForeignKeys(List<ForeignKeyDef> foreignKeys) {
            this.foreignKeys = foreignKeys;
        }
    }

    public static class ColumnDef {
        private String name;
        private String type;
        private boolean nullable = true;
        private boolean autoIncrement;
        private String defaultValue;
        private int length;
        private int precision;
        private int scale;
        private String comment;

        public ColumnDef() {
        }

        public ColumnDef(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isNullable() {
            return nullable;
        }

        public void setNullable(boolean nullable) {
            this.nullable = nullable;
        }

        public boolean isAutoIncrement() {
            return autoIncrement;
        }

        public void setAutoIncrement(boolean autoIncrement) {
            this.autoIncrement = autoIncrement;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getPrecision() {
            return precision;
        }

        public void setPrecision(int precision) {
            this.precision = precision;
        }

        public int getScale() {
            return scale;
        }

        public void setScale(int scale) {
            this.scale = scale;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

    public static class IndexDef {
        private String name;
        private List<String> columns;
        private boolean unique;

        public IndexDef() {
        }

        public IndexDef(String name, List<String> columns) {
            this.name = name;
            this.columns = columns;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getColumns() {
            return columns;
        }

        public void setColumns(List<String> columns) {
            this.columns = columns;
        }

        public boolean isUnique() {
            return unique;
        }

        public void setUnique(boolean unique) {
            this.unique = unique;
        }
    }

    public static class ForeignKeyDef {
        private String name;
        private String column;
        private String referencedTable;
        private String referencedColumn;
        private String onDelete = "CASCADE";
        private String onUpdate = "CASCADE";

        public ForeignKeyDef() {
        }

        public ForeignKeyDef(String name, String column, String referencedTable, String referencedColumn) {
            this.name = name;
            this.column = column;
            this.referencedTable = referencedTable;
            this.referencedColumn = referencedColumn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getReferencedTable() {
            return referencedTable;
        }

        public void setReferencedTable(String referencedTable) {
            this.referencedTable = referencedTable;
        }

        public String getReferencedColumn() {
            return referencedColumn;
        }

        public void setReferencedColumn(String referencedColumn) {
            this.referencedColumn = referencedColumn;
        }

        public String getOnDelete() {
            return onDelete;
        }

        public void setOnDelete(String onDelete) {
            this.onDelete = onDelete;
        }

        public String getOnUpdate() {
            return onUpdate;
        }

        public void setOnUpdate(String onUpdate) {
            this.onUpdate = onUpdate;
        }
    }
}