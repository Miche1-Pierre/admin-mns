let filteredAbsences = [];
let currentPage = 1;
let itemsPerPage = 10;

document.addEventListener("DOMContentLoaded", function () {
    setActiveMenu();
    initSidebar();
    initDropdownMenu();
    initAbsences();
    fetchProfile();
    updateBreadcrumb();
    initAddAbsenceModal();
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
    const itemsPerPageSelect = document.getElementById("itemsPerPage");
    const searchInput = document.getElementById("searchInput");
    const filterStatut = document.getElementById("filterStatut");
    const filterMotif = document.getElementById("filterMotif");
    const tableBody = document.getElementById("absencesTableBody");

    if (!itemsPerPageSelect || !searchInput || !filterStatut || !filterMotif || !tableBody) {
        console.error("Un ou plusieurs éléments DOM manquent.");
        return;
    }

    itemsPerPage = parseInt(itemsPerPageSelect.value) || 10;
    filteredAbsences = [...absences];
    currentPage = 1;

    itemsPerPageSelect.addEventListener("change", () => {
        itemsPerPage = parseInt(itemsPerPageSelect.value) || 10;
        currentPage = 1;
        displayAbsences();
    });

    searchInput.addEventListener("input", filterAbsences);
    filterStatut.addEventListener("change", filterAbsences);
    filterMotif.addEventListener("change", filterAbsences);

    displayAbsences();
}

function filterAbsences() {
    const searchQuery = document.getElementById("searchInput").value.toLowerCase();
    const selectedStatut = document.getElementById("filterStatut").value;
    const selectedMotif = document.getElementById("filterMotif").value;

    filteredAbsences = absences.filter(absence => {
        const matchSearch = absence.utilisateur.toLowerCase().includes(searchQuery);
        const matchStatut = selectedStatut === "" || absence.statut === selectedStatut;
        const matchMotif = selectedMotif === "" || absence.type === selectedMotif;

        return matchSearch && matchStatut && matchMotif;
    });

    currentPage = 1;
    displayAbsences();
}

function displayAbsences() {
    const tableBody = document.getElementById("absencesTableBody");
    if (!tableBody) return;

    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const displayAbsences = filteredAbsences.slice(startIndex, endIndex);

    tableBody.innerHTML = "";

    if (displayAbsences.length === 0) {
        tableBody.innerHTML = "<tr><td colspan='6'>Aucune absence trouvée.</td></tr>";
        return;
    }

    displayAbsences.forEach(absence => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${absence.id}</td>
            <td>${absence.utilisateur}</td>
            <td>${absence.statut}</td>
            <td>${absence.type}</td>
            <td>${new Date(absence.debut).toLocaleString()}</td>
            <td>${new Date(absence.fin).toLocaleString()}</td>
            <td>${absence.justifie ? 'Oui' : 'Non'}</td>
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
    const prevButton = document.querySelector(".prev-slide");
    const nextButton = document.querySelector(".next-slide");

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

function initAddAbsenceModal() {
    const addButton = document.querySelector(".button.add");
    const modal = document.getElementById("addAbsenceModal");
    const closeModalButton = modal.querySelector(".close-btn");
    const form = document.getElementById("addAbsenceForm");

    addButton.addEventListener("click", () => {
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

    form.addEventListener("submit", (e) => {
        e.preventDefault();

        const utilisateur = document.getElementById("utilisateur").value;
        const statut = document.getElementById("statut").value;
        const type = document.getElementById("type").value;
        const debut = document.getElementById("debut").value;
        const fin = document.getElementById("fin").value;
        const justifie = document.getElementById("justifie").files[0];

        const newAbsence = {
            id: window.absences.length + 1,
            utilisateur,
            statut,
            type,
            debut,
            fin,
            justifie: justifie ? justifie.name : null,
        };

        window.absences.push(newAbsence);
        displayAbsences();
        modal.style.display = "none";
        form.reset();
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
