
package org.bukkit.plugin.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import org.bukkit.Server;
import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamagedByBlockEvent;
import org.bukkit.event.entity.EntityDamagedByEntityEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.player.*;
import org.bukkit.event.server.PluginEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.event.vehicle.*;
import org.bukkit.event.world.ChunkLoadedEvent;
import org.bukkit.event.world.ChunkUnloadedEvent;
import org.bukkit.event.world.WorldListener;
import org.bukkit.plugin.*;

/**
 * Represents a Java plugin loader, allowing plugins in the form of .jar
 */
public final class JavaPluginLoader implements PluginLoader {
    private final Server server;
    private final Pattern[] fileFilters = new Pattern[] {
            Pattern.compile("\\.jar$"),
    };

    public JavaPluginLoader(Server instance) {
        server = instance;
    }

    public Plugin loadPlugin(File file) throws InvalidPluginException, InvalidDescriptionException {
        JavaPlugin result = null;
        PluginDescriptionFile description = null;

        if (!file.exists()) {
            throw new InvalidPluginException(new FileNotFoundException(String.format("%s does not exist", file.getPath())));
        }
        try {
            JarFile jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("plugin.yml");

            if (entry == null) {
                throw new InvalidPluginException(new FileNotFoundException("Jar does not contain plugin.yml"));
            }

            InputStream stream = jar.getInputStream(entry);
            description = new PluginDescriptionFile(stream);

            stream.close();
            jar.close();
        } catch (IOException ex) {
            throw new InvalidPluginException(ex);
        }

        try {
            ClassLoader loader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()}, getClass().getClassLoader());
            Class<?> jarClass = Class.forName(description.getMain(), true, loader);
            Class<? extends JavaPlugin> plugin = jarClass.asSubclass(JavaPlugin.class);
            Constructor<? extends JavaPlugin> constructor = plugin.getConstructor(PluginLoader.class, Server.class, PluginDescriptionFile.class, File.class, ClassLoader.class);

