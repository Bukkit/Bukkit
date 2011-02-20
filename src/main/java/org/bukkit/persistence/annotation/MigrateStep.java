package org.bukkit.persistence.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MigrateStep
{
	String					id();
	String					pluginVersion() default "";
	String					script() default "";
	String					statement() default "";
	boolean					reset() default false;
}
