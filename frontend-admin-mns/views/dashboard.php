<?php
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_absences.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_messages.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_documents.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_candidatures.php";
?>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/frontend-admin-mns/css/dashboard.css">
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <title>ADMIN MNS | Dashboard</title>
</head>

<body>
    <header>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/navbar.php"; ?>
    </header>
    <main>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/breadcrumb.php"; ?>

        <div class="dashboard-zone" id="dashboard-zone">
            <?php
            widgetAbsences();
            widgetCandidatures();
            widgetDocuments();
            widgetMessages();
            ?>
        </div>
    </main>
    <footer>
    </footer>

    <script src="/frontend-admin-mns/js/dashboard.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</body>

</html>