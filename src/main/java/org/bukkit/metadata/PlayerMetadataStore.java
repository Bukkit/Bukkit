package org.bukkit.metadata;

import org.bukkit.OfflinePlayer;

/**
 * A PlayerMetadataStore stores metadata for {@link org.bukkit.entity.Player} and {@link OfflinePlayer} objects.
 */
public class PlayerMetadataStore extends MetadataStoreBase<OfflinePlayer> implements MetadataStore<OfflinePlayer> {
    /**
     * Generates a unique metadata key for {@link org.bukkit.entity.Player} and {@link OfflinePlayer} using the player
     * name.
     * @see MetadataStoreBase#Disambiguate(Object, String)
     * @param player
     * @param metadataKey The name identifying the metadata value
     * @return
     */
    @Override
    protected String Disambiguate(OfflinePlayer player, String metadataKey) {
        return player.getName().toLowerCase() + ":" + metadataKey;
    }
}
