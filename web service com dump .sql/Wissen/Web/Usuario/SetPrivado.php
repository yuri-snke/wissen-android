<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');

    include 'Conf.php';
    $con = mysqli_connect($local, $login, $pass, $base);

    $Privado = $_POST["Privado"];
    $Email = $_POST["Email"];	
    $statement = mysqli_prepare($con, "UPDATE tbl_usuarios SET Privado = ? WHERE Email = ?");
    mysqli_stmt_bind_param($statement, "ss", $Privado, $Email);
    mysqli_stmt_execute($statement) or die;
    $response = array();
    $response["success"] = true;  

    echo json_encode($response);
?>
