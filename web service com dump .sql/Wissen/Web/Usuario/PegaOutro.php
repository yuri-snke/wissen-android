<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);

$Email = $_POST["Email"];
$id_User = $_POST["id_User"];

$statement = mysqli_prepare($con, "SELECT * from tbl_usuarios where Email = ? and id_User != ?");
mysqli_stmt_bind_param($statement, "s", $Email);
mysqli_stmt_execute($statement) or die;
$response = array();
$response["success"] = true;
while(mysqli_stmt_fetch($statement)){
	$response["success"] = false;
}
echo json_encode($response);
?>