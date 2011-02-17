package org.bukkit.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.persistence.Persistence;
import org.bukkit.persistence.annotation.PersistClass;
import org.bukkit.persistence.annotation.PersistField;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 * A class to encapsulate data for a plugin.
 * 
 * Each plugin can register any number of messages and commands.
 * 
 * @author NathanWolf
 *
 */
@PersistClass(schema="global", name="plugin")
public class PluginData
{

	public PluginData()
	{
	}
	
	public PluginData(Plugin plugin)
	{
		update(plugin);
	}
	
	public void update(Plugin plugin)
	{
		PluginDescriptionFile pdfFile = plugin.getDescription();
		id = pdfFile.getName();
		version = pdfFile.getVersion();
		description = pdfFile.getDescription();
		authors = new ArrayList<String>();
		if (authors == null) 
		{
			authors = new ArrayList<String>();
		}
		authors.addAll(pdfFile.getAuthors());
		website = pdfFile.getWebsite();
	}
	
	public Message getMessage(String messageId, String defaultValue)
	{
		if (messages == null)
		{
			messages = new ArrayList<Message>();
		}

		for (Message  message : messages)
		{
			if (message.getMessageId().equalsIgnoreCase(messageId))
			{
				return message;
			}
		}
		
		// Create a new message
		Message message = new Message(messageId, defaultValue);
		
		// Add to command and mark dirty
		messages.add(message);
		
		Persistence persistence = Persistence.getInstance();
		persistence.put(message);
		persistence.put(this);
		
		return message;
	}
	
	public PluginCommand getCommand(String commandName, String defaultTooltip, String defaultUsage, CommandSenderData sender, String pNode, PermissionType pType)
	{
		// First, look for a root command by this name
		if (commands == null)
		{
			commands = new ArrayList<PluginCommand>();
		}
		
		for (PluginCommand command : commands)
		{
			if (command.getCommand().equalsIgnoreCase(commandName))
			{
				return command;
			}
		}
		
		// Create a new un-parented command
		Persistence persistence = Persistence.getInstance();
		PluginCommand command = new PluginCommand(this);
		
		command.setPermissionType(pType);
		command.setCommand(commandName);
		command.setTooltip(defaultTooltip);
		if (defaultUsage != null && defaultUsage.length() > 0)
		{
			command.addUsage(defaultUsage);
		}
		
		if (pNode == null)
		{
			command.setPermissionNode(command.getDefaultPermissionNode());
		}
		else
		{
			command.setPermissionNode(pNode);
		}
		
		if (sender != null)
		{
			command.addSender(sender);
		}
		
		commands.add(command);

		persistence.put(command);
		persistence.put(this);
		
		return command;
	}
	
	@PersistField
	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	@PersistField
	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@PersistField
	public List<String> getAuthors()
	{
		return authors;
	}

	public void setAuthors(List<String> authors)
	{
		this.authors = authors;
	}

	@PersistField
	public String getWebsite()
	{
		return website;
	}

	public void setWebsite(String website)
	{
		this.website = website;
	}

	@PersistField(id=true)
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
	
	@PersistField
	public List<PluginCommand> getCommands()
	{
		return commands;
	}

	public void setCommands(List<PluginCommand> commands)
	{
		this.commands = commands;
	}

	@PersistField(contained=true)
	public List<Message> getMessages()
	{
		return messages;
	}

	public void setMessages(List<Message> messages)
	{
		this.messages = messages;
	}

	protected String			id;
	protected String			version;
	protected String			description;
	protected List<String>		authors;
	protected String			website;
	
	// TODO: cache these in a hashmap
	protected List<PluginCommand>	commands;
	protected List<Message>		messages;
}
