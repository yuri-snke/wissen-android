package com.example.cttarde.tcc_wissen.detail;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
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
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.cttarde.tcc_wissen.DPO.IPrequest;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.activity.HomeClassActivity;
import com.example.cttarde.tcc_wissen.mask.Mask;
import com.example.cttarde.tcc_wissen.request.EditEventoRequest;
import com.example.cttarde.tcc_wissen.request.EditEventoRequestFoto;
import com.squareup.picasso.Picasso;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MyImageDetailEdit extends AppCompatActivity {

    TextView wissen;

    TextView titulo;
    EditText etTitulo;

    TextView responsavel;
    EditText etResponsavel;

    TextView estado;
    EditText etEstado;

    TextView cidade;
    EditText etCidade;

    TextView bairro;
    EditText etBairro;

    TextView descricao;
    EditText etDescricao;

    TextView categorias;
    EditText etCategorias;

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

    TextView endereco;
    EditText etEndereco;

    TextView local;
    EditText etLocal;

    TextView preco;
    EditText etPreco;

    TextView imagensTextView;
    TextView imagemCapa;

    Button criarEvento;
    Button criarEventoFoto;

    private static int RESULT_LOAD_IMAGE = 1;
    private static String priv = "0";
    private static String base1;
    private static String base2;
    private static String base3;
    private static String nomeF1;
    private static String nomeF2;
    private static String nomeF3;
    private static int Gamb;

    private static final String DRAWABLE_RESOURE = "resource";
    private static final String POSITON = "position";
    private static final String Nome = "nome";
    private static final String Id_Evento = "ide";
    private static final String NomeResp = "nomeresp";
    private static final String Data = "data";
    private static final String HorarioIni = "horarioIni";
    private static final String HorarioTerm = "horarioTerm";
    private static final String Endereco = "endereco";
    private static final String Local = "local";
    private static final String Preco = "preco";
    private static final String Resp = "resp";
    private static final String TelMovel = "telMovel";
    private static final String TelFixo = "telFixo";
    private static final String Cep = "cep";
    private static final String Estado = "estado";
    private static final String Bairro = "bairro";
    private static final String Cidade = "cidade";
    private static final String Descricao = "descricao";
    private static final String Imagens = "imagens";

    private String[] categoriasSpinner = new String[]{"Selecione...", "Ao ar livre", "Bar", "Casamento", "Cinema", "Corporativo",
            "Crianca", "Eventos Culturais", "Exposicao", "Feiras", "Festas", "Festival", "Formatura", "Gastronomia",
            "Literatura", "Restaurante", "Social", "Urbano", "Outros"};

    private Spinner categoria1;
    private Spinner categoria2;
    private Spinner categoria3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_evento_edit);

        fonts();


        String nomeRel = getIntent().getStringExtra(Nome);

