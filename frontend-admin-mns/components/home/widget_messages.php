<?php
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";
include_once $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/php/api/db.php";

function getNotifications($pdo)
{
    $query = "SELECT date_notification, id_type_notification, contenu_notification 
              FROM notification
              ORDER BY date_notification DESC
              LIMIT 5";

    $stmt = $pdo->prepare($query);
    $stmt->execute();
    return $stmt->fetchAll(PDO::FETCH_ASSOC);
}

function widgetMessages()
{
    global $pdo;
    $title = "Messagerie";
    $text = "Consultez et envoyez vos messages.";
    $link = "#";
    $img = null;

    // Récupération des 5 dernières notifications
    $notifications = getNotifications($pdo);

    $content = "<table class='table-widget'><thead><tr><th>Type</th><th>Message</th><th>Date</th></tr></thead><tbody>";
    foreach ($notifications as $notif) {
        $content .= "<tr>
                        <td>" . htmlspecialchars($notif["id_type_notification"]) . "</td>
                        <td>" . htmlspecialchars($notif["contenu_notification"]) . "</td>
                        <td>" . htmlspecialchars(date("d/m/Y H:i", strtotime($notif["date_notification"]))) . "</td>
                     </tr>";
    }
    $content .= "</tbody></table>";

    generateCard($title, $link, $content, $img);
}

