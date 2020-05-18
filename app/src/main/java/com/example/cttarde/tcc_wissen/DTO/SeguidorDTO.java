package com.example.cttarde.tcc_wissen.DTO;

/**
 * Created by CT Tarde on 20/10/2017.
 */

public class SeguidorDTO {
    private String id;
    private String NomeSeg;
    private  String Idade;
    private  String Cel;
    private  String Estado;
    private  String Cidade;
    private  String CEP;
    private  String Status;
    private  String ImagemPerfil;

    public String getNomeSeg() {
        return NomeSeg;
    }

    public void setNomeSeg(String nomeSeg) {
        NomeSeg = nomeSeg;
    }

    public String getIdade() {
        return Idade;
    }

    public void setIdade(String idade) {
        Idade = idade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getCidade() {
        return Cidade;
    }

    public  void setCidade(String cidade) {
        Cidade = cidade;
    }

    public  String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        CEP = CEP;
    }


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public  String getImagemPerfil() {
        return ImagemPerfil;
    }

    public  void setImagemPerfil(String imagemPerfil) {
        ImagemPerfil = imagemPerfil;
    }

    public  String getCel() {
        return Cel;
    }

    public void setCel(String cel) {
        Cel = cel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
