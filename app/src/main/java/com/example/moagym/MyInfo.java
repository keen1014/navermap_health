package com.example.moagym;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MyInfo extends StringRequest{
        final static private String URL = "http://3.85.87.213/Info.php";
        private Map<String, String> map;

        public MyInfo(String OLDID, String Email, String ID,String mod_height,String mod_Weight,String mod_add, Response.Listener<String> listener)
        {
            super(Method.POST, URL, listener, null);

            map = new HashMap<>();
            map.put("OLDID", OLDID);
            map.put("Email", Email);
            map.put("ID", ID);
            map.put("Height", mod_height);
            map.put("Weight", mod_Weight);
            map.put("Add", mod_add);

        }
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
}
