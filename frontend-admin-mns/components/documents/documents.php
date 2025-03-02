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
                <button class="button add">Add document</button>

                <!-- Bandeau de filtrage -->
                <div class="filter-bar">
                    <input type="text" id="searchInput" placeholder="Search document..." />
                    <select id="filterType">
                        <option value="">All types</option>
                        <option value="PDF">PDF</option>
                        <option value="DOCX">DOCX</option>
                        <option value="XLSX">XLSX</option>
                    </select>
                    <select id="filterAuthor">
                        <option value="">All authors</option>
                        <?php foreach (array_unique(array_column($documents, 'auteur')) as $author) : ?>
                            <option value="<?= htmlspecialchars($author) ?>"><?= htmlspecialchars($author) ?></option>
                        <?php endforeach; ?>
                    </select>
                    <select id="itemsPerPage">
                        <option value="10">10 lines</option>
                        <option value="25" selected>25 lines</option>
                        <option value="50">50 lines</option>
                        <option value="100">100 lines</option>
                    </select>
                </div>

                <!-- Tableau des documents -->
                <div class="carousel">
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Date</th>
                                <th>Author</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody id="documentTableBody">
                        </tbody>
                    </table>
                </div>

                <div class="pagination">
                    <button class="prev-slide">Previous</button>
                    <button class="next-slide">Next</button>
                </div>
            </div>

            <!-- Popup pour ajouter un document -->
            <div id="addDocumentModal" class="modal">
                <div class="modal-content">
                    <span class="close-btn"><i class='bx bx-x'></i></span>
                    <h2>Add document</h2>
                    <form id="addDocumentForm">
                        <label for="document">Name</label>
                        <input type="text" id="document" name="document" required>

                        <label for="date_limite">Document deadline</label>
                        <input type="datetime-local" id="date_limite" name="date_limite" required>

                        <label for="id_dossier">Dossier ID</label>
                        <input type="number" id="id_dossier" name="id_dossier" required>

                        <label for="id_statut">Status ID</label>
                        <input type="number" id="id_statut" name="id_statut" required>

                        <label for="id_type_document">Document type ID</label>
                        <input type="number" id="id_type_document" name="id_type_document" required>

                        <button type="submit" class="button">Create</button>
                    </form>
                </div>
            </div>
        </div>
    </main>
    <footer></footer>

    <script src="/frontend-admin-mns/js/documents.js"></script>
</body>

</html>