<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
    include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);


    $Nome = utf8_decode(urldecode($_POST["Nome"]));
    $Idade = $_POST["Idade"];	
    $Email = $_POST["Email"];
    $Estado = utf8_decode(urldecode($_POST["Estado"]));
    $Cidade = utf8_decode(urldecode($_POST["Cidade"]));
    $Endereco = utf8_decode(urldecode($_POST["Endereco"]));
    $CEP = $_POST["CEP"];
    $telCel = $_POST["telCel"];


    $statement = mysqli_prepare($con, "UPDATE tbl_usuarios SET Nome = ?, Idade = ?, Estado = ?, Cidade = ?, Endereco = ?, CEP = ?, telCel = ? WHERE  Email = ?");

    mysqli_stmt_bind_param($statement, "ssssssss", $Nome, $Idade, $Estado, $Cidade, $Endereco, $CEP, $telCel, $Email);
    mysqli_stmt_execute($statement) or die;
    $response = array();
    $response["success"] = true;  

    echo json_encode($response);
?>
