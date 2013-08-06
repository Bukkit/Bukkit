package org.bukkit;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.bukkit.map.MapView;
import org.bukkit.material.Bed;
import org.bukkit.material.Button;
import org.bukkit.material.Cake;
import org.bukkit.material.Cauldron;
import org.bukkit.material.Chest;
import org.bukkit.material.Coal;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.Command;
import org.bukkit.material.Crops;
import org.bukkit.material.DetectorRail;
import org.bukkit.material.Diode;
import org.bukkit.material.DirectionalContainer;
import org.bukkit.material.Dispenser;
import org.bukkit.material.Door;
import org.bukkit.material.Dye;
import org.bukkit.material.EnderChest;
import org.bukkit.material.FlowerPot;
import org.bukkit.material.Furnace;
import org.bukkit.material.Gate;
import org.bukkit.material.Ladder;
import org.bukkit.material.Lever;
import org.bukkit.material.LongGrass;
import org.bukkit.material.MaterialData;
import org.bukkit.material.MonsterEggs;
import org.bukkit.material.Mushroom; 
import org.bukkit.material.NetherWarts;
import org.bukkit.material.PistonBaseMaterial;
import org.bukkit.material.PistonExtensionMaterial;
import org.bukkit.material.PoweredRail;
import org.bukkit.material.PressurePlate;
import org.bukkit.material.Pumpkin;
import org.bukkit.material.Rails;
import org.bukkit.material.RedstoneTorch;
import org.bukkit.material.RedstoneWire;
import org.bukkit.material.Sandstone;
import org.bukkit.material.Sign;
import org.bukkit.material.Skull;
import org.bukkit.material.SmoothBrick;
import org.bukkit.material.SpawnEgg;
import org.bukkit.material.Stairs;
import org.bukkit.material.Step;
import org.bukkit.material.Torch;
import org.bukkit.material.TrapDoor;
import org.bukkit.material.Tree;
import org.bukkit.material.Tripwire;
import org.bukkit.material.TripwireHook;
import org.bukkit.material.Vine;
import org.bukkit.material.WoodenStep;
import org.bukkit.material.Wool;
import org.bukkit.potion.Potion;
import org.bukkit.util.Java15Compat;

import com.google.common.collect.Maps;

/**
 * An enum of all material ids accepted by the official server + client
 */
