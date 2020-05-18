<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
    include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);
	
    $Email = $_POST["Email"];
    $Senha = $_POST["Senha"];
    $PalavraSecret = $_POST["PalavraSecret"];


    $statement = mysqli_prepare($con, "UPDATE tbl_usuarios SET Email = ?, Senha = ? WHERE  PalavraSecret = ?");

    mysqli_stmt_bind_param($statement, "sss", $Email, $Senha, $PalavraSecret);
    mysqli_stmt_execute($statement) or die;
    $response = array();
    $response["success"] = true;  

    echo json_encode($response);
?>
