
package org.bukkit.permission;

/**
 * Thrown when attempting to load an invalid permission profile
 */
public class InvalidPermissionProfileException extends Exception {

    /**
     * Creates a new instance of <code>InvalidPermissionProfileException</code> without detail message.
     */
    public InvalidPermissionProfileException() {
    }

    /**
     * Constructs an instance of <code>InvalidPermissionProfileException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidPermissionProfileException(String msg) {
        super(msg);
    }

    public InvalidPermissionProfileException(Throwable cause) {
        super(cause);
    }

    public InvalidPermissionProfileException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
