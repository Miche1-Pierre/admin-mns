<?php
if (session_status() === PHP_SESSION_NONE) {
    session_start();
}

if (!isset($_SESSION["token"])) {
    header("Location: login.php");
    exit();
}

$token = $_SESSION["token"];
$apiUrl = "http://admin-mns:8080/api/dashboard/candidatures";

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
    $candidaturesData = [];
} else {
    curl_close($ch);
    $candidaturesData = json_decode($response, true);
}

$candidatures = $candidaturesData["candidaturesMenu"] ?? [];
?>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/dashboard.css">
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <title>ADMIN MNS | Applications</title>
</head>

<body>
    <header>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/components/navbar.php"; ?>
    </header>
    <main>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/components/breadcrumb.php"; ?>

        <script>
            const candidatures = <?php echo json_encode($candidatures, JSON_HEX_TAG | JSON_HEX_APOS | JSON_HEX_QUOT | JSON_HEX_AMP); ?>;
            console.log(candidatures)
        </script>

        <div class="dashboard-zone" id="dashboard-zone">
            <div class="document-container">
                <!-- Bandeau de filtrage -->
                <div class="filter-bar">
                    <input type="text" id="searchInput" placeholder="Rechercher une candidature..." />
                    <select id="filterType">
                        <option value="">Type de Formation</option>
                        <option value="BSD">BSD</option>
                        <option value="DEVWEB">DEVWEB</option>
                        <option value="CDA">CDA</option>
                        <option value="BSRC">BSRC</option>
                        <option value="DFS">DFS</option>
                        <option value="M2I">M2I</option>
                        <option value="TSSR">TSSR</option>
                        <option value="MSRC">MSRC</option>
                    </select>
                    <select id="itemsPerPage">
                        <option value="10">10 lignes</option>
                        <option value="25" selected>25 lignes</option>
                        <option value="50">50 lignes</option>
                        <option value="100">100 lignes</option>
                    </select>
                </div>

                <!-- Tableau des candidatures -->
                <div class="carousel">
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nom</th>
                                <th>Prénom</th>
                                <th>Email</th>
                                <th>Formation</th>
                                <th>Date</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody id="candidaturesTableBody">
                        </tbody>
                    </table>
                </div>

                <div class="pagination">
                    <button class="prev-slide">Arrière</button>
                    <button class="next-slide">Suivant</button>
                </div>
            </div>

            <!-- Modal de consultation -->
            <div id="readCandidatureModal" class="modal">
                <div class="modal-content">
                    <span class="close-btn" id="closeReadModal"><i class='bx bx-x'></i></span>
                    <h2>Consulter une Candidature</h2>
                    <div id="readCandidatureContent">
                        <p><strong>ID :</strong> <span id="readId"></span></p>
                        <p><strong>Nom :</strong> <span id="readNom"></span></p>
                        <p><strong>Prenom :</strong> <span id="readPrenom"></span></p>
                        <p><strong>Email :</strong> <span id="readEmail"></span></p>
                        <p><strong>Formation :</strong> <span id="readFormation"></span></p>
                        <p><strong>Date de Candidature :</strong> <span id="readDateInscription"></span></p>
                        <p><strong>Message :</strong> <span id="readMessage"></span></p>
                        <p><strong>CV :</strong> <span id="readCV"></span></p>
                        <p><strong>Lettre :</strong> <span id="readLettre"></span></p>
                    </div>
                    <div id="validationSection">
                        <button id="validateCandidature" class="btn validate" disabled>Valider</button>
                        <button id="refuseCandidature" class="btn refuse" disabled>Refuser</button>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <footer></footer>

    <script src="/js/candidatures.js"></script>
</body>

</html>