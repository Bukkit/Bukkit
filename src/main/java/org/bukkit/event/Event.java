package org.bukkit.event;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents an event
 */
public abstract class Event {
    private final Type type;
    private final String name;
    private final int nameId;
    private final Enum customType;

    static private final ConcurrentHashMap<String,Integer> nameMap = new ConcurrentHashMap<String,Integer>();
    static private final ConcurrentHashMap<Integer,String> reverseNameMap = new ConcurrentHashMap<Integer,String>();
    static private final Object nameIdCounterLock = new Object();
    static private int nameIdCounter = 1;

    protected Event(final Type type) {
        exAssert(type != null, "type is null");
        exAssert(type != Type.CUSTOM_EVENT, "use Event(String) to make custom events");
        this.type = type;
        this.name = null;
        this.nameId = -1;
        this.customType = null;
    }

    protected Event(final String name) {
        exAssert(name != null, "name is null");
        this.type = Type.CUSTOM_EVENT;
        this.name = name;
        this.nameId = -1;
        this.customType = null;
    }

    protected Event(final Enum type, final String name) { 
        exAssert(type != null, "type is null");
        exAssert(name != null, "name is null");
        this.type = Type.CUSTOM_EVENT;
        this.name = name;
        this.nameId = getNameId(name);
        this.customType = type;
    }

    protected Event(final Enum type, final int nameId) {
        exAssert(type != null, "type is null");
        this.type = Type.CUSTOM_EVENT;
        this.name = getNameString(nameId);
        this.nameId = nameId;
        exAssert(this.name != null, "unknown name id");
        this.customType = type;
    }

    static private int getNextNameId() {
        synchronized(nameIdCounterLock) {
            return nameIdCounter++;
        }
    }

    /**
     * Gets the id number corresponding to name.  If the name doesn't exist, an id will be assigned for the string.
     * Id numbers are not constant between server restarts
     *
     * @param String Name of custom event
     * @return int Id number for the named event
     */
    static public final int getNameId(String name) {
        if(!nameMap.containsKey(name)) {
            Integer temp1 = getNextNameId();

            reverseNameMap.put(temp1,name);  // This is safe since name ids are unique.  temp1 is not known to any other thread.

            Integer temp2 = nameMap.putIfAbsent(name, temp1);

            if(temp2 != null) { // Two string to (different) key mappings occured at almost the same time.  
                return temp2;   // The other thread "won", return its mapping
            } else {
                return temp1;
            }
        } else {  // The name already existed, return its id.
            return nameMap.get(name);
        }
    }

    /**
     * Gets the name corresponding to the given Id.  This function should only be called for ids that exist
     *
     * @param int Id number for the name
     * @return String Name of event
     */
    static public final String getNameString(int id) {
        String ret = reverseNameMap.get(id);
        exAssert(ret != null, "Unknown name id");
        return ret;
    }

    /**
     * Gets the Type of this event
     * @return Event type that this object represents
     */
    public final Type getType() {
        return type;
    }

    /**
     * Gets the custom type of this custom name event
     * @return Enum type that this object represents
     */
    public final Enum getCustomType() {
        exAssert(customType!=null, "No custom event type set");
        return customType;
    }

    /**
     * Gets the nameId of this custom name event
     * @return int Id number for the current event's name
     */
    public final int getNameId() {
        return nameId;
    }


    static private void exAssert(boolean b, String s) {
        if(!b) throw new IllegalArgumentException(s);
    }

    /**
     * Gets the event's name. 
     *
     * @return Name of this event
     */
    public final String getEventName() {
        if( type != Type.CUSTOM_EVENT) {
            return type.toString();
        } else if ( customType != null ) {
            return customType.toString();
        } else {
            return name;
        }
    }

    /**
     * Represents an events priority in execution
     */
    public enum Priority {
        /**
         * Event call is of very low importance and should be ran first, to allow
         * other plugins to further customise the outcome
         */
        Lowest,

        /**
         * Event call is of low importance
         */
        Low,

        /**
         * Event call is neither important or unimportant, and may be ran normally
         */
        Normal,

        /**
         * Event call is of high importance
         */
        High,

        /**
         * Event call is critical and must have the final say in what happens
         * to the event
         */
        Highest,

        /**
         * Event is listened to purely for monitoring the outcome of an event.
         *
         * No modifications to the event should be made under this priority
         */
        Monitor
    }

    /**
     * Represents a category used by Type
     */
    public enum Category {
        /**
         * Represents Player-based events
         * @see Category.LIVING_ENTITY
         */
        PLAYER,

        /**
         * Represents Block-based events
         */
        BLOCK,

        /**
         * Represents LivingEntity-based events
         */
        LIVING_ENTITY,

