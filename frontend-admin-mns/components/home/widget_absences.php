<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";

function widgetAbsences($widgetsData)
{
    if (!isset($widgetsData["absences"]) || empty($widgetsData["absences"])) {
        return;
    }

    $title = "Absences & Lateness";
    $link = "#";
    $text = "View absences and late arrivals";
    $img = null;
    $chartId = "absencesChart";
    $chartType = "bar";

    $data = $widgetsData["absences"];

    $labels = [];
    $absencesData = [];
    $retardsData = [];

    foreach ($data as $row) {
        $mois = $row["mois"];
        $labels[] = date("M", mktime(0, 0, 0, $mois, 1));
        $absencesData[] = $row["absences"];
        $retardsData[] = $row["retards"];
    }

    $datasets = [
        [
            "label" => "Absences",
            "data" => $absencesData,
            "backgroundColor" => "#2B2D42",
            "borderColor" => "#eeeeee",
            "borderWidth" => 1
        ],
        [
            "label" => "Lateness",
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
