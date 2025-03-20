<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";

function widgetDocuments($widgetsData)
{
    if (!isset($widgetsData["documents"]) || empty($widgetsData["documents"])) {
        return;
    }

    $title = "Documents récents";
    $link = "/frontend-admin-mns/components/documents/documents.php";
    $img = null;

    $data = $widgetsData["documents"];

    $content = "<table class='table-widget'>
                    <thead>
                        <tr>
                            <th>N° Document</th>
                            <th>N° Dossier</th>
                            <th>Date de dépôt</th>
                        </tr>
                    </thead>
                    <tbody>";

    foreach ($data as $doc) {
        $id_doc = $doc["id_document"] ?? "N/A";
        $id_dossier = $doc["id_dossier"] ?? "N/A";
        $date = $doc["date_depot_document"] ?? "N/A";

        $content .= "<tr>
                        <td>{$id_doc}</td>
                        <td>{$id_dossier}</td>
                        <td>{$date}</td>
                    </tr>";
    }

    $content .= "</tbody></table>";

    generateCard($title, $link, $content, $img);
}
