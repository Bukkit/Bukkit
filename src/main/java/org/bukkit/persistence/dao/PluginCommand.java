package org.bukkit.persistence.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permission.Security;
import org.bukkit.persistence.annotation.PersistClass;
import org.bukkit.persistence.annotation.PersistField;

import com.elmakers.mine.craftbukkit.persistence.Persistence;

/**
 * A data class for encapsulating and storing a Command object.
 * 
 * @author NathanWolf
 *
 */
@PersistClass(name="command", schema="global")
public class PluginCommand implements Comparable<PluginCommand>
{	
	/**
	 * The default constructor, used by Persistence to create instances.
	 * 
	 * Use PluginUtilities to create PluginCommands.
	 * 
	 * @see com.elmakers.mine.bukkit.utilities.PluginUtilities#getCommand(String, String, String, CommandSenderData, String, PermissionType)
	 */
	public PluginCommand()
	{
		
	}
	
	protected PluginCommand(PluginData plugin, String commandName, String tooltip, PermissionType pType)
	{
		this.plugin = plugin;
		this.command = commandName;
		this.tooltip = tooltip;
		this.permissionType = pType;
	}
	
	/**
	 * Set up automatic command binding for this command.
	 * 
	 * If you dispatch commands with messaging.dispatch, this command will automatically call
	 * the given method on the listener class if executed.
	 * 
	 * For Player commands, the signature should be:
	 * 
	 * public boolean onMyCommand(Player player, String[] parameters)
	 * {
	 * }
	 * 
	 * For General commands, a CommandSender should be used in place of Player.
	 * 
	 * @param methodName
	 * @see com.elmakers.mine.bukkit.utilities.PluginUtilities#dispatch(Object, CommandSender, String, String[])
	 */
	public void bind(String methodName)
	{
		callbackMethod = methodName;
	}
	
	/**
	 * Use this to add an additional usage (example) string to this command.
	 * 
	 * @param use The usage string
	 */
	public void addUsage(String use)
	{
		if (use == null || use.length() <= 0) return;
		
		if (usage == null)
		{
			usage = new ArrayList<String>();
		}
		if (!usage.contains(use))
		{
			usage.add(use);
		}
	}
	
	/**
	 * Use this to add an additional command sender that is able to receive this type of message.
	 * 
	 * @param sender the command sender to add
	 */
	public void addSender(CommandSenderData sender)
	{
		if (sender == null) return;
		
		if (senders == null)
		{
			senders = new ArrayList<CommandSenderData>();
		}
		if (!senders.contains(sender))
		{
			senders.add(sender);
		}
	}
	
	/**
	 * Get or create a sub-command of this command.
	 * 
	 * @param subCommandName The sub-command name
	 * @param defaultTooltip The default tooltip
	 * @param defaultUsage The default usage string
	 * @return A new command object
	 */
	public PluginCommand getSubCommand(String subCommandName, String defaultTooltip, String defaultUsage)
	{
		return getSubCommand(subCommandName, defaultTooltip, defaultUsage, null, PermissionType.DEFAULT);
	}
	
	/**
	 * Get or create a sub-command of this command.
	 * 
	 * @param subCommandName The sub-command name
	 * @param defaultTooltip The default tooltip
	 * @param defaultUsage The default usage string
	 * @param pType The type of permissions to apply
	 * @return A new command object
	 */
	public PluginCommand getSubCommand(String subCommandName, String defaultTooltip, String defaultUsage, PermissionType pType)
	{
		return getSubCommand(subCommandName, defaultTooltip, defaultUsage, null, pType);
	}
	
