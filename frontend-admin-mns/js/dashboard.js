/* Navbar */
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

document.querySelector(".menu-btn").addEventListener("click", function () {
    document.querySelector(".sidebar").classList.toggle("active");
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
    document.querySelectorAll("canvas[data-chart]").forEach(canvas => {
        let chartConfig = JSON.parse(canvas.getAttribute("data-chart"));
        let ctx = canvas.getContext("2d");

        new Chart(ctx, {
            type: chartConfig.type,
            data: chartConfig.data,
            options: {
                responsive: false,
                maintainAspectRatio: false
            }
        });
    });
});