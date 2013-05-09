package org.bukkit.event.player;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.meta.BookMeta;

/**
 * Called when a player edits or signs a book and quill item
 */
public class PlayerEditBookEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final BookMeta previousBookMeta;
    private BookMeta newBookMeta;
    private final int slot;
    private final boolean isSigning;
    private boolean cancel;

    public PlayerEditBookEvent(Player who, int slot, BookMeta previousBookMeta, BookMeta newBookMeta, boolean isSigning) {
        super(who);

        Validate.isTrue(slot >= 0 && slot <=8, "Slot must be in range 0-8 inclusive");
        Validate.notNull(previousBookMeta, "Previous book metadata must not be null");
        Validate.notNull(newBookMeta, "New book metadata must not be null");

        Bukkit.getItemFactory().equals(previousBookMeta, newBookMeta);

        this.previousBookMeta = previousBookMeta;
        this.newBookMeta = newBookMeta;
        this.slot = slot;
        this.isSigning = isSigning;
        this.cancel = false;
    }

    /**
     * Gets the book meta data currently on the book
     * <p>
     * Note: this is a copy of the book meta data.  You cannot use this
     * object to change the existing book meta data.
     *
     * @return the book meta data currently on the book
     */
    public BookMeta getPreviousBookMeta() {
        return previousBookMeta.clone();
    }

    /**
     * Gets the book meta data that the player is attempting to add to
     * the book
     * <p>
     * Note: this is a copy of the proposed new book meta data.  Use
     * {@link #setNewBookMeta(BookMeta)} to change what will actually be
     * added to the book.
     *
     * @return the book meta data that the player is attempting to add
     */
    public BookMeta getNewBookMeta() {
        return newBookMeta.clone();
    }

    /**
     * Gets the inventory slot number for the book item that triggered this
     * event
     * <p>
     * This is a slot number on the player's hotbar in the range 0-8.
     *
     * @return the inventory slot number that the book item occupies
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Sets the book meta data that will actually be added to the book
     *
     * @param bookMeta new book meta data
     * @throws IllegalArgumentException if the new book meta data is null
     */
    public void setNewBookMeta(BookMeta newBookMeta) throws IllegalArgumentException {
        Validate.notNull(newBookMeta, "New book metadata must not be null");
        Bukkit.getItemFactory().equals(newBookMeta, null);
        this.newBookMeta = newBookMeta.clone();
    }

    /**
     * Checks if this event is being fired because the book is being signed
     *
     * @return true if the book is being signed, false otherwise
     */
    public boolean isSigning() {
        return isSigning;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
