<?php
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/stats.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_absences.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_messages.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/widget_documents.php";

/* Stats */
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/home/chart_stats.php";

$labelsAbsences = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
$labelsCandidatures = ["CDA", "Bachelor Dev", "Bachelor Réseaux", "DFS", "Num&Boost"];

$absencesData = [2, 5, 3, 12, 6, 2, 4, 3, 9, 8, 3];
$absencesColors = ['#2B2D42'];
$absencesBorder = ['#eeeeee'];

$candidaturesData = [5, 9, 7, 12, 15];
$candidaturesColors = ['#2B2D42', '#D90429', '#8D99AE', '#424242', '#EF233C'];
$candidaturesBorder = ['#eeeeee'];

$options = [
    "responsive" => true,
    "maintainAspectRatio" => false
];
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS -->
    <link rel="stylesheet" href="/frontend-admin-mns/css/dashboard.css">
    <!-- Icons -->
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <title>ADMIN MNS | Home</title>
</head>

<body>
    <header>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/navbar.php"; ?>
    </header>
    <main>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/breadcrumb.php"; ?>
        <div class="dashboard-zone">
            <?php
            widgetAbsences();
            widgetMessages();
            widgetDocuments();
            generateCard(
                "Absences",
                "#",
                null,
                null,
                "absencesChart",
                "bar",
                $labelsAbsences,
                $absencesData,
                $absencesColors,
                $absencesBorder,
                $options
            );
            generateCard(
                "Candidatures",
                "#",
                null,
                null,
                "candidaturesChart",
                "pie",
                $labelsCandidatures,
                $candidaturesData,
                $candidaturesColors,
                $candidaturesBorder,
                $options
            );
            ?>
        </div>
    </main>
    <footer>

    </footer>

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="/frontend-admin-mns/js/dashboard.js"></script>

</body>

</html>