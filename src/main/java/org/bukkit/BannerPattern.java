package org.bukkit;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import org.apache.commons.lang.Validate;

import java.util.List;
import java.util.Map;

/**
 * Represents a <i>complete</i> pattern for a banner.
 */
public class BannerPattern {

    /**
     * Represents an individual pattern texture on a banner.<p/>
     */
    public static enum LayerTexture {
        BORDER("bo"),
        BOTTOM_HALF("hhb"),
        BRICK("bri"),
        CIRCLE("mc"),
        CREEPER("cre"),
        CURLY_BORDER("cbo"),
        DIAGONAL_CROSS("cr"),
        FLOWER("flo"),
        GRADIENT("gra"),
        GRADIENT_UP("gru"),
        LEFT_HALF("vh"),
        MOJANG("moj"),
        RIGHT_HALF("vhr"),
        RHOMBUS("mr"),
        SAW_BOTTOM("bts"),
        SAW_TOP("tts"),
        SKULL("sku"),
        STRIPES("ss"),
        SQUARE_BOTTOM_LEFT("bl"),
        SQUARE_BOTTOM_RIGHT("br"),
        SQUARE_TOP_LEFT("tl"),
        SQUARE_TOP_RIGHT("tr"),
        STRIPE_BOTTOM("bs"),
        STRAIGHT_CROSS("sc"),
        STRIPE_DOWN_LEFT("dls"),
        STRIPE_DOWN_RIGHT("drs"),
        STRIPE_LEFT("ls"),
        STRIPE_HORIZONTAL("ms"),
        STRIPE_RIGHT("rs"),
        STRIPE_TOP("ts"),
        STRIPE_VERTICAL("cs"),
        TOP_HALF("hh"),
        TRIANGLE_BOTTOM("bt"),
        TRIANGLE_BOTTOM_LEFT("lud"),
        TRIANGLE_BOTTOM_RIGHT("rd"),
        TRIANGLE_TOP("tt"),
        TRIANGLE_TOP_LEFT("ld"),
        TRIANGLE_TOP_RIGHT("rud");

        private final String code;
        private final static Map<String, LayerTexture> BY_CODE = Maps.newHashMap();

        private LayerTexture(String code) {
            this.code = code;
        }

        /**
         * Gets the internal code of this banner texture
         *
         * @return The 2 or 3 character pattern code
         * @deprecated Magic value
         */
        @Deprecated
        public String getCode() {
            return code;
        }

        /**
         * Gets a texture by the code
         *
         * @param code 2 or 3 character pattern code
         * @return Banner pattern
         * @deprecated Magic value
         */
        @Deprecated
        public static LayerTexture getByCode(String code) {
            return BY_CODE.get(code);
        }

        static {
            for (LayerTexture type : LayerTexture.values()) {
                BY_CODE.put(type.code, type);
            }
        }
    }

    /**
     * Representation of a single colored layer on a banner.
     */
    public static class BannerLayer {

        private LayerTexture texture;
        private DyeColor color;

        private BannerLayer(LayerTexture texture, DyeColor color) {
            this.texture = texture;
            this.color = color;
        }

        public LayerTexture getTexture() {
            return texture;
        }

        public DyeColor getColor() {
            return color;
        }
    }

    /**
     * Construct a builder for a BannerPattern.
     *
     * @return BannerPattern Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        ImmutableList.Builder<BannerLayer> layers = ImmutableList.builder();

        /**
         * Adds a colored layer to the banner.
         *
         * @param texture Pattern for this layer
         * @param color Color of the layer
         * @return This object, for chaining
         * @throws IllegalArgumentException If texture or color is null
         */
        public Builder layer(LayerTexture texture, DyeColor color) {
            Validate.notNull(texture, "Cannot have null texture");
            Validate.notNull(color, "Cannot have null color");
            layers.add(new BannerLayer(texture, color));

            return this;
        }

        /**
         * Creates a {@link BannerPattern} from the contents of this builder.
         *
         * @return The built BannerPattern
         */
        public BannerPattern build() {
            return new BannerPattern(layers.build());
        }
    }

    private final ImmutableList<BannerLayer> layers;

    private BannerPattern(ImmutableList<BannerLayer> layers) {
        this.layers = layers;
    }

    /**
     * Get the layers of this pattern.
     *
     * @return The layers of this pattern
     */
    public List<BannerLayer> getLayers() {
        return layers;
    }

}