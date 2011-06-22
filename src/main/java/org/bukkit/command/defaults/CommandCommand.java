package org.bukkit.command.defaults;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.MultipleCommandAlias;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;

public class CommandCommand extends Command {

    private final SimpleCommandMap commandMap;

    public CommandCommand(SimpleCommandMap commandMap) {
        super("command");
        this.commandMap = commandMap;
        this.usageMessage = "/command [command name]";
        this.description = "Gets information about the command";
        this.setAliases("cmd");
        this.setPermission("bukkit.command.command");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (args.length == 0) {
            Collection<Command> registeredCommands = this.commandMap.getCommands();
            Collection<VanillaCommand> fallbackCommands = SimpleCommandMap.getFallbackCommands();
            HashSet<Command> allCommands = new HashSet<Command>(registeredCommands.size() + fallbackCommands.size());
            allCommands.addAll(registeredCommands);
            allCommands.addAll(fallbackCommands);
            ArrayList<String> all = new ArrayList<String>(allCommands.size());
            process(allCommands, all, COMMAND_CALLBACK);
            Collections.sort(all, getCollator(Collator.SECONDARY));
            sender.sendMessage("Available commands (" + ChatColor.GREEN + all.size() + ChatColor.WHITE + "):");
            sender.sendMessage(concat(all));
            return true;
        } else if (args.length == 1) {
            Command cmd = this.commandMap.getCommandWithFallback(args[0]);
            if (cmd != null) {
                sender.sendMessage("Commandinfo for '" + ChatColor.GREEN + cmd.getName() + ChatColor.WHITE + "':");
                if (!args[0].equalsIgnoreCase(cmd.getName())) {
                    sender.sendMessage("You searched with an alias!");
                }
                sender.sendMessage(cmd.getDescription());
                if (cmd instanceof PluginCommand) {
                    sender.sendMessage("Registered by " + ChatColor.GREEN + ((PluginCommand) cmd).getPlugin().getDescription().getFullName());
                } else if (cmd instanceof VanillaCommand) {
                    sender.sendMessage("This command is a command from " + ChatColor.GREEN + "Minecraft");
                } else if (cmd instanceof MultipleCommandAlias) {
                    sender.sendMessage("Alias for: " + concat(Arrays.asList(((MultipleCommandAlias) cmd).getCommands()), COMMAND_CALLBACK));
                }
                if (cmd.getAliases().isEmpty()) {
                    sender.sendMessage("There are no aliases for this command available.");
                } else {
                    sender.sendMessage("Aliases: " + concat(cmd.getAliases()));
                }
                String[] usageLines = cmd.getUsage().replace("<command>", cmd.getName()).split("\n");
                if (usageLines.length > 0) {
                    sender.sendMessage("Usage: " + usageLines[0]);
                    for (int i = 1; i < usageLines.length; i++) {
                        sender.sendMessage(usageLines[i]);
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Unknown command name given.");
            }
            return true;
        } else {
            return false;
        }
    }

    private static final CommandCallback COMMAND_CALLBACK = new CommandCallback();

    private static class CommandCallback implements Callback<String, Command> {
        public String call(Command command) {
            String meta = "";
            if (command instanceof MultipleCommandAlias) {
                meta = "Alias";
            } else if (command instanceof VanillaCommand) {
                meta = "Minecraft";
            } else if (command instanceof PluginCommand) {
                meta = "Plugin: " + ((PluginCommand) command).getPlugin().getDescription().getName();
            } else if (command instanceof VersionCommand || command instanceof ReloadCommand || command instanceof PluginsCommand) {
                meta = "Bukkit";
            }
            if (!meta.isEmpty()) {
                meta = " (" + meta + ")";
            }
            return command.getName() + meta;
        }
    }

    // Utility stuff

    /*
     * Nothing special for this command and could/should be moved to a utility
     * class.
     */
    public static <I, O> List<O> process(Collection<I> input, List<O> output, Callback<O, ? super I> processor) {
        for (I i : input) {
            output.add(processor.call(i));
        }
        return output;
    }

    /**
     * <p>
     * Concat a list of elements with an and between the last two and a comma
     * between the rest. This method is using
     * {@link CommandCommand#concat(List, Callback)} with
     * {@link CommandCommand#TO_STRING_CALLBACK} as callback.
     * </p>
     * <p>
     * For example the lines {@code [ 'Hello', 'World', 'Foo', 'Bar' ]} would
     * result in {@code 'Hello, World, Foo and Bar'}.
     * </p>
     * 
     * @param lines
     *            all elements.
     * @return a concatenated string with all elements.
     * @see {@link CommandCommand#concat(List, Callback)}
     */
    public static String concat(List<? extends Object> lines) {
        return concat(lines, TO_STRING_CALLBACK);
    }

    /**
     * Returns a {@link Collator} with the specified strength.
     * @param strength the strength of the collator.
     * @return the collator.
     * @see {@link Collator#getInstance()}
     * @see {@link Collator#setStrength(int)}
     * @exception IllegalArgumentException If the strength value is not one of
     * {@link Collator#PRIMARY}, {@link Collator#SECONDARY}, {@link Collator#TERTIARY} or {@link Collator#IDENTICAL}.
     */
    public static Collator getCollator(int strength) {
        Collator result = Collator.getInstance();
        result.setStrength(strength);
        return result;
    }

    // This interface was copied from Bukkit pull request #232
    // https://github.com/xZise/Bukkit/commit/a50d16fbb4ab1a19b0488756d4f88dde662d665f#diff-1
    /**
     * Primitive callback class, to allow specific callback handling. For example to determine if a block in sight is invisible.
     *
     * @param <Result> This is the type it will return.
     * @param <Parameter> This is the type it has as parameter.
     */
    public interface Callback<Result, Parameter> {

        /**
         * This method will be called on each step.
         *
         * @param parameter The parameter of this step.
         * @return The result of the step.
         */
        public Result call(Parameter parameter);

    }

    /** Default instance. */
    public static final ToStringCallback TO_STRING_CALLBACK = new ToStringCallback();

    /**
     * Simple callback which only calls the {@link Object#toString()} method. 
     */
    public static class ToStringCallback implements Callback<String, Object> {
        public String call(Object parameter) {
            return parameter.toString();
        }
    }

    /**
     * <p>
     * Concat a list of elements with an and between the last two and a comma
     * between the rest. The callback determines the resulted string for each
     * element in the list.
     * </p>
     * <p>
     * For example the lines {@code [ 'Hello', 'World', 'Foo', 'Bar' ]} would
     * result in {@code 'Hello, World, Foo and Bar'}.
     * </p>
     * 
     * @param lines
     *            all elements.
     * @param callback
     *            Callback instance to get the string for an element.
     * @return a concatenated string with all elements.
     */
    public static <T extends Object> String concat(List<T> lines, Callback<String, ? super T> callback) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < lines.size(); i++) {
            if (result.length() > 0) {
                result.append(ChatColor.WHITE);

                if (i < lines.size() - 1) {
                    result.append(", ");
                } else {
                    result.append(" and ");
                }
            }

            result.append(ChatColor.GREEN);
            result.append(callback.call(lines.get(i)));
        }

        return result.toString();
    }
}
