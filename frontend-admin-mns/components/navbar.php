<?php
if (!isset($_SESSION["token"])) {
    header("Location: login.php");
    exit();
}

$token = $_SESSION["token"];
$apiUrl = "http://admin-mns:8080/api/dashboard/menus";

$headers = [
    "Authorization: Bearer $token",
    "Content-Type: application/json"
];

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $apiUrl);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
$response = curl_exec($ch);

if ($response === false) {
    error_log("Erreur cURL : " . curl_error($ch));
    curl_close($ch);
    $menusData = [];
} else {
    curl_close($ch);
    $menusData = json_decode($response, true);
}
?>

<div class="navbar">
    <aside class="sidebar">
        <div class="menu-btn">
            <i class='bx bx-chevron-left icon'></i>
        </div>
        <div class="head">
            <div class="logo-details">
                <p class="details">Admin</p>
                <img src="/frontend-admin-mns/assets/logo.png" alt="">
            </div>
        </div>
        <div class="nav">
            <div class="menu">
                <p class="title">Main</p>
                <ul>
                    <!-- Lien Home -->
                    <?php if (isset($menusData['home'])): ?>
                        <li>
                            <a href="<?php echo $menusData['home']['link']; ?>">
                                <i class="<?php echo $menusData['home']['icon']; ?>"></i>
                                <span class="text"><?php echo $menusData['home']['title']; ?></span>
                            </a>
                        </li>
                    <?php endif; ?>

                    <!-- Sous-menu Modules pour Candidatures et Absences -->
                    <?php if (isset($menusData['candidatures']) || isset($menusData['absences'])): ?>
                        <li>
                            <a>
                                <i class='bx bx-grid-alt'></i>
                                <span class="text">Modules</span>
                                <i class='bx bx-chevron-down'></i>
                            </a>
                            <ul class="sub-menu">
                                <?php if (isset($menusData['candidatures'])): ?>
                                    <li>
                                        <a href="<?php echo $menusData['candidatures']['link']; ?>">
                                            <i class="<?php echo $menusData['candidatures']['icon']; ?>"></i>
                                            <span class="text"><?php echo $menusData['candidatures']['title']; ?></span>
                                        </a>
                                    </li>
                                <?php endif; ?>
                                <?php if (isset($menusData['absences'])): ?>
                                    <li>
                                        <a href="<?php echo $menusData['absences']['link']; ?>">
                                            <i class="<?php echo $menusData['absences']['icon']; ?>"></i>
                                            <span class="text"><?php echo $menusData['absences']['title']; ?></span>
                                        </a>
                                    </li>
                                <?php endif; ?>
                            </ul>
                        </li>
                    <?php endif; ?>

                    <!-- Lien Users -->
                    <?php if (isset($menusData['users'])): ?>
                        <li>
                            <a href="<?php echo $menusData['users']['link']; ?>">
                                <i class="<?php echo $menusData['users']['icon']; ?>"></i>
                                <span class="text"><?php echo $menusData['users']['title']; ?></span>
                            </a>
                        </li>
                    <?php endif; ?>

                    <!-- Lien Documents -->
                    <?php if (isset($menusData['documents'])): ?>
                        <li>
                            <a href="<?php echo $menusData['documents']['link']; ?>">
                                <i class="<?php echo $menusData['documents']['icon']; ?>"></i>
                                <span class="text"><?php echo $menusData['documents']['title']; ?></span>
                            </a>
                        </li>
                    <?php endif; ?>

                    <!-- Lien Messaging -->
                    <?php if (isset($menusData['messaging'])): ?>
                        <li>
                            <a href="<?php echo $menusData['messaging']['link']; ?>">
                                <i class="<?php echo $menusData['messaging']['icon']; ?>"></i>
                                <span class="text"><?php echo $menusData['messaging']['title']; ?></span>
                            </a>
                        </li>
                    <?php endif; ?>

                    <!-- Sous-menu More pour Stats et Settings -->
                    <?php if (isset($menusData['stats']) || isset($menusData['settings'])): ?>
                        <li>
                            <a>
                                <i class='bx bx-dots-horizontal-rounded'></i>
                                <span class="text">More</span>
                                <i class='bx bx-chevron-down'></i>
                            </a>
                            <ul class="sub-menu">
                                <?php if (isset($menusData['stats'])): ?>
                                    <li>
                                        <a href="<?php echo $menusData['stats']['link']; ?>">
                                            <i class="<?php echo $menusData['stats']['icon']; ?>"></i>
                                            <span class="text"><?php echo $menusData['stats']['title']; ?></span>
                                        </a>
                                    </li>
                                <?php endif; ?>
                                <?php if (isset($menusData['settings'])): ?>
                                    <li>
                                        <a href="<?php echo $menusData['settings']['link']; ?>">
                                            <i class="<?php echo $menusData['settings']['icon']; ?>"></i>
                                            <span class="text"><?php echo $menusData['settings']['title']; ?></span>
                                        </a>
                                    </li>
                                <?php endif; ?>
                            </ul>
                        </li>
                    <?php endif; ?>
                </ul>
            </div>
        </div>
        <div class="menu">
            <p class="title">Account</p>
            <ul>
                <li>
                    <a href="#" onclick="logout()">
                        <i class='bx bx-log-out'></i>
                        <span class="text">Logout</span>
                    </a>
                </li>
                <li>
                    <a href="#" class="ajax-link">
                        <i class='bx bx-cog'></i>
                        <span class="text">Settings</span>
                    </a>
                </li>
                <li>
                    <a href="#" class="ajax-link">
                        <i class='bx bx-question-mark'></i>
                        <span class="text">Help</span>
                    </a>
                </li>
            </ul>
        </div>
    </aside>
</div>

<script>
    function logout() {
        const token = localStorage.getItem('token');
        fetch("http://admin-mns:8080/api/auth/logout", {
                method: "POST",
                headers: {
                    'Authorization': 'Bearer ' + token,
                    'Content-Type': 'application/json'
                }
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Erreur lors du logout côté serveur");
                }
                return response.text();
            })
            .then(data => {
                console.log(data);
                localStorage.removeItem("token");
                window.location.href = "/frontend-admin-mns/views/login";
            })
            .catch(error => {
                console.error(error);
                alert("Erreur lors du logout");
            });
    }
</script>