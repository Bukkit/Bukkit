package org.bukkit.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.persistence.annotation.EntityInfo;
import org.bukkit.persistence.annotation.FieldInfo;
import org.bukkit.persistence.annotation.PersistClass;
import org.bukkit.persistence.core.PersistedClass;
import org.bukkit.persistence.core.Schema;
import org.bukkit.persistence.dao.CommandSenderData;
import org.bukkit.persistence.dao.PlayerData;
import org.bukkit.persistence.data.DataStore;
import org.bukkit.persistence.data.sql.SqlLiteStore;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BlockVector;

/** 
 * The main Persistence interface.
 * 
 * This class is a singleton- use Persistence.getInstance or PersistencePlugin.getPersistence
 * to retrieve the instance.
 * 
 * @author NathanWolf
 */
public class Persistence
{	
	/**
	 *  Persistence is a singleton, so we hide the constructor.
	 *  
	 *  Use PersistencePlugin.getInstance to retrieve a reference to Persistence safely.
	 *  
	 *  @see PersistencePlugin#getPersistence()
	 *  @see Persistence#getInstance()
	 */
	protected Persistence()
	{
	}
	
	/**
	 * Retrieve the Logger that Persistence uses for debug messages and errors.
	 * 
	 * Currently, this is hard-coded to the Minecraft server logger.
	 * 
	 * @return A Logger that can be used for errors or debugging.
	 */
	public static Logger getLogger()
	{
		return log;
	}
	
	/**
	 * Populates a list of all instances of a specified type.
	 * 
	 * This is a parameterized function. It will populate a list with object instances for a given type. An example call:
	 * 
	 * List<MyObject> myInstances = new ArrayList<MyObject>();
	 * persistence.getAll(myInstances, MyObject.class);
	 * 
	 * This would populate the myInstances list with any persisted MyObject instances. You must ensure that your List is of a
	 * compatible type with the objects you are retrieving.
	 * 
	 * @param <T> The base type of object. This is an invisible parameter, you don't need to worry about it
	 * @param objects A List (needs not be empty) to populate with object instances
	 * @param objectType The type of object to retrieve
	 */
	public <T> void getAll(List<T> objects, Class<T> objectType)
	{	
		synchronized(cacheReadLock)
		{
			PersistedClass persistedClass = getPersistedClass(objectType);
			if (persistedClass == null)
			{
				return;
			}
			
			persistedClass.getAll(objects);	
		}
	}
	
	public void remove(Object o)
	{
		synchronized(cacheWriteLock)
		{
			PersistedClass persistedClass = getPersistedClass(o.getClass());
			if (persistedClass == null)
			{
				return;
			}
			
			persistedClass.remove(o);
		}
	}
	
	/**
	 * Merge a list of objects into the data store.
	 * 
	 * Use this method to completely replace the stored entity list for a type of entity. Entities not in the "objects" list will
	 * be deleted, new objects will be added, and existing objects merged.
	 * 
	 * This is a parameterized function. It will populate a list with object instances for a given type. An example call:
	 * 
	 * List<MyObject> myInstances = new ArrayList<MyObject>();
	 * ... Fill myInstances with some data
	 * persistence.putAll(myInstances, MyObject.class);
	 * 
	 * This would replace all instances of MyObject with the instances in the myInstances list.
	 * 
	 * TODO: Currently, this method replaces all of the instances directly. This would
	 * invalidate any externally maintained references, so it needs to merge data instead.
	 * 
	 * @param <T> The base type of object. This is an invisible parameter, you don't need to worry about it
	 * @param objects A list of objects to store
	 * @param objectType The type of object to replace instances
	 */
	public <T> void putAll(List<T> objects, Class<T> objectType)
	{
		synchronized(cacheReadLock)
		{
			synchronized(cacheWriteLock)
			{
				PersistedClass persistedClass = getPersistedClass(objectType);
				if (persistedClass == null)
				{
					return;
				}
				
				persistedClass.putAll(objects);	
			}
		}
	}
	
	/**
	 * Retrieve an instance of the specified type.
	 * 
	 * This method retrieves an object instance from the data store, based on the object's id.
	 * The id passed in should match the type of this object's id field- a String for a String id, for instance.
	 * 
	 * If an object with the specified id cannot be found, the method returns null;
	 * 
	 * @param <T> The base type of object. This is an invisible parameter, you don't need to worry about it
	 * @param id The id of the object to lookup
	 * @param objectType The type of object to search for
	 * @return The object instance with the specified id, or null if not found
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Object id, Class<T> objectType)
	{
		synchronized(cacheReadLock)
		{
			PersistedClass persistedClass = getPersistedClass(objectType);
			if (persistedClass == null)
			{
				return null;
			}
			
			Object result = persistedClass.get(id);
			if (result == null) return null;
			return (T)result;	
		}
	}
	
	/**
	 * Add an object to the data store.
	 * 
	 * This only adds the object to the cache. At save time, the cached object will trigger a data save.
	 * 
	 * If this is the first instance of this type of object to ever be stored, the schema and tables needed to store this object will be created
	 * at save time. Then, the tables will be populated with this object's data.
	 * 
	 * @param persist The object to persist
	 * @return false if, for some reason, the storage failed.
	 */
	public boolean put(Object persist)
	{
		if (persist == null) return false;
		
		synchronized(cacheReadLock)
		{
			synchronized(cacheWriteLock)
			{
				PersistedClass persistedClass = getPersistedClass(persist.getClass());
				if (persistedClass == null)
				{
					return false;
				}
				
				persistedClass.put(persist);
			}
		}
		return true;		
	}
	