public enum Material {
    AIR(0, 0, "Air"),
    STONE(1, "Stone"),
    GRASS(2, "Grass"),
    DIRT(3, "Dirt"),
    COBBLESTONE(4, "Cobblestone"),
    WOOD(5, Tree.class, "Wooden Planks"),
    SAPLING(6, Tree.class, "Sapling"),
    BEDROCK(7, "Bedrock"),
    WATER(8, MaterialData.class, "Water"),
    STATIONARY_WATER(9, MaterialData.class, "Water"),
    LAVA(10, MaterialData.class, "Lava"),
    STATIONARY_LAVA(11, MaterialData.class, "Lava"),
    SAND(12, "Sand"),
    GRAVEL(13, "Gravel"),
    GOLD_ORE(14, "Gold Ore"),
    IRON_ORE(15, "Iron Ore"),
    COAL_ORE(16, "Coal Ore"),
    LOG(17, Tree.class, "Log"),
    LEAVES(18, Tree.class, "Leaves"),
    SPONGE(19, "Sponge"),
    GLASS(20, "Glass"),
    LAPIS_ORE(21, "Lapis Lazuli Ore"),
    LAPIS_BLOCK(22, "Lapis Lazuli Block"),
    DISPENSER(23, Dispenser.class, "Dispenser"),
    SANDSTONE(24, Sandstone.class, "Sandstone"),
    NOTE_BLOCK(25, "Note Block"),
    BED_BLOCK(26, Bed.class, "Bed"),
    POWERED_RAIL(27, PoweredRail.class, "Powered Rail"),
    DETECTOR_RAIL(28, DetectorRail.class, "Detector Rail"),
    PISTON_STICKY_BASE(29, PistonBaseMaterial.class, "Sticky Piston"),
    WEB(30, "Cobweb"),
    LONG_GRASS(31, LongGrass.class, "Tall Grass"),
    DEAD_BUSH(32, "Dead Bush"),
    PISTON_BASE(33, PistonBaseMaterial.class, "Piston"),
    PISTON_EXTENSION(34, PistonExtensionMaterial.class, "Piston"),
    WOOL(35, Wool.class, "Wool"),
    PISTON_MOVING_PIECE(36, "Piston"),
    YELLOW_FLOWER(37, "Flower"),
    RED_ROSE(38, "Rose"),
    BROWN_MUSHROOM(39, "Mushroom"),
    RED_MUSHROOM(40, "Mushroom"),
    GOLD_BLOCK(41, "Block of Gold"),
    IRON_BLOCK(42, "Block of Iron"),
    DOUBLE_STEP(43, Step.class, "Double Slab"),
    STEP(44, Step.class, "Slab"),
    BRICK(45, "Bricks"),
    TNT(46, "TNT"),
    BOOKSHELF(47, "Bookshelf"),
    MOSSY_COBBLESTONE(48, "Mossy Cobblestone"),
    OBSIDIAN(49, "Obsidian"),
    TORCH(50, Torch.class, "Torch"),
    FIRE(51, "Fire"),
    MOB_SPAWNER(52, "Monster Spawner"),
    WOOD_STAIRS(53, Stairs.class, "Oak Wood Stairs"),
    CHEST(54, Chest.class, "Chest"),
    REDSTONE_WIRE(55, RedstoneWire.class, "Redstone"),
    DIAMOND_ORE(56, "Diamond Ore"),
    DIAMOND_BLOCK(57, "Block of Diamond"),
    WORKBENCH(58, "Crafting Table"),
    CROPS(59, Crops.class, "Wheat"),
    SOIL(60, MaterialData.class, "Farmland"),
    FURNACE(61, Furnace.class, "Furnace"),
    BURNING_FURNACE(62, Furnace.class, "Furnace"),
    SIGN_POST(63, 64, Sign.class, "Sign"),
    WOODEN_DOOR(64, Door.class, "Wooden Door"),
    LADDER(65, Ladder.class, "Ladder"),
    RAILS(66, Rails.class, "Rail"),
    COBBLESTONE_STAIRS(67, Stairs.class, "Cobblestone Stairs"),
    WALL_SIGN(68, 64, Sign.class, "Sign"),
    LEVER(69, Lever.class, "Lever"),
    STONE_PLATE(70, PressurePlate.class, "Pressure Plate"),
    IRON_DOOR_BLOCK(71, Door.class, "Iron Door"),
    WOOD_PLATE(72, PressurePlate.class, "Pressure Plate"),
    REDSTONE_ORE(73, "Redstone Ore"),
    GLOWING_REDSTONE_ORE(74, "Redstone Ore"),
    REDSTONE_TORCH_OFF(75, RedstoneTorch.class, "Redstone Torch"),
    REDSTONE_TORCH_ON(76, RedstoneTorch.class, "Redstone Torch"),
    STONE_BUTTON(77, Button.class, "Stone Button"),
    SNOW(78, "Snow"),
    ICE(79, "Ice"),
    SNOW_BLOCK(80, "Snow"),
    CACTUS(81, MaterialData.class, "Cactus"),
    CLAY(82, "Clay"),
    SUGAR_CANE_BLOCK(83, MaterialData.class, "Sugar Cane"),
    JUKEBOX(84, "Jukebox"),
    FENCE(85, "Fence"),
    PUMPKIN(86, Pumpkin.class, "Pumpkin"),
    NETHERRACK(87, "Netherrack"),
    SOUL_SAND(88, "Soul Sand"),
    GLOWSTONE(89, "Glowstone"),
    PORTAL(90, "Portal"),
    JACK_O_LANTERN(91, Pumpkin.class, "Jack 'o' Lantern"),
    CAKE_BLOCK(92, 64, Cake.class, "Cake"),
    DIODE_BLOCK_OFF(93, Diode.class, "Redstone Repeater"),
    DIODE_BLOCK_ON(94, Diode.class, "Redstone Repeater"),
    LOCKED_CHEST(95, "Locked Chest"),
    TRAP_DOOR(96, TrapDoor.class, "Trap Door"),
    MONSTER_EGGS(97, MonsterEggs.class, "Silverfish Block"),
    SMOOTH_BRICK(98, SmoothBrick.class, "Stone Brick"),
    HUGE_MUSHROOM_1(99, Mushroom.class, "Mushroom"),
    HUGE_MUSHROOM_2(100, Mushroom.class, "Mushroom"),
    IRON_FENCE(101, "Iron Bars"),
    THIN_GLASS(102, "Glass Pane"),
    MELON_BLOCK(103, "Melon"),
    PUMPKIN_STEM(104, MaterialData.class, "Pumpkin Stem"),
    MELON_STEM(105, MaterialData.class, "Melon Stem"),
    VINE(106, Vine.class, "Vine"),
    FENCE_GATE(107, Gate.class, "Fence Gate"),
    BRICK_STAIRS(108, Stairs.class, "Brick Stairs"),
    SMOOTH_STAIRS(109, Stairs.class, "Stone Brick Stairs"),
    MYCEL(110, "Mycelium"),
    WATER_LILY(111, "Lilypad"),
    NETHER_BRICK(112, "Nether Brick"),
    NETHER_FENCE(113, "Nether Brick Fence"),
    NETHER_BRICK_STAIRS(114, Stairs.class, "Nether Brick Stairs"),
    NETHER_WARTS(115, NetherWarts.class, "Nether Wart"),
    ENCHANTMENT_TABLE(116,  "Enchantment Table"),
    BREWING_STAND(117, MaterialData.class, "Brewing Stand"),
    CAULDRON(118, Cauldron.class, "Cauldron"),
    ENDER_PORTAL(119, "End Portal"),
    ENDER_PORTAL_FRAME(120, "End Portal Frame"),
    ENDER_STONE(121, "End Stone"),
    DRAGON_EGG(122, "Dragon Egg"),
    REDSTONE_LAMP_OFF(123, "Redstone Lamp"),
    REDSTONE_LAMP_ON(124, "Redstone Lamp"),
    WOOD_DOUBLE_STEP(125, WoodenStep.class, "Wooden Double Slab"),
    WOOD_STEP(126, WoodenStep.class, "Wooden Slab"),
    COCOA(127, CocoaPlant.class, "Cocoa Beans"),
    SANDSTONE_STAIRS(128, Stairs.class, "Sandstone Stairs"),
    EMERALD_ORE(129, "Emerald Ore"),
    ENDER_CHEST(130, EnderChest.class, "Ender Chest"),
    TRIPWIRE_HOOK(131, TripwireHook.class, "Tripwire Hook"),
    TRIPWIRE(132, Tripwire.class, "Tripwire"),
    EMERALD_BLOCK(133, "Block of Emerald"),
    SPRUCE_WOOD_STAIRS(134, Stairs.class, "Spruce Wood Stairs"),
    BIRCH_WOOD_STAIRS(135, Stairs.class, "Birch Wood Stairs"),
    JUNGLE_WOOD_STAIRS(136, Stairs.class, "Jungle Wood Stairs"),
    COMMAND(137, Command.class, "Command Block"),
    BEACON(138, "Beacon"),
    COBBLE_WALL(139, "Cobblestone Wall"),
    FLOWER_POT(140, FlowerPot.class, "Flower Pot"),
    CARROT(141, "Carrots"),
    POTATO(142, "Potatoes"),
    WOOD_BUTTON(143, Button.class, "Button"),
    SKULL(144, Skull.class, "Head"),
    ANVIL(145, "Anvil"),
    TRAPPED_CHEST(146, "Trapped Chest"),
    GOLD_PLATE(147, "Weighted Pressure Plate (Light)"),
    IRON_PLATE(148, "Weighted Pressure Plate (Heavy)"),
    REDSTONE_COMPARATOR_OFF(149, "Redstone Comparator"),
    REDSTONE_COMPARATOR_ON(150, "Redstone Comparator"),
    DAYLIGHT_DETECTOR(151, "Daylight Sensor"),
    REDSTONE_BLOCK(152, "Block of Redstone"),
    QUARTZ_ORE(153, "Nether Quartz Ore"),
    HOPPER(154, "Hopper"),
    QUARTZ_BLOCK(155, "Block of Quartz"),
    QUARTZ_STAIRS(156, Stairs.class, "Quartz Stairs"),
    ACTIVATOR_RAIL(157, PoweredRail.class, "Activator Rail"),
    DROPPER(158, Dispenser.class, "Dropper"),
    STAINED_CLAY(159, "Stained Hardened Clay"),
    HAY_BLOCK(170, "Hay Block"),
    CARPET(171, "Carpet"),
    HARD_CLAY(172, "Hardened Clay"),
    COAL_BLOCK(173, "Block of Coal"),
    // ----- Item Separator -----
    IRON_SPADE(256, 1, 250, "Iron Shovel"),
    IRON_PICKAXE(257, 1, 250, "Iron Pickaxe"),
    IRON_AXE(258, 1, 250, "Iron Axe"),
    FLINT_AND_STEEL(259, 1, 64, "Flint and Steel"),
    APPLE(260, "Apple"),
    BOW(261, 1, 384, "Bow"),
    ARROW(262, "Arrow"),
    COAL(263, Coal.class, "Coal"),
    DIAMOND(264, "Diamond"),
    IRON_INGOT(265, "Iron Ingot"),
    GOLD_INGOT(266, "Gold Ingot"),
    IRON_SWORD(267, 1, 250, "Iron Sword"),
    WOOD_SWORD(268, 1, 59, "Wooden Sword"),
    WOOD_SPADE(269, 1, 59, "Wooden Shovel"),
    WOOD_PICKAXE(270, 1, 59, "Wooden Pickaxe"),
    WOOD_AXE(271, 1, 59, "Wooden Axe"),
    STONE_SWORD(272, 1, 131, "Stone Sword"),
    STONE_SPADE(273, 1, 131, "Stone Shovel"),
    STONE_PICKAXE(274, 1, 131, "Stone Pickaxe"),
    STONE_AXE(275, 1, 131, "Stone Axe"),
    DIAMOND_SWORD(276, 1, 1561, "Diamond Sword"),
    DIAMOND_SPADE(277, 1, 1561, "Diamond Shovel"),
    DIAMOND_PICKAXE(278, 1, 1561, "Diamond Pickaxe"),
    DIAMOND_AXE(279, 1, 1561, "Diamond Axe"),
    STICK(280, "Stick"),
    BOWL(281, "Bowl"),
    MUSHROOM_SOUP(282, 1, "Mushroom Stew"),
    GOLD_SWORD(283, 1, 32, "Gold Sword"),
    GOLD_SPADE(284, 1, 32, "Gold Shovel"),
    GOLD_PICKAXE(285, 1, 32, "Gold Pickaxe"),
    GOLD_AXE(286, 1, 32, "Gold Axe"),
    STRING(287, "String"),
    FEATHER(288, "Feather"),
    SULPHUR(289, "Gunpowder"),
    WOOD_HOE(290, 1, 59, "Wooden Hoe"),
    STONE_HOE(291, 1, 131, "Stone Hoe"),
    IRON_HOE(292, 1, 250, "Iron Hoe"),
    DIAMOND_HOE(293, 1, 1561, "Diamond Hoe"),
    GOLD_HOE(294, 1, 32, "Gold Hoe"),
    SEEDS(295, "Seeds"),
    WHEAT(296, "Wheat"),
    BREAD(297, "Bread"),
    LEATHER_HELMET(298, 1, 55, "Leather Helmet"),
    LEATHER_CHESTPLATE(299, 1, 80, "Lether Chestplate"),
    LEATHER_LEGGINGS(300, 1, 75, "Leather Leggings"),
    LEATHER_BOOTS(301, 1, 65, "Leather Boots"),
    CHAINMAIL_HELMET(302, 1, 165, "Chainmail Helmet"),
    CHAINMAIL_CHESTPLATE(303, 1, 240, "Chainmail Chestplate"),
    CHAINMAIL_LEGGINGS(304, 1, 225, "Chainmail Leggings"),
    CHAINMAIL_BOOTS(305, 1, 195, "Chainmail Boots"),
    IRON_HELMET(306, 1, 165, "Iron Helmet"),
    IRON_CHESTPLATE(307, 1, 240, "Iron Chestplate"),
    IRON_LEGGINGS(308, 1, 225, "Iron Leggings"),
    IRON_BOOTS(309, 1, 195, "Iron Boots"),
    DIAMOND_HELMET(310, 1, 363, "Diamond Helmet"),
    DIAMOND_CHESTPLATE(311, 1, 528, "Diamond Chestplate"),
    DIAMOND_LEGGINGS(312, 1, 495, "Diamond Leggings"),
    DIAMOND_BOOTS(313, 1, 429, "Diamond Boots"),
    GOLD_HELMET(314, 1, 77, "Gold Helmet"),
    GOLD_CHESTPLATE(315, 1, 112, "Gold Chestplate"),
    GOLD_LEGGINGS(316, 1, 105, "Gold Leggings"),
    GOLD_BOOTS(317, 1, 91, "Gold Boots"),
    FLINT(318, "Flint"),
    PORK(319, "Raw Porkchop"),
    GRILLED_PORK(320, "Cooked Porkchop"),
    PAINTING(321, "Painting"),
    GOLDEN_APPLE(322, "Golden Apple"),
    SIGN(323, 16, "Sign"),
    WOOD_DOOR(324, 1, "Wooden Door"),
    BUCKET(325, 16, "Bucket"),
    WATER_BUCKET(326, 1, "Water Bucket"),
    LAVA_BUCKET(327, 1, "Lava Bucket"),
    MINECART(328, 1, "Minecart"),
    SADDLE(329, 1, "Saddle"),
    IRON_DOOR(330, 1, "Iron Door"),
    REDSTONE(331, "Redstone"),
    SNOW_BALL(332, 16, "Snowball"),
    BOAT(333, 1, "Boat"),
    LEATHER(334, "Leather"),
    MILK_BUCKET(335, 1, "Milk Bucket"),
    CLAY_BRICK(336, "Brick"),
    CLAY_BALL(337, "Clay"),
    SUGAR_CANE(338, "Sugar Cane"),
    PAPER(339, "Paper"),
    BOOK(340, "Book"),
    SLIME_BALL(341, "Slimeball"),
    STORAGE_MINECART(342, 1, "Minecart with Chest"),
    POWERED_MINECART(343, 1, "Minecart with Furnace"),
    EGG(344, 16, "Egg"),
    COMPASS(345, "Compass"),
    FISHING_ROD(346, 1, 64, "Fishing Rod"),
    WATCH(347, "Clock"),
    GLOWSTONE_DUST(348, "Glowstone Dust"),
    RAW_FISH(349, "Raw Fish"),
    COOKED_FISH(350, "Cooked Fish"),
    INK_SACK(351, Dye.class, "Dye"),
    BONE(352, "Bone"),
    SUGAR(353, "Sugar"),
    CAKE(354, 1, "Cake"),
    BED(355, 1, "Bed"),
    DIODE(356, "Redstone Repeater"),
    COOKIE(357, "Cookie"),
    /**
     * @see MapView
     */
    MAP(358, MaterialData.class, "Map"),
    SHEARS(359, 1, 238, "Shears"),
    MELON(360, "Melon"),
    PUMPKIN_SEEDS(361, "Pumpkin Seeds"),
    MELON_SEEDS(362, "Melon Seeds"),
    RAW_BEEF(363, "Raw Beef"),
    COOKED_BEEF(364, "Steak"),
    RAW_CHICKEN(365, "Raw Chicken"),
    COOKED_CHICKEN(366, "Cooked Chicken"),
    ROTTEN_FLESH(367, "Rotten Flesh"),
    ENDER_PEARL(368, 16, "Ender Pearl"),
    BLAZE_ROD(369, "Blaze Rod"),
    GHAST_TEAR(370, "Ghast Tear"),
    GOLD_NUGGET(371, "Gold Nugger"),
    NETHER_STALK(372, "Nether Wart"),
    /**
     * @see Potion
     */
    POTION(373, 1, MaterialData.class, "Potion"),
    GLASS_BOTTLE(374, "Glass Bottle"),
    SPIDER_EYE(375, "Spider Eye"),
    FERMENTED_SPIDER_EYE(376, "Fermented Spider Eye"),
    BLAZE_POWDER(377, "Blaze Powder"),
    MAGMA_CREAM(378, "Magma Cream"),
    BREWING_STAND_ITEM(379, "Brewing Stand"),
    CAULDRON_ITEM(380, "Cauldron"),
    EYE_OF_ENDER(381, "Eye of Ender"),
    SPECKLED_MELON(382, "Glistering Melon"),
    MONSTER_EGG(383, 64, SpawnEgg.class, "Spawn Egg"),
    EXP_BOTTLE(384, 64, "Experience Potion"),
    FIREBALL(385, 64, "Fireball"),
    BOOK_AND_QUILL(386, 1, "Book and Quill"),
    WRITTEN_BOOK(387, 1, "Written Book"),
    EMERALD(388, 64, "Emerald"),
    ITEM_FRAME(389, "Item Frame"),
    FLOWER_POT_ITEM(390, "Flower Pot"),
    CARROT_ITEM(391, "Carrot"),
    POTATO_ITEM(392, "Potato"),
    BAKED_POTATO(393, "Baked Potato"),
    POISONOUS_POTATO(394, "Poisonous Potato"),
    EMPTY_MAP(395, "Emmpty Map"),
    GOLDEN_CARROT(396, "Golden Carrot"),
    SKULL_ITEM(397, "Head"),
    CARROT_STICK(398, 1, 25, "Carrot on a Stick"),
    NETHER_STAR(399, "Nether Star"),
    PUMPKIN_PIE(400, "Pumpkin Pie"),
    FIREWORK(401, "Firework Rocket"),
    FIREWORK_CHARGE(402, "Firework Star"),
    ENCHANTED_BOOK(403, 1, "Enchanted Book"),
    REDSTONE_COMPARATOR(404, "Redstone Comparator"),
    NETHER_BRICK_ITEM(405, "Nether Brick"),
    QUARTZ(406, "Nether Quartz"),
    EXPLOSIVE_MINECART(407, 1, "Minecart with TNT"),
    HOPPER_MINECART(408, 1, "Minecart with Hopper"),
    IRON_BARDING(417, 1, "Iron Horse Armor"),
    GOLD_BARDING(418, 1, "Gold Horse Armor"),
    DIAMOND_BARDING(419, 1, "Diamond Horse Armor"),
    LEASH(420, "Lead"),
    NAME_TAG(421, "Name Tag"),
    GOLD_RECORD(2256, 1, "C418 - 13"),
    GREEN_RECORD(2257, 1, "C418 - Cat"),
    RECORD_3(2258, 1, "C418 - Blocks"),
    RECORD_4(2259, 1, "C418 - Chirp"),
    RECORD_5(2260, 1, "C418 - Far"),
    RECORD_6(2261, 1, "C418 - Mall"),
    RECORD_7(2262, 1, "C418 - Mellohi"),
    RECORD_8(2263, 1, "C418 - Stal"),
    RECORD_9(2264, 1, "C418 - Strad"),
    RECORD_10(2265, 1, "C418 - Ward"),
    RECORD_11(2266, 1, "C418 - 11"),
    RECORD_12(2267, 1, "C418 - Wait"),
    ;

