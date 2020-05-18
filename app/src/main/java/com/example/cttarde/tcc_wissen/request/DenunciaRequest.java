package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

public class DenunciaRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = IPrequest.getDenuncia() + "Denuncia.php";
    //private static final String REGISTER_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Usuario/Register.php";
    private Map<String, String> params;

    public DenunciaRequest(String Email,String Motivo,String Descricao,String id_Evento, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("Email_User", Email);
        params.put("Id_Evento", id_Evento);
        params.put("Motivo", Motivo);
        params.put("Descricao", Descricao);
    }
    @Override
    public Map<String, String> getParams(){

        return params;
    }
}
