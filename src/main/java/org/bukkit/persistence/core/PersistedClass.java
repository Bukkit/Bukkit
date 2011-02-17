package org.bukkit.persistence.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.persistence.Persistence;
import org.bukkit.persistence.annotation.EntityInfo;
import org.bukkit.persistence.annotation.FieldInfo;
import org.bukkit.persistence.annotation.PersistField;
import org.bukkit.persistence.data.DataField;
import org.bukkit.persistence.data.DataRow;
import org.bukkit.persistence.data.DataStore;
import org.bukkit.persistence.data.DataTable;
import org.bukkit.persistence.data.DataType;

/**
 * Represents and manages a single persisted class.
 * 
 * This class binds to a Class, looking for @Persist and @PersistClass
 * tags.
 * 
 * It will keep track of the persisted fields of a class, and handle
 * creating and caching object instances of that class.
 * 
 * @author NathanWolf
 *
 */
public class PersistedClass
{
	public PersistedClass(EntityInfo entityInfo)
	{
		this.entityInfo = entityInfo;
	}
	
	public PersistedClass(PersistedClass copy, PersistedField container)
	{
		this.contained = true;
		this.defaultStore = copy.defaultStore;
		this.container = container;
		this.name = copy.name;
		this.schema = copy.schema;
		this.entityInfo = copy.entityInfo;
		this.persistClass = copy.persistClass;
		
		// TODO: Make sure it's ok to share fields!
		for (PersistedField field : copy.fields)
		{
			// If a field is an id field, we don't care about it in the container, unless the container is also the id field
			// TODO: Consider this logic for validity
			if (!field.isIdField() || container.isIdField())
			{
				addField(field.clone(), field.getFieldInfo());
			}
		}
	}
	
	public boolean bind(Class<? extends Object> persistClass)
	{
		this.persistClass = persistClass;
		
		cacheObjects = entityInfo.isCached();
		schema = entityInfo.getSchema();
		name = entityInfo.getName();
		
		name = name.replace(" ", "_");
		schema = schema.replace(" ", "_");
		
		defaultStore = Persistence.getInstance().getStore(schema);
		
		if (!cacheObjects)
		{
			log.warning("Persistence: class " + persistClass.getName() + ": non-cached objects no supported, yet.");
			return false;
		}
		
		/*
		 * Find fields, getters and setters
		 */
		
		idField = null;

		for (Field classField : persistClass.getDeclaredFields())
		{
			PersistField persist = classField.getAnnotation(PersistField.class);
			if (persist != null)
			{
				PersistedField field = PersistedField.tryCreate(new FieldInfo(persist), classField, this);		
				if (field == null)
				{
					log.warning("Persistence: Field " + persistClass.getName() + "." + classField.getName() + " is not persistable, type=" + classField.getType().getName());
				}
				else
				{
					addField(field, new FieldInfo(persist));
				}
			}
		}
	
		for (Method method : persistClass.getDeclaredMethods())
		{
			PersistField persist = method.getAnnotation(PersistField.class);
			if (persist != null)
			{
				PersistedField field = PersistedField.tryCreate(new FieldInfo(persist), method, this);
				if (field == null)
				{
					Class<?> type = method.getReturnType();
					if (type == void.class && method.getParameterTypes().length > 0)
					{
						type = method.getParameterTypes()[0];
					}
					log.warning("Persistence: Field " + persistClass.getName() + "." + method.getName() + " is not persistable, type=" +type.getName());
				}
				else
				{
					addField(field, new FieldInfo(persist));
				}
			}
		}
		
		return true;
	}

	/*
	 * TODO: make this check for duplicate fields, and also maybe rename to getPersistedField
	 */
	public PersistedField persistField(String fieldName)
	{
		return persistField(fieldName, new FieldInfo());
	}
	
