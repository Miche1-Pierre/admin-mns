document.addEventListener("DOMContentLoaded", function () {
    initSidebar();
    initDropdownMenu();
    initCards();
    initializeCharts();
    initPagination();
    loadPage(1);
    setupAjaxNavigation();
});

function initSidebar() {
    let sidebar = document.querySelector(".sidebar");
    let main = document.querySelector("main");
    let menuBtn = document.querySelector(".menu-btn");

    if (!sidebar || !menuBtn) return;

    let isSidebarActive = localStorage.getItem("sidebarActive") === "true";

    sidebar.classList.toggle("active", isSidebarActive);
    updateMainLayout(isSidebarActive);

    menuBtn.addEventListener("click", function () {
        let isActive = sidebar.classList.toggle("active");
        updateMainLayout(isActive);
        localStorage.setItem("sidebarActive", isActive);
    });

    function updateMainLayout(active) {
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
                    let subMenu = activeItem.querySelector("ul");
                    if (subMenu) subMenu.style.display = "none";
                }
            });

            this.classList.toggle("active");

            let subMenu = this.querySelector("ul");
            if (subMenu) {
                subMenu.style.display = subMenu.style.display === "block" ? "none" : "block";
            }
        });
    });
}

function initCards() {
    document.querySelectorAll(".card-container").forEach(card => {
        card.addEventListener("mousemove", (e) => {
            let boundingBox = card.getBoundingClientRect();
            let x = e.clientX - boundingBox.left;
            let y = e.clientY - boundingBox.top;
            let centerX = boundingBox.width / 2;
            let centerY = boundingBox.height / 2;
            let rotateX = (centerY - y) / 20;
            let rotateY = (x - centerX) / 20;

            card.style.transform = `rotateX(${rotateX}deg) rotateY(${rotateY}deg)`;
        });

        card.addEventListener("mouseleave", () => {
            card.style.transform = "rotateX(0deg) rotateY(0deg)";
        });
    });
}

function initializeCharts() {
    setTimeout(() => {
        if (window.chartData) {
            window.chartData.forEach(chart => {
                var ctx = document.getElementById(chart.chartId);
                if (ctx) {
                    if (ctx.chartInstance) {
                        ctx.chartInstance.destroy();
                    }

                    ctx.chartInstance = new Chart(ctx.getContext("2d"), {
                        type: chart.chartType,
                        data: {
                            labels: chart.labels,
                            datasets: chart.datasets
                        },
                        options: chart.options
                    });
                } else {
                    console.error(`Canvas avec l'ID '${chart.chartId}' non trouvé.`);
                }
            });
        } else {
            console.error("Aucune donnée de graphique trouvée.");
        }
    }, 500);
}

function setupAjaxNavigation() {
    const links = document.querySelectorAll(".ajax-link");
    const contentDiv = document.getElementById("dashboard-zone");

    links.forEach(link => {
        link.addEventListener("click", function (event) {
            event.preventDefault();

            let page = this.getAttribute("data-page");
            let subPage = this.getAttribute("data-subpage");
            let url = `dashboard.php?page=${page}`;

            if (subPage) {
                url += `&subpage=${subPage}`;
            }

            if (!page) return;

            console.log(`Navigating to: ${url}`);

            fetch(url)
                .then(response => response.text())
                .then(html => {
                    let parser = new DOMParser();
                    let doc = parser.parseFromString(html, "text/html");
                    let newContent = doc.querySelector("#dashboard-zone");

                    if (newContent) {
                        contentDiv.innerHTML = newContent.innerHTML;
                        reinitializeComponents();
                        window.history.pushState({ page: page, subpage: subPage }, "", subPage ? `?page=${page}&subpage=${subPage}` : `?page=${page}`);
                    } else {
                        console.error("Erreur : la page n'a pas pu être chargée");
                    }
                })
                .catch(error => console.error("Erreur:", error));
        });
    });

    window.addEventListener("popstate", function (event) {
        let page = event.state ? event.state.page : "home";
        let subPage = event.state ? event.state.subpage : null;
        let url = `dashboard.php?page=${page}`;

        if (subPage) {
            url += `&subpage=${subPage}`;
        }

        fetch(url)
            .then(response => response.text())
            .then(html => {
                let parser = new DOMParser();
                let doc = parser.parseFromString(html, "text/html");
                let newContent = doc.querySelector("#dashboard-zone");

                if (newContent) {
                    contentDiv.innerHTML = newContent.innerHTML;
                    reinitializeComponents();
                }
            });
    });
}

function initPagination() {
    let pagination = document.getElementById("pagination");
    if (!pagination) return;

    pagination.addEventListener("click", function (e) {
        if (e.target.classList.contains("page-link")) {
            e.preventDefault();
            let page = e.target.getAttribute("data-page");

            loadPage(page);
        }
    });
}

function loadPage(page) {
    fetch(`documents.php?page=${page}`)
        .then(response => response.text())
        .then(html => {
            let parser = new DOMParser();
            let doc = parser.parseFromString(html, "text/html");

            let documentList = doc.querySelector("#document-list");
            let pagination = doc.querySelector("#pagination");

            if (documentList && pagination) {
                document.getElementById("document-list").innerHTML = documentList.innerHTML;
                document.getElementById("pagination").innerHTML = pagination.innerHTML;

                updatePaginationActive(page);
                window.history.pushState({ page: page }, "", `?page=${page}`);
            }

            initDocumentActions();
        })
        .catch(error => console.error("Erreur:", error));
}

function updatePaginationActive(page) {
    document.querySelectorAll(".page-link").forEach(link => {
        if (link.getAttribute("data-page") == page) {
            link.classList.add("active");
        } else {
            link.classList.remove("active");
        }
    });
}

function initDocumentActions() {
    document.getElementById("add-document-btn")?.addEventListener("click", function () {
        document.getElementById("add-document-form")?.classList.toggle("hidden");
    });

    document.getElementById("save-document-btn")?.addEventListener("click", function () {
        let name = document.getElementById("doc-name").value;
        let type = document.getElementById("doc-type").value;
        let auteur = document.getElementById("doc-auteur").value;

        let newRow = `<tr>
            <td>NEW</td>
            <td>${name}</td>
            <td>${type}</td>
            <td>${new Date().toLocaleDateString()}</td>
            <td>${auteur}</td>
            <td>
                <button class="button edit">Modifier</button>
                <button class="button delete">Supprimer</button>
            </td>
        </tr>`;

        document.getElementById("document-list").innerHTML += newRow;
    });
}

window.addEventListener("DOMContentLoaded", () => {
    let params = new URLSearchParams(window.location.search);
    let page = params.get("page") || 1;
    loadPage(page);
});

window.addEventListener("popstate", function (event) {
    let page = event.state ? event.state.page : 1;
    loadPage(page);
});

function reinitializeComponents() {
    initializeCharts();
    initCards();
    initDropdownMenu();
    initPagination();
    initDocumentActions();
}
