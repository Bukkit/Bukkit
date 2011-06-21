package org.bukkit.util.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.bukkit.util.cast.BooleanCaster;
import org.bukkit.util.cast.Caster;
import org.bukkit.util.cast.DoubleCaster;
import org.bukkit.util.cast.IntCaster;
import org.bukkit.util.cast.LongCaster;
import org.bukkit.util.cast.MapCaster;
import org.bukkit.util.cast.StringCaster;

/**
 * Represents a configuration node.
 */
public class ConfigurationNode {
    protected Map<String, Object> root;
    
    protected ConfigurationNode(Map<String, Object> root) {
        this.root = root;
    }

    /**
     * Gets all of the cofiguration values within the Node as
     * a key value pair, with the key being the full path and the
     * value being the Object that is at the path.
     *
     * @return A map of key value pairs with the path as the key and the object as the value
     */
    public Map<String, Object> getAll() {
        return recursiveBuilder(root);
    }

    /**
     * A helper method for the getAll method that deals with the recursion
     * involved in traversing the tree
     *
     * @param node The map for that node of the tree
     * @return The fully pathed map for that point in the tree, with the path as the key
     */
    @SuppressWarnings("unchecked")
    protected Map<String, Object> recursiveBuilder(Map<String, Object> node) {
        Map<String, Object> map = new TreeMap<String, Object>();

        Set<String> keys = node.keySet();
        for( String k : keys ) {
            Object tmp = node.get(k);
            if( tmp instanceof Map<?,?> ) {
                Map<String, Object> rec = recursiveBuilder((Map <String,Object>) tmp);

                Set<String> subkeys = rec.keySet();
                for( String sk : subkeys ) {
                    map.put(k + "." + sk, rec.get(sk));
                }
            }
            else {
                map.put(k, tmp);
            }
        }

        return map;
    }

    /**
     * Gets a property at a location. This will either return an Object
     * or null, with null meaning that no configuration value exists at
     * that location. This could potentially return a default value (not yet
     * implemented) as defined by a plugin, if this is a plugin-tied
     * configuration.
     *
     * @param path path to node (dot notation)
     * @return object or null
     */
    @SuppressWarnings("unchecked")
    public Object getProperty(String path) {
        if (!path.contains(".")) {
            Object val = root.get(path);

            if (val == null) {
                return null;
            }
            return val;
        }

        String[] parts = path.split("\\.");
        Map<String, Object> node = root;

        for (int i = 0; i < parts.length; i++) {
            Object o = node.get(parts[i]);

            if (o == null) {
                return null;
            }

            if (i == parts.length - 1) {
                return o;
            }

            try {
                node = (Map<String, Object>) o;
            } catch (ClassCastException e) {
                return null;
            }
        }

        return null;
    }

    /**
     * Set the property at a location. This will override existing
     * configuration data to have it conform to key/value mappings.
     *
     * @param path
     * @param value
     */
    @SuppressWarnings("unchecked")
    public void setProperty(String path, Object value) {
        if (!path.contains(".")) {
            root.put(path, value);
            return;
        }

        String[] parts = path.split("\\.");
        Map<String, Object> node = root;

        for (int i = 0; i < parts.length; i++) {
            Object o = node.get(parts[i]);

            // Found our target!
            if (i == parts.length - 1) {
                node.put(parts[i], value);
                return;
            }

            if (o == null || !(o instanceof Map)) {
                // This will override existing configuration data!
                o = new HashMap<String, Object>();
                node.put(parts[i], o);
            }

            node = (Map<String, Object>) o;
        }
    }

    /**
     * Gets a string at a location. This will either return an String
     * or null, with null meaning that no configuration value exists at
     * that location. If the object at the particular location is not actually
     * a string, it will be converted to its string representation.
     *
     * @param path path to node (dot notation)
     * @return string or null
     */
    public String getString(String path) {
        Object o = getProperty(path);

        if (o == null) {
            return null;
        }
        return o.toString();
    }

