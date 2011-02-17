
package org.bukkit.persistence.dao;

import org.bukkit.persistence.annotation.PersistClass;
import org.bukkit.persistence.annotation.PersistField;

/**
 * Represents a group, which can be assigned permissions and contain players and other groups.
 * 
 * This isn't used anymore. I'll probably delete it soon.
 * 
 * @author NathanWolf
 *
 */
@PersistClass(name = "group", schema = "global") 
public class Group
{
	@PersistField(id=true)
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	@PersistField
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@PersistField
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	@PersistField
	public Group getParent()
	{
		return parent;
	}
	
	public void setParent(Group parent)
	{
		this.parent = parent;
	}
	
	private String 		id;
	private String 		name;
	private String 		description;
	private Group 		parent;
}
