package com.example.cttarde.tcc_wissen.DPO;


public class IPrequest {

    private static final String ImgPerfil = "http://10.104.205.23:8087/Wissen/Imagens/ImagemPerfil/";
   private static final String ImgCapa = "http://10.104.205.23:8087/Wissen/Imagens/ImagemEventoCapa/";
   private static final String Usuario = "http://10.104.205.23:8087/Wissen/Android/Usuario/";
   private static final String Denuncia = "http://10.104.205.23:8087/Wissen/Android/Denuncia/";
   private static final String Evento = "http://10.104.205.23:8087/Wissen/Android/Eventos/";
   private static final String Seguidor = "http://10.104.205.23:8087/Wissen/Android/Seguidores/";

//    private static final String ImgPerfil = "http://app.blindtech.com.br/Webservice/Imagens/ImagemPerfil/";
//    private static final String ImgCapa = "http://app.blindtech.com.br/Webservice/Imagens/ImagemEventoCapa/";
//    private static final String Usuario = "http://app.blindtech.com.br/Webservice/Android/Usuario/";
//    private static final String Denuncia = "http://app.blindtech.com.br/Webservice/Android/Denuncia/";
//    private static final String Evento = "http://app.blindtech.com.br/Webservice/Android/Eventos/";
//    private static final String Seguidor = "http://app.blindtech.com.br/Webservice/Android/Seguidores/";

    public static String getImgPerfil() {
        return ImgPerfil;
    }

    public static String getImgCapa() {
        return ImgCapa;
    }

    public static String getUsuario() {
        return Usuario;
    }

    public static String getDenuncia() {
        return Denuncia;
    }

    public static String getEvento() {
        return Evento;
    }

    public static String getSeguidor() {
        return Seguidor;
    }
}
