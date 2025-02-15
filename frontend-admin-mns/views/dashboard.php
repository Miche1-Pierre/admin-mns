<?php
include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/card.php";
?>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/frontend-admin-mns/css/dashboard.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <script src="/frontend-admin-mns/js/dashboard.js"></script>
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
            $allowed_pages = ['home', 'documents', 'messaging', 'users', 'stats', 'settings', 'modules'];
            $allowed_subpages = ['candidatures', 'absences', 'stats', 'settings'];

            $page = isset($_GET['page']) ? $_GET['page'] : 'home';
            $subPage = isset($_GET['subpage']) ? $_GET['subpage'] : null;

            if (!in_array($page, $allowed_pages)) {
                echo "<p>404 - Page introuvable</p>";
                exit;
            }

            if ($subPage) {
                if (!in_array($subPage, $allowed_subpages)) {
                    echo "<p>404 - Page introuvable</p>";
                    exit;
                }
                $file_path = $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/$page/$subPage.php";
            } else {
                $file_path = $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/$page/$page.php";
            }

            if (file_exists($file_path)) {
                include $file_path;
            } else {
                echo "<p>404 - Page introuvable</p>";
            }
            ?>
        </div>
    </main>
    <footer></footer>

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</body>

</html>