    private final int id;
    private final Constructor<? extends MaterialData> ctor;
    private static Material[] byId = new Material[383];
    private final static Map<String, Material> BY_NAME = Maps.newHashMap();
    private final int maxStack;
    private final short durability;
    private final String name;

    private Material(final int id, String name) {
        this(id, 64, name);
    }

    private Material(final int id, final int stack, String name) {
        this(id, stack, MaterialData.class, name);
    }

    private Material(final int id, final int stack, final int durability, String name) {
        this(id, stack, durability, MaterialData.class, name);
    }

    private Material(final int id, final Class<? extends MaterialData> data, String name) {
        this(id, 64, data, name);
    }

    private Material(final int id, final int stack, final Class<? extends MaterialData> data, String name) {
        this(id, stack, 0, data, name);
    }

    private Material(final int id, final int stack, final int durability, final Class<? extends MaterialData> data, String name) {
        this.name = name;
        this.id = id;
        this.durability = (short) durability;
        this.maxStack = stack;
        // try to cache the constructor for this material
        try {
            this.ctor = data.getConstructor(int.class, byte.class);
        } catch (NoSuchMethodException ex) {
            throw new AssertionError(ex);
        } catch (SecurityException ex) {
            throw new AssertionError(ex);
        }
    }

    public String toString(){
        return name;
    }

