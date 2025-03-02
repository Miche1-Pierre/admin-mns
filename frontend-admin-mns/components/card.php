<?php
function generateCard($title, $link, $text = null, $img = null, $chartId = null, $chartType = null, $labels = [], $datasets = [], $options = [])
{
    if (!isset($datasets[0]['label'])) {
        $datasets = [[
            "label" => $title,
            "data" => $datasets,
            "backgroundColor" => "#2B2D42",
            "borderColor" => "#eeeeee",
            "borderWidth" => 1
        ]];
    }
?>
    <div class="card">
        <div class="card-container" id="card-container">
            <h2><?= htmlspecialchars($title) ?></h2>

            <?php if ($text): ?>
                <p><?= $text ?></p>
            <?php endif; ?>

            <?php if ($img): ?>
                <img src="<?= htmlspecialchars($img) ?>" alt="Thumbnail">
            <?php endif; ?>

            <?php if ($chartId): ?>
                <canvas id="<?= htmlspecialchars($chartId) ?>"></canvas>
                <script>
                    window.chartData = window.chartData || [];
                    window.chartData.push({
                        title: <?= json_encode($title) ?>,
                        chartId: <?= json_encode($chartId) ?>,
                        chartType: <?= json_encode($chartType) ?>,
                        labels: <?= json_encode($labels) ?>,
                        datasets: <?= json_encode($datasets) ?>,
                        options: <?= json_encode($options) ?>
                    });
                </script>
            <?php endif; ?>

            <div class="buttons">
                <a href="<?= htmlspecialchars($link) ?>" class="button primary">View More</a>
            </div>
        </div>
    </div>
<?php
}
?>