<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/php/api/db.php";

function getJustificatifs($pdo)
{
    $query = "SELECT id_justificatif, date_depot_justificatif, id_statut, type_document_justificatif 
              FROM justificatif
              ORDER BY date_depot_justificatif DESC
              LIMIT 5";

    $stmt = $pdo->prepare($query);
    $stmt->execute();
    return $stmt->fetchAll(PDO::FETCH_ASSOC);
}

function widgetJustificatifs()
{
    global $pdo;
    $title = "Receipts";
    $link = "#";
    $img = null;

    $documents = getJustificatifs($pdo);

    $content = "<table class='table-widget'><thead><tr><th>Type</th><th>Deposit date</th><th>Status</th><th>Receipt Document Type</th></tr></thead><tbody>";
    foreach ($documents as $doc) {
        $content .= "<tr>
                        <td>" . htmlspecialchars($doc["id_justificatif"]) . "</td>
                        <td>" . htmlspecialchars(date("d/m/Y", strtotime($doc["date_depot_justificatif"]))) . "</td>
                        <td>" . htmlspecialchars($doc["id_statut"]) . "</td>
                        <td>" . htmlspecialchars($doc["type_document_justificatif"]) . "</td>
                     </tr>";
    }
    $content .= "</tbody></table>";

    generateCard($title, $link, $content, $img);
}