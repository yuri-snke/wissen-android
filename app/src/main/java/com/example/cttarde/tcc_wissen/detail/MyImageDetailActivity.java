package com.example.cttarde.tcc_wissen.detail;

import android.animation.Animator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.activity.HomeClassActivity;
import com.example.cttarde.tcc_wissen.request.ExcluirEventoRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MyImageDetailActivity extends AppCompatActivity {

    private ImageView fruta1;
    private ImageView fruta2;
    private ImageView imageView;
    private Toolbar toolbar;
    private TextView data;
    private TextView horario;
    private TextView endereco;
    private TextView bairro;
    private TextView cidade;
    private TextView estado;
    private TextView local;
    private TextView preco;
    private TextView responsavel;
    private TextView telefones;
    private TextView descricao;
    private TextView imagens;
    private TextView etData;
    private TextView etHorarioIni;
    private TextView etHorarioFim;
    private TextView etEndereco;
    private TextView etEstado;
    private TextView etCidade;
    private TextView etBairro;
    private TextView etCEP;
    private TextView etLocal;
    private TextView etPreco;
    private TextView etRespon;
    private TextView etTelefoneFixo;
    private TextView etTelefoneMovel;
    private TextView etDescricao;
    private ProgressBar spinner;
    private static final String Compas = "Compa";
    private static final String Compes = "Compe";
    private static final String Inters = "Inter";


    private static final String POSITON = "position";
    private static final String DRAWABLE_RESOURE = "resource";
    private static final String Id_Evento = "ide";
    private static final String Nome = "nome";
    private static final String NomeResp = "nomeresp";
    private static final String Data = "data";
    private static final String HorarioIni = "horarioIni";
    private static final String HorarioTerm = "horarioTerm";
    private static final String Endereco = "endereco";
    private static final String Bairro = "bairro";
    private static final String Estado = "estado";
    private static final String Cidade = "cidade";
    private static final String CEP = "cep";
    private static final String Local = "local";
    private static final String Preco = "preco";
    private static final String Resp = "resp";
    private static final String TelMovel = "telMovel";
    private static final String TelFixo = "telFixo";
    private static final String Descricao = "descricao";
    private static final String Imagens = "imagens";
    private static final String Imagem = "imagemCapa";

    FloatingActionButton fab, excluir, editar;
    LinearLayout fabLayout1, fabLayout2, fabLayout3;
    View fabBGLayout;
    boolean isFABOpen=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_image_detail);


        imageView = (ImageView)findViewById(R.id.img);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/righteous_regular.ttf");
        data = (TextView) findViewById(R.id.data);
        data.setTypeface(font);

        horario = (TextView) findViewById(R.id.horario);
        horario.setTypeface(font);

        endereco = (TextView) findViewById(R.id.endereco);
        endereco.setTypeface(font);

        local = (TextView) findViewById(R.id.local);
        local.setTypeface(font);

        preco = (TextView) findViewById(R.id.preco);
        preco.setTypeface(font);

        bairro = (TextView) findViewById(R.id.bairro);
        bairro.setTypeface(font);

        cidade = (TextView) findViewById(R.id.cidade);
        cidade.setTypeface(font);

        estado = (TextView) findViewById(R.id.estado);
        estado.setTypeface(font);

        responsavel = (TextView) findViewById(R.id.responsavel);
        responsavel.setTypeface(font);

        telefones = (TextView) findViewById(R.id.telefone);
        telefones.setTypeface(font);

        descricao = (TextView) findViewById(R.id.descricao);
        descricao.setTypeface(font);

        imagens = (TextView) findViewById(R.id.imagens);
        imagens.setTypeface(font);

        imageView = (ImageView)findViewById(R.id.img);
        fruta1 = (ImageView)findViewById(R.id.image_evento_1);
        fruta2 = (ImageView)findViewById(R.id.image_evento_2);

        data = (TextView) findViewById(R.id.data);
        data.setTypeface(font);

        horario = (TextView) findViewById(R.id.horario);
        horario.setTypeface(font);

        endereco = (TextView) findViewById(R.id.endereco);
        endereco.setTypeface(font);

        local = (TextView) findViewById(R.id.local);
        local.setTypeface(font);

        preco = (TextView) findViewById(R.id.preco);
        preco.setTypeface(font);

        responsavel = (TextView) findViewById(R.id.responsavel);
        responsavel.setTypeface(font);

        telefones = (TextView) findViewById(R.id.telefone);
        telefones.setTypeface(font);

        descricao = (TextView) findViewById(R.id.descricao);
        descricao.setTypeface(font);

        imagens = (TextView) findViewById(R.id.imagens);
        imagens.setTypeface(font);

        etCEP = (TextView) findViewById(R.id.cep);
        etCEP.setTypeface(font);

        etEstado = (TextView) findViewById(R.id.estado);
        etEstado.setTypeface(font);

        etCidade = (TextView) findViewById(R.id.cidade);
        etCidade.setTypeface(font);

        etBairro = (TextView) findViewById(R.id.info_bairro);
        etBairro.setTypeface(font);



        //JOOJ

        etData = (TextView) findViewById(R.id.info_data);


        etHorarioIni = (TextView) findViewById(R.id.info_horario_inicio);

        etHorarioFim = (TextView) findViewById(R.id.info_horario_fim);


        etEndereco = (TextView) findViewById(R.id.info_endereco);


        etLocal = (TextView) findViewById(R.id.info_local);


        etPreco = (TextView) findViewById(R.id.info_preco);


        etRespon = (TextView) findViewById(R.id.info_responsavel);


        etTelefoneFixo = (TextView) findViewById(R.id.info_telefone_fixo);
        etTelefoneMovel = (TextView) findViewById(R.id.info_telefone_cel);


        etDescricao = (TextView) findViewById(R.id.info_descricao);

        final String nomeRel = getIntent().getStringExtra(Nome);
        final String dataRel = getIntent().getStringExtra(Data);
        final String horarioIniRel = getIntent().getStringExtra(HorarioIni);
        final String horarioTermRel = getIntent().getStringExtra(HorarioTerm);
        final String enderecoRel = getIntent().getStringExtra(Endereco);
        final String cepRel = getIntent().getStringExtra(CEP);
        final String estadoRel = getIntent().getStringExtra(Estado);
        final String cidadeRel = getIntent().getStringExtra(Cidade);
        final String bairroRel = getIntent().getStringExtra(Bairro);
        final String localRel = getIntent().getStringExtra(Local);
        final String precoRel = getIntent().getStringExtra(Preco);
        final String responRel = getIntent().getStringExtra(NomeResp);
        final String telMovelRel = getIntent().getStringExtra(TelMovel);
        final String telFixoRel = getIntent().getStringExtra(TelFixo);
        final String descricaoRel = getIntent().getStringExtra(Descricao);
        final String ImagemCapa = getIntent().getStringExtra(DRAWABLE_RESOURE);
        final String ImagemJunta = getIntent().getStringExtra(Imagens);

        final String id_evento = getIntent().getStringExtra(Id_Evento);

        final String Imagem[] = getIntent().getStringExtra(Imagens).trim().split(";");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(nomeRel);



        // toolbar.setTitle(nom);
        etData.setText(dataRel);
        etHorarioIni.setText(horarioIniRel);
        etHorarioFim.setText(horarioTermRel);
        etEndereco.setText(enderecoRel);
        etLocal.setText(localRel);
        etPreco.setText(precoRel);
        etBairro.setText(bairroRel);
        etRespon.setText(responRel);
        etTelefoneFixo.setText(telFixoRel);
        etTelefoneMovel.setText(telMovelRel);
        etDescricao.setText(descricaoRel);

        Picasso.with(this.getApplication()).load(IPrequest.getImgCapa() + ImagemCapa).into(imageView);

        if (Imagem.length > 0 ) {
            if (!Imagem[0].equals("SemImagem")) {
                if (Imagem.length == 1){
                    Picasso.with(this.getApplication()).load(IPrequest.getImgCapa() + Imagem[0]).into(fruta1);
                    fruta2.setVisibility(View.GONE);
                }else if (Imagem.length > 1){
                    Picasso.with(this.getApplication()).load(IPrequest.getImgCapa() + Imagem[0]).into(fruta1);
                    Picasso.with(this.getApplication()).load(IPrequest.getImgCapa() + Imagem[1]).into(fruta2);
                }


            }else{ fruta1.setVisibility(View.GONE);
                fruta2.setVisibility(View.GONE);
            }

        }else {
            fruta2.setVisibility(View.GONE);
            fruta1.setVisibility(View.GONE);
        }



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fabLayout1= (LinearLayout) findViewById(R.id.fabLayout1);
        fabLayout2= (LinearLayout) findViewById(R.id.fabLayout2);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        //Fab2 == Interesse
        excluir = (FloatingActionButton) findViewById(R.id.fab1);

        //Fab2 == Comparecerei
        editar= (FloatingActionButton) findViewById(R.id.fab2);

        fabBGLayout=findViewById(R.id.fabBGLayout);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyImageDetailActivity.this);
                builder.setMessage("Tem certeza que gostaria de excluir o evento?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("Antes do Response");
                                Response.Listener<String> responseListener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            System.out.println("Dps do Response");
                                            JSONObject jsonResponse = new JSONObject(response);

                                            boolean success = jsonResponse.getBoolean("success");
                                            if (success) {

                                                Intent intent = new Intent(MyImageDetailActivity.this, HomeClassActivity.class);
                                                MyImageDetailActivity.this.startActivity(intent);


                                            } else {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(MyImageDetailActivity.this);
                                                builder.setMessage("Excluir Failed")
                                                        .setNegativeButton("Retry", null)
                                                        .create()
                                                        .show();

                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                };
                                System.out.println("Request");



                               ExcluirEventoRequest eventoExluirEdit = new ExcluirEventoRequest(id_evento,responseListener);
                                RequestQueue queue = Volley.newRequestQueue(MyImageDetailActivity.this);
                                queue.add(eventoExluirEdit);



                            }
                        })
                        .setNegativeButton("No" ,null)
                        .create()
                        .show();
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyImageDetailActivity.this, MyImageDetailEdit.class);

                intent.putExtra(DRAWABLE_RESOURE, ImagemCapa);
                intent.putExtra(Nome, nomeRel);
                intent.putExtra(Id_Evento, id_evento);
                intent.putExtra(CEP,cepRel);
                intent.putExtra(Data, dataRel);
                intent.putExtra(HorarioIni, horarioIniRel);
                intent.putExtra(HorarioTerm, horarioTermRel);
                intent.putExtra(Endereco, enderecoRel);
                intent.putExtra(Estado,estadoRel);
                intent.putExtra(Cidade,cidadeRel);
                intent.putExtra(Bairro,bairroRel);
                intent.putExtra(Local, localRel);
                intent.putExtra(Preco, precoRel);
                intent.putExtra(Resp, responRel);
                intent.putExtra(TelMovel, telMovelRel);
                intent.putExtra(TelFixo, telFixoRel);
                intent.putExtra(Descricao, descricaoRel);
                intent.putExtra(Imagens, ImagemJunta);



                startActivity(intent);
                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                MyImageDetailActivity.this.finish();

            }
        });

        fabBGLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFABMenu();
            }
        });

//        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_myEvent);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    private void showFABMenu(){
        isFABOpen=true;
        fabLayout1.setVisibility(View.VISIBLE);
        fabLayout2.setVisibility(View.VISIBLE);
//        fabLayout3.setVisibility(View.VISIBLE);
        fabBGLayout.setVisibility(View.VISIBLE);

//        fab.animate().rotationBy(360);
        fabLayout1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabLayout2.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
//        fabLayout3.animate().translationY(-getResources().getDimension(R.dimen.standard_145));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fabBGLayout.setVisibility(View.GONE);
//        fab.animate().rotationBy(-360);
        fabLayout1.animate().translationY(0);
        fabLayout2.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(!isFABOpen){
                    fabLayout1.setVisibility(View.GONE);
                    fabLayout2.setVisibility(View.GONE);
//                    fabLayout3.setVisibility(View.GONE);
                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(isFABOpen){
            closeFABMenu();
        }else{
            super.onBackPressed();
        }
    }
}
