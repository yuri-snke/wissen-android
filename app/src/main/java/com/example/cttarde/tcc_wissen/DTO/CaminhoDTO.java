package com.example.cttarde.tcc_wissen.DTO;

/**
 * Created by CT Tarde on 25/09/2017.
 */

public class CaminhoDTO {


    private static String[] Recomendados = setRecomendados();

    private static final String RiseRequest = "SELECT * FROM tbl_usuarios";
    private final String SqlListSeguidores = "SELECT usu.*, segui.Status FROM tbl_usuarios AS usu, tbl_seguidores AS segui WHERE segui.UserPrimario = " + usuarioDTO.getId_User() + " AND segui.UserSecundario = usu.id_User";
    private final String SqlListEvFollSeg = "SELECT ev.*, evSeg.Status FROM tbl_eventosseg AS evSeg, tbl_eventos AS ev WHERE evSeg.Id_Evento = ev.id and evSeg.Id_usuario = "+ usuarioDTO.getId_User() +" and evSeg.Status != 0 ORDER BY Comparecerao ASC";

    private static final String DestaqueRequest = "Select * from tbl_eventos where Premium = 1";
    private static final String ProximoRequest = "SELECT * FROM tbl_eventos where Premium = 0";
    private static final String RecomendadoRequest = "SELECT * FROM tbl_eventos where Tipo = ?";
    private final String OwnRequest = "SELECT * FROM tbl_eventos where EmailCriador = '"+ usuarioDTO.getEmail()+"'";
    private static final String PEIEI = "SELECT DISTINCT ev.* FROM tbl_usuarios usu, tbl_eventosseg AS evSeg, tbl_eventos AS ev, tbl_seguidores AS segui WHERE segui.UserPrimario = "+usuarioDTO.getId_User()+" AND evSeg.Id_usuario = segui.UserSecundario AND evSeg.Id_Evento = ev.id AND usu.id_User = segui.UserSecundario";

    private  final String SqlLista1 = "SELECT * FROM tbl_eventos WHERE Tipo Like '%" + Recomendados[0] + "%' AND Tipo Like '%" + Recomendados[1] + "%' AND Tipo Like '%" + Recomendados[2] + "%' AND Premium = 0";
    private   final String SqlLista2 = "SELECT * FROM `tbl_eventos` WHERE Tipo Like '%" + Recomendados[0] + "%' AND Tipo Like '%" + Recomendados[1] + "%' AND Premium = 0";
    private  final String SqlLista3 = "SELECT * FROM `tbl_eventos` WHERE Tipo Like '%" + Recomendados[1] + "%' AND Tipo Like '%" + Recomendados[2] + "%' AND Premium = 0";
    private  final String SqlLista4 = "SELECT * FROM `tbl_eventos` WHERE Tipo Like '%" + Recomendados[0] + "%' AND Tipo Like '%" + Recomendados[2] + "%' AND Premium = 0";
    private final String SqlLista5 = "SELECT * FROM `tbl_eventos` WHERE Tipo Like '%" + Recomendados[0] + "%' AND Premium = 0";
    private final String SqlLista6 = "SELECT * FROM `tbl_eventos` WHERE Tipo Like '%" + Recomendados[1] + "%' AND Premium = 0";
    private final String SqlLista7 = "SELECT * FROM `tbl_eventos` WHERE Tipo Like '%" + Recomendados[2] + "%' AND Premium = 0";
    private static final String ListaAll = "SELECT * FROM tbl_eventos";

    public String getSqlListSeguidores() {
        return SqlListSeguidores;
    }

    public String getSqlListEvFollSeg() {
        return SqlListEvFollSeg;
    }

    public static String getListaAll() {
        return ListaAll;
    }

    public  String getSqlLista1() {
        return SqlLista1;
    }

    public  String getSqlLista2() {
        return SqlLista2;
    }

    public  String getSqlLista3() {
        return SqlLista3;
    }

    public  String getSqlLista4() {
        return SqlLista4;
    }

    public  String getSqlLista5() {
        return SqlLista5;
    }

    public  String getSqlLista6() {
        return SqlLista6;
    }

    public  String getSqlLista7() {
        return SqlLista7;
    }

    public static String getDestaqueRequest() {

        return DestaqueRequest;
    }

    public static String getProximoRequest() {

        return ProximoRequest;
    }

    public String getOwnRequest() {

        return OwnRequest;
    }

    public static String getRecomendadoRequest() {
        return RecomendadoRequest;
    }

    public static String[] setRecomendados() {
        return usuarioDTO.getPreferencias().trim().split(";");
    }

    public static String getPEIEI() {
        return PEIEI;
    }

    public static String getRiseRequest() {
        return RiseRequest;
    }
}