            result = constructor.newInstance(this, server, description, file, loader);
        } catch (Throwable ex) {
            throw new InvalidPluginException(ex);
        }

        return (Plugin)result;
    }

    public Pattern[] getPluginFileFilters() {
        return fileFilters;
    }

    public void callEvent(RegisteredListener registration, Event event) {
        Listener listener = registration.getListener();

        if (listener instanceof PlayerListener) {
            PlayerListener trueListener = (PlayerListener)listener;

            switch (event.getType()) {
                case PLAYER_JOIN:
                    trueListener.onPlayerJoin((PlayerEvent)event);
                    break;
                case PLAYER_QUIT:
                    trueListener.onPlayerQuit((PlayerEvent)event);
                    break;
                case PLAYER_COMMAND:
                    trueListener.onPlayerCommand((PlayerChatEvent)event);
                    break;
                case PLAYER_CHAT:
                    trueListener.onPlayerChat((PlayerChatEvent)event);
                    break;
                case PLAYER_MOVE:
                    trueListener.onPlayerMove((PlayerMoveEvent)event);
                    break;
                case PLAYER_TELEPORT:
                    trueListener.onPlayerTeleport((PlayerMoveEvent)event);
                    break;
                case PLAYER_ITEM:
                    trueListener.onPlayerItem((PlayerItemEvent)event);
                    break;
                case PLAYER_LOGIN:
                    trueListener.onPlayerLogin((PlayerLoginEvent)event);
                    break;
            }
        } else if (listener instanceof BlockListener) {
            BlockListener trueListener = (BlockListener)listener;

            switch (event.getType()) {
                case BLOCK_PHYSICS:
                    trueListener.onBlockPhysics((BlockPhysicsEvent)event);
                    break;
                case BLOCK_CANBUILD:
                    trueListener.onBlockCanBuild((BlockCanBuildEvent)event);
                    break;
                case BLOCK_RIGHTCLICKED:
                    trueListener.onBlockRightClicked((BlockRightClickedEvent) event);
                    break;
                case BLOCK_PLACED:
                    trueListener.onBlockPlaced((BlockPlacedEvent)event);
                    break;
                case BLOCK_INTERACT:
                    trueListener.onBlockInteracted((BlockInteractEvent)event);
                    break;
                case BLOCK_FLOW:
                    trueListener.onBlockFlow((BlockFromToEvent)event);
                    break;
                case LEAVES_DECAY:
                    trueListener.onLeavesDecay((LeavesDecayEvent)event);
                    break;
            }
        } else if(listener instanceof ServerListener) {
            ServerListener trueListener = (ServerListener)listener;

            switch (event.getType()) {
                case PLUGIN_ENABLE:
                    trueListener.onPluginEnabled((PluginEvent)event);
                    break;
                case PLUGIN_DISABLE:
                    trueListener.onPluginDisabled((PluginEvent)event);
                    break;
            }
        } else if(listener instanceof WorldListener) {
            WorldListener trueListener = (WorldListener)listener;

            switch (event.getType()) {
                case CHUNK_LOADED:
                    trueListener.onChunkLoaded((ChunkLoadedEvent)event);
                    break;
                case CHUNK_UNLOADED:
                    trueListener.onChunkUnloaded((ChunkUnloadedEvent)event);
                    break;
            }
        } else if(listener instanceof EntityListener) {
            EntityListener trueListener = (EntityListener) listener;
            switch(event.getType())
            {
                case ENTITY_DAMAGEDBY_BLOCK:
                    trueListener.onEntityDamagedByBlock((EntityDamagedByBlockEvent)event);
                    break;
                case ENTITY_DAMAGEDBY_ENTITY:
                    trueListener.onEntityDamagedByEntity((EntityDamagedByEntityEvent)event);
                    break;
                case ENTITY_DEATH:
                    // TODO: ENTITY_DEATH hook
                    break;
            }
        } else if (listener instanceof VehicleListener) {
            VehicleListener trueListener = (VehicleListener)listener;

            switch (event.getType()) {
                case VEHICLE_CREATE:
                    trueListener.onVehicleCreate((VehicleCreateEvent)event);
                    break;
                case VEHICLE_DAMAGE:
                    trueListener.onVehicleDamage((VehicleDamageEvent)event);
                    break;
                case VEHICLE_COLLISION_BLOCK:
                    trueListener.onVehicleBlockCollision((VehicleBlockCollisionEvent)event);
                    break;
                case VEHICLE_COLLISION_ENTITY:
                    trueListener.onVehicleEntityCollision((VehicleEntityCollisionEvent)event);
                    break;
                case VEHICLE_ENTER:
                    trueListener.onVehicleEnter((VehicleEnterEvent)event);
                    break;
                case VEHICLE_EXIT:
                    trueListener.onVehicleExit((VehicleExitEvent)event);
                    break;
                case VEHICLE_MOVE:
                    trueListener.onVehicleMove((VehicleMoveEvent)event);
                    break;
            }
        } else if(listener instanceof CustomEventListener) {
            if(event.getType()==Event.Type.CUSTOM_EVENT) {
                ((CustomEventListener)listener).onCustomEvent(event);
            }
        }
    }

    public void enablePlugin(final Plugin plugin) {
        if (!(plugin instanceof JavaPlugin)) {
            throw new IllegalArgumentException("Plugin is not associated with this PluginLoader");
        }

        if (!plugin.isEnabled()) {
            JavaPlugin jPlugin = (JavaPlugin)plugin;

            server.getPluginManager().callEvent(new PluginEvent(Event.Type.PLUGIN_ENABLE, plugin));
            
            jPlugin.setEnabled(true);
        }
    }

    public void disablePlugin(Plugin plugin) {
        if (!(plugin instanceof JavaPlugin)) {
            throw new IllegalArgumentException("Plugin is not associated with this PluginLoader");
        }

        if (plugin.isEnabled()) {
            JavaPlugin jPlugin = (JavaPlugin)plugin;

            server.getPluginManager().callEvent(new PluginEvent(Event.Type.PLUGIN_DISABLE, plugin));

            jPlugin.setEnabled(false);
        }
    }
}
