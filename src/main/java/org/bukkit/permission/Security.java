package org.bukkit.permission;

import org.bukkit.entity.Player;

public interface Security
{
	public boolean hasPermission(Player player, String node);
}
