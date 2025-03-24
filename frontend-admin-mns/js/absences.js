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
        const userRole = localStorage.getItem("role");

        let actionsHtml = `<button class="button read">Voir</button>`;

        if (userRole === "Admin") {
            actionsHtml += `<button class="button delete">Supprimer</button>`;
        } else {
            if (absence.etat !== "VALIDE") {
                actionsHtml += `<button class="button edit">Modifier</button>`;
                actionsHtml += `<button class="button delete">Supprimer</button>`;
            }
        }

        row.innerHTML = `
            <td>${absence.id}</td>
            <td>${absence.utilisateur}</td>
            <td>${absence.statut}</td>
            <td>${absence.type}</td>
            <td>${new Date(absence.debut).toLocaleString()}</td>
            <td>${new Date(absence.fin).toLocaleString()}</td>
            <td>${absence.justifie ? 'Oui' : 'Non'}</td>
            <td>${absence.etat || "Non défini"}</td>
            <td>
                ${actionsHtml}
            </td>
        `;
        tableBody.appendChild(row);
    });

    // Fonction de gestion du bouton "Voir" (lecture)
    document.querySelectorAll(".button.read").forEach(button => {
        button.addEventListener("click", () => {
            const absenceId = button.closest("tr").querySelector("td:first-child").textContent;
            console.log("Token:", localStorage.getItem("token"));

            fetch(`http://admin-mns:8080/api/absences/absence/${absenceId}`, {
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('token')
                }
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Erreur réseau: " + response.status);
                    }
                    return response.json();
                })
                .then(data => {
                    console.log("Données reçues :", data);

                    document.getElementById("readId").textContent = data.idAbsence || "Non disponible";
                    document.getElementById("readUtilisateur").textContent = data.utilisateur || "Non disponible";
                    document.getElementById("readStatut").textContent = data.statutAbsence || "Non disponible";
                    document.getElementById("readType").textContent = data.typeAbsence || "Non disponible";

                    document.getElementById("readDebut").textContent = data.dateDebutAbsence
                        ? new Date(data.dateDebutAbsence).toLocaleString()
                        : "Non disponible";

                    document.getElementById("readFin").textContent = data.dateFinAbsence
                        ? new Date(data.dateFinAbsence).toLocaleString()
                        : "Non disponible";

                    document.getElementById("readJustifie").textContent = data.justifieAbsence ? "Oui" : "Non";

                    const validateButton = document.getElementById("validateAbsence");
                    const refuseButton = document.getElementById("refuseAbsence");

                    const currentUserRole = localStorage.getItem("role");

                    if (currentUserRole === "Stagiaire") {
                        validateButton.style.display = "none";
                        refuseButton.style.display = "none";
                    } else {
                        validateButton.style.display = "inline-block";
                        refuseButton.style.display = "inline-block";

                        if (data.etatAbsence === "VALIDE") {
                            validateButton.disabled = true;
                            refuseButton.disabled = true;
                        } else if (data.etatAbsence === "REFUSE") {
                            validateButton.disabled = false;
                            refuseButton.disabled = true;
                        } else {
                            validateButton.disabled = false;
                            refuseButton.disabled = false;
                        }

                        // Fonction pour valider l'absence
                        validateButton.onclick = function () {
                            fetch(`http://admin-mns:8080/api/absences/absence/validate/${data.idAbsence}`, {
                                method: 'PUT',
                                headers: {
                                    'Authorization': 'Bearer ' + localStorage.getItem('token')
                                }
                            })
                                .then(response => response.json())
                                .then(() => {
                                    console.log("Absence validée");
                                    validateButton.disabled = true;
                                    refuseButton.disabled = true;
                                })
                                .catch(error => console.error("Erreur de validation :", error));
                        };

                        // Fonction pour refuser l'absence
                        refuseButton.onclick = function () {
                            fetch(`http://admin-mns:8080/api/absences/absence/refuse/${data.idAbsence}`, {
                                method: 'PUT',
                                headers: {
                                    'Authorization': 'Bearer ' + localStorage.getItem('token')
                                }
                            })
                                .then(response => response.json())
                                .then(() => {
                                    console.log("Absence refusée");
                                    validateButton.disabled = true;
                                    refuseButton.disabled = true;
                                })
                                .catch(error => console.error("Erreur de refus :", error));
                        };
                    }

                    // Afficher la modal
                    const modal = document.getElementById("readAbsenceModal");
                    modal.style.display = "block";
                })
                .catch(error => console.error("Erreur lors de la récupération :", error));
        });
    });

    // Fonction de gestion de la fermeture de la modal
    document.getElementById("closeReadModal").addEventListener("click", () => {
        document.getElementById("readAbsenceModal").style.display = "none";
    });

    window.addEventListener("click", (e) => {
        const modal = document.getElementById("readAbsenceModal");
        if (e.target === modal) {
            modal.style.display = "none";
        }
    });

    // Fonction de gestion du bouton "Modifier" (édition)
    document.querySelectorAll(".button.edit").forEach(button => {
        button.addEventListener("click", () => {
            const row = button.closest("tr");
            const absenceId = row.querySelector("td:first-child").textContent;

            const absenceData = filteredAbsences.find(a => a.id == absenceId);
            if (!absenceData) {
                console.error("Absence non trouvée pour l'édition");
                return;
            }

            const modal = document.getElementById("editAbsenceModal");
            modal.style.display = "block";

            document.getElementById("editStatut").value = absenceData.statut;
            document.getElementById("editType").value = absenceData.type;

            const debut = new Date(absenceData.debut).toISOString().slice(0, 16);
            const fin = new Date(absenceData.fin).toISOString().slice(0, 16);
            document.getElementById("editDebut").value = debut;
            document.getElementById("editFin").value = fin;

            const editForm = document.getElementById("editAbsenceForm");
            editForm.onsubmit = async (e) => {
                e.preventDefault();

                const updatedStatut = document.getElementById("editStatut").value;
                const updatedType = document.getElementById("editType").value;
                const updatedDebut = document.getElementById("editDebut").value;
                const updatedFin = document.getElementById("editFin").value;
                const updatedJustificatif = document.getElementById("editJustificatif").files[0];

                const formData = new FormData();
                formData.append("statutAbsence", updatedStatut);
                formData.append("dateDebutAbsence", updatedDebut);
                formData.append("dateFinAbsence", updatedFin);
                if (updatedJustificatif) {
                    formData.append("justificatif", updatedJustificatif);
                }

                try {
                    const response = await fetch(`http://admin-mns:8080/api/absences/update/${absenceId}`, {
                        method: "PUT",
                        headers: {
                            'Authorization': 'Bearer ' + localStorage.getItem('token')
                        },
                        body: formData
                    });

                    const result = await response.json();

                    if (response.ok) {
                        alert("Absence mise à jour avec succès !");
                        modal.style.display = "none";
                        window.location.reload();
                    } else {
                        alert("Erreur lors de la mise à jour : " + (result.message || "Erreur inconnue."));
                    }
                } catch (error) {
                    console.error("Erreur lors de la mise à jour :", error);
                    alert("Une erreur est survenue lors de la mise à jour.");
                }
            };
        });
    });

    // Fonction de gestion de la fermeture de la modal d'édition
    document.getElementById("closeEditModal").addEventListener("click", () => {
        document.getElementById("editAbsenceModal").style.display = "none";
    });

    window.addEventListener("click", (e) => {
        const modal = document.getElementById("editAbsenceModal");
        if (e.target === modal) {
            modal.style.display = "none";
        }
    });

    // Fonction de gestion du bouton "Supprimer"
    document.querySelectorAll(".button.delete").forEach(button => {
        button.addEventListener("click", () => {
            if (confirm("Voulez-vous vraiment supprimer cette absence ?")) {
                const absenceId = button.closest("tr").querySelector("td:first-child").textContent;
                fetch(`http://admin-mns:8080/api/absences/delete/${absenceId}`, {
                    method: "DELETE",
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.getItem('token')
                    }
                })
                    .then(response => {
                        if (response.ok) {
                            alert("Absence supprimée");
                        } else {
                            alert("Erreur lors de la suppression");
                        }
                    })
                    .catch(error => console.error("Erreur:", error));
            }
        });
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

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const statut = document.getElementById("statut").value;
        const idTypeAbsence = document.getElementById("type").value;
        const dateDebutAbsence = document.getElementById("debut").value;
        const dateFinAbsence = document.getElementById("fin").value;
        const justifie = document.getElementById("justifie").files[0];

        const formData = new FormData();
        formData.append("statutAbsence", statut);
        formData.append("idTypeAbsence", idTypeAbsence);
        formData.append("dateDebutAbsence", dateDebutAbsence);
        formData.append("dateFinAbsence", dateFinAbsence);

        if (justifie) {
            formData.append("justificatif", justifie);
        }

        try {
            const response = await fetch("http://admin-mns:8080/api/absences", {
                method: "POST",
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('token')
                },
                body: formData,
            });

            const result = await response.json();

            if (response.ok) {
                alert("Absence ajoutée avec succès !");
                window.location.reload();
            } else {
                alert("Erreur : " + (result.message || "Impossible d'ajouter l'absence."));
            }
        } catch (error) {
            console.error("Erreur lors de l'ajout de l'absence :", error);
            alert("Une erreur est survenue. Veuillez réessayer.");
        }
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
                localStorage.setItem("role", profile.nom_role);
            }
        })
        .catch(error => console.error('Erreur:', error));
}
