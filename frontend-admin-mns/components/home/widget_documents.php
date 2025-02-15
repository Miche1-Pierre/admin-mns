<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";

function widgetDocuments() {
    $title = "Mes Documents";
    $text = "Accédez à vos fichiers importants";
    $link = "#";
    $img = null;

    $documents = [
        ["nom" => "Contrat_stage.pdf", "taille" => "245 KB", "date" => "12/02/2024"],
        ["nom" => "Reglement_interieur.pdf", "taille" => "512 KB", "date" => "10/02/2024"],
        ["nom" => "Planning_cours.xlsx", "taille" => "78 KB", "date" => "08/02/2024"],
        ["nom" => "Convocation.pdf", "taille" => "135 KB", "date" => "05/02/2024"],
        ["nom" => "Attestation_presence.pdf", "taille" => "290 KB", "date" => "03/02/2024"]
    ];

    $content = "<table class='table-widget'><thead><tr><th>Nom</th><th>Taille</th><th>Date</th></tr></thead><tbody>";
    foreach ($documents as $doc) {
        $content .= "<tr>
                        <td>" . htmlspecialchars($doc["nom"]) . "</td>
                        <td>" . htmlspecialchars($doc["taille"]) . "</td>
                        <td>" . htmlspecialchars($doc["date"]) . "</td>
                     </tr>";
    }
    $content .= "</tbody></table>";

    generateCard($title, $link, $content, $img);
}