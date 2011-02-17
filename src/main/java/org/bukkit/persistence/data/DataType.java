package org.bukkit.persistence.data;

import java.util.Date;
import java.util.List;

public enum DataType
{
	INTEGER,
	LONG,
	BOOLEAN,
	FLOAT,
	DOUBLE,
	STRING,
	DATE,
	ENUMERATION,
	OBJECT,
	LIST,
	NULL;
	
	public String toString()
	{
		return this.name().toLowerCase();
	}
	
	public static DataType getTypeFromClass(Class<?> fieldType)
	{
		DataType sqlType = NULL;
		
		if (fieldType.isEnum())
		{
			sqlType = DataType.ENUMERATION;
		}
		else if (List.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.LIST;
		}
		else if (Enum.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.ENUMERATION;
		} 
		else if (Date.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.DATE;
		}
		else if (Boolean.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.BOOLEAN;
		}
		else if (Integer.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.INTEGER;
		}
		else if (Double.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.DOUBLE;
		}
		else if (Float.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.FLOAT;
		}
		else if (Long.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.LONG;
		}
		else if (String.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.STRING;
		}
		else if (boolean.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.BOOLEAN;
		}
		else if (int.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.INTEGER;
		}
		else if (long.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.LONG;
		}
		else if (double.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.DOUBLE;
		}
		else if (float.class.isAssignableFrom(fieldType))
		{
			sqlType = DataType.FLOAT;
		}
		else
		{
			// Don't get the PersistedClass here, or you might cause recursion issues with circular dependencies.
			// Also, don't look for an annotation, since it might not be used. 
			// We make sure the object reference is valid in validate().
			sqlType = DataType.OBJECT;
		}
		return sqlType;
	}
	
}
