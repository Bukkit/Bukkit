package org.bukkit.event.entity;

import java.util.List;

import org.bukkit.chat.Message;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Thrown whenever a {@link Player} dies
 */
public class PlayerDeathEvent extends EntityDeathEvent {
    private int newExp = 0;
    private Message deathMessage;
    private int newLevel = 0;
    private int newTotalExp = 0;
    private boolean keepLevel = false;
    private boolean keepInventory = false;

    /**
     * Creates a new PlayerDeathEvent for the given player with the given
     * default message sent to all online players after the event finished being
     * processed.
     * 
     * @param player the player that had died
     * @param drops a list items the player will drop
     * @param droppedExp the dropped exp amount, does not have any impact on the
     *     actual players exp
     * @param deathMessage the death message being send to all online players
     */
    public PlayerDeathEvent(final Player player, final List<ItemStack> drops, final int droppedExp, final Message deathMessage) {
        this(player, drops, droppedExp, 0, deathMessage);
    }

    /**
     * Creates a new PlayerDeathEvent for the given player with the given
     * default message sent to all online players after the event finished being
     * processed.
     * 
     * @param player the player that had died
     * @param drops a list items the player will drop
     * @param droppedExp the dropped exp amount, does not have any impact on the
     *     actual players exp
     * @param deathMessage the death message being send to all online players
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #PlayerDeathEvent(Player, List, int, Message)} instead.
     */
    @Deprecated
    public PlayerDeathEvent(final Player player, final List<ItemStack> drops, final int droppedExp, final String deathMessage) {
        this(player, drops, droppedExp, 0, deathMessage);
    }

    /**
     * Creates a new PlayerDeathEvent for the given player with the given
     * default message sent to all online players after the event finished being
     * processed.
     * 
     * @param player the player that had died
     * @param drops a list items the player will drop
     * @param droppedExp the dropped exp amount, does not have any impact on the
     *     actual players exp
     * @param newExp the new exp amount the player should have after respawn
     * @param deathMessage the death message being send to all online players
     */
    public PlayerDeathEvent(final Player player, final List<ItemStack> drops, final int droppedExp, final int newExp, final Message deathMessage) {
        this(player, drops, droppedExp, newExp, 0, 0, deathMessage);
    }

    /**
     * Creates a new PlayerDeathEvent for the given player with the given
     * default message sent to all online players after the event finished being
     * processed.
     * 
     * @param player the player that had died
     * @param drops a list items the player will drop
     * @param droppedExp the dropped exp amount, does not have any impact on the
     *     actual players exp
     * @param newExp the new exp amount the player should have after respawn
     * @param deathMessage the death message being send to all online players
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #PlayerDeathEvent(Player, List, int, int, Message)} instead.
     */
    @Deprecated
    public PlayerDeathEvent(final Player player, final List<ItemStack> drops, final int droppedExp, final int newExp, final String deathMessage) {
        this(player, drops, droppedExp, newExp, 0, 0, deathMessage);
    }

    /**
     * Creates a new PlayerDeathEvent for the given player with the given
     * default message sent to all online players after the event finished being
     * processed.
     * 
     * @param player the player that had died
     * @param drops a list items the player will drop
     * @param droppedExp the dropped exp amount, does not have any impact on the
     *     actual players exp
     * @param newExp the new exp amount the player should have after respawn
     * @param newTotalExp the new total exp amount the player should have after
     *     respawn
     * @param newLevel the new exp level the player should have after respawn
     * @param deathMessage the death message being send to all online players
     */
    public PlayerDeathEvent(final Player player, final List<ItemStack> drops, final int droppedExp, final int newExp, final int newTotalExp, final int newLevel, final Message deathMessage) {
        super(player, drops, droppedExp);
        this.newExp = newExp;
        this.newTotalExp = newTotalExp;
        this.newLevel = newLevel;
        this.deathMessage = deathMessage;
    }

