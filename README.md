# Wissen - Gerenciador de Eventos

Essa aplicação que tem como função gerenciar eventos, foi desenvolvida com a finalidade de ser um projeto de TCC do SENAI Ary Torres.

## Requisitos:

- Android Studio.
- XAMPP configurado com a porta 8087 do Apache.
- Ter instalado o Apache Tomcat 8.0.27.0.

## Configuração
  
- Após o clone do projeto, mover a pasta Wissen que está em [web service com dump .sql](https://github.com/yurisnke/Wissen-ANDROID/tree/master/web%20service%20com%20dump%20.sql) para o diretório do XAMPP dentro da htdocs.
- Inserir o banco de dados no mysql.
- Caso haja ncessidade de utilizar o webservice a partir de outro local, modificar o endereço IP da classe IPRequest.java dentro da pasta app/src/main/java/com/example/cttarde/tcc_wissen/DPO/.
```  
      private static final String ImgPerfil = "http://localhost:8087/Wissen/Imagens/ImagemPerfil/";
      private static final String ImgEventCapa = "http://localhost:8087/Wissen/Imagens/ImagemEventoCapa/";
      private static final String Usuario = "http://localhost:8087/Wissen/Web/Usuario/";
      private static final String Eventos = "http://localhost:8087/Wissen/Web/Eventos/";
      private static final String Denuncia = "http://localhost:8087/Wissen/Web/Denuncia/";  
      private static final String Seguidores = "http://localhost:8087/Wissen/Web/Seguidores/";
```  
  
## Autoria e contribuições

  André Pessoa - Front-End WEB  
  Gustavo Daniel - Front-End Mobile  
  Gabriel Moreira - Back-End WEB  
  Victor Mota - Back-End Mobile  
  Yuri Oliveira - DBA  

## Documentação

  Toda a documentação está disponível em:  
  [Documentação - Wissen](https://drive.google.com/file/d/1F5SahXPKcBgM-gkpSa3UhC6aesu4IiIY/view)
