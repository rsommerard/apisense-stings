package fr.inria.rsommerard.wifidirectsting;

import java.util.EnumSet;

import io.apisense.dart.api.Seed;
import io.apisense.dart.lib.BitMask;

public enum WiFiDirectSeed implements Seed {

    TIMESTAMP("timestamp", Long.class);

    private final int value;
    private final String label;
    private final Class type;

    private WiFiDirectSeed(final String label, final Class type) {
        this.value = 1 << this.ordinal();
        this.label = label;
        this.type = type;
    }

    public Class type() {
        return this.type;
    }

    @Override
    public int value() {
        return this.value;
    }

    public final boolean matches(final int value) {
        return BitMask.matches(this.value, value);
    }

    public final boolean equals(final String label) {
        return this.label.equalsIgnoreCase(label);
    }

    public final String toString() {
        return this.label;
    }

    public static final EnumSet<WiFiDirectSeed> parse(final String... labels) {
        EnumSet<WiFiDirectSeed> res = EnumSet.noneOf(WiFiDirectSeed.class);
        for (String label : labels) {
            for (WiFiDirectSeed val : values()) {
                if (val.equals(label)) {
                    res.add(val);
                }
            }
        }
        return res;
    }

    public static final EnumSet<WiFiDirectSeed> parse(final int... codes) {
        EnumSet<WiFiDirectSeed> res = EnumSet.noneOf(WiFiDirectSeed.class);
        for (int code : codes) {
            for (WiFiDirectSeed val : values()) {
                if (val.matches(code)) {
                    res.add(val);
                }
            }
        }
        return res;
    }
}
