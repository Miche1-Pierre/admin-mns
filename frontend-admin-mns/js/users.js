document.addEventListener("DOMContentLoaded", function () {
    setActiveMenu();
    initSidebar();
    initDropdownMenu();
    updateBreadcrumb();
    fetchProfile();
    initViewAccountModal();
    initUsers();
});

function setActiveMenu() {
    const path = window.location.pathname;
    const menuItems = document.querySelectorAll(".navbar .sidebar .nav ul li a");

    menuItems.forEach(item => {
        const linkHref = item.getAttribute("href");

        if (path.includes(linkHref)) {
            item.classList.add("active");

            const parentLi = item.closest("li");
            if (parentLi) {
                parentLi.classList.add("active");
                const subMenu = parentLi.querySelector("ul");
                if (subMenu) {
                    subMenu.style.display = "block";
                }
            }
        } else {
            item.classList.remove("active");
            const parentLi = item.closest("li");
            if (parentLi) {
                const subMenu = parentLi.querySelector("ul");
                if (subMenu) {
                    subMenu.style.display = "none";
                }
            }
        }
    });
}

function initSidebar() {
    const sidebar = document.querySelector(".sidebar");
    const menuBtn = document.querySelector(".menu-btn");

    if (!sidebar || !menuBtn) return;

    const isSidebarActive = localStorage.getItem("sidebarActive") === "true";
    sidebar.classList.toggle("active", isSidebarActive);
    updateMainLayout(isSidebarActive);

    menuBtn.addEventListener("click", function () {
        const isActive = sidebar.classList.toggle("active");
        updateMainLayout(isActive);
        localStorage.setItem("sidebarActive", isActive);
    });
}

function updateMainLayout(active) {
    const main = document.querySelector("main");
    if (main) {
        main.style.marginLeft = active ? "100px" : "270px";
        main.style.width = active ? "calc(100% - 180px)" : "calc(100% - 350px)";
    }
}

function initDropdownMenu() {
    document.querySelectorAll(".menu > ul > li").forEach(item => {
        item.addEventListener("click", function (e) {
            e.stopPropagation();

            this.parentElement.querySelectorAll("li.active").forEach(activeItem => {
                if (activeItem !== this) {
                    activeItem.classList.remove("active");
                    const subMenu = activeItem.querySelector("ul");
                    if (subMenu) subMenu.style.display = "none";
                }
            });

            this.classList.toggle("active");

            const subMenu = this.querySelector("ul");
            if (subMenu) {
                subMenu.style.display = subMenu.style.display === "block" ? "none" : "block";
            }
        });
    });
}

function updateBreadcrumb() {
    const breadcrumbList = document.getElementById('breadcrumb-list');
    const breadcrumbTitle = document.getElementById('breadcrumb-title');

    const path = window.location.pathname;

    if (path.includes("dashboard.php")) {
        breadcrumbTitle.textContent = "Dashboard";
        updateBreadcrumbLinks(["Home"]);
    } else if (path.includes("candidatures.php")) {
        breadcrumbTitle.textContent = "Dashboard";
        updateBreadcrumbLinks(["Modules", "/", "Candidatures"]);
    } else if (path.includes("absences.php")) {
        breadcrumbTitle.textContent = "Dashboard";
        updateBreadcrumbLinks(["Modules", "/", "Absences & Lateness"]);
    } else if (path.includes("users.php")) {
        breadcrumbTitle.textContent = "Dashboard";
        updateBreadcrumbLinks(["Users"]);
    } else if (path.includes("documents.php")) {
        breadcrumbTitle.textContent = "Dashboard";
        updateBreadcrumbLinks(["Documents"]);
    } else if (path.includes("stats.php")) {
        breadcrumbTitle.textContent = "Dashboard";
        updateBreadcrumbLinks(["More", "/", "Stats"]);
    } else if (path.includes("messaging.php")) {
        breadcrumbTitle.textContent = "Dashboard";
        updateBreadcrumbLinks(["Messaging"]);
    } else {
        breadcrumbTitle.textContent = "Page Not Found";
        updateBreadcrumbLinks([]);
    }
}

