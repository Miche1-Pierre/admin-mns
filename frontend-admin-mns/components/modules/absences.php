<?php
include $_SERVER['DOCUMENT_ROOT'] . '/frontend-admin-mns/php/api/db.php';

$absences = [];

$query = $pdo->query("
    SELECT
        a.id_absence AS id,
        ua.nom_utilisateur AS utilisateur,
        ta.nom_type_absence AS type,
        a.date_debut_absence AS debut,
        a.date_fin_absence AS fin,
        a.justifie_absence AS justifie,
        a.statut_absence AS statut
    FROM absence a
    JOIN utilisateur ua ON ua.id_utilisateur = a.id_stagiaire
    JOIN type_absence ta ON ta.id_type_absence = a.id_type_absence
");

$absences = $query->fetchAll(PDO::FETCH_ASSOC);
?>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/frontend-admin-mns/css/dashboard.css">
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <title>ADMIN MNS | Absences et Retards</title>
</head>

<body>
    <header>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/navbar.php"; ?>
    </header>
    <main>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/breadcrumb.php"; ?>

        <script>
            const absences = <?php echo json_encode($absences, JSON_HEX_TAG | JSON_HEX_APOS | JSON_HEX_QUOT | JSON_HEX_AMP); ?>;
        </script>

        <div class="dashboard-zone" id="dashboard-zone">
            <div class="document-container">
                <button class="button add">Ajouter une absence/retard</button>

                <!-- Bandeau de filtrage -->
                <div class="filter-bar">
                    <input type="text" id="searchInput" placeholder="Rechercher un étudiant..." />
                    <select id="filterStatut">
                        <option value="">Tous les statuts</option>
                        <option value="Absence">Absence</option>
                        <option value="Retard">Retard</option>
                    </select>
                    <select id="filterMotif">
                        <option value="">Tous les motifs</option>
                        <option value="Maladie">Maladie</option>
                        <option value="Congé payé">Congé payé</option>
                        <option value="Autre">Autre</option>
                    </select>
                    <select id="itemsPerPage">
                        <option value="10">10 éléments</option>
                        <option value="25" selected>25 éléments</option>
                        <option value="50">50 éléments</option>
                        <option value="100">100 éléments</option>
                    </select>
                </div>

                <!-- Tableau des absences et retards -->
                <div class="carousel">
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Étudiant</th>
                                <th>Statut</th>
                                <th>Type</th>
                                <th>Debut</th>
                                <th>Fin</th>
                                <th>Justifie</th>
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

    <script src="/frontend-admin-mns/js/absences.js"></script>
</body>

</html>
