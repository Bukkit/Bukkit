
package org.bukkit.permission;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a definition of permissions for a plugin
 */
public class PermissionDescription {
    private final Map<String, PermissionDescriptionNode> permissions = new HashMap<String, PermissionDescriptionNode>();

    @SuppressWarnings("LeakingThisInConstructor")
    public PermissionDescription(final Map<String, Map> map) throws PermissionDescriptionException {
        final Set<String> keys = map.keySet();

        for (String key : keys) {
            try {
                Map<String, Object> value = (Map<String, Object>)map.get(key);
                PermissionDescriptionNode node = PermissionDescriptionNode.createNode(this, null, value);

                if (node != null) {
                    permissions.put(key, node);
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

    public String[] getNames() {
        return permissions.keySet().toArray(new String[0]);
    }

    public PermissionDescriptionNode getNode(final String path) {
        return permissions.get(path); // TODO: Collapse path
    }
}