<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";

function widgetMessages() {
    $title = "Messagerie";
    $text = "Consultez et envoyez vos messages.";
    $link = "#";
    $img = null;

    $messages = [
        ["expediteur" => "Admin", "contenu" => "Rappel: réunion demain à 10h.", "heure" => "10:30"],
        ["expediteur" => "Professeur X", "contenu" => "Votre devoir est à rendre vendredi.", "heure" => "09:15"],
        ["expediteur" => "RH", "contenu" => "Votre attestation est disponible.", "heure" => "08:50"],
        ["expediteur" => "Candidature", "contenu" => "Votre dossier est en cours de traitement.", "heure" => "08:30"],
        ["expediteur" => "Secrétariat", "contenu" => "Votre document a été validé.", "heure" => "08:00"]
    ];

    $content = "<table class='table-widget'><thead><tr><th>Expéditeur</th><th>Message</th><th>Heure</th></tr></thead><tbody>";
    foreach ($messages as $msg) {
        $content .= "<tr>
                        <td>" . htmlspecialchars($msg["expediteur"]) . "</td>
                        <td>" . htmlspecialchars($msg["contenu"]) . "</td>
                        <td>" . htmlspecialchars($msg["heure"]) . "</td>
                     </tr>";
    }
    $content .= "</tbody></table>";

    generateCard($title, $link, $content, $img);
}
