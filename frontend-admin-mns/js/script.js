/* Hero */
const canvas = document.getElementById('beamsCanvas');
const ctx = canvas.getContext('2d');

// Définir la taille du canvas
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

// Tableau des lignes
let lines = [];

// Nombre de lignes à créer par seconde (ou par frame)
const linesPerFrame = 1;  // Par exemple, 5 lignes à chaque frame

let frameCount = 0;

// Fonction pour créer une nouvelle ligne
function createLine() {
    const line = {
        x: Math.random() * canvas.width,  // Position horizontale aléatoire
        y: -20,                          // Position initiale au-dessus de l'écran
        length: Math.random() * 100 + 50, // Longueur aléatoire de la ligne
        speed: Math.random() * 3 + 2,     // Vitesse de chute aléatoire
        alpha: 1,                         // Opacité initiale
        color: `#D90429` // Couleur aléatoire
    };
    lines.push(line);
}

// Dessiner les lignes
function drawLines() {
    ctx.clearRect(0, 0, canvas.width, canvas.height); // Effacer le canvas à chaque frame

    lines.forEach((line, index) => {
        ctx.save();
        ctx.translate(line.x, line.y); // Déplacer le point de départ de la ligne

        // Dessiner la ligne
        ctx.globalAlpha = line.alpha;
        ctx.strokeStyle = line.color;
        ctx.lineWidth = 2;
        ctx.beginPath();
        ctx.moveTo(0, 0);
        ctx.lineTo(0, line.length);
        ctx.stroke();

        // Mettre à jour la position de la ligne
        line.y += line.speed;
        line.alpha -= 0.005; // La ligne devient de plus en plus transparente

        // Retirer la ligne une fois qu'elle est partie de l'écran
        if (line.y > canvas.height || line.alpha <= 0) {
            lines.splice(index, 1);
        }

        ctx.restore();
    });
}

// Boucle d'animation
function animate() {
    frameCount++;

    // Créer plusieurs lignes à chaque "linesPerFrame" frames
    if (frameCount % 5 === 0) {  // Crée 5 lignes toutes les 5 frames
        for (let i = 0; i < linesPerFrame; i++) {
            createLine();
        }
    }

    drawLines();  // Dessiner toutes les lignes
    requestAnimationFrame(animate); // Continuer l'animation
}

animate();

/* Card */
document.querySelectorAll(".card").forEach(card => {
    card.addEventListener("mousemove", (e) => {
        let boundingBox = card.getBoundingClientRect();
        let x = e.clientX - boundingBox.left;
        let y = e.clientY - boundingBox.top;
        let centerX = boundingBox.width / 2;
        let centerY = boundingBox.height / 2;
        let rotateX = (centerY - y) / 5;
        let rotateY = (x - centerX) / 5;

        card.style.transform = `rotateX(${rotateX}deg) rotateY(${rotateY}deg)`;
    });

    card.addEventListener("mouseleave", () => {
        card.style.transform = "rotateX(0deg) rotateY(0deg)";
    });
});


/* Testimonials */
const testimonials = [
    {
        quote: "The attention to detail and innovative features have completely transformed our workflow. This is exactly what we've been looking for.",
        name: "Sarah Chen",
        designation: "Product Manager at TechFlow",
        src: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?q=80&w=3560&auto=format&fit=crop"
    },
    {
        quote: "Implementation was seamless and the results exceeded our expectations. The platform's flexibility is remarkable.",
        name: "Michael Rodriguez",
        designation: "CTO at InnovateSphere",
        src: "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=3540&auto=format&fit=crop"
    },
    {
        quote: "This solution has significantly improved our team's productivity. The intuitive interface makes complex tasks simple.",
        name: "Emily Watson",
        designation: "Operations Director at CloudScale",
        src: "https://images.unsplash.com/photo-1623582854588-d60de57fa33f?q=80&w=3540&auto=format&fit=crop"
    }
];

const track = document.querySelector(".card-testimonials-track");
const prevBtn = document.querySelector(".prev-btn");
const nextBtn = document.querySelector(".next-btn");

let currentIndex = 0;

function loadTestimonials() {
    testimonials.forEach((testimonial) => {
        const card = document.createElement("div");
        card.classList.add("card-testimonial");
        card.innerHTML = `
            <img src="${testimonial.src}" alt="${testimonial.name}">
            <p>"${testimonial.quote}"</p>
            <h4>${testimonial.name}</h4>
            <span>${testimonial.designation}</span>
        `;
        track.appendChild(card);
    });
}

loadTestimonials();

function updateSlider() {
    track.style.transform = `translateX(-${currentIndex * 100}%)`;
}

nextBtn.addEventListener("click", () => {
    if (currentIndex < testimonials.length - 1) {
        currentIndex++;
    } else {
        currentIndex = 0;
    }
    updateSlider();
});

prevBtn.addEventListener("click", () => {
    if (currentIndex > 0) {
        currentIndex--;
    } else {
        currentIndex = testimonials.length - 1;
    }
    updateSlider();
});

setInterval(() => {
    if (currentIndex < testimonials.length - 1) {
        currentIndex++;
    } else {
        currentIndex = 0;
    }
    updateSlider();
}, 5000);


/* FAQ */
document.querySelectorAll('.faq-item .question').forEach(item => {
    item.addEventListener('click', () => {
        const faqItem = item.closest('.faq-item');
        faqItem.classList.toggle('active');
    });
});
