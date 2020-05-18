package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

public class PerfilEditRequestFoto extends StringRequest{
   private static final String LOGIN_REQUEST_URL = IPrequest.getUsuario() +  "AtualizaPerfilFoto.php";
   // private static final String LOGIN_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Usuario/AtualizaPerfilFoto.php";
    private Map<String, String> params;

    public PerfilEditRequestFoto(String id_User,String NomeImagem,String ImagemPerfil, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_User", id_User);
        params.put("Imagem",NomeImagem);
        params.put("ImagemPerfil",ImagemPerfil);

    }


    @Override
    public Map<String, String> getParams(){
        return params;
    }
}


