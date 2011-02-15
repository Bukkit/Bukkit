package org.bukkit.plugin.java;

import java.util.ArrayList;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockInteractEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ExplosionPrimedEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInventoryEvent;
import org.bukkit.event.player.PlayerItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.PluginEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.vehicle.VehicleEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleListener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldEvent;
import org.bukkit.event.world.WorldListener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescription;

/**
 * Represents a Java plugin
 */
public abstract class JavaPlugin implements Plugin {
    private boolean initialized = false;
    private Server server = null;
    private JavaPluginDescription description = null;

    /**
     * Constructs a new Java plugin instance
     *
     * A plugin typically does not much more than calling super here.
     *
     * @param server Server instance that is running this plugin
     * @param description Description containing metadata on this plugin
     */
    public JavaPlugin(Server server, JavaPluginDescription description) {
        initialize(server, description);
        
        server.getLogger().warning("Using the stupidly long constructor " + description.getMain() + "(PluginLoader, Server, PluginDescriptionFile, File, File, ClassLoader) is no longer recommended. Go nag the plugin author of " + description.getName() + " to remove it! (Nothing is broken, we just like to keep code clean.)");

        ArrayList<String> authors = description.getAuthors();
        if (authors.size() > 0) {
            server.getLogger().info("Hint! It's probably someone called '" + authors.get(0) + "'");
        }
    }

    public JavaPlugin() {
    }

    /**
     * Returns the Server instance currently running this plugin
     *
     * @return Server running this plugin
     */
    public final Server getServer() {
        return server;
    }

    /**
     * {@inheritDoc}
     */
    public PluginDescription getDescription() {
        return description;
    }

    /**
     * Called when this plugin is enabled
     *
     * This is the place to do initialization, such as installing listeners.
     */
    public void onEnable() {
        // default implementation:  do nothing!
    }

    /**
     * Called when this plugin is disabled
     *
     * The PluginManager sees to cleaning up commands, listeners, loaders and
     * scheduled tasks, but any other resources held by the plugin should be
     * cleaned up here.
     */
    public void onDisable() {
        // default implementation:  do nothing!
    }

