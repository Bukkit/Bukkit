package org.bukkit.potion;

public class PotionIngredientMeta {

    private boolean[] bitArray = new boolean[16];
    private boolean[] setFalse = new boolean[16];
    private boolean[] bitCheckIf = new boolean[16];
    private boolean[] bitCheckNot = new boolean[16];
    public int baseID = 0;

    public enum PotionBase {
        /**
         * Represents a water bottle
         */
        WATER,
        /**
         * Represents an awkward potion
         */
        AWKWARD, 
        /**
         * Represents a thick potion
         */
        THICK, 
        /**
         * Represents a mundane potion
         */
        MUNDANE,
    }

    public enum PotionBit {
        /**
         * Represents bit 0
         */
        ZERO(0), 

        /**
         * Represents bit 1
         */
        ONE(1),

        /**
         * Represents bit 2
         */
        TWO(2), 

        /**
         * Represents bit 3
         */
        THREE(3), 

        /**
         * Represents the effect bits (bits 0-3)
         */
        EFFECT(new int[] { 0, 1, 2, 3 }), 

        /**
         * Represents the bit that modifies whether the potion is a level II potion or not
         */
        LEVELII(5), 

        /**
         * Represents the bit that modifies whether the potion is an extended potion or not
         */
        EXTENDED(6), 

        /**
         * Represents the bit that sets a potion as drinkable
         */
        DRINKABLE(13),

        /**
         * Represents the bit that sets a potion as throwable
         */
        THROWABLE(14), 

        /**
         * Represents the bit that is set when a potion is an awkward potion
         */
        AWKWARD(4),

        /**
         * Represents the bit that is set when a potion is an thick potion
         */
        THICK(5), 

        /**
         * Represents the bit that is set when a potion is an mundane potion
         */
        MUNDANE(6), 

        /**
         * Represents the unused bits, (bits 7-12, 15)
         */
        UNKNOWN(new int[] { 7, 8, 9, 10, 11, 12, 15 });

        private int[] bits;

        PotionBit(int[] bits) {
            this.bits = bits.clone();
        }

        PotionBit(int bit) {
            this.bits = new int[] { bit };
        }

        public int[] getBits() {
            return bits.clone();
        }

        /**
         * Gets the specified bit as an integer. Returns -1 on arrays.
         * @return
         */
        public int getBit() {
            if (this != UNKNOWN && this != EFFECT)
                return getBits()[0]; // Only return bit if it doesn't consist of multiple bits
            else
                return -1; // Return -1 on bit arrays
        }
    }

    /**
     * Instantiates a PotionIngredientMeta class to be used with BrewRecipe.
     * 
     * @param type
     *            Type of the potion to be brewed as an integer less than 16
     * @param levelII
     *            Sets whether the potion is a level II or not
     * @param extended
     *            Sets whether the potion is extended or not
     * @param splash
     *            Sets whether the potion is a splash potion or not
     * @param base
     *            Sets the base potion this ingredient will require 
     */
    public PotionIngredientMeta(int type, boolean levelII, boolean extended, boolean splash, PotionBase base) {
        baseID = type;
        String bits = Integer.toBinaryString(type);
        while(bits.length() < 4)
            bits = "0" + bits;
        int count = 0;
        for (int i = 3; i >= 0; i--) {
            if (bits.charAt(i) == '1')
                bitArray[count] = true;
            else
                setFalse[count] = true;
            count++;
        }

        if (levelII)
            bitArray[5] = true;
        if (extended)
            bitArray[6] = true;
        if (splash)
            bitArray[14] = true;
        else
            bitArray[13] = true;

        switch (base) {
            case AWKWARD:
                bitCheckIf[4] = true;
                setFalse[4] = true;
                break;
            case THICK:
                bitCheckIf[5] = true;
                setFalse[5] = true;
                break;
            case MUNDANE:
                bitCheckIf[6] = true;
                setFalse[6] = true;
                break;
            case WATER:
            default:
                break;
        }
    }

    /**
     * Instantiates a PotionIngredientMeta class to be used with BrewRecipe.
     * 
     * @param type
     *            Type of the potion to be brewed
     * @param levelII
     *            Sets whether the potion is a level II or not
     * @param extended
     *            Sets whether the potion is extended or not
     * @param splash
     *            Sets whether the potion is a splash potion
     */
    public PotionIngredientMeta(int type, boolean levelII, boolean extended, boolean splash) {
        this(type, levelII, extended, splash, true);
    }

