package org.bukkit.command.defaults;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.ListenerLog;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDebugger;

public class DebugCommand extends Command {
	private long startTime;
	
    public DebugCommand(String name) {
        super(name);
        this.description = "For Managing the Bukkit Plugin Debug System";
        this.usageMessage = "/debug on, /debug off, debug out [PluginName]";
        this.setPermission("bukkit.command.debug");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;
        PluginDebugger pluginDebugger = Bukkit.getPluginManager().getDebugger();
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("on")) {
                if (!pluginDebugger.getDebugging()) {
                    startTime = System.nanoTime();
                    pluginDebugger.clearAll();
                    pluginDebugger.setDebugging(true);
                    sender.sendMessage("Debug Mode activated. Use /debug out [PluginName] for the results");
                } else {
                    sender.sendMessage("Debug Mode already activated!");
                }
            }
        	if (args[0].equalsIgnoreCase("off")) {
                if (pluginDebugger.getDebugging()) {
                    pluginDebugger.setDebugging(false);
            	    pluginDebugger.clearAll();
                    sender.sendMessage("Debugging deactivated");
                } else {
                    sender.sendMessage("Debug Mode not activ!");
                }
            }
            if (args[0].equalsIgnoreCase("out")) {
                if (!pluginDebugger.getDebugging()) {
                    sender.sendMessage("No Debugging enabled!");
                } else {
                    pluginDebugger.setDebugging(false);
                    sendDebugMessage(sender, pluginDebugger, null);
                }
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("out")) {
                if (!pluginDebugger.getDebugging()) {
                    sender.sendMessage("No Debugging enabled!");
                } else {
                    pluginDebugger.setDebugging(false);
                    sendDebugMessage(sender, pluginDebugger, args[1]);
                }
            }
        }
        return true;
    }

    private void sendDebugMessage(CommandSender sender, PluginDebugger pluginDebugger, String pluginName) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern( "#,###,###,###" );
    	if (pluginName != null) {
            Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
            if (plugin == null) {
        	    sender.sendMessage("No Plugin with Name '" + pluginName + "' found. Try again!");
                return;
            }
            ListenerLog listenerLog = pluginDebugger.getPluginDebugger(plugin);
            sender.sendMessage("Debug Informations (" + pluginName + "):");
            sender.sendMessage(plugin.getDescription().getName() + ": " + decimalFormat.format(listenerLog.getFullCount()) + "ns");
            for(String key : listenerLog.getListenerLog().keySet()) {
                sender.sendMessage("-> " + key + ": " + decimalFormat.format(listenerLog.getCount(key)) + "ns (" + decimalFormat.format((listenerLog.getCount(key).doubleValue() / pluginDebugger.getFullUsedTime().doubleValue()) * 100) + "%)");
            }
        } else {
            ArrayList<ListenerLog> pluginDebugs = pluginDebugger.getAll();
            for (ListenerLog listenerLog : pluginDebugs) {
                sender.sendMessage(listenerLog.getPlugin().getDescription().getName() + ": " + decimalFormat.format(listenerLog.getFullCount()) + "ns (" + decimalFormat.format((listenerLog.getFullCount().doubleValue() / pluginDebugger.getFullUsedTime().doubleValue()) * 100) + "%)");
		    }
        }
        sender.sendMessage("----------------------");
        sender.sendMessage("Total: (" + pluginDebugger.getAll().size() + " Plugins) " + decimalFormat.format(pluginDebugger.getFullUsedTime()) + "ns");
        sender.sendMessage("Used Listener events: " + decimalFormat.format(pluginDebugger.getCalledListenersCount()));
        sender.sendMessage("Debug Running Time: " + decimalFormat.format(System.nanoTime() - startTime) + "ns");
        pluginDebugger.clearAll();
    }
}
