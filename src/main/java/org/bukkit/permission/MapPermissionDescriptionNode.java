
package org.bukkit.permission;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a map permission description node, for categorising permissions
 */
public class MapPermissionDescriptionNode extends PermissionDescriptionNode {
    protected final Map<String, PermissionDescriptionNode> nodes = new HashMap<String, PermissionDescriptionNode>();

    public MapPermissionDescriptionNode(final PermissionDescriptionNode parent, Map<String, Object> map)
            throws PermissionDescriptionException, PermissionDescriptionNodeException {
        super(parent, map);

        if (map.containsKey("map")) {
            try {
                loadMap((Map<String, Object>)map.get("map"));
            } catch (ClassCastException ex) {
                throw new PermissionDescriptionNodeException("Map is not actually a map", ex);
            }
        } else {
            throw new PermissionDescriptionNodeException("Map is not defined");
        }
    }

    protected final void loadMap(final Map<String, Object> map) throws PermissionDescriptionException {
        final Set<String> keys = map.keySet();
        
        for (String key : keys) {
            try {
                Map<String, Object> value = (Map<String, Object>) map.get(key);
                PermissionDescriptionNode node = PermissionDescriptionNode.createNode(this, value);
                
                if (node != null) {
                    nodes.put(key, node);
                } else {
                    throw new PermissionDescriptionException(key + " is not a valid map");
                }
            } catch (ClassCastException ex) {
                throw new PermissionDescriptionException(key + " is not a map", ex);
            } catch (PermissionDescriptionNodeException ex) {
                throw new PermissionDescriptionException(key + " contains an invalid node", ex);
            }
        }
    }

    protected MapPermissionDescriptionNode(Map<String, Object> map)
            throws PermissionDescriptionException, PermissionDescriptionNodeException {
        super(null, map);
    }

    @Override
    public Object getDefault() {
        return null;
    }

    @Override
    public void setDefault(final Object value) {
        throw new UnsupportedOperationException("Map cannot have a default value");
    }

    public String[] getNames() {
        return nodes.keySet().toArray(new String[0]);
    }

    public PermissionDescriptionNode getNode(final String name) {
        return nodes.get(name);
    }

    public boolean isValid(final Object value) {
        if ((value != null) && (value instanceof Map)) {
            return true;
        }

        return false;
    }
}