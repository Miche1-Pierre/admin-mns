<?php
include $_SERVER['DOCUMENT_ROOT'] . '/frontend-admin-mns/php/api/db.php';

$users = [];

$query = $pdo->query("
    SELECT
        u.id_utilisateur AS id,
        u.nom_utilisateur AS nom,
        u.prenom_utilisateur AS prenom,
        u.email_utilisateur AS email,
        ru.nom_role AS role
    FROM utilisateur u
    JOIN role ru ON u.role_id = ru.id_role
");

$users = $query->fetchAll(PDO::FETCH_ASSOC);
?>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/frontend-admin-mns/css/dashboard.css">
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <title>ADMIN MNS | Utilisateurs</title>
</head>

<body>
    <header>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/navbar.php"; ?>
    </header>
    <main>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/breadcrumb.php"; ?>

        <script>
            const users = <?php echo json_encode($users, JSON_HEX_TAG | JSON_HEX_APOS | JSON_HEX_QUOT | JSON_HEX_AMP); ?>;
        </script>

        <div class="dashboard-zone" id="dashboard-zone">
            <div class="document-container">
                <button class="button add">Ajouter un utilisateur</button>

                <div class="filter-bar">
                    <input type="text" id="searchInput" placeholder="Rechercher un utilisateur..." />
                    <select id="filterRole">
                        <option value="">Tous les rôles</option>
                        <option value="Admin">Admin</option>
                        <option value="Candidat">Candidat</option>
                        <option value="Stagiaire">Stagiaire</option>
                        <option value="Formateur">Formateur</option>
                    </select>
                    <select id="itemsPerPage">
                        <option value="10">10 éléments</option>
                        <option value="25" selected>25 éléments</option>
                        <option value="50">50 éléments</option>
                        <option value="100">100 éléments</option>
                    </select>
                </div>

                <div class="carousel">
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nom</th>
                                <th>Prénom</th>
                                <th>Email</th>
                                <th>Rôle</th>
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

    <script src="/frontend-admin-mns/js/users.js"></script>
</body>
</html>