    /**
     * Gets the item ID or block ID of this Material
     *
     * @return ID of this material
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the maximum amount of this material that can be held in a stack
     *
     * @return Maximum stack size for this material
     */
    public int getMaxStackSize() {
        return maxStack;
    }

    /**
     * Gets the maximum durability of this material
     *
     * @return Maximum durability for this material
     */
    public short getMaxDurability() {
        return durability;
    }

    /**
     * Gets the MaterialData class associated with this Material
     *
     * @return MaterialData associated with this Material
     */
    public Class<? extends MaterialData> getData() {
        return ctor.getDeclaringClass();
    }

    /**
     * Constructs a new MaterialData relevant for this Material, with the given
     * initial data
     *
     * @param raw Initial data to construct the MaterialData with
     * @return New MaterialData with the given data
     */
    public MaterialData getNewData(final byte raw) {
        try {
            return ctor.newInstance(id, raw);
        } catch (InstantiationException ex) {
            final Throwable t = ex.getCause();
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }
            if (t instanceof Error) {
                throw (Error) t;
            }
            throw new AssertionError(t);
        } catch (Throwable t) {
            throw new AssertionError(t);
        }
    }

    /**
     * Checks if this Material is a placable block
     *
     * @return true if this material is a block
     */
    public boolean isBlock() {
        return id < 256;
    }

    /**
     * Checks if this Material is edible.
     *
     * @return true if this Material is edible.
     */
    public boolean isEdible() {
        switch (this) {
            case BREAD:
            case CARROT_ITEM:
            case BAKED_POTATO:
            case POTATO_ITEM:
            case POISONOUS_POTATO:
            case GOLDEN_CARROT:
            case PUMPKIN_PIE:
            case COOKIE:
            case MELON:
            case MUSHROOM_SOUP:
            case RAW_CHICKEN:
            case COOKED_CHICKEN:
            case RAW_BEEF:
            case COOKED_BEEF:
            case RAW_FISH:
            case COOKED_FISH:
            case PORK:
            case GRILLED_PORK:
            case APPLE:
            case GOLDEN_APPLE:
            case ROTTEN_FLESH:
            case SPIDER_EYE:
                return true;
            default:
                return false;
        }
    }

    /**
     * Attempts to get the Material with the given ID
     *
     * @param id ID of the material to get
     * @return Material if found, or null
     */
    public static Material getMaterial(final int id) {
        if (byId.length > id && id >= 0) {
            return byId[id];
        } else {
            return null;
        }
    }

    /**
     * Attempts to get the Material with the given name.
     * This is a normal lookup, names must be the precise name they are given
     * in the enum.
     *
     * @param name Name of the material to get
     * @return Material if found, or null
     */
    public static Material getMaterial(final String name) {
        return BY_NAME.get(name);
    }

    /**
     * Attempts to match the Material with the given name.
     * This is a match lookup; names will be converted to uppercase, then stripped
     * of special characters in an attempt to format it like the enum
     *
     * @param name Name of the material to get
     * @return Material if found, or null
     */
    public static Material matchMaterial(final String name) {
        Validate.notNull(name, "Name cannot be null");

        Material result = null;

        try {
            result = getMaterial(Integer.parseInt(name));
        } catch (NumberFormatException ex) {}

        if (result == null) {
            String filtered = name.toUpperCase();

            filtered = filtered.replaceAll("\\s+", "_").replaceAll("\\W", "");
            result = BY_NAME.get(filtered);
        }

        return result;
    }

