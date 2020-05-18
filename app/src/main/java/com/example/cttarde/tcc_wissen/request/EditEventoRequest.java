package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

public class EditEventoRequest extends StringRequest {
    private static final String ADD_REQUEST_URL = IPrequest.getEvento() + "Atualizar.php";
   // private static final String ADD_REQUEST_URL = "http://10.104.205.23:8087/Wissen/Android/Eventos/Atualizar.php";
    //private static final String ADD_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Eventos/Atualizar.php";
    private Map<String, String> params;

    public EditEventoRequest(String Id,String Nome,String NomeRespon, String DataInicio, String DataTermino, String HoraInicio, String HoraTermino, String Preco, String Descricao,
                             String CEP,String Estado,String Cidade,String Bairro,String Local,String Endereco,String TelFixo,String TelMovel,String Tipo,
                             String Privado, Response.Listener<String> listener) {
        super(Method.POST, ADD_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Id",Id);
        params.put("Nome", Nome);
        params.put("Responsavel",NomeRespon);
        params.put("DataInicio", DataInicio);
        params.put("DataTermino", DataTermino);
        params.put("HoraInicio", HoraInicio);
        params.put("HoraTermino", HoraTermino);
        params.put("Preco", Preco);
        params.put("Descricao", Descricao);
        params.put("CEP", CEP);
        params.put("Estado",Estado);
        params.put("Cidade",Cidade);
        params.put("Bairro",Bairro);
        params.put("Local", Local);
        params.put("Endereco",Endereco);
        params.put("TelFixo", TelFixo);
        params.put("TelMovel", TelMovel);
        params.put("Tipo", Tipo);
        params.put("Privado", Privado);
    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
