package fr.inria.rsommerard.gsmsting;

import java.util.Set;

import io.apisense.dart.api.Seed;
import io.apisense.dart.lib.DartImpl;

public abstract class GSMDartSkel extends DartImpl<GSMData> implements GSMDart {

    protected GSMDartSkel(final Set<? extends Seed> fields) {
        super(GSMDart.class, fields);
    }

    @Override
    public String networkOperatorName() {
        return null;
    }

    @Override
    public Integer networkType() {
        return null;
    }

    @Override
    public Integer signalStrengthLevel() {
        return null;
    }

    @Override
    public Integer dbm() {
        return null;
    }

    @Override
    public Integer asu() {
        return null;
    }

    @Override
    public GSMData map(final int mask) {
        return new GSMData(mask,
                GSMSeed.NETWORK_OPERATOR_NAME.matches(mask) ? this.networkOperatorName() : null,
                GSMSeed.NETWORK_TYPE.matches(mask) ? this.networkType() : null,
                GSMSeed.SIGNAL_STRENGTH_LEVEL.matches(mask) ? this.signalStrengthLevel() : null,
                GSMSeed.DBM.matches(mask) ? this.dbm() : null,
                GSMSeed.ASU.matches(mask) ? this.asu() : null);
    }
}
