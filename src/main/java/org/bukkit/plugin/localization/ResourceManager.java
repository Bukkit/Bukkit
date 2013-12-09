package org.bukkit.plugin.localization;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;

public class ResourceManager {
    //Save all Loaded Locales
    private HashMap<Locale, ResourceLoader> loadedLocales = new HashMap<Locale, ResourceLoader>();

    //The list of all available ResourceLoaders
    private ArrayList<ResourceLoader> registerdLoaders = new ArrayList<ResourceLoader>();

    //The Plugin for which this Manager manages Resources
    private JavaPlugin plugin;

    /**
     * Constructs a new ResourceManager which handles the loading and getting, reloading and cleanup for Resources
     *
     * @param plugin The Plugin for which this ResourceManager
     */
    public ResourceManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Register a new ResourceLoader. It must implement the {@link org.bukkit.plugin.localization.ResourceLoadFailedException}
     * and have a Constructor which takes two Arguments, JavaPlugin as first and a String as second Parameter. It also
     * needs to have a empty default Constructor so you can register the ResourceLoader without it having loaded any
     * Resource in it.
     *
     *
     * The JavaPlugin will be the Plugin for which this loader should load.
     * The String is the second parameter from {@link org.bukkit.plugin.LocaleManager#load(java.util.Locale, String)}
     *
     * @param loader New loader which can be used to load Resources
     */
    public void registerLoader(ResourceLoader loader) {
        //Validate the input
        Validate.notNull(loader);

        registerdLoaders.add(loader);
    }

    /**
     * Load a new Resource for the locale. The Loaders gets selected based of the ending of the Parameter. If
     * a Locale has been loaded before it gets unloaded and the new one gets loaded instead. So you can overload
     * old Resources with new ones
     *
     * @param locale Locale for which this Resource should be loaded
     * @param param The param from {@link org.bukkit.plugin.LocaleManager#load(java.util.Locale, String)}
     * @throws ResourceLoadFailedException
     */
    public void load(Locale locale, String param) throws ResourceLoadFailedException {
        //Check if parameters are correct
        Validate.notNull(locale);
        Validate.notNull(param);

        //Check if locale has already been loaded
        if(loadedLocales.containsKey(locale)) {
            //Unload the locale and get the new one
            ResourceLoader loader = loadedLocales.get(locale);
            loader.cleanup();
            loadedLocales.remove(locale);
        }

        //Get the correct loader for this param
        for(ResourceLoader loader : registerdLoaders) {
            for(String ending : loader.getFormats()) {
                if(param.endsWith(ending)) {
                    try {
                        loadedLocales.put(locale, buildNewResourceLoader(loader, param));
                    } catch (RuntimeException e) {
                        throw new ResourceLoadFailedException(e);
                    }
                }
            }
        }
    }

    /**
     * Get the String for key out of the correct Loader. If the Locale has not been loaded you will get an
     * {@link org.bukkit.plugin.localization.ResourceNotLoadedException}. If the Resource has been loaded but
     * it missed a translation you will get null
     *
     * @param locale Locale to lookup
     * @param key Key to search in the loader
     * @return The String which has been resolved by the Loader
     * @throws ResourceNotLoadedException
     */
    public String get(Locale locale, String key) throws ResourceNotLoadedException {
        //Validate the input
        Validate.notNull(locale);
        Validate.notNull(key);

        //If Locale is not loaded throw a ResourceNotLoadedException
        if(!loadedLocales.containsKey(locale)) {
            throw new ResourceNotLoadedException("The locale " + locale.toString() + " has not been loaded");
        }

        //Get the Loader and read the key out of it
        return loadedLocales.get(locale).get(key);
    }

    /**
     * Checks if a Locale has been loaded into this ResourceManager
     *
     * @param locale Locale which should be checked for
     * @return true if loaded / false if not
     */
    public boolean isLoaded(Locale locale) {
        return loadedLocales.containsKey(locale);
    }

    /**
     * Tries to construct a new ResourceLoader from the given Template
     *
     * @param loader The ResourceLoader which should be duplicated
     * @param argument The argument which should be given as Loadstring
     * @return A hopefully new ResourceLoader
     * @throws java.lang.RuntimeException
     */
    private ResourceLoader buildNewResourceLoader(ResourceLoader loader, String argument) {
        try {
            Constructor constructor = loader.getClass().getConstructor(JavaPlugin.class, String.class);
            return (ResourceLoader) constructor.newInstance(this.plugin, argument);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Could not construct new ResourceLoader", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Could not construct new ResourceLoader", e);
        } catch (InstantiationException e) {
            throw new RuntimeException("Could not construct new ResourceLoader", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Could not construct new ResourceLoader", e);
        }
    }

    /**
     * Reload all ResourceLoaders
     *
     * If one of the ResourceLoaders reports an error upon reloading it will get printed to the Plugins Logger
     */
    public void reload() {
        //Reload all ResourceLoaders
        for(ResourceLoader loader : registerdLoaders) {
            try {
                loader.reload();
            } catch (ResourceLoadFailedException e) {
                plugin.getLogger().log(Level.SEVERE, "Could not reload all Resources", e);
            }
        }
    }

    /**
     * If the Plugin should be unloaded remove all loaded Things and unref the plugin
     */
    public void cleanup() {
        //Cleanup all ResourceLoaders
        for(ResourceLoader loader : registerdLoaders) {
            loader.cleanup();
        }

        //Remove all refs
        registerdLoaders = null;
        loadedLocales = null;
        plugin = null;
    }
}