package org.bukkit.metadata;

import org.bukkit.OfflinePlayer;

/**
 */
public class PlayerMetadataStore extends MetadataStoreBase<OfflinePlayer> implements MetadataStore<OfflinePlayer> {
    @Override
    protected String Disambiguate(OfflinePlayer player, String metadataKey) {
        return player.getName().toLowerCase() + ":" + metadataKey;
    }
}
