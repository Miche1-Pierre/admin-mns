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
    <title>ADMIN MNS | Documents</title>
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
            <div class="document-container">
                <button class="button add">Ajouter un document</button>

                <!-- Bandeau de filtrage -->
                <div class="filter-bar">
                    <input type="text" id="searchInput" placeholder="Rechercher un document..." />
                    <select id="filterType">
                        <option value="">Tous les types</option>
                        <option value="PDF">PDF</option>
                        <option value="DOCX">DOCX</option>
                        <option value="XLSX">XLSX</option>
                    </select>
                    <select id="filterAuthor">
                        <option value="">Tous les auteurs</option>
                        <?php foreach (array_unique(array_column($documents, 'auteur')) as $author) : ?>
                            <option value="<?= htmlspecialchars($author) ?>"><?= htmlspecialchars($author) ?></option>
                        <?php endforeach; ?>
                    </select>
                    <select id="itemsPerPage">
                        <option value="10">10 éléments</option>
                        <option value="25" selected>25 éléments</option>
                        <option value="50">50 éléments</option>
                        <option value="100">100 éléments</option>
                    </select>
                </div>

                <!-- Tableau des documents -->
                <div class="carousel">
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nom</th>
                                <th>Type</th>
                                <th>Date</th>
                                <th>Auteur</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody id="documentTableBody">
                        </tbody>
                    </table>
                </div>

                <div class="pagination">
                    <button class="prev-slide">Précédent</button>
                    <button class="next-slide">Suivant</button>
                </div>
            </div>
        </div>
    </main>
    <footer></footer>

    <script src="/frontend-admin-mns/js/documents.js"></script>
</body>

</html>
