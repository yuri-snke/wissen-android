package com.example.cttarde.tcc_wissen.activity;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;
import com.example.cttarde.tcc_wissen.DTO.CaminhoDTO;
import com.example.cttarde.tcc_wissen.DTO.FollowEventDTO;
import com.example.cttarde.tcc_wissen.DTO.SeguidorDTO;
import com.example.cttarde.tcc_wissen.DTO.eventoDTO;
import com.example.cttarde.tcc_wissen.DTO.listaDTO;
import com.example.cttarde.tcc_wissen.DTO.usuarioDTO;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.adapter.CustomListAdapter;
import com.example.cttarde.tcc_wissen.detail.MyImageDetailActivity;
import com.example.cttarde.tcc_wissen.request.HomeRequest;
import com.example.cttarde.tcc_wissen.utils.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerfilActivity extends AppCompatActivity implements Callback {


    ListView list;


    Integer[] imgid={
            R.drawable.aoarlivreicon_01_01,
            R.drawable.baricon_01,
            R.drawable.casamentoicon_01,
            R.drawable.cinemaicon_01,
            R.drawable.eventosculturaisicon_01,
            R.drawable.festasicon_01,
            R.drawable.festivaicon_01,
            R.drawable.criancaicon_01,
    };

    private Toolbar toolbar;

    public static String ImagensCapaOwn[];
    public static String IdsOwn[];
    public static String NomesOwn[];
    public static String CEPOwn[];
    public static String DataOwn[];
    public static String HorarioIniOwn[];
    public static String HorarioTermOwn[];
    public static String EstadoOwn[];
    public static String CidadeOwn[];
    public static String BairroOwn[];
    public static String EnderecoOwn[];
    public static String LocalOwn[];
    public static String PrecoOwn[];
    public static String ResponsavelOwn[];
    public static String TelMovelOwn[];
    public static String TelFixoOwn[];
    public static String DescricaoOwn[];
    public static String ImagensOwn[];

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


    TextView meusEventos;
    TextView wissen;
    TextView nome_titulo;
    TextView idade_titulo;
    TextView cel_titulo;
    TextView cep_titulo;
    TextView cidade_titulo;
    TextView estado_titulo;
    TextView nome;
    TextView idade;
    TextView cel;
    TextView cep;
    TextView cidade;
    TextView estado;
    CircleImageView foto_usu;
    de.hdodenhof.circleimageview.CircleImageView foto_usu_teste;

    Button edit;

    private TextView denuncias;
    private TextView criarEvento;

    Boolean inte = true;
    Boolean comp = true;

    FloatingActionButton fab_sup, denuncia_sup, criar_sup, fab3_sup;
    LinearLayout fabLayout1_sup, fabLayout2_sup;
    View fabBGLayout;
    boolean isFABOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        criarEvento = (TextView)findViewById(R.id.criar_evento);
        denuncias = (TextView)findViewById(R.id.denuncias);

        fabLayout1_sup= (LinearLayout) findViewById(R.id.fabLayout1_adm);
        fabLayout2_sup= (LinearLayout) findViewById(R.id.fabLayout2_adm);
        fab_sup = (FloatingActionButton) findViewById(R.id.fab_adm);

        //Fab2 == Interesse
        denuncia_sup = (FloatingActionButton) findViewById(R.id.fab1_adm);

        //Fab2 == Comparecerei


        fabBGLayout=findViewById(R.id.fabBGLayout_adm);

        fab_sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });





        fabBGLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFABMenu();
            }
        });


        CaminhoDTO dto = new CaminhoDTO();
        PegaALista(dto.getOwnRequest());
