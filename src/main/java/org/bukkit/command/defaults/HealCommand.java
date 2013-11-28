package org.bukkit.command.defaults;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand extends BukkitCommand {
    public HealCommand(String name) {
        super(name);
        this.description = "Heal yourself if you are hurt!";
        this.usageMessage = "/heal";
        this.setPermission("bukkit.command.heal");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;

        Player player = (Player) sender;
        
        player.setHealth(20);
        
        player.sendMessage("You have healed yourself.");

        return true;
    }
}
