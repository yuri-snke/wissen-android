package com.example.cttarde.tcc_wissen.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
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
import com.example.cttarde.tcc_wissen.adapter.CarouselPagerAdapter;
import com.example.cttarde.tcc_wissen.adapter.CarouselPagerAdapterProx;
import com.example.cttarde.tcc_wissen.adapter.CarouselPagerAdapterSeg;
import com.example.cttarde.tcc_wissen.adapter.CarouselPagerAdapterSug;
import com.example.cttarde.tcc_wissen.fragment.ItemFragment;
import com.example.cttarde.tcc_wissen.fragment.ItemFragmentProx;
import com.example.cttarde.tcc_wissen.fragment.ItemFragmentSeg;
import com.example.cttarde.tcc_wissen.fragment.ItemFragmentSug;
import com.example.cttarde.tcc_wissen.request.HomeRequest;
import com.example.cttarde.tcc_wissen.request.HomeRequestSug;
import com.example.cttarde.tcc_wissen.utils.Callback;
import com.example.cttarde.tcc_wissen.viacep.ViaCep;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*A Activity principal do Aplicativo
*
* Aqui estará o menu para outras partes do aplicativo e todos os eventos dos outros usuarios do aplicativo
*
* Daqui o Cliente poderá ir para todas as outras partes do aplicativo, como seu perfil, seu seguidos, o sistema de busca, etc
*
*
* */

public class HomeClassActivity extends AppCompatActivity implements Callback, LocationListener {


    private static final String TAG = "";
    public CarouselPagerAdapter adapter;
    public CarouselPagerAdapterProx adapter2;
    public CarouselPagerAdapterSeg adapter3;
    public CarouselPagerAdapterSug adapter4;
    public ViewPager pager;
    public ViewPager pager2;
    public ViewPager pager3;
    public ViewPager pager4;
    public SwipeRefreshLayout refresh;
    static Location location = null;
    static boolean first = true;


    public static int FIRST_PAGE = 0;
    public static int FIRST_PAGE2 = 0;
    public static int FIRST_PAGE3 = 0;
    public static int FIRST_PAGE4 = 0;

    public TextView destaque;
    public TextView proximos;
    public TextView seguidos;
    public TextView sugeridos;



    public TextView destaqueTitle;
    public TextView proximosTitle;
    public TextView seguidosTitle;
    public TextView sugeridosTitle;


    public TextView wissen;
    public boolean teste = false;
    public double latitude = 0;
    public double longitude = 0;
    public double latitude2 = 0;
    public double longitude2 = 0;




    public RelativeLayout spinnerLayout;

    private static String msgErro = "Erro de Conexão";
    private static String msgErro1 = "Você não segue ninguem";
    private static String msgErro2 = "Não há eventos proximos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onResume();
                refresh.setRefreshing(false);
            }
        });


        spinnerLayout = (RelativeLayout) findViewById(R.id.progressBarlayout);



        fonts();


            /*O Menu inferior, é a base da navegação do aplicativo*/
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation_bot);

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.action_perfil:
                                Intent intent_perfil = new Intent(HomeClassActivity.this, PerfilActivity.class);
                                startActivity(intent_perfil);
//                                intent_perfil.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
//                                HomeClassActivity.this.finish();
                                break;
                            case R.id.action_seguidores:
                                Intent intent_seguidores = new Intent(HomeClassActivity.this, EventosSeguidosActivity.class);
                                startActivity(intent_seguidores);
//                                intent_seguidores.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
//                                HomeClassActivity.this.finish();
                                break;
                            case R.id.action_busca:
                                Intent intent_busca = new Intent(HomeClassActivity.this, BuscaEventosActivity.class);
                                startActivity(intent_busca);
