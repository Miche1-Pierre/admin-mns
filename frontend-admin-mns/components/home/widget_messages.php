<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";

function widgetMessages() {
    $title = "Messagerie";
    $text = "Consultez et envoyez vos messages.";
    $img = "assets/icons/messages.png";
    $link = "#";

    generateCard($title, $text, $img, $link);
}