static {
        for (Material material : values()) {
        if (byId.length > material.id) {
        byId[material.id] = material;
} else {
        byId = Java15Compat.Arrays_copyOfRange(byId, 0, material.id + 2);
byId[material.id] = material;
}
        BY_NAME.put(material.name(), material);
}
        }

/**
 * @return True if this material represents a playable music disk.
 */
public boolean isRecord() {
        return id >= GOLD_RECORD.id && id <= RECORD_12.id;
}

/**
 * Check if the material is a block and solid (cannot be passed through by a player)
 *
 * @return True if this material is a block and solid
 */
public boolean isSolid() {
        if (!isBlock() || id == 0) {
        return false;
}
        switch (this) {
        case STONE:
        case GRASS:
        case DIRT:
        case COBBLESTONE:
        case WOOD:
        case BEDROCK:
        case SAND:
        case GRAVEL:
        case GOLD_ORE:
        case IRON_ORE:
        case COAL_ORE:
        case LOG:
        case LEAVES:
        case SPONGE:
        case GLASS:
        case LAPIS_ORE:
        case LAPIS_BLOCK:
        case DISPENSER:
        case SANDSTONE:
        case NOTE_BLOCK:
        case BED_BLOCK:
        case PISTON_STICKY_BASE:
        case PISTON_BASE:
        case PISTON_EXTENSION:
        case WOOL:
        case PISTON_MOVING_PIECE:
        case GOLD_BLOCK:
        case IRON_BLOCK:
        case DOUBLE_STEP:
        case STEP:
        case BRICK:
        case TNT:
        case BOOKSHELF:
        case MOSSY_COBBLESTONE:
        case OBSIDIAN:
        case MOB_SPAWNER:
        case WOOD_STAIRS:
        case CHEST:
        case DIAMOND_ORE:
        case DIAMOND_BLOCK:
        case WORKBENCH:
        case SOIL:
        case FURNACE:
        case BURNING_FURNACE:
        case SIGN_POST:
        case WOODEN_DOOR:
        case COBBLESTONE_STAIRS:
        case WALL_SIGN:
        case STONE_PLATE:
        case IRON_DOOR_BLOCK:
        case WOOD_PLATE:
        case REDSTONE_ORE:
        case GLOWING_REDSTONE_ORE:
        case ICE:
        case SNOW_BLOCK:
        case CACTUS:
        case CLAY:
        case JUKEBOX:
        case FENCE:
        case PUMPKIN:
        case NETHERRACK:
        case SOUL_SAND:
        case GLOWSTONE:
        case JACK_O_LANTERN:
        case CAKE_BLOCK:
        case LOCKED_CHEST:
        case TRAP_DOOR:
        case MONSTER_EGGS:
        case SMOOTH_BRICK:
        case HUGE_MUSHROOM_1:
        case HUGE_MUSHROOM_2:
        case IRON_FENCE:
        case THIN_GLASS:
        case MELON_BLOCK:
        case FENCE_GATE:
        case BRICK_STAIRS:
        case SMOOTH_STAIRS:
        case MYCEL:
        case NETHER_BRICK:
        case NETHER_FENCE:
        case NETHER_BRICK_STAIRS:
        case ENCHANTMENT_TABLE:
        case BREWING_STAND:
        case CAULDRON:
        case ENDER_PORTAL_FRAME:
        case ENDER_STONE:
        case DRAGON_EGG:
        case REDSTONE_LAMP_OFF:
        case REDSTONE_LAMP_ON:
        case WOOD_DOUBLE_STEP:
        case WOOD_STEP:
        case SANDSTONE_STAIRS:
        case EMERALD_ORE:
        case ENDER_CHEST:
        case EMERALD_BLOCK:
        case SPRUCE_WOOD_STAIRS:
        case BIRCH_WOOD_STAIRS:
        case JUNGLE_WOOD_STAIRS:
        case COMMAND:
        case BEACON:
        case COBBLE_WALL:
        case ANVIL:
        case TRAPPED_CHEST:
        case GOLD_PLATE:
        case IRON_PLATE:
        case DAYLIGHT_DETECTOR:
        case REDSTONE_BLOCK:
        case QUARTZ_ORE:
        case HOPPER:
        case QUARTZ_BLOCK:
        case QUARTZ_STAIRS:
        case DROPPER:
        case STAINED_CLAY:
        case HAY_BLOCK:
        case HARD_CLAY:
        case COAL_BLOCK:
        return true;
default:
        return false;
}
        }

