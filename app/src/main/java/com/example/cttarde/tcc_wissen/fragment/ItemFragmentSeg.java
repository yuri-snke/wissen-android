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
import com.example.cttarde.tcc_wissen.activity.HomeClassActivity;
import com.example.cttarde.tcc_wissen.detail.ImageDetailsActivity;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.detail.MyImageDetailActivity;
import com.squareup.picasso.Picasso;


public class ItemFragmentSeg extends Fragment {
    public static int count3 = listaDTO.getListaSeg().size();
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
    private static final String Compas = "Compa";
    private static final String Compes = "Compe";
    private static final String Inters = "Inter";



    static String ImagensCapaSeg[] = new String[20000];
  public  static String NomesSeg[] = new String[20000];
    public  static String NomesRespSeg[] = new String[20000];
    public static String DataSeg[] = new String[20000];
    public static String HorarioIniSeg[] = new String[20000];
    public static String HorarioTermSeg[] = new String[20000];
    public static String EnderecoSeg[] = new String[20000];
    public  static String BairroSeg[] = new String[20000];
    public  static String CidadeSeg[] = new String[20000];
    public  static String EstadoSeg[] = new String[20000];
    public  static String CEPSeg[] = new String[20000];
    public static String LocalSeg[] = new String[20000];
    public static String PrecoSeg[] = new String[20000];
    public static String ResponsavelSeg[] = new String[20000];
    public static String TelMovelSeg[] = new String[20000];
    public static String TelFixoSeg[] = new String[20000];
    public static String DescricaoSeg[] = new String[20000];
    public static String ImagemSeg[] = new String[20000];
    public static String IdsSeg[] = new String[20000];
    public static String CompaSeg[] = new String[20000];
    public static String CompeSeg[] = new String[20000];
    public static String InterSeg[] = new String[20000];



    public void setImagensCapaSeg(){
        int f = 0;
        for (eventoDTO evento : listaDTO.getListaSeg()) {
            ImagensCapaSeg[f] = evento.getImagemCapa();
            NomesSeg[f] = evento.getNome();
            NomesRespSeg[f] = evento.getNomeResp();
            DataSeg[f] = evento.getDataIni();
            HorarioIniSeg[f] = evento.getHoraIni();
            HorarioTermSeg[f] = evento.getHoraTer();
            EnderecoSeg[f] = evento.getEndereco();
            BairroSeg[f] = evento.getBairro();
            CidadeSeg[f] = evento.getCidade();
            EstadoSeg[f] = evento.getEstado();
            CEPSeg[f] = evento.getCEP();
            LocalSeg[f] = evento.getLocal();
            PrecoSeg[f] = evento.getPreco();
            ResponsavelSeg[f] = evento.getResponsavel();
            TelMovelSeg[f] = evento.getTelFixo();
            TelFixoSeg[f] = evento.getTelMovel();
            DescricaoSeg[f] = evento.getDescricao();
            ImagemSeg[f] = evento.getImagens();
            IdsSeg[f] = evento.getId_Evento();
            CompaSeg[f] = evento.getComparecerao();
            CompeSeg[f] = evento.getCompareceram();
            InterSeg[f] = evento.getInteressados();

            f++;
        }

    }
    static boolean first = true;


    public static Fragment newInstance3(HomeClassActivity context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt(POSITON, pos);

        return Fragment.instantiate(context, ItemFragmentSeg.class.getName(), b);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setImagensCapaSeg();
            first = false;

    }
    static int i = 0;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }


        final int postion = this.getArguments().getInt(POSITON);

        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_image, container, false);

        ImageView imageViewSeguidos = (ImageView) linearLayout.findViewById(R.id.destaqueImg);
       /* imageViewProximo.setImageResource(imageArrayProximos[postion]);*/
        Picasso.with(getActivity()).load(IPrequest.getImgCapa() + ImagensCapaSeg[postion]).into(imageViewSeguidos);


        //handling click event
        imageViewSeguidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                if (usuarioDTO.getEmail().equals("suporte@blindtech.com")) {
                    intent = new Intent(getActivity(), MyImageDetailActivity.class);
                }else{
                    intent = new Intent(getActivity(), ImageDetailsActivity.class);
                }

                //Intent intent = new Intent(getActivity(), ImageDetailsActivity.class);
                intent.putExtra(DRAWABLE_RESOURE, ImagensCapaSeg[postion]);
                intent.putExtra(Nome, NomesSeg[postion]);
                intent.putExtra(NomeResp, NomesRespSeg[postion]);
                intent.putExtra(Data, DataSeg[postion]);
                intent.putExtra(HorarioIni, HorarioIniSeg[postion]);
                intent.putExtra(HorarioTerm, HorarioTermSeg[postion]);
                intent.putExtra(Endereco, EnderecoSeg[postion]);
                intent.putExtra(Bairro, BairroSeg[postion]);
                intent.putExtra(Cidade, CidadeSeg[postion]);
                intent.putExtra(Estado, EstadoSeg[postion]);
                intent.putExtra(CEP, CEPSeg[postion]);
                intent.putExtra(Local, LocalSeg[postion]);
                intent.putExtra(Preco, PrecoSeg[postion]);
                intent.putExtra(Resp, ResponsavelSeg[postion]);
                intent.putExtra(TelMovel, TelMovelSeg[postion]);
                intent.putExtra(TelFixo, TelFixoSeg[postion]);
                intent.putExtra(Descricao, DescricaoSeg[postion]);
                intent.putExtra(Imagens, ImagemSeg[postion]);
                intent.putExtra(ID, IdsSeg[postion]);
                intent.putExtra(Id_Evento, IdsSeg[postion]);
                intent.putExtra(Compas, CompaSeg[postion]);
                intent.putExtra(Compes, CompeSeg[postion]);
                intent.putExtra(Inters, InterSeg[postion]);
                startActivity(intent);
            }
        });
        return linearLayout;
    }

}
