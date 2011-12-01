package org.bukkit.command.defaults;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XpCommand extends VanillaCommand {
    public XpCommand(){
	super("xp");
	this.description = "Gives the specified user the given number of orbs.";
    this.usageMessage = "/xp <playername> <amount>";
    this.setPermission("bukkit.command.xp");
    }

	
	@Override
	public boolean matches(String input) {
		return input.startsWith("xp ") || input.equalsIgnoreCase("xp");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel,
			String[] args) {
		if (!testPermission(sender)) return true;
		Player victim = Bukkit.getPlayerExact(args[0]);
		int amount = 0;

        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            sender.sendMessage("Unable to convert amount, " + args[1]);
            return true;
        }
        if (victim == null) {
            sender.sendMessage("Can't find user " + args[0] + ". No xp.");
        }else{
        	victim.giveExp(amount);
        }
		return true;
	}

}
