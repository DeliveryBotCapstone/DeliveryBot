<?php
	$con = mysqli_connect("*.*.*.*", "ostus", "748596", "ostus");
	mysql_query($con, 'SET NAMES utf8');

	$id = $_POST["id"];
	$pw = $_POST["pw"];
	$mg = $_POST["mg"];

	$statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?,?,?)");
	mysqli_stmt_bind_param($statement, "sss", $id, $pw, $mg);
	mysqli_stmt_execute($staement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>