        /**
         * Represents Vehicle-based events
         */
        VEHICLE,

        /**
         * Represents World-based events
         */
        WORLD,

        /**
         * Represents Server and Plugin based events
         */
        SERVER,

        /**
         * Represents Inventory-based events
         */
        INVENTORY,

        /**
         * Any miscellaneous events
         */
        MISCELLANEOUS;
    }

    /**
     * Provides a lookup for all core events
     *
     * @see org.bukkit.event.
     */
    public enum Type {
        /**
         * PLAYER EVENTS
         */

        /**
         * Called when a player joins a server
         *
         * @see org.bukkit.event.player.PlayerEvent
         */
        PLAYER_JOIN (Category.PLAYER),

        /**
         * Called when a player is attempting to join a server
         *
         * @see org.bukkit.event.player.PlayerLoginEvent
         */
        PLAYER_LOGIN (Category.PLAYER),

        /**
         * Called when a player respawns
         *
         * @see org.bukkit.event.player.PlayerEvent
         */
        PLAYER_RESPAWN (Category.PLAYER),

        /**
         * Called when a player gets kicked a server
         *
         * @see org.bukkit.event.player.PlayerEvent
         */
        PLAYER_KICK (Category.PLAYER),

        /**
         * Called when a player sends a chat message
         *
         * @see org.bukkit.event.player.PlayerChatEvent
         */
        PLAYER_CHAT (Category.PLAYER),

        /**
         * Called when a player attempts to use a command
         *
         * @see org.bukkit.event.player.PlayerChatEvent
         */
        PLAYER_COMMAND (Category.PLAYER),

        /**
         * Called when a player leaves a server
         *
         * @see org.bukkit.event.player.PlayerEvent
         */
        PLAYER_QUIT (Category.PLAYER),

        /**
         * Called when a player moves position in the world
         *
         * @see org.bukkit.event.player.PlayerMoveEvent
         */
        PLAYER_MOVE (Category.PLAYER),

        /**
         * Called when a player undergoes an animation, such as arm swinging
         *
         * @see org.bukkit.event.player.PlayerAnimationEvent
         */
        PLAYER_ANIMATION (Category.PLAYER),

        /**
         * Called when a player toggles sneak mode
         *
         * @todo: add javadoc see comment
         */
        PLAYER_TOGGLE_SNEAK (Category.PLAYER),

        /**
         * Called when a player uses an item
         *
         * @see org.bukkit.event.player.PlayerItemEvent
         */
        PLAYER_ITEM (Category.PLAYER),

        /**
         * Called when a player throws an egg and it might hatch
         *
         * @see org.bukkit.event.player.PlayerEggThrowEvent
         */
        PLAYER_EGG_THROW (Category.PLAYER),

        /**
         * Called when a player teleports from one position to another
         *
         * @see org.bukkit.event.player.PlayerMoveEvent
         */
        PLAYER_TELEPORT (Category.PLAYER),

        /**
         * Called when a player changes their held item
         *
         * @see org.bukkit.event.player.PlayerItemHeldEvent
         */
        PLAYER_ITEM_HELD (Category.PLAYER),

        /**
         * Called when a player drops an item
         *
         * @see org.bukkit.event.player.PlayerDropItemEvent
         */
        PLAYER_DROP_ITEM (Category.PLAYER),

        /**
         * Called when a player picks an item up off the ground
         *
         * @see org.bukkit.event.player.PlayerPickupItemEvent
         */
        PLAYER_PICKUP_ITEM (Category.PLAYER),

        /**
         * BLOCK EVENTS
         */

        /**
         * Called when a block is damaged (hit by a player)
         *
         * @see org.bukkit.event.block.BlockDamageEvent
         */
        BLOCK_DAMAGED (Category.BLOCK),

        /**
         * Called when a block is undergoing a universe physics
         * check on whether it can be built
         *
         * For example, cacti cannot be built on grass unless overridden here
         *
         * @see org.bukkit.event.block.BlockCanBuildEvent
         */
        BLOCK_CANBUILD (Category.BLOCK),

        /**
         * Called when a block of water or lava attempts to flow into another
         * block
         *
         * @see org.bukkit.event.block.BlockFromToEvent
         */
        BLOCK_FLOW (Category.BLOCK),

        /**
         * Called when a block is being set on fire from another block, such as
         * an adjacent block of fire attempting to set fire to wood
         *
         * @see org.bukkit.event.block.BlockIgniteEvent
         */
        BLOCK_IGNITE (Category.BLOCK),

        /**
         * Called when a block undergoes a physics check
         *
         * A physics check is commonly called when an adjacent block changes
         * type
         *
         * @see org.bukkit.event.block.BlockPhysicsEvent
         */
        BLOCK_PHYSICS (Category.BLOCK),

