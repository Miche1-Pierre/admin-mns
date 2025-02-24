<?php
$candidatures = [];
for ($i = 1; $i <= 100; $i++) {
    $candidatures[] = [
        "id" => $i,
        "nom" => "Candidature $i",
        "type" => "PDF",
        "date" => date("d/m/Y", strtotime("-$i days")),
        "auteur" => "Utilisateur $i"
    ];
}
?>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/frontend-admin-mns/css/dashboard.css">
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <title>ADMIN MNS | Candidatures</title>
</head>

<body>
    <header>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/navbar.php"; ?>
    </header>
    <main>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/breadcrumb.php"; ?>

        <script>
            const candidatures = <?php echo json_encode($candidatures, JSON_HEX_TAG | JSON_HEX_APOS | JSON_HEX_QUOT | JSON_HEX_AMP) ?>;
        </script>

        <div class="dashboard-zone" id="dashboard-zone">
            <div class="document-container">
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
                        <?php foreach (array_unique(array_column($candidatures, 'auteur')) as $author) : ?>
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

                <!-- Tableau des candidatures -->
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

    <script src="/frontend-admin-mns/js/candidatures.js"></script>
</body>

</html>
