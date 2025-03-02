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
                <h1>Create an Account Integrating MNS</h1>
                <p>Vous allez être redirigé vers la page de candidature en cliquant sur le bouton ci-dessous.</p>
                <p>Veuillez vous assurer d'avoir toutes les informations nécessaires avant de continuer.</p>
                <button>Sign Up</button>
            </form>
        </div>
        <div class="form-container sign-in">
            <form id="login-form">
                <h1>Sign In</h1>
                <div class="social-icons">
                    <a href="#" class="icon"><i class='bx bxl-google-plus'></i></a>
                    <a href="#" class="icon"><i class='bx bxl-facebook'></i></a>
                    <a href="#" class="icon"><i class='bx bxl-github'></i></a>
                    <a href="#" class="icon"><i class='bx bxl-linkedin'></i></a>
                </div>
                <span>or use your email password</span>
                <div class="email-field">
                    <label for="email">Email</label>
                    <input type="email" name="email" placeholder="Email" required>
                </div>
                <div class="password-field">
                    <label for="password">Password</label>
                    <input type="password" name="password" placeholder="Password" required>
                </div>
                <a href="#">Forget Your Password ?</a>
                <button>Sign In</button>
            </form>
        </div>
        <div class="toggle-container">
            <div class="toggle">
                <div class="toggle-panel toggle-left">
                    <h1>Welcome Back !</h1>
                    <p>Enter your personal details to use all of site features</p>
                    <button class="hidden" id="login">Sign In</button>
                </div>
                <div class="toggle-panel toggle-right">
                    <h1>Hello, Friend !</h1>
                    <p>Register with your personal details to use all of app features</p>
                    <button class="hidden" id="register">Create Account</button>
                </div>
            </div>
        </div>
    </div>

    <script src="/frontend-admin-mns/js/login.js"></script>
</body>

</html>