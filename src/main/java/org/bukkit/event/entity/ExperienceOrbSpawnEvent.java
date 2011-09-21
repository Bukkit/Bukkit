package org.bukkit.event.entity;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

/**
 * Called when spawning experience, allowing customisation of the experience
 * and the way it is broken down between experience orbs. If the event is
 * cancelled, no orbs will be spawned.
 */
public class ExperienceOrbSpawnEvent extends EntitySpawnEvent implements Cancellable {

    private int experience;
    private ExperienceBreakdown experienceBreakdown;
    
    /**
     * Constructs an ExperienceOrbSpawnEvent with the specified parameters.
     * 
     * @param spawner
     *            Entity from which experience is spawned.
     * @param loc
     *            Location at which experience is to be spawned.
     * @param experience
     *            Total value of experience.
     * @param breakdown
     *            How the experience is broken down.
     */
    public ExperienceOrbSpawnEvent(Entity spawner, Location loc, int experience, ExperienceBreakdown breakdown) {
        super(Type.EXPERIENCE_ORB_SPAWN, spawner, loc);
        this.experience = experience;
        this.experienceBreakdown = breakdown;
    }

    /**
     * Total experience to be spawned.
     * 
     * @return Total experience of all orbs.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Sets the total experience of all orbs to be spawned.
     * 
     * @param experience
     *            Total experience of all orbs.
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * The breakdown of experience between the orbs to be spawned.
     * 
     * @return Breakdown of experience between orbs.
     */
    public ExperienceBreakdown getExperienceBreakdown() {
        return experienceBreakdown;
    }

    /**
     * Sets the breakdown of experience between the orbs to be spawned.
     * 
     * @param breakdown
     *            Breakdown of experience between orbs.
     */
    public void setExperienceBreakdown(ExperienceBreakdown breakdown) {
        this.experienceBreakdown = breakdown;
    }
    
    /**
     * Describes the breakdown of experience into individual orbs.
     */
    public enum ExperienceBreakdown {
        /**
         * Experience is consolidated into a single orb.
         */
        SINGLE,
        /**
         * Experience is separated into multiple orbs using the default breakdown.
         */
        DEFAULT
    }
}
