package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Represents stairs.
 * @author sunkid
 */
public class Stairs extends MaterialData {
       public Stairs(final int type) {
            super(type);
        }

        public Stairs(final Material type) {
            super(type);
        }

        public Stairs(final int type, final byte data) {
            super(type, data);
        }

        public Stairs(final Material type, final byte data) {
            super(type, data);
        }

        /**
         * @return the direction the stairs ascend towards
         */
        public BlockFace getAscendingDirection() {
            byte d = getData();
            if ((d & 0x1) == 0x1) {
                return BlockFace.NORTH;
            } else if ((d & 0x2) == 0x2) {
                return BlockFace.WEST;
            } else if ((d & 0x3) == 0x3) {
                return BlockFace.EAST;
            }
            
            return BlockFace.SOUTH;
        }
        
        /**
         * @return the direction the stairs descend towards
         */
        public BlockFace getDescendingDirection() {
            return getAscendingDirection().getOppositeFace();
        }
}
