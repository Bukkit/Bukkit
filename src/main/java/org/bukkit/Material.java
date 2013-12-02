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
    AIR("minecraft:air", 0, 0),
    STONE("minecraft:stone", 1),
    GRASS("minecraft:grass", 2),
    DIRT("minecraft:dirt", 3),
    COBBLESTONE("minecraft:cobblestone", 4),
    WOOD("minecraft:planks", 5, Tree.class),
    SAPLING("minecraft:sapling", 6, Tree.class),
    BEDROCK("minecraft:bedrock", 7),
    WATER("minecraft:flowing_water", 8, MaterialData.class),
    STATIONARY_WATER("minecraft:water", 9, MaterialData.class),
    LAVA("minecraft:flowing_lava", 10, MaterialData.class),
    STATIONARY_LAVA("minecraft:lava", 11, MaterialData.class),
    SAND("minecraft:sand", 12),
    GRAVEL("minecraft:gravel", 13),
    GOLD_ORE("minecraft:gold_ore", 14),
    IRON_ORE("minecraft:iron_ore", 15),
    COAL_ORE("minecraft:coal_ore", 16),
    LOG("minecraft:log", 17, Tree.class),
    LEAVES("minecraft:leaves", 18, Tree.class),
    SPONGE("minecraft:sponge", 19),
    GLASS("minecraft:glass", 20),
    LAPIS_ORE("minecraft:lapis_ore", 21),
    LAPIS_BLOCK("minecraft:lapis_block", 22),
    DISPENSER("minecraft:dispenser", 23, Dispenser.class),
    SANDSTONE("minecraft:sandstone", 24, Sandstone.class),
    NOTE_BLOCK("minecraft:noteblock", 25),
    BED_BLOCK("minecraft:bed", 26, Bed.class),
    POWERED_RAIL("minecraft:golden_rail", 27, PoweredRail.class),
    DETECTOR_RAIL("minecraft:detector_rail", 28, DetectorRail.class),
    PISTON_STICKY_BASE("minecraft:sticky_piston", 29, PistonBaseMaterial.class),
    WEB("minecraft:web", 30),
    LONG_GRASS("minecraft:tallgrass", 31, LongGrass.class),
    DEAD_BUSH("minecraft:deadbush", 32),
    PISTON_BASE("minecraft:piston", 33, PistonBaseMaterial.class),
    PISTON_EXTENSION("minecraft:piston_head", 34, PistonExtensionMaterial.class),
    WOOL("minecraft:wool", 35, Wool.class),
    PISTON_MOVING_PIECE("minecraft:piston_extension", 36),
    YELLOW_FLOWER("minecraft:yellow_flower", 37),
    RED_ROSE("minecraft:red_flower", 38),
    BROWN_MUSHROOM("minecraft:brown_mushroom", 39),
    RED_MUSHROOM("minecraft:red_mushroom", 40),
    GOLD_BLOCK("minecraft:gold_block", 41),
    IRON_BLOCK("minecraft:iron_block", 42),
    DOUBLE_STEP("minecraft:double_stone_slab", 43, Step.class),
    STEP("minecraft:stone_slab", 44, Step.class),
    BRICK("minecraft:brick_block", 45),
    TNT("minecraft:tnt", 46),
    BOOKSHELF("minecraft:bookshelf", 47),
    MOSSY_COBBLESTONE("minecraft:mossy_cobblestone", 48),
    OBSIDIAN("minecraft:obsidian", 49),
    TORCH("minecraft:torch", 50, Torch.class),
    FIRE("minecraft:fire", 51),
    MOB_SPAWNER("minecraft:mob_spawner", 52),
    WOOD_STAIRS("minecraft:oak_stairs", 53, Stairs.class),
    CHEST("minecraft:chest", 54, Chest.class),
    REDSTONE_WIRE("minecraft:redstone_wire", 55, RedstoneWire.class),
    DIAMOND_ORE("minecraft:diamond_ore", 56),
    DIAMOND_BLOCK("minecraft:diamond_block", 57),
    WORKBENCH("minecraft:crafting_table", 58),
    CROPS("minecraft:wheat", 59, Crops.class),
    SOIL("minecraft:farmland", 60, MaterialData.class),
    FURNACE("minecraft:furnace", 61, Furnace.class),
    BURNING_FURNACE("minecraft:lit_furnace", 62, Furnace.class),
    SIGN_POST("minecraft:standing_sign", 63, 64, Sign.class),
    WOODEN_DOOR("minecraft:wooden_door", 64, Door.class),
    LADDER("minecraft:ladder", 65, Ladder.class),
    RAILS("minecraft:rail", 66, Rails.class),
    COBBLESTONE_STAIRS("minecraft:stone_stairs", 67, Stairs.class),
    WALL_SIGN("minecraft:wall_sign", 68, 64, Sign.class),
    LEVER("minecraft:lever", 69, Lever.class),
    STONE_PLATE("minecraft:stone_pressure_plate", 70, PressurePlate.class),
    IRON_DOOR_BLOCK("minecraft:iron_door", 71, Door.class),
    WOOD_PLATE("minecraft:wooden_pressure_plate", 72, PressurePlate.class),
    REDSTONE_ORE("minecraft:redstone_ore", 73),
    GLOWING_REDSTONE_ORE("minecraft:lit_redstone_ore", 74),
    REDSTONE_TORCH_OFF("minecraft:unlit_redstone_torch", 75, RedstoneTorch.class),
    REDSTONE_TORCH_ON("minecraft:redstone_torch", 76, RedstoneTorch.class),
    STONE_BUTTON("minecraft:stone_button", 77, Button.class),
    SNOW("minecraft:snow_layer", 78),
    ICE("minecraft:ice", 79),
    SNOW_BLOCK("minecraft:snow", 80),
    CACTUS("minecraft:cactus", 81, MaterialData.class),
    CLAY("minecraft:clay", 82),
    SUGAR_CANE_BLOCK("minecraft:reeds", 83, MaterialData.class),
    JUKEBOX("minecraft:jukebox", 84),
    FENCE("minecraft:fence", 85),
    PUMPKIN("minecraft:pumpkin", 86, Pumpkin.class),
    NETHERRACK("minecraft:netherrack", 87),
    SOUL_SAND("minecraft:soul_sand", 88),
    GLOWSTONE("minecraft:glowstone", 89),
    PORTAL("minecraft:portal", 90),
    JACK_O_LANTERN("minecraft:lit_pumpkin", 91, Pumpkin.class),
    CAKE_BLOCK("minecraft:cake", 92, 64, Cake.class),
    DIODE_BLOCK_OFF("minecraft:unpowered_repeater", 93, Diode.class),
    DIODE_BLOCK_ON("minecraft:powered_repeater", 94, Diode.class),
    @Deprecated
    LOCKED_CHEST("minecraft:chest_locked_aprilfools_super_old_legacy_we_should_not_even_have_this", 95),
    STAINED_GLASS("minecraft:stained_glass", 95),
    TRAP_DOOR("minecraft:trapdoor", 96, TrapDoor.class),
    MONSTER_EGGS("minecraft:monster_egg", 97, MonsterEggs.class),
    SMOOTH_BRICK("minecraft:stonebrick", 98, SmoothBrick.class),
    HUGE_MUSHROOM_1("minecraft:brown_mushroom_block", 99, Mushroom.class),
    HUGE_MUSHROOM_2("minecraft:red_mushroom_block", 100, Mushroom.class),
    IRON_FENCE("minecraft:iron_bars", 101),
    THIN_GLASS("minecraft:glass_pane", 102),
    MELON_BLOCK("minecraft:melon_block", 103),
    PUMPKIN_STEM("minecraft:pumpkin_stem", 104, MaterialData.class),
    MELON_STEM("minecraft:melon_stem", 105, MaterialData.class),
    VINE("minecraft:vine", 106, Vine.class),
    FENCE_GATE("minecraft:fence_gate", 107, Gate.class),
    BRICK_STAIRS("minecraft:brick_stairs", 108, Stairs.class),
    SMOOTH_STAIRS("minecraft:stone_brick_stairs", 109, Stairs.class),
    MYCEL("minecraft:mycelium", 110),
    WATER_LILY("minecraft:waterlily", 111),
    NETHER_BRICK("minecraft:nether_brick", 112),
    NETHER_FENCE("minecraft:nether_brick_fence", 113),
    NETHER_BRICK_STAIRS("minecraft:nether_brick_stairs", 114, Stairs.class),
    NETHER_WARTS("minecraft:nether_wart", 115, NetherWarts.class),
    ENCHANTMENT_TABLE("minecraft:enchanting_table", 116),
    BREWING_STAND("minecraft:brewing_stand", 117, MaterialData.class),
    CAULDRON("minecraft:cauldron", 118, Cauldron.class),
    ENDER_PORTAL("minecraft:end_portal", 119),
    ENDER_PORTAL_FRAME("minecraft:end_portal_frame", 120),
    ENDER_STONE("minecraft:end_stone", 121),
    DRAGON_EGG("minecraft:dragon_egg", 122),
    REDSTONE_LAMP_OFF("minecraft:redstone_lamp", 123),
    REDSTONE_LAMP_ON("minecraft:lit_redstone_lamp", 124),
    WOOD_DOUBLE_STEP("minecraft:double_wooden_slab", 125, WoodenStep.class),
    WOOD_STEP("minecraft:wooden_slab", 126, WoodenStep.class),
    COCOA("minecraft:cocoa", 127, CocoaPlant.class),
    SANDSTONE_STAIRS("minecraft:sandstone_stairs", 128, Stairs.class),
    EMERALD_ORE("minecraft:emerald_ore", 129),
    ENDER_CHEST("minecraft:ender_chest", 130, EnderChest.class),
    TRIPWIRE_HOOK("minecraft:tripwire_hook", 131, TripwireHook.class),
    TRIPWIRE("minecraft:tripwire", 132, Tripwire.class),
    EMERALD_BLOCK("minecraft:emerald_block", 133),
    SPRUCE_WOOD_STAIRS("minecraft:spruce_stairs", 134, Stairs.class),
    BIRCH_WOOD_STAIRS("minecraft:birch_stairs", 135, Stairs.class),
    JUNGLE_WOOD_STAIRS("minecraft:jungle_stairs", 136, Stairs.class),
    COMMAND("minecraft:command_block", 137, Command.class),
    BEACON("minecraft:beacon", 138),
    COBBLE_WALL("minecraft:cobblestone_wall", 139),
    FLOWER_POT("minecraft:flower_pot", 140, FlowerPot.class),
    CARROT("minecraft:carrots", 141),
    POTATO("minecraft:potatoes", 142),
    WOOD_BUTTON("minecraft:wooden_button", 143, Button.class),
    SKULL("minecraft:skull", 144, Skull.class),
    ANVIL("minecraft:anvil", 145),
    TRAPPED_CHEST("minecraft:trapped_chest", 146),
    GOLD_PLATE("minecraft:light_weighted_pressure_plate", 147),
    IRON_PLATE("minecraft:heavy_weighted_pressure_plate", 148),
    REDSTONE_COMPARATOR_OFF("minecraft:unpowered_comparator", 149),
    REDSTONE_COMPARATOR_ON("minecraft:powered_comparator", 150),
    DAYLIGHT_DETECTOR("minecraft:daylight_detector", 151),
    REDSTONE_BLOCK("minecraft:redstone_block", 152),
    QUARTZ_ORE("minecraft:quartz_ore", 153),
    HOPPER("minecraft:hopper", 154),
    QUARTZ_BLOCK("minecraft:quartz_block", 155),
    QUARTZ_STAIRS("minecraft:quartz_stairs", 156, Stairs.class),
    ACTIVATOR_RAIL("minecraft:activator_rail", 157, PoweredRail.class),
    DROPPER("minecraft:dropper", 158, Dispenser.class),
    STAINED_CLAY("minecraft:stained_hardened_clay", 159),
    STAINED_GLASS_PANE("minecraft:stained_glass_pane", 160),
    LEAVES_2("minecraft:leaves2", 161),
    LOG_2("minecraft:log2", 162),
    ACACIA_STAIRS("minecraft:acacia_stairs", 163),
    DARK_OAK_STAIRS("minecraft:dark_oak_stair", 164),
    HAY_BLOCK("minecraft:hay_block", 170),
    CARPET("minecraft:carpet", 171),
    HARD_CLAY("minecraft:hardened_clay", 172),
    COAL_BLOCK("minecraft:coal_block", 173),
    PACKED_ICE("minecraft:packed_ice", 174),
    DOUBLE_PLANT("minecraft:dobule_plant", 175),
    // ----- Item Separator -----
    IRON_SPADE("minecraft:iron_shovel", 256, 1, 250),
    IRON_PICKAXE("minecraft:iron_pickaxe", 257, 1, 250),
    IRON_AXE("minecraft:iron_axe", 258, 1, 250),
    FLINT_AND_STEEL("minecraft:flint_and_steel", 259, 1, 64),
    APPLE("minecraft:apple", 260),
    BOW("minecraft:bow", 261, 1, 384),
    ARROW("minecraft:arrow", 262),
    COAL("minecraft:coal", 263, Coal.class),
    DIAMOND("minecraft:diamond", 264),
    IRON_INGOT("minecraft:iron_ingot", 265),
    GOLD_INGOT("minecraft:gold_ingot", 266),
    IRON_SWORD("minecraft:iron_sword", 267, 1, 250),
    WOOD_SWORD("minecraft:wooden_sword", 268, 1, 59),
    WOOD_SPADE("minecraft:wooden_shovel", 269, 1, 59),
    WOOD_PICKAXE("minecraft:wooden_pickaxe", 270, 1, 59),
    WOOD_AXE("minecraft:wooden_axe", 271, 1, 59),
    STONE_SWORD("minecraft:stone_sword", 272, 1, 131),
    STONE_SPADE("minecraft:stone_shovel", 273, 1, 131),
    STONE_PICKAXE("minecraft:stone_pickaxe", 274, 1, 131),
    STONE_AXE("minecraft:stone_axe", 275, 1, 131),
    DIAMOND_SWORD("minecraft:diamond_sword", 276, 1, 1561),
    DIAMOND_SPADE("minecraft:diamond_shovel", 277, 1, 1561),
    DIAMOND_PICKAXE("minecraft:diamond_pickaxe", 278, 1, 1561),
    DIAMOND_AXE("minecraft:diamond_axe", 279, 1, 1561),
    STICK("minecraft:stick", 280),
    BOWL("minecraft:bowl", 281),
    MUSHROOM_SOUP("minecraft:mushroom_stew", 282, 1),
    GOLD_SWORD("minecraft:golden_sword", 283, 1, 32),
    GOLD_SPADE("minecraft:golden_shovel", 284, 1, 32),
    GOLD_PICKAXE("minecraft:golden_pickaxe", 285, 1, 32),
    GOLD_AXE("minecraft:golden_axe", 286, 1, 32),
    STRING("minecraft:string", 287),
    FEATHER("minecraft:feather", 288),
    SULPHUR("minecraft:gunpowder", 289),
    WOOD_HOE("minecraft:wooden_hoe", 290, 1, 59),
    STONE_HOE("minecraft:stone_hoe", 291, 1, 131),
    IRON_HOE("minecraft:iron_hoe", 292, 1, 250),
    DIAMOND_HOE("minecraft:diamond_hoe", 293, 1, 1561),
    GOLD_HOE("minecraft:golden_hoe", 294, 1, 32),
    SEEDS("minecraft:wheat_seeds", 295),
    WHEAT("minecraft:wheat", 296),
    BREAD("minecraft:bread", 297),
    LEATHER_HELMET("minecraft:leather_helmet", 298, 1, 55),
    LEATHER_CHESTPLATE("minecraft:leather_chestplate", 299, 1, 80),
    LEATHER_LEGGINGS("minecraft:leather_leggings", 300, 1, 75),
    LEATHER_BOOTS("minecraft:leather_boots", 301, 1, 65),
    CHAINMAIL_HELMET("minecraft:chainmail_helmet", 302, 1, 165),
    CHAINMAIL_CHESTPLATE("minecraft:chainmail_chestplate", 303, 1, 240),
    CHAINMAIL_LEGGINGS("minecraft:chainmail_leggings", 304, 1, 225),
    CHAINMAIL_BOOTS("minecraft:chainmail_boots", 305, 1, 195),
    IRON_HELMET("minecraft:iron_helmet", 306, 1, 165),
    IRON_CHESTPLATE("minecraft:iron_chestplate", 307, 1, 240),
    IRON_LEGGINGS("minecraft:iron_leggings", 308, 1, 225),
    IRON_BOOTS("minecraft:iron_boots", 309, 1, 195),
    DIAMOND_HELMET("minecraft:diamond_helmet", 310, 1, 363),
    DIAMOND_CHESTPLATE("minecraft:diamond_chestplate", 311, 1, 528),
    DIAMOND_LEGGINGS("minecraft:diamond_leggings", 312, 1, 495),
    DIAMOND_BOOTS("minecraft:diamond_boots", 313, 1, 429),
    GOLD_HELMET("minecraft:golden_helmet", 314, 1, 77),
    GOLD_CHESTPLATE("minecraft:golden_chestplate", 315, 1, 112),
    GOLD_LEGGINGS("minecraft:golden_leggings", 316, 1, 105),
    GOLD_BOOTS("minecraft:golden_boots", 317, 1, 91),
    FLINT("minecraft:flint", 318),
    PORK("minecraft:porkchop", 319),
    GRILLED_PORK("minecraft:cooked_porkchop", 320),
    PAINTING("minecraft:painting", 321),
    GOLDEN_APPLE("minecraft:golden_apple", 322),
    SIGN("minecraft:sign", 323, 16),
    WOOD_DOOR("minecraft:wooden_door", 324, 1),
    BUCKET("minecraft:bucket", 325, 16),
    WATER_BUCKET("minecraft:water_bucket", 326, 1),
    LAVA_BUCKET("minecraft:lava_bucket", 327, 1),
    MINECART("minecraft:minecart", 328, 1),
    SADDLE("minecraft:saddle", 329, 1),
    IRON_DOOR("minecraft:iron_door", 330, 1),
    REDSTONE("minecraft:redstone", 331),
    SNOW_BALL("minecraft:snowball", 332, 16),
    BOAT("minecraft:boat", 333, 1),
    LEATHER("minecraft:leather", 334),
    MILK_BUCKET("minecraft:milk_bucket", 335, 1),
    CLAY_BRICK("minecraft:brick", 336),
    CLAY_BALL("minecraft:clay_ball", 337),
    SUGAR_CANE("minecraft:reeds", 338),
    PAPER("minecraft:paper", 339),
    BOOK("minecraft:book", 340),
    SLIME_BALL("minecraft:slime_ball", 341),
    STORAGE_MINECART("minecraft:chest_minecart", 342, 1),
    POWERED_MINECART("minecraft:furnace_minecart", 343, 1),
    EGG("minecraft:egg", 344, 16),
    COMPASS("minecraft:compass", 345),
    FISHING_ROD("minecraft:fishing_rod", 346, 1, 64),
    WATCH("minecraft:clock", 347),
    GLOWSTONE_DUST("minecraft:glowstone_dust", 348),
    RAW_FISH("minecraft:fish", 349),
    COOKED_FISH("minecraft:cooked_fish", 350),
    INK_SACK("minecraft:dye", 351, Dye.class),
    BONE("minecraft:bone", 352),
    SUGAR("minecraft:sugar", 353),
    CAKE("minecraft:cake", 354, 1),
    BED("minecraft:bed", 355, 1),
    DIODE("minecraft:repeater", 356),
    COOKIE("minecraft:cookie", 357),
    /**
     * @see MapView
     */
    MAP("minecraft:filled_map", 358, MaterialData.class),
    SHEARS("minecraft:shears", 359, 1, 238),
    MELON("minecraft:melon", 360),
    PUMPKIN_SEEDS("minecraft:pumpkin_seeds", 361),
    MELON_SEEDS("minecraft:melon_seeds", 362),
    RAW_BEEF("minecraft:beef", 363),
    COOKED_BEEF("minecraft:cooked_beef", 364),
    RAW_CHICKEN("minecraft:chicken", 365),
    COOKED_CHICKEN("minecraft:cooked_chicken", 366),
    ROTTEN_FLESH("minecraft:rotten_flesh", 367),
    ENDER_PEARL("minecraft:ender_pearl", 368, 16),
    BLAZE_ROD("minecraft:blaze_rod", 369),
    GHAST_TEAR("minecraft:ghast_tear", 370),
    GOLD_NUGGET("minecraft:gold_nugget", 371),
    NETHER_STALK("minecraft:nether_wart", 372),
    /**
     * @see Potion
     */
    POTION("minecraft:potion", 373, 1, MaterialData.class),
    GLASS_BOTTLE("minecraft:glass_bottle", 374),
    SPIDER_EYE("minecraft:spider_eye", 375),
    FERMENTED_SPIDER_EYE("minecraft:fermented_spider_eye", 376),
    BLAZE_POWDER("minecraft:blaze_powder", 377),
    MAGMA_CREAM("minecraft:magma_cream", 378),
    BREWING_STAND_ITEM("minecraft:brewing_stand", 379),
    CAULDRON_ITEM("minecraft:cauldron", 380),
    EYE_OF_ENDER("minecraft:ender_eye", 381),
    SPECKLED_MELON("minecraft:speckled_melon", 382),
    MONSTER_EGG("minecraft:spawn_egg", 383, 64, SpawnEgg.class),
    EXP_BOTTLE("minecraft:experience_bottle", 384, 64),
    FIREBALL("minecraft:fire_charge", 385, 64),
    BOOK_AND_QUILL("minecraft:writable_book", 386, 1),
    WRITTEN_BOOK("minecraft:written_book", 387, 16),
    EMERALD("minecraft:emerald", 388, 64),
    ITEM_FRAME("minecraft:item_frame", 389),
    FLOWER_POT_ITEM("minecraft:flower_pot", 390),
    CARROT_ITEM("minecraft:carrot", 391),
    POTATO_ITEM("minecraft:potato", 392),
    BAKED_POTATO("minecraft:baked_potato", 393),
    POISONOUS_POTATO("minecraft:poisonous_potato", 394),
    EMPTY_MAP("minecraft:map", 395),
    GOLDEN_CARROT("minecraft:golden_carrot", 396),
    SKULL_ITEM("minecraft:skull", 397),
    CARROT_STICK("minecraft:carrot_on_a_stick", 398, 1, 25),
    NETHER_STAR("minecraft:nether_star", 399),
    PUMPKIN_PIE("minecraft:pumpkin_pie", 400),
    FIREWORK("minecraft:fireworks", 401),
    FIREWORK_CHARGE("minecraft:firework_charge", 402),
    ENCHANTED_BOOK("minecraft:enchanted_book", 403, 1),
    REDSTONE_COMPARATOR("minecraft:comparator", 404),
    NETHER_BRICK_ITEM("minecraft:netherbrick", 405),
    QUARTZ("minecraft:quartz", 406),
    EXPLOSIVE_MINECART("minecraft:tnt_minecart", 407, 1),
    HOPPER_MINECART("minecraft:hopper_minecart", 408, 1),
    IRON_BARDING("minecraft:iron_horse_armor", 417, 1),
    GOLD_BARDING("minecraft:golden_horse_armor", 418, 1),
    DIAMOND_BARDING("minecraft:diamond_horse_armor", 419, 1),
    LEASH("minecraft:lead", 420),
    NAME_TAG("minecraft:name_tag", 421),
    COMMAND_MINECART("minecraft:command_block_minecart", 422, 1),
    GOLD_RECORD("minecraft:record_13", 2256, 1),
    GREEN_RECORD("minecraft:record_cat", 2257, 1),
    RECORD_3("minecraft:record_blocks", 2258, 1),
    RECORD_4("minecraft:record_chirp", 2259, 1),
    RECORD_5("minecraft:record_far", 2260, 1),
    RECORD_6("minecraft:record_mall", 2261, 1),
    RECORD_7("minecraft:record_mellohi", 2262, 1),
    RECORD_8("minecraft:record_stal", 2263, 1),
    RECORD_9("minecraft:record_strad", 2264, 1),
    RECORD_10("minecraft:record_ward", 2265, 1),
    RECORD_11("minecraft:record_11", 2266, 1),
    RECORD_12("minecraft:record_wait", 2267, 1),
    ;

    private final int id;
    private final Constructor<? extends MaterialData> ctor;
    private static Material[] byId = new Material[383];
    private final static Map<String, Material> BY_NAME = Maps.newHashMap();
    private final static Map<String, Material> ITEM_BY_STRING_ID = Maps.newHashMap();
    private final static Map<String, Material> BLOCK_BY_STRING_ID = Maps.newHashMap();
    private final int maxStack;
    private final short durability;
    private final String stringId;

    private Material(final String stringId,final int id) {
        this(stringId, id, 64);
    }

    private Material(final String stringId,final int id, final int stack) {
        this(stringId, id, stack, MaterialData.class);
    }

    private Material(final String stringId,final int id, final int stack, final int durability) {
        this(stringId, id, stack, durability, MaterialData.class);
    }

    private Material(final String stringId,final int id, final Class<? extends MaterialData> data) {
        this(stringId, id, 64, data);
    }

    private Material(final String stringId,final int id, final int stack, final Class<? extends MaterialData> data) {
        this(stringId, id, stack, 0, data);
    }

    private Material(final String stringId,final int id, final int stack, final int durability, final Class<? extends MaterialData> data) {
        this.stringId = stringId;
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

    /**
     * Gets the item ID or block ID of this Material
     *
     * @return ID of this material
     * @deprecated Magic value
     */
    @Deprecated
    public int getId() {
        return id;
    }

    /**
     * Gets the String id of this material. Vanilla blocks follow the format minecraft:block_name_here
     * @return
     */
    public String getStringId() {
        return stringId;
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
     * @deprecated Magic value
     */
    @Deprecated
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
     * @deprecated Magic value
     */
    @Deprecated
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
     * of special characters in an attempt to format it like the enum.
     * <p>
     * Using this for match by ID is deprecated.
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

    /**
     * Attempts to get a material for a block using the vanilla string id (e.g. minecraft:cobblestone)
     * <b>This Material may not be safe for ItemStacks, use {@link #getItemMaterial(String)} for ItemStacks related operations.</b>  
     * @param stringId id to lookup 
     * @return Material of the block or null 
     */
    public static Material getBlockMaterial(String stringId){
        return BLOCK_BY_STRING_ID.get(stringId);
    }

    /**
     * Attempts to get a material for a item using the vanilla string id (e.g. minecraft:cobblestone)
     * <b>This Material is safe to use in ItemStacks, but may not work as a block, use {@link #getBlockMaterial(String)} for block related operations.</b>
     * @param stringId id to lookup 
     * @return Material of the item or null 
     */
    public static Material getItemMaterial(String stringId){
        return ITEM_BY_STRING_ID.get(stringId);
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
            if(material.isBlock()){
                BLOCK_BY_STRING_ID.put(material.getStringId(), material);
                //Do not overwrite an item that has the same String id, 
                if(!ITEM_BY_STRING_ID.containsKey(material.getStringId())){
                    ITEM_BY_STRING_ID.put(material.getStringId(), material);
                }
            }
            else
            {
                ITEM_BY_STRING_ID.put(material.getStringId(), material);
            }
            
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
            case STAINED_GLASS:
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
            case STAINED_GLASS_PANE:
            case LEAVES_2:
            case LOG_2:
            case ACACIA_STAIRS:
            case DARK_OAK_STAIRS:
            case PACKED_ICE:
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
            case DOUBLE_PLANT:
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
            case LEAVES_2:
            case LOG_2:
            case ACACIA_STAIRS:
            case DARK_OAK_STAIRS:
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
            case YELLOW_FLOWER:
            case RED_ROSE:
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
            case LEAVES_2:
            case LOG_2:
            case CARPET:
            case DOUBLE_PLANT:
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
            case LOG_2:
            case PACKED_ICE:
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
