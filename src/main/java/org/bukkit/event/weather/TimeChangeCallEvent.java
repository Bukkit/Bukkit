package org.bukkit.event.weather;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class TimeChangeCallEvent implements Runnable {
        
        public void run() {
                for(World worlds : Bukkit.getWorlds()) {
                        TimeChangeEvent event = new TimeChangeEvent(worlds, worlds.getTime() - 1, worlds.getTime());
                        Bukkit.getPluginManager().callEvent(event);
                        if(event.isCancelled()) {
                                event.setCurrentTime(event.getTimeBefore());
                        }
                }
        }
}
