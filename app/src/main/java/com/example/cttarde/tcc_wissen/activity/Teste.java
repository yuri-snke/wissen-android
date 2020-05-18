package com.example.cttarde.tcc_wissen.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.cttarde.tcc_wissen.DTO.CaminhoDTO;
import com.example.cttarde.tcc_wissen.DTO.eventoDTO;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.request.HomeRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by CT Tarde on 16/08/2017.
 */

public class Teste extends AppCompatActivity {







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        System.out.println("Antes do Response");
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Dps do Response");

                int w = 0;
                try {


                    JSONArray json = new JSONArray(response);
                    System.out.println(json.length());

                    ArrayList<eventoDTO> teste = new ArrayList<>();
                    while (w < json.length()) {
                        JSONObject object = json.getJSONObject(w);
                        eventoDTO evento = new eventoDTO();
                        evento.setContUni(json.length());
                        evento.setNome(object.getString("Nome"));
                        evento.setDataIni(object.getString("DataInicio"));
                        evento.setDataTer(object.getString("DataTermino"));
                        evento.setHoraIni(object.getString("HoraInicio"));
                        evento.setHoraTer(object.getString("HoraTermino"));
                        evento.setResponsavel(object.getString("Responsavel"));
                        evento.setPreco(object.getString("Preco"));
                        evento.setDescricao(object.getString("Descricao"));
                        evento.setImagens(object.getString("Imagens"));
                        evento.setImagemCapa(object.getString("ImagemCapa"));
                        evento.setCEP(object.getString("CEP"));
                        evento.setLocal(object.getString("Local"));
                        evento.setEndereco(object.getString("Endereco"));
                        evento.setTelFixo(object.getString("TelFixo"));
                        evento.setTelMovel(object.getString("TelMovel"));
                        evento.setEmailCriador(object.getString("EmailCriador"));
                        evento.setTipo(object.getString("Tipo"));
                        evento.setCpfCnpj(object.getString("CpfCnpj"));
                        evento.setPrivado(object.getInt("Privado"));
                        evento.setConfianca(object.getInt("Confianca"));
                        evento.setPremium(object.getInt("Premium"));
                        evento.setInteressados(object.getString("Interessados"));
                        evento.setComparecerao(object.getString("Comparecerao"));
                        evento.setCompareceram(object.getString("Compareceram"));
                        teste.add(evento);
                        w++;


                    }



                } catch (JSONException | NullPointerException ex) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        };


        System.out.println("Request");
        HomeRequest homeRequest = new HomeRequest(CaminhoDTO.getDestaqueRequest(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        queue.add(homeRequest);




    }

/*
ResponseRequest("http://10.104.205.23:8087/Wissen/Android/Eventos/List.php");
*/


    }

 /*
            public static boolean ResponseRequest(String response){
                try {
                    URL url = new URL(response);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    if ((line = rd.readLine()) != null){
                        System.out.println(line);
                        JSONArray json = new JSONArray(line);
                        System.out.println(json.length());
                        ArrayList<eventoDTO> teste = new ArrayList<>();
                        int w = 0;
                        while (w < json.length()){
                            JSONObject object = json.getJSONObject(w);
                            eventoDTO evento = new eventoDTO();
                            evento.setNome(object.getString("Nome"));
                            evento.setDataIni(object.getString("DataInicio"));
                            evento.setDataTer(object.getString("DataTermino"));
                            evento.setHoraIni(object.getString("HoraInicio"));
                            evento.setHoraTer(object.getString("HoraTermino"));
                            evento.setResponsavel(object.getString("Responsavel"));
                            evento.setPreco(object.getString("Preco"));
                            evento.setDescricao(object.getString("Descricao"));
                            evento.setImagens(object.getString("Imagens"));
                            evento.setImagemCapa(object.getString("ImagemCapa"));
                            evento.setCEP(object.getString("CEP"));
                            evento.setLocal(object.getString("Local"));
                            evento.setEndereco(object.getString("Endereco"));
                            evento.setTelFixo(object.getString("TelFixo"));
                            evento.setTelMovel(object.getString("TelMovel"));
                            evento.setEmailCriador(object.getString("EmailCriador"));
                            evento.setTipo(object.getString("Tipo"));
                            evento.setCpfCnpj(object.getString("CpfCnpj"));
                            evento.setPrivado(object.getInt("Privado"));
                            evento.setConfianca(object.getInt("Confianca"));
                            evento.setPremium(object.getInt("Premium"));
                            evento.setInteressados(object.getInt("Interessados"));
                            evento.setComparecerao(object.getInt("Comparecerao"));
                            evento.setCompareceram(object.getInt("Compareceram"));
                            teste.add(evento);
                            w++;



                        }




                    }








                }catch (MalformedURLException | UnsupportedEncodingException | JSONException | NullPointerException ex){
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null,ex);
                } catch (IOException e) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null,e);
                }
               return false;
            }
            */








