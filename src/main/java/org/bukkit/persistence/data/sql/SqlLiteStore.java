package org.bukkit.persistence.data.sql;

import java.io.File;
import java.util.List;

import org.bukkit.persistence.data.DataField;
import org.bukkit.persistence.data.DataRow;
import org.bukkit.persistence.data.DataTable;
import org.bukkit.persistence.data.DataType;


public class SqlLiteStore extends SqlStore
{
	@Override
	public String getDriverClassName() { return "org.sqlite.JDBC"; }

	@Override
	public String getDriverFileName() { return "sqlite"; }
	
	@Override
	public String getMasterTableName() { return "sqlite_master"; }
	
	@Override
	public String getConnectionString(String schema, String user, String password) 
	{ 
		File sqlLiteFile = new File(dataFolder, schema + ".db");
		return "jdbc:sqlite:" + sqlLiteFile.getAbsolutePath();
	}
	
	@Override
	public String getTypeName(DataType dataType)
	{
		switch (dataType)
		{
			case INTEGER:
				return "INTEGER";
			case LONG:
				return "INTEGER";
			case BOOLEAN:
				return "INTEGER";
			case DATE:
				return "INTEGER";
			case DOUBLE:
				return "REAL";
			case FLOAT:
				return "REAL";
			case STRING:
				return "TEXT";
		}
		return null;
	}
	
	// This is all really pointless since SqlLite doesn't even really type things :\
	public DataType getTypeFromName(String typeName)
	{
		if (typeName.equalsIgnoreCase("INTEGER"))
		{
			return DataType.LONG;
		}
		else if (typeName.equalsIgnoreCase("REAL"))
		{
			return DataType.DOUBLE;
		}
		else if (typeName.equalsIgnoreCase("TEXT"))
		{
			return DataType.STRING;
		}
		
		return DataType.NULL;
	}


	@Override
	public DataTable getTableSchema(String tableName)
	{
		DataTable currentTable = new DataTable(tableName);
		DataRow headerRow = currentTable.getHeader();
		
		DataTable pragmaTable = new DataTable("pragma");
		String pragmaSql = "PRAGMA TABLE_INFO(\"" + tableName +"\")";
		load(pragmaTable, pragmaSql);
		
		List<DataRow> pragmaRows = pragmaTable.getRows();
		for (DataRow row : pragmaRows)
		{
			DataField nameField = row.get("name");
			DataField typeField = row.get("type");
			
			if (nameField == null || typeField == null) continue;
			
			String fieldName = (String)nameField.getValue();
			String fieldType = (String)typeField.getValue(); 
			DataType dataType = getTypeFromName(fieldType);
			
			DataField newColumn = new DataField(fieldName, dataType);
			headerRow.add(newColumn);
		}
		
		return currentTable;
	}
	
}
