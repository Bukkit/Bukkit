package org.bukkit.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.dao.CommandSenderData;
import org.bukkit.persistence.dao.Message;
import org.bukkit.persistence.dao.PermissionType;
import org.bukkit.persistence.dao.PlayerData;
import org.bukkit.persistence.dao.PluginCommand;
import org.bukkit.persistence.dao.PluginData;
import org.bukkit.persistence.dao.WorldData;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.elmakers.mine.craftbukkit.persistence.Persistence;

/** 
 * An interface for displaying data-driven messages and processing data-driven commands.
 * 
 * @author NathanWolf
 *
 */
public class PluginUtilities
{
	/**
	 * Messaging constructor. Use to create an instance of Messaging for your plugin.
	 * 
	 * This can also be done via persistence.getMessaging(plugin)
	 * 
	 * @param requestingPlugin The plugin requesting the messaging interface
	 * @param persistence The Persistence reference to use for retrieving data
	 */
	public PluginUtilities(Plugin requestingPlugin, Persistence persistence)
	{
		this.persistence = persistence;
		this.owner = requestingPlugin;
		
		// Retreive or create the plugin data record for this plugin.
		PluginDescriptionFile pdfFile = requestingPlugin.getDescription();
		String pluginId = pdfFile.getName();
		plugin = persistence.get(pluginId, PluginData.class);
		if (plugin == null)
		{
			plugin = new PluginData(requestingPlugin);
			persistence.put(plugin);
		}
		
		// Let the plugin bind its transient command and message instances
		List<Message> allMessages = new ArrayList<Message>();
		List<PluginCommand> allCommands = new ArrayList<PluginCommand>();
		persistence.getAll(allMessages, Message.class);
		persistence.getAll(allCommands, PluginCommand.class);
		plugin.initializeCache(allMessages, allCommands);
		
		playerSender = persistence.get("player", CommandSenderData.class);
	}
	
	public Plugin getOwningPlugin()
	{
		return owner;
	}
	
	public PlayerData getPlayer(Player player)
	{
		PlayerData playerData = persistence.get(player.getName(), PlayerData.class);
		if (playerData == null)
		{
			playerData = new PlayerData(player);
		}
		else
		{
			playerData.update(player);
		}
		persistence.put(playerData);
		
		return playerData;
	}
	
	public WorldData getWorld(Server server, String name, Environment defaultType)
	{
		WorldData data = persistence.get(name, WorldData.class);
		if (data == null)
		{
			data = new WorldData(name, defaultType);
			addNewWorld(server, data);
		}
		
		return data;
	}
	
	protected void addNewWorld(Server server, WorldData newWorld)
	{		
		//TODO : Ok, really need to find some way to automatically persist references....
		persistence.put(newWorld);
		persistence.put(newWorld.getSpawn());
	}
	
	public WorldData getWorld(Server server, World world)
	{
		WorldData data = persistence.get(world.getName(), WorldData.class);
		if (data == null)
		{
			data = new WorldData();
			data.update(world);
			addNewWorld(server, data);
		}
		else
		{
			data.update(world);
		}
		
		return data;
	}
	
	/**
	 * Get a message based on id, or create one using a default.
	 * 
	 * @param id The message id
	 * @param defaultString The default string to use if no value exists
	 * @return The stored message, or defaultString if none exists
	 */
	public Message getMessage(String id, String defaultString)
	{
		return plugin.getMessage(id, defaultString);
	}
	
	/**
	 * Retrieve a player command description based on id. 
	 * 
	 * A command description can be used to easily process commands, including
	 * commands with sub-commands.
	 * 
	 * This method automatically creates a player-specific (in-game) command.
	 * 
	 * @param commandName The command id to retrieve or create
	 * @param defaultTooltip The default tooltip to use if this is a new command
	 * @param defaultUsage The default usage string, more can be added
	 * @return A command descriptor
	 */
	public PluginCommand getPlayerCommand(String commandName, String defaultTooltip, String defaultUsage)
	{
		return getPlayerCommand(commandName, defaultTooltip, defaultUsage, null, PermissionType.DEFAULT);
	}
	