        /**
         * Called when a player is attempting to place a block
         *
         * @see org.bukkit.event.block.BlockRightClickEvent
         */
        BLOCK_RIGHTCLICKED (Category.BLOCK),

        /**
         * Called when a player is attempting to place a block
         *
         * @see org.bukkit.event.block.BlockPlaceEvent
         */
        BLOCK_PLACED (Category.BLOCK),

        /**
         * Called when an entity interacts with a block (lever, door, pressure plate, chest, furnace)
         *
         * @see org.bukkit.event.block.BlockInteractEvent
         */
        BLOCK_INTERACT (Category.BLOCK),

        /**
         * Called when a block is destroyed from being burnt by fire
         *
         * @see org.bukkit.event.block.BlockBurnEvent
         */
        BLOCK_BURN (Category.BLOCK),

        /**
         * Called when leaves are decaying naturally
         *
         * @see org.bukkit.event.block.LeavesDecayEvent
         */
        LEAVES_DECAY (Category.BLOCK),

        /**
         * Called when a sign is changed
         *
         * @see org.bukkit.event.block.SignChangeEvent
         */
        SIGN_CHANGE (Category.BLOCK),

        /**
         * Called when a liquid attempts to flow into a block which already
         * contains a "breakable" block, such as redstone wire
         *
         * @todo: add javadoc see comment
         */
        LIQUID_DESTROY (Category.BLOCK),

        /**
         * Called when a block changes redstone current. Only triggered on blocks
         * that are actually capable of transmitting or carrying a redstone
         * current
         *
         * @see org.bukkit.event.block.BlockFromToEvent
         */
        REDSTONE_CHANGE (Category.BLOCK),

        /**
         * Called when a block is destroyed by a player.
         *
         * @see org.bukkit.event.block.BlockBreakEvent
         */
        BLOCK_BREAK (Category.BLOCK),

        /**
         * INVENTORY EVENTS
         */

        /**
         * Called when a player opens an inventory
         *
         * @todo: add javadoc see comment
         */
        INVENTORY_OPEN (Category.INVENTORY),

        /**
         * Called when a player closes an inventory
         *
         * @todo: add javadoc see comment
         */
        INVENTORY_CLOSE (Category.INVENTORY),

        /**
         * Called when a player clicks on an inventory slot
         *
         * @todo: add javadoc see comment
         */
        INVENTORY_CLICK (Category.INVENTORY),

        /**
         * Called when an inventory slot changes values or type
         *
         * @todo: add javadoc see comment
         */
        INVENTORY_CHANGE (Category.INVENTORY),

        /**
         * Called when a player is attempting to perform an inventory transaction
         *
         * @todo: add javadoc see comment
         */
        INVENTORY_TRANSACTION (Category.INVENTORY),

        /**
         * SERVER EVENTS
         */

        /**
         * Called when a plugin is enabled
         *
         * @see org.bukkit.event.server.PluginEvent
         */
        PLUGIN_ENABLE (Category.SERVER),

        /**
         * Called when a plugin is disabled
         *
         * @see org.bukkit.event.server.PluginEvent
         */
        PLUGIN_DISABLE (Category.SERVER),

        /**
         * Called when a server command is called
         *
         * @see org.bukkit.event.server.PluginEvent
         */
        SERVER_COMMAND (Category.SERVER),

        /**
         * WORLD EVENTS
         */

        /**
         * Called when a chunk is loaded
         *
         * If a new chunk is being generated for loading, it will call
         * Type.CHUNK_GENERATION and then Type.CHUNK_LOADED upon completion
         *
         * @see org.bukkit.event.world.ChunkLoadEvent
         */
        CHUNK_LOADED (Category.WORLD),

        /**
         * Called when a chunk is unloaded
         *
         * @see org.bukkit.event.world.ChunkUnloadEvent
         */
        CHUNK_UNLOADED (Category.WORLD),

        /**
         * Called when a chunk needs to be generated
         *
         * @todo: add javadoc see comment
         */
        CHUNK_GENERATION (Category.WORLD),

        /**
         * Called when an ItemEntity spawns in the world
         *
         * @todo: add javadoc see comment
         */
        ITEM_SPAWN (Category.WORLD),

        /**
         * Called when a world is saved
         * 
         */
        WORLD_SAVED (Category.WORLD),

        /**
         * Called when a World is loaded
         */
        WORLD_LOADED (Category.WORLD),

        /**
         * LIVING_ENTITY EVENTS
         */

