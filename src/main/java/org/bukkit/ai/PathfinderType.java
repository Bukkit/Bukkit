package org.bukkit.ai;

import org.bukkit.event.entity.EntityTargetEvent;
/** 
 * Represents type of a Pathfinder
 *
 */
public enum PathfinderType {
    /**
     * Goal pathfinders are peaceful and help mobs to swim, panic and lots of more useful things
     */
    GOAL,
    /**
     * Targetting pathfinders help monsters & golems to choose targets
     *  Note: You should call {@link EntityTargetEvent} when you choose target
     */
    TARGET
}
