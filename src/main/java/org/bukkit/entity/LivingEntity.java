package org.bukkit.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Represents a living entity, such as a monster or player
 */
public interface LivingEntity extends Entity {

    /**
     * Gets the entity's health from 0 to {@link #getMaxHealth()}, where 0 is dead
     *
     * @return Health represented from 0 to max
     */
    public int getHealth();

    /**
     * Sets the entity's health from 0 to {@link #getMaxHealth()}, where 0 is dead
     *
     * @param health New health represented from 0 to max
     * @throws IllegalArgumentException Thrown if the health is < 0 or > max
     */
    public void setHealth(int health);

    /**
     * Gets the maximum health this entity may have
     *
     * @return Maximum health
     */
    public int getMaxHealth();

    /**
     * Gets the height of the entity's head above its Location
     *
     * @return Height of the entity's eyes above its Location
     */
    public double getEyeHeight();

    /**
     * Gets the height of the entity's head above its Location
     *
     * @param ignoreSneaking If set to true, the effects of sneaking will be ignored
     * @return Height of the entity's eyes above its Location
     */
    public double getEyeHeight(boolean ignoreSneaking);

    /**
     * Get a Location detailing the current eye position of the LivingEntity.
     *
     * @return a Location at the eyes of the LivingEntity.
     */
    public Location getEyeLocation();

