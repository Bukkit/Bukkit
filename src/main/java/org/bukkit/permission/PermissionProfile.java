
package org.bukkit.permission;

import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

/**
 * Represents a profile of permissions
 */
public class PermissionProfile implements Permissions {
    private String name;
    private final Map<String, Object> values = new HashMap<String, Object>();
    private final Server server;
    private final PluginManager manager;
    private static final Yaml yaml = new Yaml(new SafeConstructor());

    public PermissionProfile(final Server server, final String name) {
        this.server = server;
        this.name = name;

        this.manager = server.getPluginManager();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void set(final String key, final Object value) {
        PermissionDescriptionNode desc = manager.getPermissionPath(key);

        if (desc.isValid(value)) {
            values.put(key, value);
        } else {
            throw new IllegalArgumentException("Cannot set " + key + " to " + value);
        }
    }

    public <T> T get(final String key) {
        T result = (T)values.get(key);

        if (result == null) {
            PermissionDescriptionNode desc = manager.getPermissionPath(key);

            result = (T)desc.getDefault();
        }

        return result;
    }

    public boolean isSet(final String key) {
        return values.containsKey(key);
    }

    private void loadNode(final String path, Object node) throws InvalidPermissionProfileException {
        if (node instanceof Map<?, ?>) {
            try {
                Map<String, Object> map = (Map<String, Object>)node;
                Set<String> keys = map.keySet();

                for (String key : keys) {
                    loadNode(path + "." + key, map.get(key));
                }
            } catch (ClassCastException ex) {
                throw new InvalidPermissionProfileException(path + " is not a valid map in " + name, ex);
            } catch (IllegalArgumentException ex) {
                throw new InvalidPermissionProfileException(ex);
            }
        } else {
            set(path, node);
        }
    }

    public static PermissionProfile loadProfile(final Server server,
            final String name, final Map<String, Object> map) throws InvalidPermissionProfileException {
        PermissionProfile result = new PermissionProfile(server, name);
        Set<String> keys = map.keySet();

        for (String key : keys) {
            result.loadNode(key, map.get(key));
        }

        return result;
    }

    public static PermissionProfile[] loadProfiles(final Server server,
            final Map<String, Object> map) throws InvalidPermissionProfileException {
        List<PermissionProfile> result = new ArrayList<PermissionProfile>();
        Set<String> keys = map.keySet();

        for (String key : keys) {
            try {
                result.add(loadProfile(server, key, (Map<String, Object>) map.get(key)));
            } catch (ClassCastException ex) {
                throw new InvalidPermissionProfileException("Attempted to load profile " + key, ex);
            }
        }

        return result.toArray(new PermissionProfile[0]);
    }

    public static PermissionProfile[] loadProfiles(final Server server, final InputStream stream)
            throws InvalidPermissionProfileException {
        return loadProfiles(server, (Map<String, Object>)yaml.load(stream));
    }

    public static PermissionProfile[] loadProfiles(final Server server, final Reader reader)
            throws InvalidPermissionProfileException {
        return loadProfiles(server, (Map<String, Object>)yaml.load(reader));
    }
}
