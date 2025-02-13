/* Navbar */
document.addEventListener("DOMContentLoaded", function () {
    let sidebar = document.querySelector(".sidebar");
    let main = document.querySelector("main");
    let menuBtn = document.querySelector(".menu-btn");

    // Récupérer l'état de la sidebar au chargement de la page
    let isSidebarActive = localStorage.getItem("sidebarActive") === "true";

    if (isSidebarActive) {
        sidebar.classList.add("active");
        main.style.marginLeft = "100px";
        main.style.width = "calc(100% - 180px)";
    } else {
        sidebar.classList.remove("active");
        main.style.marginLeft = "270px";
        main.style.width = "calc(100% - 350px)";
    }

    // Gérer le clic sur le bouton menu pour afficher/masquer la sidebar
    menuBtn.addEventListener("click", function () {
        sidebar.classList.toggle("active");

        if (sidebar.classList.contains("active")) {
            main.style.marginLeft = "100px";
            main.style.width = "calc(100% - 180px)";
            localStorage.setItem("sidebarActive", "true");
        } else {
            main.style.marginLeft = "270px";
            main.style.width = "calc(100% - 350px)";
            localStorage.setItem("sidebarActive", "false");
        }
    });

    /* Gestion du menu déroulant */
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
});


/* Card */
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

/* Stats */
document.addEventListener("DOMContentLoaded", function () {
    setTimeout(() => {
        if (window.chartData) {
            window.chartData.forEach(chart => {
                var ctx = document.getElementById(chart.chartId);
                if (ctx) {
                    new Chart(ctx.getContext('2d'), {
                        type: chart.chartType,
                        data: {
                            labels: chart.labels,
                            datasets: [{
                                label: chart.title,
                                data: chart.data,
                                backgroundColor: chart.bgColor,
                                borderColor: chart.borderColor,
                                borderWidth: 1
                            }]
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
});


/* AJAX */
document.addEventListener("DOMContentLoaded", function() {
    // Écouteur sur le menu
    document.querySelectorAll(".menu-item").forEach(item => {
        item.addEventListener("click", function() {
            let page = this.dataset.page;
            loadComponent(page);
        });
    });
});
