package org.bukkit.persistence.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.persistence.Persistence;


/**
 * Used to represent a single row of data from a data store.
 * 
 * @author NathanWolf
 *
 */
public class DataRow
{
	/**
	 * Create an empty DataRow.
	 * 
	 * This can be used to populate with data for setting data.
	 * 
	 */
	public DataRow(DataTable dataTable)
	{
		this.table = dataTable;
	}
	
	/**
	 * Retrieve a data field from this row by name.
	 * 
	 * @param columnName The name of the DataField to find
	 * @return A data field, or null if not found
	 */
	public DataField get(String columnName)
	{
		return fieldMap.get(columnName);
	}
	
	/**
	 * Retrieve all of the fields in this row.
	 * 
	 * @return The internal list of fields
	 */
	public final List<DataField> getFields()
	{
		return fields;
	}
	
	/**
	 * Add a new field to this row.
	 * 
	 * This is used to set up a row for writing to a store. 
	 * Do not attempt to add the same field name twice.
	 * 
	 * @param newField The datafield to add.
	 */
	public void add(DataField newField)
	{
		String fieldName = newField.getName();
		if (fieldName == null || fieldName.length() <= 0)
		{
			log.warning("Persistence: Empty DataRow name");
			return;			
		}
		
		DataField existingField = fieldMap.get(fieldName);
		if (existingField != null)
		{
			log.warning("Persistence: Warning, duplicate field in DataRow: " + fieldName);
			return;
		}
		
		if (newField.isIdField())
		{
			table.addIdFieldName(newField.getName());
		}
		
		fieldMap.put(fieldName, newField);
		fields.add(newField);
	}
	
	public DataField getField(String fieldName)
	{
		return fieldMap.get(fieldName);
	}
	
	protected DataTable table;
	protected static Logger log = Persistence.getLogger();
	protected HashMap<String, DataField> fieldMap = new HashMap<String, DataField>();
	protected List<DataField> fields = new ArrayList<DataField>();
}