//        String[] dataInicioQuebraPos = getIntent().getStringExtra(Data).split("/");
//
//
//
//        String dataRel = dataInicioQuebraPos[2] + "/" + dataInicioQuebraPos[1] + "/" + dataInicioQuebraPos[0];
String dataRel = getIntent().getStringExtra(Data);

        String horarioIniRel = getIntent().getStringExtra(HorarioIni).replace(":", "-");
        String horarioTermRel = getIntent().getStringExtra(HorarioTerm).replace(":", "-");
        String enderecoRel = getIntent().getStringExtra(Endereco);
        String estadoRel = getIntent().getStringExtra(Estado);
        String cidadeRel = getIntent().getStringExtra(Cidade);
        String bairroRel = getIntent().getStringExtra(Bairro);
        String CepRel = getIntent().getStringExtra(Cep);
        String localRel = getIntent().getStringExtra(Local);
        String precoRel = getIntent().getStringExtra(Preco);
        String responRel = getIntent().getStringExtra(Resp);
        String telMovelRel = getIntent().getStringExtra(TelMovel);
        String telFixoRel = getIntent().getStringExtra(TelFixo);
        String descricaoRel = getIntent().getStringExtra(Descricao);
        final String ImagensEve = getIntent().getStringExtra(Imagens);
        final String ImagemCapa = getIntent().getStringExtra(DRAWABLE_RESOURE);
        final String id_evento = getIntent().getStringExtra(Id_Evento);
        final  String Imagem[] = getIntent().getStringExtra(Imagens).trim().split(";");







        etTitulo.setText(nomeRel);
        etDescricao.setText(descricaoRel);
        etInicioData.setText(dataRel);
        etTerminoData.setText(dataRel);
        etInicioHora.setText(horarioIniRel);
        etTerminoHora.setText(horarioTermRel);
        etEndereco.setText(enderecoRel);
        etEstado.setText(estadoRel);
        etCidade.setText(cidadeRel);
        etBairro.setText(bairroRel);
        etCep.setText(CepRel);
        etLocal.setText(localRel);
        etPreco.setText(precoRel);
        etCelular.setText(telMovelRel);
        etTelefone.setText(telFixoRel);
        etResponsavel.setText(responRel);









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
        Picasso.with(MyImageDetailEdit.this).load(IPrequest.getImgCapa() + ImagemCapa ).into(buttonLoadImage1);
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
        System.out.println(Imagem[1] + "QUE VENHA SEM IMAGEM");
        if (Imagem[1] != "SemImagem" ) {
            Picasso.with(MyImageDetailEdit.this).load(IPrequest.getImgCapa() + Imagem[1]).into(buttonLoadImage3);
        }
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
        System.out.println(Imagem[0] + "QUE VENHA SEM IMAGEM");
        if (Imagem[0] != "SemImagem") {
            Picasso.with(MyImageDetailEdit.this).load(IPrequest.getImgCapa() + Imagem[0]).into(buttonLoadImage2);
        }
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

                    String horaInicio;
                    String horaTermino;
                    String dataInicioQuebra[];
                    String dataInicio;
                    String dataTerminoQuebra[];
                    String dataTermino;



                    horaInicio = etInicioHora.getText().toString().replace("-", ":");
                    horaTermino = etTerminoHora.getText().toString().replace("-", ":");
                    dataInicioQuebra = etInicioData.getText().toString().split("/");
                    dataInicio = dataInicioQuebra[2] + "-" + dataInicioQuebra[1] + "-" + dataInicioQuebra[0];
                    dataTerminoQuebra = etTerminoData.getText().toString().split("/");
                    dataTermino = dataTerminoQuebra[2] + "-" + dataTerminoQuebra[1] + "-" + dataTerminoQuebra[0];

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

                                    AlertDialog.Builder builder = new AlertDialog.Builder(MyImageDetailEdit.this);
                                    builder.setMessage("Informações Mudadas com Sucesso! Gostaria de Continuar?")
                                            .setNegativeButton("Sim", null)
                                            .setPositiveButton("Não", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(MyImageDetailEdit.this, HomeClassActivity.class);
                                                    MyImageDetailEdit.this.startActivity(intent);

                                                }
                                            })
                                            .create()
                                            .show();

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MyImageDetailEdit.this);
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



                    EditEventoRequest eventoRequestEdit = new EditEventoRequest(id_evento,etTitulo.getText().toString(), etResponsavel.getText().toString(), dataInicio, dataTermino,
                            horaInicio, horaTermino, etPreco.getText().toString(), etDescricao.getText().toString(), etCep.getText().toString(), etEstado.getText().toString(),
                            etCidade.getText().toString(), etBairro.getText().toString(), etLocal.getText().toString(), etEndereco.getText().toString(),
                            etTelefone.getText().toString(), etCelular.getText().toString(),tipos, priv,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(MyImageDetailEdit.this);
                    queue.add(eventoRequestEdit);


                }




            }
        });

        criarEventoFoto = (Button) findViewById(R.id.button_criarEvento_Foto);
        criarEventoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String verificaImg = "0";

                String fotos = ImagensEve;
                String verificaImgFoto = "0";



                    if (base1 == null){
                        verificaImgFoto = "1";
                        base1 = "SemImagemNova";
                    }else {

                        verificaImgFoto = "2";

                    }