    /**
     * Instantiates a PotionIngredientMeta class to be used with BrewRecipe.
     * 
     * @param type
     *            Type of the potion to be brewed
     * @param levelII
     *            Sets whether the potion is a level II or not
     * @param extended
     *            Sets whether the potion is extended or not
     * @param splash
     *            Sets whether the potion is a splash potion
     * @param awkwardBase
     *            Sets whether the potion is based upon an awkward potion
     *            or not.
     */
    public PotionIngredientMeta(int type, boolean levelII, boolean extended, boolean splash, boolean awkwardBase) {
        this(type, levelII, extended, splash, PotionBase.AWKWARD);
    }

    /**
     * Instantiates a PotionIngredientMeta class to be used with BrewRecipe.
     * 
     * @param type
     *            Type of the potion to be brewed
     * @param levelII
     *            Sets whether the potion is a level II or not
     * @param extended
     *            Sets whether the potion is extended or not
     * @param splash
     *            Sets whether the potion is a splash potion
     * @param base
     *            Sets the base potion this ingredient will require
     */
    public PotionIngredientMeta(PotionType type, boolean levelII, boolean extended, boolean splash, PotionBase base) {
        this(type.getDamageValue(), levelII, extended, splash, base);
    }

    /**
     * Instantiates a PotionIngredientMeta class to be used with BrewRecipe.
     * 
     * @param type
     *            Type of the potion to be brewed
     * @param levelII
     *            Sets whether the potion is a level II or not
     * @param extended
     *            Sets whether the potion is extended or not
     * @param splash
     *            Sets whether the potion is a splash potion
     */
    public PotionIngredientMeta(PotionType type, boolean levelII, boolean extended, boolean splash) {
        this(type, levelII, extended, splash, true);
    }

    /**
     * Instantiates a PotionIngredientMeta class to be used with BrewRecipe.
     * 
     * @param type
     *            Type of the potion to be brewed
     * @param levelII
     *            Sets whether the potion is a level II or not
     * @param extended
     *            Sets whether the potion is extended or not
     * @param splash
     *            Sets whether the potion is a splash potion
     * @param awkwardBase
     *            Sets whether the potion is based upon an awkward potion
     *            or not.
     */
    public PotionIngredientMeta(PotionType type, boolean levelII, boolean extended, boolean splash, boolean awkwardBase) {
        this(type, levelII, extended, splash, PotionBase.AWKWARD);
    }

    /**
     * Instantiates a PotionIngredientMeta class to be used with BrewRecipe with a bit
     * string
     * 
     * @param bitString
     *            The bit string to be used
     */
    public PotionIngredientMeta(String bitString) {
        PotionBase base = PotionBase.WATER;
        if (bitString.contains("&4-4"))
            base = PotionBase.AWKWARD;
        if (bitString.contains("&5-5"))
            base = PotionBase.THICK;
        if (bitString.contains("&6-6"))
            base = PotionBase.MUNDANE;

        String bits;
        if (base == PotionBase.AWKWARD)
            bits = Integer.toBinaryString(getFinalBits(16, bitString));
        else if (base == PotionBase.THICK)
            bits = Integer.toBinaryString(getFinalBits(32, bitString));
        else if (base == PotionBase.MUNDANE)
            bits = Integer.toBinaryString(getFinalBits(64, bitString));
        else
            bits = Integer.toBinaryString(getFinalBits(0, bitString));
        for (int i = 0; i < bits.length(); i++)
            if (bits.charAt(i) == '1')
                bitArray[i] = true;
            else
                setFalse[i] = true;

        bits = "" + bits.charAt(0) + bits.charAt(1) + bits.charAt(2) + bits.charAt(3);
        baseID = Integer.parseInt(bits,2);
    }

    /**
     * Instantiates a PotionIngredientMeta class to be used with BrewRecipe with a bit
     * string.
     * 
     * @param bitString
     *            The bit string to be used
     * @param awkwardBase
     *            Sets whether the potion is based upon an awkward potion or
     *            not.
     */
    public PotionIngredientMeta(String bitString, boolean awkwardBase) {
        String bits;
        if (awkwardBase)
            bits = Integer.toBinaryString(getFinalBits(16, bitString));
        else
            bits = Integer.toBinaryString(getFinalBits(0, bitString));
        while(bits.length() < 17)
            bits = "0" + bits;
        for (int i = 0; i < 17; i++)
            if (bits.charAt(i) == '1')
                bitArray[i] = true;
            else
                setFalse[i] = true;

        bits = "" + bits.charAt(0) + bits.charAt(1) + bits.charAt(2) + bits.charAt(3);
        baseID = Integer.parseInt(bits,2);
    }

