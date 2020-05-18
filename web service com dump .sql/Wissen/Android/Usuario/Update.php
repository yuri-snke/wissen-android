<?php
    include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);


    $Preferencias = $_POST["Preferencias"];
    $Email = $_POST["Email"];	

    $statement = mysqli_prepare($con, "UPDATE tbl_usuarios SET Preferencias = ? WHERE Email = ?");

    mysqli_stmt_bind_param($statement, "ss", $Preferencias, $Email);
    mysqli_stmt_execute($statement) or die;
    $response = array();
    $response["success"] = true;  

    echo json_encode($response);
?>
