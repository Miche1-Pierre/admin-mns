document.addEventListener("DOMContentLoaded", function () {
    setActiveMenu();
    initSidebar();
    initDropdownMenu();
    window.absences = absences;
    initAbsences();
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

function initAbsences() {
    console.log("initAbsences() appelé");

    const itemsPerPageSelect = document.getElementById("itemsPerPage");
    const searchInput = document.getElementById("searchInput");
    const filterStatut = document.getElementById("filterStatut");
    const filterMotif = document.getElementById("filterMotif");
    const filterAuthor = document.getElementById("filterAuthor");
    const tableBody = document.getElementById("documentTableBody");

    if (!itemsPerPageSelect || !searchInput || !filterStatut || !filterMotif || !filterAuthor || !tableBody) {
        console.error("Un ou plusieurs éléments DOM manquent.");
        return;
    }

    window.absences = window.absences || [];
    let currentPage = 1;
    let filteredAbsences = [...window.absences];
    let itemsPerPage = parseInt(itemsPerPageSelect.value) || 10;

    function filterAbsences() {
        console.log("Filtrage des absences...");
        const searchQuery = searchInput.value.toLowerCase();
        const selectedStatut = filterStatut.value;
        const selectedMotif = filterMotif.value;
        const selectedAuthor = filterAuthor.value;

        filteredAbsences = window.absences.filter(abs => {
            return (
                (selectedStatut === "" || abs.statut?.toLowerCase() === selectedStatut.toLowerCase()) &&
                (selectedMotif === "" || abs.motif?.toLowerCase() === selectedMotif.toLowerCase()) &&
                (selectedAuthor === "" || abs.auteur?.toLowerCase() === selectedAuthor.toLowerCase()) &&
                (abs.nom?.toLowerCase().includes(searchQuery))
            );
        });

        currentPage = 1;
        displayAbsences();
    }

    function displayAbsences() {
        console.log("Affichage des absences...");
        const startIndex = (currentPage - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;

        const displayedAbs = filteredAbsences.slice(startIndex, endIndex);
        tableBody.innerHTML = "";

        if (displayedAbs.length === 0) {
            tableBody.innerHTML = "<tr><td colspan='7'>Aucune absence trouvée.</td></tr>";
            return;
        }

        displayedAbs.forEach(abs => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${abs.id}</td>
                <td>${abs.nom}</td>
                <td>${abs.statut}</td>
                <td>${abs.date}</td>
                <td>${abs.motif}</td>
                <td>${abs.auteur}</td>
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
        nextButton.disabled = currentPage * itemsPerPage >= filteredAbsences.length;
    }

    document.addEventListener("click", function (event) {
        if (event.target.matches(".prev-slide") && currentPage > 1) {
            currentPage--;
            displayAbsences();
        }

        if (event.target.matches(".next-slide") && currentPage * itemsPerPage < filteredAbsences.length) {
            currentPage++;
            displayAbsences();
        }
    });

    itemsPerPageSelect.addEventListener("change", () => {
        itemsPerPage = parseInt(itemsPerPageSelect.value) || 10;
        currentPage = 1;
        displayAbsences();
    });

    searchInput.addEventListener("input", filterAbsences);
    filterStatut.addEventListener("change", filterAbsences);
    filterMotif.addEventListener("change", filterAbsences);
    filterAuthor.addEventListener("change", filterAbsences);

    displayAbsences();
}