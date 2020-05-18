<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);


$UserPrim = $_POST["UserPrim"];
$UserSecu = $_POST["UserSecu"];

$existe = false;
$response = array();
$response["success"] = false;
$result = mysqli_query($con, "SELECT * FROM `tbl_seguidores` WHERE UserPrimario = ".$UserPrim." and UserSecundario = ".$UserSecu) or die("Erro execute 1");
while(mysqli_fetch_assoc($result))
{
$response["success"] = true;		
}

echo json_encode($response);
?>