	public PersistedField persistField(String fieldName, FieldInfo fieldInfo)
	{
		PersistedField persistField = null;
		String getterName = fieldName;
		if (fieldInfo.getGetter() != null && fieldInfo.getGetter().length() > 0)
		{
			getterName = fieldInfo.getGetter();
		}
		Method getter = PersistedField.findGetter(getterName, persistClass);
		
		if (getter != null)
		{
			persistField = PersistedField.tryCreate(fieldInfo, getter, this);		
		}
		else
		{
			if (fieldInfo.getField() != null && fieldInfo.getField().length() > 0)
			{
				fieldName = fieldInfo.getField();
			}
			Field field = PersistedField.findField(fieldName, persistClass);
			if (field != null)
			{
				persistField = PersistedField.tryCreate(fieldInfo, field, this);
			}
		}
		if (persistField != null)
		{
			addField(persistField, fieldInfo);
		}
		return persistField;
	}
	
	
	public boolean addField(PersistedField field, FieldInfo fieldInfo)
	{
		if (fieldInfo.isIdField())
		{
			if (idField != null)
			{
				log.warning("Persistence: class " + persistClass.getName() + ": can't have more than one id field");
				return false;
			}
			if (fieldInfo.isContained())
			{
				log.warning("Persistence: class " + persistClass.getName() + ": an id field can't be a contained entity");
				return false;
			}
			idField = field;
		}
		
		if (field instanceof PersistedList)
		{
			PersistedList list = (PersistedList)field;
			if (list.getListDataType() == DataType.LIST)
			{
				log.warning("Persistence: class " + persistClass.getName() + ": lists of lists not supported");
				return false;
			}
			externalFields.add(list);
			if (list.isObject())
			{
				referenceFields.add(list);
			}
		}
		else if (field instanceof PersistedObject)
		{
			PersistedObject reference = (PersistedObject)field;
			internalFields.add(reference);
			if (reference.isObject())
			{
				referenceFields.add(reference);
			}
		}
		else
		{
			if (fieldInfo.isContained())
			{
				log.warning("Persistence: class " + persistClass.getName() + ": only List and Object fields may be contained");
				return false;
			}
			if (fieldInfo.isAutogenerated())
			{
				if (!fieldInfo.isIdField())
				{
					log.warning("Persistence: class " + persistClass.getName() + ": only id fields may be autogenerated");
				}
				else if (!field.getType().isAssignableFrom(Integer.class) && !field.getType().isAssignableFrom(int.class))
				{
					log.warning("Persistence: class " + persistClass.getName() + ": only integer fields may be autogenerated");
				}
			}
			internalFields.add(field);
		}
		
		field.setContainer(container);

		fields.add(field);
		
		return true;
	}
	
	public void bindReferences()
	{
		for (PersistedField field : fields)
		{
			field.bind();
		}
	}
	
	public boolean validate()
	{
		if (idField == null && !isContained())
		{
			log.warning("Persistence: class " + persistClass.getName() + ": must specify one id field. Use an auto int if you need.");
			return false;
		}
		
		if (fields.size() == 0)
		{
			log.warning("Persistence: class " + persistClass.getName() + ": has no persisted fields.");
			return false;			
		}
		
		for (PersistedReference reference : referenceFields)
		{
			if (reference.getReferenceType() == null)
			{
				log.warning("Persistence: class " + persistClass.getName() + ", field " + reference.getName() + " references a non-persistable class: " + reference.getType().getName());
				return false;	
			}
		}
	
		return true;
	}
	
	public boolean isContained()
	{
		return entityInfo.isContained();
	}
	
	public void put(Object o)
	{
		checkLoadCache();
				
		Object id = getId(o);
		CachedObject co = cacheMap.get(id);
		if (co == null)
		{
			co = addToCache(o);
		}
		
		// TODO: merge
		co.setCached(cacheObjects);
		co.setObject(o);
		dirty = true;
	}
	
	public void remove(Object o)
	{
		Object id = getId(o);
		removeFromCache(id);
		dirty = true;
	}
	
	protected Object getById(PersistedField idField, HashMap<Object, CachedObject> fromCache, Object id)
	{
		if (idField == null || id == null) return null;
		if (id.getClass().isAssignableFrom(idField.getType()))
		{
			CachedObject cached = fromCache.get(id);
			if (cached != null)
			{
				return cached.getObject();
			}
		}
		else
		{
			// Try to do some fancy casting.
			// This is mainly here to avoid the Integer/int problem.
			
			DataField requestId = new DataField(id);
			id = requestId.getValue(idField.getType());
				
			CachedObject cached = fromCache.get(id);
			if (cached != null)
			{
				return cached.getObject();
			}
		}
		
		return null;
	}

	public Object get(Object id, Object defaultValue)
	{
		checkLoadCache();
		Object result = get(id);
		if (result != null)
		{
			return result;
		}
		
		if (defaultValue == null) return null;
		
		CachedObject cached = addToCache(defaultValue);
		return cached.getObject();
	}
	
