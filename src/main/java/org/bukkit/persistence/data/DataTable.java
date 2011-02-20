package org.bukkit.persistence.data;

import java.util.List;
// TODO: Document!
public interface DataTable
{
	public String getName();
	
	public void addRow(DataRow row);
	
	public DataRow getHeader();
	
	public void setName(String name);
	
	public List<String> getIdFieldNames();
	
	public void addIdFieldName(String idFieldName);
	
	public void createHeader();
	
	/**
	 * Fills a list of DataRows- you must create the list first.
	 * 
	 * @param rows the list of rows to populate
	 * @return How many items were added to the list
	 */
	public <T extends DataRow> int getRows(List<T> rows);
	
}
