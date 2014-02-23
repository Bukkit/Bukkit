package org.bukkit.command;

import static org.bukkit.util.Java15Compat.Arrays_copyOfRange;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.Validate;
import org.bukkit.Server;
import org.bukkit.command.defaults.*;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

public class SimpleCommandMap implements CommandMap {
    private static final Pattern PATTERN_ON_SPACE = Pattern.compile(" ", Pattern.LITERAL);
    protected final Map<String, Command> knownCommands = new HashMap<String, Command>();
    private final Server server;

    public SimpleCommandMap(final Server server) {
        this.server = server;
        setDefaultCommands();
    }

    private void setDefaultCommands() {
        register("bukkit", new SaveCommand());
        register("bukkit", new SaveOnCommand());
        register("bukkit", new SaveOffCommand());
        register("bukkit", new StopCommand());
        register("bukkit", new VersionCommand("version"));
        register("bukkit", new ReloadCommand("reload"));
        register("bukkit", new PluginsCommand("plugins"));
        register("bukkit", new TimingsCommand("timings"));
    }

    public void setFallbackCommands() {
        register("bukkit", new ListCommand());
        register("bukkit", new OpCommand());
        register("bukkit", new DeopCommand());
        register("bukkit", new BanIpCommand());
        register("bukkit", new PardonIpCommand());
        register("bukkit", new BanCommand());
        register("bukkit", new PardonCommand());
        register("bukkit", new KickCommand());
        register("bukkit", new TeleportCommand());
        register("bukkit", new GiveCommand());
        register("bukkit", new TimeCommand());
        register("bukkit", new SayCommand());
        register("bukkit", new WhitelistCommand());
        register("bukkit", new TellCommand());
        register("bukkit", new MeCommand());
        register("bukkit", new KillCommand());
        register("bukkit", new GameModeCommand());
        register("bukkit", new HelpCommand());
        register("bukkit", new ExpCommand());
        register("bukkit", new ToggleDownfallCommand());
        register("bukkit", new BanListCommand());
        register("bukkit", new DefaultGameModeCommand());
        register("bukkit", new SeedCommand());
        register("bukkit", new DifficultyCommand());
        register("bukkit", new WeatherCommand());
        register("bukkit", new SpawnpointCommand());
        register("bukkit", new ClearCommand());
        register("bukkit", new GameRuleCommand());
        register("bukkit", new EnchantCommand());
        register("bukkit", new TestForCommand());
        register("bukkit", new EffectCommand());
        register("bukkit", new ScoreboardCommand());
        register("bukkit", new PlaySoundCommand());
        register("bukkit", new SpreadPlayersCommand());
        register("bukkit", new SetWorldSpawnCommand());
        register("bukkit", new SetIdleTimeoutCommand());
        register("bukkit", new AchievementCommand());
    }

