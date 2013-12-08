package org.bukkit.plugin;

import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang.LocaleUtils;
import org.apache.commons.lang.Validate;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.localisation.ResourceBundleControl;
import org.bukkit.plugin.localisation.YamlResourceBundle;

public class LocaleManager {
    //The BundleControl to use for this LocaleManager
    private final ResourceBundleControl resourceBundleControl;

    //The Plugin which has loaded this LocaleManager
    private JavaPlugin plugin;

    //Use the same Resolver Cache for all Plugins
    private final static Map<String, Locale> localeCache = new HashMap<String, Locale>();

    //The fallback Locale to use
    private Locale defaultLocale;

    /**
     * Construct a new LocaleManager for this Plugin
     *
     * @param plugin The plugin for which this LocaleManager should be loaded
     */
    public LocaleManager(JavaPlugin plugin) {
        this.plugin = plugin;

        File languageDirectory = plugin.getLocaleDirectory();
        if(!languageDirectory.exists()) languageDirectory.mkdirs();

        resourceBundleControl = new ResourceBundleControl(languageDirectory);
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
     * Get the correct ResourceBundle based on the Locale
     *
     * @param locale Locale which should be loaded
     * @return ResourceBundle which represents the Locale
     */
    private ResourceBundle getResourceBundle(Locale locale) {
        //Set the default Locale to the java.util.Locale
        Locale oldDefault = Locale.getDefault();
        Locale.setDefault(defaultLocale);

        //Get the ResourceBundle
        ResourceBundle temp = YamlResourceBundle.getBundle("lang", locale, plugin.getClass().getClassLoader(), resourceBundleControl);

        //Change the default Locale back
        Locale.setDefault(oldDefault);

        return temp;
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

        defaultLocale = locale;
    }

    /**
     * Translate the Text based on the Player locale.
     * If the locale from the player is not loaded the LocaleManager will try to load it, if this fails
     * it will use the default Locale. If this is also not loaded you will get a MissingResourceException
     *
     * @param player Player from which the Locale should be used
     * @param translationKey The key in the YML which should be translated
     * @param args The Arguments which will be passed into the String when translating
     * @return The translated String
     * @throws MissingResourceException when the Resource for the locale could not be loaded or the key could not be found in the Resource
     */
    public String translate(Player player, String translationKey, Object ...args) {
        //Validate the Player
        Validate.notNull(player, "Player can not be null");
        Validate.notNull(player.getLocale(), "The Players locale is null");

        //Get the resource and translate
        ResourceBundle resource = getResourceBundle(lookupLocale(player.getLocale()));
        MessageFormat msgFormat = new MessageFormat(resource.getString(translationKey));
        msgFormat.setLocale(resource.getLocale());
        return msgFormat.format(args);
    }

    /**
     * Translate the Text based on the Player locale / default Locale.
     * If the locale from the player is not loaded the LocaleManager
     * will use the default Locale. If this is also not loaded it
     * will use the translationKey as text and give it back
     *
     * @param commandSender CommandSender which can be a Player, if a Player the locale from it will be used otherwise the default one will be taken
     * @param translationKey The key in the YML which should be translated
     * @param args The Arguments which will be passed into the String when translating
     * @return The translated String
     * @throws MissingResourceException when the Resource for the locale could not be loaded or the key could not be found in the Resource
     */
    public String translate(CommandSender commandSender, String translationKey, Object ...args) {
        //Validate the CommandSender
        Validate.notNull(commandSender, "Commandsender can not be null");

        //If the CommandSender is a Player use the correct method
        if(commandSender instanceof Player) return translate((Player) commandSender, translationKey, args);

        //Get the resource and translate
        ResourceBundle resource = getResourceBundle(defaultLocale);
        MessageFormat msgFormat = new MessageFormat(resource.getString(translationKey));
        msgFormat.setLocale(resource.getLocale());
        return msgFormat.format(args);
    }

    /**
     * Be sure to remove resources loaded and to remove refs
     */
    public void cleanup() {
        plugin = null;

        resourceBundleControl.cleanup();
    }
}
