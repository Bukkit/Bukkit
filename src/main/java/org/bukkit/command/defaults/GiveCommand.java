package org.bukkit.command.defaults;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Java15Compat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class GiveCommand extends VanillaCommand {
    public GiveCommand() {
        super("give");
        this.description = "Gives the specified player a certain amount of items";
        this.usageMessage = "/give <player> <item> [:|amount] [:|data]";
        this.setPermission("bukkit.command.give");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;
        
        if ((args.length < 2) || (args.length > 5)) {
            sendUsageMessage(sender);
            return false;
        }

        Player player = Bukkit.getPlayerExact(args[0]);

        if (player != null) {
            String argString = join(Java15Compat.Arrays_copyOfRange(args, 1, args.length), " ");
            Pattern pattern = Pattern.compile("(\\w+) *(:|[0-9]+)? *(:|[0-9]+)?");
            //Pattern pattern = Pattern.compile("^ *([0-9]+) *(:| +[0-9]+)? *(:| +[0-9]+)? *$");
            Matcher matcher = pattern.matcher(argString);
            
            if (matcher.find()) {
                String materialString = "";
                String dataString = "";
                String amountString = "";
                Material material = null;
                Byte data = new Byte((byte)0);
                int amount = 1;
                
                if (matcher.start(1) > -1) {
                    materialString = argString.substring(matcher.start(1), matcher.end(1));
                }
                if (matcher.start(2) > -1) {
                    amountString = argString.substring(matcher.start(2), matcher.end(2));
                }
                if (matcher.start(3) > -1) {
                    dataString = argString.substring(matcher.start(3), matcher.end(3));
                }
                
                material = Material.matchMaterial(materialString);
                
                try {
                    data = new Byte(dataString);
                } catch (NumberFormatException e) {
                }
                try {
                    amount = Integer.parseInt(amountString);
                } catch (NumberFormatException e) {
                }

                if (material != null) {
                    if (material == Material.AIR) {
                        sender.sendMessage("Can't give air.");
                        return true;
                    }
                    Command.broadcastCommandMessage(sender, "Giving " + player.getName() + " some " + material.getId() + " (" + material + ")");

                    if (amount < 1) amount = 1;
                    if (amount > 64) amount = 64;

                    player.getInventory().addItem(new ItemStack(material, amount, (short)0, data));
                } else {
                    sender.sendMessage("There's no item called " + args[1]);
                }
            } else {
                // The regex didn't match.
                sendUsageMessage(sender);
                return false;
            }
        } else {
            sender.sendMessage("Can't find user " + args[0]);
        }

        return true;
    }
    
    private void sendUsageMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
    }

    @Override
    public boolean matches(String input) {
        return input.startsWith("give ") || input.equalsIgnoreCase("give");
    }
    
    private static String join(String[] s, String delimiter) {
        if (s.length == 0) return "";
        StringBuffer buffer = new StringBuffer(s[0]);
        for (int i = 1; i < s.length; i++ ) {
            buffer.append(delimiter).append(s[i]);
        }
        return buffer.toString();
    }

}
