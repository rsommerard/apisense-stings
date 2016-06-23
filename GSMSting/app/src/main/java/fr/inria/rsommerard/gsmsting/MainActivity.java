package fr.inria.rsommerard.gsmsting;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.apisense.sdk.APISENSE;
import com.apisense.sdk.adapter.SimpleAPSCallback;
import com.apisense.sdk.core.store.Crop;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String SDK_KEY = "";
    private static final String ACCESS_KEY = "";
    private static final String CROP_ID = "";

    private static final String USER_LOGIN = "";
    private static final String USER_PASSWORD = "";

    private static final int PERMISSIONS = 42;

    private APISENSE.Sdk mSdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APISENSE apisense = new APISENSE(this.getApplication());
        apisense.useSdkKey(SDK_KEY);
        apisense.useAccessKey(ACCESS_KEY);
        apisense.addStingsModules(new CustomStingModule());

        mSdk = apisense.getSdk();

        if (mSdk.getSessionManager().isConnected()) {
            startExperiment();
        } else {
            mSdk.getSessionManager().login(USER_LOGIN, USER_PASSWORD,  new SimpleAPSCallback<Void>() {
                @Override
                public void onDone(Void aVoid) {
                    startExperiment();
                }
            });
        }
    }

    private void startExperiment() {
        mSdk.getStoreManager().findSpecificCrop(CROP_ID,  new SimpleAPSCallback<Crop>() {
            @Override
            public void onDone(Crop crop) {
                Set<String> deniedPermissions = mSdk.getCropManager().deniedPermissions(crop);
                if (deniedPermissions.isEmpty()) {
                    if (mSdk.getCropManager().isInstalled(crop)) {
                        mSdk.getCropManager().update(crop.getLocation(), new SimpleAPSCallback<Crop>() {
                            @Override
                            public void onDone(Crop crop) {
                                Log.d("GSMSting", "Crop updated: " + crop.getName() + ", " + crop.getOwner());
                            }
                        });
                    } else {
                        mSdk.getCropManager().installSpecific(CROP_ID,  new SimpleAPSCallback<Crop>() {
                            @Override
                            public void onDone(Crop crop) {
                                Log.d("GSMSting", "Crop installed: " + crop.getName() + ", " + crop.getOwner());
                                mSdk.getCropManager().start(crop, new SimpleAPSCallback<Crop>() {
                                    @Override
                                    public void onDone(Crop crop) {
                                        Log.d("GSMSting", "Crop started: " + crop.getName() + ", " + crop.getOwner());
                                    }
                                });
                            }
                        });
                    }
                } else {
                    String[] requestedPermissions = deniedPermissions.toArray(new String[deniedPermissions.size()]);
                    ActivityCompat.requestPermissions(MainActivity.this, requestedPermissions, PERMISSIONS);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS: {
                startExperiment();
            }
        }
    }
}
