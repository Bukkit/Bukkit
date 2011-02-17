package org.bukkit.persistence.core;

public interface PersistedReference
{
	public PersistedClass getReferenceType();
	public boolean isObject();
	public String getName();
	public Class<?> getType();
}
