package org.bukkit.persistence.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to perform a migration step. 
 * 
 * A migration step can currently be one of the following MigrationType actions:
 * 
 * AUTO: This is an automatic migration- a backup, drop, and restore.
 * RESET: This is for auto-generated data, the data is dropped and data is list.
 * CUSTOM: A custom script is used to perform the migration.
 * 
 * @author NathanWolf
 * @see PersistClass
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Migrate
{
	boolean autoReset() default false;
	
	MigrateStep[] steps() default {};
}