	/**
	 * Force a save of all cached data.
	 * 
	 * This only saves dirty data- unmodified data is not saved back to the database.
	 * Persistence calls save() internally on server shutdown, player login, and player logout. So, calling save is not
	 * mandatory- you only need to use it to force an immediate save.
	 * 
	 */
	public void save()
	{
		for (PersistedClass persistedClass : persistedClasses)
		{
			persistedClass.save();
		}
	}
	
	
	/**
	 * Clear all data.
	 * 
	 * This is currently the method used to clear the cache and reload data, however it is flawed.
	 * It will probably be replaced with a "reload" method eventually.
	 */
	public void clear()
	{
		persistedClasses.clear();
		persistedClassMap.clear();
		schemaMap.clear();
		schemas.clear();
	}
	
	/**
	 * Return the singleton instance for Persistence.
	 * 
	 * Will create the instance if it does not exist, but most likely the PersistencePlugin has done this already.
	 * 
	 * It is more adviseable to use PersistencePlugin.getPersistence() so that Persistence gets initialized properly
	 * 
	 * TODO : better bukkit integration, pre-bind data folder or server instance?
	 * Not even sure where "." is going to store- assuming server root?
	 * 
	 * @return the Persistence singleton instance
	 * @see PersistencePlugin#getPersistence()
	 */
	public static Persistence getInstance()
	{
		synchronized(instanceLock)
		{
			if (instance == null)
			{
				instance = new Persistence();
				instance.initialize(new File("."));
			}
		}
		return instance;
	}
	
	/**
	 * Retrieve or create the persistence store for a particular schema.
	 * 
	 * Each schema gets its own persistent connection and database schema.
	 * 
	 * This is an internal function that doesn't necessarily need to be called.
	 * 
	 * @param schema The schema name to retrieve
	 * @return The data store for the given schema
	 */
	public DataStore getStore(String schema)
	{
		DataStore store = null;
		synchronized(dataLock)
		{
			schema = schema.toLowerCase();
			store = schemaStores.get(schema);
			if (store == null)
			{
				store = createStore();
				store.initialize(schema, this);
				schemaStores.put(schema, store);
				stores.add(store);
			}
		}
		return store;
	}
	
	/**
	 * Retrieve a Schema definition, with a list of PersistedClasses.
	 * 
	 * This function is used for inspecting schemas and entities.
	 * 
	 * @param schemaName The schema to retrieve
	 * @return A Schema definition class, containing entity classes
	 */
	public Schema getSchema(String schemaName)
	{
		return schemaMap.get(schemaName);
	}
	
	/**
	 * Retrieve a list of definitions for all known schemas.
	 * 
	 * This function is used for inspecting schemas and entities.
	 * 
	 * @return The list of schemas
	 */
	public List<Schema> getSchemaList()
	{
		List<Schema> schemaList = new ArrayList<Schema>();
		schemaList.addAll(schemas);
		return schemaList;
	}
	
	/**
	 * Retrieve or create a persisted class, using the annotations built into the class.
	 * 
	 * @param persistClass The annotated Class to persist
	 * @return The persisted class definition, or null if failure
	 */
	public PersistedClass getPersistedClass(Class<? extends Object> persistClass)
	{		
		/*
		 * Look for Class annotations
		 */
		
		// TODO: Lookup from schema/name map ... hm... uh, how to do this without the annotation?
		// I guess pass in one, and then other persisted classes can request data from their own schema...
		PersistedClass persistedClass = persistedClassMap.get(persistClass);
		if (persistedClass == null)
		{
			PersistClass entityAnnotation = persistClass.getAnnotation(PersistClass.class);
			
			if (entityAnnotation == null)
			{
				log.warning("Persistence: class " + persistClass.getName() + " does not have the @PersistClass annotation.");
				return null;
			}
		
			persistedClass = getPersistedClass(persistClass, new EntityInfo(entityAnnotation));
		}

		return persistedClass;
	}
	
