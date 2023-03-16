<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "jiranismart";

$db = mysqli_connect($servername, $username, $password, $dbname);
if (!$db) {
    die("connection failed: " . mysqli_connect_error());
}