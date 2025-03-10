<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";

function widgetMessages($widgetsData)
{
    if (!isset($widgetsData["messages"]) || empty($widgetsData["messages"])) {
        return;
    }

    $title = "Messaging";
    $link = "#";
    $img = null;

    $data = $widgetsData["messages"];

    // Construction d'un tableau affichant Expéditeur, Date et un extrait du Message
    $content = "<table class='table-widget'>
                    <thead>
                        <tr>
                            <th>Expéditeur</th>
                            <th>Date</th>
                            <th>Message</th>
                        </tr>
                    </thead>
                    <tbody>";

    foreach ($data as $notif) {
        $senderName = "";
        if (isset($notif["nom_utilisateur"])) {
            $senderName .= $notif["nom_utilisateur"];
        }
        if (isset($notif["prenom_utilisateur"])) {
            $senderName .= " " . $notif["prenom_utilisateur"];
        }
        if (empty(trim($senderName))) {
            $senderName = "Inconnu";
        }

        $date = isset($notif["date_notification"]) 
                    ? date("d M Y H:i", strtotime($notif["date_notification"])) 
                    : "N/A";

        $snippet = isset($notif["contenu_notification"]) 
                    ? substr($notif["contenu_notification"], 0, 50) . "..." 
                    : "";

        $content .= "<tr>
                        <td>{$senderName}</td>
                        <td>{$date}</td>
                        <td>{$snippet}</td>
                     </tr>";
    }

    $content .= "</tbody></table>";

    generateCard($title, $link, $content, $img);
}
