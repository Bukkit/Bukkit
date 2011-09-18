package org.bukkit;

import com.avaje.ebean.config.ServerConfig;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import org.bukkit.World.Environment;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.Recipe;
import org.bukkit.map.MapView;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Represents the Bukkit core, for version and Server singleton handling
 */
public final class Bukkit {
    private static Server server;

    /**
     * Static class cannot be initialized.
     */
    private Bukkit() {}

    /**
     * Gets the current {@link Server} singleton
     *
     * @return Server instance being ran
     */
    public static Server getServer() {
        return server;
    }

    /**
     * Attempts to set the {@link Server} singleton.
     *
     * This cannot be done if the Server is already set.
     *
     * @param server Server instance
     */
    public static void setServer(Server server) {
        if (Bukkit.server != null) {
            throw new UnsupportedOperationException("Cannot redefine singleton Server");
        }

        Bukkit.server = server;
        server.getLogger().info("This server is running " + server.getName() + " version " + server.getVersion());
    }

    @Deprecated
    public static String getName() {
        return server.getName();
    }

    @Deprecated
    public static String getVersion() {
        return server.getVersion();
    }

    @Deprecated
    public static Player[] getOnlinePlayers() {
        return server.getOnlinePlayers();
    }

    @Deprecated
    public static int getMaxPlayers() {
        return server.getMaxPlayers();
    }

    @Deprecated
    public static int getPort() {
        return server.getPort();
    }

    @Deprecated
    public static int getViewDistance() {
        return server.getViewDistance();
    }

    @Deprecated
    public static String getIp() {
        return server.getIp();
    }

    @Deprecated
    public static String getServerName() {
        return server.getServerName();
    }

    @Deprecated
    public static String getServerId() {
        return server.getServerId();
    }

    @Deprecated
    public static boolean getAllowNether() {
        return server.getAllowNether();
    }

    @Deprecated
    public static boolean hasWhitelist() {
        return server.hasWhitelist();
    }

    @Deprecated
    public static int broadcastMessage(String message) {
        return server.broadcastMessage(message);
    }

    @Deprecated
    public static String getUpdateFolder() {
        return server.getUpdateFolder();
    }

    @Deprecated
    public static Player getPlayer(String name) {
        return server.getPlayer(name);
    }

    @Deprecated
    public static List<Player> matchPlayer(String name) {
        return server.matchPlayer(name);
    }

    @Deprecated
    public static PluginManager getPluginManager() {
        return server.getPluginManager();
    }

    @Deprecated
    public static BukkitScheduler getScheduler() {
        return server.getScheduler();
    }

    @Deprecated
    public static ServicesManager getServicesManager() {
        return server.getServicesManager();
    }

    @Deprecated
    public static List<World> getWorlds() {
        return server.getWorlds();
    }

    @Deprecated
    public static World createWorld(String name, Environment environment) {
        return server.createWorld(name, environment);
    }

    @Deprecated
    public static World createWorld(String name, Environment environment, long seed) {
        return server.createWorld(name, environment, seed);
    }

    @Deprecated
    public static World createWorld(String name, Environment environment, ChunkGenerator generator) {
        return server.createWorld(name, environment, generator);
    }

    @Deprecated
    public static World createWorld(String name, Environment environment, long seed, ChunkGenerator generator) {
        return server.createWorld(name, environment, seed, generator);
    }

    @Deprecated
    public static boolean unloadWorld(String name, boolean save) {
        return server.unloadWorld(name, save);
    }

    @Deprecated
    public static boolean unloadWorld(World world, boolean save) {
        return server.unloadWorld(world, save);
    }

    @Deprecated
    public static World getWorld(String name) {
        return server.getWorld(name);
    }

    @Deprecated
    public static World getWorld(UUID uid) {
        return server.getWorld(uid);
    }

    @Deprecated
    public static MapView getMap(short id) {
        return server.getMap(id);
    }

    @Deprecated
    public static MapView createMap(World world) {
        return server.createMap(world);
    }

    @Deprecated
    public static void reload() {
        server.reload();
    }

    @Deprecated
    public static Logger getLogger() {
        return server.getLogger();
    }

    @Deprecated
    public static PluginCommand getPluginCommand(String name) {
        return server.getPluginCommand(name);
    }

    @Deprecated
    public static void savePlayers() {
        server.savePlayers();
    }

    @Deprecated
    public static boolean dispatchCommand(CommandSender sender, String commandLine) {
        return server.dispatchCommand(sender, commandLine);
    }

    @Deprecated
    public static void configureDbConfig(ServerConfig config) {
        server.configureDbConfig(config);
    }

    @Deprecated
    public static boolean addRecipe(Recipe recipe) {
        return server.addRecipe(recipe);
    }

    @Deprecated
    public static Map<String, String[]> getCommandAliases() {
        return server.getCommandAliases();
    }

    @Deprecated
    public static int getSpawnRadius() {
        return server.getSpawnRadius();
    }

    @Deprecated
    public static void setSpawnRadius(int value) {
        server.setSpawnRadius(value);
    }

    @Deprecated
    public static boolean getOnlineMode() {
        return server.getOnlineMode();
    }

    @Deprecated
    public static boolean getAllowFlight() {
        return server.getAllowFlight();
    }

    @Deprecated
    public static void shutdown() {
        server.shutdown();
    }

    @Deprecated
    public static int broadcast(String message, String permission) {
        return server.broadcast(message, permission);
    }

    @Deprecated
    public static OfflinePlayer getOfflinePlayer(String name) {
        return server.getOfflinePlayer(name);
    }

    @Deprecated
    public static Player getPlayerExact(String name) {
        return server.getPlayerExact(name);
    }

    @Deprecated
    public static Set<String> getIPBans() {
        return server.getIPBans();
    }

    @Deprecated
    public static void banIP(String address) {
        server.banIP(address);
    }

    @Deprecated
    public static void unbanIP(String address) {
        server.unbanIP(address);
    }
    
    @Deprecated
    public static Set<OfflinePlayer> getBannedPlayers() {
        return server.getBannedPlayers();
    }

    @Deprecated
    public static void setWhitelist(boolean value) {
        server.setWhitelist(value);
    }

    @Deprecated
    public static Set<OfflinePlayer> getWhitelistedPlayers() {
        return server.getWhitelistedPlayers();
    }

    @Deprecated
    public static void reloadWhitelist() {
        server.reloadWhitelist();
    }
}
