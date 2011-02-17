package org.bukkit.persistence.data;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.persistence.Persistence;

/**
 * An abstract class representing a data store.
 * 
 * @author NathanWolf
 *
 */
public abstract class DataStore
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
	 * Validate the table structure for a given table.
	 * 
	 * Will create the table if it does not exist.
	 * 
	 * It should also migrate table data and modify table structures as needed.
	 * 
	 * @param table The table definition
	 * @return true if success
	 */
	public abstract boolean validateTable(DataTable table);
	
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
	public abstract boolean clearIds(DataTable table, List<Object> ids);
	
	/**
	 * Clear a table, except for the objects contained in the specified DataTable
	 * 
	 * This is used to remove deleted objects from a data store.
	 * 
	 * @param table The table to clear, containing the rows to keep
	 * @return true if success
	 */
	public abstract boolean clear(DataTable table);
		
	/**
	 * Completely drop a table, allowing it to be re-created.
	 * 
	 * @param table The table to drop.
	 * @return true on success
	 */
	public abstract boolean reset(DataTable table);
	
	/**
	 * Load a table into memory.
	 * 
	 * Assumes that the table already exists.
	 * 
	 * @param table The table to load
	 * @return true if success
	 */
	public abstract boolean load(DataTable table);
	
	/**
	 * Save a table to the data store.
	 * 
	 * Assumes that the table already exists.
	 * 
	 * @param table the table
	 * @return true if success
	 */
	public abstract boolean save(DataTable table);
	
	/**
	 * Check to see if the specified table exists.
	 * 
	 * @param table The table to check
	 * @return true if the table exists
	 */
	public abstract boolean tableExists(DataTable table);
	
	/**
	 * Check to see if this is a read-only data store
	 * 
	 * @return true if the store cannot be written to
	 */
	public boolean isReadOnly()
	{
		return false;
	}
	
	/**
	 * Initialize this data store.
	 * 
	 * This is called internally by Persistence.
	 * 
	 * @param schema The schema this data store connects to
	 * @param p The Persistence instance this data store should use.
	 */
	public void initialize(String schema, Persistence p)
	{
		persistence = p;
		this.schema = schema;
	}
	
	public static void logStoreAccess(String message, int rowCount)
	{
		if (logSqlUse)
		{
			log.info("Persistence: " + String.format(message, rowCount));
		}
	}
	
	public static void logStoreAccess(String message)
	{
		if (logSqlUse)
		{
			log.info("Persistence: " + message);
		}
	}
	
	protected static boolean logSqlStatements = false;
	protected static boolean logSqlUse = false;
	
	protected Persistence persistence = null;
	protected String schema;
	protected static Logger log = Persistence.getLogger();
	
}
