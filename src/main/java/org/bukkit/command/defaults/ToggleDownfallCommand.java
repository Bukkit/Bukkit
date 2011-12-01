package org.bukkit.command.defaults;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleDownfallCommand extends VanillaCommand{
	 public ToggleDownfallCommand() {
	        super("toggledownfall");
	        this.description = "Toggles rain and snow";
	        this.usageMessage = "/toggledownfall";
	        this.setPermission("bukkit.command.toggledownfall");
	    }

	@Override
	public boolean matches(String input) {
		return input.startsWith("toggledownfall ");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel,
			String[] args) {
		if (!testPermission(sender)) return true;
		if (sender instanceof Player) {
			World world = ((Player)sender).getWorld();
			world.setStorm(!world.hasStorm());
		}else {
            sender.sendMessage("You can only perform this command as a player");
        }
		
		return true;
	}
}
