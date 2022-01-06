<?php
include $_SERVER["DOCUMENT_ROOT"]."/db/PDO.class.php";
$dbh=DBPDO::getDB();
$page=$_REQUEST["page"];
    $res = $dbh->prepare("select id,title,img,url from news_top where id<=30 order by id desc limit ?,?");
 $res->bindValue(1,$page*10);
$res->bindValue(2,10);
$res->execute();
$row=$res->fetchAll(PDO::FETCH_ASSOC);
$json= json_encode($row,JSON_UNESCAPED_UNICODE);
echo $json;
?>