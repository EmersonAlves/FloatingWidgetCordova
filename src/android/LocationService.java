package br.com.fabrica704.widgetfloat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LocationService extends BroadcastReceiver {
    private int driverId;
    private int userId;
    private String token;

    public static final String ACTION_PROCESS_UPDATE = "br.com.fabrica704.widgetfloat.UPDATE_LOCATION";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();

            if (ACTION_PROCESS_UPDATE.equals(action)) {

                LocationResult result = LocationResult.extractResult(intent);
                if (result != null) {

                    Location location = result.getLastLocation();
                    try {
                        driverId = intent.getExtras().getInt("driverId");
                        userId = intent.getExtras().getInt("userId");
                        token = intent.getExtras().getString("token");
                        RequestApi.HOST = intent.getExtras().getString("url");

                        sendLocation(context,location);
                    } catch (Exception ex) {
                        Log.d("actionLocation", ex.getMessage());
                    }
                }
            }
        }
    }


    public void sendLocation(Context context, Location location) throws JSONException {
        Map<String, String> headers = new HashMap<>();

        headers.put("token", token);
        headers.put("userid", "" + userId);

        JSONObject objectBase = new JSONObject();
        JSONArray objectArray = new JSONArray();
        JSONObject data = new JSONObject();

        data.put("latitude", location.getLatitude());
        data.put("longitude", location.getLongitude());
        data.put("driverId", driverId);

        objectArray.put(data);

        objectBase.put("stdObject", objectArray);

        RequestApi.sendPost(context, "Location/Insert", objectBase, headers);
    }
}