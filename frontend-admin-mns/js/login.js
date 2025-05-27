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
                showToast("Un email de vérification a été envoyé. Veuillez vérifier votre email.", "info");
                return;
            }

            if (!response.ok) {
                throw new Error("Échec de la connexion. Vérifiez vos identifiants.");
            }

            const token = await response.text();
            localStorage.setItem("token", token);

            await fetch("/frontend-admin-mns/views/login.php", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                body: `token=${token}`,
            });

            window.location.href = "/frontend-admin-mns/views/dashboard.php";
        } catch {
            showToast("Échec de la connexion. Vérifiez vos identifiants.", "error");
        }
    });
});

function showToast(message = null, type = "success", duration = 5000, persist = false) {
    if (persist && message) {
        // Stocke le toast dans localStorage pour affichage après reload
        localStorage.setItem("toastMessage", JSON.stringify({ message, type, duration }));
        return;
    }

    if (!message) {
        const stored = localStorage.getItem("toastMessage");
        if (stored) {
            try {
                const parsed = JSON.parse(stored);
                message = parsed.message;
                type = parsed.type || type;
                duration = parsed.duration || duration;
                localStorage.removeItem("toastMessage");
            } catch (e) {
                console.error("Toast mal formaté :", e);
                return;
            }
        } else {
            return;
        }
    }

    let container = document.getElementById('toast-container');

    if (!container) {
        container = document.createElement('div');
        container.id = 'toast-container';
        document.body.appendChild(container);
    }

    const toast = document.createElement("div");
    toast.className = `toast ${type}`;
    toast.innerHTML = `
        <span>${message}</span>
        <button class="close-btn" onclick="this.parentElement.remove()">
            <i class='bx bx-x'></i>
        </button>
    `;

    container.appendChild(toast);

    setTimeout(() => {
        toast.remove();
    }, duration);
}