/**
 * Check if the material is a block and does not block any light
 *
 * @return True if this material is a block and does not block any light
 */
public boolean isTransparent() {
        if (!isBlock()) {
        return false;
}
        switch (this) {
        case AIR:
        case SAPLING:
        case POWERED_RAIL:
        case DETECTOR_RAIL:
        case LONG_GRASS:
        case DEAD_BUSH:
        case YELLOW_FLOWER:
        case RED_ROSE:
        case BROWN_MUSHROOM:
        case RED_MUSHROOM:
        case TORCH:
        case FIRE:
        case REDSTONE_WIRE:
        case CROPS:
        case LADDER:
        case RAILS:
        case LEVER:
        case REDSTONE_TORCH_OFF:
        case REDSTONE_TORCH_ON:
        case STONE_BUTTON:
        case SNOW:
        case SUGAR_CANE_BLOCK:
        case PORTAL:
        case DIODE_BLOCK_OFF:
        case DIODE_BLOCK_ON:
        case PUMPKIN_STEM:
        case MELON_STEM:
        case VINE:
        case WATER_LILY:
        case NETHER_WARTS:
        case ENDER_PORTAL:
        case COCOA:
        case TRIPWIRE_HOOK:
        case TRIPWIRE:
        case FLOWER_POT:
        case CARROT:
        case POTATO:
        case WOOD_BUTTON:
        case SKULL:
        case REDSTONE_COMPARATOR_OFF:
        case REDSTONE_COMPARATOR_ON:
        case ACTIVATOR_RAIL:
        case CARPET:
        return true;
default:
        return false;
}
        }

