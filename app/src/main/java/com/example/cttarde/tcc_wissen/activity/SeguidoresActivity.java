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
import android.support.v7.widget.GridLayoutManager;
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
import com.example.cttarde.tcc_wissen.utils.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeguidoresActivity extends AppCompatActivity implements Callback{


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


    public static String ImagemPerfilTrial[];
    public static String NomeTrial[];
    public static String IdadeTrial[];
    public static String CelTrial[];
    public static String CepTrial[];
    public static String CidadeTrial[];
    public static String EstadoTrial[];
    public static String IdTrial[];
    public static String StatusTrial[];


    private String[] lstpessoas;
    private ListView lv;
    private EditText et;
    private ArrayList<String> lstPessoas_Encontradas = new ArrayList<String>();


    ListView list;
    String[] itemname ={
            "Pessoa 1",
            "Pessoa 2",
            "Pessoa 3",
            "Pessoa 4",
            "Pessoa 5",
            "Pessoa 6",
            "Pessoa 7",
            "Pessoa 8"
    };

    Integer[] imgid={
            R.drawable.profile_image,
            R.drawable.profile_image,
            R.drawable.profile_image,
            R.drawable.profile_image,
            R.drawable.profile_image,
            R.drawable.profile_image,
            R.drawable.profile_image,
            R.drawable.profile_image,
    };
    ImageButton evento;

    TextView wissen;

    private GridLayoutManager lLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguidores);




        CaminhoDTO dto = new CaminhoDTO();
       PegaALista(dto.getSqlListSeguidores());

       evento = (ImageButton) findViewById(R.id.seguidores);
        evento.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Intent intent_evento = new Intent(SeguidoresActivity.this, EventosSeguidosActivity.class);
                startActivity(intent_evento);
               intent_evento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                SeguidoresActivity.this.finish();
               deleteCache(SeguidoresActivity.this);
           }
       });

      /*  lv = (ListView) findViewById(R.id.list_Pessoas);
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


        lstpessoas = new String[] {"Victor Mota", "Gustavo Daniel", "Gabriel Moreira", "Yuri Oliveira",
                "André Pessoa", "Jonathan bonfim", "Fernanda Morais", "Juliana Sousa"};
        //Carrega o listview com todos os itens

        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, lstpessoas));
        //Adiciona um TextWatcher ao TextView cujos métodos são chamados sempre
        //que este TextView sofra alterações.

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View view, int position, long index) {
                Mensagem("Você clicou na Pessoa : " + lstPessoas_Encontradas.get(position).toString());
            }
        });
*/




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
                                Intent intent_perfil = new Intent(SeguidoresActivity.this, PerfilActivity.class);
                                startActivity(intent_perfil);
                                intent_perfil.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                SeguidoresActivity.this.finish();
                                break;
                            case R.id.action_home:
                                Intent intent_home = new Intent(SeguidoresActivity.this, HomeClassActivity.class);
                                startActivity(intent_home);
                                intent_home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                SeguidoresActivity.this.finish();
                                break;
                            case R.id.action_busca:
                                Intent intent_busca = new Intent(SeguidoresActivity.this, BuscaEventosActivity.class);
                                startActivity(intent_busca);
                                intent_busca.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                                SeguidoresActivity.this.finish();
                                break;
                        }

                        return true;
                    }
                });


        bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    ArrayList<SeguidorDTO> ArrayPessoa = new ArrayList<SeguidorDTO>();
    public ArrayList<SeguidorDTO> PegaALista(String sql) {


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                int w = 0;
                try {
                    JSONArray json = new JSONArray(response);

                    ArrayPessoa.clear();



                    while (w < json.length()) {
                        JSONObject object = json.getJSONObject(w);
                        SeguidorDTO pessoa = new SeguidorDTO();


                        pessoa.setNomeSeg(object.getString("Nome"));
                        pessoa.setId(object.getString("id_User"));
                        pessoa.setStatus(object.getString("Status"));
                        pessoa.setCEP(object.getString("CEP"));
                        pessoa.setCidade(object.getString("Cidade"));
                        pessoa.setImagemPerfil(object.getString("ImagemPerfil"));
                        pessoa.setEstado(object.getString("Estado"));
                        pessoa.setIdade(object.getString("Idade"));



                        ArrayPessoa.add(pessoa);

                        listaDTO.setListaTrial(ArrayPessoa);

                        w++;

                    }

                    onSucessRise(ArrayPessoa);



                } catch (JSONException | NullPointerException ex) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                }



            }


        };


        HomeRequest homeRequest = new HomeRequest(sql, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SeguidoresActivity.this);
        queue.add(homeRequest);
        return ArrayPessoa;
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
            startActivity(new Intent(SeguidoresActivity.this, LoginActivity.class));
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
        if (listaDTO.getListaTrial() != null ) {
            System.out.println("Maué2");
            listagem();
            deleteCache(SeguidoresActivity.this);
        }
        else {
            System.out.println("Maué");

        }
    }

    public void listagem(){


        ImagemPerfilTrial = new String[listaDTO.getListaTrial().size()];
        NomeTrial = new String[listaDTO.getListaTrial().size()];
        IdadeTrial = new String[listaDTO.getListaTrial().size()];
        CelTrial = new String[listaDTO.getListaTrial().size()];
        CepTrial = new String[listaDTO.getListaTrial().size()];
        CidadeTrial = new String[listaDTO.getListaTrial().size()];
        EstadoTrial = new String[listaDTO.getListaTrial().size()];
        IdTrial = new String[listaDTO.getListaTrial().size()];
        StatusTrial = new String[listaDTO.getListaTrial().size()];




        int f = 0;
        for (SeguidorDTO usuario: listaDTO.getListaTrial()) {

            ImagemPerfilTrial[f] = usuario.getImagemPerfil();
            NomeTrial[f] = usuario.getNomeSeg();
            IdadeTrial[f] = usuario.getIdade();
            CelTrial[f] = usuario.getCel();
            CepTrial[f] = usuario.getCEP();
            CidadeTrial[f] = usuario.getCidade();
            EstadoTrial[f] = usuario.getEstado();
            IdTrial[f] = usuario.getId();
            StatusTrial[f] = usuario.getStatus();

            f++;

        }


        final String[] itemname = NomeTrial;


        lv = (ListView) findViewById(R.id.list_Pessoas);
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


        lstpessoas = new String[] {"Victor Mota", "Gustavo Daniel", "Gabriel Moreira", "Yuri Oliveira",
                "André Pessoa", "Jonathan bonfim", "Fernanda Morais", "Juliana Sousa"};
        //Carrega o listview com todos os itens

        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, NomeTrial));
        //Adiciona um TextWatcher ao TextView cujos métodos são chamados sempre
        //que este TextView sofra alterações.


       /* CustomListSeguidoresAdapter adapter = new CustomListSeguidoresAdapter(this, itemname, ImagemPerfilTrial);
        list = (ListView)findViewById(R.id.list_Pessoas);
        list.setAdapter(adapter);*/

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(SeguidoresActivity.this, PerfilSeguidorActivity.class);
                intent.putExtra(DRAWABLE_RESOURE, ImagemPerfilTrial[position]);
                intent.putExtra(Nomes, NomeTrial[position]);
                intent.putExtra(Idade, IdadeTrial[position]);
                intent.putExtra(Cel,CelTrial[position]);
                intent.putExtra(Cep, CepTrial[position]);
                intent.putExtra(Cidade, CidadeTrial[position]);
                intent.putExtra(Estado, EstadoTrial[position]);
                intent.putExtra(ID, IdTrial[position] );
                intent.putExtra(Status, StatusTrial[position] );


                startActivity(intent);




            }
        });



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

