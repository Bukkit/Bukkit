
package org.bukkit.permission;

import java.util.Map;

/**
 * Represents a map permission description node, for categorising permissions
 */
public class MapPermissionDescriptionNode extends PermissionDescriptionNode {
    public MapPermissionDescriptionNode(final PermissionDescription root,
            final PermissionDescriptionNode parent, Map<String, Object> map) {
        super(root, parent, map);
    }

    @Override
    public Object getDefault() {
        return null;
    }

    @Override
    public void setDefault(final Object value) {
        throw new UnsupportedOperationException("Map cannot have a default value");
    }

    public boolean isValid(final Object value) {
        if ((value != null) && (value instanceof Map)) {
            return true;
        }

        return false;
    }
}