<?php
header('Content-Type: application/json');
header('Character-Encoding: utf-8');

include 'Conf.php';
$connection = mysqli_connect($local, $login, $pass, $base) or die("Erro na conex " . mysqli_error($connection));
$sql = $_POST["sql"];

$emparray = array();
$result = mysqli_query($connection, $sql) or die("Erro no select " . mysqli_error($connection));
while($row = mysqli_fetch_assoc($result))
{
	$emparray[] = $row;
}
mysqli_close($connection);
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

