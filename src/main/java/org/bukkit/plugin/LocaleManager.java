package org.bukkit.plugin;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.LocaleUtils;
import org.apache.commons.lang.Validate;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.localization.ResourceLoadFailedException;
import org.bukkit.plugin.localization.ResourceLoader;
import org.bukkit.plugin.localization.ResourceManager;
import org.bukkit.plugin.localization.ResourceNotLoadedException;
import org.bukkit.plugin.localization.loader.PropertiesResourceLoader;
import org.bukkit.plugin.localization.loader.YamlResourceLoader;

public class LocaleManager {
    //The ResourceManager to use for this LocaleManager
    private ResourceManager resourceManager;

    //Use the same Resolver Cache for all Plugins
    private final static Map<String, Locale> localeCache = new HashMap<String, Locale>();

    //The fallback Locale to use
    private Locale defaultLocale;

    /**
     * Construct a new LocaleManager for this Plugin
     *
     * @param plugin The plugin for which this LocaleManager should be loaded
     */
    public LocaleManager(Plugin plugin) {
        resourceManager = new ResourceManager(plugin);
        resourceManager.registerLoader(new YamlResourceLoader());
        resourceManager.registerLoader(new PropertiesResourceLoader());
    }

    /**
     * Load a new Locale into the ResourceManager for this Plugin
     *
     * @param locale Locale which should be loaded
     * @param param The param which should be given to the ResourceLoader
     * @throws org.bukkit.plugin.localization.ResourceLoadFailedException if the loading has thrown any Error
     */
    public void load(Locale locale, String param) throws ResourceLoadFailedException {
        //Validate the input
        Validate.notNull(locale);
        Validate.notNull(param);

        resourceManager.load(locale, param);
    }

    /**
     * Get the correct java.util.Locale for the ISO locale String
     *
     * @param isoLocale The ISO String which should be looked up
     * @return The correct Locale from Java
     */
    private Locale lookupLocale(String isoLocale) {
        if (!localeCache.containsKey(isoLocale)) {
            localeCache.put(isoLocale, LocaleUtils.toLocale(isoLocale));
        }

        return localeCache.get(isoLocale);
    }

    /**
     * Gets the correct String out of the Locale. If the locale given is not loaded by the underlying ResourceManager
     * it takes the set default Locale to read the String from.
     *
     * @param locale Locale which should be read for
     * @param key The key which should be looked up
     * @return The String stored in the ResourceLoader
     * @throws ResourceNotLoadedException
     */
    private String getTranslationString(Locale locale, String key) throws ResourceNotLoadedException {
        return resourceManager.get(locale, key);
    }

    /**
     * Check if the given Locale has been loaded by the ResourceManager. If not return the default Locale
     *
     * @param locale Locale which should be checked
     * @return The default locale or the param
     */
    private Locale checkForDefault(Locale locale) {
        if(!resourceManager.isLoaded(locale)) {
            return defaultLocale;
        }

        return locale;
    }

    /**
     * Change the default Locale for this plugin.
     * It must be loaded before a Locale can be set as default.
     *
     * @param locale Locale which should be used as default Fallback
     */
    public void setDefaultLocale(Locale locale) {
        //Validate the locale
        Validate.notNull(locale, "Locale can not be null");
        Validate.isTrue(resourceManager.isLoaded(locale), "Locale has not been loaded");

        defaultLocale = locale;
    }

    /**
     * Translate the Text based on the Player locale.
     * If the locale from the player is not loaded the LocaleManager will try to load it, if this fails
     * it will use the default Locale. If this is also not loaded you will get a ResourceNotLoadedException
     *
     * @param player Player from which the Locale should be used
     * @param translationKey The key in the ResourceLoader which should be translated
     * @param args The Arguments which will be passed into the String when translating
     * @return The translated String
     * @throws org.bukkit.plugin.localization.ResourceNotLoadedException when the Resource for the locale could not be loaded or the key is missing
     */
    public String translate(Player player, String translationKey, Object ...args) throws ResourceNotLoadedException {
        //Validate the Player
        Validate.notNull(player, "Player can not be null");
        Validate.notNull(player.getLocale(), "The Players locale is null");
        Validate.notNull(translationKey, "The translationKey can not be null");

        //Get the resource and translate
        Locale playerLocale = checkForDefault(lookupLocale(player.getLocale()));
        String translationString = getTranslationString(playerLocale, translationKey);
        if(translationString == null) throw new ResourceNotLoadedException("The key(" + translationKey + ") is not present in the Locale " + playerLocale.toString());

        MessageFormat msgFormat = new MessageFormat(translationString);
        msgFormat.setLocale(playerLocale);
        return msgFormat.format(args);
    }

    /**
     * Translate the Text based on the Player locale / default Locale.
     * If the locale from the player is not loaded the LocaleManager
     * will use the default Locale. If this is also not loaded it
     * will use the translationKey as text and give it back
     *
     * @param commandSender CommandSender which can be a Player, if a Player the locale from it will be used otherwise the default one will be taken
     * @param translationKey The key in the ResourceLoader which should be translated
     * @param args The Arguments which will be passed into the String when translating
     * @return The translated String
     * @throws org.bukkit.plugin.localization.ResourceNotLoadedException when the Resource for the locale could not be loaded or the key is missing
     */
    public String translate(CommandSender commandSender, String translationKey, Object ...args) throws ResourceNotLoadedException {
        //Validate the CommandSender
        Validate.notNull(commandSender, "Commandsender can not be null");
        Validate.notNull(translationKey, "The translationKey can not be null");

        //If the CommandSender is a Player use the correct method
        if(commandSender instanceof Player) return translate((Player) commandSender, translationKey, args);

        //Get the resource and translate
        String translationString = getTranslationString(defaultLocale, translationKey);
        if(translationString == null) throw new ResourceNotLoadedException("The key(" + translationKey + ") is not present in the Locale " + defaultLocale.toString());

        MessageFormat msgFormat = new MessageFormat(translationString);
        msgFormat.setLocale(defaultLocale);
        return msgFormat.format(args);
    }

    /**
     * Register a new custom ResourceLoader. See {@link org.bukkit.plugin.localization.ResourceManager#registerLoader(org.bukkit.plugin.localization.ResourceLoader)}
     *
     * @param loader
     */
    public void registerLoader(ResourceLoader loader) {
        //Check if loader is good
        Validate.notNull(loader);

        resourceManager.registerLoader(loader);
    }

    /**
     * Tells the ResourceManager to reload all Locale Resources which has been loaded by this Plugin
     */
    public void reload() {
        resourceManager.reload();
    }

    /**
     * Be sure to remove resources loaded and to remove refs
     */
    public void cleanup() {
        resourceManager.cleanup();
        resourceManager = null;
    }
}
