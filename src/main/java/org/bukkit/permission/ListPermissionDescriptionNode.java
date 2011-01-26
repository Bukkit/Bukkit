
package org.bukkit.permission;

import java.util.ArrayList;
import java.util.Map;

/**
 * Represents an array permission description node, for simple true/false access
 */
public class ListPermissionDescriptionNode extends PermissionDescriptionNode {
    public ListPermissionDescriptionNode(final PermissionDescriptionNode parent, Map<String, Object> map)
            throws PermissionDescriptionException, PermissionDescriptionNodeException {
        super(parent, map);
    }
    
    @Override
    public Object getDefault() {
        if (map.containsKey("default")) {
            try {
                return (ArrayList<Object>)map.get("default");
            } catch (ClassCastException ex) {
                return new ArrayList<Object>();
            }
        } else {
            return new ArrayList<Object>();
        }
    }

    @Override
    public void setDefault(final Object value) {
        if (isValid(value)) {
            this.map.put("default", value);
        } else {
            throw new IllegalArgumentException("Default value must be an ArrayList");
        }
    }

    public boolean isValid(final Object value) {
        if ((value != null) && (value instanceof ArrayList)) {
            return true;
        }

        return false;
    }
}
