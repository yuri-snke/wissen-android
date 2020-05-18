package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{
    private static final String LOGIN_REQUEST_URL = IPrequest.getUsuario() + "Login.php";
   // private static final String LOGIN_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Usuario/Login.php";
    private Map<String, String> params;

    public LoginRequest(String Email, String Senha, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Email", Email);
        params.put("Senha", Senha);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}


