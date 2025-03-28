<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/components/card.php";

function widgetAbsences($widgetsData)
{
    if (!isset($widgetsData["absences"]) || empty($widgetsData["absences"])) {
        return;
    }

    $title = "Absences & Retards";
    $link = "/components/modules/absences.php";
    $text = "Voir les Absences et les Retards";
    $img = null;
    $chartId = "absencesChart";
    $chartType = "bar";

    $data = $widgetsData["absences"];

    $labels = [];
    $absencesData = [];
    $retardsData = [];

    for ($m = 1; $m <= 12; $m++) {
        $labels[$m - 1] = date("M", mktime(0, 0, 0, $m, 1));
        $absencesData[$m - 1] = 0;
        $retardsData[$m - 1] = 0;
    }

    foreach ($data as $row) {
        if (!isset($row["mois"])) {
            continue;
        }
        $mois = (int)$row["mois"] - 1;
        if ($mois >= 0 && $mois < 12) {
            $absencesData[$mois] = $row["absences"];
            $retardsData[$mois] = $row["retards"];
        }
    }

    $currentMonth = (int)date("n") - 1;

    $barColorsAbsences = array_fill(0, 12, "#1F2232");
    $barColorsRetards = array_fill(0, 12, "#B70320");

    $barColorsAbsences[$currentMonth] = "#2B2D42";
    $barColorsRetards[$currentMonth] = "#D90429";

    $rotatedLabels = [];
    $rotatedAbsences = [];
    $rotatedRetards = [];
    $rotatedColorsAbsences = [];
    $rotatedColorsRetards = [];

    for ($m = $currentMonth + 1; $m < 12; $m++) {
        $rotatedLabels[] = $labels[$m];
        $rotatedAbsences[] = $absencesData[$m];
        $rotatedRetards[] = $retardsData[$m];
        $rotatedColorsAbsences[] = $barColorsAbsences[$m];
        $rotatedColorsRetards[] = $barColorsRetards[$m];
    }

    for ($m = 0; $m <= $currentMonth; $m++) {
        $rotatedLabels[] = $labels[$m];
        $rotatedAbsences[] = $absencesData[$m];
        $rotatedRetards[] = $retardsData[$m];
        $rotatedColorsAbsences[] = $barColorsAbsences[$m];
        $rotatedColorsRetards[] = $barColorsRetards[$m];
    }

    $datasets = [
        [
            "label" => "Absences",
            "data" => $rotatedAbsences,
            "backgroundColor" => $rotatedColorsAbsences
        ],
        [
            "label" => "Retards",
            "data" => $rotatedRetards,
            "backgroundColor" => $rotatedColorsRetards
        ]
    ];

    $options = [
        "responsive" => true,
        "maintainAspectRatio" => false,
    ];

    generateCard($title, $link, $text, $img, $chartId, $chartType, $rotatedLabels, $datasets, $options);
}
