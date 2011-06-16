package org.bukkit.bukkitinterface;

import org.bukkit.bukkitinterface.CustomInterface;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles all plugin management from the Server
 */
public class SimpleInterfaceManager implements InterfaceManager {

    Map<String, List<CustomInterface>> map = new HashMap<String, List<CustomInterface>>();

    /**
     * Registers the specified handler to respond to calls to the CustomInterface
     *
     * @param customInterface CustomInterface to register
     * @throws IllegalArgumentException Thrown when the given Class is not a valid CustomInterface
     */
    public void registerInterface(CustomInterface customInterface) throws IllegalArgumentException {
        String name = customInterface.getName();
        if(name == null) {
            throw new IllegalArgumentException("Null name passed to registerInterface");
        }
        if(map.get(name) == null) {
            List<CustomInterface> list = new ArrayList<CustomInterface>();
            map.put(name, list);
        }
        List<CustomInterface> list = map.get(name);
        if(list.size() != 0 && customInterface.isExclusive()) {
            throw new IllegalArgumentException("Interface " + name + " is an exclusive interface.  Only one handler can be registered");
        }
        if(!list.contains(customInterface)) {
            list.add(customInterface);
        }
    }

    /**
     * Unregisters the specified handler from responding to calls to the CustomInterface
     *
     * @param customInterface CustomInterface to register
     */
    public void unRegisterInterface(CustomInterface customInterface) throws IllegalArgumentException {
        String  name = customInterface.getName();
        if(name == null) {
            throw new IllegalArgumentException("Null name passed to unRegisterInterface");
        }
        List<CustomInterface> list = map.get(name);
        if(list == null) {
            return;
        }
        int index = list.indexOf(customInterface);
        if(index >= 0) {
            list.remove(index);
        } else {
            return;
        }
        if(list.size() == 0) {
            map.remove(name);
        }
    }

    /**
     * Returns a list of handlers registered to use the CustomInterface
     *
     * Please note that the name of the interface is case-sensitive
     *
     * @param name Name of the custom interface to check
     * @return List of handlers if any have been registered, otherwise null
     */
    public List<CustomInterface> getInterfaces(String name) {
        return map.get(name);
    }

    /**
     * Returns the handler registered to use the CustomInterface
     *
     * Please note that the name of the interface is case-sensitive
     *
     * @param name Name of the custom interface to check
     * @return if exactly one handler has been registered for this interface, return the handler, otherwise null
     */
    public CustomInterface getInterface(String name) {
        List<CustomInterface> list = map.get(name);
        if(list != null && list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * Clears all stored interfaces
     */
    public void clearInterfaces() {
        map.clear();
    }

}
