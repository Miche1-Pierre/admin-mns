<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";

function widgetJustificatifs($widgetsData)
{
    if (!isset($widgetsData["justificatifs"]) || empty($widgetsData["justificatifs"])) {
        return;
    }

    $title = "Receipts";
    $link = "/frontend-admin-mns/components/documents/documents.php";
    $img = null;

    $data = $widgetsData["justificatifs"];

    $content = "<table class='table-widget'>
                    <thead>
                        <tr>
                            <th>Utilisateur</th>
                            <th>Date de dépôt</th>
                            <th>Type</th>
                        </tr>
                    </thead>
                    <tbody>";

    foreach ($data as $doc) {
        $utilisateur = $doc["prenom_utilisateur"] . " " . $doc["nom_utilisateur"];
        $dateDepot = date("d/m/Y H:i", strtotime($doc["date_depot_justificatif"]));
        $type = $doc["type_document_justificatif"] ?? "Unknown";

        $content .= "<tr>
                        <td>{$utilisateur}</td>
                        <td>{$dateDepot}</td>
                        <td>{$type}</td>
                    </tr>";
    }

    $content .= "</tbody></table>";

    generateCard($title, $link, $content, $img);
}