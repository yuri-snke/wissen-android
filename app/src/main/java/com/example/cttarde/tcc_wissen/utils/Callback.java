package com.example.cttarde.tcc_wissen.utils;

import android.location.Location;

import com.example.cttarde.tcc_wissen.DTO.FollowEventDTO;
import com.example.cttarde.tcc_wissen.DTO.SeguidorDTO;
import com.example.cttarde.tcc_wissen.DTO.eventoDTO;

import java.util.ArrayList;

/**
 * Created by CT Tarde on 22/09/2017.
 */

public interface Callback {

    void onSucessProx(ArrayList<eventoDTO> arrayEvento);
    void OnSucesso(int i);
    void onSucessDest(ArrayList<eventoDTO> arrayEvento);
    void onSucessRise(ArrayList<SeguidorDTO> arrayEvento);
    void onSucessSeg(ArrayList<eventoDTO> arrayEvento);
    void onSucessSug(ArrayList<eventoDTO> arrayEvento);
    void onSucessList(ArrayList<eventoDTO> arrayEvento);
    void onSucessListF(ArrayList<FollowEventDTO> arrayEvento);
    float onSucessCep();


    void onLocationChanged(Location location);
}