    /**
     * {@inheritDoc}
     */
    public void registerAll(String fallbackPrefix, List<Command> commands) {
        if (commands != null) {
            for (Command c : commands) {
                register(fallbackPrefix, c);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean register(String fallbackPrefix, Command command) {
        return register(command.getName(), fallbackPrefix, command);
    }

    /**
     * {@inheritDoc}
     */
    public boolean register(String label, String fallbackPrefix, Command command) {
        label = label.toLowerCase().trim();
        fallbackPrefix = fallbackPrefix.toLowerCase().trim();
        boolean registered = register(label, command, false);
        knownCommands.put(fallbackPrefix + ":" + label, command);

        Iterator<String> iterator = command.getAliases().iterator();
        while (iterator.hasNext()) {
            if (!register(iterator.next(), command, true)) {
                iterator.remove();
            }
        }

        // If we failed to register under the real name, we need to set the command label to the direct address
        if (!registered) {
            command.setLabel(fallbackPrefix + ":" + label);
        }

        // Register to us so further updates of the commands label and aliases are postponed until its reregistered
        command.register(this);

        return registered;
    }

    /**
     * Registers a command with the given name is possible.
     *
     * @param label the name of the command, without the '/'-prefix.
     * @param command the command to register
     * @return true if command was registered, false otherwise.
     */
    private synchronized boolean register(String label, Command command, boolean isAlias) {
        if (isAlias && knownCommands.containsKey(label)) {
            // Request is for an alias and it conflicts with a existing command or previous alias ignore it
            // Note: This will mean it gets removed from the commands list of active aliases
            return false;
        }

        boolean registered = true;

        // If the command exists but is an alias we overwrite it, otherwise we return
        Command conflict = knownCommands.get(label);
        if (conflict != null && conflict.getLabel().equals(label)) {
            return false;
        }

        if (!isAlias) {
            command.setLabel(label);
        }
        knownCommands.put(label, command);

        return registered;
    }

    /**
     * {@inheritDoc}
     */
    public boolean dispatch(CommandSender sender, String commandLine) throws CommandException {
        String[] args = PATTERN_ON_SPACE.split(commandLine);

        if (args.length == 0) {
            return false;
        }

        String sentCommandLabel = args[0].toLowerCase();
        Command target = getCommand(sentCommandLabel);

        if (target == null) {
            return false;
        }

        try {
            // Note: we don't return the result of target.execute as thats success / failure, we return handled (true) or not handled (false)
            target.execute(sender, sentCommandLabel, Arrays_copyOfRange(args, 1, args.length));
        } catch (CommandException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw new CommandException("Unhandled exception executing '" + commandLine + "' in " + target, ex);
        }

        // return true as command was handled
        return true;
    }

    public synchronized void clearCommands() {
        for (Map.Entry<String, Command> entry : knownCommands.entrySet()) {
            entry.getValue().unregister(this);
        }
        knownCommands.clear();
        setDefaultCommands();
    }

    public Command getCommand(String name) {
        Command target = knownCommands.get(name.toLowerCase());
        return target;
    }

    public List<String> tabComplete(CommandSender sender, String cmdLine) {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(cmdLine, "Command line cannot null");

        int spaceIndex = cmdLine.indexOf(' ');

        if (spaceIndex == -1) {
            ArrayList<String> completions = new ArrayList<String>();
            Map<String, Command> knownCommands = this.knownCommands;

            final String prefix = (sender instanceof Player ? "/" : "");

            for (Map.Entry<String, Command> commandEntry : knownCommands.entrySet()) {
                Command command = commandEntry.getValue();

                if (!command.testPermissionSilent(sender)) {
                    continue;
                }

                String name = commandEntry.getKey(); // Use the alias, not command name

                if (StringUtil.startsWithIgnoreCase(name, cmdLine)) {
                    completions.add(prefix + name);
                }
            }

            Collections.sort(completions, String.CASE_INSENSITIVE_ORDER);
            return completions;
        }

        String commandName = cmdLine.substring(0, spaceIndex);
        Command target = getCommand(commandName);

        if (target == null) {
            return null;
        }

        if (!target.testPermissionSilent(sender)) {
            return null;
        }

        String argLine = cmdLine.substring(spaceIndex + 1, cmdLine.length());
        String[] args = PATTERN_ON_SPACE.split(argLine, -1);

        try {
            return target.tabComplete(sender, commandName, args);
        } catch (CommandException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw new CommandException("Unhandled exception executing tab-completer for '" + cmdLine + "' in " + target, ex);
        }
    }

    public Collection<Command> getCommands() {
        return Collections.unmodifiableCollection(knownCommands.values());
    }

    public void registerServerAliases() {
        Map<String, String[]> values = server.getCommandAliases();

        for (String alias : values.keySet()) {
            if (alias.contains(":") || alias.contains(" ")) {
                server.getLogger().warning("Could not register alias " + alias + " because it contains illegal characters");
                continue;
            }

            String[] commandStrings = values.get(alias);
            List<String> targets = new ArrayList<String>();
            StringBuilder bad = new StringBuilder();

            for (String commandString : commandStrings) {
                String[] commandArgs = commandString.split(" ");
                Command command = getCommand(commandArgs[0]);

                if (command == null) {
                    if (bad.length() > 0) {
                        bad.append(", ");
                    }
                    bad.append(commandString);
                } else {
                    targets.add(commandString);
                }
            }

            if (bad.length() > 0) {
                server.getLogger().warning("Could not register alias " + alias + " because it contains commands that do not exist: " + bad);
                continue;
            }

            // We register these as commands so they have absolute priority.
            if (targets.size() > 0) {
                knownCommands.put(alias.toLowerCase(), new FormattedCommandAlias(alias.toLowerCase(), targets.toArray(new String[targets.size()])));
            } else {
                knownCommands.remove(alias.toLowerCase());
            }
        }
    }
}
