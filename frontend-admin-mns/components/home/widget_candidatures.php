<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";

function widgetCandidatures($widgetsData)
{
    if (!isset($widgetsData["candidatures"]) || empty($widgetsData["candidatures"])) {
        return;
    }

    $title = "Applications by Training";
    $link = "#";
    $text = "Breakdown of applicants by training course";
    $img = null;
    $chartId = "candidaturesChart";
    $chartType = "pie";

    $data = $widgetsData["candidatures"];

    $groupedData = [];
    $formationNames = []; 

    foreach ($data as $row) {
        $idFormation = $row["id_formation"] ?? "Unknown";
        $nomFormation = $row["nom_formation"] ?? "Formation inconnue";  // Utiliser le nom de la formation

        $formationNames[$idFormation] = $nomFormation;

        if (!isset($groupedData[$idFormation])) {
            $groupedData[$idFormation] = 0;
        }

        $groupedData[$idFormation]++;
    }

    $labels = [];
    foreach ($groupedData as $idFormation => $count) {
        $labels[] = $formationNames[$idFormation];
    }

    $dataValues = array_values($groupedData);

    $bgColors = ["#D90429", "#2B2D42", "#EDF2F4", "#424242", "#ef233c", "#393E46", "#c70021"];
    $backgroundColors = array_pad($bgColors, count($dataValues), "#000000");

    $datasets = [
        [
            "label" => "Applications",
            "data" => $dataValues,
            "backgroundColor" => array_slice($backgroundColors, 0, count($dataValues)),
            "borderColor" => array_fill(0, count($dataValues), "#222222"),
            "borderWidth" => 0

        ]
    ];

    $options = [
        "responsive" => true,
        "maintainAspectRatio" => false
    ];

    generateCard($title, $link, $text, $img, $chartId, $chartType, $labels, $datasets, $options);
}
