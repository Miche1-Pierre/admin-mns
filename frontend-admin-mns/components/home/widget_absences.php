<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";

function widgetAbsences()
{
    $title = "Absences & Retards";
    $link = "#";
    $text = "Consultez les absences et retards";
    $img = null;
    $chartId = "absencesChart";
    $chartType = "bar";

    $labels = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    $absencesData = [2, 5, 3, 12, 6, 2, 4, 3, 9, 8, 3, 6];
    $retardsData = [1, 3, 2, 6, 4, 1, 3, 2, 5, 4, 2, 3];

    $datasets = [
        [
            "label" => "Absences",
            "data" => $absencesData,
            "backgroundColor" => "#2B2D42",
            "borderColor" => "#eeeeee",
            "borderWidth" => 1
        ],
        [
            "label" => "Retards",
            "data" => $retardsData,
            "backgroundColor" => "#D90429",
            "borderColor" => "#eeeeee",
            "borderWidth" => 1
        ]
    ];

    $options = [
        "responsive" => true,
        "maintainAspectRatio" => false
    ];

    generateCard($title, $link, $text, $img, $chartId, $chartType, $labels, $datasets, $options);
}