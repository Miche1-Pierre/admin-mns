<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/php/api/db.php";

function getAbsencesParMois($pdo)
{
    $query = "SELECT 
                MONTH(date_debut_absence) AS mois, 
                SUM(CASE WHEN id_type_absence = 1 THEN 1 ELSE 0 END) AS absences,
                SUM(CASE WHEN id_type_absence = 2 THEN 1 ELSE 0 END) AS retards
              FROM absence
              GROUP BY mois
              ORDER BY mois";

    $stmt = $pdo->prepare($query);
    $stmt->execute();
    return $stmt->fetchAll(PDO::FETCH_ASSOC);
}

function widgetAbsences()
{
    global $pdo;
    $title = "Absences & Lateness";
    $link = "#";
    $text = "View absences and late arrivals";
    $img = null;
    $chartId = "absencesChart";
    $chartType = "bar";

    $data = getAbsencesParMois($pdo);

    $labels = [];
    $absencesData = [];
    $retardsData = [];

    foreach ($data as $row) {
        $labels[] = date("M", mktime(0, 0, 0, $row["mois"], 1));
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