/**
 * Check if the material is a block and can catch fire
 *
 * @return True if this material is a block and can catch fire
 */
public boolean isFlammable() {
        if (!isBlock()) {
        return false;
}
        switch (this) {
        case WOOD:
        case LOG:
        case LEAVES:
        case NOTE_BLOCK:
        case BED_BLOCK:
        case LONG_GRASS:
        case DEAD_BUSH:
        case WOOL:
        case TNT:
        case BOOKSHELF:
        case WOOD_STAIRS:
        case CHEST:
        case WORKBENCH:
        case SIGN_POST:
        case WOODEN_DOOR:
        case WALL_SIGN:
        case WOOD_PLATE:
        case JUKEBOX:
        case FENCE:
        case LOCKED_CHEST:
        case TRAP_DOOR:
        case HUGE_MUSHROOM_1:
        case HUGE_MUSHROOM_2:
        case VINE:
        case FENCE_GATE:
        case WOOD_DOUBLE_STEP:
        case WOOD_STEP:
        case SPRUCE_WOOD_STAIRS:
        case BIRCH_WOOD_STAIRS:
        case JUNGLE_WOOD_STAIRS:
        case TRAPPED_CHEST:
        case DAYLIGHT_DETECTOR:
        case CARPET:
        return true;
default:
        return false;
}
        }

