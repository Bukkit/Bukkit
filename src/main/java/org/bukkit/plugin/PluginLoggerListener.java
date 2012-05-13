package org.bukkit.plugin;

import java.util.logging.LogRecord;

public abstract class PluginLoggerListener {
    public abstract void onLogged(Plugin plugin, LogRecord record);
}
