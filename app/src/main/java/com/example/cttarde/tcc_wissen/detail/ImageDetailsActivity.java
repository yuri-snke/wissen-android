package com.example.cttarde.tcc_wissen.detail;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;
import com.example.cttarde.tcc_wissen.DTO.FollowEventDTO;
import com.example.cttarde.tcc_wissen.DTO.SeguidorDTO;
import com.example.cttarde.tcc_wissen.DTO.eventoDTO;
import com.example.cttarde.tcc_wissen.DTO.usuarioDTO;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.request.FollowEventoRequest;
import com.example.cttarde.tcc_wissen.utils.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImageDetailsActivity extends AppCompatActivity  implements Callback{

    private ImageView fruta1;
    private ImageView fruta2;
    private ImageView imageView;
    private Toolbar toolbar;
    private TextView cep;
    private TextView cidade;
    private TextView estado;
    private TextView bairro;
    private TextView data;
    private TextView horario;
    private TextView endereco;
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
    private TextView etLocal;
    private TextView etPreco;
    private TextView etRespon;
    private TextView etTelefoneFixo;
    private TextView etTelefoneMovel;
    private TextView etDescricao;
    private TextView etCidade;
    private TextView etNomeResp;
    private TextView etEstado;
    private TextView etBairro;
    private TextView etCep;

    private ProgressBar spinnerAll;


   public static int Cont;

    private static final String DRAWABLE_RESOURE = "resource";
    private static final String POSITON = "position";
    private static final String Nome = "nome";
    private static final String NomeResp = "nomeresp";
    private static final String Data = "data";
    private static final String HorarioIni = "horarioIni";
    private static final String HorarioTerm = "horarioTerm";
    private static final String Endereco = "endereco";
    private static final String Local = "local";
    private static final String Preco = "preco";
    private static final String Resp = "resp";
    private static final String TelMovel = "telMovel";
    private static final String TelFixo = "telFixo";
    private static final String Cep = "cep";
    private static final String Estado = "estado";
    private static final String Bairro = "bairro";
    private static final String Cidade = "cidade";
    private static final String Descricao = "descricao";
    private static final String Imagens = "imagens";
    private static final String ID = "id";
    private static final String Compas = "Compa";
    private static final String Compes = "Compe";
    private static final String Inters = "Inter";
    private static final String Status = "Status";


    private TextView comparecer;
    private TextView interesse;
    private String stat;

    Boolean inte = true;
    Boolean comp = true;

    int teste = 0;

    FloatingActionButton fab, fab1, fab2, fab3;
    LinearLayout fabLayout1, fabLayout2, fabLayout3;
    View fabBGLayout;
    boolean isFABOpen=false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scrolling);

        imageView = (ImageView) findViewById(R.id.img);
        fruta1 = (ImageView) findViewById(R.id.image_evento_1);
        fruta2 = (ImageView) findViewById(R.id.image_evento_2);


        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/righteous_regular.ttf");


        cep = (TextView) findViewById(R.id.cep);
        cep.setTypeface(font);

        cidade = (TextView) findViewById(R.id.cidade);
        cidade.setTypeface(font);

        estado = (TextView) findViewById(R.id.estado);
        estado.setTypeface(font);

        bairro = (TextView) findViewById(R.id.bairro);
        bairro.setTypeface(font);

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

        etCidade = (TextView) findViewById(R.id.info_cidade);

        etEstado = (TextView) findViewById(R.id.info_estado);
        etBairro = (TextView) findViewById(R.id.info_bairro);
        etCep = (TextView) findViewById(R.id.info_cep);


        String nomeRel = getIntent().getStringExtra(Nome);
        String dataRel = getIntent().getStringExtra(Data);
        String horarioIniRel = getIntent().getStringExtra(HorarioIni);
        String horarioTermRel = getIntent().getStringExtra(HorarioTerm);
        String enderecoRel = getIntent().getStringExtra(Endereco);
        String localRel = getIntent().getStringExtra(Local);
        String cidadeRel = getIntent().getStringExtra(Cidade);
        String estadoRel = getIntent().getStringExtra(Estado);
        String bairroRel = getIntent().getStringExtra(Bairro);
        String cepRel = getIntent().getStringExtra(Cep);
        String precoRel = getIntent().getStringExtra(Preco);
        String responRel = getIntent().getStringExtra(NomeResp);
        String telMovelRel = getIntent().getStringExtra(TelMovel);
        String telFixoRel = getIntent().getStringExtra(TelFixo);
        String descricaoRel = getIntent().getStringExtra(Descricao);
        String ImagemCapa = getIntent().getStringExtra(DRAWABLE_RESOURE);


        String Imagem[] = getIntent().getStringExtra(Imagens).trim().split(";");


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
        etRespon.setText(responRel);
        etTelefoneFixo.setText(telFixoRel);
        etTelefoneMovel.setText(telMovelRel);
        etDescricao.setText(descricaoRel);
        etCidade.setText(cidadeRel);
        etEstado.setText(estadoRel);
        etBairro.setText(bairroRel);
        etCep.setText(cepRel);

        Picasso.with(this.getApplication()).load(IPrequest.getImgCapa() + ImagemCapa).into(imageView);

        if (Imagem.length > 0) {
            if (!Imagem[0].equals("SemImagem")) {
                if (Imagem.length == 1) {
                    Picasso.with(this.getApplication()).load(IPrequest.getImgCapa() + Imagem[0]).into(fruta1);
                    fruta2.setVisibility(View.GONE);
                } else if (Imagem.length > 1) {
                    Picasso.with(this.getApplication()).load(IPrequest.getImgCapa() + Imagem[0]).into(fruta1);
                    Picasso.with(this.getApplication()).load(IPrequest.getImgCapa() + Imagem[1]).into(fruta2);
                }


            } else {
                fruta1.setVisibility(View.GONE);
                fruta2.setVisibility(View.GONE);
            }

        } else {
            fruta2.setVisibility(View.GONE);
            fruta1.setVisibility(View.GONE);
        }

        comparecer = (TextView) findViewById(R.id.comparecer_id);
        interesse = (TextView) findViewById(R.id.interesse_id);

        fabLayout1 = (LinearLayout) findViewById(R.id.fabLayout1);
        fabLayout2 = (LinearLayout) findViewById(R.id.fabLayout2);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        //Fab2 == Interesse
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        final String id_evento = getIntent().getStringExtra(ID);
        final String comparecerao = getIntent().getStringExtra(Compas);
        final String compareceram = getIntent().getStringExtra(Compes);
        final String interessados = getIntent().getStringExtra(Inters);
        stat = getIntent().getStringExtra(Status);



        fab2= (FloatingActionButton) findViewById(R.id.fab2);
        if (stat != null){
            if (stat.equals("1")) {
                fab1.setImageResource(R.drawable.ic_check_white_24dp);

                interesse.setText("Cancelar interesse");
                inte = false;

            } else if (stat.equals("2")) {
                fab2.setImageResource(R.drawable.ic_check_white_24dp);
                comparecer.setText("Cancelar presença");

                comp = false;

            }
    }
        //Fab2 == Comparecerei


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


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaClick("1");
                 String finalStat = stat;

                if (inte) {
                    System.out.println("Antes do Response");
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");


                                // List<usuarioDTO> resultados = new ArrayList<usuarioDTO>();

                                if (success) {
                                    System.out.println("Pós Response,Sucesso");
                                    fab1.setImageResource(R.drawable.ic_check_white_24dp);
                                    Toast.makeText(getApplicationContext(), "Interessado", Toast.LENGTH_SHORT).show();
                                    interesse.setText("Interessado!");
                                    inte = false;

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };

                    System.out.println(id_evento + " ID DO EVENTO");
                    System.out.println(finalStat + "FINAL STATIS");
                    System.out.println(comparecerao + "COmparecerao");
                    System.out.println(interessados + "INTERESSADOS");

                    FollowEventoRequest followRequest = new FollowEventoRequest(usuarioDTO.getId_User(),id_evento, finalStat,comparecerao,interessados,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ImageDetailsActivity.this);
                    queue.add(followRequest);


                }
