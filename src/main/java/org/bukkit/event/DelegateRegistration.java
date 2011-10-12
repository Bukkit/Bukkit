package org.bukkit.event;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zml2008
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DelegateRegistration {
    public Class<? extends Event> value();
}
