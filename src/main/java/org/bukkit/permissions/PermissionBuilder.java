package org.bukkit.permissions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Builds a Permission object in a friendly way. Similar to StringBuilder, but
 * for Permissions!
 */
public class PermissionBuilder {
	private String name;
	private String description;
	private PermissionDefault defaultValue;
	private Map<String, Boolean> children;

	public PermissionBuilder() {
	}

	/**
	 * Sets the name of the permission node
	 * 
	 * @param name
	 * @return The PermissionBuilder object
	 */
	public PermissionBuilder setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Sets the description of the permission node
	 * 
	 * @param description
	 * @return The PermissionBuilder object
	 */
	public PermissionBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Matches a string to a PermissionDefault and sets it.
	 * 
	 * @param def
	 * @return The PermissionBuilder object
	 */
	public PermissionBuilder setPermissionDefault(String def) {
		for (PermissionDefault df : PermissionDefault.values())
			if (def.toLowerCase().equals(df.name().toLowerCase()))
				this.defaultValue = df;
		return this;
	}

	/**
	 * Sets the PermissionDefault for people who don't want to use the String
	 * matching.
	 * 
	 * @param def
	 * @return The PermissionBuilder object
	 */
	public PermissionBuilder setPermissionDefault(PermissionDefault def) {
		this.defaultValue = def;
		return this;
	}

	/**
	 * Instantiates the Map if is null; Adds a child node.
	 * 
	 * @param child
	 * @param setting
	 * @return The PermissionBuilder object.
	 */
	public PermissionBuilder addChild(String child, boolean setting) {
		if (this.children == null)
			this.children = new LinkedHashMap<String, Boolean>();
		this.children.put(child, setting);
		return this;
	}

	/**
	 * Converts the set parameters to a new Permission object Returns null if
	 * name has not been defined.
	 * 
	 * @return Permission
	 */
	public Permission toPermission() {
		if (name == null) {
			System.err.println("Name not defined!");
			return null;
		}
		return new Permission(name, description, defaultValue, children);
	}

	/**
	 * Converts the parameters to a String, with null values for those that have
	 * not been defined.
	 * 
	 * @return A nicer toString() method for the PermissionBuilder object
	 */
	public String toString() {
		return new StringBuilder("Permission[")
				.append("name=").append(name)
				.append(", description=").append(description)
				.append(", defaultValue=").append(defaultValue)
				.append(", children=").append(children)
				.append("]").toString();
	}

}

