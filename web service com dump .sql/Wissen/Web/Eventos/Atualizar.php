<?php

header('Content-Type: application/json');
header('Character-Encoding: utf-8');

include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);

$id = $_POST["Id"];
$Nome = utf8_decode(urldecode($_POST["Nome"]));
$DataInicio = $_POST["DataInicio"];
$DataTermino = $_POST["DataTermino"];
$HoraInicio = $_POST["HoraInicio"];	
$HoraTermino = $_POST["HoraTermino"];
$Responsavel = utf8_decode(urldecode($_POST["Responsavel"]));
$Preco = $_POST["Preco"];
$Descricao = utf8_decode(urldecode($_POST["Descricao"]));
$Imagens = $_POST["Imagens"];
$ImagemCapa = $_POST["ImagemCapa"];
$CEP = $_POST["CEP"];
$Estado = utf8_decode(urldecode($_POST["Estado"]));
$Cidade = utf8_decode(urldecode($_POST["Cidade"]));
$Bairro = utf8_decode(urldecode($_POST["Bairro"]));
$Local = utf8_decode(urldecode($_POST["Local"]));
$Endereco = utf8_decode(urldecode($_POST["Endereco"]));
$TelFixo = $_POST["TelFixo"];
$TelMovel = $_POST["TelMovel"];
$Tipo = $_POST["Tipo"];
$CpfCnpj = $_POST["CpfCnpj"];
$Privado = $_POST["Privado"];



$Conta = $_POST["VerificaImagens"];
if($Conta == 23){
	$foto2 = $_POST["foto2"];
	$NomeFoto2 = $_POST["NomeFoto2"];
	$foto3 = $_POST["foto3"];
	$NomeFoto3 = $_POST["NomeFoto3"];



	$DecodedFoto2 = base64_decode($foto2);
	list($width2, $height2) = getimagesizefromstring($DecodedFoto2);
	$ImagemAnt2 = imagecreatefromstring(base64_decode($foto2));
	$ImagemNov2 = imagecreatetruecolor(800, 500);
	imagecopyresampled($ImagemNov2, $ImagemAnt2, 0, 0, 0, 0, 800, 500, $width2, $height2);
	ob_start();
	imagepng($ImagemNov2);
	$data2 = ob_get_contents();
	ob_end_clean();
	file_put_contents('C:/xampp/htdocs/Wissen/Imagens/ImagemEventoCapa/'.$NomeFoto2, $data2);


	
	$DecodedFoto3 = base64_decode($foto3);
	list($width3, $height3) = getimagesizefromstring($DecodedFoto3);
	$ImagemAnt3 = imagecreatefromstring(base64_decode($foto3));
	$ImagemNov3 = imagecreatetruecolor(800, 500);
	imagecopyresampled($ImagemNov3, $ImagemAnt3, 0, 0, 0, 0, 800, 500, $width3, $height3);
	ob_start();
	imagepng($ImagemNov3);
	$data3 = ob_get_contents();
	ob_end_clean();
	file_put_contents('C:/xampp/htdocs/Wissen/Imagens/ImagemEventoCapa/'.$NomeFoto3, $data3);
}
if($Conta == 2){
	$foto2 = $_POST["foto2"];
	$NomeFoto2 = $_POST["NomeFoto2"];

	$DecodedFoto2 = base64_decode($foto2);
	list($width2, $height2) = getimagesizefromstring($DecodedFoto2);
	$ImagemAnt2 = imagecreatefromstring(base64_decode($foto2));
	$ImagemNov2 = imagecreatetruecolor(800, 500);
	imagecopyresampled($ImagemNov2, $ImagemAnt2, 0, 0, 0, 0, 800, 500, $width2, $height2);
	ob_start();
	imagepng($ImagemNov2);
	$data2 = ob_get_contents();
	ob_end_clean();
	file_put_contents('C:/xampp/htdocs/Wissen/Imagens/ImagemEventoCapa/'.$NomeFoto2, $data2);
}
if($Conta == 3){
	$foto3 = $_POST["foto3"];
	$NomeFoto3 = $_POST["NomeFoto3"];

	$DecodedFoto3 = base64_decode($foto3);
	list($width3, $height3) = getimagesizefromstring($DecodedFoto3);
	$ImagemAnt3 = imagecreatefromstring(base64_decode($foto3));
	$ImagemNov3 = imagecreatetruecolor(800, 500);
	imagecopyresampled($ImagemNov3, $ImagemAnt3, 0, 0, 0, 0, 800, 500, $width3, $height3);
	ob_start();
	imagepng($ImagemNov3);
	$data3 = ob_get_contents();
	ob_end_clean();
	file_put_contents('C:/xampp/htdocs/Wissen/Imagens/ImagemEventoCapa/'.$NomeFoto3, $data3);
}


$foto1 = $_POST["foto1"];
$NomeFoto1 = $_POST["NomeFoto1"];


$DecodedFoto1 = base64_decode($foto1);
list($width, $height) = getimagesizefromstring($DecodedFoto1);
$ImagemAnt = imagecreatefromstring(base64_decode($foto1));
$ImagemNov = imagecreatetruecolor(800, 500);
imagecopyresampled($ImagemNov, $ImagemAnt, 0, 0, 0, 0, 800, 500, $width, $height);
ob_start();
imagepng($ImagemNov);
$data = ob_get_contents();
ob_end_clean();
file_put_contents('C:/xampp/htdocs/Wissen/Imagens/ImagemEventoCapa/'.$NomeFoto1, $data);


$statement = mysqli_prepare($con, "UPDATE tbl_eventos SET Nome = ?, DataInicio = ?, DataTermino = ?, HoraInicio = ?, HoraTermino = ?, Responsavel = ?, Preco = ?, Descricao = ?, Imagens = ?, ImagemCapa = ?,CEP = ?, Estado = ?, Cidade = ?, Bairro = ?, Local = ?, Endereco = ?, TelFixo = ?, TelMovel = ?, Tipo = ?, CpfCnpj = ?, Privado = ? WHERE tbl_eventos.id = ?") or die("Erro no statement");




mysqli_stmt_bind_param($statement, "ssssssssssssssssssssss", $Nome, $DataInicio, $DataTermino, $HoraInicio, $HoraTermino, $Responsavel, $Preco, $Descricao, $Imagens, $ImagemCapa, $CEP,$Estado,$Cidade,$Bairro, $Local, $Endereco, $TelFixo, $TelMovel, $Tipo, $CpfCnpj, $Privado, $id) or die("Erro no param");
mysqli_stmt_execute($statement) or die;

$response = array();
$response["success"] = true;  






echo json_encode($response);
?>		 	