package org.bukkit.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.lang.Validate;

import org.bukkit.Locale;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class LocaleManager {
    private HashMap<Locale, YamlConfiguration> loadedLocales = new HashMap<Locale, YamlConfiguration>();
    private Locale defaultLocale;

    /**
     * Validate that the Locale is not null and also not loaded
     *
     * @param locale Locale to validate
     */
    private void validateInput(Locale locale) {
        Validate.notNull(locale, "Locale can not be null");
        Validate.isTrue(!loadedLocales.containsKey(locale), "This locale has already been loaded");
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
        Validate.isTrue(loadedLocales.containsKey(locale), "This locale is not loaded");

        defaultLocale = locale;
    }

    /**
     * Load a locale from a File
     *
     * @param locale Locale to load into
     * @param languageFile File to load from
     * @throws LocaleLoadFailedException Thrown if any error loading the Locale happend
     */
    public void load(Locale locale, File languageFile) throws LocaleLoadFailedException {
        //Check for valid params
        validateInput(locale);
        Validate.notNull(languageFile, "File can not be null");
        Validate.isTrue(languageFile.exists(), "File does not exist");

        //Load the File and store it into the HashMap
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        try {
            yamlConfiguration.load(languageFile);
            loadedLocales.put(locale, yamlConfiguration);
        } catch (IOException e) {
            throw new LocaleLoadFailedException("Could not load Locale: " + locale.getCode(), e);
        } catch (InvalidConfigurationException e) {
            throw new LocaleLoadFailedException("Could not load Locale: " + locale.getCode(), e);
        }
    }

    /**
     * Load a locale from an InputStream
     *
     * @param locale Locale to load into
     * @param inputStream InputStream to load from
     * @throws LocaleLoadFailedException Thrown if any error loading the Locale happend
     */
    public void load(Locale locale, InputStream inputStream) throws LocaleLoadFailedException {
        //Check for valid params
        validateInput(locale);
        Validate.notNull(inputStream, "InputStream can not be null");

        //Load the File and store it into the HashMap
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        try {
            yamlConfiguration.load(inputStream);
            loadedLocales.put(locale, yamlConfiguration);
        } catch (IOException e) {
            throw new LocaleLoadFailedException("Could not load Locale: " + locale.getCode(), e);
        } catch (InvalidConfigurationException e) {
            throw new LocaleLoadFailedException("Could not load Locale: " + locale.getCode(), e);
        }
    }

    /**
     * Checks if a Locale is already loaded
     *
     * @param locale Locale to check
     * @return True if loaded / false if not
     */
    public boolean isLocaleLoaded(Locale locale) {
        //Check if locale is not null
        Validate.notNull(locale, "Locale can not be null");

        return loadedLocales.containsKey(locale);
    }

    /**
     * Removes a loaded Locale. This must be done before you replace a Locale
     *
     * @param locale Locale which should be removed
     */
    public void remove(Locale locale) {
        //Check if locale is valid
        Validate.notNull(locale, "Locale can not be null");
        Validate.isTrue(loadedLocales.containsKey(locale), "Locale is not loaded");

        loadedLocales.remove(locale);
    }

    /**
     * Translate the Text based on the Player locale.
     * If the locale from the player is not loaded the LocaleManager
     * will use the default Locale. If this is also not loaded it
     * will use the translationKey as text and give it back
     *
     * @param player Player from which the Locale should be used
     * @param translationKey The key in the YML which should be translated
     * @param args The Arguments which will be passed into the String when translating
     * @return The translated String
     */
    public String translate(Player player, String translationKey, Object ...args) {
        //Validate the Player
        Validate.notNull(player, "Player can not be null");
        Validate.notNull(player.getLocale(), "The Players locale is null");

        //Validate the Locale => If the Players locale is loaded use it, else use the default one
        YamlConfiguration localeDirectory = loadedLocales.get(defaultLocale);
        if(loadedLocales.containsKey(player.getLocale())) {
            localeDirectory = loadedLocales.get(player.getLocale());
        }

        //Validate that the loadedLocale is not null
        Validate.notNull(localeDirectory, "The locale Directory is empty");

        //Get the text to translate
        String textToTranslate = translationKey;
        if(localeDirectory.contains(translationKey)) {
            textToTranslate = localeDirectory.getString(translationKey);
        }

        return String.format(textToTranslate, args);
    }
}