	public Object get(Object id)
	{
		checkLoadCache();
		PersistedField idField = getIdField();
		if (idField == null) return null;
		
		Object result = getById(idField, cacheMap, id);
		if (result == null)
		{
			idField = idField.getConcreteField();
			result = getById(idField, concreteIdMap, id);
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public <T> void getAll(List<T> objects)
	{
		checkLoadCache();
		for (CachedObject cachedObject : cache)
		{
			Object object = cachedObject.getObject();
			if (persistClass.isAssignableFrom(object.getClass()))
			{
				objects.add((T)object);
			}
		}
	}
	
	public void putAll(List<? extends Object> objects)
	{
		checkLoadCache();
		// TODO: merge...
	}
	
	public void clear()
	{
		cacheMap.clear();
		concreteIdMap.clear();
		cache.clear();
		loadState = LoadState.UNLOADED;
	}
	
	public void reset()
	{
		reset(defaultStore);
	}
	
	public void reset(DataStore store)
	{
		if (!store.connect()) return;
		
		DataTable resetTable = getClassTable(); 
		store.reset(resetTable);
		
		// Reset any list sub-tables
		for (PersistedList list : externalFields)
		{
			DataTable listTable = getListTable(list);
			store.reset(listTable);
		}
		
		maxId = 1;
	}
	
	public boolean isDirty()
	{
		return dirty;
	}
	
	public int getFieldCount()
	{
		return fields.size();
	}
	
	public String getTableName()
	{
		return name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSchema()
	{
		return schema;
	}
	
	public Class<? extends Object> getType()
	{
		return persistClass;
	}
	
	public PersistedField getIdField()
	{
		return idField;
	}
	
	public List<PersistedField> getPersistedFields()
	{
		return fields;
	}
	
	public void save()
	{
		save(defaultStore);
	}
	
	public void save(DataStore store)
	{
		if (loadState != LoadState.LOADED) return;
		if (!dirty) return;
		
		// Drop removed objects
		if (removedFromCache.size() > 0)
		{
			DataTable clearTable = getClassTable();
			populate(clearTable, removedFromCache);			
			store.clear(clearTable);
			
			removedFromCache.clear();
			removedMap.clear();
		}
		
		// Save dirty objects
		List<CachedObject> dirtyObjects = new ArrayList<CachedObject>();
		for (CachedObject cached : cache)
		{
			if (cached.isDirty())
			{
				dirtyObjects.add(cached);
			}
		}
		
		save(dirtyObjects, store);
		dirty = false;
	}
	
	protected void populate(DataTable dataTable, List<CachedObject> instances)
	{
		for (CachedObject instance : instances)
		{
			DataRow instanceRow = new DataRow(dataTable);
			populate(instanceRow, instance.getObject());
			dataTable.addRow(instanceRow);	
		}
	}
	
	public void populate(DataRow row, Object instance)
	{
		for (PersistedField field : internalFields)
		{
			field.save(row, instance);
		}	
	}
	
	public void save(List<CachedObject> instances)
	{
		save(instances, defaultStore);
	}
	
	public void save(List<CachedObject> instances, DataStore store)
	{
		if (!store.connect()) return;
		
		// Save main class data
		DataTable classTable = getClassTable();
		populate(classTable, instances);
		store.save(classTable);
		
		// Save list data
		for (PersistedList list : externalFields)
		{
			DataTable listTable = getListTable(list);
			List<Object> instanceIds = new ArrayList<Object>();
			
			for (CachedObject instance : instances)
			{
				Object id = getId(instance.getObject());
				instanceIds.add(id);			
				list.save(listTable, instance.getObject());
			}
			
			// First, delete removed items
			store.clearIds(listTable, instanceIds);
			
			// Save new list data
			store.save(listTable);
		}	
		
		for (CachedObject cached : instances)
		{
			cached.setSaved();
		}
	}
	
	/*
	 * Protected members
	 */
	
	protected DataTable getClassTable()
	{
		DataTable classTable = new DataTable(getTableName());
		return classTable;
	}
	
	protected DataTable getListTable(PersistedList list)
	{
		DataTable listTable = new DataTable(list.getTableName());
		return listTable;
	}
	
	protected void checkLoadCache()
	{
		checkLoadCache(defaultStore);
	}
	
	protected void checkLoadCache(DataStore store)
	{
		if (loadState == LoadState.UNLOADED && cacheObjects)
		{
			loadState = LoadState.LOADING;
			try
			{
				if (store.connect())
				{
					validateTables(store);
					loadCache();
					loadState = LoadState.LOADED;
				}
			}
			catch(Exception e)
			{
				log.severe("Persistence: Exception loading cache for " + name);
				clear();
				e.printStackTrace();
				return;
			}
		}
	}
	
	protected void validateTables(DataStore store)
	{
		if (!store.connect())
		{
			return;
		}
		DataTable classTable = getClassTable();
		
		classTable.createHeader();
		populateHeader(classTable);
		
		store.validateTable(classTable);
		
		// Validate any list sub-tables
		for (PersistedList list : externalFields)
		{
			DataTable listTable = getListTable(list);
			listTable.createHeader();
			list.populateHeader(listTable);
			store.validateTable(listTable);
		}		
	}
	
	public String getContainedIdName()
	{
		String idName = getTableName();
		if (idField != null)
		{
			idName = PersistedField.getContainedName(idName, idField.getDataName());
		}
		return idName;
	}
	
	public String getContainedIdName(PersistedField container)
	{
		String idName = container.getDataName();
		if (idField != null)
		{
			idName = PersistedField.getContainedName(idName, idField.getDataName());
		}
		return idName;
	}
	
	public void populateHeader(DataTable table)
	{
		for (PersistedField field : internalFields)
		{
			field.populateHeader(table);
		}
	}
	
	protected void loadCache()
	{
		loadCache(defaultStore);
	}
	
	protected void loadCache(DataStore store)
	{
		if (!store.connect()) return;
		
		DataTable classTable = getClassTable();
		store.load(classTable);
		
		// Begin deferred referencing, to prevent the problem of DAO's referencing unloaded DAOs.
		// DAOs will be loaded recursively as needed,
		// and then all deferred references will be resolved afterward.
		PersistedObject.beginDefer();
		
		for (DataRow row : classTable.getRows())
		{
			Object newInstance = createInstance(row);
			if (newInstance == null)
			{
				int bar = 0;
				bar++;
			}
			if (newInstance != null)
			{
				if (idField.isAutogenerated())
				{
					int id = (Integer)idField.get(newInstance);
					if (id > maxId) maxId = id;
				}
				
				// Check for objects with objects as ids
				// These will have null ids at the moment, since the
				// id object's class load is deferred.
				
				// So, in this case, we need to get the concrete id 
				// from the data store to cache this instance so we 
				// can look it up later when we bind its instances
				
				Object id = getId(newInstance);
				Object concreteId = null;
				if (id == null)
				{
					DataField idData = row.get(idField.getDataName());
					concreteId = idData.getValue();
				}
				
				addToCache(newInstance, concreteId);
			}
		}
		
		// Bind deferred references, to handle DAOs referencing other DAOs, even of the
		// Same type. 
		// DAOs will be loaded recursively as needed, and then references bound when everything has been
		// resolved.
		PersistedObject.endDefer(this);

		// Defer load lists of entities
		PersistedList.beginDefer();
		
		// Load list data
		if (externalFields.size() > 0)
		{
			List<Object> instances = new ArrayList<Object>();
			for (CachedObject cached : cache)
			{
				instances.add(cached.getObject());
			}
			for (PersistedList list : externalFields)
			{
				DataTable listTable = getListTable(list);
				store.load(listTable);
				list.load(listTable, instances);
			}
		}
		
		// Load any reference lists
		PersistedList.endDefer();
	}
	
	public void load(DataRow row, Object o)
	{
        for (PersistedField field : internalFields)
        {
        	if (field.isReadOnly()) continue;
        	
    		try
    		{
    			field.load(row, o);
    		}
			catch (Exception ex)
			{
				log.warning("Persistence error getting field " + field.getName() + " for " + getTableName());
				ex.printStackTrace();
	        }
        }
	}
	
	protected Object createInstance(DataRow row)
	{
		Object newObject = null;
		
		try
		{
			newObject = persistClass.newInstance();
			load(row, newObject);
		}
		catch (IllegalAccessException ex)
		{
			newObject = null;
			log.warning("Persistence error creating instance of " + getTableName() + ": " + ex.getMessage());		
		}
		catch (InstantiationException ex)
		{
			newObject = null;
			log.warning("Persistence error creating instance of " + getTableName() + ": " + ex.getMessage());		
		}
		
		return newObject;
	}

	/**
	 * Whew! Ok, took me a while to figure out  I needed this...
	 * 
	 * getIdData will recurse down objects-as-id reference chains. This is for persisting
	 * in the data store.
	 * 
	 * getId will return the actual id value, which is how data is cached internally.
	 * 
	 * A VERY important distinction! You look up object-as-id objects using their id instance,
	 * not the id of their id (of that id's id, etc...)
	 */
	public Object getIdData(Object o)
	{
		Object value = null;
		PersistedField field = idField;
		if (field != null)
		{
			if (field instanceof PersistedReference)
			{
				PersistedReference ref = (PersistedReference)field;
				Object refId = idField.get(o);
				if (ref.getReferenceType() != null)
				{
					return ref.getReferenceType().getIdData(refId);
				}
			}
			else
			{
				value = idField.get(o);				
			}
		}
		return value;
	}
	
	public PersistedField getConcreteIdField()
	{
		if (idField == null)
		{
			return null;
		}
		return idField.getConcreteField();
	}
	
	public Object getId(Object o)
	{
		Object value = null;
		if (idField != null)
		{
			value = idField.get(o);
		}
		return value;
	}
	
	protected CachedObject addToCache(Object o)
	{
		return addToCache(o, null);
	}
	
	protected CachedObject addToCache(Object o, Object concreteId)
	{
		// Check to see if this object has already been removed
		// If the ids are autogenerated, just assume it's a new object.
		boolean autogenerate = idField.isAutogenerated();
		Object id = null;
		if (!autogenerate)
		{
			CachedObject removedObject = removedMap.get(id);
			if (removedObject != null)
			{
				removedMap.remove(id);
				removedFromCache.remove(removedObject);
			}
			id = getId(o);
			if (concreteId == null)
			{
				concreteId = getIdData(o);
			}
		}
		else
		{
			id = maxId++;
			concreteId = id;
			idField.set(o, id);
		}

		CachedObject cached = new CachedObject(o);
		if (id != null || concreteId != null)
		{
			cache.add(cached);
		}
		else
		{
			return null;
		}
		if (id != null)
		{
			cacheMap.put(id, cached);
		}
		if (concreteId != null)
		{
			concreteIdMap.put(concreteId, cached);
		}
		
		return cached;
	}
	
	protected void removeFromCache(Object id)
	{
		CachedObject co = cacheMap.get(id);
		if (co == null)
		{
			return;
		}
		
		cache.remove(co);
		if (cacheMap.containsKey(id))
		{
			cacheMap.remove(id);
		}
		if (concreteIdMap.containsKey(id))
		{
			concreteIdMap.remove(id);
		}
		removedFromCache.add(co);
		removedMap.put(id, co);
	}
	
	/*
	 * Private data
	 */
	
	enum LoadState
	{
		UNLOADED,
		LOADING,
		LOADED,
	}
	
	protected boolean						dirty				= false;
	protected LoadState						loadState			= LoadState.UNLOADED;

	protected boolean						cacheObjects		= false;
	protected DataStore						defaultStore		= null;
	protected int							maxId				= 1;

	protected HashMap<Object, CachedObject>	cacheMap			= new HashMap<Object, CachedObject>();
	protected HashMap<Object, CachedObject>	concreteIdMap		= new HashMap<Object, CachedObject>();
	protected List<CachedObject>			cache				= new ArrayList<CachedObject>();
	protected HashMap<Object, CachedObject>	removedMap			= new HashMap<Object, CachedObject>();
	protected List<CachedObject>			removedFromCache	= new ArrayList<CachedObject>();

	protected Class<? extends Object>		persistClass;

	protected List<PersistedField>			fields				= new ArrayList<PersistedField>();
	protected List<PersistedField>			internalFields		= new ArrayList<PersistedField>();
	protected List<PersistedList>			externalFields		= new ArrayList<PersistedList>();
	protected List<PersistedReference>		referenceFields		= new ArrayList<PersistedReference>();

	protected PersistedField 				idField 			= null;
	protected PersistedField				container 			= null;
	protected boolean						contained			= false;

	protected EntityInfo					entityInfo			= null;

	protected String						schema 				= null;
	protected String						name 				= null;

	protected static Logger					log					= Persistence.getLogger();
}
