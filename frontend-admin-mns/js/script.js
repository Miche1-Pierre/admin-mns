/* Banner */
const container = document.querySelector('.banner-animation .stagger-visualizer');
const containerWidth = container.offsetWidth;
const containerHeight = container.offsetHeight;
const elementSize = 16;
const columns = Math.floor(containerWidth / elementSize);
const rows = Math.floor(containerHeight / elementSize);
const grid = [columns, rows];
const numberOfElements = columns * rows;

const fragment = document.createDocumentFragment();
for (let i = 0; i < numberOfElements; i++) {
    fragment.appendChild(document.createElement('div'));
}
container.appendChild(fragment);

const staggersAnimation = anime.timeline({
    targets: '.banner-animation .stagger-visualizer div',
    easing: 'easeInOutSine',
    delay: anime.stagger(50),
    loop: true,
    autoplay: false
})
    .add({
        translateX: [
            { value: anime.stagger('-.1rem', { grid: grid, from: 'center', axis: 'x' }) },
            { value: anime.stagger('.1rem', { grid: grid, from: 'center', axis: 'x' }) }
        ],
        translateY: [
            { value: anime.stagger('-.1rem', { grid: grid, from: 'center', axis: 'y' }) },
            { value: anime.stagger('.1rem', { grid: grid, from: 'center', axis: 'y' }) }
        ],
        duration: 1000,
        scale: 0.5,
        delay: anime.stagger(100, { grid: grid, from: 'center' })
    })
    .add({
        translateX: () => anime.random(-10, 10),
        translateY: () => anime.random(-10, 10),
        delay: anime.stagger(8, { from: 'last' })
    })
    .add({
        translateX: anime.stagger('.25rem', { grid: grid, from: 'center', axis: 'x' }),
        translateY: anime.stagger('.25rem', { grid: grid, from: 'center', axis: 'y' }),
        rotate: 0,
        scaleX: 2.5,
        scaleY: 0.25,
        delay: anime.stagger(4, { from: 'center' })
    })
    .add({
        rotate: anime.stagger([90, 0], { grid: grid, from: 'center' }),
        delay: anime.stagger(50, { grid: grid, from: 'center' })
    })
    .add({
        translateX: 0,
        translateY: 0,
        scale: 0.5,
        scaleX: 1,
        rotate: 180,
        duration: 1000,
        delay: anime.stagger(100, { grid: grid, from: 'center' })
    })
    .add({
        scaleY: 1,
        scale: 1,
        delay: anime.stagger(20, { grid: grid, from: 'center' })
    });

staggersAnimation.play();

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
