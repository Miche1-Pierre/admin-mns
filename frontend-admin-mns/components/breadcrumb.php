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
            <p>Hello Pierre !</p>
            <small class="text-muted">Admin</small>
        </div>
        <div class="profile-photo">
            <a class="button view"><img src="/frontend-admin-mns/assets/user1.png"></a>
        </div>
    </div>

    <!-- Popup pour visualiser le compte -->
    <div id="viewAccountModal" class="modal">
        <div class="modal-content">
            <span class="close-btn"><i class='bx bx-x'></i></span>
            <h2>Account</h2>
            <form id="changeAccountForm">
                <label for="photo">Profile picture</label>
                <input type="file" id="photo" name="photo">

                <label for="utilisateur">Name</label>
                <input type="text" id="utilisateur" name="utilisateur">

                <label for="email">Email</label>
                <input type="email" id="email" name="email">

                <button type="submit" class="button">Apply changes</button>
            </form>
        </div>
    </div>
</div>