//                                intent_busca.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
//                                HomeClassActivity.this.finish();
                                break;
                        }

                        return true;
                    }
                });


        bottomNavigationView.getMenu().getItem(1).setChecked(true);

        checkLocationPermission();
        readMyCurrentCoordinates();


        try {
            /*Preeenchimentos das listas e dos carroseis do aplicativo*/

                CaminhoDTO cami = new CaminhoDTO();

            PegaAListaProx(CaminhoDTO.getProximoRequest());

            PegaALista(CaminhoDTO.getDestaqueRequest(), 1);

            PegaAListaSug(cami.getSqlLista1(), cami.getSqlLista2(), cami.getSqlLista3(), cami.getSqlLista4(), cami.getSqlLista5(), cami.getSqlLista6(), cami.getSqlLista7());

            PegaALista(CaminhoDTO.getPEIEI(), 2);


        }catch (NegativeArraySizeException ex){
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(HomeClassActivity.this);
            builder.setMessage("Erro de Conexão, Por Favor tente mais tarde")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }

    }

    @Override
    public void onResume() {

        /*Esse Resume sempre mantem os carroseis atualizados*/
        super.onResume();  // Always call the superclass method first

        CaminhoDTO cami = new CaminhoDTO();

       LimpaSamerda();

        PegaAListaProx(CaminhoDTO.getProximoRequest());

        PegaALista(CaminhoDTO.getDestaqueRequest(), 1);

        PegaALista(CaminhoDTO.getPEIEI(),2);


        PegaAListaSug(cami.getSqlLista1(), cami.getSqlLista2(), cami.getSqlLista3(), cami.getSqlLista4(), cami.getSqlLista5(), cami.getSqlLista6(), cami.getSqlLista7());




    }



    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            LimpaTuto();
            startActivity(new Intent(HomeClassActivity.this, LoginActivity.class));
            return;
        }
        else {
            Toast.makeText(getBaseContext(), "Pressione novamente para sair", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }


    /*Esse Método é responsável por limpar o aplicativo ao usuário dar logout*/



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

    /*O Carrosel dos destaques*/


    public void destaque() {

        final ItemFragment dest = new ItemFragment();


/*O carrosel é montado a partir de uma lista criada*/


        adapter = new CarouselPagerAdapter(this, getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.destaquePager);
        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(FIRST_PAGE);
        pager.setOffscreenPageLimit(5);


            if (ItemFragment.Nomes[0].length() > 27) {
                destaque.setText(ItemFragment.Nomes[0].substring(0, 27) + "...");
            } else {
                destaque.setText(ItemFragment.Nomes[0]);
            }

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(ItemFragment.Nomes[position].length() > 27){
                    destaque.setText(ItemFragment.Nomes[position].substring(0,27)+"...");
                }else{
                    destaque.setText(ItemFragment.Nomes[position]);
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    ArrayList<eventoDTO> ArrayEvento = new ArrayList<>();

    public void setArrayEvento(ArrayList<eventoDTO> arrayEvento) {
        ArrayEvento = arrayEvento;
    }


    /*O Sistema de Lista, com isso os eventos do banco são preenchidos de acordo com sua categoria e armazenados em suas listas correspondentes*/

    public ArrayList<eventoDTO> PegaALista(String sql, final int i) {

try {
    Response.Listener<String> responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            ArrayEvento.clear();

            int w = 0;
            try {
                JSONArray json = new JSONArray(response);

                ArrayList<eventoDTO> Array = new ArrayList<>();

                while (w < json.length()) {

                    /*Esta parte armazena os eventos em uma DTO, que mantem o checking em todas as informações dos eventos*/

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

                    Array.add(evento2);
                    setArrayEvento(Array);

                    /*De acordo com o pedido, os eventos puxados do banco são armazenados em sua lista correspondentes para ser exibida logo após*/

                    if (i == 0) {

                        listaDTO.setListaProx(ArrayEvento);
                    } else if (i == 1) {

                        listaDTO.setLista(ArrayEvento);
                    } else if (i == 2) {

                        listaDTO.setListaSeg(ArrayEvento);
                    }
                    w++;

                }

                /*Após os preenchimentos da lista e graças ao sistema assincronizado do pedido no banco,
                 *a lista tera que ser encaminhada a uma "Fila de Espera", que esperara a lista ser totalmente
                 * preenchida para então ser mostrada no programa */

                if (i == 0) {
                    onSucessProx(ArrayEvento);
                } else if (i == 1) {
                    onSucessDest(ArrayEvento);

                } else if (i == 2) {

                    onSucessSeg(ArrayEvento);

                }


            } catch (JSONException | NullPointerException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }


        }


    };


    HomeRequest homeRequest = new HomeRequest(sql, responseListener);
    RequestQueue queue = Volley.newRequestQueue(HomeClassActivity.this);
    queue.add(homeRequest);

}catch (NegativeArraySizeException ex){
    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(HomeClassActivity.this);
    builder.setMessage("Erro de Conexão, Por Favor tente mais tarde")
            .setNegativeButton("Retry", null)
            .create()
            .show();

}
        return ArrayEvento;
    }

    /*A formação de lista dos eventos próximos, a diferença desta lista para outras é que ela pegará eventos nas redondezas do usuário   */

    public ArrayList<eventoDTO> PegaAListaProx(String sql) {


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                int w = 0;
                try {
                    JSONArray json = new JSONArray(response);

                    ArrayEvento.clear();

                    /*Esta parte armazena os eventos em uma DTO, que mantem o checking em todas as informações dos eventos*/

                    while (w < json.length()) {
                        JSONObject object = json.getJSONObject(w);
                        eventoDTO evento2 = new eventoDTO();
                        evento2.setContUni(json.length());
                        evento2.setNome(object.getString("Nome"));
                        evento2.setDataIni(object.getString("DataInicio"));
                        evento2.setDataTer(object.getString("DataTermino"));
                        evento2.setHoraIni(object.getString("HoraInicio"));
                        evento2.setHoraTer(object.getString("HoraTermino"));
                        evento2.setResponsavel(object.getString("Responsavel"));
                        evento2.setPreco(object.getString("Preco"));
                        evento2.setDescricao(object.getString("Descricao"));
                        evento2.setImagens(object.getString("Imagens"));
                        evento2.setImagemCapa(object.getString("ImagemCapa"));
                        evento2.setCEP(object.getString("CEP"));
                        evento2.setLocal(object.getString("Local"));
                        evento2.setEndereco(object.getString("Endereco"));
                        evento2.setTelFixo(object.getString("TelFixo"));
                        evento2.setTelMovel(object.getString("TelMovel"));
                        evento2.setEmailCriador(object.getString("EmailCriador"));
                        evento2.setTipo(object.getString("Tipo"));
                        evento2.setCpfCnpj(object.getString("CpfCnpj"));
                        evento2.setLat(object.getString("Lat"));
                        evento2.setLong(object.getString("Longi"));
                        evento2.setPrivado(object.getInt("Privado"));
                        evento2.setConfianca(object.getInt("Confianca"));
                        evento2.setPremium(object.getInt("Premium"));
                        evento2.setInteressados(object.getString("Interessados"));
                        evento2.setComparecerao(object.getString("Comparecerao"));
                        evento2.setCompareceram(object.getString("Compareceram"));

   /*De acordo com o pedido, os eventos puxados do banco são armazenados em sua lista correspondentes para ser exibida logo após*/



                        if (distanciaEvento(evento2.getLat(), evento2.getLong()) < 10) {

                            ArrayEvento.add(evento2);
                            listaDTO.setListaProx(ArrayEvento);

                        }
                        w++;

                    }

                    /*Após os preenchimentos da lista e graças ao sistema assincronizado do pedido no banco,
                 *a lista tera que ser encaminhada a uma "Fila de Espera", que esperara a lista ser totalmente
                 * preenchida para então ser mostrada no programa */

                        onSucessProx(ArrayEvento);


                } catch (JSONException | NullPointerException ex) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                }


            }


        };


        HomeRequest homeRequest = new HomeRequest(sql, responseListener);
        RequestQueue queue = Volley.newRequestQueue(HomeClassActivity.this);
        queue.add(homeRequest);
        return ArrayEvento;
    }

    /*O Sistema de Localidade do Aplicativo, com isso os eventos proximos ao usuários
    * Vistos pela sua distância, serão separados e mostrados em outro local*/

    private float distanciaEvento(String Lat, String Long) {
        float distancia = 0;
        if (!Lat.equals("null") && !Long.equals("null")){

            Location loc1 = new Location("");
        Location loc2 = new Location("");

        loc1.setLatitude(latitude);
        loc1.setLongitude(longitude);


        loc2.setLatitude(Double.parseDouble(Lat));
        loc2.setLongitude(Double.parseDouble(Long));
            distancia = loc2.distanceTo(loc1) / 1000;
    }else {
            distancia = 1000000;
        }


    return distancia;
    }

    /*A Lista de Sugeridos. Pelas possibilidades em questão de categorias, a lista de sugeridos terá inumeras variações em seus resultados*/

    public ArrayList<eventoDTO> PegaAListaSug(String sql1, String sql2, String sql3, String sql4, String sql5, String sql6, String sql7) {

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                String[] eventosSec = new String[50];
                int count = 0;
                int w = 0;
                try {
                    JSONArray json = new JSONArray(response);

                    ArrayEvento.clear();

                    ArrayList<eventoDTO> Array = new ArrayList<>();

                    while (w < json.length()) {

                         /*Esta parte armazena os eventos em uma DTO, que mantem o checking em todas as informações dos eventos*/

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

                        /*De acordo com o pedido, os eventos puxados do banco são armazenados em sua lista correspondentes para ser exibida logo após
                        *
                        * Algo que é importante notar é que, ja que o eventos pedidos no banco poderão ser repetidos, é necessario um filtro
                        * Para barrar essa possibilidade de repetição
                        * Isso é feito pelo método abaixo, que compara eventos de uma lista reserva com a lista principal
                        * Se iguais, o evento é excluido
                        * */


                        if (vericaLista(object.getString("id"), eventosSec, count)) {
                            eventosSec[count] = object.getString("id");
                            Array.add(evento2);
                            count++;
                        }


                        listaDTO.setListaSug(Array);


                        w++;

                    }


                    /*Após os preenchimentos da lista e graças ao sistema assincronizado do pedido no banco,
                 *a lista tera que ser encaminhada a uma "Fila de Espera", que esperara a lista ser totalmente
                 * preenchida para então ser mostrada no programa */

                    setArrayEvento(Array);
                    onSucessSug(ArrayEvento);


                } catch (JSONException | NullPointerException ex) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                }


            }


        };


        HomeRequestSug homeRequestsug = new HomeRequestSug(sql1, sql2, sql3, sql4, sql5, sql6, sql7, responseListener);
        RequestQueue queue = Volley.newRequestQueue(HomeClassActivity.this);
        queue.add(homeRequestsug);
        return ArrayEvento;
    }

    /*O Carrossel dos eventos proximos  */

    public void proximos() {
       final ItemFragmentProx proxi = new ItemFragmentProx();

        adapter2 = new CarouselPagerAdapterProx(this, getSupportFragmentManager());
        pager2 = (ViewPager) findViewById(R.id.proximoPager);
        pager2.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
        pager2.setCurrentItem(FIRST_PAGE2);
        pager2.setOffscreenPageLimit(5);


    if (ItemFragmentProx.NomesProx[0].length() > 27) {
        proximos.setText(ItemFragmentProx.NomesProx[0].substring(0, 27) + "...");
    } else {
        proximos.setText(ItemFragmentProx.NomesProx[0]);
    }


        pager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                    if (ItemFragmentProx.NomesProx[position].length() > 27) {
                        proximos.setText(ItemFragmentProx.NomesProx[position].substring(0, 27) + "...");
                    } else {
                        proximos.setText(ItemFragmentProx.NomesProx[position]);
                    }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /*O carrossel dos eventos seguidos */
    public void seguidos() {

        adapter3 = new CarouselPagerAdapterSeg(this, getSupportFragmentManager());
        pager3 = (ViewPager) findViewById(R.id.seguidoPager);
        pager3.setAdapter(adapter3);
        adapter3.notifyDataSetChanged();
        pager3.setCurrentItem(FIRST_PAGE3);
        pager3.setOffscreenPageLimit(5);


            if (ItemFragmentSeg.NomesSeg[0].length() > 27) {
                seguidos.setText(ItemFragmentSeg.NomesSeg[0].substring(0, 27) + "...");
            } else {
                seguidos.setText(ItemFragmentSeg.NomesSeg[0]);
            }



        pager3.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }



            @Override
            public void onPageSelected(int position) {


                    if (ItemFragmentSeg.NomesSeg[position].length() > 27) {
                        seguidos.setText(ItemFragmentSeg.NomesSeg[position].substring(0, 27) + "...");
                    } else {
                        seguidos.setText(ItemFragmentSeg.NomesSeg[position]);
                    }

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /*O Carrossel dos eventos sugeridos */

    public void sugeridos() {

        adapter4 = new CarouselPagerAdapterSug(this, getSupportFragmentManager());
        pager4 = (ViewPager) findViewById(R.id.sugeridoPager);
        pager4.setAdapter(adapter4);
        adapter4.notifyDataSetChanged();
        pager4.setCurrentItem(FIRST_PAGE4);
        pager4.setOffscreenPageLimit(5);

            if (ItemFragmentSug.NomesSug[0].length() > 27) {
                sugeridos.setText(ItemFragmentSug.NomesSug[0].substring(0, 27) + "...");
            } else {
                sugeridos.setText(ItemFragmentSug.NomesSug[0]);
            }


        pager4.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(ItemFragmentSug.NomesSug[position].length() > 27){
                    sugeridos.setText(ItemFragmentSug.NomesSug[position].substring(0,27)+"...");
                }else{
                    sugeridos.setText(ItemFragmentSug.NomesSug[position]);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /*As fontes utilizadas no aplicativo*/

    public void fonts() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/righteous_regular.ttf");
        wissen = (TextView) findViewById(R.id.toolbar_title);
        wissen.setTypeface(font);

        destaque = (TextView) findViewById(R.id.nome_destaque);
        destaque.setTypeface(font);

        proximos = (TextView) findViewById(R.id.nome_proximo);
        proximos.setTypeface(font);

        seguidos = (TextView) findViewById(R.id.nome_seguido);
        seguidos.setTypeface(font);

        sugeridos = (TextView) findViewById(R.id.nome_sugerido);
        sugeridos.setTypeface(font);

        destaqueTitle = (TextView) findViewById(R.id.destaque);
        destaqueTitle.setTypeface(font);

        proximosTitle = (TextView) findViewById(R.id.proximo);
        proximosTitle.setTypeface(font);

        seguidosTitle = (TextView) findViewById(R.id.seguido);
        seguidosTitle.setTypeface(font);

        sugeridosTitle = (TextView) findViewById(R.id.sugerido);
        sugeridosTitle.setTypeface(font);
    }

    /*Callback do aplicativo */

    @Override
    public void onSucessProx(ArrayList<eventoDTO> arrayEventoProx) {


        if (listaDTO.getListaProx() != null)
            proximos();
        else {
            proximosTitle.setText(msgErro2);


        }


    }

    @Override
    public void OnSucesso(int i) {

    }



    @Override
    public void onSucessDest(ArrayList<eventoDTO> arrayEventoDest) {


        if (listaDTO.getLista() != null) {

            destaque();
        }else{
            destaqueTitle.setText(msgErro1);


        }



    }

    @Override
    public void onSucessRise(ArrayList<SeguidorDTO> arrayEvento) {

    }



    @Override
    public void onSucessSeg(ArrayList<eventoDTO> arrayEventoSeg) {


        if (listaDTO.getListaSeg() != null) {
            seguidos();
        }else{
            seguidosTitle.setText(msgErro1);
            pager3.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public void onSucessSug(ArrayList<eventoDTO> arrayEventoSug) {


        if (listaDTO.getListaSug() != null) {

            sugeridos();
        }else{
            sugeridosTitle.setText(msgErro);
            pager4.setVisibility(View.INVISIBLE);

        }


    }

    @Override
    public void onSucessList(ArrayList<eventoDTO> arrayEvento) {

    }

    @Override
    public void onSucessListF(ArrayList<FollowEventDTO> arrayEvento) {

    }

    @Override
    public float onSucessCep() {


        return Float.parseFloat(null);

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(HomeClassActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        readMyCurrentCoordinates();
                        //Request location updates:
                        //locationManager.requestLocationUpdates(provider, 400, 1, this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }


    // GEOLOCATION
    private void readMyCurrentCoordinates() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);



        if (!isGPSEnabled && !isNetworkEnabled) {
            Log.i(TAG, "Incapaz de se conectar a Rede");
        } else {
            if (isNetworkEnabled) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    checkLocationPermission();
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0,  this);
                Log.d(TAG, "Network");
                location = locationManager.getLastKnownLocation( LocationManager.NETWORK_PROVIDER );
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    System.out.println(latitude + "|" + longitude);
                }
            }

            if (isGPSEnabled) {
                if (location == null) {
                    locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 2000, 0,  this);
                    Log.d(TAG, "GPS Enabled");
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        System.out.println(latitude + "|" + longitude);
                    }
                }
            }
        }
        Log.i( TAG, "Lat: "+latitude+" | Long: "+longitude );
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i( TAG, "Lat: "+location.getLatitude()+" | Long: "+location.getLongitude() );
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private class DownloadCEPTask extends AsyncTask<String, Void, ViaCep> {
        @Override
        protected ViaCep doInBackground(String ... ceps) {
            ViaCep vCep = null;



            try {
                vCep = new ViaCep(ceps[0]);


            } finally {
                return vCep;

            }
        }

        @Override
        protected void onPostExecute(ViaCep result) {
            if (result != null) {

                latitude2 = Double.parseDouble(result.getLat());
                longitude2 = Double.parseDouble(result.getLong());




                onSucessCep();


            }else{

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(HomeClassActivity.this);
                builder.setMessage("Não Foi Possivel achar eventos proximos")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();


            }

        }
    }

    private boolean vericaLista(String string, String[] eventos, int count) {
        for (int w = 0; w < count; w++) {
            if (string.equals(eventos[w])) {
                return false;
            }
        }
        return true;
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
        listaDTO.setListaAll(null);
        listaDTO.setListaRise(null);
        listaDTO.setListaNoso(null);
        listaDTO.setListaTrial(null);


    }
}