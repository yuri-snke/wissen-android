<?php
    include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);


    $id_User = $_POST["id_User"];
    
    $Nome = $_POST["Nome"];
    $Idade = $_POST["Idade"];	
    $Email = $_POST["Email"];
    $Estado = $_POST["Estado"];
    $Cidade = $_POST["Cidade"];
    $Endereco = $_POST["Endereco"];
    $CEP = $_POST["CEP"];
    $telCel = $_POST["telCel"];
   

    


    $statement = mysqli_prepare($con, "UPDATE tbl_usuarios SET Nome = ?, Idade = ?, Estado = ?, Cidade = ?, Endereco = ?, CEP = ?, telCel = ?,Email = ? WHERE  id_User = ?");

    mysqli_stmt_bind_param($statement, "sssssssss", $Nome, $Idade, $Estado, $Cidade, $Endereco, $CEP, $telCel, $Email,$id_User);
    mysqli_stmt_execute($statement) or die;
    $response = array();
    $response["success"] = true;  

    echo json_encode($response);
?>
