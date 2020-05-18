package com.example.cttarde.tcc_wissen.detail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.cttarde.tcc_wissen.DTO.usuarioDTO;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.activity.HomeClassActivity;
import com.example.cttarde.tcc_wissen.request.DenunciaRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Denuncia extends Activity {
    private static final String ID = "id";

    public TextView wissen_denuncia;
    public TextView motivo;
    public TextView obs;
    EditText etDescricao;
    EditText etMotivo;

    public Button send_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/righteous_regular.ttf");
        wissen_denuncia = (TextView) findViewById(R.id.wissen_denuncia);
        wissen_denuncia.setTypeface(font);

        motivo = (TextView) findViewById(R.id.motivo);
        motivo.setTypeface(font);

        obs = (TextView) findViewById(R.id.obs);
        obs.setTypeface(font);

         etDescricao = (EditText) findViewById(R.id.descricao);

         etMotivo = (EditText) findViewById(R.id.etmotivo);

        final String idRel = getIntent().getStringExtra(ID);

        send_btn = (Button) findViewById(R.id.send_btn);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validar()){

                    System.out.println("Antes do Response");
                    System.out.println(idRel);
                    System.out.println(usuarioDTO.getEmail());
                    Response.Listener<String> responseListener = new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response){
                            try {
                                System.out.println("Dps do Response");
                                JSONObject jsonResponse = new JSONObject(response);

                                boolean success = jsonResponse.getBoolean("success");
                                if(success){
                                    Toast.makeText(getApplicationContext(), "Denunciado com sucesso!" ,Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Denuncia.this, HomeClassActivity.class));
                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Denuncia.this);
                                    builder.setMessage("Denuncia Failed")
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
                    DenunciaRequest registerRequest = new DenunciaRequest(usuarioDTO.getEmail(),etMotivo.getText().toString(),etDescricao.getText().toString(),idRel, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Denuncia.this);
                    queue.add(registerRequest);
                }else{AlertDialog.Builder builder = new AlertDialog.Builder(Denuncia.this);
                    builder.setMessage("Campos Necessarios em Branco e/ou Senhas Não Coincidem")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();     }




            }
        });

        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width, height);


    }

    private boolean validar() {
        boolean valid = true;

        String checkBlank;

        String pass = etMotivo.getText().toString();
        String checkPass = etDescricao.getText().toString();


         if(pass.equals("") | checkPass.equals("")) {
             Toast.makeText(Denuncia.this, "Um ou mais Campos estão vazios", Toast.LENGTH_SHORT).show();
             valid = false;
         }


        return valid;
    }


    }

