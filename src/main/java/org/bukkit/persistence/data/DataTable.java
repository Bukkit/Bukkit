package org.bukkit.persistence.data;

import java.util.List;
// TODO: Document!
public interface DataTable
{
	public String getName();
	
	public List<DataRow> getRows();
	
	public void addRow(DataRow row);
	
	public DataRow getHeader();
	
	public void setName(String name);
	
	public List<String> getIdFieldNames();
	
	public void addIdFieldName(String idFieldName);
	
	public void createHeader();
}