//                if(!comp){
//                    fab2.setImageResource(R.drawable.ic_event_available_white_24dp);
//                    comp = false;
//                }
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaClick("2");
                String finalStat2 = stat;
                if (comp) {
                    System.out.println("Antes do Response");
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");


                                // List<usuarioDTO> resultados = new ArrayList<usuarioDTO>();

                                if (success) {
                                    OnSucesso(1);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };


                    FollowEventoRequest followRequest = new FollowEventoRequest(usuarioDTO.getId_User(), id_evento, finalStat2, comparecerao, interessados, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ImageDetailsActivity.this);
                    queue.add(followRequest);


                }


//                if(!inte){
//                    fab1.setImageResource(R.drawable.ic_event_note_white_24dp);
//                    inte = false;
//                }
            }
        });

        fabBGLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFABMenu();
            }
        });


    }

    private void TaClick(String click) {
    stat = click;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.denuncia) {
            Intent intent;
            String id_evento = getIntent().getStringExtra(ID);
            intent = new Intent(ImageDetailsActivity.this, Denuncia.class);
            intent.putExtra(ID,id_evento);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
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


    @Override
    public void onSucessProx(ArrayList<eventoDTO> arrayEvento) {

    }

    @Override
    public void OnSucesso(int i) {
        System.out.println("Pós Response,Sucesso");
        fab2.setImageResource(R.drawable.ic_check_white_24dp);
        comparecer.setText("Presença Confirmada!");
        Toast.makeText(getApplicationContext(), "Presença confirmada!", Toast.LENGTH_SHORT).show();
        comp = false;
    }

    @Override
    public void onSucessDest(ArrayList<eventoDTO> arrayEvento) {

    }

    @Override
    public void onSucessRise(ArrayList<SeguidorDTO> arrayEvento) {

    }

    @Override
    public void onSucessSeg(ArrayList<eventoDTO> arrayEvento) {

    }

    @Override
    public void onSucessSug(ArrayList<eventoDTO> arrayEvento) {

    }

    @Override
    public void onSucessList(ArrayList<eventoDTO> arrayEvento) {

    }

    @Override
    public void onSucessListF(ArrayList<FollowEventDTO> arrayEvento) {

    }

    @Override
    public float onSucessCep() {
        return 0;
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
