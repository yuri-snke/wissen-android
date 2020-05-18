package com.example.cttarde.tcc_wissen.viacep;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by CT Tarde on 15/08/2017.
 */

public class CepDTO extends AppCompatActivity {

    private static String Endereco;
    private static String Estado;
    private static String Cidade;
    private static String CEP;
    private static String Compl;

    public static String getEndereco() {
        return Endereco;
    }

    public static void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public static String getEstado() {
        return Estado;
    }

    public static void setEstado(String estado) {
        Estado = estado;
    }

    public static String getCidade() {
        return Cidade;
    }

    public static void setCidade(String cidade) {
        Cidade = cidade;
    }

    public static String getCEP() {
        return CEP;
    }

    public static void setCEP(String CEP) {
        CepDTO.CEP = CEP;
    }

    public static String getCompl() {
        return Compl;
    }

    public static void setCompl(String compl) {
        Compl = compl;
    }
}
