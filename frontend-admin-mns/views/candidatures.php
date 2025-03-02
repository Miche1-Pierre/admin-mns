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
            <form action="#" method="post" enctype="multipart/form-data" class="styled-form">
                <label for="nom">Nom</label>
                <input type="text" id="nom" name="nom" required>

                <label for="prenom">Prénom</label>
                <input type="text" id="prenom" name="prenom" required>

                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>

                <label for="cv">CV (PDF)</label>
                <input type="file" id="cv" name="cv" accept=".pdf" required>

                <label for="lettre">Lettre de Motivation (PDF)</label>
                <input type="file" id="lettre" name="lettre" accept=".pdf" required>

                <label for="message">Message / Questions ?</label>
                <textarea id="message" name="message" rows="4"></textarea>

                <button type="submit" class="button primary">Envoyer la candidature</button>
            </form>
        </div>
    </main>
</body>
</html>
