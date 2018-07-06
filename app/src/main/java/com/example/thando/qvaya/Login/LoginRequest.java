package com.example.thando.qvaya.Login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "https://qvayaapp.000webhostapp.com/Login.php";
    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("EmployeeID", username);
        params.put("EmployeeID", password);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}