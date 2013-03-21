package org.bukkit.material;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.SmoothBrickType;

/**
 * Represents the different types of smooth bricks.
 */
public class SmoothBrick extends TexturedMaterial {

    @Deprecated
    private static final List<Material> textures = new ArrayList<Material>();
    static {
        textures.add(Material.STONE);
        textures.add(Material.MOSSY_COBBLESTONE);
        textures.add(Material.COBBLESTONE);
        textures.add(Material.SMOOTH_BRICK);
    }

    public SmoothBrick() {
        super(Material.SMOOTH_BRICK);
    }

    public SmoothBrick(SmoothBrickType type) {
        this();
        setType(type);
    }

    public SmoothBrick(final int type) {
        super(type);
    }

    public SmoothBrick(final Material type) {
        super(type);
    }

    public SmoothBrick(final int type, final byte data) {
        super(type, data);
    }

    public SmoothBrick(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current type of this smooth brick
     *
     * @return SmoothBrickType of this smooth brick
     */
    public SmoothBrickType getType() {
        switch (getData()) {
            case 0x1:
                return SmoothBrickType.MOSSY;
            case 0x2:
                return SmoothBrickType.CRACKED;
            case 0x3:
                return SmoothBrickType.CIRCLE;
            default:
                return SmoothBrickType.NORMAL;
        }
    }

    /**
     * Sets the type of this smooth brick
     *
     * @param type New type of this smooth brick
     */
    public void setType(SmoothBrickType type) {
        switch (type) {
            case NORMAL:
                setData((byte) 0x0);
                return;
            case MOSSY:
                setData((byte) 0x1);
                return;
            case CRACKED:
                setData((byte) 0x2);
                return;
            case CIRCLE:
                setData((byte) 0x3);
                return;
        }
    }

    @Deprecated
    public List<Material> getTextures() {
        return textures;
    }

    @Override
    public String toString() {
        return getType() + " " + super.toString();
    }

    @Override
    public SmoothBrick clone() {
        return (SmoothBrick) super.clone();
    }
}
