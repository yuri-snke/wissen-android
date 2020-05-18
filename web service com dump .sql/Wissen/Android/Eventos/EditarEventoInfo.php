<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);

$Nome = $_POST["Nome"];
$id = $_POST["Id_User"];
$DataInicio = $_POST["DataInicio"];
$DataTermino = $_POST["DataTermino"];
$HoraInicio = $_POST["HoraInicio"];	
$HoraTermino = $_POST["HoraTermino"];
$Responsavel = $_POST["Responsavel"];
$Preco = $_POST["Preco"];
$Descricao = $_POST["Descricao"];
$CEP = $_POST["CEP"];
$Estado = $_POST["Estado"];
$Cidade = $_POST["Cidade"];
$Bairro = $_POST["Bairro"];
$Local = $_POST["Local"];
$Endereco = $_POST["Endereco"];
$TelFixo = $_POST["TelFixo"];
$TelMovel = $_POST["TelMovel"];
$EmailCriador = $_POST["EmailCriador"];
$Tipo = $_POST["Tipo"];

$Privado = $_POST["Privado"];




$statement = mysqli_prepare($con, "UPDATE `tbl_eventos` SET `Nome = ?`, `DataInicio = ?`, `DataTermino = ?`, `HoraInicio = ?`, `HoraTermino = ?`, `Responsavel = ?`, `Preco = ?`, `Descricao = ?`, `CEP = ?`,`Estado = ?`,`Cidade = ?`,`Bairro = ?`, `Local = ?`, `Endereco = ?`, `TelFixo = ?`, `TelMovel = ?`, `EmailCriador = ?`, `Tipo = ?`, `Privado = ?`  WHERE id = ?") or die("Erro no statement");

mysqli_stmt_bind_param($statement, "ssssssssssssssssssss", $Nome, $DataInicio, $DataTermino, $HoraInicio, $HoraTermino, $Responsavel, $Preco, $Descricao, $CEP,$Estado,$Cidade,$Bairro, $Local, $Endereco, $TelFixo, $TelMovel, $EmailCriador, $Tipo, $Privado,$id) or die("Erro no param");
mysqli_stmt_execute($statement) or die;

$response = array();
$response["success"] = true;  






echo json_encode($response);
?>		 	