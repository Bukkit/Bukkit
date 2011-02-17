package org.bukkit.persistence.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify that a class is persistable.
 * 
 * Every persistable class must have at least one field, getter or setter
 * marked with Persist(id=true).
 * 
 * Other fields can be marked up with Persist to store data for this class.
 * 
 * @author NathanWolf
 * @see PersistField
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PersistClass
{
	/**
	 * The schema to use when persisting this class.
	 * 
	 * This is a required attribute, each persisted class much specify a schema.
	 * Each schema gets its own connection and data store (database, file in SqlLite).
	 * 
	 * Plugins are welcome to simply use their plugin name as the schema, though that is not enforced.
	 * 
	 * Persistence puts some common objects in the "global" schema, it is not necessarily recommended to use
	 * that in external plugins.
	 * 
	 * @return The name of the schema to use when storing this class
	 */
	String schema();
	
	/**
	 * The name to use when storing this entity.
	 * 
	 * No rules are enforced, but generally -lower-case (or camelCase) names are preferred. Also, singular names are
	 * preferred- so "player" instead of "players".
	 * 
	 * Following these rules will ensure that auto-generated join table names and ids won't look "strange".
	 * 
	 * @return The name of this entity
	 */
	String name();
	
	/**
	 * Specify that a class may only be used when contained in another class.
	 * 
	 * This allows a PersistClass to be used without an id.
	 * 
	 * @return true if this is a contained object
	 */
	boolean contained() default false;
	
	/**
	 * Whether or not to keep this entity in the cache.
	 * 
	 * NOT YET SUPPORTED
	 * 
	 * @return true if this is a cached object
	 */
	boolean cached() default true;
	
}
