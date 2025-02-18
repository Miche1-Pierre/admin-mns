document.addEventListener("DOMContentLoaded", function () {
    setActiveMenu();
    initSidebar();
    initDropdownMenu();
    window.users = users;
    initUsers();
    updateBreadcrumb();
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
        updateBreadcrumbLinks(["Modules", "Candidatures"]);
    } else if (path.includes("absences.php")) {
        breadcrumbTitle.textContent = "Dashboard";
        updateBreadcrumbLinks(["Modules", "Absences & Lateness"]);
    } else if (path.includes("users.php")) {
        breadcrumbTitle.textContent = "Dashboard";
        updateBreadcrumbLinks(["Users"]);
    } else if (path.includes("documents.php")) {
        breadcrumbTitle.textContent = "Dashboard";
        updateBreadcrumbLinks(["Documents"]);
    } else if (path.includes("stats.php")) {
        breadcrumbTitle.textContent = "Dashboard";
        updateBreadcrumbLinks(["More", "Stats"]);
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
    console.log("initUsers() appelé");

    const itemsPerPageSelect = document.getElementById("itemsPerPage");
    const searchInput = document.getElementById("searchInput");
    const filterRole = document.getElementById("filterRole");
    const tableBody = document.getElementById("documentTableBody");

    if (!itemsPerPageSelect || !searchInput || !filterRole || !tableBody) {
        console.error("Un ou plusieurs éléments DOM manquent.");
        return;
    }

    let currentPage = 1;
    let filteredUsers = [...users];
    let itemsPerPage = parseInt(itemsPerPageSelect.value) || 25;

    function filterUsers() {
        console.log("Filtrage des utilisateurs...");
        const searchQuery = searchInput.value.toLowerCase();
        const selectedRole = filterRole.value;

        filteredUsers = users.filter(user => {
            return (
                (selectedRole === "" || user.role === selectedRole) &&
                (user.nom.toLowerCase().includes(searchQuery) ||
                 user.email.toLowerCase().includes(searchQuery))
            );
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
            tableBody.innerHTML = "<tr><td colspan='6'>Aucun utilisateur trouvé.</td></tr>";
            return;
        }

        displayedUsers.forEach(user => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${user.id}</td>
                <td>${user.nom}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>${user.date_inscription}</td>
                <td>
                    <button class="button edit">Modifier</button>
                    <button class="button delete">Supprimer</button>
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
        itemsPerPage = parseInt(itemsPerPageSelect.value) || 25;
        currentPage = 1;
        displayUsers();
    });

    searchInput.addEventListener("input", filterUsers);
    filterRole.addEventListener("change", filterUsers);

    displayUsers();
}
