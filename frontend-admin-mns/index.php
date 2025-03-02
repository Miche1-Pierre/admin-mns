<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS -->
    <link rel="stylesheet" href="/frontend-admin-mns/css/style.css">
    <!-- Icons -->
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <title>ADMIN MNS | Landing Page</title>
</head>

<body>
    <header>
        <a href="#" class="logo">Admin <img src="/frontend-admin-mns/assets/logo.png" alt=""></a>
        <nav class="navbar">
            <a href="index.html" class="active">Home</a>
            <a href="#">Products</a>
            <a href="#">Prices</a>
            <a href="#">Ressources</a>
        </nav>
        <div class="auth">
            <a href="views/login.php" class="login">Log In</a>
            <a href="views/login.php" class="signup">Sign Up</a>
        </div>
    </header>

    <main>
        <section class="hero">
            <canvas id="beamsCanvas"></canvas>
            <div class="left">
                <h2>Welcome to Admin <span>MNS</span></h2>
                <p>Unlock your design skills to create beautiful websites and apps with a
                    poweful and easy-to-use tool</p>
                <div class="btn-container">
                    <div class="btn"><a href="#">Primary Action</a></div>
                    <div class="btn"><a href="#">Secondary Action</a></div>
                </div>
            </div>

            <div class="right">
                <img class="hero-image" src="/frontend-admin-mns/assets/hero.png" alt="">
            </div>
            <div class="scroll">
                <a href="#ancre-box" class="scroll-btn"><i class='bx bx-chevrons-down'></i></a>
            </div>
        </section>

        <section class="features" id="ancre-box">
            <h3>The tools you need</h3>
            <h2>Lorem ipsum dolor sit amet, consectetur adipiscing elit</h2>
            <div class="features-card">
                <div class="card" id="card">
                    <i class='bx bx-calendar' id="icon"></i>
                    <p>Egestas elit dui scelerisque ut eu
                        purus aliquam vitae habitasse.</p>
                </div>
                <div class="card" id="card">
                    <i class='bx bx-cloud' id="icon"></i>
                    <p>Id eros pellentesque facilisi id
                        mollis faucibus commodo enim</p>
                </div>
                <div class="card" id="card">
                    <i class='bx bx-line-chart' id="icon"></i>
                    <p>Nunc, pellentesque velit
                        malesuada non massa arcu</p>
                </div>
                <div class="card" id="card">
                    <i class='bx bxs-devices' id="icon"></i>
                    <p>Imperdiet purus pellentesque sit
                        mi nibh sit integer faucibus.</p>
                </div>
            </div>
            <div class="btn-container">
                <div class="btn"><a href="#">Primary Action</a></div>
            </div>
        </section>

        <section class="company">
            <h3>You are in good company</h3>
            <h2>Bibendum amet at molestie mattis</h2>
            <p> Rhoncus morbi et augue nec, in id ullamcorper at sit. Condimentum sit nunc in eros scelerisque sed. Commodo in viverra nunc, ullamcorper ut. Non, amet,
                aliquet scelerisque nullam sagittis, pulvinar. Fermentum scelerisque sit consectetur hac mi. Mollis leo eleifend ultricies purus iaculis.</p>
            <div class="company-card">
                <div class="card-company" id="card">
                    <img src="/frontend-admin-mns/assets/logo.png" alt="" class="logo">
                </div>
                <div class="card-company" id="card">
                    <img src="/frontend-admin-mns/assets/logo_ifa.png" alt="" class="logo">
                </div>
                <div class="card-company" id="card">
                    <img src="/frontend-admin-mns/assets/logo_republique_francaise.jpg" alt="" class="logo">
                </div>
                <div class="card-company" id="card">
                    <img src="/frontend-admin-mns/assets/logo_france_competences.jpg" alt="" class="logo">
                </div>
                <div class="card-company" id="card">
                    <img src="/frontend-admin-mns/assets/logo_eureka-education.png" alt="" class="logo">
                </div>
                <div class="card-company" id="card">
                    <img src="/frontend-admin-mns/assets/qualiopi-logo.png" alt="" class="logo">
                </div>
            </div>
        </section>

        <section class="caption">
            <h3>Caption</h3>
            <h2>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                Bibendum amet at molestie mattis</h2>
            <div class="caption-card">
                <div class="card-caption" id="card">
                    <i class='bx bx-calendar' id="icon"></i>
                    <p> Nibh nullam vitae semper pharetra sit enim id. Ut eu
                        non massa nec. Proin eget semper orci suspendisse in
                        ornare adipiscing phasellus mauris. Velit faucibus at
                        habitasse tempor sit odio ac commodo dui.</p>
                </div>
                <div class="card-caption" id="card">
                    <i class='bx bx-cloud' id="icon"></i>
                    <p>Sed et pulvinar donec sed et, nisl dolor amet. Mollis
                        aliquet volutpat ullamcorper ac sed lectus iaculis.
                        Fringilla sed placerat commodo bibendum integer.
                        Diam ut magna eleifend consectetur. </p>
                </div>
                <div class="card-caption" id="card">
                    <i class='bx bx-line-chart' id="icon"></i>
                    <p>Nunc amet, tempor morbi ligula ut faucibus gravida.
                        Accumsan, suspendisse mus quisque pellentesque id
                        vulputate hendrerit. Donec ipsum nibh elementum
                        platea proin egestas gravida consectetur sit.</p>
                </div>
                <div class="card-caption" id="card">
                    <i class='bx bxs-devices' id="icon"></i>
                    <p>Pellentesque auctor adipiscing lacus viverra. Neque,
                        nulla in amet eget. Arcu, nibh purus urna amet sagittis
                        quis tellus etiam eget. Ultrices egestas a tristique
                        aliquet odio varius.</p>
                </div>
            </div>
            <div class="btn-container">
                <div class="btn"><a href="#">Primary Action</a></div>
            </div>
        </section>

        <section class="pricing">
            <h3>Pricing</h3>
            <h2>Bibendum amet at molestie mattis</h2>
            <p> Rhoncus morbi et augue nec, in id ullamcorper at sit. Condimentum sit nunc in eros scelerisque sed. Commodo in viverra nunc, ullamcorper ut. Non, amet,
                aliquet scelerisque nullam sagittis, pulvinar. Fermentum scelerisque sit consectetur hac mi. Mollis leo eleifend ultricies purus iaculis.</p>
            <div class="pricing-card">
                <div class="card-pricing">
                    <h3>Freelancer</h3>
                    <p> Quisque donec nibh diam tellus integer eros</p>
                    <div class="price">
                        <p class="old">$35</p>
                        <p class="new">$25</p>
                        <p class="description">$25 USD per month, paid annually</p>
                    </div>
                    <div class="btn-container">
                        <div class="btn"><a href="#">Get Started</a></div>
                    </div>
                    <div class="pricing-feature">
                        <p><i class='bx bx-check'></i> Feature One</p>
                        <p><i class='bx bx-check'></i> Feature Two</p>
                        <p><i class='bx bx-check'></i> Feature Three</p>
                        <p><i class='bx bx-check'></i> Feature Four</p>
                        <p><i class='bx bx-check'></i> Feature Five</p>
                        <p><i class='bx bx-check'></i> Feature Six</p>
                    </div>
                </div>

                <div class="card-pricing">
                    <div class="action">
                        <p>Most Popular</p>
                    </div>
                    <h3>Professional</h3>
                    <p> Quisque donec nibh diam tellus integer eros</p>
                    <div class="price">
                        <p class="old">$65</p>
                        <p class="new">$55</p>
                        <p class="description">$55 USD per month, paid annually</p>
                    </div>
                    <div class="btn-container">
                        <div class="btn"><a href="#">Get Started</a></div>
                    </div>
                    <div class="pricing-feature">
                        <p><i class='bx bx-check'></i> Feature One</p>
                        <p><i class='bx bx-check'></i> Feature Two</p>
                        <p><i class='bx bx-check'></i> Feature Three</p>
                        <p><i class='bx bx-check'></i> Feature Four</p>
                        <p><i class='bx bx-check'></i> Feature Five</p>
                        <p><i class='bx bx-check'></i> Feature Six</p>
                        <p><i class='bx bx-check'></i> Feature Seven</p>
                        <p><i class='bx bx-check'></i> Feature Eight</p>
                        <p><i class='bx bx-check'></i> Feature Nine</p>
                    </div>
                </div>

                <div class="card-pricing">
                    <h3>Agency</h3>
                    <p> Quisque donec nibh diam tellus integer eros</p>
                    <div class="price">
                        <p class="old">$125</p>
                        <p class="new">$95</p>
                        <p class="description">$95 USD per month, paid annually</p>
                    </div>
                    <div class="btn-container">
                        <div class="btn"><a href="#">Get Started</a></div>
                    </div>
                    <div class="pricing-feature">
                        <p><i class='bx bx-check'></i> Feature One</p>
                        <p><i class='bx bx-check'></i> Feature Two</p>
                        <p><i class='bx bx-check'></i> Feature Three</p>
                        <p><i class='bx bx-check'></i> Feature Four</p>
                        <p><i class='bx bx-check'></i> Feature Five</p>
                        <p><i class='bx bx-check'></i> Feature Six</p>
                        <p><i class='bx bx-check'></i> Feature Seven</p>
                        <p><i class='bx bx-check'></i> Feature Eight</p>
                        <p><i class='bx bx-check'></i> Feature Nine</p>
                        <p><i class='bx bx-check'></i> Feature Ten</p>
                        <p><i class='bx bx-check'></i> Feature Eleven</p>
                        <p><i class='bx bx-check'></i> Feature Twelve</p>
                    </div>
                </div>
            </div>
        </section>

        <section class="testimonials">
            <h3>Testimonials</h3>
            <h2>Lorem ipsum dolor sit amet, consectetur adipiscing elit</h2>

            <div class="testimonials-container">
                <div class="prev-btn"><i class='bx bx-chevron-left'></i></div>
                <div class="testimonials-card">
                    <div class="card-testimonials-track">
                        <!-- Injection des témoignages -->
                    </div>
                </div>
                <div class="next-btn"><i class='bx bx-chevron-right'></i></div>
            </div>
        </section>


        <section class="team">
            <h3>The team</h3>
            <h2> Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                Bibendum amet at molestie mattis.</h2>
            <div class="team-cards">
                <div class="cards-team">
                    <img src="https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?q=80&w=3560&auto=format&fit=crop" alt="Profile 1" class="profile-img">
                    <h4>John Doe</h4>
                    <p class="role">Software Engineer</p>
                    <div class="social-icons">
                        <a href="#" class="social-icon"><i class="bx bxl-facebook"></i></a>
                        <a href="#" class="social-icon"><i class="bx bxl-twitter"></i></a>
                        <a href="#" class="social-icon"><i class="bx bxl-linkedin"></i></a>
                    </div>
                    <div class="contact-btn">
                        <a href="#">Contact</a>
                    </div>
                </div>

                <div class="cards-team">
                    <img src="https://images.unsplash.com/photo-1623582854588-d60de57fa33f?q=80&w=3540&auto=format&fit=crop" alt="Profile 2" class="profile-img">
                    <h4>Jane Smith</h4>
                    <p class="role">Product Manager</p>
                    <div class="social-icons">
                        <a href="#" class="social-icon"><i class='bx bxl-facebook'></i></a>
                        <a href="#" class="social-icon"><i class="bx bxl-twitter"></i></a>
                        <a href="#" class="social-icon"><i class="bx bxl-linkedin"></i></a>
                    </div>
                    <div class="contact-btn">
                        <a href="#">Contact</a>
                    </div>
                </div>

                <div class="cards-team">
                    <img src="https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=3540&auto=format&fit=crop" alt="Profile 3" class="profile-img">
                    <h4>Mark Johnson</h4>
                    <p class="role">Designer</p>
                    <div class="social-icons">
                        <a href="#" class="social-icon"><i class="bx bxl-facebook"></i></a>
                        <a href="#" class="social-icon"><i class="bx bxl-twitter"></i></a>
                        <a href="#" class="social-icon"><i class="bx bxl-linkedin"></i></a>
                    </div>
                    <div class="contact-btn">
                        <a href="#">Contact</a>
                    </div>
                </div>

                <div class="cards-team">
                    <img src="https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?q=80&w=3560&auto=format&fit=crop" alt="Profile 4" class="profile-img">
                    <h4>Sarah Lee</h4>
                    <p class="role">Marketing Specialist</p>
                    <div class="social-icons">
                        <a href="#" class="social-icon"><i class="bx bxl-facebook"></i></a>
                        <a href="#" class="social-icon"><i class="bx bxl-twitter"></i></a>
                        <a href="#" class="social-icon"><i class="bx bxl-linkedin"></i></a>
                    </div>
                    <div class="contact-btn">
                        <a href="#">Contact</a>
                    </div>
                </div>
            </div>
        </section>

        <section class="faq">
            <h3>FAQ</h3>
            <h2>Everything You Need to Know</h2>
            <div class="faq-item">
                <div class="question">
                    <h4>What is the return policy?</h4>
                    <span class="icon">+</span>
                </div>
                <div class="answer">
                    <p>Our return policy allows for returns within 30 days of purchase. Items must be in original condition.</p>
                </div>
            </div>

            <div class="faq-item">
                <div class="question">
                    <h4>How long does shipping take?</h4>
                    <span class="icon">+</span>
                </div>
                <div class="answer">
                    <p>Shipping typically takes 5-7 business days depending on your location. Expedited shipping is available for an extra fee.</p>
                </div>
            </div>

            <div class="faq-item">
                <div class="question">
                    <h4>Can I change my order after it is placed?</h4>
                    <span class="icon">+</span>
                </div>
                <div class="answer">
                    <p>Once an order is placed, it can only be modified within 24 hours. Please contact customer service immediately if changes are needed.</p>
                </div>
            </div>

            <div class="faq-item">
                <div class="question">
                    <h4>Do you offer international shipping?</h4>
                    <span class="icon">+</span>
                </div>
                <div class="answer">
                    <p>Yes, we offer international shipping to most countries. Shipping fees and delivery times may vary.</p>
                </div>
            </div>

            <div class="faq-item">
                <div class="question">
                    <h4>How do I track my order?</h4>
                    <span class="icon">+</span>
                </div>
                <div class="answer">
                    <p>Once your order has shipped, we will send you a tracking number via email so you can track your package online.</p>
                </div>
            </div>
        </section>

        <section class="cta">
            <h3>CTA</h3>
            <h2> Bibendum amet at molestie mattis</h2>
            <p> Rhoncus morbi et augue nec, in id ullamcorper at sit. Condimentum sit nunc in eros scelerisque sed. Commodo in viverra nunc, ullamcorper ut. Non, amet,
                aliquet scelerisque nullam sagittis, pulvinar. Fermentum scelerisque sit consectetur hac mi. Mollis leo eleifend ultricies purus iaculis</p>
            <div class="btn-container">
                <div class="btn"><a href="#">Primary Action</a></div>
                <div class="btn"><a href="#">Secondary Action</a></div>
            </div>
        </section>
    </main>

    <footer>
        <div class="footer-container">
            <div class="footer-left">
                <p>&copy; 2025 MNS. All rights reserved.</p>
            </div>
            <div class="footer-center">
                <ul>
                    <li><a href="index.html">Home</a></li>
                    <li><a href="#">Products</a></li>
                    <li><a href="#">Prices</a></li>
                    <li><a href="#">Ressources</a></li>
                </ul>
            </div>
            <div class="footer-right">
                <ul class="social-links">
                    <li><a href="#" class="social-icon"><i class="bx bxl-facebook icon"></i></a></li>
                    <li><a href="#" class="social-icon"><i class="bx bxl-twitter"></i></a></li>
                    <li><a href="#" class="social-icon"><i class="bx bxl-instagram"></i></a></li>
                    <li><a href="#" class="social-icon"><i class="bx bxl-linkedin"></i></a></li>
                </ul>
            </div>
        </div>
    </footer>

    <script src="/frontend-admin-mns/js/script.js"></script>
</body>

</html>