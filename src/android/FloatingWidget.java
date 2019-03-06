package br.com.floatingwidget;


import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class FloatingWidget extends CordovaPlugin  {

    @Override
    public boolean execute(String action, JSONArray args,
                           CallbackContext callbackContext) throws JSONException {

        if (action.equals("YOUR_ACTION_NAME_PARAMETER")) {
            return true;
        }

        return false; // Returning false results in a "MethodNotFound" error.
    }
}