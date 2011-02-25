package org.bukkit.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

/**
 * Represents a command usable by players.
 */
public abstract class Command {
    private final Plugin plugin;
    private final String name;
    private List<String> aliases;
    protected String tooltip = "";
    protected String usageMessage;

    /**
     * Base constructor for all commands
     *
     * @param plugin The plugin associated with this command
     * @param name The primary name for this command
     */
    public Command(Plugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        this.aliases = new ArrayList<String>();
        this.usageMessage = "/" + name;
    }

    /**
     * Callback used when the command is issued
     *
     * @param sender The sender who isseud the command
     * @param currentAlias The name or alias used to issue this command
     * @param args A list of supplied arguments (excluding the command name itself)
     * @return True on success; false if usage should be displayed
     */
    public abstract boolean execute(CommandSender sender, String currentAlias, String[] args);

    /**
     * Get the Plugin associated with this command.
     *
     * May be null for built-in commands.
     *
     * @return A loaded Plugin.
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Gets the command name of this command
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a list of optional aliases for this command
     *
     * @return A list of names
     */
    public List<String> getAliases() {
        return aliases;
    }

    /**
     * Gets an optional tooltip for this command
     *
     * @return A short descriptive string
     */
    public String getTooltip() {
        return tooltip;
    }

    /**
     * Gets the usage information for this command
     *
     * @return One or more lines of text
     */
    public String getUsage() {
        return usageMessage;
    }

    /**
     * Updates the list of aliases for this command
     *
     * @return The command instance for chaining
     */
    public Command setAliases(List<String> aliases) {
        this.aliases = aliases;
        return this;
    }

    /**
     * Updates the tooltip for this command
     *
     * @return The command instance for chaining
     */
    public Command setTooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    /**
     * Updates the usage information for this command
     *
     * @return The command instance for chaining
     */
    public Command setUsage(String usage) {
        this.usageMessage = usage;
        return this;
    }

    /**
     * Displays the usage information.
     *
     * @param sender The command sender to display usage to
     * @param label The label that was used to execute the command
     */
    public void displayUsage(CommandSender sender, String label) {
        if (!usageMessage.isEmpty()) {
            String tmpMsg = usageMessage.replace("<command>", label);
            String[] usageLines = tmpMsg.split("\\n");
            for(String line: usageLines) {
                while (line.length() > 0) {
                    int stripChars = (line.length() > 53 ? 53:line.length());
                    sender.sendMessage(ChatColor.RED + line.substring(0, stripChars));
                    line = line.substring(stripChars);
                }
            }
        }
    }
}