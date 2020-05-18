<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);

$id = $_POST["id"];

$statement = mysqli_prepare($con, "DELETE FROM `tbl_eventos` WHERE `tbl_eventos`.`id` = ?") or die("Erro no statement");

mysqli_stmt_bind_param($statement, "s", $id) or die("Erro no param");
mysqli_stmt_execute($statement) or die;

$response = array();
$response["success"] = true;  






echo json_encode($response);
?>		 	