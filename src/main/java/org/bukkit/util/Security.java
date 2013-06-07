package org.bukkit.util;

import java.util.logging.Level;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.bukkit.Server;

import sun.reflect.Reflection;

/**
 * Handle jvm security concerns
 */
public class Security {

    /**
     * Attempts to make a method invocation possible with the least privilege
     *
     * @param method method to be invoked
     */
    public static void setAccessible(Method method, Server server) {
        // if security manager is not set, no need to check
        // System.getSecurityManager is the fastest way to check and
        // should not have any impact on performance
        SecurityManager sm = System.getSecurityManager();
        if (sm == null) {
            method.setAccessible(true);
        } else {
            final Method fmethod = method;
            // Plugins registered methods should be accessible by World meaning public method declared in public class
            if(!Modifier.isPublic(fmethod.getModifiers()) && !Modifier.isPublic(fmethod.getDeclaringClass().getModifiers()))
            {
                server.getLogger().log(Level.FINE, "Attempt to invoke a non public method \"" + fmethod.toGenericString() + "\" in " + fmethod.getDeclaringClass());
                // methods and declaring classes should be public
                // if not, remove check access in an elevated block code so that
                // "suppressAccessChecks" ReflectPermission is only needed for this class
                AccessController.doPrivileged(new PrivilegedAction() {
                    public Object run() {
                        fmethod.setAccessible(true);
                        return null;
                    }
                });
            }
        }
    }
}
