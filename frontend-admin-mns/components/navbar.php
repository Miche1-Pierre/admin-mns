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
                    <li>
                        <a href="/frontend-admin-mns/views/dashboard.php">
                            <i class='bx bx-home-alt'></i>
                            <span class="text">Home</span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class='bx bx-grid-alt'></i>
                            <span class="text">Modules</span>
                            <i class='bx bx-chevron-down'></i>
                        </a>
                        <ul class="sub-menu">
                            <li>
                                <a href="/frontend-admin-mns/components/modules/candidatures.php">
                                    <i class='bx bxs-id-card'></i>
                                    <span class="text">Applications</span>
                                </a>
                            </li>
                            <li>
                                <a href="/frontend-admin-mns/components/modules/absences.php">
                                    <i class='bx bx-timer'></i>
                                    <span class="text">Absences & Lateness</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="/frontend-admin-mns/components/users/users.php">
                            <i class='bx bx-user'></i>
                            <span class="text">Users</span>
                        </a>
                    </li>
                    <li>
                        <a href="/frontend-admin-mns/components/documents/documents.php">
                            <i class='bx bx-folder'></i>
                            <span class="text">Documents (GED)</span>
                        </a>
                    </li>
                    <li>
                        <a href="/frontend-admin-mns/components/messaging/messaging.php">
                            <i class='bx bx-mail-send'></i>
                            <span class="text">Messaging</span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class='bx bx-dots-horizontal-rounded'></i>
                            <span class="text">More</span>
                            <i class='bx bx-chevron-down'></i>
                        </a>
                        <ul class="sub-menu">
                            <li>
                                <a href="/frontend-admin-mns/components/more/stats.php">
                                    <i class='bx bx-line-chart'></i>
                                    <span class="text">Stats</span>
                                </a>
                            </li>
                            <li>
                                <a href="/frontend-admin-mns/components/more/settings.php">
                                    <i class='bx bx-cog'></i>
                                    <span class="text">Settings (Admin)</span>
                                </a>
                            </li>
                        </ul>
                    </li>
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
        localStorage.removeItem("token");
        window.location.href = "/frontend-admin-mns/views/login";
    }
</script>