<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vortex Particles</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            background-color: black;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            overflow: hidden;
        }

        /* Conteneur du vortex */
        .vortex-container {
            width: 100%;
            height: 100vh;
            position: relative;
        }

        /* Canvas pour les particules */
        #particles-js {
            position: absolute;
            width: 100%;
            height: 100%;
        }

        /* Contenu au centre */
        .content {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            text-align: center;
            color: white;
            z-index: 10;
        }

        h2 {
            font-size: 2rem;
            font-weight: bold;
        }

        p {
            font-size: 1rem;
            max-width: 500px;
            margin: 20px auto;
        }

        .buttons {
            display: flex;
            gap: 10px;
            justify-content: center;
        }

        .buttons button {
            padding: 10px 20px;
            font-size: 1rem;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: 0.2s;
        }

        .btn-primary {
            background-color: #1d4ed8;
            color: white;
        }

        .btn-primary:hover {
            background-color: #1e40af;
        }

        .btn-secondary {
            background: none;
            color: white;
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="vortex-container">
        <div id="particles-js"></div>
        <div class="content">
            <h2>The hell is this?</h2>
            <p>This is chemical burn. It'll hurt more than you've ever been burned and you'll have a scar.</p>
            <div class="buttons">
                <button class="btn-primary">Order now</button>
                <button class="btn-secondary">Watch trailer</button>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js"></script>
    <script>
        particlesJS("particles-js", {
            "particles": {
                "number": {
                    "value": 150, // Nombre de particules
                    "density": { "enable": true, "value_area": 800 }
                },
                "color": { "value": "#ffffff" }, // Couleur des particules
                "shape": {
                    "type": "circle",
                    "stroke": { "width": 0, "color": "#000000" },
                },
                "opacity": {
                    "value": 0.5,
                    "random": true,
                    "anim": { "enable": true, "speed": 1, "opacity_min": 0.1, "sync": false }
                },
                "size": {
                    "value": 3,
                    "random": true,
                    "anim": { "enable": true, "speed": 2, "size_min": 0.3, "sync": false }
                },
                "line_linked": { "enable": false },
                "move": {
                    "enable": true,
                    "speed": 2, // Vitesse des particules
                    "direction": "none",
                    "random": false,
                    "straight": false,
                    "out_mode": "out",
                    "bounce": false
                }
            },
            "interactivity": {
                "detect_on": "canvas",
                "events": {
                    "onhover": { "enable": true, "mode": "repulse" },
                    "onclick": { "enable": true, "mode": "push" },
                    "resize": true
                },
                "modes": {
                    "repulse": { "distance": 100, "duration": 0.4 }
                }
            },
            "retina_detect": true
        });
    </script>

</body>
</html>
