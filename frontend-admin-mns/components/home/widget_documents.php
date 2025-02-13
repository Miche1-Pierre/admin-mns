<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";

function widgetDocuments() {
    $title = "Mes Documents";
    $text = "Accédez à vos fichiers importants.";
    $img = "assets/icons/documents.png";
    $link = "#";

    generateCard($title, $text, $img, $link);
}