//
//                    if (Imagem[0].equals("SemImagem") && Imagem[1].equals("SemImagem")){
//                        if (base2 == null && base3 != null) {
//                            base2 = "SemImagem";
//                            nomeF2 = "SemImagem";
//                            fotos = nomeF2 + ";" + nomeF3;
//                            verificaImg = "3";
//
//                        } else if (base2 != null && base3 == null) {
//                            base3 = "SemImagem";
//                            nomeF3 = "SemImagem";
//                            fotos = nomeF2 + ";" + nomeF3;
//                            verificaImg = "2";
//
//                        } else if (base2 != null && base3 != null) {
//                            fotos = nomeF2 + ";" + nomeF3;
//                            verificaImg = "23";
//                        } else if (base2 == null && base3 == null) {
//                            base2 = "SemImagem";
//                            base3 = "SemImagem";
//                            nomeF2 = "SemImagem";
//                            nomeF3 = "SemImagem";
//                            fotos = nomeF2 + ";" + nomeF3;
//
//                        }
//                    }
//                else if (!Imagem[0].equals("SemImagem") && Imagem[1].equals("SemImagem")){
//                    if (base2 == null && base3 != null) {
//                        base2 = "SemImagem";
//                        nomeF2 = "SemImagem";
//                        fotos = nomeF2 + ";" + nomeF3;
//                        verificaImg = "3";
//
//                    } else if (base2 != null && base3 == null) {
//                        base3 = "SemImagem";
//                        nomeF3 = "SemImagem";
//                        fotos = nomeF2 + ";" + nomeF3;
//                        verificaImg = "2";
//
//                    } else if (base2 != null && base3 != null) {
//                        fotos = nomeF2 + ";" + nomeF3;
//                        verificaImg = "23";
//                    } else if (base2 == null && base3 == null) {
//                        base2 = "SemImagem";
//                        base3 = "SemImagem";
//                        nomeF2 = "SemImagem";
//                        nomeF3 = "SemImagem";
//                        fotos = nomeF2 + ";" + nomeF3;
//
//                    }
//                } else  if (Imagem[0].equals("SemImagem") && !Imagem[1].equals("SemImagem")){
//                        if (base2 == null && base3 != null) {
//                            base2 = "SemImagem";
//                            nomeF2 = "SemImagem";
//                            fotos = nomeF2 + ";" + nomeF3;
//                            verificaImg = "3";
//
//                        } else if (base2 != null && base3 == null) {
//                            base3 = "SemImagem";
//                            nomeF3 = "SemImagem";
//                            fotos = nomeF2 + ";" + nomeF3;
//                            verificaImg = "2";
//
//                        } else if (base2 != null && base3 != null) {
//                            fotos = nomeF2 + ";" + nomeF3;
//                            verificaImg = "23";
//                        } else if (base2 == null && base3 == null) {
//                            base2 = "SemImagem";
//                            base3 = "SemImagem";
//                            nomeF2 = "SemImagem";
//                            nomeF3 = "SemImagem";
//                            fotos = nomeF2 + ";" + nomeF3;
//
//                        }
//                    } else  if (!Imagem[0].equals("SemImagem") && !Imagem[1].equals("SemImagem")){
                     if (base2 == null && base3 != null) {
                            base2 = "null";
                            nomeF2 = Imagem[0];
                         verificaImg = "3";

                        } else if (base2 != null && base3 == null) {
                           base3 = "null";
                            nomeF3 = Imagem[1];
                           fotos = nomeF2 + ";" + nomeF3;
                            verificaImg = "2";
                        } else if (base2 != null && base3 != null) {
                            fotos = nomeF2 + ";" + nomeF3;
                           verificaImg = "23";
                        } else if (base2 == null && base3 == null) {
                            base2 = "null";
                            base3 = "null";
                            nomeF2 = Imagem[0];
                            nomeF3 = Imagem[1];
                            fotos = nomeF2 + ";" + nomeF3;

                        }
