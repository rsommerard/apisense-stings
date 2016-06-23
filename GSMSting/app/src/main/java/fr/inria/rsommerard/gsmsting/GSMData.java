package fr.inria.rsommerard.gsmsting;

import io.apisense.dart.lib.DataImpl;

public final class GSMData extends DataImpl {

    public final String networkOperatorName;
    public final Integer networkType;
    public final Integer signalStrengthLevel;
    public final Integer dbm;
    public final Integer asu;

    public GSMData(final int seeds, final String networkOperatorName, final Integer networkType,
                   final Integer signalStrengthLevel, final Integer dbm, final Integer asu) {
        super(seeds);
        this.networkOperatorName = networkOperatorName;
        this.networkType = networkType;
        this.signalStrengthLevel = signalStrengthLevel;
        this.dbm = dbm;
        this.asu = asu;
    }
}
