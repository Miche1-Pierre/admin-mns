document.addEventListener("DOMContentLoaded", function () {
    initSidebar();
    initDropdownMenu();
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
                        reinitializeComponents(page);
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
                    reinitializeComponents(page);
                }
            });
    });
}

function reinitializeComponents(page) {
    console.log(`Reinitialisation des composants pour ${page}...`);

    removeScript("home.js");
    removeScript("documents.js");

    if (page === "home") {
        loadScript("home.js");
    } else if (page === "documents") {
        loadScript("documents.js", function () {
            if (typeof initDocuments === "function") {
                initDocuments();
            } else {
                console.error("initDocuments() non trouvé après le chargement de documents.js");
            }
        });
    }
}

function loadScript(filename, callback) {
    let existingScript = document.querySelector(`script[src*="${filename}"]`);
    if (existingScript) {
        existingScript.remove();
    }

    let script = document.createElement("script");
    script.type = "text/javascript";
    script.src = `/frontend-admin-mns/js/${filename}`;
    
    script.onload = () => {
        console.log(`${filename} chargé avec succès.`);
        if (callback) callback();
    };
    
    script.onerror = () => console.error(`Erreur de chargement du script: ${filename}`);
    
    document.body.appendChild(script);
}

function removeScript(filename) {
    let oldScript = document.querySelector(`script[src*="${filename}"]`);
    if (oldScript) {
        oldScript.remove();
        console.log(`${filename} supprimé.`);
    }
}
