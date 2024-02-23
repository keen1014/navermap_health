package com.example.moagym;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class TextintoRequestActivity extends StringRequest {

    final static private String URL = "http://3.85.87.213/intotext.php";
    private Map<String, String> map;

    public TextintoRequestActivity(String livew, String user_id, String name, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("livew", livew);
        map.put("user_id", user_id);
        map.put("name", name);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

