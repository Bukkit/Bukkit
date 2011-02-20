package org.bukkit.persistence.data;

import java.util.List;

public interface DataStore
{
	/**
	 * Connect to the data store represented by this store's schema
	 * 
	 * @return true if success
	 */
	public abstract boolean connect();
	
	/**
	 * Disconnect from the connected store
	 */
	public abstract void disconnect();
	
	/**
	 * Will attempt to create a table- check to see if the table exists before
	 * calling this.
	 * 
	 * @param table The table definition
	 * @return true if success
	 * @see #tableExists(DataTable)
	 * @see #validateTable(DataTable)
	 * 
	 */
	public abstract boolean createTable(DataTable table);
	
	/**
	 * Will create a table if it does not exist.
	 * 
	 * Does not do data migration- that is handled by PersistedClass directly.
	 * 
	 * @param table The table definition
	 * @return true if success
	 * @see #tableExists(DataTable)
	 */
	public boolean validateTable(DataTable table);
	
	/**
	 * Clear a table of data for the specified ids, keeping the data included in "table".
	 * 
	 * This is used to clear list sub-tables in order to handle removed items.
	 * 
	 * Only rows with a matching primary id will be dropped, and then
	 * only if they are not specified in the passed-in table.
	 * 
	 * @param table The table to clear, containing the rows to keep
	 * @param ids A list of primary ids of objects to clear
	 * @return true if success
	 */
	public boolean clearIds(DataTable table, List<Object> ids);
	
	/**
	 * Clear a table, except for the objects contained in the specified DataTable
	 * 
	 * This is used to remove deleted objects from a data store.
	 * 
	 * @param table The table to clear, containing the rows to keep
	 * @return true if success
	 */
	public boolean clear(DataTable table);
		
	/**
	 * Completely drop a table, allowing it to be re-created.
	 * 
	 * @param table The table to drop.
	 * @return true on success
	 */
	public boolean reset(DataTable table);
	
	/**
	 * Load a table into memory.
	 * 
	 * Assumes that the table already exists.
	 * 
	 * @param table The table to load
	 * @return true if success
	 */
	public boolean load(DataTable table);
	
	/**
	 * Save a table to the data store.
	 * 
	 * Assumes that the table already exists.
	 * 
	 * @param table the table
	 * @return true if success
	 */
	public boolean save(DataTable table);
	
	/**
	 * Check to see if the specified table exists.
	 * 
	 * @param table The table to check
	 * @return true if the table exists
	 */
	public boolean tableExists(DataTable table);
	
	/**
	 * Check to see if this is a read-only data store
	 * 
	 * @return true if the store cannot be written to
	 */
	public boolean isReadOnly();
}
