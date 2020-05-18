package com.example.cttarde.tcc_wissen.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.cttarde.tcc_wissen.DTO.usuarioDTO;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.request.FirstRequest;
import com.example.cttarde.tcc_wissen.viacep.CheckableImageButao;

import org.json.JSONException;
import org.json.JSONObject;


public class FirstActivity extends AppCompatActivity {
    @Nullable
    static boolean valid = Boolean.parseBoolean(null);
    private TextView categ;
    private CheckableImageButao arlivre;
    private CheckableImageButao bar;
    private CheckableImageButao casamento;
    private CheckableImageButao cinema;
    private CheckableImageButao corporativo;
    private CheckableImageButao crianca;
    private CheckableImageButao eventocultural;
    private CheckableImageButao exposicao;
    private CheckableImageButao feiras;
    private CheckableImageButao festa;
    private CheckableImageButao festival;
    private CheckableImageButao formatura;
    private CheckableImageButao gastronomia;
    private CheckableImageButao literatura;
    private CheckableImageButao restaurante;
    private CheckableImageButao social;
    private CheckableImageButao urbano;
    private CheckableImageButao outros;
    private FloatingActionButton Pross;
    private String banco;
    private static String pref1="", pref2="", pref3="";


    String vetor[];
    int cont = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstime_login);


        categ = (TextView) findViewById(R.id.categText);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/righteous_regular.ttf");
        categ.setTypeface(font);



        botoes();

