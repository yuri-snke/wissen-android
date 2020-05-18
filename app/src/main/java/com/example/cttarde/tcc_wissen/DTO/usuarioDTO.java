package com.example.cttarde.tcc_wissen.DTO;

/**
 * Created by CT Tarde on 09/08/2017.
 */

public class usuarioDTO {
    private static String Id_User;
    private static String Nome;
    private static String DtNascim;
    private static String Idade;
    private static String Email;
    private static String Senha;
    private static String Estado;
    private static String Cidade;
    private static String Endereco;
    private static String CEP;
    private static String Genero;
    private static String telCel;
    private static String Preferencias;
    private static String PalavraSecret;
    private static String ImagemPerfil;

    public static String getImagemPerfil() {
        return ImagemPerfil;
    }

    public static void setImagemPerfil(String imagemPerfil) {
        ImagemPerfil = imagemPerfil;
    }

    public static String getPreferencias() {
        return Preferencias;
    }

    public static void setPreferencias(String preferencias) {
        Preferencias = preferencias;
    }

    public static String getId_User() {
        return Id_User;
    }

    public static void setId_User(String id_User) {
        Id_User = id_User;
    }

    public static String getNome() {
        return Nome;
    }

    public static void setNome(String nome) {
        Nome = nome;
    }

    public static String getDtNascim() {
        return DtNascim;
    }

    public static void setDtNascim(String dtNascim) {
        DtNascim = dtNascim;
    }

    public static String getIdade() {
        return Idade;
    }

    public static void setIdade(String idade) {
        Idade = idade;
    }

    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String email) {
        Email = email;
    }

    public static String getSenha() {
        return Senha;
    }

    public static void setSenha(String senha) {
        Senha = senha;
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

    public static String getEndereco() {
        return Endereco;
    }

    public static void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public static String getCEP() {
        return CEP;
    }

    public static void setCEP(String CEP) {
        usuarioDTO.CEP = CEP;
    }

    public static String getGenero() {
        return Genero;
    }

    public static void setGenero(String genero) {
        Genero = genero;
    }

    public static String getTelCel() {
        return telCel;
    }

    public static void setTelCel(String telCel) {
        usuarioDTO.telCel = telCel;
    }

    public static String getPalavraSecret() {
        return PalavraSecret;
    }

    public static void setPalavraSecret(String palavraSecret) {
        PalavraSecret = palavraSecret;
    }

}

