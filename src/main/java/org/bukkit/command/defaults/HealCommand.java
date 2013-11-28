package org.bukkit.command.defaults;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.ImmutableList;

public class HealCommand extends VanillaCommand {
    public HealCommand() {
        super("heal");
        this.description = "Heal yourself if you are hurt";
        this.usageMessage = "/heal";
        this.setPermission("bukkit.command.heal");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;

        if (sender instanceof Player) {
            Player player = (Player) sender;

            player.setHealth(20);
            sender.sendMessage("You have healed yourself.");
        } else {
            sender.sendMessage("Console is not allowed to use this command!");
        }

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "Alias cannot be null");

        return ImmutableList.of();
    }
}
