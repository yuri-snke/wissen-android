package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CT Tarde on 12/09/2017.
 */

public class HomeRequest extends StringRequest {
    private static final String HOME_REQUEST_URL = IPrequest.getEvento() + "List.php";
    //private static final String HOME_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Eventos/List.php";
    private Map<String , String> params;


    public HomeRequest(String jooj, Response.Listener<String> listener){
        super(Method.POST, HOME_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("sql",jooj);




    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