    /**
     * Instantiates a PotionIngredientMeta class to be used with BrewRecipe with a bit
     * string.
     * 
     * @param bitString
     *            The bit string to be used
     * @param base
     *            Sets the base potion this ingredient will require
     */
    public PotionIngredientMeta(String bitString, PotionBase base) {
        String bits;
        if (base == PotionBase.AWKWARD)
            bits = Integer.toBinaryString(getFinalBits(16, bitString));
        else if (base == PotionBase.THICK)
            bits = Integer.toBinaryString(getFinalBits(32, bitString));
        else if (base == PotionBase.MUNDANE)
            bits = Integer.toBinaryString(getFinalBits(64, bitString));
        else
            bits = Integer.toBinaryString(getFinalBits(0, bitString));

        while(bits.length() < 17)
            bits = "0" + bits;

        for (int i = 0; i < 17; i++)
            if (bits.charAt(i) == '1')
                bitArray[i] = true;
            else
                setFalse[i] = true;

        bits = "" + bits.charAt(0) + bits.charAt(1) + bits.charAt(2) + bits.charAt(3);
        baseID = Integer.parseInt(bits,2);
    }

    public String getBitString() {
        String bits = "";
        for (int i = 0; i < 16; i++) {
            if (bitCheckIf[i] == bitCheckNot[i])
                bitCheckIf[i] = bitCheckNot[i] = false;

            if (bitCheckIf[i])
                bits += "&" + i;
            if (bitCheckNot[i])
                bits += "!" + i;

            if (bitArray[i])
                bits += "+" + i;
            else if (!bitArray[i] && (i < 4 || setFalse[i]))
                bits += "-" + i;

        }
        return bits;
    }

    /**
     * Sets a specified bit as set or unset
     * 
     * @param i
     *            Bit to set
     * @param set
     *            Value (true as set or false as unset) to set them to
     */
    public PotionIngredientMeta setBit(int i, boolean set) {
        if (!set)
            setFalse[i] = true;
        bitArray[i] = set;
        return this;
    }

    /**
     * Sets if a specified bit should be checked as set
     * 
     * @param i
     *            Bit to set
     * @param set
     *            Value (true as set or false as unset) to set them to
     */
    public PotionIngredientMeta setCheckIfSet(int i, boolean check) {
        bitCheckIf[i] = check;
        return this;
    }

    /**
     * Sets if a specified bit should be checked as unset
     * 
     * @param i
     *            Bit to set
     * @param set
     *            Value (true as set or false as unset) to set them to
     */
    public PotionIngredientMeta setCheckNotSet(int i, boolean check) {
        bitCheckNot[i] = check;
        return this;
    }

    /**
     * Sets multiple bits to a single value
     * 
     * @param ia
     *            Integer Array of the different bits to set
     * @param set
     *            Value (true as set or false as unset) to set them to
     */
    public PotionIngredientMeta setBits(int[] ia, boolean set) {
        for (int i : ia) {
            if (!set)
                setFalse[i] = true;
            bitArray[i] = set;
        }
        return this;
    }

    /**
     * Checks an array of bits as set
     * 
     * @param ia
     *            Integer Array of the different bits to set
     * @param set
     *            Value (true as set or false as unset) to set them to
     */
    public PotionIngredientMeta setCheckIfSet(int[] ia, boolean check) {
        for (int i : ia)
            bitCheckIf[i] = check;
        return this;
    }

    /**
     * Checks an array of bits as unset
     * 
     * @param ia
     *            Integer Array of the different bits to set
     * @param set
     *            Value (true as set or false as unset) to set them to
     */
    public PotionIngredientMeta setCheckNotSet(int[] ia, boolean check) {
        for (int i : ia)
            bitCheckNot[i] = check;
        return this;
    }

    /**
     * Sets a specified bit as set or unset
     * 
     * @param i
     *            Bit to set
     * @param set
     *            Value (true as set or false as unset) to set them to
     */
    public PotionIngredientMeta setBit(PotionBit bit, boolean set) {
        if (!set)
            setFalse[bit.getBit()] = true;

        if (bit != PotionBit.EFFECT)
            bitArray[bit.getBit()] = set;
        return this;
    }

    /**
     * Sets if a specified bit should be checked as set
     * 
     * @param i
     *            Bit to set
     * @param set
     *            Value (true as set or false as unset) to set them to
     */
    public PotionIngredientMeta setCheckIfSet(PotionBit bit, boolean check) {
        if (bit != PotionBit.EFFECT)
            bitCheckIf[bit.getBit()] = check;
        return this;
    }

    /**
     * Sets if a specified bit should be checked as unset
     * 
     * @param i
     *            Bit to set
     * @param set
     *            Value (true as set or false as unset) to set them to
     */
    public PotionIngredientMeta setCheckNotSet(PotionBit bit, boolean check) {
        if (bit != PotionBit.EFFECT)
            bitCheckNot[bit.getBit()] = check;
        return this;
    }

