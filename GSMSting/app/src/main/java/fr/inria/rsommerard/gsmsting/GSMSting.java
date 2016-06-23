package fr.inria.rsommerard.gsmsting;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.apisense.sdk.core.preferences.Sensor;

import java.util.EnumSet;
import java.util.List;

import javax.inject.Inject;

public final class GSMSting extends GSMDartSkel {

    private final Context context;
    private final TelephonyManager telephonyManager;
    private GSMData gsmData;

    private static final String TAG = "GSMSting";

    public static final Sensor SENSOR_DESCRIPTION = new Sensor(
            "GSM analyzer",
            NAME,
            "Analyze GSM network",
            R.drawable.aps_network,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION);

    @Inject
    public GSMSting(final Context context) {
        super(EnumSet.allOf(GSMSeed.class));
        this.context = context;
        this.telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        if (checkPermission()) {
            updateData();
        }

        if (gsmData == null) {
            gsmData = new GSMData(seeds(), null, null, null, null, null);
        }
    }

    private void updateData() {
        String networkOperatorName = telephonyManager.getNetworkOperatorName();
        int networkType = telephonyManager.getNetworkType();

        int signalStrengthLevel = 0;
        int dbm = 0;
        int asu = 0;
        // Caution: http://stackoverflow.com/questions/16541172/getallcellinfo-returns-null-in-android-4-2-1
        List<CellInfo> cells = telephonyManager.getAllCellInfo();

        if (cells == null) {
            gsmData = new GSMData(seeds(), null, null, null, null, null);
            return;
        }

        for (CellInfo c : cells) {
            if (! c.isRegistered()) {
                continue;
            }

            CellSignalStrength cellSignalStrength = null;

            if (c instanceof CellInfoLte) {
                cellSignalStrength = ((CellInfoLte) c).getCellSignalStrength();
            } else if (c instanceof CellInfoCdma) {
                cellSignalStrength = ((CellInfoCdma) c).getCellSignalStrength();
            } else if (c instanceof CellInfoGsm) {
                cellSignalStrength = ((CellInfoGsm) c).getCellSignalStrength();
            } else if (c instanceof CellInfoWcdma) {
                cellSignalStrength = ((CellInfoWcdma) c).getCellSignalStrength();
            }

            if (cellSignalStrength == null) {
                Log.e(TAG, "cellSignalStrength == null");
                break;
            }

            signalStrengthLevel = cellSignalStrength.getLevel();
            dbm = cellSignalStrength.getDbm();
            asu = cellSignalStrength.getAsuLevel();

            break;
        }

        gsmData = new GSMData(seeds(), networkOperatorName, networkType, signalStrengthLevel, dbm,
                asu);
    }

    private boolean checkPermission() {
        return permissionGranted(Manifest.permission.READ_PHONE_STATE) &&
                permissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    private boolean permissionGranted(String permission) {
        return ContextCompat
                .checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public String networkOperatorName() {
        updateData();
        return gsmData.networkOperatorName;
    }

    @Override
    public Integer networkType() {
        updateData();
        return gsmData.networkType;
    }

    @Override
    public Integer signalStrengthLevel() {
        updateData();
        return gsmData.signalStrengthLevel;
    }

    @Override
    public Integer dbm() {
        updateData();
        return gsmData.dbm;
    }

    @Override
    public Integer asu() {
        updateData();
        return gsmData.asu;
    }
}