//                   }




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

                                    AlertDialog.Builder builder = new AlertDialog.Builder(MyImageDetailEdit.this);
                                    builder.setMessage("Fotos Mudadas com Sucesso")
                                            .setNegativeButton("Sim", null)
                                            .setPositiveButton("Não", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(MyImageDetailEdit.this, HomeClassActivity.class);
                                                    MyImageDetailEdit.this.startActivity(intent);

                                                }
                                            })
                                            .create()
                                            .show();

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MyImageDetailEdit.this);
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
                    EditEventoRequestFoto eventoRequest = new EditEventoRequestFoto(id_evento,fotos, ImagemCapa, base1, ImagemCapa, base2, nomeF2, base3, nomeF3, verificaImg,verificaImgFoto, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(MyImageDetailEdit.this);
                    queue.add(eventoRequest);






            }
        });


        spinners();

    }
    public void spinners(){
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, R.layout.spinner_item, categoriasSpinner);

        categoria1 = (Spinner) findViewById(R.id.categoria_1);
        categoria1.setAdapter(adapter);
        categoria1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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

        endereco = (TextView) findViewById(R.id.textView_endereco);
        endereco.setTypeface(font);

        cep = (TextView) findViewById(R.id.textView_cep);
        cep.setTypeface(font);

        local = (TextView) findViewById(R.id.textView_local);
        local.setTypeface(font);

        preco = (TextView) findViewById(R.id.textView_preco);
        preco.setTypeface(font);

        imagensTextView = (TextView) findViewById(R.id.textView_imagens);
        imagensTextView.setTypeface(font);

        imagemCapa = (TextView) findViewById(R.id.textView_imagem_capa);
        imagemCapa.setTypeface(font);

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

        etTitulo = (EditText) findViewById(R.id.titulo_editText_);

        etDescricao = (EditText) findViewById(R.id.descricao_editText);

        etCategorias1 = (Spinner) findViewById(R.id.categoria_1);

        etCategorias2 = (Spinner) findViewById(R.id.categoria_2);

        etCategorias3 = (Spinner) findViewById(R.id.categoria_3);

        etResponsavel = (EditText) findViewById(R.id.responsavel_editText);

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

        etBairro = (EditText) findViewById(R.id.editText_bairro);

        etCidade = (EditText) findViewById(R.id.editText_cidade);

        etEstado = (EditText) findViewById(R.id.editText_estado);

        etLocal = (EditText) findViewById(R.id.editText_local);

        etPreco = (EditText) findViewById(R.id.editText_preco);



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
                    base1 = Base64.encodeToString(inputData, 0);
                    ImageButton imageBtn = (ImageButton) findViewById(R.id.image_add_evento_1);

                    imageBtn.setImageURI(selectedImage);

                }else if (Gamb == 2){
                    base2 =Base64.encodeToString(inputData, 0);

                    GeraFoto(base2,Gamb);
                    ImageButton imageBtn2 = (ImageButton) findViewById(R.id.image_add_evento_2);
                    imageBtn2.setImageURI(selectedImage);


                }else if (Gamb == 3){
                    base3 =Base64.encodeToString(inputData, 0);
                    GeraFoto(base3,Gamb);

                    ImageButton imageBtn3 = (ImageButton) findViewById(R.id.image_add_evento_3);


                    imageBtn3.setImageURI(selectedImage);
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
    public String GeraFoto(String sus, int gamb) {
        final  String Imagem[] = getIntent().getStringExtra(Imagens).trim().split(";");

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
            if (Imagem[0].equals("SemImagem"))
            nomeF2 = sus;
            else
                nomeF2 = Imagem[0];

        }else if (Gamb == 3){
            if (Imagem[1].equals("SemImagem"))
                nomeF3 = sus;
            else
                nomeF3 = Imagem[1];

        }

        return sus;
        //return sb.toString() + ".jpg".equals(sus);
    }


    private boolean validar(){
        boolean valid = true;

        String checkBlank;



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
            Toast.makeText(MyImageDetailEdit.this,"Um ou mais Campos estão vazios", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if(estado.equals("") | cidade.equals("") | End.equals("")){
            Toast.makeText(MyImageDetailEdit.this,"CEP Errado ou desatualizado", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if        (Categoria1.equals(Categoria2) || Categoria1.equals(Categoria3) || Categoria2.equals(Categoria3)){
            Toast.makeText(MyImageDetailEdit.this,"Por favor, Escolha Categorias Diferentes", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }
}