    /**
     * Creates a new PlayerDeathEvent for the given player with the given
     * default message sent to all online players after the event finished being
     * processed.
     * 
     * @param player the player that had died
     * @param drops a list items the player will drop
     * @param droppedExp the dropped exp amount, does not have any impact on the
     *     actual players exp
     * @param newExp the new exp amount the player should have after respawn
     * @param newTotalExp the new total exp amount the player should have after
     *     respawn
     * @param newLevel the new exp level the player should have after respawn
     * @param deathMessage the death message being send to all online players
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #PlayerDeathEvent(Player, List, int, int, int, int, Message)}
     *     instead.
     */
    @Deprecated
    public PlayerDeathEvent(final Player player, final List<ItemStack> drops, final int droppedExp, final int newExp, final int newTotalExp, final int newLevel, final String deathMessage) {
        this(player,drops,droppedExp,newExp, newTotalExp,newLevel, (Message) null);
        setDeathMessage(deathMessage);
    }

    @Override
    public Player getEntity() {
        return (Player) entity;
    }

    /**
     * Sets the death message to send to all online players. Set to null or
     * empty to prevent the message being shown.
     *
     * @param deathMessage the death message to show. Can be null, empty and can
     *     contain color codes.
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #setMessage(Message)} instead.
     */
    @Deprecated
    public void setDeathMessage(String deathMessage) {
        this.deathMessage = deathMessage == null || deathMessage.isEmpty() ? null : Message.of(deathMessage);
    }

    /**
     * Gets the death message that will be shown to everyone on the server. Can
     * be null/empty and may contain color codes.
     *
     * @return the death message to show
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #getMessage()} instead.
     */
    @Deprecated
    public String getDeathMessage() {
        return deathMessage == null ? null : deathMessage.toString();
    }

    /**
     * Gets the death message to send to all online players. Can be null.
     *
     * @return the join message being shown
     */
    public Message getMessage() {
        return deathMessage;
    }

    /**
     * Sets the join message to send to all online players. Set to null to
     * prevent the message being shown.
     *
     * @param deathMessage the death message to show
     */
    public void setMessage(Message deathMessage) {
        this.deathMessage = deathMessage;
    }

    /**
     * Gets how much EXP the Player should have at respawn.
     * <p>
     * This does not indicate how much EXP should be dropped, please see
     * {@link #getDroppedExp()} for that.
     *
     * @return New EXP of the respawned player
     */
    public int getNewExp() {
        return newExp;
    }

    /**
     * Sets how much EXP the Player should have at respawn.
     * <p>
     * This does not indicate how much EXP should be dropped, please see
     * {@link #setDroppedExp(int)} for that.
     *
     * @param exp New EXP of the respawned player
     */
    public void setNewExp(int exp) {
        newExp = exp;
    }

    /**
     * Gets the Level the Player should have at respawn.
     *
     * @return New Level of the respawned player
     */
    public int getNewLevel() {
        return newLevel;
    }

    /**
     * Sets the Level the Player should have at respawn.
     *
     * @param level New Level of the respawned player
     */
    public void setNewLevel(int level) {
        newLevel = level;
    }

    /**
     * Gets the Total EXP the Player should have at respawn.
     *
     * @return New Total EXP of the respawned player
     */
    public int getNewTotalExp() {
        return newTotalExp;
    }

    /**
     * Sets the Total EXP the Player should have at respawn.
     *
     * @param totalExp New Total EXP of the respawned player
     */
    public void setNewTotalExp(int totalExp) {
        newTotalExp = totalExp;
    }

    /**
     * Gets if the Player should keep all EXP at respawn.
     * <p>
     * This flag overrides other EXP settings
     *
     * @return True if Player should keep all pre-death exp
     */
    public boolean getKeepLevel() {
        return keepLevel;
    }

    /**
     * Sets if the Player should keep all EXP at respawn.
     * <p>
     * This overrides all other EXP settings
     *
     * @param keepLevel True to keep all current value levels
     */
    public void setKeepLevel(boolean keepLevel) {
        this.keepLevel = keepLevel;
    }

    /**
     * Sets if the Player keeps inventory on death.
     *
     * @param keepInventory True to keep the inventory
     */
    public void setKeepInventory(boolean keepInventory) {
        this.keepInventory = keepInventory;
    }

    /**
     * Gets if the Player keeps inventory on death.
     *
     * @return True if the player keeps inventory on death
     */
    public boolean getKeepInventory() {
        return keepInventory;
    }
}
