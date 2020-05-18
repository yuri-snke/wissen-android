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


public class ItemFragmentSug extends Fragment {
    public static int count4 = listaDTO.getListaSug().size();
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

   // private int[] imageArraySugeridos = new int[]{R.drawable.seguidos_5, R.drawable.seguidos_4,
         //   R.drawable.seguidos_3, R.drawable.seguidos_2, R.drawable.seguidos_1};

    static String ImagensCapa[] = new String[20000];
   public static String NomesSug[] = new String[20000];
    public static String NomesRespSug[] = new String[20000];
    public static String DataSug[] = new String[20000];
    public static String HorarioIniSug[] = new String[20000];
    public static String HorarioTermSug[] = new String[20000];
    public static String EnderecoSug[] = new String[20000];
    public static String BairroSug[] = new String[20000];
    public static String CidadeSug[] = new String[20000];
    public static String EstadoSug[] = new String[20000];
    public static String CEPSug[] = new String[20000];
    public static String LocalSug[] = new String[20000];
    public static String PrecoSug[] = new String[20000];
    public static String ResponsavelSug[] = new String[20000];
    public static String TelMovelSug[] = new String[20000];
    public static String TelFixoSug[] = new String[20000];
    public static String DescricaoSug[] = new String[20000];
    public static String ImagemSug[] = new String[20000];
    public static String EmailsSug[] = new String[20000];
    public static String IdsSug[] = new String[20000];
    public static String CompaSug[] = new String[20000];
    public static String CompeSug[] = new String[20000];
    public static String InterSug[] = new String[20000];

    public void setImagensCapaSug(){
        int f = 0;
        for (eventoDTO evento : listaDTO.getListaSug()) {
            ImagensCapa[f] = evento.getImagemCapa();
            NomesSug[f] = evento.getNome();
            NomesRespSug[f] = evento.getNomeResp();
            DataSug[f] = evento.getDataIni();
            HorarioIniSug[f] = evento.getHoraIni();
            HorarioTermSug[f] = evento.getHoraTer();
            EnderecoSug[f] = evento.getEndereco();
            BairroSug[f] = evento.getBairro();
            CidadeSug[f] = evento.getCidade();
            EstadoSug[f] = evento.getEstado();
            CEPSug[f] = evento.getCEP();
            LocalSug[f] = evento.getLocal();
            PrecoSug[f] = evento.getPreco();
            ResponsavelSug[f] = evento.getResponsavel();
            TelMovelSug[f] = evento.getTelFixo();
            TelFixoSug[f] = evento.getTelMovel();
            DescricaoSug[f] = evento.getDescricao();
            ImagemSug[f] = evento.getImagens();
            EmailsSug[f] = evento.getEmailCriador();
            IdsSug[f] = evento.getId_Evento();
            CompaSug[f] = evento.getComparecerao();
            CompeSug[f] = evento.getCompareceram();
            InterSug[f] = evento.getInteressados();

            f++;
        }

    }
    static boolean first = true;

    public static Fragment newInstance4(HomeClassActivity context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt(POSITON, pos);

        return Fragment.instantiate(context, ItemFragmentSug.class.getName(), b);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setImagensCapaSug();


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

        ImageView imageViewSugeridos = (ImageView) linearLayout.findViewById(R.id.destaqueImg);
       /* imageViewProximo.setImageResource(imageArrayProximos[postion]);*/
        Picasso.with(getActivity()).load(IPrequest.getImgCapa() + ImagensCapa[postion]).into(imageViewSugeridos);

        if(i<ImagensCapa.length-1){
            i++;
        }else{
            i=0;

        }

        //handling click event
        imageViewSugeridos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (usuarioDTO.getEmail().equals(EmailsSug[postion])  || usuarioDTO.getEmail().equals("suporte@blindtech.com")) {
                    intent = new Intent(getActivity(), MyImageDetailActivity.class);
                }else{
                    intent = new Intent(getActivity(), ImageDetailsActivity.class);
                }
               // intent.putExtra(DRAWABLE_RESOURE, imageArraySugeridos[postion]);
                intent.putExtra(DRAWABLE_RESOURE, ImagensCapa[postion]);
                intent.putExtra(Nome, NomesSug[postion]);
                intent.putExtra(NomeResp, NomesRespSug[postion]);
                intent.putExtra(Data, DataSug[postion]);
                intent.putExtra(HorarioIni, HorarioIniSug[postion]);
                intent.putExtra(HorarioTerm, HorarioTermSug[postion]);
                intent.putExtra(Endereco, EnderecoSug[postion]);
                intent.putExtra(Bairro, BairroSug[postion]);
                intent.putExtra(Cidade, CidadeSug[postion]);
                intent.putExtra(Estado, EstadoSug[postion]);
                intent.putExtra(CEP, CEPSug[postion]);
                intent.putExtra(Local, LocalSug[postion]);
                intent.putExtra(Preco, PrecoSug[postion]);
                intent.putExtra(Resp, ResponsavelSug[postion]);
                intent.putExtra(TelMovel, TelMovelSug[postion]);
                intent.putExtra(TelFixo, TelFixoSug[postion]);
                intent.putExtra(Descricao, DescricaoSug[postion]);
                intent.putExtra(Imagens, ImagemSug[postion]);
                intent.putExtra(ID, IdsSug[postion]);
                intent.putExtra(Id_Evento, IdsSug[postion]);
                intent.putExtra(Compas, CompaSug[postion]);
                intent.putExtra(Compes, CompeSug[postion]);
                intent.putExtra(Inters, InterSug[postion]);

                startActivity(intent);
            }
        });
        return linearLayout;
    }

}
