<?php
	$con = mysqli_connect("*.*.*.*", "ostus", "748596", "ostus");
	mysql_query($con, 'SET NAMES utf8');

	$id = $_POST["id"];
	$pw = $_POST["pw"];

	$statement = mysqli_prepare($con, "SELECT * FROM USER WHERE id = ? AND pw = ?");
	mysqli_stmt_bind_param($statement, "ss", $id, $pw);
	mysqli_stmt_execute($staement);

	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $id, $pw, $mg);

	$response = array();
	$response["success"] = false;

	while(mysqli_stmt_fetch($statement)) {
		$response["success"] = true;
		$response["id"] = $id;
		$response["pw"] = $pw;
		$response["mg"] = $mg;
	}

	echo json_encode($response);
?>