package br.com.floatingwidget;

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

public class FloatingWidget extends CordovaPlugin  {

    private static final int DRAW_OVER_OTHER_APP_PERMISSION = 4321;

    @Override
    public boolean execute(String action, JSONArray args,
                           CallbackContext callbackContext) throws JSONException {

        if (action.equals("open")) {
            openFloatingWidget();
            return true;
        }

        if(action.equals("getPermission")){
            askForSystemOverlayPermission();
            return true;
        }

        return false; // Returning false results in a "MethodNotFound" error.
    }

    private void openFloatingWidget(){
        Activity context = cordova.getActivity();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(context)) {
            context.startService(new Intent(cordova.getActivity(), FloatingWidgetService.class));
        } else {
            errorToast();
        }

    }

    private void errorToast() {
        Toast.makeText(cordova.getActivity(), "Draw over other app permission not available. Can't start the application without the permission.", Toast.LENGTH_LONG).show();
    }

    private void askForSystemOverlayPermission() {
        Activity context = cordova.getActivity();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {

            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + context.getPackageName()));
            context.startActivityForResult(intent, DRAW_OVER_OTHER_APP_PERMISSION);
        }
    }



}