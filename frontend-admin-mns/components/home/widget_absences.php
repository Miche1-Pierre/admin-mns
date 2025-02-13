<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";

function widgetAbsences() {
    $title = "Absences & Retards";
    $text = "Consultez vos absences et retards.";
    $img = "assets/icons/absence.png";
    $link = "#";

    generateCard($title, $text, $img, $link);
}