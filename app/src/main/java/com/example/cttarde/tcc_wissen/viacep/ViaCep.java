package com.example.cttarde.tcc_wissen.viacep;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;




/**
 * Created by CT Tarde on 14/08/2017.
 */

public class ViaCep  {

    private static final String TAG = "";

    private String CEP;
    private String Endereco;
    private String Complemento;
    private String Lat;
    private String Long;
    private String Cidade;
    private String Estado;
    private String Bairro;





    public ViaCep(){
        this.CEP = null;
        this.Bairro = null;
        this.Cidade = null;
        this.Complemento = null;
        this.Estado = null;
        this.Cidade = null;

    }

    public ViaCep(String cep) throws ViaCepException, JSONException {

        System.out.println("Via?");
        System.out.println("Via do Cep");
        this.buscar(cep);

    }

    private final void buscar(String cep) throws ViaCepException, JSONException {
        this.CEP = cep;

        String url = "http://viacep.com.br/ws/" + cep + "/json/";
        System.out.println(url);
        JSONObject obj = new JSONObject(this.get(url));

        System.out.println("Chegou no Buscar");

        try {
            Document doc = Jsoup.connect("http://maps.googleapis.com/maps/api/geocode/xml?address="+ cep +"&language=pt-BR&sensor=true").get();

            Elements lat = doc.select("geometry").select("location").select("lat");
            Elements lng = doc.select("geometry").select("location").select("lng");

            for (Element Latitude : lat) {

                this.Lat = Latitude.text();
                for (Element Longitude : lng) {
                    this.Long = Longitude.text();

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        this.CEP = obj.getString("cep");
        this.Bairro = obj.getString("bairro");
        this.Cidade = obj.getString("localidade");
        this.Estado = obj.getString("uf");
        this.Complemento = obj.getString("complemento");
        this.Endereco = obj.getString("logradouro");





    }
    /*private final void buscarGeo(String cep){
        this.CEP = cep;



        try {
            Document doc = Jsoup.connect("http://maps.googleapis.com/maps/api/geocode/xml?address="+ cep +"&language=pt-BR&sensor=true").get();

            Elements lat = doc.select("geometry").select("location").select("lat");
            Elements lng = doc.select("geometry").select("location").select("lng");

            for (Element Latitude : lat) {
                for (Element Longitude : lng) {

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }*/



    public String getCEP() {
        return  this.CEP;
    }

    public String getEndereco() {
        return this.Endereco;
    }

    public String getComplemento() {
        return this.Complemento;
    }

    public String getBairro() {
        return this.Bairro;
    }

    public String getCidade() {
        return this.Cidade;
    }

    public String getEstado() {
        return this.Estado;
    }

    public String getLat() { return this.Lat;
        /*this.CEP = cep;



        try {
            Document doc = Jsoup.connect("http://maps.googleapis.com/maps/api/geocode/xml?address="+ cep +"&language=pt-BR&sensor=true").get();

            Elements lat = doc.select("geometry").select("location").select("lat");
            Elements lng = doc.select("geometry").select("location").select("lng");

            for (Element Latitude : lat) {
                for (Element Longitude : lng) {
                    this.LatLong = Latitude.text() + "," + Longitude.text();
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.LatLong;
*/
    }

    public String getLong() {
        return this.Long;
    }

    public final String get(String urlToRead) throws ViaCepException{
        StringBuilder result = new StringBuilder();

        try{
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = rd.readLine()) != null){
                result.append(line);

            }



        } catch (MalformedURLException e) {
            throw new ViaCepException(e.getMessage());
        } catch (ProtocolException e) {
            throw new ViaCepException(e.getMessage());
        } catch (IOException e) {
            throw new ViaCepException(e.getMessage());
        }

        return result.toString();
    }
}
