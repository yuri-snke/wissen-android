<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);

$Imagem = $_POST["Imagem"];
$Email = $_POST["Email"];
$ImagemPerfil = $_POST["ImagemPerfil"];

$imagem = base64_decode($Imagem);

list($width, $height) = getimagesizefromstring($imagem);

$ImagemAnt = imagecreatefromstring(base64_decode($Imagem));
$ImagemNov = imagecreatetruecolor(200, 200);

imagecopyresampled($ImagemNov, $ImagemAnt, 0, 0, 0, 0, 200, 200, $width, $height);
ob_start();
imagepng($ImagemNov);
$data = ob_get_contents();
ob_end_clean();

$response = array();
	file_put_contents('C:/xampp/htdocs/Wissen/Imagens/ImagemPerfil/'.$ImagemPerfil, $data);
	$statement = mysqli_prepare($con, "UPDATE tbl_usuarios SET ImagemPerfil = ? WHERE Email = ?");

	mysqli_stmt_bind_param($statement, "ss", $ImagemPerfil, $Email);
	mysqli_stmt_execute($statement) or die;	
	$response["success"] = true;  


echo json_encode($response);

?>
