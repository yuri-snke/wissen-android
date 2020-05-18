package com.example.cttarde.tcc_wissen.activity;

import android.content.Context;
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
import com.example.cttarde.tcc_wissen.detail.ImageDetailsActivity;
import com.example.cttarde.tcc_wissen.detail.MyImageDetailActivity;
import com.example.cttarde.tcc_wissen.request.HomeRequest;
import com.example.cttarde.tcc_wissen.utils.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuscaEventosActivity extends AppCompatActivity implements Callback {

    private static final String POSITON = "position";
    private static final String DRAWABLE_RESOURE = "resource";
    private static final String Nome = "nome";
    private static final String Id_Evento = "ide";
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
    private static final String ID = "id";
    private static final String Compas = "Compa";
    private static final String Compes = "Compe";
    private static final String Inters = "Inter";




    public static String ImagensCapaAll[];
    public static String CepAll[];
    public static String RespAll[];
    public static String HorarioIniAll[];
    public static String HorarioTermAll[];
    public static String EstadoAll[];
    public static String CidadeAll[];
    public static String BairroAll[];
    public static String EnderecoAll[];
    public static String LocalAll[];
    public static String PrecoAll[];
    public static String ResponsavelAll[];
    public static String TelMovelAll[];
    public static String TelFixoAll[];
    public static String DataAll[];
    public static String DescricaoAll[];
    public static String ImagensAll[];
    public static String NomesAll[];
    public static String EmailAll[];
    public static String IdAll[];
    public static String CompAll[];
    public static String IntAll[];


    ImageButton pessoasBusca;
    private ListView lv;
    private EditText et;
    private ArrayList<String> lstNomes_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstIds_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstImagemCapa_Encontradas = new ArrayList<String>();
    private ArrayList<String> lstHrIni_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstHrTer_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstEst_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstBair_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstCid_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstCep_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstEnd_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstLocal_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstPreco_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstResp_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstTelMov_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstTelFix_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstData_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstDescricao_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstImagens_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstEmails_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstComp_Encontrados = new ArrayList<String>();
    private ArrayList<String> lstInt_Encontrados = new ArrayList<String>();


    TextView wissen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_evento);

        PegaALista(CaminhoDTO.getListaAll());


        pessoasBusca = (ImageButton) findViewById(R.id.procurar_pessoas);
        pessoasBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_pessoas = new Intent(BuscaEventosActivity.this, BuscarPessoasActivity.class);
                startActivity(intent_pessoas);
                intent_pessoas.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                BuscaEventosActivity.this.finish();
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
                                Intent intent_perfil = new Intent(BuscaEventosActivity.this, PerfilActivity.class);
                                startActivity(intent_perfil);
                                intent_perfil.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                BuscaEventosActivity.this.finish();
                                break;

                            case R.id.action_seguidores:
                                Intent intent_seguidores = new Intent(BuscaEventosActivity.this, EventosSeguidosActivity.class);
                                startActivity(intent_seguidores);
                                intent_seguidores.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                BuscaEventosActivity.this.finish();
                                break;

                            case R.id.action_home:
                                Intent intent_home = new Intent(BuscaEventosActivity.this, HomeClassActivity.class);
                                startActivity(intent_home);
                                intent_home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                BuscaEventosActivity.this.finish();
                                break;
                        }

                        return true;
                    }
                });

        bottomNavigationView.getMenu().getItem(3).setChecked(true);



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
        lstNomes_Encontrados.clear();
        lstImagemCapa_Encontradas.clear();
        lstHrIni_Encontrados.clear();
        lstHrTer_Encontrados.clear();
        lstEnd_Encontrados.clear();
        lstEst_Encontrados.clear();
        lstBair_Encontrados.clear();
        lstCid_Encontrados.clear();
        lstCep_Encontrados.clear();
        lstLocal_Encontrados.clear();
        lstPreco_Encontrados.clear();
        lstResp_Encontrados.clear();
        lstTelMov_Encontrados.clear();
        lstTelFix_Encontrados.clear();
        lstData_Encontrados.clear();
        lstDescricao_Encontrados.clear();
        lstImagens_Encontrados.clear();
        lstEmails_Encontrados.clear();
        lstIds_Encontrados.clear();
        lstComp_Encontrados.clear();
        lstInt_Encontrados.clear();


        for (int i = 0; i < NomesAll.length; i++)
        {
            if (textlength <= NomesAll[i].length())
            {
                //Verifica se existe algum item no array original
                //caso encontre é adicionado no array de encontrados
                if(et.getText().toString().equalsIgnoreCase((String)NomesAll[i].subSequence(0, textlength)))
                {

                    lstNomes_Encontrados.add(NomesAll[i]);
                    lstImagemCapa_Encontradas.add(ImagensCapaAll[i]);
                    lstHrIni_Encontrados.add(HorarioIniAll[i]);
                    lstHrTer_Encontrados.add(HorarioTermAll[i]);
                    lstEnd_Encontrados.add(EnderecoAll[i]);
                    lstEst_Encontrados.add(EstadoAll[i]);
                    lstCep_Encontrados.add(CepAll[i]);
                    lstCid_Encontrados.add(CidadeAll[i]);
                    lstBair_Encontrados.add(BairroAll[i]);
                    lstLocal_Encontrados.add(LocalAll[i]);
                    lstPreco_Encontrados.add(PrecoAll[i]);
                    lstResp_Encontrados.add(ResponsavelAll[i]);
                    lstTelMov_Encontrados.add(TelMovelAll[i]);
                    lstTelFix_Encontrados.add(TelFixoAll[i]);
                    lstData_Encontrados.add(DataAll[i]);
                    lstDescricao_Encontrados.add(DescricaoAll[i]);
                    lstImagens_Encontrados.add(ImagensAll[i]);
                    lstEmails_Encontrados.add(EmailAll[i]);
                    lstIds_Encontrados.add(IdAll[i]);
                    lstComp_Encontrados.add(CompAll[i]);
                    lstInt_Encontrados.add(IntAll[i]);
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
            startActivity(new Intent(BuscaEventosActivity.this, LoginActivity.class));
            return;
        }
        else {
            Toast.makeText(getBaseContext(), "Pressione novamente para sair", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }
    ArrayList<eventoDTO> ArrayEvento = new ArrayList<eventoDTO>();
    public ArrayList<eventoDTO> PegaALista(String sql) {


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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



                        listaDTO.setListaAll(ArrayEvento);


                        w++;

                    }
                    onSucessList(ArrayEvento);


                } catch (JSONException | NullPointerException ex) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                }


            }


        };


        HomeRequest homeRequest = new HomeRequest(sql, responseListener);
        RequestQueue queue = Volley.newRequestQueue(BuscaEventosActivity.this);
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


        listagem();

        deleteCache(BuscaEventosActivity.this);
    }

    @Override
    public void onSucessListF(ArrayList<FollowEventDTO> arrayEvento) {

    }

    @Override
    public float onSucessCep() {
        return 0;
    }

    private void listagem() {




        ImagensCapaAll = new String[listaDTO.getListaAll().size()];
        NomesAll = new String[listaDTO.getListaAll().size()];
        DataAll = new String[listaDTO.getListaAll().size()];
        HorarioIniAll = new String[listaDTO.getListaAll().size()];
        HorarioTermAll = new String[listaDTO.getListaAll().size()];
        EnderecoAll = new String[listaDTO.getListaAll().size()];
        EstadoAll = new String[listaDTO.getListaAll().size()];
        CidadeAll = new String[listaDTO.getListaAll().size()];
        BairroAll = new String[listaDTO.getListaAll().size()];
        CepAll = new String[listaDTO.getListaAll().size()];
        LocalAll = new String[listaDTO.getListaAll().size()];
        PrecoAll = new String[listaDTO.getListaAll().size()];
        ResponsavelAll = new String[listaDTO.getListaAll().size()];
        TelMovelAll = new String[listaDTO.getListaAll().size()];
        TelFixoAll = new String[listaDTO.getListaAll().size()];
        DescricaoAll = new String[listaDTO.getListaAll().size()];
        ImagensAll = new String[listaDTO.getListaAll().size()];
        EmailAll = new String[listaDTO.getListaAll().size()];
        IdAll = new String[listaDTO.getListaAll().size()];
        IntAll = new String[listaDTO.getListaAll().size()];
        CompAll = new String[listaDTO.getListaAll().size()];

        int f = 0;
        for (eventoDTO evento : listaDTO.getListaAll()) {

            ImagensCapaAll[f] = evento.getImagemCapa();
            NomesAll[f] = evento.getNome();
            DataAll[f] = evento.getDataIni();
            HorarioIniAll[f] = evento.getHoraIni();
            HorarioTermAll[f] = evento.getHoraTer();
            EnderecoAll[f] = evento.getEndereco();
            EstadoAll[f] = evento.getEstado();
            CidadeAll[f] = evento.getCidade();
            BairroAll[f] = evento.getBairro();
            CepAll[f] = evento.getCEP();
            LocalAll[f] = evento.getLocal();
            PrecoAll[f] = evento.getPreco();
            ResponsavelAll[f] = evento.getNomeResp();
            TelMovelAll[f] = evento.getTelFixo();
            TelFixoAll[f] = evento.getTelMovel();
            DescricaoAll[f] = evento.getDescricao();
            ImagensAll[f] = evento.getImagens();
            EmailAll[f] = evento.getEmailCriador();
            IdAll[f] = evento.getId_Evento();
            IntAll[f] = evento.getInteressados();
            CompAll[f] = evento.getComparecerao();

            f++;
        }


        final String[] itemname = NomesAll;


        lv = (ListView) findViewById(R.id.lvEventos);
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


        et = (EditText) findViewById(R.id.etProcurarEventos);


        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, NomesAll));
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
                lv.setAdapter(new ArrayAdapter<String>(BuscaEventosActivity.this, R.layout.spinner_item, lstNomes_Encontrados));
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View view, int position, long index) {
                Intent intent;
                if (usuarioDTO.getEmail().equals(lstEmails_Encontrados.get(position))  || usuarioDTO.getEmail().equals("suporte@blindtech.com")) {
                    intent = new Intent(BuscaEventosActivity.this, MyImageDetailActivity.class);
                }else{
                    intent = new Intent(BuscaEventosActivity.this, ImageDetailsActivity.class);
                }

               // Intent intent = new Intent(BuscaEventosActivity.this, ImageDetailsActivity.class);

                /*
                intent.putExtra(DRAWABLE_RESOURE, ImagensCapaAll[position]);
                intent.putExtra(Nome, NomesAll[position]);
                intent.putExtra(Data, DataAll[position]);
                intent.putExtra(HorarioIni, HorarioIniAll[position]);
                intent.putExtra(HorarioTerm, HorarioTermAll[position]);
                intent.putExtra(Endereco, EnderecoAll[position]);
                intent.putExtra(Local, LocalAll[position]);
                intent.putExtra(Preco, PrecoAll[position]);
                intent.putExtra(Resp, ResponsavelAll[position]);
                intent.putExtra(TelMovel, TelMovelAll[position]);
                intent.putExtra(TelFixo, TelFixoAll[position]);
                intent.putExtra(Descricao, DescricaoAll[position]);
                intent.putExtra(Imagens, ImagensAll[position]);
                startActivity(intent);
*/
                intent.putExtra(DRAWABLE_RESOURE, lstImagemCapa_Encontradas.get(position));
                intent.putExtra(Nome, lstNomes_Encontrados.get(position));
                intent.putExtra(Data, lstData_Encontrados.get(position));
                intent.putExtra(HorarioIni, lstHrIni_Encontrados.get(position));
                intent.putExtra(HorarioTerm, lstHrTer_Encontrados.get(position));
                intent.putExtra(Endereco, lstEnd_Encontrados.get(position));
                intent.putExtra(Estado, lstEst_Encontrados.get(position));
                intent.putExtra(Cidade, lstCid_Encontrados.get(position));
                intent.putExtra(Bairro, lstBair_Encontrados.get(position));
                intent.putExtra(CEP, lstCep_Encontrados.get(position));
                intent.putExtra(Local, lstLocal_Encontrados.get(position));
                intent.putExtra(Preco, lstPreco_Encontrados.get(position));
                intent.putExtra(NomeResp, lstResp_Encontrados.get(position));
                intent.putExtra(TelMovel, lstTelMov_Encontrados.get(position));
                intent.putExtra(TelFixo, lstTelFix_Encontrados.get(position));
                intent.putExtra(Descricao, lstDescricao_Encontrados.get(position));
                intent.putExtra(Imagens, lstImagens_Encontrados.get(position));
                intent.putExtra(ID, lstIds_Encontrados.get(position));
                intent.putExtra(Id_Evento, lstIds_Encontrados.get(position));
                intent.putExtra(Inters, lstInt_Encontrados.get(position));
                intent.putExtra(Compas, lstComp_Encontrados.get(position));

                startActivity(intent);




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

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

}
