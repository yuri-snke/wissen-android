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
import com.example.cttarde.tcc_wissen.request.HomeRequest;
import com.example.cttarde.tcc_wissen.utils.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EventosSeguidosActivity extends AppCompatActivity implements Callback{

    TextView wissen;

    ListView list;

    public static String ImagensCapaNoso[];
    public static String IdsNoso[];
    public static String NomesNoso[];
    public static String CEPNoso[];
    public static String DataNoso[];
    public static String HorarioIniNoso[];
    public static String HorarioTermNoso[];
    public static String EstadoNoso[];
    public static String CidadeNoso[];
    public static String BairroNoso[];
    public static String EnderecoNoso[];
    public static String LocalNoso[];
    public static String PrecoNoso[];
    public static String ResponsavelNoso[];
    public static String TelMovelNoso[];
    public static String TelFixoNoso[];
    public static String DescricaoNoso[];
    public static String ImagensNoso[];
    public static String IdNoso[];
    public static String CompaNoso[];
    public static String CompeNoso[];
    public static String InterNoso[];
    public static String StatusNoso[];

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
    private static final String Status = "Status";
    private static final String ID = "id";
    private static final String Compas = "Compa";
    private static final String Compes = "Compe";
    private static final String Inters = "Inter";


    ImageButton evento;

    private String[] lsteventos;
    private ListView lv;
    private EditText et;
    private ArrayList<String> lstPessoas_Encontradas = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_seguidos);


           CaminhoDTO dto = new CaminhoDTO();
            PegaALista(dto.getSqlListEvFollSeg());





        evento = (ImageButton) findViewById(R.id.eventos);
        evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_evento = new Intent(EventosSeguidosActivity.this, SeguidoresActivity.class);
                startActivity(intent_evento);
               intent_evento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               EventosSeguidosActivity.this.finish();
                deleteCache(EventosSeguidosActivity.this);
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
                                Intent intent_perfil = new Intent(EventosSeguidosActivity.this, PerfilActivity.class);
                                startActivity(intent_perfil);
                                intent_perfil.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                EventosSeguidosActivity.this.finish();
                                break;
                            case R.id.action_home:
                                Intent intent_home = new Intent(EventosSeguidosActivity.this, HomeClassActivity.class);
                                startActivity(intent_home);
                                intent_home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                EventosSeguidosActivity.this.finish();
                                break;
                            case R.id.action_busca:
                                Intent intent_busca = new Intent(EventosSeguidosActivity.this, BuscaEventosActivity.class);
                                startActivity(intent_busca);
                                intent_busca.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                EventosSeguidosActivity.this.finish();
                                break;
                        }

                        return true;
                    }
                });


        bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    ArrayList<FollowEventDTO> ArrayEvento = new ArrayList<FollowEventDTO>();
    public ArrayList<FollowEventDTO> PegaALista(String sql) {

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

                        String[] eventosSec = new String[50];
                        int count = 0;

                        JSONObject object = json.getJSONObject(w);
                        FollowEventDTO evento2 = new FollowEventDTO();
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
                        evento2.setStatusEvent(object.getString("Status"));
                        evento2.setNome_User(usuarioDTO.getId_User());

                        if (vericaLista(object.getString("id"), eventosSec, count)) {
                            eventosSec[count] = object.getString("id");
                            ArrayEvento.add(evento2);
                            count++;
                        }



                        listaDTO.setListaNoso(ArrayEvento);


                        w++;

                    }
                    onSucessListF(ArrayEvento);


                } catch (JSONException | NullPointerException ex) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                }

            }


        };

        System.out.println("Request");
        HomeRequest homeRequest = new HomeRequest(sql, responseListener);
        RequestQueue queue = Volley.newRequestQueue(EventosSeguidosActivity.this);
        queue.add(homeRequest);
        return ArrayEvento;
    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            LimpaTuto();
            return;
        }
        else {
            Toast.makeText(getBaseContext(), "Pressione novamente para sair", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
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

    }

    @Override
    public void onSucessListF(ArrayList<FollowEventDTO> arrayEvento) {
        if (listaDTO.getListaNoso() != null ) {
            System.out.println("Maué2");
            listagem();
            deleteCache(EventosSeguidosActivity.this);
        }
        else {
            System.out.println("Maué");

        }
    }

    @Override
    public float onSucessCep() {
        return 0;
    }

    private boolean vericaLista(String string, String[] eventos, int count) {
        for (int w = 0; w < count; w++) {
            if (string.equals(eventos[w])) {
                return false;
            }
        }
        return true;
    }

    public void listagem(){

        ImagensCapaNoso = new String[listaDTO.getListaNoso().size()];
        IdsNoso = new String[listaDTO.getListaNoso().size()];
        NomesNoso = new String[listaDTO.getListaNoso().size()];
        CEPNoso = new String[listaDTO.getListaNoso().size()];
        DataNoso = new String[listaDTO.getListaNoso().size()];
        HorarioIniNoso = new String[listaDTO.getListaNoso().size()];
        HorarioTermNoso = new String[listaDTO.getListaNoso().size()];
        EnderecoNoso = new String[listaDTO.getListaNoso().size()];
        EstadoNoso = new String[listaDTO.getListaNoso().size()];
        CidadeNoso = new String[listaDTO.getListaNoso().size()];
        BairroNoso = new String[listaDTO.getListaNoso().size()];
        LocalNoso = new String[listaDTO.getListaNoso().size()];
        PrecoNoso = new String[listaDTO.getListaNoso().size()];
        ResponsavelNoso = new String[listaDTO.getListaNoso().size()];
        TelMovelNoso = new String[listaDTO.getListaNoso().size()];
        TelFixoNoso = new String[listaDTO.getListaNoso().size()];
        DescricaoNoso = new String[listaDTO.getListaNoso().size()];
        ImagensNoso = new String[listaDTO.getListaNoso().size()];
        StatusNoso = new String[listaDTO.getListaNoso().size()];
        IdNoso = new String[listaDTO.getListaNoso().size()];
        CompaNoso = new String[listaDTO.getListaNoso().size()];
        CompeNoso = new String[listaDTO.getListaNoso().size()];
        InterNoso = new String[listaDTO.getListaNoso().size()];



        int f = 0;
        for (FollowEventDTO evento : listaDTO.getListaNoso()) {
            ImagensCapaNoso[f] = evento.getImagemCapa();
            IdsNoso[f] = evento.getId_Evento();
            NomesNoso[f] = evento.getNome();
            CEPNoso[f] = evento.getCEP();
            DataNoso[f] = evento.getDataIni();
            HorarioIniNoso[f] = evento.getHoraIni();
            HorarioTermNoso[f] = evento.getHoraTer();
            EnderecoNoso[f] = evento.getEndereco();
            EstadoNoso[f] = evento.getEstado();
            CidadeNoso[f] = evento.getCidade();
            BairroNoso[f] = evento.getBairro();
            LocalNoso[f] = evento.getLocal();
            PrecoNoso[f] = evento.getPreco();
            ResponsavelNoso[f] = evento.getNomeResp();
            TelMovelNoso[f] = evento.getTelFixo();
            TelFixoNoso[f] = evento.getTelMovel();
            DescricaoNoso[f] = evento.getDescricao();
            ImagensNoso[f] = evento.getImagens();
            StatusNoso[f] = evento.getStatusEvent();
            IdNoso[f] = evento.getId_Evento();
            CompaNoso[f] = evento.getComparecerao();
            CompeNoso[f] = evento.getCompareceram();
            InterNoso[f] = evento.getInteressados();

            f++;

        }


        final String[] itemname = NomesNoso;



//        CustomListEventosSeguidosAdapter adapter = new CustomListEventosSeguidosAdapter(this, itemname, ImagensCapaNoso);
//        list = (ListView)findViewById(R.id.list_Eventos);
//        list.setAdapter(adapter);

        lv = (ListView) findViewById(R.id.list_Eventos);


        lsteventos = new String[] {"Teste 1", "Gustavo Daniel", "Gabriel Moreira", "Yuri Oliveira",
                "André Pessoa", "Jonathan bonfim", "Fernanda Morais", "Juliana Sousa"};
        //Carrega o listview com todos os itens

        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, NomesNoso));
        //Adiciona um TextWatcher ao TextView cujos métodos são chamados sempre
        //que este TextView sofra alterações.


