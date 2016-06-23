package fr.inria.rsommerard.gsmsting;

import io.apisense.dart.api.Dart;

public interface GSMDart extends Dart<GSMData> {

    static final String NAME = "gsm";

    String networkOperatorName();

    Integer networkType();

    Integer signalStrengthLevel();

    Integer dbm();

    Integer asu();
}
