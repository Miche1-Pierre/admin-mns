<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/php/api/db.php";

function getCandidaturesParFormation($pdo)
{
    $query = "SELECT f.nom_formation, COUNT(i.id_inscription) AS nb_candidatures
    FROM inscription i
    JOIN formation f ON i.id_formation = f.id_formation
    GROUP BY f.nom_formation
    ORDER BY nb_candidatures DESC";

    $stmt = $pdo->prepare($query);
    $stmt->execute();
    return $stmt->fetchAll(PDO::FETCH_ASSOC);
}
function widgetCandidatures()
{
    global $pdo;
    $title = "Candidatures par Formation";
    $link = "#";
    $text = "Répartition des candidats par formation";
    $img = null;
    $chartId = "candidaturesChart";
    $chartType = "pie";

    $data = getCandidaturesParFormation($pdo);

    $labels = [];
    $dataValues = [];
    $bgColors = ["#D90429", "#2B2D42", "#EDF2F4", "#424242", "#8D99AE"]; // Ajoute plus de couleurs si besoin

    foreach ($data as $index => $row) {
        $labels[] = $row["nom_formation"];
        $dataValues[] = $row["nb_candidatures"];
    }

    $datasets = [
        [
            "label" => "Candidatures",
            "data" => $dataValues,
            "backgroundColor" => array_slice($bgColors, 0, count($dataValues)),
            "borderColor" => array_fill(0, count($dataValues), "#ffffff"),
            "borderWidth" => 1
        ]
    ];

    $options = [
        "responsive" => true,
        "maintainAspectRatio" => false
    ];

    generateCard($title, $link, $text, $img, $chartId, $chartType, $labels, $datasets, $options);
}
