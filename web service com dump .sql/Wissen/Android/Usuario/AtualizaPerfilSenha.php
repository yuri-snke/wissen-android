<?php
    include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);


    $id_User = $_POST["id_User"];
    
    $Senha = $_POST["Senha"];
    



    $statement = mysqli_prepare($con, "UPDATE tbl_usuarios SET Senha = ? WHERE  id_User = ?");

    mysqli_stmt_bind_param($statement, "ss", $Senha,$id_User);
    mysqli_stmt_execute($statement) or die;
    $response = array();
    $response["success"] = true;  

    echo json_encode($response);
?>
