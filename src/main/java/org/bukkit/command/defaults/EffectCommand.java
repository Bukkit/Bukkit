package org.bukkit.command.defaults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.StringUtil;

public class EffectCommand extends VanillaCommand{
	private static List<String> POTIONEFFECT_NAMES = new ArrayList<String>();
	
	public EffectCommand(){
		super("effect");
        this.description = "Gives a potion effect to a player.";
        this.usageMessage = "/effect <player> <effect> [duration] [amplifier]";
        this.setPermission("bukkit.command.effect");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) return true;
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
            return false;
        }
        
        Player player = Bukkit.getPlayerExact(args[0]);
        if (player == null) {
            sender.sendMessage("Can't find player " + args[0]);
        } else {
        	Integer effect = getInteger(args[1]);
        	Integer duration = getInteger(args[2]);
        	Integer amplifier = getInteger(args[3]);
       		if (effect != null && duration != null && amplifier != null){
               	PotionEffect potioneffect = new PotionEffect(PotionEffectType.getById(getInteger(args[1])), getInteger(args[2]) * 20, getInteger(args[3]));
               	player.sendMessage("Given " + PotionEffectType.getById(getInteger(args[1])).getName() + "(ID " + PotionEffectType.getById(getInteger(args[1])).getId() + ") * " + args[3] + " to " + args[0] + " for " + args[2] + " seconds.");
               	return player.addPotionEffect(potioneffect);
       		} else {
       			PotionEffectType potiontype = PotionEffectType.getByName(args[1].toUpperCase());
       			if (potiontype != null){
       				if (duration != null && amplifier != null) {
                       	PotionEffect potioneffect = new PotionEffect(potiontype, getInteger(args[2]) * 20, getInteger(args[3]));
                       	player.sendMessage("Given " + potiontype.getName() + "(ID " + potiontype.getId() + ") * " + args[3] + " to " + args[0] + " for " + args[2] + " seconds.");
                       	return player.addPotionEffect(potioneffect);
       				} else if (duration != null) {
                       	PotionEffect potioneffect = new PotionEffect(potiontype, getInteger(args[2]) * 20, 0);
                       	player.sendMessage("Given " + potiontype.getName() + "(ID " + potiontype.getId() + ") * 0 to " + args[0] + " for " + args[2] + " seconds.");
                       	return player.addPotionEffect(potioneffect);
       				} else if (amplifier != null) {
                       	PotionEffect potioneffect = new PotionEffect(potiontype, 30 * 20 , getInteger(args[3]));
                       	player.sendMessage("Given " + potiontype.getName() + "(ID " + potiontype.getId() + ") * 0 to " + args[0] + " for 30 seconds.");
                       	return player.addPotionEffect(potioneffect);
       				}

       			} else {
       				sender.sendMessage(String.format("Potion effect does not exist: %s", args[1]));
       			}
       		}

        }
		return false;
	}
	
    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "Alias cannot be null");

        if (args.length >= 1) {
            return super.tabComplete(sender, alias, args);
        }
        if (args.length == 2) {
            return StringUtil.copyPartialMatches(args[1], POTIONEFFECT_NAMES, new ArrayList<String>(POTIONEFFECT_NAMES.size()));
        }
        return ImmutableList.of();
    }
    
    public static void buildPotionEffects(){
    	if(!POTIONEFFECT_NAMES.isEmpty()){
            throw new IllegalStateException("Potion effects have already been built!");
    	}
    	for(PotionEffectType effect: PotionEffectType.values()){
    		POTIONEFFECT_NAMES.add(effect.getName());
    	}
    	Collections.sort(POTIONEFFECT_NAMES);
    }

}
