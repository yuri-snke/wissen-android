package com.example.cttarde.tcc_wissen.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;
import com.example.cttarde.tcc_wissen.DTO.usuarioDTO;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.request.PerfilEditRequest;
import com.example.cttarde.tcc_wissen.request.PerfilEditRequestFoto;
import com.example.cttarde.tcc_wissen.request.PerfilEditRequestSenha;
import com.squareup.picasso.Picasso;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class PerfilEdit extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;

    EditText senhaAtual;
    EditText newSenha;
    EditText confSenha;
    EditText nomeEdit;
    EditText etIdade;
    EditText etCel;
    EditText etCep;
    EditText etEstado;
    EditText etCidade;
    EditText etEmail;
    EditText etEndereco;


    TextView wissen;
    TextView nome;
    TextView idade;
    TextView cel;
    TextView cep;
    TextView estado;
    TextView cidade;
    TextView endereco;
    TextView email;
    TextView senhaAtualTitulo;
    TextView novaSenha;
    TextView confirmNovaSenha;
    TextView alterarSenha;
    Button salvar;
    Button salvarinfo;
    ImageButton salvarfoto;

    Button mostrarSenha;


    static String nomePerfil;
    static String ImagemPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_edit);

        salvar = (Button) findViewById(R.id.editar_perfil_btn);
        salvarinfo = (Button) findViewById(R.id.editar_perfil_btn_info);
        salvarfoto = (ImageButton) findViewById(R.id.editar_perfil_btn_foto) ;




        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/righteous_regular.ttf");

        wissen = (TextView) findViewById(R.id.toolbar_title);
        wissen.setTypeface(font);

        nome = (TextView) findViewById(R.id.nome_titulo);
        nome.setTypeface(font);

        idade = (TextView) findViewById(R.id.idade_titulo);
        idade.setTypeface(font);

        cel = (TextView) findViewById(R.id.cel_titulo);
        cel.setTypeface(font);

        cep = (TextView) findViewById(R.id.cep_titulo);
        cep.setTypeface(font);

        estado = (TextView) findViewById(R.id.estado_titulo);
        estado.setTypeface(font);

        cidade = (TextView) findViewById(R.id.cidade_titulo);
        cidade.setTypeface(font);

        endereco = (TextView) findViewById(R.id.endereco_titulo);
        endereco.setTypeface(font);

        email = (TextView) findViewById(R.id.email_titulo);
        email.setTypeface(font);

        alterarSenha = (TextView) findViewById(R.id.alterar_senha);
        alterarSenha.setTypeface(font);

        senhaAtualTitulo = (TextView) findViewById(R.id.senha_old_titulo);
        senhaAtualTitulo.setTypeface(font);

        novaSenha = (TextView) findViewById(R.id.senha_nova_titulo);
        novaSenha.setTypeface(font);

        confirmNovaSenha = (TextView) findViewById(R.id.senha_nova_confirmar_Titulo);
        confirmNovaSenha.setTypeface(font);

        nomeEdit = (EditText) findViewById(R.id.Edit_Nome);
        nomeEdit.setText(usuarioDTO.getNome());

        etIdade = (EditText) findViewById(R.id.Edit_idade);
        etIdade.setText(usuarioDTO.getIdade());

        etCel = (EditText) findViewById(R.id.Edit_Celular);
        etCel.setText(usuarioDTO.getTelCel());

        etCep = (EditText) findViewById(R.id.Edit_Cep);
        etCep.setText(usuarioDTO.getCEP());

        etEstado = (EditText) findViewById(R.id.Edit_Estado);
        etEstado.setText(usuarioDTO.getEstado());

        etCidade = (EditText) findViewById(R.id.Edit_Cidade);
        etCidade.setText(usuarioDTO.getCidade());

        etEmail = (EditText) findViewById(R.id.Edit_email);
        etEmail.setText(usuarioDTO.getEmail());

        etEndereco = (EditText) findViewById(R.id.Edit_endereco);
        etEndereco.setText(usuarioDTO.getEndereco());


        newSenha = (EditText) findViewById(R.id.senha_nova_EditText);

        confSenha = (EditText) findViewById(R.id.senha_nova_confirmar_EditText);




        mostrarSenha = (Button) findViewById(R.id.mostrar_senha);
        mostrarSenha.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        senhaAtual.setInputType(InputType.TYPE_CLASS_TEXT);
                        confSenha.setInputType(InputType.TYPE_CLASS_TEXT);
                        newSenha.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        senhaAtual.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        confSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        newSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });

        nomeEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    Toast.makeText(getApplicationContext(), "Preencha somente os campos que deseja atualizar", Toast.LENGTH_LONG).show();
            }
        });

        senhaAtual = (EditText) findViewById(R.id.senha_atual_EditText);
        senhaAtual.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                Toast.makeText(getApplicationContext(), "Somente preencha os campos caso queira Mudar a senha!", Toast.LENGTH_LONG).show();
            }
        });

        de.hdodenhof.circleimageview.CircleImageView buttonLoadImage = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.profile_image_edit);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
                }





        });
        if (usuarioDTO.getImagemPerfil() == null || usuarioDTO.getImagemPerfil().equals("SemImagemPerfil") )
            buttonLoadImage.setImageResource(R.drawable.profile_image);
        else
            Picasso.with(PerfilEdit.this).load(IPrequest.getImgPerfil() + usuarioDTO.getImagemPerfil()).into(buttonLoadImage);

        salvarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Antes do Response");
                if (ImagemPerfil != null){
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("Dps do Response");
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                    usuarioDTO.setImagemPerfil(nomePerfil);

                                AlertDialog.Builder builder = new AlertDialog.Builder(PerfilEdit.this);
                                builder.setMessage("Foto Mudada com Sucesso! Gostaria de Continuar?")
                                        .setNegativeButton("Sim", null)
                                        .setPositiveButton("Não", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(PerfilEdit.this, HomeClassActivity.class);
                                                PerfilEdit.this.startActivity(intent);
                                                System.out.println("Request");
                                            }
                                        })
                                        .create()
                                        .show();


                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(PerfilEdit.this);
                                builder.setMessage("Edit Failed")
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

                System.out.println("A Imagem em si" + ImagemPerfil.substring(0, 30));
                PerfilEditRequestFoto EditRequestFoto = new PerfilEditRequestFoto(usuarioDTO.getId_User(), ImagemPerfil, nomePerfil, responseListener);
                System.out.println("O Nome da Imagem" + nomePerfil);



                RequestQueue queue = Volley.newRequestQueue(PerfilEdit.this);
                queue.add(EditRequestFoto);
            }else {
                    Toast.makeText(getBaseContext(), "Escolha uma foto primeiro", Toast.LENGTH_SHORT).show();

                }
        }});



        salvarinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Antes do Response");
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try {
                            System.out.println("Dps do Response");
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                usuarioDTO.setNome(nomeEdit.getText().toString());
                                usuarioDTO.setIdade(etIdade.getText().toString());
                                usuarioDTO.setEmail(etEmail.getText().toString());
                                usuarioDTO.setEstado(etEstado.getText().toString());
                                usuarioDTO.setCidade(etCidade.getText().toString());
                                usuarioDTO.setEndereco(etEndereco.getText().toString());
                                usuarioDTO.setCEP(etCep.getText().toString());
                                usuarioDTO.setTelCel(etCel.getText().toString());


                                AlertDialog.Builder builder = new AlertDialog.Builder(PerfilEdit.this);
                                builder.setMessage("Informações Mudadas com Sucesso! Gostaria de Continuar?")
                                        .setNegativeButton("Sim", null)
                                        .setPositiveButton("Não", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(PerfilEdit.this, HomeClassActivity.class);
                                                PerfilEdit.this.startActivity(intent);

                                            }
                                        })
                                        .create()
                                        .show();


                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(PerfilEdit.this);
                                builder.setMessage("Edit Failed")
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


              PerfilEditRequest EditRequest = new PerfilEditRequest(usuarioDTO.getId_User(), nomeEdit.getText().toString(), etIdade.getText().toString(),
                        etEmail.getText().toString(), etEstado.getText().toString(), etCidade.getText().toString(), etEndereco.getText().toString(), etCep.getText().toString(),
                        etCel.getText().toString(), responseListener);

                RequestQueue queue = Volley.newRequestQueue(PerfilEdit.this);
                queue.add(EditRequest);
            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Antes do Response");

                if (senhaAtual.getText().toString().equals("") || !senhaAtual.getText().toString().equals(usuarioDTO.getSenha()) ) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PerfilEdit.this);
                    builder.setMessage("Senha atual incorreta")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }else if (!newSenha.getText().toString().equals(confSenha.getText().toString()) || newSenha.getText().toString().equals("") || confSenha.getText().toString().equals("")) {
                    System.out.println(newSenha.getText());
                    System.out.println(confSenha.getText());

                        AlertDialog.Builder builder = new AlertDialog.Builder(PerfilEdit.this);
                        builder.setMessage("Senhas não coincidem")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                    else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                System.out.println("Dps do Response");
                                JSONObject jsonResponse = new JSONObject(response);

                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(PerfilEdit.this);
                                    builder.setMessage("Senha Mudada com Sucesso! Gostaria de Continuar?")
                                            .setNegativeButton("Sim", null)
                                            .setPositiveButton("Não", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(PerfilEdit.this, HomeClassActivity.class);
                                                    PerfilEdit.this.startActivity(intent);
                                                    System.out.println("Request");
                                                }
                                            })
                                            .create()
                                            .show();


                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(PerfilEdit.this);
                                    builder.setMessage("Edit Failed")
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


                    PerfilEditRequestSenha EditRequestSenha = new PerfilEditRequestSenha(usuarioDTO.getId_User(), confSenha.getText().toString(), responseListener);


                    RequestQueue queue = Volley.newRequestQueue(PerfilEdit.this);
                    queue.add(EditRequestSenha);
                }
            }
        });


    }



        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            BitmapFactory.Options options = new BitmapFactory.Options();
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            InputStream iStream = null;
            try {
                iStream = getContentResolver().openInputStream(selectedImage);

                byte[] inputData = getBytes(iStream);


                ImagemPerfil = Base64.encodeToString(inputData,0);
                if (usuarioDTO.getImagemPerfil() == null || usuarioDTO.getImagemPerfil().equals("SemImagemPerfil") )
                GeraFoto(ImagemPerfil);
                else

                    nomePerfil = usuarioDTO.getImagemPerfil();


            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            de.hdodenhof.circleimageview.CircleImageView imageView = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.profile_image_edit);



                imageView.setImageURI(selectedImage);

        } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

}

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
    public static String GeraFoto(String sus) {
        StringBuilder sb = new StringBuilder();
        int qtdChars = 1;
        while (qtdChars <= 35) {
            int charInt = (int) (Math.random() * 91);
            if (((charInt >= 48) && (charInt <= 57)) || ((charInt >= 65) && (charInt <= 90))) {
                char c = (char) charInt;
                sb.append(c);
                qtdChars++;
            }
        }
        nomePerfil = sb.toString() + ".jpg";




        return sus;
        //return sb.toString() + ".jpg".equals(sus);
    }
}
