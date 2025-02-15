<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";

function widgetCandidatures()
{
    $title = "Candidatures par Formation";
    $link = "#";
    $text = "Répartition des candidats par formation";
    $img = null;
    $chartId = "candidaturesChart";
    $chartType = "pie";

    $labels = ["Dev", "Reseau", "DFS", "CDA", "RAN"];
    $data = [30, 25, 15, 10, 20];
    $bgColors = ["#D90429", "#2B2D42", "#EDF2F4", "#424242", "#8D99AE"];
    $borderColors = ["#ffffff", "#ffffff", "#ffffff", "#ffffff", "#ffffff"];

    $datasets = [
        [
            "label" => "Candidatures",
            "data" => $data,
            "backgroundColor" => $bgColors,
            "borderColor" => $borderColors,
            "borderWidth" => 1
        ]
    ];

    $options = [
        "responsive" => true,
        "maintainAspectRatio" => false
    ];

    generateCard($title, $link, $text, $img, $chartId, $chartType, $labels, $datasets, $options);
}
