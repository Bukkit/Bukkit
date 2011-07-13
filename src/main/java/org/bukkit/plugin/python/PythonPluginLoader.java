package org.bukkit.plugin.python;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;

import org.bukkit.Server;
import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.block.SnowFormEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.PigZapEvent;
import org.bukkit.event.painting.PaintingBreakEvent;
import org.bukkit.event.painting.PaintingPlaceEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerInventoryEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleListener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.weather.WeatherListener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.SpawnChangeEvent;
import org.bukkit.event.world.WorldListener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.UnknownSoftDependencyException;
import org.bukkit.plugin.java.PluginClassLoader;
import org.python.core.PyObject;
import org.python.core.PyDictionary;
import org.python.core.PyFile;
import org.python.core.PyString;
import org.python.core.util.FileUtil;
import org.python.util.PythonInterpreter;
import org.yaml.snakeyaml.error.YAMLException;

public class PythonPluginLoader implements PluginLoader
{
	private final Server server;
    private final Pattern[] fileFilters = new Pattern[] {
        Pattern.compile("\\.pyp$"),
    };
    
    private final Map<String, Class<?>> classes = new HashMap<String, Class<?>>();
    private final HashMap<String, PluginClassLoader> loaders = new HashMap<String, PluginClassLoader>();
    
    static PythonInterpreter interpreter;
    
    public PythonPluginLoader(Server server)
    {
    	this.server = server;
    }
	
    public Plugin loadPlugin(File file) throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException {
        return loadPlugin(file, false);
    }

    public Plugin loadPlugin(File file, boolean ignoreSoftDependencies) throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException {
        PythonPlugin result = null;
        PluginDescriptionFile description = null;
		ZipFile zfile = null;
		try {
			zfile = new ZipFile(file);
		} catch(IOException e) {
			throw new InvalidPluginException(e);
		}

        if (!file.exists()) {
            throw new InvalidPluginException(new FileNotFoundException(String.format("%s does not exist", file.getPath())));
        }
        try {
			ZipEntry pdfEntry = zfile.getEntry("plugin.yaml");
			if(pdfEntry == null)
				throw new InvalidPluginException(new NullPointerException("Missing plugin.yml"));
            InputStream pdfstream = zfile.getInputStream(pdfEntry);
            description = new PluginDescriptionFile(pdfstream);
            pdfstream.close();
        } catch (IOException ex) {
            throw new InvalidPluginException(ex);
        } catch (YAMLException ex) {
            throw new InvalidPluginException(ex);
        }

        File dataFolder = new File(file.getParentFile(), description.getName());
        //File oldDataFolder = getDataFolder(file);

        if (dataFolder.exists() && !dataFolder.isDirectory()) {
            throw new InvalidPluginException(new Exception(String.format(
                "Projected datafolder: '%s' for %s (%s) exists and is not a directory",
                dataFolder,
                description.getName(),
                file
            )));
        }

        ArrayList<String> depend;

        try {
            depend = (ArrayList) description.getDepend();
            if (depend == null) {
                depend = new ArrayList<String>();
            }
        } catch (ClassCastException ex) {
            throw new InvalidPluginException(ex);
        }

        
        for (String pluginName : depend) {
            if (loaders == null) {
                throw new UnknownDependencyException(pluginName);
            }
            URLClassLoader current = loaders.get(pluginName);

            if (current == null) {
                throw new UnknownDependencyException(pluginName);
            }
        }

        if (!ignoreSoftDependencies) {
            ArrayList<String> softDepend;

            try {
                softDepend = (ArrayList) description.getSoftDepend();
                if (softDepend == null) {
                    softDepend = new ArrayList<String>();
                }
            } catch (ClassCastException ex) {
                throw new InvalidPluginException(ex);
            }

            for (String pluginName : softDepend) {
                if (loaders == null) {
                    throw new UnknownSoftDependencyException(pluginName);
                }
                URLClassLoader current = loaders.get(pluginName);

                if (current == null) {
                    throw new UnknownSoftDependencyException(pluginName);
                }
            }
        }
        
        try {
        	if(interpreter == null)
        	{
        		interpreter = new PythonInterpreter();
        	}
			interpreter.exec("import sys");
			interpreter.exec("sys.path.append(\""+file.getAbsolutePath()+"\")");
			interpreter.execfile(zfile.getInputStream(zfile.getEntry("plugin.py")));
			PyObject pyClass = interpreter.get(description.getMain());
			result = (PythonPlugin)pyClass.__call__().__tojava__(PythonPlugin.class);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new InvalidPluginException(e);
		}
		
		result.initialize(this, server, description, dataFolder, file, ClassLoader.getSystemClassLoader());
		
        return (Plugin) result;
    }

