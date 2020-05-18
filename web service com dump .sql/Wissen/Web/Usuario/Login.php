<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
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