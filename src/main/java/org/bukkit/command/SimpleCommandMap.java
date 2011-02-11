package org.bukkit.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Server;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescription;
import org.bukkit.plugin.PluginManager;

public final class SimpleCommandMap implements CommandMap {
    private final Map<String, Command> knownCommands = new HashMap<String, Command>();
    private final Server server;

    public SimpleCommandMap(final Server server) {
        this.server = server;
        setDefaultCommands(server);
    }

    /**
     * Helper for installing the built-in Bukkit commands
     */
    private void setDefaultCommands(final Server server) {
        register("bukkit", new VersionCommand(server));
        register("bukkit", new ReloadCommand(server));
        register("bukkit", new PluginsCommand(server));
        register("bukkit", new EnableCommand(server));
        register("bukkit", new DisableCommand(server));
    }

    /**
     * {@inheritDoc}
     */
    public void registerAll(String fallbackPrefix, List<Command> commands) {
        if (commands != null) {
            for(Command c : commands) {
                register(fallbackPrefix, c);
            }
        }
    }

    /**
     * Helper that registers a single command
     */
    private void register(String fallbackPrefix, Command command) {
        List<String> names = new ArrayList<String>();
        names.add(command.getName());
        names.addAll(command.getAliases());

        for (String name : names) {
            register(name, fallbackPrefix, command);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean register(String name, String fallbackPrefix, Command command) {
        boolean nameInUse = (knownCommands.get(name) != null);
        if (nameInUse)
            name = fallbackPrefix + ":" + name;

        knownCommands.put(name, command);
        return !nameInUse;
    }

    /**
     * {@inheritDoc}
     */
    public boolean dispatch(CommandSender sender, String commandLine) {
        String[] args = commandLine.split(" ");
        String sentCommandLabel = args[0].toLowerCase();

        args = Arrays.copyOfRange(args, 1, args.length);

        Command target = knownCommands.get(sentCommandLabel);
        boolean isRegisteredCommand = (target != null);
        if (isRegisteredCommand) {
            try {
                target.execute(sender, sentCommandLabel, args);
            } catch (CommandException ex) {
                throw ex;
            } catch (Throwable ex) {
                throw new CommandException("Unhandled exception executing '" + commandLine + "' in " + target, ex);
            }
        }
        return isRegisteredCommand;
    }


    /**
     * {@inheritDoc}
     */
    public void clearCommands() {
        synchronized (this) {
            knownCommands.clear();
            setDefaultCommands(server);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void clearCommands(Plugin plugin) {
        synchronized (this) {
            Iterator<Command> iterator = knownCommands.values().iterator();
            while (iterator.hasNext()) {
                Command command = iterator.next();
                if (command.getPlugin() == plugin) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * Implements the "/bukkit version" command
     */
    private static class VersionCommand extends Command {
        private final Server server;

        public VersionCommand(Server server) {
            super(null, "version");
            this.server = server;
            this.tooltip = "Gets the version of this server including any plugins in use";
            this.usageMessage = "/version [plugin name]";
            this.setAliases(Arrays.asList("ver", "about"));
        }

        @Override
        public boolean execute(CommandSender sender, String currentAlias, String[] args) {
            if (args.length == 0) {
                sender.sendMessage("This server is running " + ChatColor.GREEN
                        + server.getName() + ChatColor.WHITE + " version " + ChatColor.GREEN + server.getVersion());
                sender.sendMessage("This server is also sporting some funky dev build of Bukkit!");
            } else {
                StringBuilder name = new StringBuilder();

                for (String arg : args) {
                    if (name.length() > 0) {
                        name.append(' ');
                    }
                    name.append(arg);
                }

                Plugin plugin = server.getPluginManager().getPlugin(name.toString());

                if (plugin != null) {
                    PluginDescription desc = plugin.getDescription();
                    sender.sendMessage(ChatColor.GREEN + desc.getName() + ChatColor.WHITE + " version " + ChatColor.GREEN + desc.getVersion());

                    if (desc.getDescription() != null) {
                        sender.sendMessage(desc.getDescription());
                    }

                    if (desc.getWebsite() != null) {
                        sender.sendMessage("Website: " + ChatColor.GREEN + desc.getWebsite());
                    }

                    if (!desc.getAuthors().isEmpty()) {
                        if (desc.getAuthors().size() == 1) {
                            sender.sendMessage("Author: " + getAuthors(desc));
                        } else {
                            sender.sendMessage("Authors: " + getAuthors(desc));
                        }
                    }
                } else {
                    sender.sendMessage("This server is not running any plugin by that name.");
                    sender.sendMessage("Use /plugins to get a list of plugins.");
                }
            }

            return true;
        }

        /**
         * Helper that builds a string description of a plugin's authors
         */
        private String getAuthors(final PluginDescription desc) {
            StringBuilder result = new StringBuilder();
            ArrayList<String> authors = desc.getAuthors();

            for (int i = 0; i < authors.size(); i++) {
                if (result.length() > 0) {
                    result.append(ChatColor.WHITE);

                    if (i < authors.size() - 1) {
                        result.append(", ");
                    } else {
                        result.append(" and ");
                    }
                }

                result.append(ChatColor.GREEN);
                result.append(authors.get(i));
            }

            return result.toString();
        }
    }

    /**
     * Implements the "/bukkit reload" command
     */
    private static class ReloadCommand extends Command {
        private final Server server;

        public ReloadCommand(Server server) {
            super(null, "reload");
            this.server = server;
            this.tooltip = "Reloads the server configuration and plugins";
            this.usageMessage = "/reload";
            this.setAliases(Arrays.asList("rl"));
        }

        @Override
        public boolean execute(CommandSender sender, String currentAlias, String[] args) {
            if (sender.isOp()) {
                server.reload();
                sender.sendMessage(ChatColor.GREEN + "Reload complete.");
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have sufficient access" + " to reload this server.");
            }
            return true;
        }
    }

    /**
     * Implements the "/bukkit plugins" command
     */
    private static class PluginsCommand extends Command {
        private final Server server;
        
        public PluginsCommand(Server server) {
            super(null, "plugins");
            this.server = server;
            this.tooltip = "Gets a list of plugins running on the server";
            this.usageMessage = "/plugins";
            this.setAliases(Arrays.asList("pl"));
        }
        
        @Override
        public boolean execute(CommandSender sender, String currentAlias, String[] args) {
            sender.sendMessage("Plugins: " + getPluginList());
            return true;
        }

        /**
         * Helper that builds a string description of all loaded plugins
         */
        private String getPluginList() {
            StringBuilder pluginList = new StringBuilder();
            Plugin[] plugins = server.getPluginManager().getPlugins();

            for (Plugin plugin : plugins) {
                if (pluginList.length() > 0) {
                    pluginList.append(ChatColor.WHITE);
                    pluginList.append(", ");
                }

                pluginList.append(ChatColor.GREEN);
                pluginList.append(plugin.getDescription().getName());
            }

            return pluginList.toString();
        }
    }

    /**
     * Implements the "/bukkit enable" command
     */
    private static class EnableCommand extends Command {
        private final Server server;

        public EnableCommand(Server server) {
            super(null, "enable");
            this.server = server;
            this.tooltip = "Enable a plugin";
            this.usageMessage = "/enable <name>";
        }

        @Override
        public boolean execute(CommandSender sender, String currentAlias, String[] args) {
            if (args.length != 1) {
                return false;
            }
            String pluginName = args[0];
            PluginManager manager = server.getPluginManager();
            PluginDescription description = manager.getPluginDescription(pluginName);
            if (description == null) {
                sender.sendMessage("Plugin " + pluginName + " not found");
                return true;
            }
            manager.enablePlugin(description);
            return true;
        }
    }

    /**
     * Implements the "/bukkit disable" command
     */
    private static class DisableCommand extends Command {
        private final Server server;

        public DisableCommand(Server server) {
            super(null, "disable");
            this.server = server;
            this.tooltip = "Disable a plugin";
            this.usageMessage = "/disable<name>";
        }

        @Override
        public boolean execute(CommandSender sender, String currentAlias, String[] args) {
            if (args.length != 1) {
                return false;
            }
            String pluginName = args[0];
            PluginManager manager = server.getPluginManager();
            Plugin plugin = manager.getPlugin(pluginName);
            if (plugin == null) {
                sender.sendMessage("There is no enabled plugin called " + pluginName);
                return true;
            }
            manager.disablePlugin(plugin);
            return true;
        }
    }
}