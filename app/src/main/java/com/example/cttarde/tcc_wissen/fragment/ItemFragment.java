package com.example.cttarde.tcc_wissen.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cttarde.tcc_wissen.DPO.IPrequest;
import com.example.cttarde.tcc_wissen.DTO.eventoDTO;
import com.example.cttarde.tcc_wissen.DTO.listaDTO;
import com.example.cttarde.tcc_wissen.DTO.usuarioDTO;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.activity.HomeClassActivity;
import com.example.cttarde.tcc_wissen.detail.ImageDetailsActivity;
import com.example.cttarde.tcc_wissen.detail.MyImageDetailActivity;
import com.squareup.picasso.Picasso;


public class ItemFragment extends Fragment {

    public static int count = listaDTO.getLista().size();
    private static final String POSITON = "position";
    private static final String DRAWABLE_RESOURE = "resource";
    private static final String Nome = "nome";
    private static final String NomeResp = "nomeresp";
    private static final String Data = "data";
    private static final String HorarioIni = "horarioIni";
    private static final String HorarioTerm = "horarioTerm";
    private static final String Endereco = "endereco";
    private static final String Bairro = "bairro";
    private static final String Estado = "estado";
    private static final String Cidade = "cidade";
    private static final String CEP = "cep";
    private static final String Local = "local";
    private static final String Preco = "preco";
    private static final String Resp = "resp";
    private static final String TelMovel = "telMovel";
    private static final String TelFixo = "telFixo";
    private static final String Descricao = "descricao";
    private static final String Imagens = "imagens";
    private static final String ID = "id";
    private static final String Id_Evento = "ide";
    private static final String EmailCria = "Cria";
    private static final String Compas = "Compa";
    private static final String Compes = "Compe";
    private static final String Inters = "Inter";







    static String ImagensCapa[] = new String[20000];
    public static String Nomes[] = new String[20000];
    public static String Emails[] = new String[20000];
    public static String Ids[] = new String[20000];
    public static String NomesResps[] = new String[20000];
    public static String Datas[] = new String[20000];
    public static String HorariosIni[] = new String[20000];
    public static String HorariosTerm[] = new String[20000];
    public static String Enderecos[] = new String[20000];
    public static String Bairros[] = new String[20000];
    public static String Cidades[] = new String[20000];
    public static String Estados[] = new String[20000];
    public static String Ceps[] = new String[20000];
    public static String Locais[] = new String[20000];
    public static String Precos[] = new String[20000];
    public static String Responsaveis[] = new String[20000];
    public static String TelMoveis[] = new String[20000];
    public static String TelFixos[] = new String[20000];
    public static String Descricaos[] = new String[20000];
    public static String Imagem[] = new String[20000];
    public static String Compa[] = new String[20000];
    public static String Compe[] = new String[20000];
    public static String Inter[] = new String[20000];


    public void setImagensCapa(){
        int f = 0;
        System.out.println(listaDTO.getLista().size());
        for (eventoDTO evento : listaDTO.getLista()) {
            ImagensCapa[f] = evento.getImagemCapa();
            Nomes[f] = evento.getNome();
            NomesResps[f] = evento.getNomeResp();
            Datas[f] = evento.getDataIni();
            HorariosIni[f] = evento.getHoraIni();
            HorariosTerm[f] = evento.getHoraTer();
            Enderecos[f] = evento.getEndereco();
            Bairros[f] = evento.getBairro();
            Cidades[f] = evento.getCidade();
            Estados[f] = evento.getEstado();
            Ceps[f] = evento.getCEP();
            Locais[f] = evento.getLocal();
            Precos[f] = evento.getPreco();
            Responsaveis[f] = evento.getResponsavel();
            TelMoveis[f] = evento.getTelFixo();
            TelFixos[f] = evento.getTelMovel();
            Descricaos[f] = evento.getDescricao();
            Imagem[f] = evento.getImagens();
            Ids[f] = evento.getId_Evento();
            Emails[f] = evento.getEmailCriador();
            Compa[f] = evento.getComparecerao();
            Compe[f] = evento.getCompareceram();
            Inter[f] = evento.getInteressados();


            f++;
        }

    }
    static boolean first = true;


    public static Fragment newInstance(HomeClassActivity context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt(POSITON, pos);

        return Fragment.instantiate(context, ItemFragment.class.getName(), b);
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setImagensCapa();
            first = false;

    }
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }




        final int postion = this.getArguments().getInt(POSITON);

        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_image, container, false);

        ImageView imageViewDestaque = (ImageView) linearLayout.findViewById(R.id.destaqueImg);
       /* imageViewProximo.setImageResource(imageArrayProximos[postion]);*/
        Picasso.with(getActivity()).load(IPrequest.getImgCapa() + ImagensCapa[postion]).into(imageViewDestaque);



        //handling click event
        imageViewDestaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (usuarioDTO.getEmail().equals(Emails[postion]) || usuarioDTO.getEmail().equals("suporte@blindtech.com") ) {
                     intent = new Intent(getActivity(), MyImageDetailActivity.class);
                }else{
                    intent = new Intent(getActivity(), ImageDetailsActivity.class);
                }
              //  intent.putExtra(DRAWABLE_RESOURE, imageArrayDestaque[postion]);
                intent.putExtra(DRAWABLE_RESOURE, ImagensCapa[postion]);
                intent.putExtra(Nome, Nomes[postion]);
                intent.putExtra(NomeResp,NomesResps[postion]);
                intent.putExtra(Data, Datas[postion]);
                intent.putExtra(HorarioIni, HorariosIni[postion]);
                intent.putExtra(HorarioTerm, HorariosTerm[postion]);
                intent.putExtra(Endereco, Enderecos[postion]);
                intent.putExtra(Bairro, Bairros[postion]);
                intent.putExtra(Cidade, Cidades[postion]);
                intent.putExtra(Estado, Estados[postion]);
                intent.putExtra(CEP, Ceps[postion]);
                intent.putExtra(Local, Locais[postion]);
                intent.putExtra(Preco, Precos[postion]);
                intent.putExtra(Resp, Responsaveis[postion]);
                intent.putExtra(TelMovel, TelMoveis[postion]);
                intent.putExtra(TelFixo, TelFixos[postion]);
                intent.putExtra(Descricao, Descricaos[postion]);
                intent.putExtra(Imagens, Imagem[postion]);
                intent.putExtra(ID, Ids[postion]);
                intent.putExtra(Id_Evento, Ids[postion]);
                intent.putExtra(Compas, Compa[postion]);
                intent.putExtra(Compes, Compe[postion]);
                intent.putExtra(Inters, Inter[postion]);

                startActivity(intent);
            }
        });
        return linearLayout;
    }

}
