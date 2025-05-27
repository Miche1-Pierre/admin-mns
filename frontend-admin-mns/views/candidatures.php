<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ADMIN MNS | Candidature</title>
    <link rel="stylesheet" href="/frontend-admin-mns/css/style.css">
</head>

<body>
    <main class="candidature">
        <div class="candidature-container">
            <h2>Postuler</h2>
            <p>Remplissez ce formulaire pour soumettre votre candidature.</p>
            <form id="candidature-form" enctype="multipart/form-data" class="styled-form">
                <label for="nom">Nom</label>
                <input type="text" id="nom" name="nom" required>

                <label for="prenom">Prénom</label>
                <input type="text" id="prenom" name="prenom" required>

                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>

                <label for="formation">Formation</label>
                <select id="formation" name="formationId" required>
                    <option value="">-- Sélectionner une formation --</option>
                </select>

                <label for="cv">CV (PDF)</label>
                <input type="file" id="cv" name="cv" accept=".pdf" required>

                <label for="lettre">Lettre de Motivation (PDF)</label>
                <input type="file" id="lettre" name="lettre" accept=".pdf" required>

                <label for="message">Message / Questions ?</label>
                <textarea id="message" name="message" rows="4"></textarea>

                <button type="button" class="button primary" id="preview-btn">Prévisualiser</button>
            </form>
        </div>

        <!-- Popup de récapitulatif -->
        <div id="recapModal" class="modal">
            <div class="modal-content">
                <span class="close-btn" id="closeModal">&times;</span>
                <h2>Récapitulatif</h2>
                <p><strong>Nom :</strong> <span id="recapNom"></span></p>
                <p><strong>Prénom :</strong> <span id="recapPrenom"></span></p>
                <p><strong>Email :</strong> <span id="recapEmail"></span></p>
                <p><strong>Formation :</strong> <span id="recapFormation"></span></p>
                <p><strong>Message :</strong> <span id="recapMessage"></span></p>
                <p><strong>CV :</strong> <span id="recapCV"></span></p>
                <p><strong>Lettre de motivation :</strong> <span id="recapLettre"></span></p>
                <button class="button confirm-btn" id="confirm-submit">Confirmer</button>
                <button class="button cancel-btn" id="cancel-btn">Modifier</button>
            </div>
        </div>
    </main>
</body>

<script>
    document.getElementById("preview-btn").addEventListener("click", function() {
        document.getElementById("recapNom").innerText = document.getElementById("nom").value;
        document.getElementById("recapPrenom").innerText = document.getElementById("prenom").value;
        document.getElementById("recapEmail").innerText = document.getElementById("email").value;
        document.getElementById("recapFormation").innerText = document.getElementById("formation").selectedOptions[0].text;
        document.getElementById("recapMessage").innerText = document.getElementById("message").value || "Aucun message";

        let cvFile = document.getElementById("cv").files[0];
        let lettreFile = document.getElementById("lettre").files[0];

        document.getElementById("recapCV").innerText = cvFile ? cvFile.name : "Non fourni";
        document.getElementById("recapLettre").innerText = lettreFile ? lettreFile.name : "Non fournie";

        document.getElementById("recapModal").style.display = "flex";
    });

    document.getElementById("closeModal").addEventListener("click", function() {
        document.getElementById("recapModal").style.display = "none";
    });

    document.getElementById("cancel-btn").addEventListener("click", function() {
        document.getElementById("recapModal").style.display = "none";
    });

    document.getElementById("confirm-submit").addEventListener("click", function(event) {
        event.preventDefault();

        let formData = new FormData(document.getElementById("candidature-form"));

        fetch("http://admin-mns:8080/api/auth/candidature", {
                method: "POST",
                body: formData
            })
            .then(response => {
                console.log("Réponse HTTP reçue :", response);
                let contentType = response.headers.get("content-type");

                if (contentType && contentType.includes("application/json")) {
                    return response.json();
                } else {
                    return response.text();
                }
            })
            .then(data => {
                console.log("Réponse reçue :", data);

                if (typeof data === "string" && data.includes("succès")) {
                    alert("Candidature envoyée avec succès ! Un e-mail de confirmation vous a été envoyé.");
                    document.getElementById("recapModal").style.display = "none";
                    document.getElementById("candidature-form").reset();
                } else if (data.success) {
                    alert("Candidature envoyée avec succès ! Un e-mail de confirmation vous a été envoyé.");
                    document.getElementById("recapModal").style.display = "none";
                    document.getElementById("candidature-form").reset();
                } else {
                    alert("Erreur : " + (data.message || "Une erreur est survenue."));
                }
            })
            .catch(error => {
                console.error("Erreur Fetch :", error);
                alert("Impossible d'envoyer la candidature. Veuillez réessayer.");
            });
    });

    document.addEventListener('DOMContentLoaded', function() {
        fetch("http://admin-mns:8080/api/formations/formations")
            .then(response => response.json())
            .then(formations => {
                let formationSelect = document.getElementById('formation');
                formations.forEach(formation => {
                    let option = document.createElement('option');
                    option.value = formation.idFormation;
                    option.text = formation.nomFormation;
                    formationSelect.appendChild(option);
                });
            })
            .catch(err => console.error("Erreur lors du chargement des formations :", err));
    });
</script>

</html>