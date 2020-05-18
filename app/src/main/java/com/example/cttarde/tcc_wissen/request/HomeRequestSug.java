package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

public class HomeRequestSug extends StringRequest {

    private static final String REGISTER_REQUEST_URL = IPrequest.getEvento() + "ListHighElo.php";
    //private static final String REGISTER_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Eventos/ListHighElo.php";
    private Map<String, String> params;

    public HomeRequestSug(String sql1,String sql2,String sql3,String sql4,String sql5,String sql6,String sql7, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("sql1", sql1);
        params.put("sql2", sql2);
        params.put("sql3", sql3);
        params.put("sql4", sql4);
        params.put("sql5", sql5);
        params.put("sql6", sql6);
        params.put("sql7", sql7);



    }
    @Override
    public Map<String, String> getParams(){

        return params;
    }
}
