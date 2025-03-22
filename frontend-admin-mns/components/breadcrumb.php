<div class="breadcrumb">
    <div class="left">
        <h1 id="breadcrumb-title">Dashboard</h1>
        <ul id="breadcrumb-list">
            <li><a href="#"></a></li>
            <li><a href="#" class="active"></a></li>
        </ul>
    </div>
    <div class="profile">
        <div class="info">
            <p id="greeting">Hello <span id="user-firstname">User</span> !</p>
            <small class="text-muted" id="user-role">User</small>
        </div>
        <div class="profile-photo">
            <a class="button view"><img src="/frontend-admin-mns/assets/user1.png"></a>
        </div>
    </div>

    <!-- Popup pour visualiser le compte -->
    <div id="viewAccountModal" class="modal">
        <div class="modal-content">
            <span class="close-btn"><i class='bx bx-x'></i></span>
            <h2>Compte</h2>

            <!-- Formulaire de mise à jour du profil -->
            <form id="updateProfileForm">
                <label for="photo">Photo de Profil</label>
                <input type="file" id="photo" name="photo">

                <label for="utilisateur">Nom</label>
                <input type="text" id="utilisateur" name="utilisateur">

                <label for="email">Email</label>
                <input type="email" id="email" name="email">

                <button type="submit" class="button">Mettre à jour le profil</button>
            </form>
            <br/>

            <!-- Formulaire de changement de mot de passe -->
            <h3>Changer le mot de passe</h3>
            <form id="changePasswordForm">
                <label for="newPassword">Nouveau mot de passe</label>
                <input type="password" id="newPassword" name="newPassword">

                <label for="confirmNewPassword">Confirmer le nouveau mot de passe</label>
                <input type="password" id="confirmNewPassword" name="confirmNewPassword">

                <button type="submit" class="button">Changer le mot de passe</button>
            </form>
        </div>
    </div>
</div>