<?php
include $_SERVER['DOCUMENT_ROOT'] . '/frontend-admin-mns/php/api/db.php';

$documents = [];

$query = $pdo->query("
    SELECT
        d.id_document AS id,
        d.contenu_chiffre_document AS nom,
        td.nom_type_document AS type,
        DATE_FORMAT(d.date_depot_document, '%d/%m/%Y') AS date,
        u.nom_utilisateur AS auteur
    FROM document d
    JOIN type_document td ON d.id_type_document = td.id_type_document
    JOIN dossier ds ON d.id_dossier = ds.id_dossier
    JOIN utilisateur u ON ds.id_stagiaire = u.id_utilisateur
");

$documents = $query->fetchAll(PDO::FETCH_ASSOC);
?>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/frontend-admin-mns/css/dashboard.css">
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <title>ADMIN MNS | Settings</title>
</head>

<body>
    <header>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/navbar.php"; ?>
    </header>
    <main>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/breadcrumb.php"; ?>

        <script>
            const documents = <?php echo json_encode($documents, JSON_HEX_TAG | JSON_HEX_APOS | JSON_HEX_QUOT | JSON_HEX_AMP) ?>;
        </script>

        <div class="dashboard-zone" id="dashboard-zone">
            <div class="settings-container">
                
            </div>
        </div>
    </main>
    <footer></footer>

    <script src="/frontend-admin-mns/js/settings.js"></script>
</body>

</html>