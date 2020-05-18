package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

public class PerfilEditRequestSenha extends StringRequest{
   private static final String LOGIN_REQUEST_URL = IPrequest.getUsuario() + "AtualizaPerfilSenha.php";
   // private static final String LOGIN_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Usuario/AtualizaPerfilSenha.php";
    private Map<String, String> params;

    public PerfilEditRequestSenha(String id_User,String Senha, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_User", id_User);
        params.put("Senha",Senha);



    }


    @Override
    public Map<String, String> getParams(){
        return params;
    }
}


