<?php
?>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/frontend-admin-mns/css/dashboard.css">
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <title>ADMIN MNS | Account</title>
</head>

<body>
    <header>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/navbar.php"; ?>
    </header>
    <main>
        <?php include $_SERVER['DOCUMENT_ROOT'] . "/frontend-admin-mns/components/breadcrumb.php"; ?>

        <div class="settings-zone" id="settings-zone">
            <h2>Account settings</h2>
            <form action="/frontend-admin-mns/actions/update_profile.php" method="POST" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="profile-photo">Profil picture</label>
                    <input type="file" id="profile-photo" name="profile-photo">
                </div>
                <div class="form-group">
                    <label for="username">User Name</label>
                    <input type="text" id="username" name="username" value="Pierre">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="pierre@example.com">
                </div>
                <button type="submit" class="btn">Apply changes</button>
            </form>
        </div>
    </main>
    <footer>
    </footer>
</body>

</html>
