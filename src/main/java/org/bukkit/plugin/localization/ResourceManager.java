package org.bukkit.plugin.localization;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.Plugin;

import java.lang.ref.SoftReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;

public class ResourceManager {
    //Save all loaded Locales with their loadString
    private final HashMap<Locale, String> loadedLocaleLoadStrings = new HashMap<Locale, String>();

    //Save all Loaded Locales
    private final HashMap<Locale, SoftReference<ResourceLoader>> loadedLocales = new HashMap<Locale, SoftReference<ResourceLoader>>();

    //The list of all available ResourceLoaders
    private final ArrayList<ResourceLoader> registerdLoaders = new ArrayList<ResourceLoader>();

    //Construct a object which can be locked on
    private final Object sharedLock = new Object();

    //The Plugin for which this Manager manages Resources
    private Plugin plugin;

    /**
     * Constructs a new ResourceManager which handles the loading and getting, reloading and cleanup for Resources
     *
     * @param plugin The Plugin for which this ResourceManager
     */
    public ResourceManager(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Register a new ResourceLoader. It must implement the {@link ResourceLoadFailedException}
     * and have a Constructor which takes two Arguments, JavaPlugin as first and a String as second Parameter. It also
     * needs to have a empty default Constructor so you can register the ResourceLoader without it having loaded any
     * Resource in it.
     *
     *
     * The JavaPlugin will be the Plugin for which this loader should load.
     * The String is the second parameter from {@link org.bukkit.plugin.LocaleManager#load(Locale, String)}
     *
     * @param loader New loader which can be used to load Resources
     */
    public synchronized void registerLoader(ResourceLoader loader) {
        //Validate the input
        Validate.notNull(loader);

        synchronized (sharedLock) {
            registerdLoaders.add(loader);
        }
    }

    /**
     * Try to load the ResourceLoader for this locale and param
     *
     * @param locale The locale which should be loaded for
     * @param param The param which the ResourceLoader should load
     * @throws ResourceLoadFailedException
     */
    private synchronized void loadLocale(Locale locale, String param) throws ResourceLoadFailedException {
        //Get the correct loader for this param
        for(ResourceLoader loader : registerdLoaders) {
            for(String ending : loader.getFormats()) {
                if(param.endsWith(ending)) {
                    try {
                        synchronized (sharedLock) {
                            loadedLocales.put(locale, new SoftReference<ResourceLoader>(buildNewResourceLoader(loader, param)));
                            loadedLocaleLoadStrings.put(locale, param);
                        }
                    } catch (RuntimeException e) {
                        throw new ResourceLoadFailedException(e);
                    }
                }
            }
        }
    }

    /**
     * Load a new Resource for the locale. The Loaders gets selected based of the ending of the Parameter. If
     * a Locale has been loaded before it gets unloaded and the new one gets loaded instead. So you can overload
     * old Resources with new ones
     *
     * @param locale Locale for which this Resource should be loaded
     * @param param The param from {@link org.bukkit.plugin.LocaleManager#load(Locale, String)}
     * @throws ResourceLoadFailedException
     */
    public synchronized void load(Locale locale, String param) throws ResourceLoadFailedException {
        //Check if parameters are correct
        Validate.notNull(locale);
        Validate.notNull(param);

        //Check if locale has already been loaded
        if(loadedLocales.containsKey(locale)) {
            synchronized (sharedLock) {
                //Unload the locale and get the new one
                ResourceLoader loader = loadedLocales.get(locale).get();
                if(loader != null)
                    loader.cleanup();

                loadedLocales.remove(locale);
                loadedLocaleLoadStrings.remove(locale);
            }
        }

        loadLocale(locale, param);
    }

    /**
     * Checks if the GC has unloaded this Locale ResourceLoader for the need of more RAM, if so try to reload the Locale
     * @param locale The Locale to check for
     * @throws ResourceLoadFailedException
     */
    private synchronized void reloadIfGCCleared(Locale locale) throws ResourceLoadFailedException {
        if(loadedLocales.get(locale).get() == null) {
            synchronized (sharedLock) {
                loadLocale(locale, loadedLocaleLoadStrings.get(locale));
            }
        }
    }

    /**
     * Get the String for key out of the correct Loader. If the Locale has not been loaded you will get an
     * {@link ResourceNotLoadedException}. If the Resource has been loaded but
     * it missed a translation you will get null
     *
     * @param locale Locale to lookup
     * @param key Key to search in the loader
     * @return The String which has been resolved by the Loader
     * @throws ResourceNotLoadedException
     * @throws ResourceLoadFailedException
     */
    public String get(Locale locale, String key) throws ResourceNotLoadedException, ResourceLoadFailedException {
        //Validate the input
        Validate.notNull(locale);
        Validate.notNull(key);

        //If Locale is not loaded throw a ResourceNotLoadedException
        if(loadedLocales.containsKey(locale)) {
            //Check if this Locale contains the key searched for
            reloadIfGCCleared(locale);

            if(loadedLocales.get(locale).get().getKeys().contains(key)) {
                return loadedLocales.get(locale).get().get(key);
            }
        }

        //Check if there is a Resource for the language only (so you can inherit en to en_US for example)
        Locale baseLocale = new Locale(locale.getLanguage());

        //If Locale is not loaded throw a ResourceNotLoadedException
        if(loadedLocales.containsKey(baseLocale)) {
            //Check if this Locale contains the key searched for
            reloadIfGCCleared(baseLocale);

            if(loadedLocales.get(baseLocale).get().getKeys().contains(key)) {
                return loadedLocales.get(baseLocale).get().get(key);
            }
        }

        //If not locale was found which contains the key throw an Exception
        throw new ResourceNotLoadedException("The locale " + locale.toString() + ":" + key +" has not been loaded");
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
     * @throws RuntimeException
     */
    private synchronized ResourceLoader buildNewResourceLoader(ResourceLoader loader, String argument) {
        try {
            Constructor constructor = loader.getClass().getConstructor(Plugin.class, String.class);
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
    public synchronized void reload() {
        //Reload all ResourceLoaders
        for(SoftReference<ResourceLoader> loader : loadedLocales.values()) {
            try {
                if(loader.get() != null)
                    loader.get().reload();
            } catch (ResourceLoadFailedException e) {
                plugin.getLogger().log(Level.SEVERE, "Could not reload all Resources", e);
            }
        }
    }

    /**
     * If the Plugin should be unloaded remove all loaded Things and unref the plugin
     */
    public synchronized void cleanup() {
        //Cleanup all ResourceLoaders
        for(SoftReference<ResourceLoader> loader : loadedLocales.values()) {
            if(loader.get() != null)
                loader.get().cleanup();
        }

        //Remove all refs
        plugin = null;
    }
}
