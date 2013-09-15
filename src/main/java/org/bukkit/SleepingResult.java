package org.bukkit;

public enum SleepingResult {
    OK,
    NOT_POSSIBLE_IN_NETHER_OR_END,
    NOT_POSSIBLE_DAYTIME,
    TOO_FAR_AWAY,
    ALREADY_SLEEPING_OR_DEAD,
    MONSTERS_NEARBY;
}
