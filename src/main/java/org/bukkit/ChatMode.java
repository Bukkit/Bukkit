package org.bukkit;

public enum ChatMode {
    FULL(0),
    COMMAND_ONLY(1),
    HIDDEN(2);

    private int intMode;

    private ChatMode(int mode) {
        this.intMode = mode;
    }

    /**
     * Get the int representing the ChatMode
     *
     * @return int representation of the ChatMode
     */
    public int getIntMode() {
        return intMode;
    }

    /**
     * Get the correct ChatMode Enum based on the mode
     *
     * @param mode Mode as integer
     * @return Correct ChatMode Enum or null if none was found
     */
    public static ChatMode getByIntMode(int mode) {
        for (ChatMode m : values()) {
            if (m.getIntMode() == mode) return m;
        }

        return null;
    }
}
