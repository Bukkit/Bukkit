package org.bukkit;

import java.util.Map;

import org.apache.commons.lang.Validate;

import com.google.common.collect.Maps;

/**
 * A note class to store a specific note.
 */
public class Note {

    /**
     * An enum holding tones.
     */
    public enum Tone {
        G(0x1, true),
        A(0x3, true),
        B(0x5, false),
        C(0x6, true),
        D(0x8, true),
        E(0xA, false),
        F(0xB, true);

        private final boolean sharpable;
        private final byte id;

        private static final Map<Byte, Note.Tone> BY_DATA = Maps.newHashMap();
        /** The number of tones including sharped tones. */
        public static final byte TONES_COUNT = 12;

        private Tone(int id, boolean sharpable) {
            this.id = (byte) (id % TONES_COUNT);
            this.sharpable = sharpable;
        }

        /**
         * Returns the not sharped id of this tone.
         *
         * @return the not sharped id of this tone.
         */
        public byte getId() {
            return getId(false);
        }

        /**
         * Returns the id of this tone. These method allows to return the
         * sharped id of the tone. If the tone couldn't be sharped it always
         * return the not sharped id of this tone.
         *
         * @param sharped Set to true to return the sharped id.
         * @return the id of this tone.
         */
        public byte getId(boolean sharped) {
            byte id = (byte) (sharped && sharpable ? this.id + 1 : this.id);

            return (byte) (id % TONES_COUNT);
        }

        /**
         * Returns if this tone could be sharped.
         *
         * @return if this tone could be sharped.
         */
        public boolean isSharpable() {
            return sharpable;
        }

        /**
         * Returns if this tone id is the sharped id of the tone.
         *
         * @param id the id of the tone.
         * @return if the tone id is the sharped id of the tone.
         * @throws IllegalArgumentException if neither the tone nor the semitone have the id.
         */
        public boolean isSharped(byte id) {
            if (id == getId(false)) {
                return false;
            } else if (id == getId(true)) {
                return true;
            } else {
                // The id isn't matching to the tone!
                throw new IllegalArgumentException("The id isn't matching to the tone.");
            }
        }

        /**
         * Returns the tone to id. Also returning the semitones.
         *
         * @param id the id of the tone.
         * @return the tone to id.
         * @deprecated use {@link #getById(byte)}
         */
        @Deprecated
        public static Tone getToneById(byte id) {
            return BY_DATA.get(id);
        }

        /**
         * Returns the tone to id. Also returning the semitones.
         *
         * @param id the id of the tone.
         * @return the tone to id.
         */
        public static Tone getById(byte id) {
            return BY_DATA.get(id);
        }

        static {
            for (Tone tone : values()) {
                int id = tone.id % TONES_COUNT;
                BY_DATA.put((byte) id, tone);

                if (tone.isSharpable()) {
                    id = (id + 1) % TONES_COUNT;
                    BY_DATA.put((byte) id, tone);
                }
            }
        }
    }

    private final byte note;

    /**
     * Creates a new note.
     *
     * @param note Internal note id. {@link #getId()} always return this value.
     *            The value has to be in the interval [0;&nbsp;24].
     */
    public Note(int note) {
        Validate.isTrue(note >= 0 && note <= 24, "The note value has to be between 0 and 24.");

        this.note = (byte) note;
    }

    /**
     * Creates a new note.
     *
     * @param octave The octave where the note is in. Has to be 0 - 2.
     * @param tone The tone within the octave. If the octave is 2 the note has to be F#.
     * @param sharped Set it the tone is sharped (e.g. for F#).
     */
    public Note(int octave, Tone tone, boolean sharped) {
        Validate.isTrue(!(sharped && !tone.isSharpable()), "This tone could not be sharped.");
        if (octave < 0 || octave > 2 || (octave == 2 && !(tone == Tone.F && sharped))) {
            throw new IllegalArgumentException("Tone and octave have to be between F#0 and F#2");
        }

        this.note = (byte) (octave * Tone.TONES_COUNT + tone.getId(sharped));
    }

    /**
     * Returns the internal id of this note.
     *
     * @return the internal id of this note.
     */
    public byte getId() {
        return note;
    }

    /**
     * Returns the octave of this note.
     *
     * @return the octave of this note.
     */
    public int getOctave() {
        return note / Tone.TONES_COUNT;
    }

    private byte getToneByte() {
        return (byte) (note % Tone.TONES_COUNT);
    }

    /**
     * Returns the tone of this note.
     *
     * @return the tone of this note.
     */
    public Tone getTone() {
        return Tone.getById(getToneByte());
    }

    /**
     * Returns if this note is sharped.
     *
     * @return if this note is sharped.
     */
    public boolean isSharped() {
        byte note = getToneByte();
        return Tone.getById(note).isSharped(note);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + note;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Note other = (Note) obj;
        if (note != other.note)
            return false;
        return true;
    }
}