function updateBreadcrumbLinks(links) {
    const breadcrumbList = document.getElementById('breadcrumb-list');
    breadcrumbList.innerHTML = "";

    links.forEach((linkText, index) => {
        const li = document.createElement('li');
        const a = document.createElement('a');
        a.href = "#";
        a.textContent = linkText;

        if (index === links.length - 1) {
            a.classList.add("active");
        }

        li.appendChild(a);
        breadcrumbList.appendChild(li);
    });
}

function initUsers() {
    console.log("Chargement des utilisateurs...");

    const tableBody = document.getElementById("documentTableBody");
    const itemsPerPageSelect = document.getElementById("itemsPerPage");
    const searchInput = document.getElementById("searchInput");
    const filterRole = document.getElementById("filterRole");

    if (!tableBody || !itemsPerPageSelect || !searchInput || !filterRole) {
        console.error("Erreur: Un ou plusieurs éléments HTML manquent.");
        return;
    }

    let currentPage = 1;
    let itemsPerPage = parseInt(itemsPerPageSelect.value, 10) || 25;
    let filteredUsers = [...users];

    function filterUsers() {
        const searchQuery = searchInput.value.toLowerCase();
        const selectedRole = filterRole.value.toLowerCase();

        filteredUsers = users.filter(user => {
            const matchRole = selectedRole === "" || user.role.toLowerCase() === selectedRole;
            const matchSearch =
                user.nom.toLowerCase().includes(searchQuery) ||
                user.prenom.toLowerCase().includes(searchQuery) ||
                user.email.toLowerCase().includes(searchQuery);
            return matchRole && matchSearch;
        });

        currentPage = 1;
        displayUsers();
    }

    function displayUsers() {
        console.log("Affichage des utilisateurs...");
        const startIndex = (currentPage - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        const displayedUsers = filteredUsers.slice(startIndex, endIndex);

        tableBody.innerHTML = "";

        if (displayedUsers.length === 0) {
            tableBody.innerHTML = `<tr><td colspan='6' style="text-align:center;">Aucun utilisateur trouvé.</td></tr>`;
            return;
        }

        displayedUsers.forEach(user => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${user.id}</td>
                <td>${user.nom}</td>
                <td>${user.prenom}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>
                    <button class="button edit" data-id="${user.id}">Modifier</button>
                    <button class="button delete" data-id="${user.id}">Supprimer</button>
                </td>
            `;
            tableBody.appendChild(row);
        });

        updatePagination();
    }

    function updatePagination() {
        const pagination = document.querySelector(".pagination");
        const prevButton = pagination.querySelector(".prev-slide");
        const nextButton = pagination.querySelector(".next-slide");

        prevButton.disabled = currentPage === 1;
        nextButton.disabled = currentPage * itemsPerPage >= filteredUsers.length;
    }

    document.addEventListener("click", function (event) {
        if (event.target.matches(".prev-slide") && currentPage > 1) {
            currentPage--;
            displayUsers();
        }

        if (event.target.matches(".next-slide") && currentPage * itemsPerPage < filteredUsers.length) {
            currentPage++;
            displayUsers();
        }
    });

    itemsPerPageSelect.addEventListener("change", () => {
        itemsPerPage = parseInt(itemsPerPageSelect.value, 10) || 25;
        currentPage = 1;
        displayUsers();
    });

    searchInput.addEventListener("input", filterUsers);
    filterRole.addEventListener("change", filterUsers);

    displayUsers();
}

function fetchProfile() {
    fetch("http://admin-mns:8080/api/dashboard/profil", {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erreur lors de la récupération du profil');
            }
            return response.json();
        })
        .then(data => {
            if (data.profil && data.profil.length > 0) {
                const profile = data.profil[0];
                document.getElementById('user-firstname').textContent = profile.prenom_utilisateur;
                document.getElementById('user-role').textContent = profile.nom_role;
            }
        })
        .catch(error => console.error('Erreur:', error));
}

function initViewAccountModal() {
    const viewButton = document.querySelector(".button.view");
    const modal = document.getElementById("viewAccountModal");
    const closeModalButton = modal.querySelector(".close-btn");
    // const form = document.getElementById("changeAccountForm");

    viewButton.addEventListener("click", () => {
        modal.style.display = "block";
    });

    closeModalButton.addEventListener("click", () => {
        modal.style.display = "none";
    });

    window.addEventListener("click", (e) => {
        if (e.target === modal) {
            modal.style.display = "none";
        }
    });
}

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