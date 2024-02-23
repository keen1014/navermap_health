package com.example.moagym;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import android.provider.ContactsContract;



public class LatlonRequestActivity extends StringRequest {
    final static private String URL = "http://3.85.87.213/Latlon_test.php";
    private Map<String, String> map;

    public LatlonRequestActivity(String Lat, String Lon, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("Lat", Lat);
        map.put("Lon", Lon);
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