    /**
     * Gets all blocks along the player's line of sight
     * List iterates from player's position to target inclusive
     *
     * @param transparent HashSet containing all transparent block IDs. If set to null only air is considered transparent.
     * @param maxDistance This is the maximum distance to scan. This may be further limited by the server, but never to less than 100 blocks.
     * @return List containing all blocks along the player's line of sight
     */
    public List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance);

    /**
     * Gets the block that the player has targeted
     *
     * @param transparent HashSet containing all transparent block IDs. If set to null only air is considered transparent.
     * @param maxDistance This is the maximum distance to scan. This may be further limited by the server, but never to less than 100 blocks.
     * @return Block that the player has targeted
     */
    public Block getTargetBlock(HashSet<Byte> transparent, int maxDistance);

    /**
     * Gets the last two blocks along the player's line of sight.
     * The target block will be the last block in the list.
     *
     * @param transparent HashSet containing all transparent block IDs. If set to null only air is considered transparent.
     * @param maxDistance This is the maximum distance to scan. This may be further limited by the server, but never to less than 100 blocks
     * @return List containing the last 2 blocks along the player's line of sight
     */
    public List<Block> getLastTwoTargetBlocks(HashSet<Byte> transparent, int maxDistance);

    /**
     * Throws an egg from the entity.
     *
     * @deprecated Use launchProjectile(Egg.class) instead
     * @return The egg thrown.
     */
    @Deprecated
    public Egg throwEgg();

    /**
     * Throws a snowball from the entity.
     *
     * @deprecated Use launchProjectile(Snowball.class) instead
     * @return The snowball thrown.
     */
    @Deprecated
    public Snowball throwSnowball();

    /**
     * Shoots an arrow from the entity.
     *
     * @deprecated Use launchProjectile(Arrow.class) instead
     * @return The arrow shot.
     */
    @Deprecated
    public Arrow shootArrow();

    /**
     * Launches a {@link Projectile} from the entity.
     *
     * @param projectile Class of the projectile to launch
     *
     * @return The launched projectile.
     */
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile);

    /**
     * Returns the amount of air that this entity has remaining, in ticks
     *
     * @return Amount of air remaining
     */
    public int getRemainingAir();

    /**
     * Sets the amount of air that this entity has remaining, in ticks
     *
     * @param ticks Amount of air remaining
     */
    public void setRemainingAir(int ticks);

    /**
     * Returns the maximum amount of air this entity can have, in ticks
     *
     * @return Maximum amount of air
     */
    public int getMaximumAir();

    /**
     * Sets the maximum amount of air this entity can have, in ticks
     *
     * @param ticks Maximum amount of air
     */
    public void setMaximumAir(int ticks);

    /**
     * Deals the given amount of damage to this entity
     *
     * @param amount Amount of damage to deal
     */
    public void damage(int amount);

    /**
     * Deals the given amount of damage to this entity, from a specified entity
     *
     * @param amount Amount of damage to deal
     * @param source Entity which to attribute this damage from
     */
    public void damage(int amount, Entity source);

    /**
     * Returns the entities current maximum noDamageTicks
     * This is the time in ticks the entity will become unable to take
     * equal or less damage than the lastDamage
     *
     * @return noDamageTicks
     */
    public int getMaximumNoDamageTicks();

    /**
     * Sets the entities current maximum noDamageTicks
     *
     * @param ticks maximumNoDamageTicks
     */
    public void setMaximumNoDamageTicks(int ticks);

    /**
     * Returns the entities lastDamage taken in the current noDamageTicks time.
     * Only damage higher than this amount will further damage the entity.
     *
     * @return lastDamage
     */
    public int getLastDamage();

    /**
     * Sets the entities current maximum noDamageTicks
     *
     * @param damage last damage
     */
    public void setLastDamage(int damage);

    /**
     * Returns the entities current noDamageTicks
     *
     * @return noDamageTicks
     */
    public int getNoDamageTicks();

    /**
     * Sets the entities current noDamageTicks
     *
     * @param ticks NoDamageTicks
     */
    public void setNoDamageTicks(int ticks);

    /**
     * Gets the player identified as the killer of this entity.
     * <p />
     * May be null.
     *
     * @return Killer player, or null if none found.
     */
    public Player getKiller();

    /**
     * Adds the given {@link PotionEffect} to this entity.
     * Only one potion effect can be present for a given {@link PotionEffectType}.
     *
     * @param effect PotionEffect to be added
     * @return Whether the effect could be added
     */
    public boolean addPotionEffect(PotionEffect effect);

    /**
     * Adds the given {@link PotionEffect} to this entity.
     * Only one potion effect can be present for a given {@link PotionEffectType}.
     *
     * @param effect PotionEffect to be added
     * @param force Whether conflicting effects should be removed
     * @return Whether the effect could be added
     */
    public boolean addPotionEffect(PotionEffect effect, boolean force);

    /**
     * Attempts to add all of the given {@link PotionEffect} to this entity.
     *
     * @param effects The effects to add
     * @return Whether all of the effects could be added
     */
    public boolean addPotionEffects(Collection<PotionEffect> effects);

    /**
     * Returns whether the entity already has an existing
     * effect of the given {@link PotionEffectType} applied to it.
     *
     * @param type The potion type to check
     * @return Whether the player has this potion effect active on them.
     */
    public boolean hasPotionEffect(PotionEffectType type);

    /**
     * Removes any effects present of the given {@link PotionEffectType}.
     *
     * @param type The potion type to remove
     */
    public void removePotionEffect(PotionEffectType type);

    /**
     * Returns all currently active {@link PotionEffect}s on this entity.
     *
     * @return A collection of {@link PotionEffect}s
     */
    public Collection<PotionEffect> getActivePotionEffects();

    /**
     * Checks whether the entity has block line of sight to another.<br />
     * This uses the same algorithm that hostile mobs use to find the closest player.
     *
     * @param other The entity to determine line of sight to.
     * @return true if there is a line of sight, false if not.
     */
    public boolean hasLineOfSight(Entity other);
    
    /**
     * Get the ItemStack from the specified equipment slot.<br />
     * <br />
     * <UL>
     * <LI>Index 0 is Held</LI>
     * <LI>Index 1 is Boots</LI>
     * <LI>Index 2 is Leggings</LI>
     * <LI>Index 3 is Chestplate</LI>
     * <LI>Index 4 is Helmet</LI>
     * </UL>
     *
     * @return The ItemStack from the specified equipment slot
     * @param index The index of the equipment slot
     */
    public ItemStack getEquipment(int index);
    
    /**
     * Get the ItemStack from the specified armor slot.<br />
     * <br />
     * <UL>
     * <LI>Index 0 is Boots</LI>
     * <LI>Index 1 is Leggings</LI>
     * <LI>Index 2 is Chestplate</LI>
     * <LI>Index 3 is Helmet</LI>
     * </UL>
     *
     * @return The ItemStack from the specified armor slot
     * @param index The index of the armor slot
     */
    public ItemStack getArmor(int index);
    
    /**
     * Get all ItemStacks from the equipment slots.<br />
     * <br />
     * <UL>
     * <LI>Index 0 is Held</LI>
     * <LI>Index 1 is Boots</LI>
     * <LI>Index 2 is Leggings</LI>
     * <LI>Index 3 is Chestplate</LI>
     * <LI>Index 4 is Helmet</LI>
     * </UL>
     *
     * @return All the ItemStacks from the equipment slots
     */
    public ItemStack[] getEquipmentContents();
    
    /**
     * Get all ItemStacks from the armor slots.<br />
     * <br />
     * <UL>
     * <LI>Index 0 is Boots</LI>
     * <LI>Index 1 is Leggings</LI>
     * <LI>Index 2 is Chestplate</LI>
     * <LI>Index 3 is Helmet</LI>
     * </UL>
     *
     * @return All the ItemStacks from the armor slots
     */
    public ItemStack[] getArmorContents();

    /**
     * Return the ItemStack from the helmet slot
     *
     * @return The ItemStack in the helmet slot
     */
    public ItemStack getHelmet();

    /**
     * Return the ItemStack from the chestplate slot
     *
     * @return The ItemStack in the chestplate slot
     */
    public ItemStack getChestplate();

    /**
     * Return the ItemStack from the leggings slot
     *
     * @return The ItemStack in the leggings slot
     */
    public ItemStack getLeggings();

    /**
     * Return the ItemStack from the boots slot
     *
     * @return The ItemStack in the boots slot
     */
    public ItemStack getBoots();
    
    /**
     * Put the ItemStack into the specified equipment slot.<br />
     * The old item will be deleted.<br />
     * <br />
     * <UL>
     * <LI>Index 0 is Held</LI>
     * <LI>Index 1 is Boots</LI>
     * <LI>Index 2 is Leggings</LI>
     * <LI>Index 3 is Chestplate</LI>
     * <LI>Index 4 is Helmet</LI>
     * </UL>
     *
     * @param index The index of the equipment slot
     * @param item The ItemStack to put there
     */
    public void setEquipment(int index, ItemStack item);
    
    /**
     * Put the ItemStack into the specified armor slot.<br />
     * The old item will be deleted.<br />
     * <br />
     * <UL>
     * <LI>Index 0 is Boots</LI>
     * <LI>Index 1 is Leggings</LI>
     * <LI>Index 2 is Chestplate</LI>
     * <LI>Index 3 is Helmet</LI>
     * </UL>
     *
     * @param index The index of the armor slot
     * @param item The ItemStack to put there
     */
    public void setArmor(int index, ItemStack item);
    
    /**
     * Put the given ItemStacks into the equipment slots.<br />
     * <br />
     * <UL>
     * <LI>Index 0 is Held</LI>
     * <LI>Index 1 is Boots</LI>
     * <LI>Index 2 is Leggings</LI>
     * <LI>Index 3 is Chestplate</LI>
     * <LI>Index 4 is Helmet</LI>
     * </UL>
     *
     * @param items The ItemStacks to use as equipment
     */
    public void setEquipmentContents(ItemStack[] items);
    
    /**
     * Put the given ItemStacks into the armor slots<br />
     * <br />
     * <UL>
     * <LI>Index 0 is Boots</LI>
     * <LI>Index 1 is Leggings</LI>
     * <LI>Index 2 is Chestplate</LI>
     * <LI>Index 3 is Helmet</LI>
     * </UL>
     *
     * @param items The ItemStacks to use as armor
     */
    public void setArmorContents(ItemStack[] items);

    /**
     * Put the given ItemStack into the helmet slot
     * This does not check if the ItemStack is a helmet
     *
     * @param helmet The ItemStack to use as helmet
     */
    public void setHelmet(ItemStack helmet);

    /**
     * Put the given ItemStack into the chestplate slot
     * This does not check if the ItemStack is a chestplate
     *
     * @param chestplate The ItemStack to use as chestplate
     */
    public void setChestplate(ItemStack chestplate);

    /**
     * Put the given ItemStack into the leg slot
     * This does not check if the ItemStack is a pair of leggings
     *
     * @param leggings The ItemStack to use as leggings
     */
    public void setLeggings(ItemStack leggings);

    /**
     * Put the given ItemStack into the boots slot
     * This does not check if the ItemStack is a boots
     *
     * @param boots The ItemStack to use as boots
     */
    public void setBoots(ItemStack boots);
    
    /**
     * Returns the ItemStack currently in the hand of the entity, can be empty.
     * @return The ItemStack of the item the entity currently is holding.
     *
     */
    public ItemStack getItemInHand();

    /**
     * Sets the item to the given ItemStack, this will replace whatever the
     * entity was holding.
     *
     * @param item The ItemStack which will end up in the hand
     */
    public void setItemInHand(ItemStack item);
}
