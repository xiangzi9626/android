<?php
//设置允许跨域
header('Content-Type: text/html;charset=utf-8');
header('Access-Control-Allow-Origin:*'); // *代表允许任何网址请求
header('Access-Control-Allow-Methods:POST,GET,OPTIONS,DELETE'); // 允许请求的类型
header('Access-Control-Allow-Credentials: true'); // 设置是否允许发送 cookies
header('Access-Control-Allow-Headers: Content-Type,Content-Length,Accept-Encoding,X-Requested-with, Origin'); // 设置允许自定义请求头的字段
require_once $_SERVER["DOCUMENT_ROOT"]."/UploadImg.class.php";
$file=$_FILES["fileName"];
$dir=$_SERVER["DOCUMENT_ROOT"]."/upload";
new UploadImg($dir,$file);
?>