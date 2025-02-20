<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/php/api/db.php";

function getDocuments($pdo)
{
    $query = "SELECT id_type_document, date_depot_document, id_statut 
              FROM document
              ORDER BY date_depot_document DESC
              LIMIT 5";

    $stmt = $pdo->prepare($query);
    $stmt->execute();
    return $stmt->fetchAll(PDO::FETCH_ASSOC);
}

function widgetDocuments()
{
    global $pdo;
    $title = "Mes Documents";
    $link = "#";
    $img = null;

    $documents = getDocuments($pdo);

    $content = "<table class='table-widget'><thead><tr><th>Type</th><th>Date Dépôt</th><th>Statut</th></tr></thead><tbody>";
    foreach ($documents as $doc) {
        $content .= "<tr>
                        <td>" . htmlspecialchars($doc["id_type_document"]) . "</td>
                        <td>" . htmlspecialchars(date("d/m/Y", strtotime($doc["date_depot_document"]))) . "</td>
                        <td>" . htmlspecialchars($doc["id_statut"]) . "</td>
                     </tr>";
    }
    $content .= "</tbody></table>";

    generateCard($title, $link, $content, $img);
}