    /**
     * Gets a string at a location. This will either return an String
     * or the default value. If the object at the particular location is not
     * actually a string, it will be converted to its string representation.
     *
     * @param path path to node (dot notation)
     * @param def default value
     * @return string or default
     */
    public String getString(String path, String def) {
        String o = getString(path);

        if (o == null) {
            setProperty(path, def);
            return def;
        }
        return o;
    }
    
    /**
     * Casts a given value, if this fails it writes the default value and return this one.
     *
     * @param path path to node (dot notation)
     * @param def default value which has to be <b><code>not null</code></b>.
     * @param caster the caster, which casts the read property.
     * @return the value (if it could be casted) or the default value otherwise.
     */
    private <T> T getProperty(String path, T def, Caster<T> caster) {
        if (def == null) {
            throw new IllegalArgumentException("Default value has to be not null.");
        }
        T t = caster.cast(this.getProperty(path));
        
        if (t == null) {
            this.setProperty(path, def);
            return def;
        } else {
            return t;
        }
    }

    /**
     * Gets an integer at a location. This will either return an integer
     * or <code>null</code>. If the object at the particular location is not
     * actually a integer, <code>null</code> will be returned. However, other
     * number types will be casted to an integer.
     *
     * @param path path to node (dot notation)
     * @return int or <code>null</code>
     */
    public Integer getInt(String path) {
        return IntCaster.INSTANCE.cast(getProperty(path));
    }
    
    /**
     * Gets an integer at a location. This will either return an integer
     * or the default value. If the object at the particular location is not
     * actually a integer, the default value will be returned. However, other
     * number types will be casted to an integer.
     *
     * @param path path to node (dot notation)
     * @param def default value
     * @return int or default
     */
    public int getInt(String path, int def) {
        return this.getProperty(path, def, IntCaster.INSTANCE);
    }
    
    /**
     * Gets an long at a location. This will either return an long
     * or <code>null</code>. If the object at the particular location is not
     * actually a long, <code>null</code> will be returned. However, other
     * number types will be casted to an long.
     *
     * @param path path to node (dot notation)
     * @return long or <code>null</code>
     */
    public Long getLong(String path) {
        return LongCaster.INSTANCE.cast(getProperty(path));
    }
    
    /**
     * Gets an long at a location. This will either return an long
     * or the default value. If the object at the particular location is not
     * actually a long, the default value will be returned. However, other
     * number types will be casted to an long.
     *
     * @param path path to node (dot notation)
     * @param def default value
     * @return long or default
     */
    public long getLong(String path, long def) {
        return this.getProperty(path, def, LongCaster.INSTANCE);
    }

    /**
     * Gets a double at a location. This will either return an double
     * or <code>null</code>. If the object at the particular location is not
     * actually a double, <code>null</code> will be returned. However, other
     * number types will be casted to an double.
     *
     * @param path path to node (dot notation)
     * @return double or <code>null</code>
     */
    public Double getDouble(String path) {
        return DoubleCaster.INSTANCE.cast(getProperty(path));
    }
    
    /**
     * Gets a double at a location. This will either return an double
     * or the default value. If the object at the particular location is not
     * actually a double, the default value will be returned. However, other
     * number types will be casted to an double.
     *
     * @param path path to node (dot notation)
     * @param def default value
     * @return double or default
     */
    public double getDouble(String path, double def) {
        return this.getProperty(path, def, DoubleCaster.INSTANCE);
    }

    /**
     * Gets a boolean at a location. This will either return an boolean
     * or <code>null</code>. If the object at the particular location is not
     * actually a boolean, <code>null</code> will be returned.
     *
     * @param path path to node (dot notation)
     * @param def default value
     * @return boolean or <code>null</code>
     */
    public Boolean getBoolean(String path) {
        return BooleanCaster.INSTANCE.cast(getProperty(path));
    }
    
    /**
     * Gets a boolean at a location. This will either return an boolean
     * or the default value. If the object at the particular location is not
     * actually a boolean, the default value will be returned.
     *
     * @param path path to node (dot notation)
     * @param def default value
     * @return boolean or default
     */
    public boolean getBoolean(String path, boolean def) {
        return this.getProperty(path, def, BooleanCaster.INSTANCE);
    }
    