	/**
	 * Retrieve a player command description based on id. 
	 * 
	 * A command description can be used to easily process commands, including
	 * commands with sub-commands.
	 * 
	 * This method automatically creates a player-specific (in-game) command.
	 * 
	 * @param commandName The command id to retrieve or create
	 * @param defaultTooltip The default tooltip to use if this is a new command
	 * @param defaultUsage The default usage string, more can be added
	 * @param pType The type of permissions to apply to this command
	 * @return A command descriptor
	 */
	public PluginCommand getPlayerCommand(String commandName, String defaultTooltip, String defaultUsage, PermissionType pType)
	{
		return getCommand(commandName, defaultTooltip, defaultUsage, playerSender, null, pType);
	}
	
	/**
	 * Retrieve a player command description based on id. 
	 * 
	 * A command description can be used to easily process commands, including
	 * commands with sub-commands.
	 * 
	 * This method automatically creates a player-specific (in-game) command.
	 * 
	 * @param commandName The command id to retrieve or create
	 * @param defaultTooltip The default tooltip to use if this is a new command
	 * @param defaultUsage The default usage string, more can be added
	 * @param pNode Override the default permission node
	 * @param pType The type of permissions to apply to this command
	 * @return A command descriptor
	 */
	public PluginCommand getPlayerCommand(String commandName, String defaultTooltip, String defaultUsage, String pNode, PermissionType pType)
	{
		return getCommand(commandName, defaultTooltip, defaultUsage, playerSender, pNode, pType);
	}
	
	/**
	 * Retrieve a general command description based on id. 
	 * 
	 * A command description can be used to easily process commands, including
	 * commands with sub-commands.
	 * 
	 * This method automatically creates a general command that will be passed
	 * a CommandSender for use as a server or in-game command.
	 * 
	 * @param commandName The command id to retrieve or create
	 * @param defaultTooltip The default tooltip to use if this is a new command
	 * @param defaultUsage The default usage string, more can be added
	 * @return A command descriptor
	 */
	public PluginCommand getGeneralCommand(String commandName, String defaultTooltip, String defaultUsage)
	{
		return getGeneralCommand(commandName, defaultTooltip, defaultUsage, null, PermissionType.DEFAULT);
	}
	
	/**
	 * Retrieve a general command description based on id. 
	 * 
	 * A command description can be used to easily process commands, including
	 * commands with sub-commands.
	 * 
	 * This method automatically creates a general command that will be passed
	 * a CommandSender for use as a server or in-game command.
	 * 
	 * @param commandName The command id to retrieve or create
	 * @param defaultTooltip The default tooltip to use if this is a new command
	 * @param defaultUsage The default usage string, more can be added
	 * @param pNode Override the default permission node
	 * @param pType The type of permissions to apply to this command
	 * @return A command descriptor
	 */
	public PluginCommand getGeneralCommand(String commandName, String defaultTooltip, String defaultUsage, PermissionType pType)
	{
		return getCommand(commandName, defaultTooltip, defaultUsage, null, null, pType);
	}
	
	/**
	 * Retrieve a general command description based on id. 
	 * 
	 * A command description can be used to easily process commands, including
	 * commands with sub-commands.
	 * 
	 * This method automatically creates a general command that will be passed
	 * a CommandSender for use as a server or in-game command.
	 * 
	 * @param commandName The command id to retrieve or create
	 * @param defaultTooltip The default tooltip to use if this is a new command
	 * @param defaultUsage The default usage string, more can be added
	 * @param pNode Override the default permission node
	 * @param pType The type of permissions to apply to this command
	 * @return A command descriptor
	 */
	public PluginCommand getGeneralCommand(String commandName, String defaultTooltip, String defaultUsage, String pNode, PermissionType pType)
	{
		return getCommand(commandName, defaultTooltip, defaultUsage, null, pNode, pType);
	}
	
	/**
	 * Retrieve a command description based on id, for a given sender
	 * 
	 * A command description can be used to easily process commands, including
	 * commands with sub-commands.
	 * 
	 * @param commandName The command id to retrieve or create
	 * @param defaultTooltip The default tooltip to use if this is a new command
	 * @param defaultUsage The default usage string, more can be added
	 * @param sender The sender that will issue this command
	 * @return A command descriptor
	 */
	public PluginCommand getCommand(String commandName, String defaultTooltip, String defaultUsage, CommandSenderData sender, String pNode, PermissionType pType)
	{
		return plugin.getCommand(commandName, defaultTooltip, defaultUsage, sender, pNode, pType);
	}

