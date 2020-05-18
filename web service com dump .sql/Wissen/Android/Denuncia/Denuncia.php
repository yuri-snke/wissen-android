<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
    include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);


    $Email_User = utf8_decode(urldecode($_POST["Email_User"])); 
    $Id_Evento = $_POST["Id_Evento"];
    $Motivo = $_POST["Motivo"];
    $Descricao = $_POST["Descricao"];   
    
    
    $statement = mysqli_prepare($con, "INSERT INTO tbl_denuneventos (Email_User, Id_Evento, Motivo, Descricao) VALUES (?,?,?,?)");

    mysqli_stmt_bind_param($statement, "ssss", $Email_User, $Id_Evento, $Motivo, $Descricao);
    mysqli_stmt_execute($statement) or die;
    
    $response = array();
    $response["success"] = true;  
    echo json_encode($response);
?>          