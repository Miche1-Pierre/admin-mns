const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});

// Login
document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("login-form");

    loginForm.addEventListener("submit", async function (event) {
        event.preventDefault();

        const email = loginForm.email.value;
        const password = loginForm.password.value;

        try {
            const response = await fetch("http://admin-mns:8080/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    email: email,
                    motDePasse: password
                }),
            });

            if (response.status === 403) {
                alert("Un email de vérification a été envoyé. Veuillez vérifier votre email.");
                return;
            }

            if (!response.ok) {
                throw new Error("Échec de la connexion. Vérifiez vos identifiants.");
            }

            const token = await response.text();
            localStorage.setItem("token", token);

            await fetch("/views/login.php", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                body: `token=${token}`,
            });

            window.location.href = "/views/dashboard.php";
        } catch (error) {
            alert(error.message);
        }
    });
});