/**
 * Check if the material is a block and can burn away
 *
 * @return True if this material is a block and can burn away
 */
public boolean isBurnable() {
        if (!isBlock()) {
        return false;
}
        switch (this) {
        case WOOD:
        case LOG:
        case LEAVES:
        case LONG_GRASS:
        case WOOL:
        case TNT:
        case BOOKSHELF:
        case WOOD_STAIRS:
        case FENCE:
        case VINE:
        case WOOD_DOUBLE_STEP:
        case WOOD_STEP:
        case SPRUCE_WOOD_STAIRS:
        case BIRCH_WOOD_STAIRS:
        case JUNGLE_WOOD_STAIRS:
        case HAY_BLOCK:
        case COAL_BLOCK:
        return true;
default:
        return false;
}
        }

/**
 * Check if the material is a block and completely blocks vision
 *
 * @return True if this material is a block and completely blocks vision
 */
public boolean isOccluding() {
        if (!isBlock()) {
        return false;
}
        switch (this) {
        case STONE:
        case GRASS:
        case DIRT:
        case COBBLESTONE:
        case WOOD:
        case BEDROCK:
        case SAND:
        case GRAVEL:
        case GOLD_ORE:
        case IRON_ORE:
        case COAL_ORE:
        case LOG:
        case SPONGE:
        case LAPIS_ORE:
        case LAPIS_BLOCK:
        case DISPENSER:
        case SANDSTONE:
        case NOTE_BLOCK:
        case WOOL:
        case GOLD_BLOCK:
        case IRON_BLOCK:
        case DOUBLE_STEP:
        case BRICK:
        case BOOKSHELF:
        case MOSSY_COBBLESTONE:
        case OBSIDIAN:
        case MOB_SPAWNER:
        case DIAMOND_ORE:
        case DIAMOND_BLOCK:
        case WORKBENCH:
        case FURNACE:
        case BURNING_FURNACE:
        case REDSTONE_ORE:
        case GLOWING_REDSTONE_ORE:
        case SNOW_BLOCK:
        case CLAY:
        case JUKEBOX:
        case PUMPKIN:
        case NETHERRACK:
        case SOUL_SAND:
        case JACK_O_LANTERN:
        case LOCKED_CHEST:
        case MONSTER_EGGS:
        case SMOOTH_BRICK:
        case HUGE_MUSHROOM_1:
        case HUGE_MUSHROOM_2:
        case MELON_BLOCK:
        case MYCEL:
        case NETHER_BRICK:
        case ENDER_PORTAL_FRAME:
        case ENDER_STONE:
        case REDSTONE_LAMP_OFF:
        case REDSTONE_LAMP_ON:
        case WOOD_DOUBLE_STEP:
        case EMERALD_ORE:
        case EMERALD_BLOCK:
        case COMMAND:
        case QUARTZ_ORE:
        case QUARTZ_BLOCK:
        case DROPPER:
        case STAINED_CLAY:
        case HAY_BLOCK:
        case HARD_CLAY:
        case COAL_BLOCK:
        return true;
default:
        return false;
}
        }

/**
 * @return True if this material is affected by gravity.
 */
public boolean hasGravity() {
        if (!isBlock()) {
        return false;
}
        switch (this) {
        case SAND:
        case GRAVEL:
        case ANVIL:
        return true;
default:
        return false;
}
        }
        }