	/**
	 * Get or create a sub-command of this command.
	 * 
	 * @param subCommandName The sub-command name
	 * @param defaultTooltip The default tooltip
	 * @param defaultUsage The default usage string
	 * @param pNode The permission node to use
	 * @param pType The type of permissions to apply
	 * @return A new command object
	 */
	public PluginCommand getSubCommand(String subCommandName, String defaultTooltip, String defaultUsage, String pNode, PermissionType pType)
	{
		PluginCommand child = childMap.get(subCommandName);
		if (child == null)
		{
			child = new PluginCommand(plugin, subCommandName, defaultTooltip, pType);
			child.addUsage(defaultUsage);
			
			if (pNode == null)
			{
				permissionNode = child.getDefaultPermissionNode();
			}
			else
			{
				permissionNode = pNode;
			}
			child.setPermissionNode(permissionNode);
			
			// adds senders
			addSubCommand(child);	
			
			Persistence persistence = Persistence.getInstance();
			persistence.put(child);
			persistence.put(this);
		}
		
		return child;
	}
	
	/**
	 * Add a command to this command as a sub-command.
	 * 
	 * Sub-commands are activated using parameters. So:
	 *
	 * /persist list global.player.NathanWolf
	 * 
	 * Consists of the main Command "persist", one sub-command "list",
	 * and one parameter "global.player.NathanWolf".
	 * 
	 * @param command The command to add as a sub-command of this one
	 */
	protected void addSubCommand(PluginCommand command)
	{
		if (children == null)
		{
			children = new ArrayList<PluginCommand>();
		}
		
		// Child will self-register!
		command.setParent(this);
		
		// Pass on any senders
		if (senders != null)
		{
			for (CommandSenderData sender : senders)
			{
				command.addSender(sender);
			}
		}
	}
	
	public boolean checkPermission(CommandSender sender)
	{
		Player player = null;
		if (sender instanceof Player)
		{
			player = (Player)sender;
		}

		if (permissionType == PermissionType.PLAYER_ONLY)
		{
			if (player != null) return false;
		}
		else
		{
			if (player == null) return true;
		}
		switch (permissionType)
		{
			case ALLOW_ALL: return true;
			case OPS_ONLY:
				if (player == null) return false;
				return player.isOp();
			case DEFAULT:
				if (permissionNode != null && permissionNode.length() > 0 && security != null)
				{
					return security.hasPermission(player, permissionNode);
				}
				break;
			case ADMINS_ONLY:
				if (permissionNode != null && permissionNode.length() > 0 && security != null)
				{
					return security.hasPermission(player, permissionNode);
				}
				break;
		}
		
		return true;
	}
	
	/**
	 * Check to see if this command matches a given command string.
	 * 
	 * If the command sender is a player, a permissions check will be done.
	 * 
	 * @param sender the sender requesting access. 
	 * @param commandString The command string to check
	 * @return Whether or not the command succeeded
	 */
	public boolean checkCommand(CommandSender sender, String commandString)
	{
		return (command.equals(commandString) || command.equals(commandString.toLowerCase()));
	}
	
	/**
	 * Use to send a short informational help message
	 * 
	 * This can be used when the player has mis-entered parameters or some other exceptional case.
	 * 
	 * @param sender The CommandSender to reply to
	 */
	public void sendShortHelp(CommandSender sender)
	{
		sendHelp(sender, "Use: ", false, false);
	}
	
	public void sendUse(CommandSender sender)
	{
		sendHelp(sender, "Use: ", true, true);
	}
	
	/**
	 * Use this to display a help message for this command to the given sender.
	 * 
	 * CommandSender may be a player, server console, etc.
	 * 
	 * @param sender The CommandSender (e.g. Player) to display help to
	 * @param prefix A prefix, such as "Use: " to put in front of the first line
	 * @param showUsage Whether or not to show detailed usage information
	 * @param showSubCommands Whether or not to also display a tree of sub-command usage
	 */
	public void sendHelp(CommandSender sender, String prefix, boolean showUsage, boolean showSubCommands)
	{
		boolean useSlash = sender instanceof Player;
		String slash = useSlash ? "/" : "";
		String currentIndent = "";
		
		if (callbackMethod != null)
		{
			String message = currentIndent + slash + getPath() + " : "  + tooltip;
			sender.sendMessage(prefix + message);
			currentIndent += indent;
		
			if (showUsage && usage != null)
			{
				for (String exampleUse : usage)
				{
					sender.sendMessage(currentIndent + " ex: " + getPath() + " " + exampleUse);
				}
			}
		}
		
		if (showSubCommands && children != null)
		{
			for (PluginCommand child : children)
			{
				child.sendHelp(sender, "", showUsage, showSubCommands);
			}
		}
	}

