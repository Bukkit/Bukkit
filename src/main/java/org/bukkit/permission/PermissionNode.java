
package org.bukkit.permission;

import java.util.Map;

/**
 * Represents a node of permissions (for example, in profiles)
 */
public class PermissionNode {
    private final PermissionNode parent;
    private final Map<String, Object> map;

    public PermissionNode(final PermissionNode parent, final Map<String, Object> map) {
        this.parent = parent;
        this.map = map;
    }
}
