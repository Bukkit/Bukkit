package org.bukkit.block;

import org.bukkit.potion.PotionEffectType;

public interface Beacon extends BlockState {

    /**
     * Gets primary effect of beacon
     *
     * @return PotionEffectType, which beacon gives
     */
    public PotionEffectType getPrimaryEffect();

    /**
     * Gets secondary effect of beacon
     *
     * @return PotionEffectType, which beacon gives
     */
    public PotionEffectType getSecondaryEffect();

    /**
     * Gets level of beacon
     *
     * @return Level
     */
    public int getLevel();

    /**
     * Sets primary effect of beacon
     *
     * @param effect PotionEffectType to set as primary effect
     */
    public void setPrimaryEffect(PotionEffectType effect);

    /**
     * Sets secondary effect of beacon
     *
     * @param effect PotionEffectType to set as secondary effect
     */
    public void setSecondaryEffect(PotionEffectType effect);

    /**
     * Sets level of beacon
     *
     * @param level Level
     */
    public void setLevel(int level);
	
}
