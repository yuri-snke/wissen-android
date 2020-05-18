package com.example.cttarde.tcc_wissen.activity;

import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
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
import com.example.cttarde.tcc_wissen.request.FollowUsuarioRequest;
import com.example.cttarde.tcc_wissen.utils.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilSeguidorActivity extends AppCompatActivity implements Callback {
    TextView wissen;
    TextView nome;
    TextView idade;
    TextView cel;
    TextView cep;
    TextView cidade;
    TextView estado;
    de.hdodenhof.circleimageview.CircleImageView imageProfile;
    TextView etNome;
    TextView etIdade;
    TextView etCel;
    TextView etCep;
    TextView etCidade;
    TextView etEstado;
    Boolean fon = true;
    ImageButton seguir;
    private String stat;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_seguidor);
        fonts();

        imageProfile = (CircleImageView) findViewById(R.id.profile_image_seguidor);

        final String relID = getIntent().getStringExtra(ID);

        seguir = (ImageButton) findViewById(R.id.seguir);
        stat = getIntent().getStringExtra(Status);

        if (stat != null){
            if (stat.equals("1")) {
                seguir.setImageResource(R.drawable.ic_favorite_gold_36dp);
                Toast.makeText(getApplicationContext(), "Seguindo", Toast.LENGTH_SHORT).show();
                fon = false;
            }else{
                seguir.setImageResource(R.drawable.seguir);
                Toast.makeText(getApplicationContext(), "Deixou de seguir", Toast.LENGTH_SHORT).show();
                fon = true;
            }
        }

        seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fon == true) {

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


                    FollowUsuarioRequest followRequest = new FollowUsuarioRequest(usuarioDTO.getId_User(),relID,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(PerfilSeguidorActivity.this);
                    queue.add(followRequest);


//                    seguir.setImageResource(R.drawable.ic_favorite_gold_36dp);
//
//
//
//                    Toast.makeText(getApplicationContext(), "Seguindo", Toast.LENGTH_SHORT).show();
//
//
//                    fon = false;
                }
                else if (fon == false){
                    System.out.println("Antes do Response");
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");


                                // List<usuarioDTO> resultados = new ArrayList<usuarioDTO>();

                                if (success) {
                                    OnSucesso(0);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };


                    FollowUsuarioRequest followRequest = new FollowUsuarioRequest(usuarioDTO.getId_User(),relID,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(PerfilSeguidorActivity.this);
                    queue.add(followRequest);
                }
          }
        });

        String relNome = getIntent().getStringExtra(Nomes);
        String relIdade = getIntent().getStringExtra(Idade);
        String relCel = getIntent().getStringExtra(Cel);
        String relCidade = getIntent().getStringExtra(Cidade);
        String relEstado = getIntent().getStringExtra(Estado);
        String relCep = getIntent().getStringExtra(Cep);
        String ImagemPerfil = getIntent().getStringExtra(DRAWABLE_RESOURE);

        wissen.setText("Wissen");
        etNome.setText(relNome);
        etEstado.setText(relEstado);
        etIdade.setText(relIdade);
        etCidade.setText(relCidade);
        etCel.setText(relCel);
        etCep.setText(relCep);

        if (ImagemPerfil.equals("SemImagemPerfil") || ImagemPerfil == null )
            imageProfile.setImageResource(R.drawable.profile_image);
        else
            Picasso.with(this.getApplication()).load(IPrequest.getImgPerfil() + ImagemPerfil).into(imageProfile);


    }


    public void fonts(){
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/righteous_regular.ttf");
        wissen = (TextView) findViewById(R.id.toolbar_title);
        wissen.setTypeface(font);

        nome = (TextView) findViewById(R.id.nome_titulo);
        nome.setTypeface(font);

        etNome = (TextView) findViewById((R.id.nome));

        idade = (TextView) findViewById(R.id.idade_titulo);
        idade.setTypeface(font);

        etIdade = (TextView) findViewById(R.id.idade);

        cel = (TextView) findViewById(R.id.cel_titulo);
        cel.setTypeface(font);

        etCel = (TextView) findViewById(R.id.cel);

        cep = (TextView) findViewById(R.id.cep_titulo);
        cep.setTypeface(font);

        etCep = (TextView) findViewById(R.id.cep);

        cidade = (TextView) findViewById(R.id.cidade_titulo);
        cidade.setTypeface(font);

        etCidade = (TextView) findViewById(R.id.cidade);

        estado = (TextView) findViewById(R.id.estado_titulo);
        estado.setTypeface(font);

        etEstado = (TextView) findViewById(R.id.estado);

    }

    @Override
    public void onSucessProx(ArrayList<eventoDTO> arrayEvento) {

    }

    @Override
    public void OnSucesso(int i) {
        if (i == 1) {
            seguir.setImageResource(R.drawable.ic_favorite_gold_36dp);
            Toast.makeText(getApplicationContext(), "Seguindo", Toast.LENGTH_SHORT).show();
            fon = false;
        }else {
            seguir.setImageResource(R.drawable.seguir);
            Toast.makeText(getApplicationContext(), "Deixou de seguir", Toast.LENGTH_SHORT).show();
            fon = true;
        }
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
