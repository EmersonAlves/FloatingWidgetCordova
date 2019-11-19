package br.com.fabrica704.widgetfloat;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class RequestApi {

    private final static String HOST = "https://apiteste.taxireturn.com.br/";

    public static void sendPost(Context context, String path, JSONObject data, Map<String, String> headers) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        final String url = HOST + path;

        final int type = Request.Method.POST;

        final String requestBody = data.toString();

        StringRequest stringRequest = RequestApi.getStringRequest(type, url, requestBody, headers);

        requestQueue.add(stringRequest);
    }


    public static void sendGet(Context context, String path, final Map<String, String> params, final Map<String, String> headers) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = HOST + path;

        final int type = Request.Method.GET;

        StringBuilder stringParams = new StringBuilder("?");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            stringParams.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        StringRequest stringRequest = RequestApi.getStringRequest(type, url + stringParams.toString(), null, headers);

        requestQueue.add(stringRequest);
    }

    private static StringRequest getStringRequest(int type, String url, final String requestBody, final Map<String, String> headers) {
        return new StringRequest(type, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
        };
    }
}