	/**
	 * Retrieve or create a persisted class definition for a given class type.
	 * 
	 * This can be used to create a persisted class based on a existing class.
	 * 
	 * @param persistType The Class to persist
	 * @param entityInfo Information on how to persist this class 
	 * @return The persisted class definition, or null if failure
	 */
	public PersistedClass getPersistedClass(Class<? extends Object> persistType, EntityInfo entityInfo)
	{	
		PersistedClass persistedClass = persistedClassMap.get(persistType);
		if (persistedClass == null)
		{
			persistedClass = new PersistedClass(entityInfo);
			if (!persistedClass.bind(persistType))
			{
				return null;
			}
			String schemaName = persistedClass.getSchema();
			Schema schema = schemaMap.get(schemaName);
			if (schema == null)
			{
				schema = new Schema();
				schema.setName(schemaName);
				schemas.add(schema);
				schemaMap.put(schemaName, schema);
			}
			schema.addPersistedClass(persistedClass);
			persistedClasses.add(persistedClass);
			persistedClassMap.put(persistType, persistedClass);
			
			// Deferred bind refernces- to avoid circular reference issues
			persistedClass.bindReferences();
		}
		return persistedClass;
	}
	
	/*
	 * Protected members
	 */
	
	protected DataStore createStore()
	{
		// Only SqlLite supported for now!
		// TODO : Support MySQL
		SqlLiteStore store = new SqlLiteStore();
		store.setDataFolder(dataFolder);
		return store;
	}
	
	protected void initialize(File dataFolder)
	{
		this.dataFolder = dataFolder;
		dataFolder.mkdirs();

		updateGlobalData();
		
		// TODO : load configuration, sql connection params, etc?
	}
	
	protected void updateGlobalData()
	{
		// Update CommandSenders
		updateCommandSender("player" , Player.class);
		
		// Create BlockVector class
		EntityInfo vectorInfo = new EntityInfo("global", "vector");
		FieldInfo vectorId = new FieldInfo("id");
		FieldInfo fieldX = new FieldInfo("x");
		FieldInfo fieldY = new FieldInfo("y");
		FieldInfo fieldZ = new FieldInfo("z");
		
		// Make the hash code the id, make it readonly, and override its storage name
		vectorId.setIdField(true);
		vectorId.setReadOnly(true);
	
		// Bind each field- this is a little awkward right now, due to the
		// assymmetry (lack of setBlockX type setters).
		fieldX.setGetter("getBlockX");
		fieldY.setGetter("getBlockY");
		fieldZ.setGetter("getBlockZ");
		fieldX.setSetter("setX");
		fieldY.setSetter("setY");
		fieldZ.setSetter("setZ");
		
		// Create the class definition
		PersistedClass persistVector = getPersistedClass(BlockVector.class, vectorInfo);
		persistVector.persistField("hashCode", vectorId);
		
		persistVector.persistField("x", fieldX);
		persistVector.persistField("y", fieldY);
		persistVector.persistField("z", fieldZ);
		
		persistVector.validate();
		
		// TODO: Materials
	}

	protected CommandSenderData updateCommandSender(String senderId, Class<?> senderClass)
	{
		CommandSenderData sender = get(senderId, CommandSenderData.class);
		if (sender == null)
		{
			sender = new CommandSenderData(senderId, senderClass);
			put(sender);
		}		
		return sender;
	}
	
	public void disconnect()
	{
		synchronized(dataLock)
		{
			for (DataStore store : stores)
			{
				store.disconnect();
			}
			stores.clear();
			schemaStores.clear();
		}
	}
	
	public boolean hasPermission(Player player, String node)
	{
		return hasPermission(player, node, true);
	}
	
	public boolean hasPermission(Player player, String node, boolean defaultValue)
	{
		if (player == null) return defaultValue;
		
		// Check for su status- this can be toggled by ops with the /su command
		PlayerData playerData = get(player.getName(), PlayerData.class);
		if (playerData != null && playerData.isSuperUser()) return true;
		
		/* g'bye!
		if (permissions != null)
		{
			return Permissions.Security.permission(player, node);
		}
		*/
		
		return defaultValue;
	}
	
	/*
	 * private data
	 */
	
	private File dataFolder = null;
	
	private final HashMap<Class<? extends Object>, PersistedClass> persistedClassMap = new HashMap<Class<? extends Object>, PersistedClass>(); 
	private final List<PersistedClass> persistedClasses = new ArrayList<PersistedClass>(); 
	private final List<Schema> schemas = new ArrayList<Schema>();
	private final HashMap<String, Schema> schemaMap = new HashMap<String, Schema>();

	private static final Logger log = Logger.getLogger("Minecraft");
	
	private final HashMap<String, DataStore> schemaStores = new HashMap<String, DataStore>();
	private final List<DataStore> stores = new ArrayList<DataStore>();
	
	private static final Object dataLock = new Object();
	private static final Object instanceLock = new Object();
	private static final Object cacheReadLock = new Object();
	private static final Object cacheWriteLock = new Object();
	
	private static Persistence instance = null;
}
