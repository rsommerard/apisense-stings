package fr.inria.rsommerard.gsmsting;

import java.util.EnumSet;

import io.apisense.dart.api.Seed;
import io.apisense.dart.lib.BitMask;

public enum GSMSeed implements Seed {

    TIMESTAMP("timestamp", Long.class),
    NETWORK_OPERATOR_NAME("networkOperatorName", String.class),
    NETWORK_TYPE("networkType", Integer.class),
    SIGNAL_STRENGTH_LEVEL("signalStrengthLevel", Integer.class),
    DBM("dbm", Integer.class),
    ASU("asu", Integer.class);

    private final int value;

    private final String label;

    private final Class type;

    private GSMSeed(final String label,final Class type) {
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

    public static final EnumSet<GSMSeed> parse(final String... labels) {
        EnumSet<GSMSeed> res = EnumSet.noneOf(GSMSeed.class);
        for (String label : labels) {
            for (GSMSeed val : values()) {
                if (val.equals(label)) {
                    res.add(val);
                }
            }
        }
        return res;
    }

    public static final EnumSet<GSMSeed> parse(final int... codes) {
        EnumSet<GSMSeed> res = EnumSet.noneOf(GSMSeed.class);
        for (int code : codes) {
            for (GSMSeed val : values()) {
                if (val.matches(code)) {
                    res.add(val);
                }
            }
        }
        return res;
    }
}
