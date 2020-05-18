package com.example.cttarde.tcc_wissen.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.cttarde.tcc_wissen.DTO.usuarioDTO;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.mask.Mask;
import com.example.cttarde.tcc_wissen.request.AddEventoRequest;
import com.example.cttarde.tcc_wissen.viacep.ViaCep;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AdicionarEventoActivity extends AppCompatActivity {

    private String[] categoriasSpinner = new String[]{"Selecione...", "Ao ar livre", "Bar", "Casamento", "Cinema", "Corporativo",
            "Crianca", "Eventos Culturais", "Exposicao", "Feiras", "Festas", "Festival", "Formatura", "Gastronomia",
            "Literatura", "Restaurante", "Social", "Urbano", "Outros"};

    private Spinner categoria1;
    private Spinner categoria2;
    private Spinner categoria3;

    private String tipo1;
    private String tipo2;
    private String tipo3;

    private String Lat;
    private String Long;

    TextView wissen;

    TextView responsavel;
    EditText etResponsavel;

    TextView estado;
    EditText etEstado;

    TextView cidade;
    EditText etCidade;

    TextView bairro;
    EditText etBairro;

    TextView titulo;
    EditText etTitulo;

    TextView descricao;
    EditText etDescricao;

    TextView endereco;
    EditText etEndereco;

    TextView categorias;
    Spinner etCategorias1;
    Spinner etCategorias2;
    Spinner etCategorias3;

    TextView inicioData;
    EditText etInicioData;

    TextView terminoData;
    EditText etTerminoData;

    TextView inicioHora;
    EditText etInicioHora;

    TextView terminoHora;
    EditText etTerminoHora;

    TextView telefone;
    EditText etTelefone;

    TextView celular;
    EditText etCelular;

    TextView cep;
    EditText etCep;

    TextView local;
    EditText etLocal;

    TextView preco;
    EditText etPreco;

    TextView imagensTextView;
    TextView imagemCapa;
    TextView privacidade;
    private ProgressBar spinner;



    Button criarEvento;

    private static int RESULT_LOAD_IMAGE = 1;
    private static String priv = "0";
    private static String base1;
    private static String base2;
    private static String base3;
    private static String nomeF1;
    private static String nomeF2;
    private static String nomeF3;
    private static int Gamb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_evento);
        spinner=(ProgressBar)findViewById(R.id.progressbar);
        spinner.setVisibility(View.GONE);

        fonts();

        etCep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    spinner.setVisibility(View.VISIBLE);

                    ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if(networkInfo != null && networkInfo.isConnected()){
                        etEndereco.setText("");

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
                            AlertDialog.Builder builder = new AlertDialog.Builder(AdicionarEventoActivity.this);
                            builder.setMessage("CEP Invalido ou Desatualizado")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();

                        }
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(AdicionarEventoActivity.this);
                        builder.setMessage("Sem Conexão")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();

                    }
                }
            }
        });


        Switch sc = (Switch) findViewById(R.id.privacidade_switch);
        sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(), "Privado", Toast.LENGTH_SHORT).show();
                    priv = "1";
                }else {
                    Toast.makeText(getApplicationContext(), "Não privado", Toast.LENGTH_SHORT).show();
                    priv = "0";
                }
            }
        });

        ImageButton buttonLoadImage1 = (ImageButton) findViewById(R.id.image_add_evento_1);
        buttonLoadImage1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
                Gamb = 1;

            }
        });

        ImageButton buttonLoadImage3 = (ImageButton) findViewById(R.id.image_add_evento_3);
        buttonLoadImage3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
                Gamb = 3;
            }
        });

        ImageButton buttonLoadImage2 = (ImageButton) findViewById(R.id.image_add_evento_2);
        buttonLoadImage2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
                Gamb = 2;
            }
        });


        criarEvento = (Button)findViewById(R.id.button_criarEvento);
        criarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validar()){
                String verificaImg = "0";
                String fotos = null;
                String horaInicio;
                String horaTermino;
                String dataInicioQuebra[];
                String dataInicio;
                String dataTerminoQuebra[];
                String dataTermino;

                horaInicio = etInicioHora.getText().toString().replace("-", ":");
                horaTermino = etTerminoHora.getText().toString().replace("-", ":");



                    System.out.println(etTitulo.getText());

                //String tudo = tipo1 + ";" + tipo2 + ";" + tipo3;
                if (nomeF2 == null && nomeF3 != null) {
                    base2 = "SemImagem";
                    nomeF2 = "SemImagem";
                    fotos = nomeF2 + ";" + nomeF3;
                    verificaImg = "3";

                } else if (nomeF2 != null && nomeF3 == null) {
                    base3 = "SemImagem";
                    nomeF3 = "SemImagem";
                    fotos = nomeF2 + ";" + nomeF3;
                    verificaImg = "2";

                } else if (nomeF2 != null && nomeF3 != null) {
                    fotos = nomeF2 + ";" + nomeF3;
                    verificaImg = "23";
                } else if (nomeF2 == null && nomeF3 == null) {
                    base2 = "SemImagem";
                    base3 = "SemImagem";
                    nomeF2 = "SemImagem";
                    nomeF3 = "SemImagem";
                    fotos = nomeF2 + ";" + nomeF3;

                }

                String tipos = categoria1.getSelectedItem().toString() + ";" + categoria2.getSelectedItem().toString() + ";" + categoria3.getSelectedItem().toString();

                System.out.println("Antes do Response");
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("Dps do Response");
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {

                                Intent intent = new Intent(AdicionarEventoActivity.this, PerfilActivity.class);
                                AdicionarEventoActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AdicionarEventoActivity.this);
                                builder.setMessage("Register Failed")
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
                AddEventoRequest eventoRequest = new AddEventoRequest(etTitulo.getText().toString(), etResponsavel.getText().toString(), etInicioData.getText().toString(), etTerminoData.getText().toString(),
                        horaInicio, horaTermino, etPreco.getText().toString(), etDescricao.getText().toString(),
                        fotos, nomeF1, etCep.getText().toString(), etEstado.getText().toString(), etCidade.getText().toString(), etBairro.getText().toString(), etLocal.getText().toString(), etEndereco.getText().toString(), etTelefone.getText().toString(), etCelular.getText().toString(),
                        usuarioDTO.getEmail(), tipos, priv, "0", "0", "0",Lat,Long, base1, nomeF1, base2, nomeF2, base3, nomeF3, verificaImg, responseListener);
                RequestQueue queue = Volley.newRequestQueue(AdicionarEventoActivity.this);
                queue.add(eventoRequest);


                startActivity(new Intent(AdicionarEventoActivity.this, HomeClassActivity.class));
                AdicionarEventoActivity.this.finish();
            }
        }
        });

        spinners();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            InputStream iStream = null;
            try {
                iStream = getContentResolver().openInputStream(selectedImage);

            byte[] inputData = getBytes(iStream);



            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();




                if (Gamb == 1) {
                    base1 =Base64.encodeToString(inputData, 0);
                    GeraFoto(base1,Gamb);
                    ImageButton imageBtn = (ImageButton) findViewById(R.id.image_add_evento_1);

                    imageBtn.setImageURI(selectedImage);

                }else if (Gamb == 2){
                    base2 =Base64.encodeToString(inputData, 0);
                    GeraFoto(base2,Gamb);

                    ImageButton imageBtn = (ImageButton) findViewById(R.id.image_add_evento_2);

                    imageBtn.setImageURI(selectedImage);
                }else if (Gamb == 3){
                    base3 =Base64.encodeToString(inputData, 0);
                    GeraFoto(base3,Gamb);

                    ImageButton imageBtn = (ImageButton) findViewById(R.id.image_add_evento_3);

                    imageBtn.setImageURI(selectedImage);
                }

                Gamb = 0;

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
    public static String GeraFoto(String sus, int gamb) {
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

        sus = sb.toString() + ".jpg";

        if (Gamb == 1){
            nomeF1 = sus;

        }else if (Gamb == 2){
            nomeF2 = sus;


        }else if (Gamb == 3){
            nomeF3 = sus;

        }

        return sus;
    }

    public void spinners(){
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, R.layout.spinner_item, categoriasSpinner);

        categoria1 = (Spinner) findViewById(R.id.categoria_1);
        categoria1.setAdapter(adapter);
        categoria1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo1 = categoriasSpinner[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        categoria2 = (Spinner) findViewById(R.id.categoria_2);
        categoria2.setAdapter(adapter);
        categoria2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo2 = categoriasSpinner[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        categoria3 = (Spinner) findViewById(R.id.categoria_3);
        categoria3.setAdapter(adapter);
        categoria3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo3 = categoriasSpinner[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void fonts(){

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/righteous_regular.ttf");
        wissen = (TextView) findViewById(R.id.toolbar_title);
        wissen.setTypeface(font);

        titulo = (TextView) findViewById(R.id.textView_titulo);
        titulo.setTypeface(font);

        descricao = (TextView) findViewById(R.id.textView_descricao);
        descricao.setTypeface(font);

        categorias = (TextView) findViewById(R.id.textView_categorias);
        categorias.setTypeface(font);

        inicioData = (TextView) findViewById(R.id.textView_inicio_data);
        inicioData.setTypeface(font);

        terminoData = (TextView) findViewById(R.id.textView_termino_data);
        terminoData.setTypeface(font);

        inicioHora = (TextView) findViewById(R.id.textView_inicio_hora);
        inicioHora.setTypeface(font);

        terminoHora = (TextView) findViewById(R.id.textView_termino_hora);
        terminoHora.setTypeface(font);

        telefone = (TextView) findViewById(R.id.textView_contato_telefone);
        telefone.setTypeface(font);

        celular = (TextView) findViewById(R.id.textView_contato_celular);
        celular.setTypeface(font);

        cep = (TextView) findViewById(R.id.textView_cep);
        cep.setTypeface(font);

        local = (TextView) findViewById(R.id.textView_local);
        local.setTypeface(font);

        responsavel = (TextView) findViewById(R.id.textView_responsavel);
        responsavel.setTypeface(font);

        bairro = (TextView) findViewById(R.id.textView_bairro);
        bairro.setTypeface(font);

        cidade = (TextView) findViewById(R.id.textView_cidade);
        cidade.setTypeface(font);

        estado = (TextView) findViewById(R.id.textView_estado);
        estado.setTypeface(font);

        endereco = (TextView) findViewById(R.id.textView_endereco);
        endereco.setTypeface(font);

        privacidade = (TextView) findViewById(R.id.textView_privacidade);
        privacidade.setTypeface(font);

        imagemCapa = (TextView) findViewById(R.id.textView_imagem_capa);
        imagemCapa.setTypeface(font);



        preco = (TextView) findViewById(R.id.textView_preco);
        preco.setTypeface(font);

        imagensTextView = (TextView) findViewById(R.id.textView_imagens);
        imagensTextView.setTypeface(font);

        etInicioData = (EditText) findViewById(R.id.editText_inicio_data);
        etInicioData.addTextChangedListener(Mask.insert("##/##/####", etInicioData));

        etTerminoData = (EditText) findViewById(R.id.editText_termino_data);
        etTerminoData.addTextChangedListener(Mask.insert("##/##/####", etTerminoData));

        etInicioHora = (EditText) findViewById(R.id.editText_inicio_hora);
        etInicioHora.addTextChangedListener(Mask.insert("##-##", etInicioHora));

        etTerminoHora = (EditText) findViewById(R.id.editText_termino_hora);
        etTerminoHora.addTextChangedListener(Mask.insert("##-##", etTerminoHora));

        etTelefone = (EditText) findViewById(R.id.editText_contato_telefone);
        etTelefone.addTextChangedListener(Mask.insert("(##)####-####", etTelefone));

        etCelular = (EditText) findViewById(R.id.editText_contato_celular);
        etCelular.addTextChangedListener(Mask.insert("(##)#####-####", etCelular));

        etCep = (EditText) findViewById(R.id.editText_cep);
        etCep.addTextChangedListener(Mask.insert("#####-###", etCep));

        etEndereco = (EditText) findViewById(R.id.endereco_editText);

        etTitulo = (EditText) findViewById(R.id.titulo_editText);

        etResponsavel = (EditText) findViewById(R.id.responsavel_editText);

        etBairro = (EditText) findViewById(R.id.editText_bairro);

        etCidade = (EditText) findViewById(R.id.editText_cidade);

        etEstado = (EditText) findViewById(R.id.editText_estado);

        etDescricao = (EditText) findViewById(R.id.descricao_editText);

        etCategorias1 = (Spinner) findViewById(R.id.categoria_1);

        etCategorias2 = (Spinner) findViewById(R.id.categoria_2);

        etCategorias3 = (Spinner) findViewById(R.id.categoria_3);

        etLocal = (EditText) findViewById(R.id.editText_local);

        etPreco = (EditText) findViewById(R.id.editText_preco);




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
                spinner.setVisibility(View.GONE);
                etEndereco.setText(result.getEndereco());
                etCidade.setText(result.getCidade());
                etBairro.setText(result.getBairro());
                etEstado.setText(result.getEstado());
                if (!result.getLat().equals("null") || !result.getLong().equals("null")) {
                    Lat = result.getLat();
                    Long = result.getLong();
                }else{
                    Lat = "-23.6525068";
                    Long = "-46.7125957";
                }


            }else{
                spinner.setVisibility(View.GONE);
                AlertDialog.Builder builder = new AlertDialog.Builder(AdicionarEventoActivity.this);
                builder.setMessage("CEP Invalido ou Desatualizado")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }

        }
    }
    private boolean validar(){
        boolean valid = true;





        String name = etTitulo.getText().toString();
        String dateBeg = etInicioData.getText().toString();
        String dateEnd = etTerminoData.getText().toString();
        String hourBeg = etInicioHora.getText().toString();
        String hourEnd = etTerminoHora.getText().toString();
        String Categoria1 = categoria1.getSelectedItem().toString();
        String Categoria2 = categoria2.getSelectedItem().toString();
        String Categoria3 = categoria3.getSelectedItem().toString();
        String cell = celular.getText().toString();
        String estado = etEstado.getText().toString();
        String cidade = etCidade.getText().toString();
        String CEP = etCep.getText().toString();
        String End = etEndereco.getText().toString();

        if( name.equals("")| dateBeg.equals("")| dateEnd.equals("")| hourBeg.equals("")| hourEnd.equals("")| Categoria1.equals("Selecione...")| Categoria2.equals("Selecione...")| Categoria3.equals("Selecione...") | cell.equals("")| CEP.equals("") ){
            Toast.makeText(AdicionarEventoActivity.this,"Um ou mais Campos estão vazios", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if(estado.equals("") | cidade.equals("") | End.equals("")){
            Toast.makeText(AdicionarEventoActivity.this,"CEP Errado ou desatualizado", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (Categoria1.equals(Categoria2) || Categoria1.equals(Categoria3) || Categoria2.equals(Categoria3)){
            Toast.makeText(AdicionarEventoActivity.this,"Por favor, Escolha Categorias Diferentes", Toast.LENGTH_SHORT).show();
            valid = false;

        }else if (base1 == null){
            Toast.makeText(AdicionarEventoActivity.this,"Escolha ao menos a imagem de Capa", Toast.LENGTH_SHORT).show();
            valid = false;
        }



        return valid;
    }



}
