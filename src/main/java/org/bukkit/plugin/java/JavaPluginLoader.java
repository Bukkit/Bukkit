package org.bukkit.plugin.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.*;
import org.bukkit.event.vehicle.*;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldEvent;
import org.bukkit.event.world.WorldListener;
import org.bukkit.plugin.*;

/**
 * Represents a Java plugin loader, allowing plugins in the form of .jar
 */
public final class JavaPluginLoader implements PluginLoader {
    private static final Logger log = Logger.getLogger(JavaPluginLoader.class.getName());
    private final Server server;
    private final File pluginFolder;
    private final List<File> systemPlugins;
    private final ArrayList<PluginClassLoader> pluginClassLoaders = new ArrayList<PluginClassLoader>();

    public JavaPluginLoader(Server server, File pluginFolder, List<File> systemPlugins) {
        this.server = server;
        this.pluginFolder = pluginFolder;
        this.systemPlugins = systemPlugins;
    }

    /**
     * {@inheritDoc}
     */
    public Plugin getContainingPlugin() {
        return null;
    }

    /**
     * Get a list of ClassLoaders in use.
     *
     * This allows PluginClassLoader to access neighbouring plugins'
     * ClassLoaders, and thus their classes.
     *
     * @return A list of ClassLoaders.
     */
    public List<PluginClassLoader> getClassLoaders() {
        return pluginClassLoaders;
    }

