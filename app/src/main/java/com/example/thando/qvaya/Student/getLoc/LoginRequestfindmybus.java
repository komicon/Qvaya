package com.example.thando.qvaya.Student.getLoc;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequestfindmybus extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "https://qvayaapp.000webhostapp.com/findmybus.php";
    private Map<String, String> params;

    public LoginRequestfindmybus(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("employeeID", username);
        params.put("employeeID", password);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}