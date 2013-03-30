package org.bukkit.scoreboard;

import java.util.Set;

import org.bukkit.OfflinePlayer;

public interface Scoreboard {
    /**
     * Creates a team
     *
     * @param name Team's new name, can not be null
     * @param displayName Team's display name, can be null
     * @return The newly created team, if successful
     * @throws IllegalArgumentException if name/display name is too long or the team already exists
     */
    public Team createTeam(String name, String displayName);

    /**
     * Gets a team by name
     *
     * @param name Team's name
     * @return The team that was requested, or null if not found
     */
    public Team getTeam(String name);

    /**
     * Gets all teams
     *
     * @return All the teams
     */
    public Set<Team> getTeams();

    /**
     * Puts a player on a team
     *
     * @param player Player that's getting a team, can not be null
     * @param team Player's new team, can be null to remove player from a team
     */
    public void setTeam(OfflinePlayer player, Team team);

    /**
     * Removes a team from the scoreboard
     *
     * @param team Team to remove, can not be null
     */
    public void removeTeam(Team team);

    /**
     * Creates an objective
     *
     * @param name Name of the objective that will be created, can not be null
     * @param criteria Criteria of the objective, can not be null
     * @param displayName Display name of the objective, can be null
     * @return The new objective that was created
     * @throws IllegalArgumentException if an objective with this name already exists, the name is too long, or the display name is too long
     */
    public Objective createObjective(String name, Objective.Criteria criteria, String displayName);

    /**
     * Gets an objective by name
     *
     * @param name Name of objective
     * @return The objective, or null if not found
     */
    public Objective getObjective(String name);

    /**
     * Gets all objectives
     *
     * @return objectives
     */
    public Set<Objective> getObjectives();

    /**
     * Removes an objective
     *
     * @param objective The objective to be removed, can not be null
     */
    public void removeObjective(Objective objective);

    /**
     * Resets a player's scores
     *
     * @param player The player whose scores will be reset, can not be null
     */
    public void resetScores(OfflinePlayer player);
}
