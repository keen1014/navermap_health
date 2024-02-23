package com.example.moagym;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GymlivewRequestActivity extends StringRequest {
        final static private String URL = "http://3.85.87.213/Gymlivews.php";
        private Map<String, String> map;

        public GymlivewRequestActivity(String ID, Response.Listener<String> listener) {
            super(Method.POST, URL, listener, null);
            map = new HashMap<>();
            map.put("ID", ID);
        }


        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
}
