<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);


$id_User = $_POST["id_User"];
$id_Evento = $_POST["id_Evento"];
$status = $_POST["status"];
$comparecerao = $_POST["comparecerao"];
$interessados = $_POST["interessados"];

$response = array();
$existe = false;


$result = mysqli_query($con, "SELECT * FROM `tbl_eventosseg` WHERE Id_usuario = ".$id_User." and Id_Evento = ".$id_Evento) or die("Erro execute 1");
while($row = mysqli_fetch_assoc($result))
{


	$existe = true;


	if($row["Status"] == 1 and $status == 2){
		mysqli_close($con);
		$con = mysqli_connect("localhost", "root", "", "wissen");
		$statement = mysqli_prepare($con, "UPDATE `tbl_eventosseg` SET `Status` = '2' WHERE `tbl_eventosseg`.`id_usuario` = ? and `tbl_eventosseg`.`id_Evento` = ?");
		mysqli_stmt_bind_param($statement, "ss", $id_User,$id_Evento);
		mysqli_execute($statement) or die("Erro execute 2");


		mysqli_close($con);
		$con = mysqli_connect("localhost", "root", "", "wissen");
		$statement = mysqli_prepare($con, "UPDATE `tbl_eventos` SET `Interessados` = ?, `Comparecerao` = ? WHERE `tbl_eventos`.`id` = ?");
		$interessados = $interessados-1;
		$comparecerao = $comparecerao+1;
		mysqli_stmt_bind_param($statement, "sss", $interessados, $comparecerao,$id_Evento);
		mysqli_execute($statement) or die("Erro execute 3");


	}else if($row["Status"] == 2 and $status == 1){
		mysqli_close($con);
		$con = mysqli_connect("localhost", "root", "", "wissen");
		$statement = mysqli_prepare($con, "UPDATE `tbl_eventosseg` SET `Status` = '1' WHERE `tbl_eventosseg`.`id_usuario` = ? and `tbl_eventosseg`.`id_Evento` = ?");
		mysqli_stmt_bind_param($statement, "ss", $id_User,$id_Evento);
		mysqli_execute($statement)  or die("Erro execute 4");


		mysqli_close($con);
		$con = mysqli_connect("localhost", "root", "", "wissen");
		$statement = mysqli_prepare($con, "UPDATE `tbl_eventos` SET `Interessados` = ?, `Comparecerao` = ? WHERE `tbl_eventos`.`id` = ?");
		$interessados = $interessados+1;
		$comparecerao = $comparecerao-1;
		mysqli_stmt_bind_param($statement, "sss", $interessados, $comparecerao, $id_Evento);
		mysqli_execute($statement) or die("Erro execute 5");


	}
}

if(!$existe){
	mysqli_close($con);
	$con = mysqli_connect("localhost", "root", "", "wissen");
	$statement = mysqli_prepare($con, "INSERT INTO `tbl_eventosseg` (`Id_usuario`, `Id_Evento`, `Status`) VALUES (?, ?, ?)") or die("Erro no statement");
	mysqli_stmt_bind_param($statement, "sss", $id_User, $id_Evento, $status);
	mysqli_stmt_execute($statement) or die ("Erro execute 6");

	if($status == 1){

		$statement = mysqli_prepare($con, "UPDATE `tbl_eventos` SET `Interessados` = ? WHERE `tbl_eventos`.`id` = ?");
		$interessados = $interessados+1;			
		mysqli_stmt_bind_param($statement, "ss", $interessados, $id_Evento);
		mysqli_execute($statement) or die("Erro execute 7");

	}else{

		$statement = mysqli_prepare($con, "UPDATE `tbl_eventos` SET `Comparecerao` = ? WHERE `tbl_eventos`.`id` = ?");
		$comparecerao = $comparecerao+1;
		mysqli_stmt_bind_param($statement, "ss", $comparecerao, $id_Evento);
		mysqli_execute($statement) or die("Erro execute 8");

	}

}
$response["success"] = true;
echo json_encode($response);
?>