<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);

$id = $_POST["Id"];

$Imagens = $_POST["Imagens"];
$ImagemCapa = $_POST["ImagemCapa"];
$Conta = $_POST["VerificaImagens"];
$ContaFoto = $_POST["VerificaImagensFoto"];

if ($ContaFoto == 2) {

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

}

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





$statement = mysqli_prepare($con, "UPDATE tbl_eventos SET Imagens = ?, ImagemCapa = ? WHERE tbl_eventos.id = ?") or die("Erro no statement");




mysqli_stmt_bind_param($statement, "sss", $Imagens, $ImagemCapa, $id) or die("Erro no param");
mysqli_stmt_execute($statement) or die;

$response = array();
$response["success"] = true;  






echo json_encode($response);
?>		 	