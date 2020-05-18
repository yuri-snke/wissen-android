package com.example.cttarde.tcc_wissen.DTO;

import java.util.ArrayList;

/**
 * Created by CT Tarde on 25/09/2017.
 */

public class listaDTO {

    private static ArrayList<eventoDTO> Lista;
    private static ArrayList<eventoDTO> ListaProx;
    private static ArrayList<eventoDTO> ListaSeg;
    private static ArrayList<eventoDTO> ListaSug;
    private static ArrayList<eventoDTO> ListaOwn;
    private static ArrayList<eventoDTO> ListaAll;
    //private static ArrayList<eventoDTO> ListaNoso;
    private static ArrayList<FollowEventDTO> ListaNoso;
    private static ArrayList<SeguidorDTO> ListaRise;
    private static ArrayList<SeguidorDTO> ListaTrial;

    public static ArrayList<SeguidorDTO> getListaTrial() {
        return ListaTrial;
    }

    public static void setListaTrial(ArrayList<SeguidorDTO> listaTrial) {
        ListaTrial = listaTrial;
    }

    public static ArrayList<FollowEventDTO> getListaNoso() {
        return ListaNoso;
    }

    public static void setListaNoso(ArrayList<FollowEventDTO> listaNoso) {
        ListaNoso = listaNoso;
    }

    public static ArrayList<eventoDTO> getListaAll() {
        return ListaAll;
    }

    public static void setListaAll(ArrayList<eventoDTO> listaAll) {
        ListaAll = listaAll;
    }

    public static ArrayList<eventoDTO> getListaProx() {
        return ListaProx;
    }

    public static void setListaProx(ArrayList<eventoDTO> listaProx) {
        ListaProx = listaProx;
    }

    public static ArrayList<eventoDTO> getListaSeg() {
        return ListaSeg;
    }

    public static void setListaSeg(ArrayList<eventoDTO> listaSeg) {
        ListaSeg = listaSeg;
    }

    public static ArrayList<eventoDTO> getLista() {
        return Lista;
    }

    public static void setLista(ArrayList<eventoDTO> lista) {
        Lista = lista;
    }

    public static ArrayList<eventoDTO> getListaSug() {
        return ListaSug;
    }

    public static void setListaSug(ArrayList<eventoDTO> listaSug) {
        ListaSug = listaSug;
    }

    public static ArrayList<eventoDTO> getListaOwn() {
        return ListaOwn;
    }

    public static void setListaOwn(ArrayList<eventoDTO> listaOwn) {
        ListaOwn = listaOwn;
    }

    public static ArrayList<SeguidorDTO> getListaRise() {
        return ListaRise;
    }

    public static void setListaRise(ArrayList<SeguidorDTO> listaRise) {
        ListaRise = listaRise;
    }
}
