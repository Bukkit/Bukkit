package org.bukkit.plugin;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * The PluginLogger class is a modified {@link Logger} that prepends all logging calls with the name of the
 * plugin doing the logging. The API for PluginLogger is exactly the same as {@link Logger}.
 *
 * @see Logger
 */
public class PluginLogger extends Logger {
    ArrayList<PluginLoggerListener> localListeners = new ArrayList<PluginLoggerListener>();
    static ArrayList<PluginLoggerListener> globalListeners = new ArrayList<PluginLoggerListener>();
    
    private String pluginName;
    private Plugin plugin;
    /**
     * Creates a new PluginLogger that extracts the name from a plugin.
     * @param context A reference to the plugin
     */
    public PluginLogger(Plugin context) {
        super(context.getClass().getCanonicalName(), null);
        plugin = context;
        String prefix = context.getDescription().getPrefix();
        pluginName = prefix != null ? new StringBuilder().append("[").append(prefix).append("] ").toString() : "[" + context.getDescription().getName() + "] ";
        setParent(context.getServer().getLogger());
        setLevel(Level.ALL);
    }

    @Override
    public void log(LogRecord logRecord) {
        for(PluginLoggerListener cur: localListeners){
            cur.onLogged(plugin, logRecord);
        }
        for(PluginLoggerListener cur: globalListeners){
            cur.onLogged(plugin, logRecord);
        }
        logRecord.setMessage(pluginName + logRecord.getMessage());
        super.log(logRecord);
    }
    
    public void registerLocalListener(PluginLoggerListener listener){
        localListeners.add(listener);
    }
    
    public void unRegisterLocalListener(PluginLoggerListener listener){
        localListeners.remove(listener);
    }
    
    //Global listeners forward output of all plugins
    public static void registerGlobalListener(PluginLoggerListener listener){
        globalListeners.add(listener);
    }
    
    public static void unRegisterGlobalListener(PluginLoggerListener listener){
        globalListeners.remove(listener);
    }
}