    private File getDataFolder(File file) {
        File dataFolder = null;

        String filename = file.getName();
        int index = file.getName().lastIndexOf(".");

        if (index != -1) {
            String name = filename.substring(0, index);

            dataFolder = new File(file.getParentFile(), name);
        } else {
            // This is if there is no extension, which should not happen
            // Using _ to prevent name collision

            dataFolder = new File(file.getParentFile(), filename + "_");
        }

        return dataFolder;
    }

    public Pattern[] getPluginFileFilters() {
        return fileFilters;
    }
    
    public static Object getPythonObject(String pythonClass, Class<?> javaClass)
    {
    	PyObject pyClass = interpreter.get(pythonClass);
		return pyClass.__call__().__tojava__(javaClass);
    }
    
    public static PyObject getPythonVariable(String name)
    {
    	return interpreter.get(name);
    }

    /*
    public Class<?> getClassByName(final String name) {
        Class<?> cachedClass = classes.get(name);

        if (cachedClass != null) {
            return cachedClass;
        } else {
            for (String current : loaders.keySet()) {
                PluginClassLoader loader = loaders.get(current);

                try {
                    cachedClass = loader.findClass(name, false);
                } catch (ClassNotFoundException cnfe) {}
                if (cachedClass != null) {
                    return cachedClass;
                }
            }
        }
        return null;
    }

    public void setClass(final String name, final Class<?> clazz) {
        if (!classes.containsKey(name)) {
            classes.put(name, clazz);
        }
    }*/

    public EventExecutor createExecutor(Event.Type type, Listener listener) {
        // TODO: remove multiple Listener type and hence casts

        switch (type) {
        // Player Events

        case PLAYER_JOIN:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerJoin((PlayerJoinEvent) event);
                }
            };

