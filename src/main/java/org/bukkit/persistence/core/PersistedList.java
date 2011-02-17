package org.bukkit.persistence.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.persistence.Persistence;
import org.bukkit.persistence.annotation.FieldInfo;
import org.bukkit.persistence.data.DataField;
import org.bukkit.persistence.data.DataRow;
import org.bukkit.persistence.data.DataTable;
import org.bukkit.persistence.data.DataType;


/**
 * A variant of PersistedField that handles persisting Lists
 * 
 * The class tries to abstract some of the complexity of persisting Lists of data,
 * including creating and using sub-tables.
 * 
 * It also supports Lists of contained objects, storing object data directly
 * in the list sub-table.
 * 
 * @author nathan
 *
 */
public class PersistedList extends PersistedField implements PersistedReference
{
	public PersistedList(PersistedList copy)
	{
		super(copy);
		
		owningType = copy.owningType;
		referenceType = copy.referenceType;
		findListType();
	}
	
	public PersistedList clone()
	{
		PersistedList field = new PersistedList(this);
		return field;
	}
	
	public PersistedList(FieldInfo fieldInfo, Field field, PersistedClass owningClass)
	{
		super(fieldInfo, field);
		owningType = owningClass;
		findListType();
	}
	
	public PersistedList(FieldInfo fieldInfo, Method getter, Method setter, PersistedClass owningClass)
	{
		super(fieldInfo, getter, setter);
		owningType = owningClass;
		findListType();
	}
	
	@Override
	public void bind()
	{
        if (listDataType == DataType.OBJECT)
        {
        	referenceType = Persistence.getInstance().getPersistedClass(listType);
        	if (referenceType == null) return;
        	
    		if (isContained())
    		{
    			// Create a sub-class of the reference class
    			referenceType = new PersistedClass(referenceType, this);
    			referenceType.bindReferences();
    		}
    		else
    		{
    			if (referenceType.isContained())
    			{
    				log.warning("Persistence: " + owningType.getTableName() + "." + getDataName() + ", entity " + referenceType.getTableName() + " must be contained");
    				referenceType = null;
    			}
    		}
        }
	}

	public void load(DataTable subTable, List<Object> instances)
	{
		load(subTable, instances, null);
	}
	
	public void load(DataTable subTable, List<Object> instances, PersistedField container)
	{
		// Load data for all lists in all instances at once, mapping to
		// correct instances based on the id column.
		
		HashMap<Object, Object> objectIdMap = new HashMap<Object, Object>();
		HashMap<Object, List<Object> > objectLists = new HashMap<Object, List<Object> >();
		for (Object instance : instances)
		{
			Object instanceId = owningType.getId(instance);
			objectIdMap.put(instanceId, instance);
			List<Object> listData = new ArrayList<Object>();
			objectLists.put(instanceId, listData);
		}
		
		String idName = owningType.getContainedIdName(this);
		
		for (DataRow row : subTable.getRows())
		{
			Object id = row.get(idName);
			List<Object> list = objectLists.get(id);
			if (list != null)
			{
				if (isContained() && referenceType != null)
				{
					Object newInstance = referenceType.createInstance(row);
					list.add(newInstance);
				}
				else
				{
					Object data = row.get(getReferenceIdName());
					list.add(data);
				}
			}
		}
		
		// Assign lists to instance fields, or defer until later
		for (Object objectId : objectLists.keySet())
		{
			List<Object> listData = objectLists.get(objectId);
			Object instance = objectIdMap.get(objectId);
			
			if (referenceType == null || isContained())
			{
				set(instance, listData);
			}
			else
			{
				DeferredReferenceList list = deferListMap.get(instance);
				if (list == null)
				{
					list = new DeferredReferenceList(this);
					deferListMap.put(instance, list);
				}
				list.idList = listData;
			}
		}
	}
	
	public String getReferenceIdName()
	{
		if (referenceType == null) return null;
		
		// Construct a field name using the name of the reference id
		return referenceType.getContainedIdName(this);
	}
	
	// PersistedReference interface
	public boolean isObject()
	{
		return listDataType == DataType.OBJECT;
	}
	
	public PersistedClass getReferenceType()
	{
		return referenceType;
	}

