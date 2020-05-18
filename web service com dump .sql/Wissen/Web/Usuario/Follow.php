<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');
include 'Conf.php';
$con = mysqli_connect($local, $login, $pass, $base);

$Email = $_POST["Email"];

$statement = mysqli_prepare($con, "SELECT * from tbl_usuarios where Email = ?");
mysqli_stmt_bind_param($statement, "s", $Email);
$result = mysqli_query($statement);
$emparray = array();
$response = array();
$response["success"] = true;

while($row = mysqli_fetch_assoc($result))
    {	
        $emparray[] = $row;
    }

utf8_encode_deep($emparray);
echo json_encode($emparray);

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