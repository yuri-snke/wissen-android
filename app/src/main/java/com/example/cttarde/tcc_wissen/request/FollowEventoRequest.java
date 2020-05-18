package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CT Tarde on 10/11/2017.
 */

public class FollowEventoRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = IPrequest.getSeguidor() + "FollowEvent.php";
    // private static final String LOGIN_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Usuario/Login.php";
    private Map<String, String> params;
    private Map<String, Integer> params2;

    public FollowEventoRequest(String id_User, String id_Evento,String status,String comparecerao,String interessados, Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params2 = new HashMap<>();
        params.put("id_User", id_User);
        params.put("id_Evento", id_Evento);
        params.put("status", status);
        params.put("comparecerao", comparecerao);
        params.put("interessados", interessados);

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }


}
