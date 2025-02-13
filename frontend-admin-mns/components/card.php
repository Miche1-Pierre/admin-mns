<?php
function generateCard($title, $link, $text = null, $img = null, $chartId = null, $chartType = null, $labels = [], $data = [], $bgColor = [], $borderColor = [], $options = [])
{
?>
    <div class="card">
        <div class="card-container" id="card-container">
            <h2><?= htmlspecialchars($title) ?></h2>

            <?php if ($text): ?>
                <p><?= htmlspecialchars($text) ?></p>
            <?php endif; ?>

            <?php if ($img): ?>
                <img src="<?= htmlspecialchars($img) ?>" alt="Thumbnail">
            <?php endif; ?>

            <?php if ($chartId): ?>
                <canvas id="<?= htmlspecialchars($chartId) ?>"></canvas>
                <script>
                    window.chartData = window.chartData || [];
                    window.chartData.push({
                        chartId: <?= json_encode($chartId) ?>,
                        chartType: <?= json_encode($chartType) ?>,
                        labels: <?= json_encode($labels) ?>,
                        data: <?= json_encode($data) ?>,
                        bgColor: <?= json_encode($bgColor) ?>,
                        borderColor: <?= json_encode($borderColor) ?>,
                        options: <?= json_encode($options) ?>
                    });
                </script>
            <?php endif; ?>

            <div class="buttons">
                <a href="<?= htmlspecialchars($link) ?>" class="button primary">Voir Plus</a>
            </div>
        </div>
    </div>
<?php
}
?>