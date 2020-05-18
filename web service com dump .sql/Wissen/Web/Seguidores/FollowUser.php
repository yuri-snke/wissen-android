<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);


$UserPrim = $_POST["UserPrim"];
$UserSecu = $_POST["UserSecu"];

$existe = false;

$result = mysqli_query($con, "SELECT * FROM `tbl_seguidores` WHERE UserPrimario = ".$UserPrim." and UserSecundario = ".$UserSecu) or die("Erro execute 1");
while($row = mysqli_fetch_assoc($result))
{
		$existe = true;
		mysqli_close($con);
		$con = mysqli_connect($local, $login, $pass, $base);
		mysqli_query($con, "DELETE FROM `tbl_seguidores` WHERE UserPrimario = ".$UserPrim." and UserSecundario = ".$UserSecu) or die ("Erro No delete");

}

if(!$existe){
	mysqli_close($con);
	$con = mysqli_connect($local, $login, $pass, $base);
	$statement = mysqli_prepare($con, " INSERT INTO tbl_seguidores (`UserPrimario`, `UserSecundario`, `Status`) VALUES (?, ?, '1')") or die("Erro no statement");
	mysqli_stmt_bind_param($statement, "ss", $UserPrim, $UserSecu);
	mysqli_stmt_execute($statement) or die ("Erro execute Up");

}

$response = array();
$response["success"] = true;
echo json_encode($response);
?>