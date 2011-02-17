package org.bukkit.persistence.core;

class PersistenceDefaults
{
	// Defaults
	public final String[] persistCommand = {"persist", "Manage Persistence", "<sub-command> <parameters>"};
	public final String[] saveSubCommand = {"save", "Save cached data", null};
	public final String[] describeSubCommand = {"describe", "Describe entities and schema", null};
	public final String[] listSubCommand = {"list", "List entities or data", "<schema>.<entity>"};
	public final String[] reloadSubCommand = {"reload", "Reload an entity",  null};
	public final String[] resetSubCommand = {"RESET", "DROP an entity table", "<schema>.<entity>"};
	public final String[] suCommand = {"su", "Enable full permission access", null};
	public final String[] helpCommand = {"phelp", "Get help on Persistence plugins", "<command | plugin>"};

	public final String[] describeUsage = {"<schema>", "<schema>.<entity>"};
	public final String[] listUsage = {"<schema>.<entity>.<id>"};
	
	public final String dataSavedMessage = "Data saved.";
	public final String resettingEntityMessage = "RESETTING entity: %s.%s";
	public final String reloadingEntityMessage = "Reloading entity: %s.%s";
	public final String entityNotFoundMessage = "Can't find entity: %s.%s with %s=%s";
	public final String entityDisplayMessage = "Entity %s.%s:";
	public final String entityListMessage = "%s, %s : %d entities:";
	public final String schemaListMessage = "Schemas:";
	public final String schemaDisplayMessage = "Schema %s:";
	public final String unknownSchemaMessage = "Unknown schema: %s";
	public final String unknownEntityMessage = "Unknown entity: %s.%s";
	public final String pluginListMessage = "Use: phelp <plugin | command> for detailed help";
	public final String commandListMessage = "Use: phelp <command>:";
	public final String pluginNotFoundMessage = "Plugin %s not found";
	public final String suEnabledMessage = "Full access enabled. Use /su again to revert to normal user.";
	public final String suDisabledMessage = "Normal access restored.";
}
