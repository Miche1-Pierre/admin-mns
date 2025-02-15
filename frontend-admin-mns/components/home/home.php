<?php
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_absences.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_messages.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_documents.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_candidatures.php";

widgetAbsences();
widgetCandidatures();
widgetDocuments();
widgetMessages();

$page = isset($_GET['page']) ? $_GET['page'] : 'home';

if ($page === "home") {
    echo '<script src="/frontend-admin-mns/js/home.js"></script>';
}
