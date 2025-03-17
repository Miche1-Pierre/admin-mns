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
                <button class="button add">Add Absences/Lateness</button>

                <!-- Bandeau de filtrage -->
                <div class="filter-bar">
                    <input type="text" id="searchInput" placeholder="Rechercher un étudiant..." />
                    <select id="filterStatut">
                        <option value="">All statuts</option>
                        <option value="Absence">Absence</option>
                        <option value="Retard">Late</option>
                    </select>
                    <select id="filterMotif">
                        <option value="">All motifs</option>
                        <option value="Maladie">Sickness</option>
                        <option value="Congé payé">Paid vacation</option>
                        <option value="Autre">Other</option>
                    </select>
                    <select id="itemsPerPage">
                        <option value="10">10 lines</option>
                        <option value="25" selected>25 lines</option>
                        <option value="50">50 lines</option>
                        <option value="100">100 lines</option>
                    </select>
                </div>

                <!-- Tableau des absences et retards -->
                <div class="carousel">
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Student</th>
                                <th>Status</th>
                                <th>Type</th>
                                <th>Start</th>
                                <th>End</th>
                                <th>Justify</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody id="absencesTableBody">
                        </tbody>
                    </table>
                </div>

                <div class="pagination">
                    <button class="prev-slide">Previous</button>
                    <button class="next-slide">Next</button>
                </div>
            </div>

            <!-- Popup pour ajouter une absence/retard -->
            <div id="addAbsenceModal" class="modal">
                <div class="modal-content">
                    <span class="close-btn"><i class='bx bx-x'></i></span>
                    <h2>Add Absence/Lateness</h2>
                    <form id="addAbsenceForm">
                        <label for="statut">Status</label>
                        <select id="statut" name="statut" required>
                            <option value="Absence">Absence</option>
                            <option value="Retard">Late</option>
                        </select>

                        <label for="type">Absence Type</label>
                        <select id="type" name="type" required>
                            <option value="1">Medical appointment</option>
                            <option value="2">Paid vacation</option>
                            <option value="3">Other</option>
                        </select>

                        <label for="debut">Start Date</label>
                        <input type="datetime-local" id="debut" name="debut" required>

                        <label for="fin">End Date</label>
                        <input type="datetime-local" id="fin" name="fin" required>

                        <label for="justifie">Receipt (optional)</label>
                        <input type="file" id="justifie" name="justifie">

                        <button type="submit" class="button">Create</button>
                    </form>
                </div>
            </div>
        </div>
    </main>
    <footer></footer>

    <script src="/frontend-admin-mns/js/absences.js"></script>
</body>

</html>