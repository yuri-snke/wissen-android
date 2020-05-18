package com.example.cttarde.tcc_wissen.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.request.EmailRequest;
import com.example.cttarde.tcc_wissen.request.RegisterRequest;
import com.example.cttarde.tcc_wissen.viacep.CepDTO;
import com.example.cttarde.tcc_wissen.viacep.ViaCep;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends CepDTO{
private TextView cadastrar;

    private EditText etEndereco;
    private EditText etComple;
    private EditText etCidade;
    private EditText etEstado;
    private EditText etCep;
    private EditText etNome;
    private EditText data;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etPalavraSecret;
    private EditText celular;
    private EditText confSenha;
    private EditText LatLong;
    private EditText chave;
    private EditText etIdade;
    private ProgressBar spinner;

    ImageButton mostrarSenha;
    ImageButton mostrarPalavra;
    ImageButton mostrarSenha2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mostrarSenha = (ImageButton) findViewById(R.id.mostrar_senha_olho);
        mostrarSenha.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
//                        senha.setInputType(InputType.TYPE_CLASS_TEXT);
                        confSenha.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
//                        senha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        confSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });

        mostrarSenha2 = (ImageButton) findViewById(R.id.mostrar_senha_olho2);
        mostrarSenha2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        etSenha.setInputType(InputType.TYPE_CLASS_TEXT);
//                        confSenha.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        etSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                        confSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });


        mostrarPalavra = (ImageButton) findViewById(R.id.mostrar_palavra_olho);
        chave  = (EditText) findViewById(R.id.secret);
        mostrarPalavra.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        chave.setInputType(InputType.TYPE_CLASS_TEXT);
//                        confSenha.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        chave.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                        confSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });

        cadastrar = (TextView) findViewById(R.id.cadastrar);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/righteous_regular.ttf");
        cadastrar.setTypeface(font);

        this.etNome = (EditText) findViewById(R.id.Reg_campo_nome);
        this.data = (EditText) findViewById(R.id.Reg_campo_nascimento);
        data.addTextChangedListener(Mask.insert("##/##/####", data));
        this.etEmail = (EditText) findViewById(R.id.Reg_campo_email);
        this.etSenha = (EditText) findViewById(R.id.Reg_campo_senha);
        this.etEstado = (EditText) findViewById(R.id.Reg_estado);
        this.etCidade = (EditText) findViewById(R.id.Reg_cidade);

        this.etCep = (EditText) findViewById(R.id.Reg_cep);
        this.etPalavraSecret = (EditText) findViewById(R.id.secret);
        final RadioButton rgMasc = (RadioButton) findViewById(R.id.radio_masc);
        final RadioButton rgFem = (RadioButton) findViewById(R.id.radio_fem);
        this.confSenha = (EditText) findViewById(R.id.Reg_campo_conf_senha);
        final ImageButton btRegistrar = (ImageButton) findViewById(R.id.button_registrar);
        this.celular = (EditText) findViewById(R.id.Reg_campo_celular);
        this.etEndereco = (EditText) findViewById(R.id.Reg_endereco);
        this.etComple = (EditText) findViewById(R.id.Reg_complemento);
        this.etIdade = (EditText) findViewById(R.id.Reg_idade);
        spinner=(ProgressBar)findViewById(R.id.progressbar);
        spinner.setVisibility(View.GONE);


        etCep.addTextChangedListener(Mask.insert("#####-###", etCep));
        celular.addTextChangedListener(Mask.insert("(##)#####-####", celular));
        String sexo = "";

        if (rgFem.isChecked()) {
            sexo = "Feminino";
        } else if (rgMasc.isChecked()) {
            sexo = "Masculino";
        }

        final String sexoTeste = sexo;

       /* btGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()){
                    etEndereco.setText("");
                    etComple.setText("");
                    etCidade.setText("");
                    etEstado.setText("");


                    String cep = etCep.getText().toString();
                    System.out.println(cep);

                    Pattern pattern = Pattern.compile("^[0-9]{5}-[0-9]{3}$");
                    Matcher matcher = pattern.matcher(cep);

                    if(matcher.find()){
                        new DownloadCEPTask().execute(cep);
                        System.out.println("Donwload passou");
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("CEP Invalido ou Desatualizado")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();

                    }
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Sem Conexão")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();

                }
            }
        });*/

        etCep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    spinner.setVisibility(View.VISIBLE);

                    ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if(networkInfo != null && networkInfo.isConnected()){
                        etEndereco.setText("");
                        etComple.setText("");
                        etCidade.setText("");
                        etEstado.setText("");
                        String cep = etCep.getText().toString();
                        System.out.println(cep);

                        Pattern pattern = Pattern.compile("^[0-9]{5}-[0-9]{3}$");
                        Matcher matcher = pattern.matcher(cep);

                        if(matcher.find()){
                            new DownloadCEPTask().execute(cep);
                            System.out.println("Donwload passou");
                        }else{
                            spinner.setVisibility(View.GONE);
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("CEP Invalido ou Desatualizado")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();

                        }
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("Sem Conexão")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();

                    }
            }
        }
    });


        final String finalSexo = sexo;
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validar()){

                System.out.println("Antes do Response");
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try {
                            System.out.println("Dps do Response");
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Resgister Failed")
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
                RegisterRequest registerRequest = new RegisterRequest(etNome.getText().toString(), etIdade.getText().toString(), etEmail.getText().toString(), etSenha.getText().toString(), etEstado.getText().toString(),
                        etCidade.getText().toString(), etEndereco.getText().toString(),etCep.getText().toString(), finalSexo, celular.getText().toString(), etPalavraSecret.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }else{AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Campos Necessarios em Branco e/ou Senhas Não Coincidem")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();     }}
            private boolean validar(){
                boolean valid = true;

                String checkBlank;

                String pass = etSenha.getText().toString();
                String checkPass = confSenha.getText().toString();
                String name = etNome.getText().toString();
                String date = data.getText().toString();
                String email = etEmail.getText().toString();
                String secret = etPalavraSecret.getText().toString();
                String cell = celular.getText().toString();
                String estado = etEstado.getText().toString();
                String cidade = etCidade.getText().toString();
                String CEP = etCep.getText().toString();
                String End = etEndereco.getText().toString();

                if(!pass.equals(checkPass) ){
                    Toast.makeText(RegisterActivity.this,"Senhas não coincidem", Toast.LENGTH_SHORT).show();
                    valid = false;
                }else if(pass.equals("") | checkPass.equals("")| name.equals("")| date.equals("")| email.equals("")| secret.equals("")| cell.equals("")| CEP.equals("") ){
                    Toast.makeText(RegisterActivity.this,"Um ou mais Campos estão vazios", Toast.LENGTH_SHORT).show();
                    valid = false;
                }else if(estado.equals("") | cidade.equals("") | End.equals("")){
                    Toast.makeText(RegisterActivity.this,"CEP Errado ou desatualizado", Toast.LENGTH_SHORT).show();
                    valid = false;
                }else if (checkEmail()){Toast.makeText(RegisterActivity.this,"Email ja Em Uso", Toast.LENGTH_SHORT).show();
                    valid = false;}



                return valid;
            }
        });
    }

    private boolean checkEmail() {
         boolean validEmail = true;
        String Emalol = etEmail.getText().toString();
        final int[] fdp = {0};
        fdp[0] = 0;
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        fdp[0] = 1;
                    }else{

                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("Email já em uso")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        EmailRequest loginRequest = new EmailRequest(Emalol, responseListener);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(loginRequest);



        if(fdp[0] == 1)
        validEmail = true;
        else
            validEmail = false;

        return validEmail;


    }

    private class DownloadCEPTask extends AsyncTask<String, Void, ViaCep> {
        @Override
        protected ViaCep doInBackground(String ... ceps) {
            ViaCep vCep = null;
            System.out.println("Chegou na Classe");


            try {
                vCep = new ViaCep(ceps[0]);
                System.out.println("Ta vindo aqui?2");

            } finally {
                return vCep;

            }
        }

        @Override
        protected void onPostExecute(ViaCep result) {
            if (result != null) {

                etEndereco.setText(result.getEndereco());
                etCidade.setText(result.getCidade());
                etComple.setText(result.getComplemento());
                etEstado.setText(result.getEstado());


                spinner.setVisibility(View.GONE);


            }else{
                spinner.setVisibility(View.GONE);
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setMessage("CEP Invalido ou Desatualizado")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();


            }

        }
    }





}
