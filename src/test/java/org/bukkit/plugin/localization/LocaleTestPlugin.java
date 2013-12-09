package org.bukkit.plugin.localization;

import org.bukkit.plugin.LocaleManager;
import org.bukkit.plugin.TestPlugin;


public class LocaleTestPlugin extends TestPlugin {
    private LocaleManager localeManager = null;

    public LocaleTestPlugin(String pluginName) {
        super(pluginName);
    }

    public LocaleManager getLocaleManager() {
        if(localeManager == null) {
            localeManager = new LocaleManager(this);
        }

        return localeManager;
    }
}
