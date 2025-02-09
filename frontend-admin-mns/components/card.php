<?php
function generateCard($title, $text, $img, $link)
{
?>
    <div class="card">
        <div class="card-container" id="card-container">
            <h2><?= htmlspecialchars($title) ?></h2>
            <p><?= htmlspecialchars($text) ?></p>
            <img src="<?= htmlspecialchars($img) ?>" alt="Thumbnail">
            <div class="buttons">
                <a href="<?= htmlspecialchars($link) ?>" target="_blank" class="button secondary">Test</a>
                <a href="" class="button primary">Test</a>
            </div>
        </div>
    </div>
<?php
}
?>