/*
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setVisibility(View.VISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alerta("FON FON FON FON");

            }
        });*/

        System.out.println("Contagem: " +cont);

    }


    public void botoes(){


        this.arlivre = (CheckableImageButao) findViewById(R.id.arlivre);
        this.bar = (CheckableImageButao) findViewById(R.id.bar);
        this.casamento = (CheckableImageButao) findViewById(R.id.casamento);
        this.cinema = (CheckableImageButao) findViewById(R.id.cinema);
        this.corporativo = (CheckableImageButao) findViewById(R.id.corporativo);
        this.crianca = (CheckableImageButao) findViewById(R.id.crianca);
        this.eventocultural = (CheckableImageButao) findViewById(R.id.evento_cultural);
        this.exposicao = (CheckableImageButao) findViewById(R.id.exposicao);
        this.feiras = (CheckableImageButao) findViewById(R.id.feiras);
        this.festa = (CheckableImageButao) findViewById(R.id.festas);
        this.festival = (CheckableImageButao) findViewById(R.id.festival);
        this.formatura = (CheckableImageButao) findViewById(R.id.formatura);
        this.gastronomia = (CheckableImageButao) findViewById(R.id.gastronomia);
        this.literatura = (CheckableImageButao) findViewById(R.id.literatura);
        this.restaurante = (CheckableImageButao) findViewById(R.id.restaurante);
        this.social = (CheckableImageButao) findViewById(R.id.social);
        this.urbano = (CheckableImageButao) findViewById(R.id.urbano);
        this.outros = (CheckableImageButao) findViewById(R.id.outros);
        this.Pross = (FloatingActionButton) findViewById(R.id.fab);

        this.arlivre.setTransitionName("Ar Livre");
        this.bar.setTransitionName("Bar");
        this.casamento.setTransitionName("Casamento");
        this.cinema.setTransitionName("Cinema");
        this.corporativo.setTransitionName("Corporativo");
        this.crianca.setTransitionName("Crianca");
        this.eventocultural.setTransitionName("Evento Cultural");
        this.exposicao.setTransitionName("Exposicao");
        this.feiras.setTransitionName("Feiras");
        this.festa.setTransitionName("Festa");
        this.festival.setTransitionName("Festival");
        this.formatura.setTransitionName("Formatura");
        this.gastronomia.setTransitionName("Gastronomia");
        this.literatura.setTransitionName("Literatura");
        this.restaurante.setTransitionName("Restaurante");
        this.social.setTransitionName("Social");
        this.urbano.setTransitionName("Urbano");
        this.outros.setTransitionName("Outros");






    Pross.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Response.Listener<String> responseListener = new Response.Listener<String>(){
                @Override
                public void onResponse(String response){
                    try {


                        System.out.println("Dps do Response");
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if(success){
                            usuarioDTO.setPreferencias(banco);
                            Intent intent = new Intent(FirstActivity.this, PerfilActivity.class);
                            FirstActivity.this.startActivity(intent);
                            FirstActivity.this.finish();

                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(FirstActivity.this);
                            builder.setMessage("First Failed")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };
            System.out.println(banco);
            banco = pref1 + ";" + pref2 + ";" + pref3;
            System.out.println("Request");
            FirstRequest firstRequest = new FirstRequest(banco,usuarioDTO.getEmail(),responseListener);
            RequestQueue queue = Volley.newRequestQueue(FirstActivity.this);
            queue.add(firstRequest);
            System.out.println(banco);
        }
    });


        arlivre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(Check(arlivre.getTransitionName())){
                    if (cont == 3)
                    Pross.setVisibility(View.VISIBLE);

                    arlivre.setImageResource(R.drawable.aoarlivreicon_01_01);
                }else {
                    if (cont < 3)
                    Pross.setVisibility(View.INVISIBLE);
                    arlivre.setImageResource(R.drawable.aoarlivrepbicon_01_01);
                }

            }
        });

        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Check(bar.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    bar.setImageResource(R.drawable.baricon_01);
                }else{
                    if (cont < 3)
                    Pross.setVisibility(View.INVISIBLE);
                    bar.setImageResource(R.drawable.barpbicon_01);
                }


            }
        });

        casamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check(casamento.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    casamento.setImageResource(R.drawable.casamentoicon_01);
                }else{
                    if (cont < 3)
                    Pross.setVisibility(View.INVISIBLE);
                    casamento.setImageResource(R.drawable.casamentopbicon_01);
                }
            }
        });

        cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check(cinema.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    cinema.setImageResource(R.drawable.cinemaicon_01);
                }else{
                    if (cont < 3)
                    Pross.setVisibility(View.INVISIBLE);
                    cinema.setImageResource(R.drawable.cinemapbicon_01);
                }


            }
        });
        corporativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Check(corporativo.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    corporativo.setImageResource(R.drawable.corporativoicon_01);
                }else{
                    if (cont < 3)
                    Pross.setVisibility(View.INVISIBLE);
                    corporativo.setImageResource(R.drawable.corporativopbicon_01);
                }
            }
        });
        crianca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Check(crianca.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    crianca.setImageResource(R.drawable.criancaicon_01);
                }else{
                    if (cont < 3)
                    Pross.setVisibility(View.INVISIBLE);
                    crianca.setImageResource(R.drawable.criancapbicon_01);
                }
            }
        });
        eventocultural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check(eventocultural.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    eventocultural.setImageResource(R.drawable.eventosculturaisicon_01);
                }else{
                    if (cont < 3)
                        Pross.setVisibility(View.INVISIBLE);
                    eventocultural.setImageResource(R.drawable.eventosculturaispbicon_01);
                }


            }
        });
        exposicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check(exposicao.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    exposicao.setImageResource(R.drawable.exposicaoicon_01);
                }else{
                    if (cont < 3)
                        Pross.setVisibility(View.INVISIBLE);
                    exposicao.setImageResource(R.drawable.exposicaopbicon_01);
                }


            }
        });
        feiras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(Check(feiras.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    feiras.setImageResource(R.drawable.feirasicon_01);
                }else{
                    if (cont < 3)
                        Pross.setVisibility(View.INVISIBLE);
                    feiras.setImageResource(R.drawable.feiraspbicon_01);
                }
            }
        });

        festa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check(festa.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    festa.setImageResource(R.drawable.festasicon_01);
                }else{
                    if (cont < 3)
                        Pross.setVisibility(View.INVISIBLE);
                    festa.setImageResource(R.drawable.festapbicon_01);
                }

            }
        });
        festival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check(festival.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    festival.setImageResource(R.drawable.festivaicon_01);
                }else{
                    if (cont < 3)
                        Pross.setVisibility(View.INVISIBLE);
                    festival.setImageResource(R.drawable.festivalpbicon_01);
                }
            }
        });
        formatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check(formatura.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    formatura.setImageResource(R.drawable.formaturaicon_01);
                }else{
                    if (cont < 3)
                        Pross.setVisibility(View.INVISIBLE);
                    formatura.setImageResource(R.drawable.formaturapbicon_01);
                }
            }
        });
        gastronomia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check(gastronomia.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    gastronomia.setImageResource(R.drawable.gastronomiaicon_01);
                }else{
                    if (cont < 3)
                        Pross.setVisibility(View.INVISIBLE);
                    gastronomia.setImageResource(R.drawable.gastronomiapbicon_01);
                }
            }
        });
        literatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check(literatura.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    literatura.setImageResource(R.drawable.literaturaicon_01);
                }else{
                    if (cont < 3)
                        Pross.setVisibility(View.INVISIBLE);
                    literatura.setImageResource(R.drawable.literaturapbicon_01);
                }
            }
        });
        restaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check(restaurante.getTransitionName())){
                    restaurante.setImageResource(R.drawable.restauranteicon_01);
                }else{
                    if (cont < 3)
                        Pross.setVisibility(View.INVISIBLE);
                    restaurante.setImageResource(R.drawable.restaurantepbicon_01);
                }
            }
        });
        social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check(social.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    social.setImageResource(R.drawable.socialicon_01);
                }else{
                    if (cont < 3)
                        Pross.setVisibility(View.INVISIBLE);
                    social.setImageResource(R.drawable.socialpbicon_01);
                }
            }
        });

        urbano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check(urbano.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    urbano.setImageResource(R.drawable.urbanoicon_01);
                }else{
                    if (cont < 3)
                        Pross.setVisibility(View.INVISIBLE);
                    urbano.setImageResource(R.drawable.urbanopbicon_01);
                }
            }
        });
        outros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check(outros.getTransitionName())){
                    if (cont == 3)
                        Pross.setVisibility(View.VISIBLE);
                    outros.setImageResource(R.drawable.outrosicon_01);
                }else{
                    if (cont < 3)
                        Pross.setVisibility(View.INVISIBLE);
                    outros.setImageResource(R.drawable.outrospbicon_01);
                }
            }
        });


    }




    private boolean Check(String nome) {





        if(cont<=3){

            if(nome.equals(pref1)){


                System.out.println(pref1 + "  Pref1");
                pref1="";
                cont--;
                return false;
            }else if(pref1 == ""){
                if (nome.equals(pref3)){pref3 = "";cont--; return false;}else if (nome.equals(pref2)){pref2 = "";cont--; return false;}

                System.out.println(nome + "nome1");


                pref1=nome;
                cont++;
                return true;
            }
            if(nome.equals(pref2)){
                System.out.println(pref2 + "  Pref2");
                pref2="";
                cont--;
                return false;
            }else if(pref2 == ""){

                if (nome.equals(pref3)){pref3 = "";cont--; return false;}else if (nome.equals(pref2)){pref2 = "";cont--; return false;}


                System.out.println(nome + "nome2");
                pref2=nome;
                cont++;
                return true;
            }
            if(nome.equals(pref3)){
                System.out.println(pref3 + "  Pref3");
                pref3="";
                cont--;
                return false;
            }else if(pref3 == ""){
                if (nome.equals(pref3)){pref3 = "";cont--; return false;}else if (nome.equals(pref2)){pref2 = "";cont--; return false;}


                System.out.println(nome + "nome3");
                pref3=nome;
                cont++;
                return true;
            }

            }

         banco = pref1 + ";" + pref2 + ";" + pref3;
        System.out.println(banco);

        Toast.makeText(FirstActivity.this,"Escolha apenas 3 Categorias", Toast.LENGTH_SHORT).show();
        return false;

    }


    }


