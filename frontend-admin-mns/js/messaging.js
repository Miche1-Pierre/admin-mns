document.addEventListener("DOMContentLoaded", function () {
    setActiveMenu();
    initSidebar();
    initDropdownMenu();
    updateBreadcrumb();
    fetchProfile();
    initViewAccountModal();
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
                    const navbar = document.getElementsByClassName("navbar");
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