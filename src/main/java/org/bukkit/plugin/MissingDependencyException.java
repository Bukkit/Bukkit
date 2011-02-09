package org.bukkit.plugin;

public final class MissingDependencyException extends GraphIterationAborted {
    private static final long serialVersionUID = -6063705632069820385L;

    public MissingDependencyException(String dependant, String dependency) {
        super("Could not resolve dependency " + dependant + " -> " + dependency);
    }
}
