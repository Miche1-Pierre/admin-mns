<?php
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/stats.php";

/* Stats */
$data1 = [
    "labels" => ["Jan", "Feb", "Mar", "Apr", "May", "Jun"],
    "datasets" => [[
        "label" => "Inscriptions",
        "data" => [10, 20, 25, 40, 60, 100],
        "backgroundColor" => "#D90429"
    ]]
];

$data2 = [
    "labels" => ["Absences", "Retards"],
    "datasets" => [[
        "data" => [25, 75],
        "backgroundColor" => ["#D90429", "#2B2D42"]
    ]]
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
        <div class="card-zone">
            <?php
            generateCard(
                "Make things float in air",
                "Hover over this card to unleash the power of CSS perspective",
                "https://images.unsplash.com/photo-1441974231531-c6227db76b6e?q=80&w=2560&auto=format&fit=crop",
                "https://twitter.com/mannupaaji"
            );

            generateCard(
                "Another cool effect",
                "Try different hover animations and interactivity",
                "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?q=80&w=2560&auto=format&fit=crop",
                "https://example.com"
            );
            ?>
        </div>
        <div class="stats-zone">
            <?php
            generateStats("Inscriptions Mensuelles", "Graphique des inscriptions par mois", "#", "chart1", $data1, "bar");
            generateStats("Absences & Retards", "Graphique des Absences et des Retards", "#", "chart2", $data2, "pie");
            ?>
        </div>
    </main>
    <footer>

    </footer>

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="/frontend-admin-mns/js/dashboard.js"></script>
    
</body>

</html>