        case PLAYER_QUIT:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerQuit((PlayerQuitEvent) event);
                }
            };

        case PLAYER_RESPAWN:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerRespawn((PlayerRespawnEvent) event);
                }
            };

        case PLAYER_KICK:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerKick((PlayerKickEvent) event);
                }
            };

        case PLAYER_COMMAND_PREPROCESS:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerCommandPreprocess((PlayerCommandPreprocessEvent) event);
                }
            };

        case PLAYER_CHAT:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerChat((PlayerChatEvent) event);
                }
            };

        case PLAYER_MOVE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerMove((PlayerMoveEvent) event);
                }
            };

        case PLAYER_TELEPORT:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerTeleport((PlayerTeleportEvent) event);
                }
            };

        case PLAYER_INTERACT:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerInteract((PlayerInteractEvent) event);
                }
            };

        case PLAYER_INTERACT_ENTITY:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerInteractEntity((PlayerInteractEntityEvent) event);
                }
            };

        case PLAYER_LOGIN:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerLogin((PlayerLoginEvent) event);
                }
            };

        case PLAYER_PRELOGIN:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerPreLogin((PlayerPreLoginEvent) event);
                }
            };

        case PLAYER_EGG_THROW:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerEggThrow((PlayerEggThrowEvent) event);
                }
            };

        case PLAYER_ANIMATION:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerAnimation((PlayerAnimationEvent) event);
                }
            };

        case INVENTORY_OPEN:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onInventoryOpen((PlayerInventoryEvent) event);
                }
            };

        case PLAYER_ITEM_HELD:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onItemHeldChange((PlayerItemHeldEvent) event);
                }
            };

        case PLAYER_DROP_ITEM:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerDropItem((PlayerDropItemEvent) event);
                }
            };

        case PLAYER_PICKUP_ITEM:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerPickupItem((PlayerPickupItemEvent) event);
                }
            };

        case PLAYER_TOGGLE_SNEAK:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerToggleSneak((PlayerToggleSneakEvent) event);
                }
            };

        case PLAYER_BUCKET_EMPTY:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerBucketEmpty((PlayerBucketEmptyEvent) event);
                }
            };

        case PLAYER_BUCKET_FILL:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerBucketFill((PlayerBucketFillEvent) event);
                }
            };

        case PLAYER_BED_ENTER:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerBedEnter((PlayerBedEnterEvent) event);
                }
            };

        case PLAYER_BED_LEAVE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((PlayerListener) listener).onPlayerBedLeave((PlayerBedLeaveEvent) event);
                }
            };

        // Block Events
        case BLOCK_PHYSICS:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((BlockListener) listener).onBlockPhysics((BlockPhysicsEvent) event);
                }
            };

        case BLOCK_CANBUILD:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((BlockListener) listener).onBlockCanBuild((BlockCanBuildEvent) event);
                }
            };

        case BLOCK_PLACE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((BlockListener) listener).onBlockPlace((BlockPlaceEvent) event);
                }
            };

        case BLOCK_DAMAGE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((BlockListener) listener).onBlockDamage((BlockDamageEvent) event);
                }
            };

        case BLOCK_FROMTO:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((BlockListener) listener).onBlockFromTo((BlockFromToEvent) event);
                }
            };

        case LEAVES_DECAY:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((BlockListener) listener).onLeavesDecay((LeavesDecayEvent) event);
                }
            };

        case SIGN_CHANGE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((BlockListener) listener).onSignChange((SignChangeEvent) event);
                }
            };

        case BLOCK_IGNITE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((BlockListener) listener).onBlockIgnite((BlockIgniteEvent) event);
                }
            };

        case REDSTONE_CHANGE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((BlockListener) listener).onBlockRedstoneChange((BlockRedstoneEvent) event);
                }
            };

        case BLOCK_BURN:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((BlockListener) listener).onBlockBurn((BlockBurnEvent) event);
                }
            };

        case BLOCK_BREAK:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((BlockListener) listener).onBlockBreak((BlockBreakEvent) event);
                }
            };

        case SNOW_FORM:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((BlockListener) listener).onSnowForm((SnowFormEvent) event);
                }
            };

        case BLOCK_DISPENSE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((BlockListener) listener).onBlockDispense((BlockDispenseEvent) event);
                }
            };

        // Server Events
        case PLUGIN_ENABLE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((ServerListener) listener).onPluginEnable((PluginEnableEvent) event);
                }
            };

        case PLUGIN_DISABLE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((ServerListener) listener).onPluginDisable((PluginDisableEvent) event);
                }
            };

        case SERVER_COMMAND:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((ServerListener) listener).onServerCommand((ServerCommandEvent) event);
                }
            };

        // World Events
        case CHUNK_LOAD:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((WorldListener) listener).onChunkLoad((ChunkLoadEvent) event);
                }
            };

        case CHUNK_UNLOAD:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((WorldListener) listener).onChunkUnload((ChunkUnloadEvent) event);
                }
            };

        case SPAWN_CHANGE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((WorldListener) listener).onSpawnChange((SpawnChangeEvent) event);
                }
            };

        case WORLD_SAVE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((WorldListener) listener).onWorldSave((WorldSaveEvent) event);
                }
            };

        case WORLD_LOAD:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((WorldListener) listener).onWorldLoad((WorldLoadEvent) event);
                }
            };

        // Painting Events
        case PAINTING_PLACE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((EntityListener) listener).onPaintingPlace((PaintingPlaceEvent) event);
                }
            };

        case PAINTING_BREAK:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((EntityListener) listener).onPaintingBreak((PaintingBreakEvent) event);
                }
            };

        // Entity Events
        case ENTITY_DAMAGE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((EntityListener) listener).onEntityDamage((EntityDamageEvent) event);
                }
            };

        case ENTITY_DEATH:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((EntityListener) listener).onEntityDeath((EntityDeathEvent) event);
                }
            };

        case ENTITY_COMBUST:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((EntityListener) listener).onEntityCombust((EntityCombustEvent) event);
                }
            };

        case ENTITY_EXPLODE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((EntityListener) listener).onEntityExplode((EntityExplodeEvent) event);
                }
            };

        case EXPLOSION_PRIME:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((EntityListener) listener).onExplosionPrime((ExplosionPrimeEvent) event);
                }
            };

        case ENTITY_TARGET:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((EntityListener) listener).onEntityTarget((EntityTargetEvent) event);
                }
            };

        case ENTITY_INTERACT:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((EntityListener) listener).onEntityInteract((EntityInteractEvent) event);
                }
            };

        case CREATURE_SPAWN:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((EntityListener) listener).onCreatureSpawn((CreatureSpawnEvent) event);
                }
            };

        case PIG_ZAP:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((EntityListener) listener).onPigZap((PigZapEvent) event);
                }
            };

        case CREEPER_POWER:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((EntityListener) listener).onCreeperPower((CreeperPowerEvent) event);
                }
            };

        // Vehicle Events
        case VEHICLE_CREATE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((VehicleListener) listener).onVehicleCreate((VehicleCreateEvent) event);
                }
            };

        case VEHICLE_DAMAGE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((VehicleListener) listener).onVehicleDamage((VehicleDamageEvent) event);
                }
            };

        case VEHICLE_DESTROY:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((VehicleListener) listener).onVehicleDestroy((VehicleDestroyEvent) event);
                }
            };

        case VEHICLE_COLLISION_BLOCK:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((VehicleListener) listener).onVehicleBlockCollision((VehicleBlockCollisionEvent) event);
                }
            };

        case VEHICLE_COLLISION_ENTITY:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((VehicleListener) listener).onVehicleEntityCollision((VehicleEntityCollisionEvent) event);
                }
            };

        case VEHICLE_ENTER:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((VehicleListener) listener).onVehicleEnter((VehicleEnterEvent) event);
                }
            };

        case VEHICLE_EXIT:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((VehicleListener) listener).onVehicleExit((VehicleExitEvent) event);
                }
            };

        case VEHICLE_MOVE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((VehicleListener) listener).onVehicleMove((VehicleMoveEvent) event);
                }
            };

        case VEHICLE_UPDATE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((VehicleListener) listener).onVehicleUpdate((VehicleUpdateEvent) event);
                }
            };

        // Weather Events
        case WEATHER_CHANGE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((WeatherListener) listener).onWeatherChange((WeatherChangeEvent) event);
                }
            };

        case THUNDER_CHANGE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((WeatherListener) listener).onThunderChange((ThunderChangeEvent) event);
                }
            };

        case LIGHTNING_STRIKE:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((WeatherListener) listener).onLightningStrike((LightningStrikeEvent) event);
                }
            };

        // Custom Events
        case CUSTOM_EVENT:
            return new EventExecutor() {
                public void execute(Listener listener, Event event) {
                    ((CustomEventListener) listener).onCustomEvent(event);
                }
            };
        }

        throw new IllegalArgumentException("Event " + type + " is not supported");
    }

    public void enablePlugin(final Plugin plugin) {
        if (!(plugin instanceof PythonPlugin)) {
            throw new IllegalArgumentException("Plugin is not associated with this PluginLoader");
        }

        if (!plugin.isEnabled()) {
            PythonPlugin pPlugin = (PythonPlugin) plugin;

//            String pluginName = pPlugin.getDescription().getName();
/*
            if (!loaders.containsKey(pluginName)) {
                loaders.put(pluginName, (PluginClassLoader) jPlugin.getClassLoader());
            }
*/
            try {
                pPlugin.setEnabled(true);
            } catch (Throwable ex) {
                server.getLogger().log(Level.SEVERE, "Error occurred while enabling " + plugin.getDescription().getFullName() + " (Is it up to date?): " + ex.getMessage(), ex);
            }

            // Perhaps abort here, rather than continue going, but as it stands,
            // an abort is not possible the way it's currently written
            server.getPluginManager().callEvent(new PluginEnableEvent(plugin));
        }
    }

    public void disablePlugin(Plugin plugin) {
        if (!(plugin instanceof PythonPlugin)) {
            throw new IllegalArgumentException("Plugin is not associated with this PluginLoader");
        }

        if (plugin.isEnabled()) {
            PythonPlugin pPlugin = (PythonPlugin) plugin;
            ClassLoader cloader = pPlugin.getClassLoader();

            try {
                pPlugin.setEnabled(false);
            } catch (Throwable ex) {
                server.getLogger().log(Level.SEVERE, "Error occurred while disabling " + plugin.getDescription().getFullName() + " (Is it up to date?): " + ex.getMessage(), ex);
            }

            server.getPluginManager().callEvent(new PluginDisableEvent(plugin));
/*
            loaders.remove(jPlugin.getDescription().getName());

            if (cloader instanceof PluginClassLoader) {
                PluginClassLoader loader = (PluginClassLoader) cloader;
                Set<String> names = loader.getClasses();

                for (String name : names) {
                    classes.remove(name);
                }
            }*/
        }
    }
}
