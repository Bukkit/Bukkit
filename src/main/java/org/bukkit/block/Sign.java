package org.bukkit.block;

import org.bukkit.chat.Message;

/**
 * Represents either a SignPost or a WallSign
 */
public interface Sign extends BlockState {

    /**
     * Gets all the lines of text currently on this sign.
     *
     * @return Array of Strings containing each line of text
     * @deprecated This method now uses {@link Message} to return the lines. Use
     *     {@link #getLineMessages()} instead.
     */
    @Deprecated
    public String[] getLines();

    /**
     * Gets all the lines of text currently on this sign.
     *
     * @return Array of Message containing each line of text
     */
    public Message[] getLineMessages();

    /**
     * Gets the line of text at the specified index.
     * <p>
     * For example, getLine(0) will return the first line of text.
     *
     * @param index Line number to get the text from, starting at 0
     * @throws IndexOutOfBoundsException Thrown when the line does not exist
     * @return Text on the given line
     * @deprecated This method now uses {@link Message} to return the line. Use
     *     {@link #getLineMessage(int)} instead.
     */
    @Deprecated
    public String getLine(int index) throws IndexOutOfBoundsException;

    /**
     * Gets the line of text at the specified index.
     * <p>
     * For example, getLine(0) will return the first line of text.
     *
     * @param index Line number to get the text from, starting at 0
     * @throws IndexOutOfBoundsException Thrown when the line does not exist
     * @return Message on the given line
     */
    public Message getLineMessage(int index) throws IndexOutOfBoundsException;

    /**
     * Sets the line of text at the specified index.
     * <p>
     * For example, setLine(0, "Line One") will set the first line of text to
     * "Line One".
     *
     * @param index Line number to set the text at, starting from 0
     * @param line New text to set at the specified index
     * @throws IndexOutOfBoundsException If the index is out of the range 0..3
     * @deprecated This method now uses {@link Message} to set the lines. Use
     *     {@link #setLine(int,Message)} instead.
     */
    @Deprecated
    public void setLine(int index, String line) throws IndexOutOfBoundsException;

    /**
     * Sets the line of text at the specified index.
     * <p>
     * For example, setLine(0, "Line One") will set the first line of text to
     * "Line One".
     *
     * @param index Line number to set the text at, starting from 0
     * @param line New text to set at the specified index
     * @throws IndexOutOfBoundsException If the index is out of the range 0..3
     */
    public void setLine(int index, Message line) throws IndexOutOfBoundsException;
}
