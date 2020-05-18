package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

   private static final String REGISTER_REQUEST_URL = IPrequest.getUsuario() + "Register.php";
    //private static final String REGISTER_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Usuario/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String Nome, String Idade, String Email, String Senha, String Estado, String Cidade, String Endereco, String CEP, String Genero, String telCel, String PalavraSecret, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("Nome", Nome);
        params.put("Idade", Idade);
        params.put("Email", Email);
        params.put("Senha", Senha);
        params.put("Estado", Estado);
        params.put("Cidade", Cidade);
        params.put("Endereco", Endereco);
        params.put("CEP", CEP);
        params.put("Genero", Genero);
        params.put("telCel", telCel);
        params.put("PalavraSecret", PalavraSecret);
    }
    @Override
    public Map<String, String> getParams(){

        return params;
    }
}
