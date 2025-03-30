<?php
session_start();

if ($_SERVER["REQUEST_METHOD"] === "POST" && isset($_POST["token"])) {
    $_SESSION["token"] = $_POST["token"];
    echo json_encode(["status" => "success"]);
    exit();
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- CSS -->
    <link rel="stylesheet" href="/frontend-admin-mns/css/login.css">
    <!-- Icons -->
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <title>ADMIN MNS | Connexion</title>
</head>

<body>
    <div class="container" id="container">
        <div class="form-container sign-up">
            <form method="" action="/frontend-admin-mns/views/candidatures.php">
                <h1>Créer un compte pour intégrer MNS</h1>
                <p>Vous allez être redirigé vers la page de candidature en cliquant sur le bouton ci-dessous.</p>
                <p>Veuillez vous assurer d'avoir toutes les informations nécessaires avant de continuer.</p>
                <button>S'inscrire</button>
            </form>
        </div>
        <div class="form-container sign-in">
            <form id="login-form">
                <h1>Se connecter</h1>
                <div class="social-icons">
                    <a href="#" class="icon"><i class='bx bxl-google-plus'></i></a>
                    <a href="#" class="icon"><i class='bx bxl-facebook'></i></a>
                    <a href="#" class="icon"><i class='bx bxl-github'></i></a>
                    <a href="#" class="icon"><i class='bx bxl-linkedin'></i></a>
                </div>
                <span>ou utilisez votre email et votre mot de passe</span>
                <div class="email-field">
                    <label for="email">Email</label>
                    <input type="email" name="email" placeholder="Email" required>
                </div>
                <div class="password-field">
                    <label for="password">Mot de Passe</label>
                    <input type="password" name="password" placeholder="Mot de Passe" required>
                </div>
                <a href="#">Vous avez oublié votre mot de passe ?</a>
                <button>Se connecter</button>
            </form>
        </div>
        <div class="toggle-container">
            <div class="toggle">
                <div class="toggle-panel toggle-left">
                    <h1>Bon retour !</h1>
                    <p>Saisissez vos données personnelles pour utiliser toutes les fonctionnalités du site</p>
                    <button class="hidden" id="login">Se Connecter</button>
                </div>
                <div class="toggle-panel toggle-right">
                    <h1>Bonjour, l'ami(e) !</h1>
                    <p>Enregistrez-vous avec vos données personnelles pour utiliser toutes les fonctionnalités de l'application.</p>
                    <button class="hidden" id="register">S'inscrire</button>
                </div>
            </div>
        </div>
    </div>

    <script src="/frontend-admin-mns/js/login.js"></script>
</body>

</html>