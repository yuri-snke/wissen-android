<?php
    include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);
    
    $Email = $_POST["Email"];
    $Senha = $_POST["Senha"];




    
    $statement = mysqli_prepare($con, "SELECT * FROM tbl_usuarios WHERE Email = ? AND Senha = ?");

    mysqli_stmt_bind_param($statement, "ss", $Email, $Senha);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);

    mysqli_stmt_bind_result($statement, $id_User, $Nome, $Idade, $Email, $Senha, $Estado, $Cidade, $Endereco, $CEP, $Genero, $telCel, $Privado, $ImagemPerfil, $Preferencias, $PalavraSecret, $Latitude, $Longitude);
    
	    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["id_User"] = $id_User;
        $response["Nome"] = $Nome;
        $response["Idade"] = $Idade;
        $response["Email"] = $Email;
        $response["Senha"] = $Senha;
        $response["Estado"] = $Estado;
        $response["Cidade"] = $Cidade;
        $response["Endereco"] = $Endereco;
        $response["CEP"] = $CEP;
        $response["Genero"] = $Genero;
        $response["telCel"] = $telCel;
	$response["Privado"] = $Privado;
	$response["imagemPerfil"] = $ImagemPerfil;
	$response["Preferencias"] = $Preferencias;
        $response["PalavraSecret"] = $PalavraSecret;
	$response["Latitude"] = $Latitude;
	$response["Longitude"] = $Longitude;
    }
    
    utf8_encode_deep($response);
    
    
$result = mysqli_query($con, "SELECT * FROM `tbl_premium`") or die("Erro execute 1");
while($row = mysqli_fetch_assoc($result))
{
    $existe = true;
    if(strtotime($row["DataExp"]) == strtotime(date('Y-m-d')) or strtotime($row["DataExp"]) < strtotime(date('Y-m-d')))
    {
        mysqli_query($con, "DELETE FROM `tbl_premium` WHERE `tbl_premium`.`id` =".$row["id"]);

        mysqli_query($con, "UPDATE `tbl_eventos` SET `Premium` = '0' WHERE `tbl_eventos`.`EmailCriador` = '".$row["Email"]."'");
    }
}


    
    echo json_encode($response);

    function utf8_encode_deep(&$input) {

    if (is_string($input)) {
        $input = utf8_encode($input);
    } else if (is_array($input)) {
        foreach ($input as &$value) {
            utf8_encode_deep($value);
        }
        unset($value);
    } else if (is_object($input)) {
        $vars = array_keys(get_object_vars($input));
        foreach ($vars as $var) {
            utf8_encode_deep($input->$var);
        }
    }
}
?>