<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
include 'Conf.php';
$connection = mysqli_connect($local, $login, $pass, $base) or die("Erro na conex " . mysqli_error($connection));

$sql1 = $_POST["sql1"];
$sql2 = $_POST["sql2"];
$sql3 = $_POST["sql3"];
$sql4 = $_POST["sql4"];
$sql5 = $_POST["sql5"];
$sql6 = $_POST["sql6"];
$sql7 = $_POST["sql7"];

$result = mysqli_query($connection, $sql1) or die("Erro no select " . mysqli_error($connection));
$emparray = array();
while($row = mysqli_fetch_assoc($result))
{
if(count($emparray) < 35){
	$emparray[] = $row;
}else{
		break;
	}
}

$result = mysqli_query($connection, $sql2) or die("Erro no select " . mysqli_error($connection));
while($row = mysqli_fetch_assoc($result))
{	
	if(count($emparray) < 40){
		$emparray[] = $row;
	}else{
		break;
	}
}

$result = mysqli_query($connection, $sql3) or die("Erro no select " . mysqli_error($connection));
while($row = mysqli_fetch_assoc($result))
{	
	if(count($emparray) < 45){
		$emparray[] = $row;
	}else{
		break;
	}
}

$result = mysqli_query($connection, $sql4) or die("Erro no select " . mysqli_error($connection));
while($row = mysqli_fetch_assoc($result))
{	
	if(count($emparray) < 50){
		$emparray[] = $row;
	}else{
		break;
	}
}


$result = mysqli_query($connection, $sql5) or die("Erro no select " . mysqli_error($connection));
while($row = mysqli_fetch_assoc($result))
{	
	if(count($emparray) < 50){
		$emparray[] = $row;
	}else{
		break;
	}
}



$result = mysqli_query($connection, $sql6) or die("Erro no select " . mysqli_error($connection));
while($row = mysqli_fetch_assoc($result))
{	
	if(count($emparray) < 50){
		$emparray[] = $row;
	}else{
		break;
	}
}



$result = mysqli_query($connection, $sql7) or die("Erro no select " . mysqli_error($connection));
while($row = mysqli_fetch_assoc($result))
{	
	if(count($emparray) < 50){
		$emparray[] = $row;
	}else{
		break;
	}
}





	//   var_dump($emparray);
utf8_encode_deep($emparray);
echo json_encode($emparray);
mysqli_close($connection);

function utf8_encode_deep(&$input) {
	if (is_string($input)) {
		$input = utf8_encode($input);
	} else if (is_array($input)) {
		foreach ($input as &$value) {
			utf8_encode_deep($value);
		}
		unset($value);
	} else if (is_object($input)) {
		$vars = array_keys(get_object_vars($input));
		foreach ($vars as $var) {
			utf8_encode_deep($input->$var);
		}
	}
}

?>	


