package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

public class PerfilEditRequest extends StringRequest{
    private static final String LOGIN_REQUEST_URL = IPrequest.getUsuario() + "AtualizaPerfil.php";
    //private static final String LOGIN_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Usuario/AtualizaPerfil.php";
    private Map<String, String> params;

    public PerfilEditRequest(String id_User,String Nome, String Idade,String Email,String Estado,String Cidade,String Endereco,String CEP,String telCel, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_User", id_User);
        params.put("Nome", Nome);

        params.put("Idade", Idade);
        params.put("Email", Email);
        params.put("Estado", Estado);
        params.put("Cidade", Cidade);
        params.put("Endereco", Endereco);
        params.put("CEP", CEP);
        params.put("telCel", telCel);

    }


    @Override
    public Map<String, String> getParams(){
        return params;
    }
}


