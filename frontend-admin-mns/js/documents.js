let filteredDocuments = [];
let currentPage = 1;
let itemsPerPage = 10;

document.addEventListener("DOMContentLoaded", function () {
    setActiveMenu();
    initSidebar();
    initDropdownMenu();
    initDocuments();
    fetchProfile();
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

function initDocuments() {
    const itemsPerPageSelect = document.getElementById("itemsPerPage");
    const searchInput = document.getElementById("searchInput");

    if (!itemsPerPageSelect || !searchInput) {
        console.error("Un ou plusieurs éléments DOM manquent.");
        return;
    }

    itemsPerPage = parseInt(itemsPerPageSelect.value) || 10;
    filteredDocuments = [...documents];
    currentPage = 1;

    itemsPerPageSelect.addEventListener("change", () => {
        itemsPerPage = parseInt(itemsPerPageSelect.value) || 10;
        currentPage = 1;
        displayDocuments();
    });

    searchInput.addEventListener("input", filterDocuments);

    displayDocuments();
}

function filterDocuments() {
    const searchQuery = document.getElementById("searchInput").value.toLowerCase();

    filteredDocuments = documents.filter(doc => {
        return (
            (doc.nom.toLowerCase().includes(searchQuery) || doc.type.toLowerCase().includes(searchQuery))
        );
    });

    currentPage = 1;
    displayDocuments();
}

function displayDocuments() {
    const tableBody = document.getElementById("documentTableBody");
    if (!tableBody) return;

    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const displayedDocs = filteredDocuments.slice(startIndex, endIndex);

    tableBody.innerHTML = "";

    if (displayedDocs.length === 0) {
        tableBody.innerHTML = "<tr><td colspan='5'>Aucun document trouvé.</td></tr>";
        return;
    }

    displayedDocs.forEach(doc => {
        const row = document.createElement("tr");
        row.dataset.filename = doc.nom;
        row.dataset.nom_physique = doc.nom_physique || doc.nom;
        row.innerHTML = `
        <td>${doc.id}</td>
        <td>${doc.nom}</td>
        <td>${doc.type}</td>
        <td>${new Date(doc.depot).toLocaleString()}</td>
        <td>${new Date(doc.limite).toLocaleString()}</td>
        <td>
            <button class="button preview">Preview</button>
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
            id: Date.now(),
            nom: documentName,
            date: dateDepotDocument,
            type: "N/A",
            auteur: "Current User"
        };

        documents.push(newDocument);
        filteredDocuments = documents;
        currentPage = 1;
        displayDocuments();
        modal.style.display = "none";
        form.reset();
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

function initViewAccountModal() {
    const viewButton = document.querySelector(".button.view");
    const modal = document.getElementById("viewAccountModal");
    if (!modal) return;
    const closeModalButton = modal.querySelector(".close-btn");

    if (viewButton) {
        viewButton.addEventListener("click", () => {
            modal.style.display = "block";
        });
    }
    if (closeModalButton) {
        closeModalButton.addEventListener("click", () => {
            modal.style.display = "none";
        });
    }

    window.addEventListener("click", (e) => {
        if (e.target === modal) {
            modal.style.display = "none";
        }
    });
}

document.addEventListener("click", function (event) {
    if (event.target.matches(".button.preview")) {
        const row = event.target.closest("tr");
        const docPhysique = row.dataset.nom_physique;

        if (!docPhysique) {
            console.warn("nom_physique non défini sur la ligne");
            showToast("Fichier introuvable", "error");
            return;
        }

        const encodedDocNom = encodeURIComponent(docPhysique);
        fetch(`http://admin-mns:8080/api/documents/preview/${encodedDocNom}`, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
        })
            .then(response => {
                if (!response.ok) throw new Error("Erreur lors du chargement du document");
                return response.blob();
            })
            .then(blob => {
                const url = URL.createObjectURL(blob);
                showDocumentPreview(url);
            })
            .catch(error => {
                console.error(error);
                showToast("Impossible d'afficher le document", "error");
            });
    }
});

document.addEventListener("click", function (event) {
    if (event.target.matches(".button.delete")) {
        const row = event.target.closest("tr");
        const docId = row.querySelector("td:first-child").textContent;

        showToast("Voulez-vous vraiment supprimer ce document ?", "warning", 10000);

        if (confirm("Êtes-vous sûr de vouloir supprimer ce document ?")) {
            fetch(`http://admin-mns:8080/api/documents/delete/${docId}`, {
                method: "DELETE",
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('token')
                }
            })
                .then(response => {
                    if (response.ok) {
                        showToast("Document supprimé avec succès", "success", 5000, true);
                        window.location.reload();
                    } else {
                        showToast("Erreur lors de la suppression", "error");
                    }
                })
                .catch(error => {
                    console.error("Erreur:", error);
                    showToast("Erreur réseau lors de la suppression", "error");
                });
        }
    }
});

function showDocumentPreview(url) {
    const modal = document.getElementById('previewDocumentModal');
    const previewContent = document.getElementById('previewContent');

    previewContent.innerHTML = `<iframe src="${url}" style="width:100%; height:100%; border:none;"></iframe>`;

    modal.classList.add('show');
    modal.style.display = 'flex';
}

document.querySelector('#previewDocumentModal .close-btn').addEventListener('click', () => {
    const modal = document.getElementById('previewDocumentModal');
    modal.classList.remove('show');
    setTimeout(() => {
        modal.style.display = 'none';
        document.getElementById('previewContent').innerHTML = '';
    }, 300);
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