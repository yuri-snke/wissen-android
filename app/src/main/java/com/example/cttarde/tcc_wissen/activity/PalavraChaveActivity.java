package com.example.cttarde.tcc_wissen.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cttarde.tcc_wissen.DTO.usuarioDTO;
import com.example.cttarde.tcc_wissen.R;

public class PalavraChaveActivity extends AppCompatActivity {

    public TextView wissen_denuncia;
    public TextView word;
    public EditText crack;
    public Button lock;

    public ImageButton mostrarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palavra_chave);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/righteous_regular.ttf");
        wissen_denuncia = (TextView) findViewById(R.id.wissen_denuncia);
        wissen_denuncia.setTypeface(font);

        crack = (EditText) findViewById(R.id.Palavra_Chave);

        word = (TextView) findViewById(R.id.word);
        word.setTypeface(font);

        mostrarSenha = (ImageButton) findViewById(R.id.mostrar_palavra_chave);
        mostrarSenha.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
//                        senha.setInputType(InputType.TYPE_CLASS_TEXT);
                        crack.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
//                        senha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        crack.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });

        lock = (Button) findViewById(R.id.lock_btn);
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (crack.getText().toString().equals(usuarioDTO.getPalavraSecret()) ) {

                    startActivity(new Intent(PalavraChaveActivity.this, PerfilEdit.class));
                    overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                    PalavraChaveActivity.this.finish();
                }else {
                    Toast.makeText(PalavraChaveActivity.this,"Palavra Secreta esta incorreta", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
