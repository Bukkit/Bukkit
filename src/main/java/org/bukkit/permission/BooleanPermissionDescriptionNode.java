
package org.bukkit.permission;

import java.util.Map;

/**
 * Represents a boolean permission description node, for simple true/false access
 */
public class BooleanPermissionDescriptionNode extends PermissionDescriptionNode {
    public BooleanPermissionDescriptionNode(final PermissionDescriptionNode parent, Map<String, Object> map)
            throws PermissionDescriptionException, PermissionDescriptionNodeException {
        super(parent, map);
    }
    
    @Override
    public Object getDefault() {
        if (map.containsKey("default")) {
            try {
                return Boolean.parseBoolean((String)map.get("default"));
            } catch (ClassCastException ex) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void setDefault(final Object value) {
        if (isValid(value)) {
            this.map.put("default", value);
        } else {
            throw new IllegalArgumentException("Default value must be a boolean");
        }
    }

    public boolean isValid(final Object value) {
        if ((value != null) && (value instanceof Boolean)) {
            return true;
        }

        return false;
    }
}
