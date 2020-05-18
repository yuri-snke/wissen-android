<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
    include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);


    $Nome = utf8_decode(urldecode($_POST["Nome"])); 
    $Idade = $_POST["Idade"];
    $Email = $_POST["Email"];
    $Senha = $_POST["Senha"];	
    $Estado = utf8_decode(urldecode($_POST["Estado"]));
    $Cidade = utf8_decode(urldecode($_POST["Cidade"])); 
    $Endereco = utf8_decode(urldecode($_POST["Endereco"])); 
    $CEP = $_POST["CEP"];
    $Genero = $_POST["Genero"];
    $telCel = $_POST["telCel"];
    $PalavraSecret = utf8_decode(urldecode($_POST["PalavraSecret"]));
	
	
    $statement = mysqli_prepare($con, "INSERT INTO tbl_usuarios (Nome, Idade, Email, Senha, Estado, Cidade, Endereco, CEP, Genero, telCel, PalavraSecret) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

    mysqli_stmt_bind_param($statement, "sssssssssss", $Nome, $Idade, $Email, $Senha, $Estado, $Cidade, $Endereco, $CEP, $Genero, $telCel, $PalavraSecret);
    mysqli_stmt_execute($statement) or die;
    
    $response = array();
    $response["success"] = true;  
    echo json_encode($response);
?>		 	