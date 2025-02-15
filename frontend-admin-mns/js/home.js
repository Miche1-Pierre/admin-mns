window.initCards = function () {
    document.querySelectorAll(".card-container").forEach(card => {
        let lastExecution = 0;
        const throttleDelay = 100;

        card.addEventListener("mousemove", (e) => {
            const now = Date.now();
            if (now - lastExecution >= throttleDelay) {
                lastExecution = now;

                let boundingBox = card.getBoundingClientRect();
                let x = e.clientX - boundingBox.left;
                let y = e.clientY - boundingBox.top;
                let centerX = boundingBox.width / 2;
                let centerY = boundingBox.height / 2;
                let rotateX = (centerY - y) / 20;
                let rotateY = (x - centerX) / 20;

                card.style.transform = `rotateX(${rotateX}deg) rotateY(${rotateY}deg)`;
            }
        });

        card.addEventListener("mouseleave", () => {
            card.style.transform = "rotateX(0deg) rotateY(0deg)";
        });
    });
};

window.initializeCharts = function () {
    console.log("Initialisation des graphiques...");
    setTimeout(() => {
        if (window.chartData) {
            window.chartData.forEach(chart => {
                var ctx = document.getElementById(chart.chartId);
                if (ctx) {
                    if (ctx.chartInstance) {
                        ctx.chartInstance.destroy(); // Supprimer l'ancien graphe
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
    }, 200);
};

initCards();
initializeCharts();