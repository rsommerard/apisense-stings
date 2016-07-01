package fr.inria.rsommerard.wifidirectsting;

import java.util.Set;

import io.apisense.dart.api.Seed;
import io.apisense.dart.lib.DartImpl;

public class WiFiDirectDartSkel extends DartImpl<WiFiDirectData> implements WiFiDirectDart {

    protected WiFiDirectDartSkel(final Set<? extends Seed> fields) {
        super(WiFiDirectDart.class, fields);
    }


    @Override
    public WiFiDirectData map(final int mask) {
        return new WiFiDirectData(mask);
    }

    @Override
    public void addData(String data) {
        return;
    }
}
