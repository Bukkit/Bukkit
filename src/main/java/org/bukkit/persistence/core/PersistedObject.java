package org.bukkit.persistence.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

import org.bukkit.persistence.Persistence;
import org.bukkit.persistence.annotation.FieldInfo;
import org.bukkit.persistence.data.DataField;
import org.bukkit.persistence.data.DataRow;
import org.bukkit.persistence.data.DataTable;
import org.bukkit.persistence.data.DataType;


public class PersistedObject extends PersistedField implements PersistedReference
{
	public PersistedObject(PersistedObject copy)
	{
		super(copy);
		
		referenceType = copy.referenceType;
	}

	public PersistedObject clone()
	{
		PersistedObject field = new PersistedObject(this);
		return field;
	}

	public PersistedObject(FieldInfo fieldInfo, Field field)
	{
		super(fieldInfo, field);
	}
	
	public PersistedObject(FieldInfo fieldInfo, Method getter, Method setter)
	{
		super(fieldInfo, getter, setter);
	}
	
	@Override
	public void bind()
	{
		referenceType = Persistence.getInstance().getPersistedClass(getType());
		
		if (referenceType == null)
		{			
			log.severe("Persistence: Reference field: " + getDataName() + " has no valid reference type");
			return;
		}
		
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
				log.warning("Persistence: Field: " + getDataName() + ", Class " + referenceType.getTableName() + " must be contained");
				referenceType = null;
			}
		}
	}
	
	@Override
	public String getDataName()
	{
		if (referenceType == null) return null;
		
		if (container != null)
		{
			return getContainedName(container.getDataName(), name);
		}

		PersistedField idField = referenceType.getIdField();
		String dataName = name;
		if (idField != null)
		{
			String idName = idField.getDataName();
			String idRemainder = "";
			if (idName.length() > 1)
			{
				idRemainder = idName.substring(1);
			}
			dataName += idName.substring(0, 1).toUpperCase() + idRemainder;
		}
		return dataName;
	}

	@Override
	public DataType getDataType()
	{	
		if (referenceType == null) return null;
		
		return getConcreteField().getDataType();
	}
	
	public void populateHeader(DataTable dataTable)
	{
		if (isContained() && referenceType != null)
		{
			referenceType.populateHeader(dataTable);
			return;
		}
		
		DataRow headerRow = dataTable.getHeader();
		DataField field = new DataField(getDataName(), getDataType());
		field.setIdField(isIdField());
		if (headerRow != null)
		{
			headerRow.add(field);
		}
	}

	public void save(DataRow row, Object o)
	{
		if (referenceType == null) return;	
		
		if (isContained())
		{
			Object containedData = get(o);
			referenceType.populate(row, containedData);
			return;
		}
		
		Object referenceId = null;
		if (o != null)
		{
			Object reference = get(o);
			if (reference != null)
			{
				referenceId = referenceType.getIdData(reference);
			}
		}
		
		DataField field = new DataField(getDataName(), getDataType(), referenceId);
		row.add(field);
	}
	
	public void load(DataRow row, Object o)
	{
		if (referenceType == null) return;	
		
		if (isContained())
		{
			Object newInstance = referenceType.createInstance(row);
			set(o, newInstance);
			return;
		}
		
		DataField dataField = row.get(getDataName());
		Object referenceId = null;
		if (dataField != null)
		{
			referenceId = dataField.getValue();
		}
		
		if (referenceId == null)
		{
			set(o, referenceId);
		}
		else
		{
			deferredReferences.add(new DeferredReference(this, o, referenceId));
		}
	}
		
	public static void beginDefer()
	{
		deferStackDepth++;
	}
	
	public static void endDefer(PersistedClass owningClass)
	{
		deferStackDepth--;
		if (deferStackDepth > 0) return;
		
		List<DeferredReference> undefer = new ArrayList<DeferredReference>();
		undefer.addAll(deferredReferences);
		deferredReferences.clear();
		
		for (DeferredReference ref : undefer)
		{
			Object reference = ref.referenceField.referenceType.get(ref.referenceId);
			ref.referenceField.set(ref.object, reference);
			
			// Re-add to cache so that we can cache by the new id
			owningClass.addToCache(ref.object);
		}
		
	}
	
	class DeferredReference
	{
		public PersistedObject referenceField;
		public Object object;
		public Object referenceId;
		
		public DeferredReference(PersistedObject field, Object o, Object id)
		{
			referenceField = field;
			object = o;
			referenceId = id;
		}
	}
	
	// Persisted Reference interface
	
	public boolean isObject()
	{
		return true;
	}
	
	public PersistedClass getReferenceType()
	{
		return referenceType;
	}
	
	private static int deferStackDepth = 0;
	private final static List<DeferredReference> deferredReferences = new ArrayList<DeferredReference>();

	protected PersistedClass referenceType = null;
}
