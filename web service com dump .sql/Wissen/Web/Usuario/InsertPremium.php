<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);

$Email = $_POST["Email"];
$DataExp = $_POST["DataExp"];

$statement = mysqli_prepare($con, "INSERT INTO `tbl_premium` (`Email`, `DataExp`) VALUES (?, ?)");
mysqli_stmt_bind_param($statement, "ss", $Email, $DataExp);
mysqli_stmt_execute($statement) or die;
$response = array();

$statement = mysqli_prepare($con, "UPDATE `tbl_eventos` SET `Premium` = '1' WHERE EmailCriador = ?");
mysqli_stmt_bind_param($statement, "s", $Email);
mysqli_stmt_execute($statement) or die;

$response["success"] = true;
echo json_encode($response);
?>