    /**
     * Called when a command registered by this plugin is received.
     *
     * @param sender The sender who issued the command
     * @param command The command object that was triggered
     * @param commandLabel The command literally as entered (sans the '/' prefix)
     * @param args A list of arguments (not including the command itself)
     * @return Whether the command was executed successfully. Returning false will display usage.
     */
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        return false; // default implementation:  do nothing!
    }

    /**
     * Initializes this plugin with the given variables.
     *
     * This method should never be called manually.
     *
     * @param loader PluginLoader that is responsible for this plugin
     * @param server Server instance that is running this plugin
     * @param description PluginDescriptionFile containing metadata on this plugin
     * @param dataFolder Folder containing the plugin's data
     * @param file File containing this plugin
     * @param classLoader ClassLoader which holds this plugin
     */
    protected final void initialize(Server server, JavaPluginDescription description) {
        if (initialized) {
            throw new UnsupportedOperationException("Cannot reinitialize a plugin");
        }

        this.initialized = true;
        this.server = server;
        this.description = description;
    }

    /**
     * Gets the initialization status of this plugin
     *
     * @return true if this plugin is initialized, otherwise false
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * Register an event for the given listener.
     */
    public final void registerEvent(Event.Type type, Priority priority, Listener listener) {
        server.getPluginManager().registerEvent(this, type, priority, createExecutor(type, listener));
    }

    /**
     * Executor base class for Java plugins.
     *
     * @param <T> The Listener type.
     */
    private static abstract class JavaEventExecutor<T> implements EventExecutor {
        protected T listener;

        @SuppressWarnings("unchecked")
        public JavaEventExecutor(Listener listener) {
            this.listener = (T)listener;
        }

        public abstract void execute(Event event);
    }

    /**
     * Creates an executor for the given type and listener.
     */
    private final EventExecutor createExecutor(Event.Type type, Listener listener) {
        // TODO: remove multiple Listener type and hence casts
        switch (type) {
        // Player Events
        case PLAYER_JOIN:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerJoin((PlayerEvent)event);
                }
            };
        case PLAYER_QUIT:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerQuit((PlayerEvent)event);
                }
            };
        case PLAYER_RESPAWN:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerRespawn((PlayerRespawnEvent)event);
                }
            };
        case PLAYER_KICK:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerKick((PlayerKickEvent)event);
                }
            };
        case PLAYER_COMMAND:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerCommand((PlayerChatEvent)event);
                }
            };
        case PLAYER_COMMAND_PREPROCESS:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerCommandPreprocess((PlayerChatEvent)event);
                }
            };
        case PLAYER_CHAT:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerChat((PlayerChatEvent)event);
                }
            };
        case PLAYER_MOVE:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerMove((PlayerMoveEvent)event);
                }
            };
        case PLAYER_TELEPORT:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerTeleport((PlayerMoveEvent)event);
                }
            };
        case PLAYER_ITEM:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerItem((PlayerItemEvent)event);
                }
            };
        case PLAYER_LOGIN:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerLogin((PlayerLoginEvent)event);
                }
            };
        case PLAYER_EGG_THROW:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerEggThrow((PlayerEggThrowEvent)event);
                }
            };
        case PLAYER_ANIMATION:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerAnimation((PlayerAnimationEvent)event);
                }
            };
        case INVENTORY_OPEN:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onInventoryOpen((PlayerInventoryEvent)event);
                }
            };
        case PLAYER_ITEM_HELD:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onItemHeldChange((PlayerItemHeldEvent)event);
                }
            };
        case PLAYER_DROP_ITEM:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerDropItem((PlayerDropItemEvent)event);
                }
            };
        case PLAYER_PICKUP_ITEM:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerPickupItem((PlayerPickupItemEvent)event);
                }
            };
        case PLAYER_TOGGLE_SNEAK:
            return new JavaEventExecutor<PlayerListener>(listener) {
                public void execute(Event event) {
                    listener.onPlayerToggleSneak((PlayerToggleSneakEvent)event);
                }
            };

        // Block Events
        case BLOCK_PHYSICS:
            return new JavaEventExecutor<BlockListener>(listener) {
                public void execute(Event event) {
                    listener.onBlockPhysics((BlockPhysicsEvent)event);
                }
            };
        case BLOCK_CANBUILD:
            return new JavaEventExecutor<BlockListener>(listener) {
                public void execute(Event event) {
                    listener.onBlockCanBuild((BlockCanBuildEvent)event);
                }
            };
        case BLOCK_RIGHTCLICKED:
            return new JavaEventExecutor<BlockListener>(listener) {
                public void execute(Event event) {
                    listener.onBlockRightClick((BlockRightClickEvent)event);
                }
            };
        case BLOCK_PLACED:
            return new JavaEventExecutor<BlockListener>(listener) {
                public void execute(Event event) {
                    listener.onBlockPlace((BlockPlaceEvent)event);
                }
            };
        case BLOCK_DAMAGED:
            return new JavaEventExecutor<BlockListener>(listener) {
                public void execute(Event event) {
                    listener.onBlockDamage((BlockDamageEvent)event);
                }
            };
        case BLOCK_INTERACT:
            return new JavaEventExecutor<BlockListener>(listener) {
                public void execute(Event event) {
                    listener.onBlockInteract((BlockInteractEvent)event);
                }
            };
        case BLOCK_FLOW:
            return new JavaEventExecutor<BlockListener>(listener) {
                public void execute(Event event) {
                    listener.onBlockFlow((BlockFromToEvent)event);
                }
            };
        case LEAVES_DECAY:
            return new JavaEventExecutor<BlockListener>(listener) {
                public void execute(Event event) {
                    listener.onLeavesDecay((LeavesDecayEvent)event);
                }
            };
        case SIGN_CHANGE:
            return new JavaEventExecutor<BlockListener>(listener) {
                public void execute(Event event) {
                    listener.onSignChange((SignChangeEvent)event);
                }
            };
        case BLOCK_IGNITE:
            return new JavaEventExecutor<BlockListener>(listener) {
                public void execute(Event event) {
                    listener.onBlockIgnite((BlockIgniteEvent)event);
                }
            };
        case REDSTONE_CHANGE:
            return new JavaEventExecutor<BlockListener>(listener) {
                public void execute(Event event) {
                    listener.onBlockRedstoneChange((BlockRedstoneEvent)event);
                }
            };
        case BLOCK_BURN:
            return new JavaEventExecutor<BlockListener>(listener) {
                public void execute(Event event) {
                    listener.onBlockBurn((BlockBurnEvent)event);
                }
            };
        case BLOCK_BREAK:
            return new JavaEventExecutor<BlockListener>(listener) {
                public void execute(Event event) {
                    listener.onBlockBreak((BlockBreakEvent)event);
                }
            };

        // Server Events
        case PLUGIN_ENABLE:
            return new JavaEventExecutor<ServerListener>(listener) {
                public void execute(Event event) {
                    listener.onPluginEnabled((PluginEvent)event);
                }
            };
        case PLUGIN_DISABLE:
            return new JavaEventExecutor<ServerListener>(listener) {
                public void execute(Event event) {
                    listener.onPluginDisabled((PluginEvent)event);
                }
            };

        case SERVER_COMMAND:
            return new JavaEventExecutor<ServerListener>(listener) {
                public void execute(Event event) {
                    listener.onServerCommand((ServerCommandEvent)event);
                }
            };

        // World Events
        case CHUNK_LOADED:
            return new JavaEventExecutor<WorldListener>(listener) {
                public void execute(Event event) {
                    listener.onChunkLoaded((ChunkLoadEvent)event);
                }
            };
        case CHUNK_UNLOADED:
            return new JavaEventExecutor<WorldListener>(listener) {
                public void execute(Event event) {
                    listener.onChunkUnloaded((ChunkUnloadEvent)event);
                }
            };
        case WORLD_SAVED:
            return new JavaEventExecutor<WorldListener>(listener) {
                public void execute(Event event) {
                    listener.onWorldSaved((WorldEvent)event);
                }
            };
        case WORLD_LOADED:
            return new JavaEventExecutor<WorldListener>(listener) {
                public void execute(Event event) {
                    listener.onWorldLoaded((WorldEvent)event);
                }
            };

        // Entity Events
        case ENTITY_DAMAGED:
            return new JavaEventExecutor<EntityListener>(listener) {
                public void execute(Event event) {
                    listener.onEntityDamage((EntityDamageEvent)event);
                }
            };
        case ENTITY_DEATH:
            return new JavaEventExecutor<EntityListener>(listener) {
                public void execute(Event event) {
                    listener.onEntityDeath((EntityDeathEvent)event);
                }
            };
        case ENTITY_COMBUST:
            return new JavaEventExecutor<EntityListener>(listener) {
                public void execute(Event event) {
                    listener.onEntityCombust((EntityCombustEvent)event);
                }
            };
        case ENTITY_EXPLODE:
            return new JavaEventExecutor<EntityListener>(listener) {
                public void execute(Event event) {
                    listener.onEntityExplode((EntityExplodeEvent)event);
                }
            };
        case EXPLOSION_PRIMED:
            return new JavaEventExecutor<EntityListener>(listener) {
                public void execute(Event event) {
                    listener.onExplosionPrimed((ExplosionPrimedEvent)event);
                }
            };
        case ENTITY_TARGET:
            return new JavaEventExecutor<EntityListener>(listener) {
                public void execute(Event event) {
                    listener.onEntityTarget((EntityTargetEvent)event);
                }
            };
        case CREATURE_SPAWN:
            return new JavaEventExecutor<EntityListener>(listener) {
                public void execute(Event event) {
                    listener.onCreatureSpawn((CreatureSpawnEvent)event);
                }
            };

        // Vehicle Events
        case VEHICLE_CREATE:
            return new JavaEventExecutor<VehicleListener>(listener) {
                public void execute(Event event) {
                    listener.onVehicleCreate((VehicleCreateEvent)event);
                }
            };
        case VEHICLE_DAMAGE:
            return new JavaEventExecutor<VehicleListener>(listener) {
                public void execute(Event event) {
                    listener.onVehicleDamage((VehicleDamageEvent)event);
                }
            };
        case VEHICLE_COLLISION_BLOCK:
            return new JavaEventExecutor<VehicleListener>(listener) {
                public void execute(Event event) {
                    listener.onVehicleBlockCollision((VehicleBlockCollisionEvent)event);
                }
            };
        case VEHICLE_COLLISION_ENTITY:
            return new JavaEventExecutor<VehicleListener>(listener) {
                public void execute(Event event) {
                    listener.onVehicleEntityCollision((VehicleEntityCollisionEvent)event);
                }
            };
        case VEHICLE_ENTER:
            return new JavaEventExecutor<VehicleListener>(listener) {
                public void execute(Event event) {
                    listener.onVehicleEnter((VehicleEnterEvent)event);
                }
            };
        case VEHICLE_EXIT:
            return new JavaEventExecutor<VehicleListener>(listener) {
                public void execute(Event event) {
                    listener.onVehicleExit((VehicleExitEvent)event);
                }
            };
        case VEHICLE_MOVE:
            return new JavaEventExecutor<VehicleListener>(listener) {
                public void execute(Event event) {
                    listener.onVehicleMove((VehicleMoveEvent)event);
                }
            };
        case VEHICLE_UPDATE:
            return new JavaEventExecutor<VehicleListener>(listener) {
                public void execute(Event event) {
                    listener.onVehicleUpdate((VehicleEvent)event);
                }
            };

        // Custom Events
        case CUSTOM_EVENT:
            return new JavaEventExecutor<CustomEventListener>(listener) {
                public void execute(Event event) {
                    listener.onCustomEvent(event);
                }
            };
        }

        throw new IllegalArgumentException("Event " + type + " is not supported");
    }
}