    /**
     * Gets a map at a location. This will either return an map
     * or <code>null</code>. If the object at the particular location is not
     * actually a map, <code>null</code> will be returned.
     *
     * @param path path to node (dot notation)
     * @param def default value
     * @return map or <code>null</code>
     */
    private Map<String, Object> getMap(String path) {
        return MapCaster.STRING_OBJECT_INSTANCE.cast(this.getProperty(path));
    }

    /**
     * Get a list of keys at a location. If the map at the particular location
     * does not exist or it is not a map, null will be returned.
     *
     * @param path path to node (dot notation)
     * @return list of keys
     */
    public List<String> getKeys(String path) {
        //FIXME: Only here has "null" the special meaning of "this node", either remove or change that getProperty(null) returns this node.
        if (path == null) {
            return this.getKeys();
        }
        Map<String, Object> map = this.getMap(path);
        if (map != null) {
            return new ArrayList<String>(map.keySet());
        } else {
            return null;
        }
    }

    /**
     * Returns a list of all keys at the root path
     *
     * @return List of keys
     */
    public List<String> getKeys() {
        return new ArrayList<String>(root.keySet());
    }

    /**
     * Gets a list of objects at a location. If the list is not defined,
     * null will be returned. The node must be an actual list.
     *
     * @param path path to node (dot notation)
     * @return boolean or default
     */
    @SuppressWarnings("unchecked")
    public List<Object> getList(String path) {
        Object o = getProperty(path);

        if (o == null) {
            return null;
        } else if (o instanceof List) {
            return (List<Object>) o;
        } else {
            return null;
        }
    }
    
    /**
     * Gets a list of <code>t</code>. Non-valid entries will not be in the list.
     * There will be no null slots. If the list is not defined, the
     * default will be returned. <code>null</code> can be passed for the default
     * and an empty list will be returned instead. The node must be
     * an actual list and not just a single value.
     *
     * @param path path to node (dot notation)
     * @param def default value or <code>null</code> for an empty list as default
     * @param caster defines if the entry is valid (= <code>not null</code>)
     * @return list of <code>t</code>. 
     */
    private <T> List<T> getList(final String path, final List<T> def, final Caster<T> caster) {
        List<Object> raw = getList(path);

        if (raw == null) {
            return def != null ? def : new ArrayList<T>();
        }

        List<T> list = new ArrayList<T>();

        for (Object o : raw) {
            T casted = caster.cast(o);

            if (casted != null) {
                list.add(casted);
            }
        }

        return list;
    }

    /**
     * Gets a list of strings. Non-valid entries will not be in the list.
     * There will be no null slots. If the list is not defined, the
     * default will be returned. <code>null</code> can be passed for the default
     * and an empty list will be returned instead. If an item in the list
     * is not a string, it will be converted to a string. The node must be
     * an actual list and not just a string.
     *
     * @param path path to node (dot notation)
     * @param def default value or <code>null</code> for an empty list as default
     * @return list of strings
     * @see The {@link StringCaster} defines, if a value is valid (= <code>not null</code>).
     */
    public List<String> getStringList(String path, List<String> def) {
        return this.getList(path, def, StringCaster.INSTANCE);
    }

    /**
     * Gets a list of integers. Non-valid entries will not be in the list.
     * There will be no null slots. If the list is not defined, the
     * default will be returned. <code>null</code> can be passed for the default
     * and an empty list will be returned instead. The node must be
     * an actual list and not just an integer.
     *
     * @param path path to node (dot notation)
     * @param def default value or <code>null</code> for an empty list as default
     * @return list of integers
     * @see The {@link IntCaster} defines, if a value is valid (= <code>not null</code>).
     */
    public List<Integer> getIntList(String path, List<Integer> def) {
        return this.getList(path, def, IntCaster.INSTANCE);
    }
    
    /**
     * Gets a list of longs. Non-valid entries will not be in the list.
     * There will be no null slots. If the list is not defined, the
     * default will be returned. <code>null</code> can be passed for the default
     * and an empty list will be returned instead. The node must be
     * an actual list and not just an long.
     *
     * @param path path to node (dot notation)
     * @param def default value or <code>null</code> for an empty list as default
     * @return list of longs
     * @see The {@link LongCaster} defines, if a value is valid (= <code>not null</code>).
     */
    public List<Long> getLongList(String path, List<Long> def) {
        return this.getList(path, def, LongCaster.INSTANCE);
    }

