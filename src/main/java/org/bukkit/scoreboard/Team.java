package org.bukkit.scoreboard;


public interface Team {

    /*
     * Get the team's name
     *
     * @return The team's name
     */
    public String getName();

    /*
     * Get the team's display name
     *
     * @return The team's display name
     */
    public String getDisplayName();

    /*
     * Set the team's display name
     *
     * @param name New display name, can not be null
     */
    public void setDisplayName(String name);

    /*
     * Get the team's prefix
     *
     * @return The team's prefix, can not be null
     */
    public String getPrefix();

    /*
     * Set the team's prefix, can not be null
     *
     * @param prefix New prefix, can not be null
     */
    public void setPrefix(String prefix);

    /*
     * Get the team's suffix
     *
     * @return The team's suffix, can not be null
     */
    public String getSuffix();

    /*
     * Set the team's suffix
     *
     * @param suffix New suffix, can not be null
     */
    public void setSuffix(String suffix);

    /*
     * Gets whether or not the team can hurt members
     *
     * @return true if friendly fire is on, false otherwise
     */
    public boolean allowFriendlyFire();

    /*
     * Sets whether or not the team can hurt other members of this team
     *
     * @param friendlyFire
     */
    public void setAllowFriendlyFire(boolean friendlyFire);

    /*
     * Gets whether or not members of this team can see other members of this team who are invisible
     *
     * @return true if team can see invisible members, false otherwise
     */
    public boolean canSeeFriendlyInvisibles();

    /*
     * Sets whether or not members of this team can see other members of this team who are invisible
     *
     * @param seeFriendlyInvisibles
     */
    public void setSeeFriendlyInvisibles(boolean seeFriendlyInvisibles);
}
