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
            <form method="POST" action="/api/auth/register">
                <h1>Create Account</h1>
                <div class="social-icons">
                    <a href="#" class="icon"><i class='bx bxl-google-plus'></i></a>
                    <a href="#" class="icon"><i class='bx bxl-facebook'></i></a>
                    <a href="#" class="icon"><i class='bx bxl-github'></i></a>
                    <a href="#" class="icon"><i class='bx bxl-linkedin'></i></a>
                </div>
                <span>or use your email for registeration</span>
                <div class="grid">
                    <div class="name-field">
                        <label for="name">Name</label>
                        <input type="text" name="name" placeholder="Name" required>
                    </div>
                    <div class="firstname-field">
                        <label for="firstname">Firstname</label>
                        <input type="text" name="firstname" placeholder="Firstname" required>
                    </div>
                </div>
                <div class="email-field">
                    <label for="email">Email</label>
                    <input type="email" name="email" placeholder="Email" required>
                </div>
                <div class="grid">
                    <div class="password-field">
                        <label for="password">Password</label>
                        <input type="password" name="password" placeholder="Password" required>
                    </div>
                    <div class="confirm_password-field">
                        <label for="confirm_password">Confirm password</label>
                        <input type="password" name="confirm_password" placeholder="Confirm Password">
                    </div>
                </div>
                <button>Sign Up</button>
            </form>
        </div>
        <div class="form-container sign-in">
            <form method="POST" action="/api/auth/login">
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