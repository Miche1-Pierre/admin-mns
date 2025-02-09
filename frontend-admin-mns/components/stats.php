<?php
function generateStats($title, $text, $link, $chartId, $chartData, $chartType = 'bar')
{
?>
    <div class="stats">
        <div class="stats-container" id="stats-container">
            <h2><?= htmlspecialchars($title) ?></h2>
            <p><?= htmlspecialchars($text) ?></p>
            <canvas id="<?= htmlspecialchars($chartId) ?>"
                data-chart='<?= json_encode(["type" => $chartType, "data" => $chartData], JSON_NUMERIC_CHECK) ?>'>
            </canvas>
            <div class="buttons">
                <a href="<?= htmlspecialchars($link) ?>" target="_blank" class="button secondary">Test</a>
            </div>
        </div>
    </div>
<?php
}
?>