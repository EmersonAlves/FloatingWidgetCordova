package br.com.fabrica704.widgetfloat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FloatingWidget extends CordovaPlugin  {

    private static final int DRAW_OVER_OTHER_APP_PERMISSION = 4321;

    @Override
    public boolean execute(String action, JSONArray args,
                           CallbackContext callbackContext) throws JSONException {

        if (action.equals("open")) {
            openFloatingWidget();
            openServiceLocation(args.getJSONObject(0));
            return true;
        }

        if(action.equals("getPermission")){
            askForSystemOverlayPermission();
            return true;
        }

        if (action.equals("close")) {
            closeFloatingWidget();
            return true;
        }

        return false; // Returning false results in a "MethodNotFound" error.
    }

    private void openFloatingWidget(){
        Activity context = cordova.getActivity();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            context.startService(new Intent(cordova.getActivity(), FloatingWidgetService.class));
            // finish();
        } else if (Settings.canDrawOverlays(cordova.getActivity())) {
            context.startService(new Intent(cordova.getActivity(), FloatingWidgetService.class));
            // finish();
        } else {
            askForSystemOverlayPermission();
            Toast.makeText(cordova.getActivity(), "You need System Alert Window Permission to do this", Toast.LENGTH_SHORT).show();
        }

    }

     private void askForSystemOverlayPermission() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(cordova.getContext())) {

                //If the draw over permission is not available to open the settings screen
                //to grant the permission.
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + cordova.getContext().getPackageName()));
                cordova.getActivity().startActivityForResult(intent, DRAW_OVER_OTHER_APP_PERMISSION);
            }
        }


    private void closeFloatingWidget() {
        Intent lintent = new Intent(cordova.getContext(), FloatingWidgetService.class);
        cordova.getContext().stopService(lintent);
    }


    private void openServiceLocation(JSONObject object) {
        Intent intent = new Intent(cordova.getContext(), LocationService.class);
        try {
            intent.putExtra("url", object.getString("url"));
            intent.putExtra("driverId", object.getInt("driverId"));
            intent.putExtra("userId", object.getInt("userId"));
            intent.putExtra("token", object.getString("token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cordova.getContext().startService(intent);
    }
}