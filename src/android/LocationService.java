package br.com.fabrica704.widgetfloat;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LocationService extends Service {

    private LocationListener listener;
    private LocationManager locationManager;

    private int driverId;
    private int userId;
    private String token;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                try {
                    sendLocation(location);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, listener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null)
            locationManager.removeUpdates(listener);
    }

    public void sendLocation(Location location) throws JSONException {
        Map<String, String> headers = new HashMap<>();

        headers.put("token", token);
        headers.put("userid", ""+userId);

        JSONObject objectBase = new JSONObject();
        JSONArray objectArray = new JSONArray();
        JSONObject data = new JSONObject();

        data.put("latitude", location.getLatitude());
        data.put("longitude", location.getLongitude());
        data.put("driverId", driverId);

        objectArray.put(data);

        objectBase.put("stdObject", objectArray);

        RequestApi.sendPost(this, "Location/Insert", objectBase, headers);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        driverId = intent.getExtras().getInt("driverId");
        userId = intent.getExtras().getInt("userId");
        token = intent.getExtras().getString("token");
        RequestApi.HOST = intent.getExtras().getString("url");

        return super.onStartCommand(intent, flags, startId);
    }
}
