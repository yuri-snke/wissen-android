package com.example.cttarde.tcc_wissen.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.cttarde.tcc_wissen.DTO.usuarioDTO;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.request.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private TextView wissen;


    /*A Pagina de Login e a primeira pagina que o usuario ira ver
    *
    * Daqui ele poderá ir para a pagina principal do programa, ao se logar
    *
    * Ele tambem podera ir para a pagina de Cadastro, onde podera criar seu perfil e aproveitar o app
    *
    *
    *
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wissen = (TextView) findViewById(R.id.wissen);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/righteous_regular.ttf");
        wissen.setTypeface(font);


        final EditText etLogin = (EditText) findViewById(R.id.campo_login);
        final EditText etSenha = (EditText) findViewById(R.id.campo_senha);
        final ImageButton btLogin = (ImageButton) findViewById(R.id.button_login);
        final TextView registrar = (TextView) findViewById(R.id.text_cadastro);
        final TextView manual = (TextView) findViewById(R.id.manual);

        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1hzL_CsZnRRI107RRxvuazqIIyRkNESpp/view"));
                startActivity(browserIntent);
            }
        });



        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);


            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
* Botão de de se logar
* Ao clicar nele, os dados preenchidos serão enviados ao banco e comparados
* Se forem iguais, o usuario continuará no programa com seu usuario
*
*Se for errado, o cliente será notificado e poderá tentar acessar novamente
*
*
* */





                final String login = etLogin.getText().toString();
                final String senha = etSenha.getText().toString();
                if (login.equals("") || senha.equals("")) {AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Um ou Mais Campos estão vazios")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();}else{

                    /*Pedido ao Banco*/
                    System.out.println("Antes do Response");
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");


                               // List<usuarioDTO> resultados = new ArrayList<usuarioDTO>();

                                if (success) {

                                    /* Ao se logar, os dados do usuario serão armazenados em uma DTO, para uso futuro  */

                                    usuarioDTO.setId_User(jsonResponse.getString("id_User"));
                                    usuarioDTO.setNome(jsonResponse.getString("Nome"));
                                    usuarioDTO.setIdade(jsonResponse.getString("Idade"));
                                    usuarioDTO.setEmail(jsonResponse.getString("Email"));
                                    usuarioDTO.setSenha(jsonResponse.getString("Senha"));
                                    usuarioDTO.setEstado(jsonResponse.getString("Estado"));
                                    usuarioDTO.setCidade(jsonResponse.getString("Cidade"));
                                    usuarioDTO.setEndereco(jsonResponse.getString("Endereco"));
                                    usuarioDTO.setCEP(jsonResponse.getString("CEP"));
                                    usuarioDTO.setGenero(jsonResponse.getString("Genero"));
                                    usuarioDTO.setImagemPerfil(jsonResponse.getString("imagemPerfil"));
                                    usuarioDTO.setTelCel(jsonResponse.getString("telCel"));
                                    usuarioDTO.setPalavraSecret(jsonResponse.getString("PalavraSecret"));
                                    usuarioDTO.setPreferencias(jsonResponse.getString("Preferencias"));




                                    System.out.println(usuarioDTO.getPreferencias());
                                    if (usuarioDTO.getPreferencias().equals("") || usuarioDTO.getPreferencias().equals("null") || usuarioDTO.getPreferencias().equals(" ") || usuarioDTO.getPreferencias().equals("SemPref")) {
                                        Intent logIntent = new Intent(LoginActivity.this, FirstActivity.class);
                                        LoginActivity.this.startActivity(logIntent);

                                        etLogin.setText("");
                                        etSenha.setText("");
                                    } else {

                                        Intent logIntent = new Intent(LoginActivity.this, HomeClassActivity.class);
                                        LoginActivity.this.startActivity(logIntent);
                                        etLogin.setText("");
                                        etSenha.setText("");
                                    }


                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setMessage("Login Failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };
                    LoginRequest loginRequest = new LoginRequest(login, senha, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(loginRequest);



                }
            }
        });


    }

    private void alerta(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            finish();
            System.exit(1);

            return;
        }
        else {
            Toast.makeText(getBaseContext(), "Pressione novamente para sair", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }
}
