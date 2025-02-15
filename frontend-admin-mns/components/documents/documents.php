<?php
$documents = [];
for ($i = 1; $i <= 50; $i++) {
    $documents[] = [
        "id" => $i,
        "nom" => "Document $i",
        "type" => "PDF",
        "date" => date("d/m/Y", strtotime("-$i days")),
        "auteur" => "Utilisateur $i"
    ];
}

$docsParPage = 10;
$pageActuelle = isset($_GET['page']) && is_numeric($_GET['page']) ? (int)$_GET['page'] : 1;
$totalPages = ceil(count($documents) / $docsParPage);
$indexDepart = ($pageActuelle - 1) * $docsParPage;
$documentsAffiches = array_slice($documents, $indexDepart, $docsParPage);
?>

<div class="document-container">
    <h2>Gestion des Documents</h2>
    <button class="button add" id="add-document-btn">Ajouter un document</button>
    <div id="add-document-form" class="hidden">
        <input type="text" id="doc-name" placeholder="Nom du document">
        <select id="doc-type">
            <option value="PDF">PDF</option>
            <option value="Word">Word</option>
            <option value="Excel">Excel</option>
        </select>
        <input type="text" id="doc-auteur" placeholder="Auteur">
        <button class="button save" id="save-document-btn">Enregistrer</button>
    </div>
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
        <tbody id="document-list">
            <?php foreach ($documentsAffiches as $doc) : ?>
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
    <div class="pagination" id="pagination">
        <?php for ($i = 1; $i <= $totalPages; $i++) : ?>
            <a href="#" class="page-link <?= ($i == $pageActuelle) ? 'active' : '' ?>" data-page="<?= $i ?>"><?= $i ?></a>
        <?php endfor; ?>
    </div>
</div>