        /**
         * Called when a creature, either hostile or neutral, attempts to spawn
         * in the world "naturally"
         *
         * @todo: add javadoc see comment
         */
        CREATURE_SPAWN (Category.LIVING_ENTITY),

        /**
         * Called when a LivingEntity is damaged by the environment (for example,
         * falling or lava)
         *
         * @see org.bukkit.event.entity.EntityDamageByBlockEvent
         */
        ENTITY_DAMAGEDBY_BLOCK (Category.LIVING_ENTITY),

        /**
         * Called when a LivingEntity is damaged by another LivingEntity
         *
         * @see org.bukkit.event.entity.EntityDamageByEntityEvent
         */
        ENTITY_DAMAGEDBY_ENTITY (Category.LIVING_ENTITY),

        /**
         * Called when a LivingEntity is damaged by a projectile Entity
         *
         * @see org.bukkit.event.entity.EntityDamageByProjectileEvent
         */
        ENTITY_DAMAGEDBY_PROJECTILE (Category.LIVING_ENTITY),

        /**
         * Called when a LivingEntity is damaged with no source.
         *
         * @see org.bukkit.event.entity.EntityDamageEvent
         */
        ENTITY_DAMAGED(Category.LIVING_ENTITY),

        /**
         * Called when a LivingEntity dies
         *
         * @todo: add javadoc see comment
         */
        ENTITY_DEATH (Category.LIVING_ENTITY),

        /**
         * Called when a Skeleton or Zombie catch fire due to the sun
         *
         * @todo: add javadoc see comment
         */
        ENTITY_COMBUST (Category.LIVING_ENTITY),

        /**
         * Called when an entity explodes, either TNT, Creeper, or Ghast Fireball
         *
         * @todo: add javadoc see comment
         */
        ENTITY_EXPLODE (Category.LIVING_ENTITY),

        /**
         * Called when an entity has made a decision to explode.
         * 
         * Provides an opportunity to act on the entity, change the explosion radius,
         * or to change the fire-spread flag.
         * 
         * Canceling the event negates the entity's decision to explode.
         * For EntityCreeper, this resets the fuse but does not kill the Entity.
         * For EntityFireball and EntityTNTPrimed....?
         * 
         * @see org.bukkit.event.entity.EntityExplodeTriggerEvent
         */
        EXPLOSION_PRIMED (Category.LIVING_ENTITY),

        /**
         * Called when an entity targets another entity
         *
         * @see org.bukkit.event.entity.EntityTargetEvent
         */
        ENTITY_TARGET (Category.LIVING_ENTITY),

        /**
         * VEHICLE EVENTS
         */

        /**
         * Called when a vehicle is placed by a player
         *
         * @see org.bukkit.event.vehicle.VehicleCreateEvent
         */
        VEHICLE_CREATE (Category.VEHICLE),

        /**
         * Called when a vehicle is damaged by a LivingEntity
         *
         * @see org.bukkit.event.vehicle.VehicleDamageEvent
         */
        VEHICLE_DAMAGE (Category.VEHICLE),

        /**
         * Called when a vehicle collides with an Entity
         *
         * @see org.bukkit.event.vehicle.VehicleCollisionEvent
         */
        VEHICLE_COLLISION_ENTITY (Category.VEHICLE),

        /**
         * Called when a vehicle collides with a Block
         *
         * @see org.bukkit.event.vehicle.VehicleBlockCollisionEvent
         */
        VEHICLE_COLLISION_BLOCK (Category.VEHICLE),

        /**
         * Called when a vehicle is entered by a LivingEntity
         *
         * @see org.bukkit.event.vehicle.VehicleEnterEvent
         */
        VEHICLE_ENTER (Category.VEHICLE),

        /**
         * Called when a vehicle is exited by a LivingEntity
         *
         * @see org.bukkit.event.vehicle.VehicleExitEvent
         */
        VEHICLE_EXIT (Category.VEHICLE),

        /**
         * Called when a vehicle moves position in the world
         *
         * @see org.bukkit.event.vehicle.VehicleMoveEvent
         */
        VEHICLE_MOVE (Category.VEHICLE),

        /**
         * Called when a vehicle is going through an update cycle, rechecking itself
         *
         * @see org.bukkit.event.vehicle.VehicleUpdateEvent
         */
        VEHICLE_UPDATE (Category.VEHICLE),

        /**
         * MISCELLANEOUS EVENTS
         */

        /**
         * Represents a custom event, isn't actually used
         */
        CUSTOM_EVENT (Category.MISCELLANEOUS);

        private final Category category;

        private Type(Category category) {
            this.category = category;
        }

        /**
         * Gets the Category assigned to this event
         *
         * @return Category of this Event.Type
         */
        public Category getCategory() {
            return category;
        }
    }
}
