package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Represents minecart rails.
 * @author sunkid
 */
public class Rails extends MaterialData {
       public Rails(final int type) {
            super(type);
        }

        public Rails(final Material type) {
            super(type);
        }

        public Rails(final int type, final byte data) {
            super(type, data);
        }

        public Rails(final Material type, final byte data) {
            super(type, data);
        }

        /**
         * @return the whether this track is set on a slope
         */
        public boolean isOnSlope() {
            byte d = getData();
            return ((d & 0x1) == 0x1 || (d & 0x2) == 0x2 || (d & 0x3) == 0x3 || (d & 0x4) == 0x4);
        }
        
        /**
         * @return the whether this track is set as a curve
         */
        public boolean isCurve() {
            byte d = getData();
            return ((d & 0x6) == 0x6 || (d & 0x7) == 0x7 || (d & 0x8) == 0x8 || (d & 0x9) == 0x9);
        }
        
        /**
         * @return the direction these tracks are set
         * <br>Note that tracks are bidirectional and that the direction returned is the ascending direction
         * if the track is set on a slope. If it is set as a curve, the corner of the track is returned.
         */
        public BlockFace getDirection() {
            byte d = getData();
            if ((d & 0x1) == 0x1) {
                return BlockFace.NORTH;
            } else if ((d & 0x2) == 0x2) {
                return BlockFace.SOUTH;
            } else if ((d & 0x3) == 0x3) {
                return BlockFace.NORTH;
            } else if ((d & 0x4) == 0x4) {
                return BlockFace.EAST;
            } else if ((d & 0x5) == 0x5) {
                return BlockFace.WEST;
            } else if ((d & 0x6) == 0x6) {
                return BlockFace.NORTH_EAST;
            } else if ((d & 0x7) == 0x7) {
                return BlockFace.SOUTH_EAST;
            } else if ((d & 0x8) == 0x8) {
                return BlockFace.SOUTH_WEST;
            } else if ((d & 0x9) == 0x9) {
                return BlockFace.NORTH_WEST;
            }
            
            return BlockFace.EAST;
        }
}