//        final String[] itemname = NomesOwn;
//
//        CustomListAdapter adapter = new CustomListAdapter(this, itemname, ImagensCapaOwn);
//        list = (ListView)findViewById(R.id.list);
//        list.setAdapter(adapter);
//        setListViewHeightBasedOnChildren(list);
//
//        list.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent motionEvent) {
//                v.getParent().requestDisallowInterceptTouchEvent(false);
//                return false;
//            }
//        });
//
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                startActivity(new Intent(PerfilActivity.this, MyImageDetailActivity.class));
//
//                String Selecteditem= itemname[+position];
//                Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_SHORT).show();
//
//            }
//        });
        usuarioDTO usuario = new usuarioDTO();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/righteous_regular.ttf");
        wissen = (TextView) findViewById(R.id.toolbar_title);
        wissen.setTypeface(font);

        nome_titulo = (TextView) findViewById(R.id.nome_titulo);
        nome_titulo.setTypeface(font);


        idade_titulo = (TextView) findViewById(R.id.idade_titulo);
        idade_titulo.setTypeface(font);

        cel_titulo = (TextView) findViewById(R.id.cel_titulo);
        cel_titulo.setTypeface(font);

        cep_titulo = (TextView) findViewById(R.id.cep_titulo);
        cep_titulo.setTypeface(font);

        cidade_titulo = (TextView) findViewById(R.id.cidade_titulo);
        cidade_titulo.setTypeface(font);

        estado_titulo = (TextView) findViewById(R.id.estado_titulo);
        estado_titulo.setTypeface(font);

        meusEventos = (TextView) findViewById(R.id.meus_eventos);
        meusEventos.setTypeface(font);

        nome = (TextView) findViewById(R.id.nome);
        nome.setText(usuarioDTO.getNome());

        idade = (TextView) findViewById(R.id.idade);
        idade.setText(usuarioDTO.getIdade());

        cel = (TextView) findViewById(R.id.cel);
        cel.setText(usuarioDTO.getTelCel());

        cep = (TextView) findViewById(R.id.cep);
        cep.setText(usuarioDTO.getCEP());

        cidade = (TextView) findViewById(R.id.cidade);
        cidade.setText(usuarioDTO.getCidade());

        estado = (TextView) findViewById(R.id.estado);
        estado.setText(usuarioDTO.getEstado());

        foto_usu_teste = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.profile_image);

        if (usuarioDTO.getImagemPerfil() == null || usuarioDTO.getImagemPerfil().equals("SemImagemPerfil") )
            foto_usu_teste.setImageResource(R.drawable.profile_image);
        else
            Picasso.with(PerfilActivity.this).load(IPrequest.getImgPerfil() + usuarioDTO.getImagemPerfil()).into(foto_usu_teste);




        final FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);
        // VISIBILIDADE DOS 2 BOTOES (Suporte e Usuario)!! ##################################################################################################################

        if (usuarioDTO.getEmail().equals("suporte@blindtech.com")) {
            fab_sup.setVisibility(View.VISIBLE);
            fabAdd.setVisibility(View.INVISIBLE);
        }
        else {
            fabAdd.setVisibility(View.VISIBLE);
            fab_sup.setVisibility(View.INVISIBLE);
        }
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PerfilActivity.this, AdicionarEventoActivity.class));
                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
            }
        });


        /*=============================================================================================================================*/
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation_bot);

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.action_seguidores:
                                Intent intent_seguidores = new Intent(PerfilActivity.this, EventosSeguidosActivity.class);
                                startActivity(intent_seguidores);
                                intent_seguidores.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                PerfilActivity.this.finish();
                                break;

                            case R.id.action_home:
                                Intent intent_home = new Intent(PerfilActivity.this, HomeClassActivity.class);
                                startActivity(intent_home);
                                intent_home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                PerfilActivity.this.finish();
                                break;

                            case R.id.action_busca:
                                Intent intent_compra = new Intent(PerfilActivity.this, BuscaEventosActivity.class);
                                startActivity(intent_compra);
                                intent_compra.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                PerfilActivity.this.finish();
                                break;
                        }

                        return true;
                    }
                });


        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        toolbar = (Toolbar) findViewById(R.id.toolbar_top_perfil);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    public void listagem(){

            ImagensCapaOwn = new String[listaDTO.getListaOwn().size()];
        IdsOwn = new String[listaDTO.getListaOwn().size()];
        NomesOwn = new String[listaDTO.getListaOwn().size()];
        CEPOwn = new String[listaDTO.getListaOwn().size()];
          DataOwn = new String[listaDTO.getListaOwn().size()];
         HorarioIniOwn = new String[listaDTO.getListaOwn().size()];
         HorarioTermOwn = new String[listaDTO.getListaOwn().size()];
         EnderecoOwn = new String[listaDTO.getListaOwn().size()];
        EstadoOwn = new String[listaDTO.getListaOwn().size()];
        CidadeOwn = new String[listaDTO.getListaOwn().size()];
        BairroOwn = new String[listaDTO.getListaOwn().size()];
          LocalOwn = new String[listaDTO.getListaOwn().size()];
          PrecoOwn = new String[listaDTO.getListaOwn().size()];
          ResponsavelOwn = new String[listaDTO.getListaOwn().size()];
          TelMovelOwn = new String[listaDTO.getListaOwn().size()];
          TelFixoOwn = new String[listaDTO.getListaOwn().size()];
         DescricaoOwn = new String[listaDTO.getListaOwn().size()];
          ImagensOwn = new String[listaDTO.getListaOwn().size()];




        int f = 0;
        for (eventoDTO evento : listaDTO.getListaOwn()) {
            ImagensCapaOwn[f] = evento.getImagemCapa();
            IdsOwn[f] = evento.getId_Evento();
            NomesOwn[f] = evento.getNome();
            CEPOwn[f] = evento.getCEP();
            DataOwn[f] = evento.getDataIni();
            HorarioIniOwn[f] = evento.getHoraIni();
            HorarioTermOwn[f] = evento.getHoraTer();
            EnderecoOwn[f] = evento.getEndereco();
            EstadoOwn[f] = evento.getEstado();
            CidadeOwn[f] = evento.getCidade();
            BairroOwn[f] = evento.getBairro();
            LocalOwn[f] = evento.getLocal();
            PrecoOwn[f] = evento.getPreco();
            ResponsavelOwn[f] = evento.getNomeResp();
            TelMovelOwn[f] = evento.getTelFixo();
            TelFixoOwn[f] = evento.getTelMovel();
            DescricaoOwn[f] = evento.getDescricao();
            ImagensOwn[f] = evento.getImagens();
            f++;

        }


        final String[] itemname = NomesOwn;

        CustomListAdapter adapter = new CustomListAdapter(this, itemname, ImagensCapaOwn);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        setListViewHeightBasedOnChildren(list);

        list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                v.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(PerfilActivity.this, MyImageDetailActivity.class);
                intent.putExtra(DRAWABLE_RESOURE, ImagensCapaOwn[position]);
                intent.putExtra(Nome, NomesOwn[position]);
                intent.putExtra(Id_Evento, IdsOwn[position]);
                intent.putExtra(CEP,CEPOwn[position]);
                intent.putExtra(Data, DataOwn[position]);
                intent.putExtra(HorarioIni, HorarioIniOwn[position]);
                intent.putExtra(HorarioTerm, HorarioTermOwn[position]);
                intent.putExtra(Endereco, EnderecoOwn[position]);
                intent.putExtra(Estado,EstadoOwn[position]);
                intent.putExtra(Cidade,CidadeOwn[position]);
                intent.putExtra(Bairro,BairroOwn[position]);
                intent.putExtra(Local, LocalOwn[position]);
                intent.putExtra(Preco, PrecoOwn[position]);
                intent.putExtra(NomeResp, ResponsavelOwn[position]);
                intent.putExtra(TelMovel, TelMovelOwn[position]);
                intent.putExtra(TelFixo, TelFixoOwn[position]);
                intent.putExtra(Descricao, DescricaoOwn[position]);
                intent.putExtra(Imagens, ImagensOwn[position]);

                startActivity(intent);

                String Selecteditem= EstadoOwn[+position];
//                Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_SHORT).show();

            }
        });


    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            LimpaTuto();
            startActivity(new Intent(PerfilActivity.this, LoginActivity.class));
            return;
        }
        else {
            Toast.makeText(getBaseContext(), "Pressione novamente para sair", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.perfil_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.editar_perfil_menu) {
            startActivity(new Intent(PerfilActivity.this, PalavraChaveActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    ArrayList<eventoDTO> ArrayEvento = new ArrayList<eventoDTO>();
    public ArrayList<eventoDTO> PegaALista(String sql) {

        System.out.println("Antes do Response");
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Dps do Response");
                int w = 0;
                try {
                    JSONArray json = new JSONArray(response);

                    ArrayEvento.clear();



                    while (w < json.length()) {
                        JSONObject object = json.getJSONObject(w);
                        eventoDTO evento2 = new eventoDTO();
                        evento2.setContUni(json.length());
                        evento2.setId_Evento(object.getString("id"));
                        evento2.setNome(object.getString("Nome"));
                        evento2.setDataIni(object.getString("DataInicio"));
                        evento2.setDataTer(object.getString("DataTermino"));
                        evento2.setHoraIni(object.getString("HoraInicio"));
                        evento2.setHoraTer(object.getString("HoraTermino"));
                        evento2.setNomeResp(object.getString("Responsavel"));
                        evento2.setPreco(object.getString("Preco"));
                        evento2.setDescricao(object.getString("Descricao"));
                        evento2.setImagens(object.getString("Imagens"));
                        evento2.setImagemCapa(object.getString("ImagemCapa"));
                        evento2.setCEP(object.getString("CEP"));
                        evento2.setBairro(object.getString("Bairro"));
                        evento2.setCidade(object.getString("Cidade"));
                        evento2.setEstado(object.getString("Estado"));
                        evento2.setLocal(object.getString("Local"));
                        evento2.setEndereco(object.getString("Endereco"));
                        evento2.setTelFixo(object.getString("TelFixo"));
                        evento2.setTelMovel(object.getString("TelMovel"));
                        evento2.setEmailCriador(object.getString("EmailCriador"));
                        evento2.setTipo(object.getString("Tipo"));
                        evento2.setCpfCnpj(object.getString("CpfCnpj"));
                        evento2.setPrivado(object.getInt("Privado"));
                        evento2.setConfianca(object.getInt("Confianca"));
                        evento2.setPremium(object.getInt("Premium"));
                        evento2.setInteressados(object.getString("Interessados"));
                        evento2.setComparecerao(object.getString("Comparecerao"));
                        evento2.setCompareceram(object.getString("Compareceram"));


                        evento2.getNome();

                        ArrayEvento.add(evento2);



                      listaDTO.setListaOwn(ArrayEvento);


                        w++;

                    }
                onSucessList(ArrayEvento);


                } catch (JSONException | NullPointerException ex) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                }

            }


        };

        System.out.println("Request");
        HomeRequest homeRequest = new HomeRequest(sql, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PerfilActivity.this);
        queue.add(homeRequest);
        return ArrayEvento;
    }

    @Override
    public void onSucessProx(ArrayList<eventoDTO> arrayEvento) {

    }

    @Override
    public void OnSucesso(int i) {

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
            if (listaDTO.getListaOwn() != null ) {
                System.out.println("Maué2");
                listagem();
            }
        else {
                System.out.println("Maué");
                meusEventos.setVisibility(View.INVISIBLE);
            }
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
    private void LimpaSamerda() {
        eventoDTO evento2 = new eventoDTO();
        evento2.setContUni(0);
        evento2.setNome("");
        evento2.setDataIni("");
        evento2.setDataTer("");
        evento2.setHoraIni("");
        evento2.setHoraTer("");
        evento2.setResponsavel("");
        evento2.setPreco("");
        evento2.setDescricao("");
        evento2.setImagens("");
        evento2.setImagemCapa("");
        evento2.setCEP("");
        evento2.setLocal("");
        evento2.setEndereco("");
        evento2.setTelFixo("");
        evento2.setTelMovel("");
        evento2.setEmailCriador("");
        evento2.setTipo("");
        evento2.setCpfCnpj("");
        evento2.setPrivado(0);
        evento2.setConfianca(0);
        evento2.setPremium(0);
        evento2.setInteressados("");
        evento2.setComparecerao("");
        evento2.setCompareceram("");

        listaDTO.setLista(null);
        listaDTO.setListaSug(null);
        listaDTO.setListaProx(null);
        listaDTO.setListaSeg(null);

    }

    private void LimpaTuto() {
        usuarioDTO usuario = new usuarioDTO();
        SeguidorDTO seguidorDTO = new SeguidorDTO();


        eventoDTO evento2 = new eventoDTO();
        evento2.setContUni(0);

        evento2.setId_Evento("");
        evento2.setNome("");
        evento2.setDataIni("");
        evento2.setDataTer("");
        evento2.setHoraIni("");
        evento2.setHoraTer("");
        evento2.setResponsavel("");
        evento2.setPreco("");
        evento2.setDescricao("");
        evento2.setImagens("");
        evento2.setImagemCapa("");
        evento2.setCEP("");
        evento2.setLocal("");
        evento2.setEndereco("");
        evento2.setTelFixo("");
        evento2.setTelMovel("");
        evento2.setEmailCriador("");
        evento2.setTipo("");
        evento2.setCpfCnpj("");
        evento2.setPrivado(0);
        evento2.setConfianca(0);
        evento2.setPremium(0);
        evento2.setInteressados("");
        evento2.setComparecerao("");
        evento2.setCompareceram("");

        usuarioDTO.setId_User("");
        usuarioDTO.setNome("");
        usuarioDTO.setIdade("");
        usuarioDTO.setDtNascim("");
        usuarioDTO.setIdade("");
        usuarioDTO.setEmail("");
        usuarioDTO.setSenha("");
        usuarioDTO.setEstado("");
        usuarioDTO.setCidade("");
        usuarioDTO.setEndereco("");
        usuarioDTO.setCEP("");
        usuarioDTO.setGenero("");
        usuarioDTO.setTelCel("");
        usuarioDTO.setPreferencias("");
        usuarioDTO.setPalavraSecret("");
        usuarioDTO.setImagemPerfil("");

        seguidorDTO.setNomeSeg("");
        seguidorDTO.setIdade("");
        seguidorDTO.setCel("");
        seguidorDTO.setEstado("");
        seguidorDTO.setCidade("");
        seguidorDTO.setCEP("");
        seguidorDTO.setImagemPerfil("");

        listaDTO.setLista(null);
        listaDTO.setListaSug(null);
        listaDTO.setListaProx(null);
        listaDTO.setListaSeg(null);
        listaDTO.setListaOwn(null);
        listaDTO.setListaRise(null);
        listaDTO.setListaNoso(null);
        listaDTO.setListaTrial(null);
        listaDTO.setListaAll(null);


    }

    private void showFABMenu(){
        isFABOpen=true;
        fabLayout1_sup.setVisibility(View.VISIBLE);
        fabLayout2_sup.setVisibility(View.VISIBLE);
//        fabLayout3.setVisibility(View.VISIBLE);
        fabBGLayout.setVisibility(View.VISIBLE);

//        fab.animate().rotationBy(360);
        fabLayout1_sup.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabLayout2_sup.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
//        fabLayout3.animate().translationY(-getResources().getDimension(R.dimen.standard_145));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fabBGLayout.setVisibility(View.GONE);
//        fab.animate().rotationBy(-360);
        fabLayout1_sup.animate().translationY(0);
        fabLayout2_sup.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(!isFABOpen){
                    fabLayout1_sup.setVisibility(View.GONE);
                    fabLayout2_sup.setVisibility(View.GONE);
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

}
