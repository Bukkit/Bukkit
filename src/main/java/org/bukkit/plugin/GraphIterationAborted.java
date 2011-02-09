package org.bukkit.plugin;

public class GraphIterationAborted extends Exception {
    private static final long serialVersionUID = 101315760852619962L;

    public GraphIterationAborted() {
        super();
    }

    public GraphIterationAborted(String message, Throwable cause) {
        super(message, cause);
    }

    public GraphIterationAborted(String message) {
        super(message);
    }

    public GraphIterationAborted(Throwable cause) {
        super(cause);
    }
}
