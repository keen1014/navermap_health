package com.example.moagym;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignupRequestActivity extends StringRequest {

    final static private String URL = "http://3.85.87.213/signup_test.php";
    private Map<String, String> map;

    public SignupRequestActivity(String ID, String Password, String Birth, String Gender, String Email, String Add, String Height, String Weight,Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("ID", ID);
        map.put("Password", Password);
        map.put("Birth", Birth);
        map.put("Gender", Gender);
        map.put("Email", Email);
        map.put("Add", Add);
        map.put("Height", Height);
        map.put("Weight", Weight);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
