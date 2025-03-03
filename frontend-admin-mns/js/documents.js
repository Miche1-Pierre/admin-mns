document.addEventListener("DOMContentLoaded", function () {
    setActiveMenu();
    initSidebar();
    initDropdownMenu();
    window.documents = documents;
    initDocuments();
    updateBreadcrumb();
    initAddDocumentModal();
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
        updateBreadcrumbLinks(["Modules", "/", "Applications"]);
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

function initDocuments() {
    console.log("initDocuments() appelé");

    const itemsPerPageSelect = document.getElementById("itemsPerPage");
    const searchInput = document.getElementById("searchInput");
    const filterType = document.getElementById("filterType");
    const filterAuthor = document.getElementById("filterAuthor");
    const tableBody = document.getElementById("documentTableBody");

    if (!itemsPerPageSelect || !searchInput || !filterType || !filterAuthor || !tableBody) {
        console.error("Un ou plusieurs éléments DOM manquent.");
        return;
    }

    window.documents = window.documents || [];
    let currentPage = 1;
    let filteredDocuments = [...window.documents];
    let itemsPerPage = parseInt(itemsPerPageSelect.value) || 10;

    function filterDocuments() {
        console.log("Filtrage en cours...");
        const searchQuery = searchInput.value.toLowerCase();
        const selectedType = filterType.value;
        const selectedAuthor = filterAuthor.value;

        filteredDocuments = window.documents.filter(doc => {
            return (
                (selectedType === "" || doc.type?.toLowerCase() === selectedType.toLowerCase()) &&
                (selectedAuthor === "" || doc.auteur?.toLowerCase() === selectedAuthor.toLowerCase()) &&
                (doc.nom?.toLowerCase().includes(searchQuery) || doc.auteur?.toLowerCase().includes(searchQuery))
            );
        });

        currentPage = 1;
        displayDocuments();
    }

    function displayDocuments() {
        console.log("Documents à afficher:", documents);
    
        const startIndex = (currentPage - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
    
        const displayedDocs = documents.slice(startIndex, endIndex);
        tableBody.innerHTML = "";
    
        if (displayedDocs.length === 0) {
            tableBody.innerHTML = "<tr><td colspan='6'>Aucun document trouvé.</td></tr>";
            return;
        }
    
        displayedDocs.forEach(doc => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${doc.id}</td>
                <td>${doc.nom}</td>
                <td>${doc.type}</td>
                <td>${doc.date}</td>
                <td>${doc.auteur}</td>
                <td>
                    <button class="button edit">Edit</button>
                    <button class="button delete">Delete</button>
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
        nextButton.disabled = currentPage * itemsPerPage >= filteredDocuments.length;
    }

    document.addEventListener("click", function (event) {
        if (event.target.matches(".prev-slide") && currentPage > 1) {
            currentPage--;
            displayDocuments();
        }

        if (event.target.matches(".next-slide") && currentPage * itemsPerPage < filteredDocuments.length) {
            currentPage++;
            displayDocuments();
        }
    });

    itemsPerPageSelect.addEventListener("change", () => {
        itemsPerPage = parseInt(itemsPerPageSelect.value) || 10;
        currentPage = 1;
        displayDocuments();
    });

    searchInput.addEventListener("input", filterDocuments);
    filterType.addEventListener("change", filterDocuments);
    filterAuthor.addEventListener("change", filterDocuments);

    displayDocuments();
}

function initAddDocumentModal() {
    const addButton = document.querySelector(".button.add");
    const modal = document.getElementById("addDocumentModal");
    const closeModalButton = modal.querySelector(".close-btn");
    const form = document.getElementById("addDocumentForm");

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

        const documentName = document.getElementById("document").value;
        const dateLimite = document.getElementById("date_limite").value;
        const idDossier = document.getElementById("id_dossier").value;
        const idStatut = document.getElementById("id_statut").value;
        const idTypeDocument = document.getElementById("id_type_document").value;
        const dateDepotDocument = new Date().toISOString();

        const newDocument = {
            documentName,
            dateDepotDocument,
            dateLimite,
            idDossier,
            idStatut,
            idTypeDocument,
        };

        window.documents.push(newDocument);

        displayDocuments();

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
