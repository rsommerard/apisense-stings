package fr.inria.rsommerard.wifidirectsting;

import io.apisense.dart.api.Dart;
import io.apisense.dart.api.Token;

public interface WiFiDirectDart extends Dart<WiFiDirectData> {

    static final String NAME = "wifidirect";

    Token onDataAvailableToSave(String data);

    void disseminate(String data);
}
