package org.bukkit.persistence.annotation;


public class EntityInfo
{
	public EntityInfo(String schema, String name)
	{
		this.schema = schema;
		this.name = name;
	}
	
	public EntityInfo(PersistClass defaults)
	{
		schema = defaults.schema();
		name = defaults.name();
		contained = defaults.contained();
		cached = defaults.cached();
	}
	
	public String getSchema()
	{
		return schema;
	}

	public void setSchema(String schema)
	{
		this.schema = schema;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isContained()
	{
		return contained;
	}

	public void setContained(boolean contained)
	{
		this.contained = contained;
	}

	public boolean isCached()
	{
		return cached;
	}

	public void setCached(boolean cached)
	{
		this.cached = cached;
	}

	private String schema;
	private String name;
	private boolean contained = false;
	private boolean cached = true;
}