	public String getDefaultPermissionNode()
	{
		String pNode = "";
		PluginCommand addParent = parent;
		while (addParent != null)
		{
			pNode = addParent.command + "." + pNode;
			addParent = addParent.parent;
		}
		pNode = plugin.getId() + ".commands." + pNode + command;
		return pNode;
	}
	
	public int compareTo(PluginCommand compare)
	{
		return command.compareTo(compare.getCommand());
	}
	
	protected String getPath()
	{
		String path = command;
		if (parent != null)
		{
			path = parent.getPath() + " " + path;
		}
		return path;
	}
	
	@PersistField(id=true, auto=true)
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	@PersistField
	public String getCommand()
	{
		return command;
	}
	
	public void setCommand(String command)
	{
		// Must do this here too, since we maintain a hash of sub-commands by command name!
		removeFromParent();
		this.command = command;
		addToParent();
	}

	@PersistField
	public void setPlugin(PluginData plugin)
	{
		this.plugin = plugin;
	}

	public PluginData getPlugin()
	{
		return plugin;
	}

	@PersistField
	public void setParent(PluginCommand parent)
	{
		removeFromParent();
		this.parent = parent;
		addToParent();
	}

	public PluginCommand getParent()
	{
		return parent;
	}
	
	public List<PluginCommand> getChildren()
	{
		return children;
	}

	@PersistField
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	@PersistField
	public void setUsage(List<String> usage)
	{
		this.usage = usage;
	}

	public List<String> getUsage()
	{
		return usage;
	}

	@PersistField
	public void setTooltip(String tooltip)
	{
		this.tooltip = tooltip;
	}

	public String getTooltip()
	{
		return tooltip;
	}

	@PersistField
	public void setSenders(List<CommandSenderData> senders)
	{
		this.senders = senders;
	}

	public List<CommandSenderData> getSenders()
	{
		return senders;
	}

	public String getCallbackMethod()
	{
		return callbackMethod;
	}
	
	protected void addToParent()
	{
		if (parent != null)
		{
			if (parent.children == null)
			{
				parent.children = new ArrayList<PluginCommand>();
			}
			if (parent.childMap.get(command) == null)
			{
				parent.children.add(this);
				parent.childMap.put(command, this);
			}
		}
	}
	
	protected void removeFromParent()
	{
		if (parent != null)
		{
			if (parent.childMap.get(command) != null)
			{
				parent.children.remove(this);
				parent.childMap.remove(command);
			}
		}
	}

	@PersistField
	public String getPermissionNode()
	{
		return permissionNode;
	}

	public void setPermissionNode(String permissionNode)
	{
		this.permissionNode = permissionNode;
	}

	@PersistField
	public void setPermissionType(PermissionType permissionType)
	{
		this.permissionType = permissionType;
	}

	public PermissionType getPermissionType()
	{
		return permissionType;
	}
	
	public static void setSecurity(Security security)
	{
		PluginCommand.security = security;
	}

	private List<PluginCommand>	children;	
	private PermissionType		permissionType;
	private String				permissionNode;
	private String				callbackMethod;
	private int					id;
	private boolean				enabled = true;
	private String				command;
	private String				tooltip;
	private List<String>		usage;
	private PluginData			plugin;
	private PluginCommand		parent;
	private List<CommandSenderData> senders;

	// Transient data
	private HashMap<String, PluginCommand>	childMap	= new HashMap<String, PluginCommand>();
	private static final String				indent		= "  ";
	private static Security					security = null;

}
