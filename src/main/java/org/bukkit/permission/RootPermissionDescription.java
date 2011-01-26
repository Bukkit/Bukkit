
package org.bukkit.permission;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a definition of permissions for a plugin
 */
public class RootPermissionDescription extends MapPermissionDescriptionNode {
    private final Map<String, PermissionDescriptionNode> permissions = new HashMap<String, PermissionDescriptionNode>();

    @SuppressWarnings("LeakingThisInConstructor")
    public RootPermissionDescription(final Map<String, Object> map)
            throws PermissionDescriptionException, PermissionDescriptionNodeException {
        super(map);

        try {
            loadMap(map);
        } catch (ClassCastException ex) {
            throw new PermissionDescriptionNodeException("Root permissions are not maps", ex);
        }
    }
}