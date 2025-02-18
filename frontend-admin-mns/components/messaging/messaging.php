<?php

?>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/frontend-admin-mns/css/dashboard.css">
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <title>ADMIN MNS | Messagerie</title>
</head>

<body>
    <header>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/navbar.php"; ?>
    </header>
    <main>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/breadcrumb.php"; ?> 

        <div class="dashboard-zone" id="dashboard-zone">
            <div class="messaging-container">
                <!-- Barre de recherche des contacts -->
                <div class="contacts-sidebar">
                    <input type="text" id="searchContacts" placeholder="Rechercher un contact..." />
                    <ul id="contactsList">
                        <!-- Liste des contacts affichés dynamiquement -->
                    </ul>
                </div>

                <!-- Zone de chat -->
                <div class="chat-zone">
                    <div class="chat-header">
                        <span id="chatContactName">Sélectionnez un contact</span>
                    </div>
                    <div class="chat-messages" id="chatMessages">
                        <!-- Messages affichés dynamiquement -->
                    </div>
                    <div class="chat-input">
                        <input type="text" id="messageInput" placeholder="Écrivez un message..." />
                        <button id="sendMessage"><i class='bx bx-send'></i></button>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <footer></footer>

    <script src="/frontend-admin-mns/js/messaging.js"></script>
</body>

</html>