    /**
     * {@inheritDoc}
     */
    public EventExecutor createExecutor( Event.Type type, Listener listener ) {
        // TODO: remove multiple Listener type and hence casts
        switch (type) {
        // Player Events
        case PLAYER_JOIN:
            return new EventExecutor() {
                public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerJoin( (PlayerEvent)event );
                }
            };
        case PLAYER_QUIT:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerQuit( (PlayerEvent)event );
                }
            };
        case PLAYER_RESPAWN:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerRespawn( (PlayerRespawnEvent)event );
                }
            };
        case PLAYER_KICK:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerKick( (PlayerKickEvent)event );
                }
            };
        case PLAYER_COMMAND:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerCommand( (PlayerChatEvent)event );
                }
            };
        case PLAYER_COMMAND_PREPROCESS:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerCommandPreprocess( (PlayerChatEvent)event );
                }
            };
        case PLAYER_CHAT:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerChat( (PlayerChatEvent)event );
                }
            };
        case PLAYER_MOVE:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerMove( (PlayerMoveEvent)event );
                }
            };
        case PLAYER_TELEPORT:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerTeleport( (PlayerMoveEvent)event );
                }
            };
        case PLAYER_ITEM:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerItem( (PlayerItemEvent)event );
                }
            };
        case PLAYER_LOGIN:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerLogin( (PlayerLoginEvent)event );
                }
            };
        case PLAYER_EGG_THROW:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerEggThrow( (PlayerEggThrowEvent)event );
                }
            };
        case PLAYER_ANIMATION:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerAnimation( (PlayerAnimationEvent)event );
                }
            };
        case INVENTORY_OPEN:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onInventoryOpen( (PlayerInventoryEvent)event );
                }
            };
        case PLAYER_ITEM_HELD:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onItemHeldChange( (PlayerItemHeldEvent)event );
                }
            };
        case PLAYER_DROP_ITEM:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerDropItem( (PlayerDropItemEvent)event );
                }
            };
        case PLAYER_PICKUP_ITEM:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerPickupItem( (PlayerPickupItemEvent)event );
                }
            };
        case PLAYER_TOGGLE_SNEAK:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((PlayerListener)listener).onPlayerToggleSneak( (PlayerToggleSneakEvent)event );
                }
            };

        // Block Events
        case BLOCK_PHYSICS:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((BlockListener)listener).onBlockPhysics( (BlockPhysicsEvent)event );
                }
            };
        case BLOCK_CANBUILD:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((BlockListener)listener).onBlockCanBuild( (BlockCanBuildEvent)event );
                }
            };
        case BLOCK_RIGHTCLICKED:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((BlockListener)listener).onBlockRightClick( (BlockRightClickEvent)event );
                }
            };
        case BLOCK_PLACED:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((BlockListener)listener).onBlockPlace( (BlockPlaceEvent)event );
                }
            };
        case BLOCK_DAMAGED:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((BlockListener)listener).onBlockDamage( (BlockDamageEvent)event );
                }
            };
        case BLOCK_INTERACT:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((BlockListener)listener).onBlockInteract( (BlockInteractEvent)event );
                }
            };
        case BLOCK_FLOW:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((BlockListener)listener).onBlockFlow( (BlockFromToEvent)event );
                }
            };
        case LEAVES_DECAY:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((BlockListener)listener).onLeavesDecay( (LeavesDecayEvent)event );
                }
            };
        case SIGN_CHANGE:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((BlockListener)listener).onSignChange( (SignChangeEvent)event );
                }
            };
        case BLOCK_IGNITE:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((BlockListener)listener).onBlockIgnite( (BlockIgniteEvent)event );
                }
            };
        case REDSTONE_CHANGE:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((BlockListener)listener).onBlockRedstoneChange( (BlockRedstoneEvent)event );
                }
            };
        case BLOCK_BURN:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((BlockListener)listener).onBlockBurn( (BlockBurnEvent)event );
                }
            };
        case BLOCK_BREAK:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((BlockListener)listener).onBlockBreak( (BlockBreakEvent)event );
                }
            };

        // Server Events
        case PLUGIN_ENABLE:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((ServerListener)listener).onPluginEnabled( (PluginEvent)event );
                }
            };
        case PLUGIN_DISABLE:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((ServerListener)listener).onPluginDisabled( (PluginEvent)event );
                }
            };

        case SERVER_COMMAND:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((ServerListener)listener).onServerCommand( (ServerCommandEvent)event );
                }
            };

        // World Events
        case CHUNK_LOADED:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((WorldListener)listener).onChunkLoaded( (ChunkLoadEvent)event );
                }
            };
        case CHUNK_UNLOADED:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((WorldListener)listener).onChunkUnloaded( (ChunkUnloadEvent)event );
                }
            };
        case WORLD_SAVED:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((WorldListener)listener).onWorldSaved( (WorldEvent)event );
                }
            };
        case WORLD_LOADED:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((WorldListener)listener).onWorldLoaded( (WorldEvent)event );
                }
            };

        // Entity Events
        case ENTITY_DAMAGED:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((EntityListener)listener).onEntityDamage( (EntityDamageEvent)event );
                }
            };
        case ENTITY_DEATH:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((EntityListener)listener).onEntityDeath( (EntityDeathEvent)event );
                }
            };
        case ENTITY_COMBUST:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((EntityListener)listener).onEntityCombust( (EntityCombustEvent)event );
                }
            };
        case ENTITY_EXPLODE:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((EntityListener)listener).onEntityExplode( (EntityExplodeEvent)event );
                }
            };
        case EXPLOSION_PRIMED:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((EntityListener)listener).onExplosionPrimed( (ExplosionPrimedEvent)event );
                }
            };
        case ENTITY_TARGET:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((EntityListener)listener).onEntityTarget( (EntityTargetEvent)event );
                }
            };
        case CREATURE_SPAWN:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((EntityListener)listener).onCreatureSpawn( (CreatureSpawnEvent)event );
                }
            };

        // Vehicle Events
        case VEHICLE_CREATE:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((VehicleListener)listener).onVehicleCreate( (VehicleCreateEvent)event );
                }
            };
        case VEHICLE_DAMAGE:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((VehicleListener)listener).onVehicleDamage( (VehicleDamageEvent)event );
                }
            };
        case VEHICLE_COLLISION_BLOCK:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((VehicleListener)listener).onVehicleBlockCollision( (VehicleBlockCollisionEvent)event );
                }
            };
        case VEHICLE_COLLISION_ENTITY:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((VehicleListener)listener).onVehicleEntityCollision( (VehicleEntityCollisionEvent)event );
                }
            };
        case VEHICLE_ENTER:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((VehicleListener)listener).onVehicleEnter( (VehicleEnterEvent)event );
                }
            };
        case VEHICLE_EXIT:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((VehicleListener)listener).onVehicleExit( (VehicleExitEvent)event );
                }
            };
        case VEHICLE_MOVE:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((VehicleListener)listener).onVehicleMove( (VehicleMoveEvent)event );
                }
            };
        case VEHICLE_UPDATE:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((VehicleListener)listener).onVehicleUpdate((VehicleEvent)event);
                }
            };

        // Custom Events
        case CUSTOM_EVENT:
            return new EventExecutor() { public void execute( Listener listener, Event event ) {
                    ((CustomEventListener)listener).onCustomEvent( event );
                }
            };
        }

        throw new IllegalArgumentException( "Event " + type + " is not supported" );
    }

    /**
     * {@inheritDoc}
     */
    public void discoverPlugins() {
        PluginManager manager = server.getPluginManager();
        for (File file : pluginFolder.listFiles()) {
            if (!file.getName().endsWith(".jar")) {
                continue;
            }
            try {
                PluginDescription description = readDescription(file);
                manager.register(description);
            } catch (InvalidDescriptionException ex) {
                log.log(Level.SEVERE, "Could not load " + file.getPath() + " in " + pluginFolder.getPath() + ": " + ex.getMessage(), ex);
            }
        }

        for (File file : systemPlugins) {
            try {
                PluginDescription description = readSystemPluginDescription(file);
                manager.register(description);
            } catch (InvalidDescriptionException ex) {
                log.log(Level.SEVERE, "Could not load " + file.getPath() + " in " + pluginFolder.getPath() + ": " + ex.getMessage(), ex);
            }
        }
    }

    /**
     * Reads the description for the plugin in the specified JAR-file
     *
     * @param file The JAR-file containing the plugin
     * @return A PluginDescription instance
     * @throws InvalidDescriptionException Thrown when the metadata was not understood
     */
    private PluginDescription readDescription(File file) throws InvalidDescriptionException {
        JavaPluginDescription result = null;

        if (!file.exists()) {
            throw new InvalidDescriptionException(new FileNotFoundException(String.format("%s does not exist", file.getPath())));
        }
        try {
            JarFile jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("plugin.yml");
            if (entry == null) {
                throw new InvalidDescriptionException(new FileNotFoundException("Jar does not contain plugin.yml"));
            }

            InputStream stream = jar.getInputStream(entry);
            result = new JavaPluginDescription(this, file, stream, false);

            stream.close();
            jar.close();
        } catch (IOException ex) {
            throw new InvalidDescriptionException(ex);
        }
        return result;
    }

    /**
     * Similar to readDescription, but for system plugins.
     *
     * This builds a PluginDescription for a system plugin. Because a system
     * plugin does not have a containing JAR file, the file points directly
     * to the YAML description file.
     *
     * @param file The YAML description file
     * @return A filled PluginDescription object
     * @throws InvalidDescriptionException Thrown when the metadata was not understood
     */
    private PluginDescription readSystemPluginDescription(File file) throws InvalidDescriptionException {
        if (!file.exists()) {
            throw new InvalidDescriptionException(new FileNotFoundException(String.format("%s does not exist", file.getPath())));
        }
        JavaPluginDescription result = null;
        try {
            InputStream stream = new FileInputStream(file);
            result = new JavaPluginDescription(this, file, stream, true);
            stream.close();
        } catch (IOException ex) {
            throw new InvalidDescriptionException(ex);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Plugin enablePlugin(PluginDescription abstractDescription) throws InvalidPluginException {
        JavaPluginDescription description = (JavaPluginDescription)abstractDescription;
        JavaPlugin plugin = null;

        PluginClassLoader cloader = description.getClassLoader();
        pluginClassLoaders.add(cloader);
        try {
            Class<?> jarClass = Class.forName(description.getMain(), true, cloader);
            Class<? extends JavaPlugin> pluginClass = jarClass.asSubclass(JavaPlugin.class);

            try {
                Constructor<? extends JavaPlugin> constructor = pluginClass.getConstructor(Server.class, JavaPluginDescription.class);
                plugin = constructor.newInstance(server, description);
            } catch (NoSuchMethodException ex) {
                Constructor<? extends JavaPlugin> constructor = pluginClass.getConstructor();
                plugin = constructor.newInstance();
            }

            plugin.initialize(server, description);
        } catch (Throwable ex) {
            pluginClassLoaders.remove(cloader);
            throw new InvalidPluginException(ex);
        }

        List<Command> pluginCommands = description.buildCommands(plugin);
        if (!pluginCommands.isEmpty()) {
            final CommandMap commandMap = server.getPluginManager().getCommandMap();
            commandMap.registerAll(plugin.getDescription().getName(), pluginCommands);
        }

        plugin.onEnable();
        return plugin;
    }

    /**
     * {@inheritDoc}
     */
    public void disablePlugin(Plugin plugin) {
        if (!(plugin instanceof JavaPlugin)) {
            throw new IllegalArgumentException("Plugin is not associated with this PluginLoader");
        }

        JavaPlugin jPlugin = (JavaPlugin)plugin;
        JavaPluginDescription description = (JavaPluginDescription)plugin.getDescription();
        pluginClassLoaders.remove(description.getClassLoader());
        description.clearClassLoader();

        jPlugin.onDisable();
    }
}