//        setListViewHeightBasedOnChildren(lv);
        lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(EventosSeguidosActivity.this, ImageDetailsActivity.class);
                intent.putExtra(DRAWABLE_RESOURE, ImagensCapaNoso[position]);
                intent.putExtra(Nome, NomesNoso[position]);
                intent.putExtra(Id_Evento, IdsNoso[position]);
                intent.putExtra(CEP,CEPNoso[position]);
                intent.putExtra(Data, DataNoso[position]);
                intent.putExtra(HorarioIni, HorarioIniNoso[position]);
                intent.putExtra(HorarioTerm, HorarioTermNoso[position]);
                intent.putExtra(Endereco, EnderecoNoso[position]);
                intent.putExtra(Estado,EstadoNoso[position]);
                intent.putExtra(Cidade,CidadeNoso[position]);
                intent.putExtra(Bairro,BairroNoso[position]);
                intent.putExtra(Local, LocalNoso[position]);
                intent.putExtra(Preco, PrecoNoso[position]);
                intent.putExtra(NomeResp, ResponsavelNoso[position]);
                intent.putExtra(TelMovel, TelMovelNoso[position]);
                intent.putExtra(TelFixo, TelFixoNoso[position]);
                intent.putExtra(Descricao, DescricaoNoso[position]);
                intent.putExtra(Imagens, ImagensNoso[position]);
                intent.putExtra(Status, StatusNoso[position]);
                intent.putExtra(Compas, CompaNoso[position]);
                intent.putExtra(Compes, CompeNoso[position]);
                intent.putExtra(ID, IdNoso[position]);
                intent.putExtra(Inters, InterNoso[position]);

                startActivity(intent);

                String Selecteditem= EstadoNoso[+position];
                Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_SHORT).show();





            }
        });



    }
    @Override
    public void onLocationChanged(Location location) {

    }

    private void Mensagem(String msg)
    {
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
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
