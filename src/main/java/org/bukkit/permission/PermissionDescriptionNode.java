
package org.bukkit.permission;

import java.lang.reflect.Constructor;
import java.util.Map;
import org.bukkit.permission.PermissionNodeType;

/**
 * Represents a node of a permission description block
 */
public abstract class PermissionDescriptionNode {
    protected final PermissionDescriptionNode parent;
    protected final Map<String, Object> map;

    public PermissionDescriptionNode(final PermissionDescriptionNode parent, Map<String, Object> map)
            throws PermissionDescriptionException, PermissionDescriptionNodeException {
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

    public static PermissionDescriptionNode createNode(final PermissionDescriptionNode parent,
            final Map<String, Object> map) throws PermissionDescriptionNodeException {
        Class<? extends PermissionDescriptionNode> clazz = null;
        
        if (map.containsKey("type")) {
            String name = (String) map.get("type");
            PermissionNodeType type = PermissionNodeType.getType(name);

            if (type != null) {
                clazz = type.getDescriptionClass();
            } else {
                throw new PermissionDescriptionNodeException("No such type " + name);
            }
        } else if (map.containsKey("map")) {
            clazz = PermissionNodeType.MAP.getDescriptionClass();
        } else {
            throw new PermissionDescriptionNodeException("No type defined");
        }

        try {
            Constructor<? extends PermissionDescriptionNode> ctor = clazz.getConstructor(PermissionDescriptionNode.class, Map.class);
            return ctor.newInstance(parent, map);
        } catch (Exception ex) {
            throw new PermissionDescriptionNodeException(ex);
        }
    }
}