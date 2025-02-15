<?php
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_absences.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_messages.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_documents.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_candidatures.php";

widgetAbsences();
widgetCandidatures();
widgetDocuments();
widgetMessages();
