package org.bukkit.metadata;

import org.bukkit.block.Block;

/**
 * A BlockMetadataStore stores metadata values for {@link Block} objects.
 */
public class BlockMetadataStore extends MetadataStoreBase<Block> implements MetadataStore<Block> {
    @Override
    protected String Disambiguate(Block block, String metadataKey) {
        return Integer.toString(block.getX()) + ":" + Integer.toString(block.getY()) + ":"  + Integer.toString(block.getZ()) + ":"  + metadataKey;
    }
}
