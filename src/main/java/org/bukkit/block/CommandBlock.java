package org.bukkit.block;

/**
 * Represents a command block.
 */
public interface CommandBlock extends BlockState {
    /**
     * Gets the command that the command block would currently run.
     *
     * @return The command line that will be executed when the command block is activated.
     */
    String getCommand();

    /**
     * Sets the command that the command block would run.
     *
     * @param command The command that would be executed when the command block is activated.
     */
    void setCommand(String command);
}
