<?php
if (session_status() === PHP_SESSION_NONE) {
    session_start();
}

if (!isset($_SESSION["token"])) {
    header("Location: login.php");
    exit();
}

$token = $_SESSION["token"];
$apiUrl = "http://admin-mns:8080/api/dashboard/absences";

$headers = [
    "Authorization: Bearer $token",
    "Content-Type: application/json"
];

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $apiUrl);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
$response = curl_exec($ch);

if ($response === false) {
    error_log("Erreur cURL : " . curl_error($ch));
    curl_close($ch);
    $absencesData = [];
} else {
    curl_close($ch);
    $absencesData = json_decode($response, true);
}

$absences = $absencesData["absencesMenu"] ?? [];
?>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/frontend-admin-mns/css/dashboard.css">
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <title>ADMIN MNS | Absences & Lateness</title>
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
                <button class="button add">Ajouter une Absence/Retards</button>

                <!-- Bandeau de filtrage -->
                <div class="filter-bar">
                    <input type="text" id="searchInput" placeholder="Rechercher un étudiant..." />
                    <select id="filterStatut">
                        <option value="">Tout les status</option>
                        <option value="Absence">Absences</option>
                        <option value="Retard">Retards</option>
                    </select>
                    <select id="filterMotif">
                        <option value="">Tout les motifs</option>
                        <option value="Maladie">Maladies</option>
                        <option value="Congé payé">Congès</option>
                        <option value="Autre">Autres</option>
                    </select>
                    <select id="itemsPerPage">
                        <option value="10">10 lignes</option>
                        <option value="25" selected>25 lignes</option>
                        <option value="50">50 lignes</option>
                        <option value="100">100 lignes</option>
                    </select>
                </div>

                <!-- Tableau des absences et retards -->
                <div class="carousel">
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Stagiaire</th>
                                <th>Status</th>
                                <th>Type</th>
                                <th>Début</th>
                                <th>Fin</th>
                                <th>Justifié</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody id="absencesTableBody">
                        </tbody>
                    </table>
                </div>

                <div class="pagination">
                    <button class="prev-slide">Arrière</button>
                    <button class="next-slide">Suivant</button>
                </div>
            </div>

            <!-- Popup pour ajouter une absence/retard -->
            <div id="addAbsenceModal" class="modal">
                <div class="modal-content">
                    <span class="close-btn"><i class='bx bx-x'></i></span>
                    <h2>Ajouter une Absence/Retards</h2>
                    <form id="addAbsenceForm">
                        <label for="statut">Status</label>
                        <select id="statut" name="statut" required>
                            <option value="Absence">Absence</option>
                            <option value="Retard">Retard</option>
                        </select>

                        <label for="type">Type d'Absence/Retard</label>
                        <select id="type" name="type" required>
                            <option value="1">RDV Medical</option>
                            <option value="2">Congès</option>
                            <option value="3">Autre</option>
                        </select>

                        <label for="debut">Début</label>
                        <input type="datetime-local" id="debut" name="debut" required>

                        <label for="fin">Fin</label>
                        <input type="datetime-local" id="fin" name="fin" required>

                        <label for="justifie">Justificatif (optionel)</label>
                        <input type="file" id="justifie" name="justifie">

                        <button type="submit" class="button">Créer</button>
                    </form>
                </div>
            </div>
        </div>
    </main>
    <footer></footer>

    <script src="/frontend-admin-mns/js/absences.js"></script>
</body>

</html>