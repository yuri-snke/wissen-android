package com.example.cttarde.tcc_wissen.request;

/**
 * Created by CT Tarde on 21/11/2017.
 */

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

public class PegaSegRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = IPrequest.getSeguidor() + "Verifica.php";
    // private static final String LOGIN_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Usuario/Login.php";
    private Map<String, String> params;
    private Map<String, Integer> params2;

    public PegaSegRequest(String id_User, String id_follow, Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params2 = new HashMap<>();
        params.put("UserPrim", id_User);
        params.put("UserSecu", id_follow);


    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }


}