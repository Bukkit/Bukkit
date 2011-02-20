package org.bukkit.persistence.dao;

import java.util.List;

import org.bukkit.persistence.PersistedClass;
import org.bukkit.persistence.annotation.MigrateStep;
import org.bukkit.persistence.annotation.PersistClass;
import org.bukkit.persistence.annotation.PersistField;

@PersistClass(schema="global", name="migration")
public class MigrationStep
{
	public MigrationStep()
	{
		
	}
	
	public MigrationStep(PersistedClass entityClass, MigrateStep info)
	{
		// Basic info - this should be kept unique, it's used as a secondary id
		// to match back up to the annotations later, since we can't exactly
		// stick an autogen id back into their code.
		//
		// Need to add some "best practice" notes, or maybe just autogen this as well?
		// If there was a way to get the time the annotation was added automatically...
		//
		// Anyway, I would use something like "changeDescriptionMMDDYY", but that's just me.
		//
		// Just keep them unique per entity, really, we'll be ok.
		//
		// At the very least, a dev can only break his/her own data :P
		stepId = info.id();
		
		// This bit is optional- if you want to track the version of your plugin
		// associated with this migration step. Not necessary, though.
		pluginVersion = info.pluginVersion();
		
		// Figure out type from annotation
		if (info.reset())
		{
			type = MigrationType.RESET;
		}
		else if (info.statement().length() > 0)
		{
			sqlData = info.statement();
			type = MigrationType.STATEMENT;
		}
		else if (info.script().length() > 0)
		{
			sqlData = info.statement();
		}
		
		
		entity = entityClass.getName();
		schema = entityClass.getSchema();
	}
	
	/**
	 * Add a dependency for this migration step.
	 * 
	 * Plan to use this make sure multiple migration steps happen in the right order.
	 * Not implemented yet.
	 * 
	 * @param step The step this step depends on.
	 */
	public void dependsOn(MigrationStep step)
	{
		
	}
	
	
	@PersistField(id=true, auto=true)
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	@PersistField
	public String getStepId()
	{
		return stepId;
	}
	
	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	@PersistField
	public MigrationType getType()
	{
		return type;
	}
	
	public void setType(MigrationType type)
	{
		this.type = type;
	}
	
	@PersistField
	public String getSchema()
	{
		return schema;
	}
	
	public void setSchema(String schema)
	{
		this.schema = schema;
	}
	
	@PersistField
	public String getEntity()
	{
		return entity;
	}
	
	public void setEntity(String entity)
	{
		this.entity = entity;
	}
	
	@PersistField
	public String getSqlData()
	{
		return sqlData;
	}
	
	public void setSqlData(String sqlData)
	{
		this.sqlData = sqlData;
	}
	
	@PersistField
	public List<MigrationStep> getDependencies()
	{
		return dependencies;
	}
	
	public void setDependencies(List<MigrationStep> dependencies)
	{
		this.dependencies = dependencies;
	}
	
	@PersistField
	public String getPluginVersion()
	{
		return pluginVersion;
	}

	public void setPluginVersion(String pluginVersion)
	{
		this.pluginVersion = pluginVersion;
	}

	// These are transient getters that mirror sqlData for the 
	// STATEMENT and SCRIPT types

	public String getScriptName()
	{
		return sqlData;
	}

	public String getSQLStatement()
	{
		return sqlData;
	}
			
	int						id;
	MigrationType			type;
	String					stepId;
	String					schema;
	String					entity;
	String					sqlData;
	String					pluginVersion;
	List<MigrationStep>		dependencies;
}
