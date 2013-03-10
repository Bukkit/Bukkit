package org.bukkit.scoreboard;

import org.bukkit.OfflinePlayer;

public interface Objective {
    enum Criteria {
        HEALTH(true),
        PLAYER_KILL_COUNT(),
        TOTAL_KILL_COUNT(),
        DUMMY(),
        DEATH_COUNT();

        private final boolean readOnly;

        Criteria() {
            this.readOnly = false;
        }

        Criteria(boolean readOnly) {
            this.readOnly = readOnly;
        }

        public boolean isReadOnly() {
            return this.readOnly;
        }
    }

    enum Display {
        NONE,
        LIST,
        SIDEBAR,
        BELOW_NAME;
    }

    /*
     * Gets the objective's name
     *
     * @return The team's name
     */
    public String getName();

    /*
     * Gets the objective's display name
     *
     * @return The objective's display name, can be null
     */
    public String getDisplayName();

    /*
     * Sets the objective's display name
     *
     * @param displayName New display name, can not be null
     */
    public void setDisplayName(String displayName);

    /*
     * Gets the objective's display slot
     *
     * @return Where the objective is displayed
     */
    public Display getDisplaySlot();

    /*
     * Sets the objective's display slot
     *
     * @param display New display slot, can not be null
     */
    public void setDisplaySlot(Display display);

    /*
     * Gets the score for a player
     *
     * @param player Player to get a score for, can not be null
     * @return Player's score
     */
    public int getScore(OfflinePlayer player);

    /*
     * Sets the score for a player
     *
     * @param player Player to set a score for, can not be null
     * @param score New score
     */
    public void setScore(OfflinePlayer player, int score);
}
