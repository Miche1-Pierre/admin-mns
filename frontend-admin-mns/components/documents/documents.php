<?php
$documents = [];
for ($i = 1; $i <= 100; $i++) {
    $documents[] = [
        "id" => $i,
        "nom" => "Document $i",
        "type" => "PDF",
        "date" => date("d/m/Y", strtotime("-$i days")),
        "auteur" => "Utilisateur $i"
    ];
}

$page = isset($_GET['page']) ? $_GET['page'] : 'documents';

?>

<script>
    window.documents = <?= json_encode($documents, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES | JSON_NUMERIC_CHECK) ?>;
</script>
<script src="/frontend-admin-mns/js/documents.js" defer></script>
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
            <?php foreach ($documents as $doc) : ?>
                <option value="<?= $doc['auteur'] ?>"><?= $doc['auteur'] ?></option>
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
                </tr>
            </thead>
            <tbody id="documentTableBody">
                <?php foreach ($documents as $doc) : ?>
                    <tr>
                        <td><?= $doc['id'] ?></td>
                        <td><?= $doc['nom'] ?></td>
                        <td><?= $doc['type'] ?></td>
                        <td><?= $doc['date'] ?></td>
                        <td><?= $doc['auteur'] ?></td>
                        <td>
                            <button class="button edit">Modifier</button>
                            <button class="button delete">Supprimer</button>
                        </td>
                    </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    </div>

    <!-- Pagination -->
    <div class="pagination">
        <button class="prev-slide">Précédent</button>
        <button class="next-slide">Suivant</button>
    </div>
</div>