	/**
	 * Dispatch any automatically bound command handlers.
	 * 
	 * Any commands registered with this plugin that around bound() to a command handler will be automatically called.
	 * 
	 * For Player commands, the signature should be:
	 * 
	 * public boolean onMyCommand(Player player, String[] parameters)
	 * {
	 * }
	 * 
	 * For General commands, a CommandSender should be used in place of Player.
	 * 
	 * @param listener The class that will handle the command callback
	 * @param sender The sender of this command
	 * @param baseCommand The base command issues
	 * @param baseParameters Any parameters (or sub-commands) passed to the base command 
	 * @see PluginCommand#bind(String)
	 */
	public boolean dispatch(Object listener, CommandSender sender, String baseCommand, String[] baseParameters)
	{
		List<PluginCommand> baseCommands = plugin.getCommands();
		if (baseCommands == null) return false;
		
		List<PluginCommand> commandsCopy = new ArrayList<PluginCommand>();
		commandsCopy.addAll(baseCommands);
		
		for (PluginCommand command : commandsCopy)
		{
			boolean success = dispatch(listener, sender, command, baseCommand, baseParameters);
			if (success) return true;
		}
		return false;
	}
	
	protected boolean dispatch(Object listener, CommandSender sender, PluginCommand command, String commandString, String[] parameters)
	{		
		if (command != null && command.checkCommand(sender, commandString))
		{
			if (!command.checkPermission(sender))
			{
				return true;
			}
			
			boolean handledByChild = false;
			if (parameters != null && parameters.length > 0)
			{
				String[] childParameters = new String[parameters.length - 1];
				for (int i = 0; i < childParameters.length; i++)
				{
					childParameters[i] = parameters[i + 1];
				}
				String childCommand = parameters[0];
				
				List<PluginCommand> subCommands = command.getChildren();
				if (subCommands != null)
				{
					List<PluginCommand> commandsCopy = new ArrayList<PluginCommand>();
					commandsCopy.addAll(subCommands);
					
					for (PluginCommand child : commandsCopy)
					{
						handledByChild = dispatch(listener, sender, child, childCommand, childParameters);
						if (handledByChild)
						{
							return true;
						}
					}
				}
			}
			
			// Not handled by a sub-child, so handle it ourselves.
			String callbackName = command.getCallbackMethod();
			if (callbackName == null || callbackName.length() <= 0) 
			{
				// auto help for commands that only have sub-commands
				command.sendUse(sender);
				return true;
			}
			
			try
			{
				List<CommandSenderData> senders = command.getSenders();
				
				if (senders != null)
				{
					for (CommandSenderData senderData : senders)
					{
						Class<?> senderType = senderData.getType();
						if (senderType == null) continue;
						try
						{
							Method customHandler;
							customHandler = listener.getClass().getMethod(callbackName, senderType, String[].class);
							try
							{
								return (Boolean)customHandler.invoke(listener, senderType.cast(sender), parameters);
							}
							catch(InvocationTargetException clientEx)
							{
								log.severe("Error invoking callback '" + callbackName);
								clientEx.getTargetException().printStackTrace();
								return false;
							}
							catch(Throwable clientEx)
							{
								log.severe("Error invoking trying to invoke callback '" + callbackName);
								clientEx.printStackTrace();
								return false;
							}
						}
						catch (NoSuchMethodException e)
						{
						}					
					}
				}

				Method genericHandler;
				genericHandler = listener.getClass().getMethod(callbackName, CommandSender.class, String[].class);
				return (Boolean)genericHandler.invoke(listener, sender, parameters);
			}
			catch (NoSuchMethodException ex)
			{
				log.warning("Persistence: Can't find callback method " + callbackName + " of " + listener.getClass().getName());
			}					
			catch (SecurityException ex)
			{
				log.warning("Persistence: Can't access callback method " + callbackName + " of " + listener.getClass().getName() + ", make sure it's public");
			}
			catch (IllegalArgumentException ex)
			{
				log.warning("Persistence: Can't find callback method " + callbackName + " of " + listener.getClass().getName() + " with the correct signature, please consult the docs.");
			}
			catch (IllegalAccessException ex)
			{
				log.warning("Persistence: Can't access callback method " + callbackName + " of " + listener.getClass().getName());
			}
			catch (InvocationTargetException ex)
			{
				log.severe("Persistence: Error invoking callback method " + callbackName + " of " + listener.getClass().getName());
				ex.printStackTrace();
			}
		}
		
		return false;
	}

	private Persistence persistence;
	private Plugin owner;
	private PluginData plugin;
	private CommandSenderData playerSender;
	private static final Logger log = Persistence.getLogger();
}
