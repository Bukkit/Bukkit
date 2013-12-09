package org.bukkit.entity;

import org.bukkit.Achievement;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class TestPlayer implements Player {
    private String locale = "de_DE";

    @Override
    public String getDisplayName() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setDisplayName(String name) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public String getPlayerListName() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setPlayerListName(String name) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setCompassTarget(Location loc) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Location getCompassTarget() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public InetSocketAddress getAddress() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isConversing() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void acceptConversationInput(String input) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void abandonConversation(Conversation conversation) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void sendRawMessage(String message) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void kickPlayer(String message) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void chat(String msg) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean performCommand(String command) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isSneaking() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setSneaking(boolean sneak) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isSprinting() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setSprinting(boolean sprinting) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void saveData() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void loadData() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setSleepingIgnored(boolean isSleeping) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isSleepingIgnored() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void playNote(Location loc, byte instrument, byte note) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void playNote(Location loc, Instrument instrument, Note note) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void playEffect(Location loc, Effect effect, int data) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public <T> void playEffect(Location loc, Effect effect, T data) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void sendBlockChange(Location loc, Material material, byte data) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean sendChunkChange(Location loc, int sx, int sy, int sz, byte[] data) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void sendBlockChange(Location loc, int material, byte data) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void sendMap(MapView map) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void updateInventory() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void awardAchievement(Achievement achievement) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void incrementStatistic(Statistic statistic) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void incrementStatistic(Statistic statistic, int amount) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material, int amount) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setPlayerTime(long time, boolean relative) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public long getPlayerTime() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public long getPlayerTimeOffset() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isPlayerTimeRelative() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void resetPlayerTime() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setPlayerWeather(WeatherType type) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public WeatherType getPlayerWeather() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void resetPlayerWeather() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void giveExp(int amount) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void giveExpLevels(int amount) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public float getExp() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setExp(float exp) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int getLevel() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setLevel(int level) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int getTotalExperience() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setTotalExperience(int exp) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public float getExhaustion() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setExhaustion(float value) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public float getSaturation() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setSaturation(float value) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int getFoodLevel() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setFoodLevel(int value) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Location getBedSpawnLocation() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setBedSpawnLocation(Location location) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setBedSpawnLocation(Location location, boolean force) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean getAllowFlight() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setAllowFlight(boolean flight) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void hidePlayer(Player player) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void showPlayer(Player player) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean canSee(Player player) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Location getLocation() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Location getLocation(Location loc) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setVelocity(Vector velocity) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Vector getVelocity() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isOnGround() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public World getWorld() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean teleport(Location location) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean teleport(Entity destination) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean teleport(Entity destination, PlayerTeleportEvent.TeleportCause cause) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public List<Entity> getNearbyEntities(double x, double y, double z) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int getEntityId() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int getFireTicks() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int getMaxFireTicks() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setFireTicks(int ticks) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isDead() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Server getServer() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Entity getPassenger() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean setPassenger(Entity passenger) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean eject() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public float getFallDistance() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setFallDistance(float distance) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent event) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public UUID getUniqueId() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int getTicksLived() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setTicksLived(int value) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void playEffect(EntityEffect type) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public EntityType getType() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isInsideVehicle() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean leaveVehicle() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Entity getVehicle() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isFlying() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setFlying(boolean value) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setFlySpeed(float value) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setWalkSpeed(float value) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public float getFlySpeed() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public float getWalkSpeed() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setTexturePack(String url) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Scoreboard getScoreboard() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isHealthScaled() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setHealthScaled(boolean scale) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setHealthScale(double scale) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public double getHealthScale() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public String getLocale() {
        return locale;
    }

    @Override
    public void sendMessage(String message) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void sendMessage(String[] messages) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Map<String, Object> serialize() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isOnline() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isBanned() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setBanned(boolean banned) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isWhitelisted() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setWhitelisted(boolean value) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Player getPlayer() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public long getFirstPlayed() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public long getLastPlayed() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean hasPlayedBefore() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public PlayerInventory getInventory() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Inventory getEnderChest() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean setWindowProperty(InventoryView.Property prop, int value) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public InventoryView getOpenInventory() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public InventoryView openInventory(Inventory inventory) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public InventoryView openWorkbench(Location location, boolean force) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public InventoryView openEnchanting(Location location, boolean force) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void openInventory(InventoryView inventory) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void closeInventory() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public ItemStack getItemInHand() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setItemInHand(ItemStack item) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public ItemStack getItemOnCursor() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setItemOnCursor(ItemStack item) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isSleeping() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int getSleepTicks() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public GameMode getGameMode() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setGameMode(GameMode mode) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isBlocking() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int getExpToLevel() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public double getEyeHeight() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public double getEyeHeight(boolean ignoreSneaking) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Location getEyeLocation() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Block getTargetBlock(HashSet<Byte> transparent, int maxDistance) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(HashSet<Byte> transparent, int maxDistance) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Egg throwEgg() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Snowball throwSnowball() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Arrow shootArrow() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int getRemainingAir() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setRemainingAir(int ticks) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int getMaximumAir() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setMaximumAir(int ticks) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int getMaximumNoDamageTicks() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setMaximumNoDamageTicks(int ticks) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public double getLastDamage() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int _INVALID_getLastDamage() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setLastDamage(double damage) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void _INVALID_setLastDamage(int damage) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int getNoDamageTicks() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setNoDamageTicks(int ticks) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Player getKiller() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect, boolean force) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> effects) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType type) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void removePotionEffect(PotionEffectType type) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean hasLineOfSight(Entity other) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setRemoveWhenFarAway(boolean remove) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public EntityEquipment getEquipment() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setCanPickupItems(boolean pickup) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean getCanPickupItems() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setCustomName(String name) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public String getCustomName() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isCustomNameVisible() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isLeashed() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean setLeashHolder(Entity holder) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void damage(double amount) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void _INVALID_damage(int amount) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void damage(double amount, Entity source) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void _INVALID_damage(int amount, Entity source) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public double getHealth() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int _INVALID_getHealth() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setHealth(double health) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void _INVALID_setHealth(int health) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public double getMaxHealth() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public int _INVALID_getMaxHealth() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setMaxHealth(double health) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void _INVALID_setMaxHealth(int health) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void resetMaxHealth() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isPermissionSet(String name) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean hasPermission(String name) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean hasPermission(Permission perm) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void recalculatePermissions() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public boolean isOp() {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void setOp(boolean value) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