	protected void populate(DataRow dataRow, Object instance, Object data)
	{
		populate(dataRow, instance, data, null);
	}
	
	protected void populate(DataRow dataRow, Object instance, Object data, PersistedField container)
	{
		PersistedField idField = owningType.getIdField();
		
		// Add id row first, this binds to the owning class
		Object id = null;
		if (instance != null)
		{
			id = owningType.getId(instance);
		}
		String idName = owningType.getContainedIdName();
		DataField idData = new DataField(idName, idField.getDataType(), id);
		idData.setIdField(true);
		dataRow.add(idData);
		
		// Add data rows
		if (referenceType == null)
		{
			DataField valueData = new DataField(getDataName(), listDataType);
			valueData.setIdField(true);
			if (data != null)
			{
				valueData.setValue(data);
			}
			dataRow.add(valueData);
		}
		else if (isContained())
		{
			referenceType.populate(dataRow, data);
		}
		else
		{	
			PersistedField referenceIdField = referenceType.getIdField();
			DataField referenceIdData = new DataField(getReferenceIdName(), referenceIdField.getDataType());
			if (data != null)
			{
				Object referenceId = referenceIdField.get(data);
				referenceIdData.setValue(referenceId);
			}
			referenceIdData.setIdField(true);
			dataRow.add(referenceIdData);
		}	
	}
	
	public void populateHeader(DataTable dataTable, PersistedField container)
	{
		dataTable.createHeader();
		DataRow headerRow = dataTable.getHeader();
		populate(headerRow, null, null, container);
	}
	
	public void save(DataTable table, Object instance)
	{
		if (instance == null) return;
		
		@SuppressWarnings("unchecked")
		List<? extends Object> list = (List<? extends Object>)get(instance);
		if (list == null) return;
		
		for (Object data : list)
		{
			DataRow row = new DataRow(table);
			populate(row, instance, data);
			table.addRow(row);
		}
	}
	
	protected void findListType()
	{
        Type type = getGenericType();  
        
        if (type instanceof ParameterizedType) 
        {  
            ParameterizedType pt = (ParameterizedType)type;
            if (pt.getActualTypeArguments().length > 0)
            {
            	listType = (Class<?>)pt.getActualTypeArguments()[0];
            }
        }
        listDataType = DataType.getTypeFromClass(listType);
        
        // Construct sub-table name
		tableName = name.substring(0, 1).toUpperCase() + name.substring(1);
		tableName = owningType.getTableName() + tableName;
	}
	
	public Class<?> getListType()
	{
		return listType;
	}
	
	public DataType getListDataType()
	{
		return listDataType;
	}
	
	public String getTableName()
	{
		return tableName;
	}
	
	protected Type getGenericType()
	{
		Type genericType = null;
		if (getter != null)
		{
			genericType = getter.getGenericReturnType();
		}
		else
		{
			genericType = field.getGenericType();
		}
		return genericType;
	}
	
	public static void beginDefer()
	{
		deferStackDepth++;
	}
	
	public static void endDefer()
	{
		deferStackDepth--;
		if (deferStackDepth > 0) return;
		
		for (Object instance : deferListMap.keySet())
		{
			List<Object> references = new ArrayList<Object>();
			DeferredReferenceList ref = deferListMap.get(instance);
			for (Object id : ref.idList)
			{
				if (id == null) 
				{
					references.add(null);
				}
				else
				{
					Object reference = ref.referenceList.referenceType.get(id);
					references.add(reference);
				}
			}
			
			ref.referenceList.set(instance, references);
		}
		deferListMap.clear();
	}
	
	class DeferredReferenceList
	{
		public PersistedList referenceList;
		public List<Object> idList;
		
		public DeferredReferenceList(PersistedList listField)
		{
			referenceList = listField;
		}
	}

	private static int deferStackDepth = 0;
	private final static HashMap<Object, DeferredReferenceList> deferListMap = new HashMap<Object, DeferredReferenceList>();

	protected final PersistedClass owningType;
	protected String tableName;
	protected Class<?> listType;
	protected DataType listDataType;

	// Only valid for Lists of Objects
	protected PersistedClass referenceType = null;
}
