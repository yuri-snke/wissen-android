package com.example.cttarde.tcc_wissen.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CT Tarde on 24/10/2017.
 */

public class EditEventoRequestFoto extends StringRequest {
    private static final String ADD_REQUEST_URL = IPrequest.getEvento() + "AtualizarEventoFotos.php";
    //private static final String ADD_REQUEST_URL = "http://10.104.205.23:8087/Wissen/Android/Eventos/AtualizarEventoFotos.php";
    //private static final String ADD_REQUEST_URL = "http://192.168.30.10:8888/Wissen/Android/Eventos/AtualizarEventoFotos.php";
    private Map<String, String> params;

    public EditEventoRequestFoto(String Id, String Imagens, String ImagemCapa,String foto1,String NomeFoto1,String foto2,String NomeFoto2,String foto3,String NomeFoto3,String VerificaImagem,String VerificaImagemFoto, Response.Listener<String> listener) {
        super(Method.POST, ADD_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Id",Id);

        params.put("Imagens", Imagens);
        params.put("ImagemCapa", ImagemCapa);

        params.put("foto1", foto1);
        params.put("NomeFoto1", NomeFoto1);
        params.put("foto2", foto2);
        params.put("NomeFoto2", NomeFoto2);
        params.put("foto3", foto3);
        params.put("NomeFoto3", NomeFoto3);
        params.put("VerificaImagens",VerificaImagem);
        params.put("VerificaImagensFoto",VerificaImagemFoto);


    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }

}