    /**
     * Sets multiple bits to a single value
     * 
     * @param ia
     *            Integer Array of the different bits to set
     * @param set
     *            Value (true as set or false as unset) to set them to
     */
    public PotionIngredientMeta setBits(PotionBit[] bits, boolean set) {
        int length = bits.length;
        for (PotionBit bit : bits)
            if (bit == PotionBit.EFFECT)
                length += 4;

        int[] ia = new int[length];
        for (int i = 0; i < length; i++) {
            if (!set)
                setFalse[bits[i].getBit()] = true;

            if (bits[i] == PotionBit.EFFECT) {
                ia[i] = bits[i].bits[0];
                ia[i + 1] = bits[i].bits[1];
                ia[i + 2] = bits[i].bits[2];
                ia[i + 3] = bits[i].bits[3];
                i += 3;
            } else
                ia[i] = bits[i].getBit();
        }

        for (int i : ia)
            bitArray[i] = set;
        return this;
    }

    /**
     * Sets multiple bits to be checked as set
     * 
     * @param ia
     *            Integer Array of the different bits to set
     * @param set
     *            Value (true as set or false as unset) to set them to
     */
    public PotionIngredientMeta setCheckIfSet(PotionBit[] bits, boolean check) {
        int[] ia = new int[bits.length];
        for (int i = 0; i < bits.length; i++)
            ia[i] = bits[i].getBit();

        for (int i : ia)
            bitCheckIf[i] = check;
        return this;
    }

    /**
     * Sets if multiple bits should be checked as unset
     * 
     * @param ia
     *            Integer Array of the different bits to set
     * @param set
     *            Value (true as set or false as unset) to set them to
     */
    public PotionIngredientMeta setCheckNot(PotionBit[] bits, boolean check) {
        int[] ia = new int[bits.length];
        for (int i = 0; i < bits.length; i++)
            ia[i] = bits[i].getBit();

        for (int i : ia)
            bitCheckNot[i] = check;
        return this;
    }

    /**
     * This code was borrowed from PotionBrewer.java from NMS to be used for
     * getResult().
     */
    public static short getFinalBits(int paramInt, String paramString) {
        int m = 0;
        int n = paramString.length();

        int i1 = 0;
        boolean bool1 = false;
        boolean bool2 = false;
        boolean bool3 = false;
        int i2 = 0;
        for (int i3 = m; i3 < n; i3++) {
            int i4 = paramString.charAt(i3);
            if ((i4 >= 48) && (i4 <= 57)) {
                i2 *= 10;
                i2 += i4 - 48;
                i1 = 1;
            } else if (i4 == 33) {
                if (i1 != 0) {
                    paramInt = a(paramInt, i2, bool2, bool1, bool3);
                    i1 = 0;
                    bool3 = bool2 = bool1 = false;
                    i2 = 0;
                }

                bool1 = true;
            } else if (i4 == 45) {
                if (i1 != 0) {
                    paramInt = a(paramInt, i2, bool2, bool1, bool3);
                    i1 = 0;
                    bool3 = bool2 = bool1 = false;
                    i2 = 0;
                }

                bool2 = true;
            } else if (i4 == 43) {
                if (i1 != 0) {
                    paramInt = a(paramInt, i2, bool2, bool1, bool3);
                    i1 = 0;
                    bool3 = bool2 = bool1 = false;
                    i2 = 0;
                }
            } else if (i4 == 38) {
                if (i1 != 0) {
                    paramInt = a(paramInt, i2, bool2, bool1, bool3);
                    i1 = 0;
                    bool3 = bool2 = bool1 = false;
                    i2 = 0;
                }
                bool3 = true;
            }
        }
        if (i1 != 0) {
            paramInt = a(paramInt, i2, bool2, bool1, bool3);
        }

        return (short) (paramInt & 0x7FFF);
    }

    /**
     * This code was borrowed from PotionBrewer.java from NMS to be used for
     * getResult().
     */
    private static int a(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
        if (paramBoolean3) {
            if (!a(paramInt1, paramInt2))
                return 0;
        } else if (paramBoolean1)
            paramInt1 &= (1 << paramInt2 ^ 0xFFFFFFFF);
        else if (paramBoolean2) {
            if ((paramInt1 & 1 << paramInt2) == 0)
                paramInt1 |= 1 << paramInt2;
            else
                paramInt1 &= (1 << paramInt2 ^ 0xFFFFFFFF);
        } else {
            paramInt1 |= 1 << paramInt2;
        }
        return paramInt1;
    }

    /**
     * This code was borrowed from PotionBrewer.java from NMS to be used for
     * getResult().
     */
    private static boolean a(int paramInt1, int paramInt2) {
        return (paramInt1 & 1 << paramInt2) != 0;
    }
}
