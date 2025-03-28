<?php
include $_SERVER['DOCUMENT_ROOT'] . "/components/card.php";
include $_SERVER['DOCUMENT_ROOT'] . "/components/home/widget_absences.php";
include $_SERVER['DOCUMENT_ROOT'] . "/components/home/widget_messages.php";
include $_SERVER['DOCUMENT_ROOT'] . "/components/home/widget_justificatif.php";
include $_SERVER['DOCUMENT_ROOT'] . "/components/home/widget_documents.php";
include $_SERVER['DOCUMENT_ROOT'] . "/components/home/widget_candidatures.php";

session_start();

if (!isset($_SESSION["token"])) {
    header("Location: login.php");
    exit();
}

function getDashboardWidgets()
{
    if (session_status() === PHP_SESSION_NONE) {
        session_start();
    }
    if (!isset($_SESSION["token"])) {
        return [];
    }

    $token = $_SESSION["token"];
    $apiUrl = "http://admin-mns:8080/api/dashboard/widgets";

    $headers = [
        "Authorization: Bearer $token",
        "Content-Type: application/json"
    ];

    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $apiUrl);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    $response = curl_exec($ch);

    if ($response === false) {
        error_log("Erreur cURL : " . curl_error($ch));
        curl_close($ch);
        return [];
    }

    curl_close($ch);

    $data = json_decode($response, true);

    if ($data === null) {
        error_log("Erreur JSON : " . json_last_error_msg());
        return [];
    }

    return $data;
}

$widgetsData = getDashboardWidgets();
?>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/dashboard.css">
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <title>ADMIN MNS | Dashboard</title>
</head>

<body>
    <header>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/components/navbar.php"; ?>
    </header>
    <main>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/components/breadcrumb.php"; ?>

        <div class="dashboard-zone" id="dashboard-zone">
            <div class="cards-container">
                <?php
                widgetAbsences(isset($widgetsData['absences']) ? $widgetsData['absences'] : []);
                widgetCandidatures(isset($widgetsData['candidatures']) ? $widgetsData['candidatures'] : []);
                widgetJustificatifs(isset($widgetsData['justificatifs']) ? $widgetsData['justificatifs'] : []);
                widgetDocuments(isset($widgetsData['documents']) ? $widgetsData['documents'] : []);
                widgetMessages(isset($widgetsData['messages']) ? $widgetsData['messages'] : []);
                ?>
            </div>
        </div>
    </main>
    <footer>
    </footer>

    <script src="/js/dashboard.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://unpkg.com/masonry-layout@4.2.2/dist/masonry.pkgd.min.js"></script>
</body>

</html>