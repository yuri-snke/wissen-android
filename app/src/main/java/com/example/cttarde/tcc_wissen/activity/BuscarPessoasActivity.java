package com.example.cttarde.tcc_wissen.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.cttarde.tcc_wissen.DTO.CaminhoDTO;
import com.example.cttarde.tcc_wissen.DTO.FollowEventDTO;
import com.example.cttarde.tcc_wissen.DTO.SeguidorDTO;
import com.example.cttarde.tcc_wissen.DTO.eventoDTO;
import com.example.cttarde.tcc_wissen.DTO.listaDTO;
import com.example.cttarde.tcc_wissen.DTO.usuarioDTO;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.request.HomeRequest;
import com.example.cttarde.tcc_wissen.request.PegaSegRequest;
import com.example.cttarde.tcc_wissen.utils.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuscarPessoasActivity extends AppCompatActivity implements Callback {


    private static final String POSITON = "position";
    private static final String DRAWABLE_RESOURE = "resource";
    private static final String Cel = "Cel";
    private static final String Nomes = "nomes";
    private static final String Idade = "idade";
    private static final String Cep = "cep";
    private static final String Estado = "estado";
    private static final String Cidade = "cidade";
    private static final String ID = "ID";
    private static final String Status = "status";


    public static String ImagemPerfilAll[];
    public static String NomeAll[];
    public static String IdadeAll[];
    public static String CelAll[];
    public static String CepAll[];
    public static String CidadeAll[];
    public static String EstadoAll[];
    public static String IdAll[];


    public ArrayList<String> lstNomesSeg_encontrados = new ArrayList<>();
    public ArrayList<String> lstIdade_encontrados = new ArrayList<>();
    public ArrayList<String> lstImagemSeg_encontrados = new ArrayList<>();
    public ArrayList<String> lstCelSeg_encontrados = new ArrayList<>();
    public ArrayList<String> lstCepSeg_encontrados = new ArrayList<>();
    public ArrayList<String> lstCidadeSeg_encontrados = new ArrayList<>();
    public ArrayList<String> lstEstadoSeg_encontrados = new ArrayList<>();
    public ArrayList<String> lstIdSeg_encontrados = new ArrayList<>();


    private String[] lstpessoas;
    private ListView lv;
    private EditText et;
    private ArrayList<String> lstPessoas_Encontradas = new ArrayList<String>();

    ImageButton eventoBusca;
    TextView wissen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_pessoas);


        eventoBusca = (ImageButton) findViewById(R.id.procurar_eventos);
        eventoBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_pessoas = new Intent(BuscarPessoasActivity.this, BuscaEventosActivity.class);
                startActivity(intent_pessoas);
                intent_pessoas.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                BuscarPessoasActivity.this.finish();
            }
        });



        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/righteous_regular.ttf");
        wissen = (TextView) findViewById(R.id.toolbar_title);
        wissen.setTypeface(font);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation_bot);

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.action_perfil:
                                Intent intent_perfil = new Intent(BuscarPessoasActivity.this, PerfilActivity.class);
                                startActivity(intent_perfil);
                                intent_perfil.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                BuscarPessoasActivity.this.finish();
                                break;

                            case R.id.action_seguidores:
                                Intent intent_seguidores = new Intent(BuscarPessoasActivity.this, EventosSeguidosActivity.class);
                                startActivity(intent_seguidores);
                                intent_seguidores.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                BuscarPessoasActivity.this.finish();
                                break;

                            case R.id.action_home:
                                Intent intent_home = new Intent(BuscarPessoasActivity.this, HomeClassActivity.class);
                                startActivity(intent_home);
                                intent_home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                BuscarPessoasActivity.this.finish();
                                break;
                        }

                        return true;
                    }
                });

        bottomNavigationView.getMenu().getItem(3).setChecked(true);


        PegaALista(CaminhoDTO.getRiseRequest());

    }
    ArrayList<SeguidorDTO> ArrayPessoa = new ArrayList<SeguidorDTO>();
    public ArrayList<SeguidorDTO> PegaALista(String sql) {

        System.out.println("Antes do Response");
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Dps do Response");
                int w = 0;
                try {
                    JSONArray json = new JSONArray(response);

                    ArrayPessoa.clear();



                    while (w < json.length()) {
                        JSONObject object = json.getJSONObject(w);
                        SeguidorDTO pessoa = new SeguidorDTO();

                        pessoa.setNomeSeg(object.getString("Nome"));
                        pessoa.setId(object.getString("id_User"));
                        pessoa.setCEP(object.getString("CEP"));
                        pessoa.setCidade(object.getString("Cidade"));
                        pessoa.setEstado(object.getString("Estado"));
                        pessoa.setImagemPerfil(object.getString("ImagemPerfil"));
                        pessoa.setIdade(object.getString("Idade"));



                        ArrayPessoa.add(pessoa);



                        listaDTO.setListaRise(ArrayPessoa);


                        w++;

                    }

                    onSucessRise(ArrayPessoa);



                } catch (JSONException | NullPointerException ex) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                }

               // System.out.println(ArrayEvento + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

            }


        };

        System.out.println("Request");
        HomeRequest homeRequest = new HomeRequest(sql, responseListener);
        RequestQueue queue = Volley.newRequestQueue(BuscarPessoasActivity.this);
        queue.add(homeRequest);
        return ArrayPessoa;
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
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AppBarLayout.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public void CarregarEncontrados()
    {
        int textlength = et.getText().length();
        //Limpa o array com os estados encontrados
        //para poder efetuar nova busca
        lstPessoas_Encontradas.clear();
        lstCelSeg_encontrados.clear();
        lstCepSeg_encontrados.clear();
        lstImagemSeg_encontrados.clear();
        lstEstadoSeg_encontrados.clear();
        lstCidadeSeg_encontrados.clear();
        lstIdade_encontrados.clear();
        lstIdSeg_encontrados.clear();


        for (int i = 0; i < NomeAll.length; i++)
        {
            if (textlength <= NomeAll[i].length())
            {
                //Verifica se existe algum item no array original
                //caso encontre é adicionado no array de encontrados
                if(et.getText().toString().equalsIgnoreCase((String)NomeAll[i].subSequence(0, textlength)))
                {
                    lstPessoas_Encontradas.add(NomeAll[i]);
                    lstCelSeg_encontrados.add(CelAll[i]);
                    lstCepSeg_encontrados.add(CepAll[i]);
                    lstImagemSeg_encontrados.add(ImagemPerfilAll[i]);
                    lstEstadoSeg_encontrados.add(EstadoAll[i]);
                    lstCidadeSeg_encontrados.add(CidadeAll[i]);
                    lstIdade_encontrados.add(IdadeAll[i]);
                    lstIdSeg_encontrados.add(IdAll[i]);
                }
            }
        }
    }
    private void Mensagem(String msg)
    {
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
    }

    private static final int TIME_INTERVAL = 2000; //# Milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            LimpaTuto();
            startActivity(new Intent(BuscarPessoasActivity.this, LoginActivity.class));
            return;
        }
        else {
            Toast.makeText(getBaseContext(), "Pressione novamente para sair", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
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
        System.out.println();
        listagem();
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

    private void listagem() {

        ImagemPerfilAll = new String[listaDTO.getListaRise().size()];
        NomeAll = new String[listaDTO.getListaRise().size()];
        IdadeAll = new String[listaDTO.getListaRise().size()];
        CelAll = new String[listaDTO.getListaRise().size()];
        CepAll = new String[listaDTO.getListaRise().size()];
        CidadeAll = new String[listaDTO.getListaRise().size()];
        EstadoAll = new String[listaDTO.getListaRise().size()];
        IdAll = new String[listaDTO.getListaRise().size()];


        int f = 0;
        for (SeguidorDTO usuario : listaDTO.getListaRise()) {

            ImagemPerfilAll[f] = usuario.getImagemPerfil();
            NomeAll[f] = usuario.getNomeSeg();
            IdadeAll[f] = usuario.getIdade();
            CelAll[f] = usuario.getCel();
            CepAll[f] = usuario.getCEP();
            CidadeAll[f] = usuario.getCidade();
            EstadoAll[f] = usuario.getEstado();
            IdAll[f] = usuario.getId();
            f++;
        }


        lv = (ListView) findViewById(R.id.lvPessoas);
        setListViewHeightBasedOnChildren(lv);
        lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        et = (EditText) findViewById(R.id.etProcurarPessoas);
        //Carrega o listview com todos os itens

        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, NomeAll));
        CarregarEncontrados();
        //Adiciona um TextWatcher ao TextView cujos métodos são chamados sempre
        //que este TextView sofra alterações.
        et.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                // Abstract Method of TextWatcher Interface.
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                // Abstract Method of TextWatcher Interface.
            }
            //Evento acionado quando o usuário teclar algo
            //na caixa de texto "Procurar"
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

                CarregarEncontrados();
                //Carrega o listview com os itens encontrados
                lv.setAdapter(new ArrayAdapter<String>(BuscarPessoasActivity.this, R.layout.spinner_item, lstPessoas_Encontradas));
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View view, final int position, long index) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");


                            // List<usuarioDTO> resultados = new ArrayList<usuarioDTO>();

                            if (success) {
                                Intent intent;
                if (usuarioDTO.getId_User().equals(lstIdSeg_encontrados.get(position))) {
                    intent = new Intent(BuscarPessoasActivity.this, PerfilActivity.class);
                }else{
                    intent = new Intent(BuscarPessoasActivity.this, PerfilSeguidorActivity.class);
                }

               // Intent intent = new Intent(BuscarPessoasActivity.this, PerfilSeguidorActivity.class);


                intent.putExtra(DRAWABLE_RESOURE, lstImagemSeg_encontrados.get(position));
                intent.putExtra(Nomes, lstPessoas_Encontradas.get(position));
                intent.putExtra(Cep, lstCepSeg_encontrados.get(position));
                intent.putExtra(Cidade, lstCidadeSeg_encontrados.get(position));
                intent.putExtra(Cel, lstCelSeg_encontrados.get(position));
                intent.putExtra(Idade, lstIdade_encontrados.get(position));
                intent.putExtra(Estado, lstEstadoSeg_encontrados.get(position));
                intent.putExtra(ID, lstIdSeg_encontrados.get(position));
                 intent.putExtra(Status, "1");

                startActivity(intent);
                            }
               else {
                                Intent intent;
                                if (usuarioDTO.getId_User().equals(lstIdSeg_encontrados.get(position))) {
                                    intent = new Intent(BuscarPessoasActivity.this, PerfilActivity.class);
                                }else{
                                    intent = new Intent(BuscarPessoasActivity.this, PerfilSeguidorActivity.class);
                                }

                                // Intent intent = new Intent(BuscarPessoasActivity.this, PerfilSeguidorActivity.class);

                                intent.putExtra(DRAWABLE_RESOURE, lstImagemSeg_encontrados.get(position));
                                intent.putExtra(Nomes, lstPessoas_Encontradas.get(position));
                                intent.putExtra(Cep, lstCepSeg_encontrados.get(position));
                                intent.putExtra(Cidade, lstCidadeSeg_encontrados.get(position));
                                intent.putExtra(Cel, lstCelSeg_encontrados.get(position));
                                intent.putExtra(Idade, lstIdade_encontrados.get(position));
                                intent.putExtra(Estado, lstEstadoSeg_encontrados.get(position));
                                intent.putExtra(ID, lstIdSeg_encontrados.get(position));
                                startActivity(intent);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                String relID = lstIdSeg_encontrados.get(position);

                PegaSegRequest followRequest = new PegaSegRequest(usuarioDTO.getId_User(),relID,responseListener);
                RequestQueue queue = Volley.newRequestQueue(BuscarPessoasActivity.this);
                queue.add(followRequest);
//



            }
        });


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
        listaDTO.setListaOwn(null);
        listaDTO.setListaRise(null);
        listaDTO.setListaNoso(null);
        listaDTO.setListaTrial(null);
        listaDTO.setListaAll(null);

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
}
