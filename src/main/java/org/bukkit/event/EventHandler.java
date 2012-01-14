package org.bukkit.event;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zml2008
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
    
    Class<? extends Event> event();
    
    EventPriority priority();
}
