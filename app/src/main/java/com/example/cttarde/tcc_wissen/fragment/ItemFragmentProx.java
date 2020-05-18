package com.example.cttarde.tcc_wissen.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.ArrayList;


public class ItemFragmentProx extends Fragment {


    public static int count2 = listaDTO.getListaProx().size();
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



    public ArrayList<listaDTO> ArrayEvento = new ArrayList<>();
    eventoDTO evento = new eventoDTO();
    private Context context;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }


    public static Fragment newInstance2(HomeClassActivity context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt(POSITON, pos);

        return Fragment.instantiate(context, ItemFragmentProx.class.getName(), b);
    }

   public static String ImagensCapaProx[] = new String[20000];
    public static String NomesProx[] = new String[20000];
    public static String NomesRespsProx[] = new String[20000];
    public static String DataProx[] = new String[20000];
    public static String HorarioIniProx[] = new String[20000];
    public static String HorarioTermProx[] = new String[20000];
    public static String EnderecoProx[] = new String[20000];
    public static String BairroProx[] = new String[20000];
    public static String EstadosProx[] = new String[20000];
    public static String CidadeProx[] = new String[20000];
    public static String CEPProx[] = new String[20000];
    public static String LocalProx[] = new String[20000];
    public static String PrecoProx[] = new String[20000];
    public static String ResponsavelProx[] = new String[20000];
    public static String TelMovelProx[] = new String[20000];
    public static String TelFixoProx[] = new String[20000];
    public static String DescricaoProx[] = new String[20000];
    public static String ImagensProx[] = new String[20000];
    public static String EmailsProx[] = new String[20000];
    public static String IdsProx[] = new String[20000];
    public static String CompaProx[] = new String[20000];
    public static String CompeProx[] = new String[20000];
    public static String InterProx[] = new String[20000];

    public void setImagensCapaProx(){

        int f = 0;
        for (eventoDTO evento : listaDTO.getListaProx()) {
            ImagensCapaProx[f] = evento.getImagemCapa();
            NomesProx[f] = evento.getNome();
            NomesRespsProx[f] = evento.getNomeResp();
            DataProx[f] = evento.getDataIni();
            HorarioIniProx[f] = evento.getHoraIni();
            HorarioTermProx[f] = evento.getHoraTer();
            EnderecoProx[f] = evento.getEndereco();
            BairroProx[f] = evento.getBairro();
            EstadosProx[f] = evento.getEstado();
            CidadeProx[f] = evento.getCidade();
            CEPProx[f] = evento.getCEP();
            LocalProx[f] = evento.getLocal();
            PrecoProx[f] = evento.getPreco();
            ResponsavelProx[f] = evento.getResponsavel();
            TelMovelProx[f] = evento.getTelFixo();
            TelFixoProx[f] = evento.getTelMovel();
            DescricaoProx[f] = evento.getDescricao();
            ImagensProx[f] = evento.getImagens();
            EmailsProx[f] = evento.getEmailCriador();
            IdsProx[f] = evento.getId_Evento();
            CompaProx[f] = evento.getComparecerao();
            CompeProx[f] = evento.getCompareceram();
            InterProx[f] = evento.getInteressados();
            f++;
        }

    }
    static boolean first = true;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setImagensCapaProx();
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

        ImageView imageViewProximo = (ImageView) linearLayout.findViewById(R.id.destaqueImg);
       /* imageViewProximo.setImageResource(imageArrayProximos[postion]);*/
           Picasso.with(getActivity()).load(IPrequest.getImgCapa() + ImagensCapaProx[postion]).into(imageViewProximo);




        //handling click event
        imageViewProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (usuarioDTO.getEmail().equals(EmailsProx[postion])  || usuarioDTO.getEmail().equals("suporte@blindtech.com")) {
                    intent = new Intent(getActivity(), MyImageDetailActivity.class );
                }else{
                    intent = new Intent(getActivity(), ImageDetailsActivity.class);
                }
                intent.putExtra(DRAWABLE_RESOURE, ImagensCapaProx[postion]);
                intent.putExtra(Nome, NomesProx[postion]);
                intent.putExtra(NomeResp,NomesRespsProx[postion]);
                intent.putExtra(Data, DataProx[postion]);
                intent.putExtra(HorarioIni, HorarioIniProx[postion]);
                intent.putExtra(HorarioTerm, HorarioTermProx[postion]);
                intent.putExtra(Endereco, EnderecoProx[postion]);
                intent.putExtra(Bairro,BairroProx[postion]);
                intent.putExtra(Estado,EstadosProx[postion]);
                intent.putExtra(Cidade,CidadeProx[postion]);
                intent.putExtra(CEP,CEPProx[postion]);
                intent.putExtra(Local, LocalProx[postion]);
                intent.putExtra(Preco, PrecoProx[postion]);
                intent.putExtra(Resp, ResponsavelProx[postion]);
                intent.putExtra(TelMovel, TelMovelProx[postion]);
                intent.putExtra(TelFixo, TelFixoProx[postion]);
                intent.putExtra(Descricao, DescricaoProx[postion]);
                intent.putExtra(Imagens, ImagensProx[postion]);
                intent.putExtra(ID, IdsProx[postion]);
                intent.putExtra(Id_Evento, IdsProx[postion]);
                intent.putExtra(Compas, CompaProx[postion]);
                intent.putExtra(Compes, CompeProx[postion]);
                intent.putExtra(Inters, InterProx[postion]);


                startActivity(intent);



            }
        });
        return linearLayout;
    }

}
