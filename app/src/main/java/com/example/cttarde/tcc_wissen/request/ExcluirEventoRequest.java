package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CT Tarde on 14/11/2017.
 */

public class ExcluirEventoRequest extends StringRequest {

    private static final String ADD_REQUEST_URL = IPrequest.getEvento() + "Excluir.php";

    private Map<String, String> params;

    public ExcluirEventoRequest(String Id, Response.Listener<String> listener) {
        super(Method.POST, ADD_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id",Id);

    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}