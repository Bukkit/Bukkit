package org.bukkit.persistence.data;

import java.util.List;

public interface DataRow
{

	/**
	 * Retrieve a data field from this row by name.
	 * 
	 * @param columnName The name of the DataField to find
	 * @return A data field, or null if not found
	 */
	public DataField get(String columnName);
	
	/**
	 * Fills a list of DataFields- you must create the list first.
	 * 
	 * @param fields the list of fields to populate
	 * @return How many items were added to the list
	 */
	public <T extends DataField> int getFields(List<T> fields);
	
	/**
	 * Add a new field to this row.
	 * 
	 * This is used to set up a row for writing to a store. 
	 * Do not attempt to add the same field name twice.
	 * 
	 * @param newField The datafield to add.
	 */
	public void add(DataField newField);
	
	
	/**
	 * Needs documentation
	 * 
	 * @param fieldName
	 * @return
	 */
	public DataField getField(String fieldName);
}
