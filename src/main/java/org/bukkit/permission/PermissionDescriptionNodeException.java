
package org.bukkit.permission;

/**
 * Thrown when attempting to deal with an invalid PermissionDescriptionNode
 */
public class PermissionDescriptionNodeException extends Exception {

    /**
     * Creates a new instance of <code>PermissionDescriptionNodeException</code> without a detailed message.
     */
    public PermissionDescriptionNodeException() {
    }

    /**
     * Constructs an instance of <code>PermissionDescriptionNodeException</code> with the specified detailed message.
     * @param msg the detailed message.
     */
    public PermissionDescriptionNodeException(String msg) {
        super(msg);
    }

    public PermissionDescriptionNodeException(Throwable cause) {
        super(cause);
    }

    public PermissionDescriptionNodeException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
