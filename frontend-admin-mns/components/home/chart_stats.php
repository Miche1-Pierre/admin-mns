<?php
function generateChart($chartId, $title, $type, $labels, $data, $bgColor, $borderColor, $options = [])
{
?>
    <div class="card">
        <h2><?= htmlspecialchars($title) ?></h2>
        <canvas id="<?= htmlspecialchars($chartId) ?>"></canvas>
    </div>
<?php
}
?>