    /**
     * Gets a list of doubles. Non-valid entries will not be in the list.
     * There will be no null slots. If the list is not defined, the
     * default will be returned. <code>null</code> can be passed for the default
     * and an empty list will be returned instead. The node must be
     * an actual list and cannot be just a double.
     *
     * @param path path to node (dot notation)
     * @param def default value or <code>null</code> for an empty list as default
     * @return list of doubles
     * @see The {@link DoubleCaster} defines, if a value is valid (= <code>not null</code>).
     */
    public List<Double> getDoubleList(String path, List<Double> def) {
        return this.getList(path, def, DoubleCaster.INSTANCE);
    }

    /**
     * Gets a list of booleans. Non-valid entries will not be in the list.
     * There will be no null slots. If the list is not defined, the
     * default will be returned. 'null' can be passed for the default
     * and an empty list will be returned instead. The node must be
     * an actual list and cannot be just a boolean,
     *
     * @param path path to node (dot notation)
     * @param def default value or null for an empty list as default
     * @return list of booleans
     * @see The {@link BooleanCaster} defines, if a value is valid (= <code>not null</code>).
     */
    public List<Boolean> getBooleanList(String path, List<Boolean> def) {
        return this.getList(path, def, BooleanCaster.INSTANCE);
    }

    /**
     * Gets a list of nodes. Non-valid entries will not be in the list.
     * There will be no null slots. If the list is not defined, the
     * default will be returned. <code>null</code> can be passed for the default
     * and an empty list will be returned instead. The node must be
     * an actual list and cannot be just a node,
     *
     * @param path path to node (dot notation)
     * @param def default value or null for an empty list as default
     * @return list of nodes
     */
    public List<ConfigurationNode> getNodeList(String path, List<ConfigurationNode> def) {
        return this.getList(path, def, new Caster<ConfigurationNode>() {

            @SuppressWarnings("unchecked")
            public ConfigurationNode cast(Object o) {
                if (o == null) {
                    return null;
                } else if (o instanceof Map) {
                    return new ConfigurationNode((Map<String, Object>) o);
                } else {
                    return null;
                }
            }
            
        });
    }

    /**
     * Get a configuration node at a path. If the node doesn't exist or the
     * path does not lead to a node, null will be returned. A node has
     * key/value mappings.
     *
     * @param path
     * @return node or null
     */
    public ConfigurationNode getNode(String path) {
        Map<String, Object> map = this.getMap(path);
        if (map != null) {
            return new ConfigurationNode(map);
        } else {
            return null;
        }
    }

    /**
     * Get a list of nodes at a location. If the map at the particular location
     * does not exist or it is not a map, null will be returned.
     *
     * @param path path to node (dot notation)
     * @return map of nodes
     */
    @SuppressWarnings("unchecked")
    public Map<String, ConfigurationNode> getNodes(String path) {
        Map<String, Object> map = this.getMap(path);
        if (map != null) {
            Map<String, ConfigurationNode> nodes = new HashMap<String, ConfigurationNode>();
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) map).entrySet()) {
                if (entry.getValue() instanceof Map) {
                    nodes.put(entry.getKey(), new ConfigurationNode((Map<String, Object>) entry.getValue()));
                }
            }
            return nodes;
        } else {
            return null;
        }
    }

    /**
     * Remove the property at a location. This will override existing
     * configuration data to have it conform to key/value mappings.
     *
     * @param path path to node (dot notation)
     */
    @SuppressWarnings("unchecked")
    public void removeProperty(String path) {
        if (!path.contains(".")) {
            root.remove(path);
            return;
        }

        String[] parts = path.split("\\.");
        Map<String, Object> node = root;

        for (int i = 0; i < parts.length; i++) {
            Object o = node.get(parts[i]);

            // Found our target!
            if (i == parts.length - 1) {
                node.remove(parts[i]);
                return;
            }

            node = (Map<String, Object>) o;
        }
    }
}
