
package org.bukkit.permission;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a node of a permission description block
 */
public abstract class PermissionDescriptionNode {
    protected final PermissionDescription parent;
    protected final Map<String, Object> map;

    public PermissionDescriptionNode(final PermissionDescription parent, Map<String, Object> map) {
        this.parent = parent;
        this.map = map;
    }

    /**
     * Gets the default value defined for this node
     *
     * @return Default value
     */
    public abstract Object getDefault();

    /**
     * Sets the default value for this node
     *
     * @param value New default value
     */
    public void setDefault(final Object value) {
        if (isValid(value)) {
            map.put("default", value);
        } else {
            throw new IllegalArgumentException("Default value is not of correct type");
        }
    }

    /**
     * Checks if the given value is valid for this node type
     *
     * @param value Value to check the validity of
     * @return true if the value is valid, otherwise false
     */
    public abstract boolean isValid(final Object value);
}