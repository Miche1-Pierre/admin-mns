window.initDocuments = function () {
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

    let currentPage = 1;
    let filteredDocuments = [...window.documents || []];
    let itemsPerPage = parseInt(itemsPerPageSelect.value) || 10;

    function filterDocuments() {
        console.log("Filtrage en cours...");
        const searchQuery = searchInput.value.toLowerCase();
        const selectedType = filterType.value;
        const selectedAuthor = filterAuthor.value;

        filteredDocuments = (window.documents || []).filter(doc => {
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
        console.log("Affichage des documents...");
        const startIndex = (currentPage - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;

        const displayedDocs = filteredDocuments.slice(startIndex, endIndex);
        tableBody.innerHTML = "";

        displayedDocs.forEach(doc => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${doc.id || "N/A"}</td>
                <td>${doc.nom || "Sans nom"}</td>
                <td>${doc.type || "Inconnu"}</td>
                <td>${doc.date || "Inconnue"}</td>
                <td>${doc.auteur || "Anonyme"}</td>
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
        console.log("Mise à jour de la pagination...");
        const pagination = document.querySelector(".pagination");
        if (!pagination) {
            console.error("Élément pagination introuvable.");
            return;
        }

        const prevButton = pagination.querySelector(".prev-slide");
        const nextButton = pagination.querySelector(".next-slide");

        if (!prevButton || !nextButton) {
            console.error("Boutons de pagination introuvables.");
            return;
        }

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
};

function waitForDocuments(callback) {
    let attempts = 0;
    const maxAttempts = 10;
    
    const checkDocuments = setInterval(() => {
        if (window.documents && window.documents.length > 0) {
            clearInterval(checkDocuments);
            callback();
        } else if (attempts >= maxAttempts) {
            clearInterval(checkDocuments);
            console.error("Les documents ne sont pas chargés après plusieurs tentatives.");
        }
        attempts++;
    }, 100);
}

document.addEventListener("DOMContentLoaded", function () {
    waitForDocuments(initDocuments);
});

