package org.bukkit.permission;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents different node types
 */
public enum PermissionNodeType {
    BOOLEAN(BooleanPermissionDescriptionNode.class),
    INTEGER(IntegerPermissionDescriptionNode.class),
    MAP(MapPermissionDescriptionNode.class),
    LIST(ListPermissionDescriptionNode.class);
    
    private final Class<? extends PermissionDescriptionNode> desc;
    private static final Map<String, PermissionNodeType> lookup = new HashMap<String, PermissionNodeType>();

    private PermissionNodeType(final Class<? extends PermissionDescriptionNode> desc) {
        this.desc = desc;
    }

    /**
     * Gets the associated PermissionDescriptionNode class for this PermissionNodeType
     *
     * @return Relevant PermissionDescriptionNode class
     */
    public Class<? extends PermissionDescriptionNode> getDescriptionClass() {
        return desc;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
    
    static {
        for (PermissionNodeType type : values()) {
            lookup.put(type.toString(), type);
        }
    }

    /**
     * Gets the given PermissionNodeType with the given name
     *
     * @param name Name of the node type
     * @return PermissionNodeType corresponding to the given name
     */
    public static PermissionNodeType getType(final String name) {
        return lookup.get(name.toLowerCase());
    }
}
