package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

public class AddEventoRequest extends StringRequest {
    private static final String ADD_REQUEST_URL = IPrequest.getEvento() + "Cadastro.php";
   //private static final String ADD_REQUEST_URL = "http://10.104.205.23:8087/Wissen/Android/Eventos/Cadastro.php";
   // private static final String ADD_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Eventos/Cadastro.php";
    private Map<String, String> params;

    public AddEventoRequest(String Nome,String NomeRespon, String DataInicio, String DataTermino, String HoraInicio, String HoraTermino, String Preco, String Descricao, String Imagens,
                            String ImagemCapa, String CEP,String Estado,String Cidade,String Bairro,String Local,String Endereco,String TelFixo,String TelMovel,String EmailCriador,String Tipo,String Privado,String Interessados,
                            String Comparecerao,String Compareceram,String Lat,String Long ,String foto1,String NomeFoto1,String foto2,String NomeFoto2,String foto3,String NomeFoto3,String VerificaImagem, Response.Listener<String> listener) {
        super(Method.POST, ADD_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Nome", Nome);
        params.put("Responsavel",NomeRespon);
        params.put("DataInicio", DataInicio);
        params.put("DataTermino", DataTermino);
        params.put("HoraInicio", HoraInicio);
        params.put("HoraTermino", HoraTermino);
        params.put("Preco", Preco);
        params.put("Descricao", Descricao);
        params.put("Imagens", Imagens);
        params.put("ImagemCapa", ImagemCapa);
        params.put("CEP", CEP);
        params.put("Estado",Estado);
        params.put("Cidade",Cidade);
        params.put("Bairro",Bairro);
        params.put("Lat",Lat);
        params.put("Long",Long);
        params.put("Local", Local);
        params.put("Endereco",Endereco);
        params.put("TelFixo", TelFixo);
        params.put("TelMovel", TelMovel);
        params.put("EmailCriador", EmailCriador);
        params.put("Tipo", Tipo);
        params.put("Privado", Privado);
        params.put("foto1", foto1);
        params.put("NomeFoto1", NomeFoto1);
        params.put("foto2", foto2);
        params.put("NomeFoto2", NomeFoto2);
        params.put("foto3", foto3);
        params.put("NomeFoto3", NomeFoto3);
        params.put("VerificaImagens",VerificaImagem);


    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
