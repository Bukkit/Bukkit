package org.bukkit.persistence.data;

import java.util.ArrayList;
import java.util.List;

public class DataTable
{
	public DataTable(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public final List<DataRow> getRows()
	{
		return rows;
	}
	
	public void addRow(DataRow row)
	{
		rows.add(row);
	}
	
	public DataRow getHeader()
	{
		if (rows.size() == 0) return null;
		
		return rows.get(0);
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public List<String> getIdFieldNames()
	{
		return idFieldNames;
	}
	
	public void addIdFieldName(String idFieldName)
	{
		if (!idFieldNames.contains(idFieldName))
		{
			idFieldNames.add(idFieldName);
		}
	}
	
	public void createHeader()
	{
		if (rows.size() > 0) return;
		
		DataRow headerRow = new DataRow(this);
		rows.add(headerRow);
	}

	protected String name;
	protected List<String> idFieldNames = new ArrayList<String>();
	protected List<DataRow> rows = new ArrayList<DataRow>();
}
