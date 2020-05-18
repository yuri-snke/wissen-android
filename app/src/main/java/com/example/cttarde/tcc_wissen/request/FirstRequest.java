package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CT Tarde on 28/08/2017.
 */

public class FirstRequest extends StringRequest{
    private static final String FIRST_REQUEST_URL = IPrequest.getUsuario() + "Update.php";
    //private static final String FIRST_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Usuario/Update.php";
    private Map<String, String> params;

    public FirstRequest(String Preferencias, String Email, Response.Listener<String> listener){
        super(Method.POST, FIRST_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Preferencias", Preferencias);
        params.put("Email", Email);
    }


    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
