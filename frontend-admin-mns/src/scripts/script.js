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
