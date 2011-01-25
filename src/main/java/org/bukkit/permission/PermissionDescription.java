
package org.bukkit.permission;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a definition of permissions for a plugin
 */
public class PermissionDescription {
    private final Map<String, Object> permissions;

    public PermissionDescription(final Map<String, Object> perms) {
        this.permissions = perms;
    }

    public PermissionDescription() {
        this(new HashMap<String, Object>());
    }
}