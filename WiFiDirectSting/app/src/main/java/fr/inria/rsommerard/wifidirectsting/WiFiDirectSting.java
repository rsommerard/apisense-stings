package fr.inria.rsommerard.wifidirectsting;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.apisense.sdk.core.preferences.Sensor;

import java.util.EnumSet;

import javax.inject.Inject;

public class WiFiDirectSting extends WiFiDirectDartSkel {

    private static final String TAG = "WiFiDirectSting";

    public static final Sensor SENSOR_DESCRIPTION = new Sensor(
            "WiFi-Direct data dissemination",
            NAME,
            "Anonymize data",
            R.drawable.aps_network,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE);

    private final Context context;

    @Inject
    public WiFiDirectSting(final Context context) {
        super(EnumSet.allOf(WiFiDirectSeed.class));
        this.context = context;

        if (checkPermission()) {
            process();
        }
    }

    private void process() {

    }

    private boolean checkPermission() {
        return permissionGranted(Manifest.permission.INTERNET) &&
                permissionGranted(Manifest.permission.ACCESS_WIFI_STATE) &&
                permissionGranted(Manifest.permission.CHANGE_WIFI_STATE);
    }

    private boolean permissionGranted(String permission) {
        return ContextCompat
                .checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void addData(String data) {

    }
}
