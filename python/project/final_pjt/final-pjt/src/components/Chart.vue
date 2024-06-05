<template>
  <div>
    <canvas ref="chartCanvas"></canvas>
    <div class="label-list">
      <div v-for="(item, index) in props.chartData" :key="index">
        <strong>{{ index + 1 }}</strong>: {{ item.product.fin_prdt_nm }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { Chart } from 'chart.js/auto';

const props = defineProps({
  chartData: {
    type: Array,
    required: true,
  },
});

const chartCanvas = ref(null);
let chartInstance = null;

const renderChart = () => {
  if (chartInstance) {
    chartInstance.destroy();
  }
  const ctx = chartCanvas.value.getContext('2d');

  const labels = props.chartData.map((_, index) => index + 1);
  const data = {
    labels: labels,
    datasets: [
      {
        label: '기본 금리',
        data: props.chartData.map(item => item.intr_rate),
        backgroundColor: 'rgba(128, 128, 128, 0.5)', // 회색
      },
      {
        label: '최고 금리',
        data: props.chartData.map(item => item.intr_rate2),
        backgroundColor: 'rgba(255, 0, 0, 0.5)', // 빨간색
      },
    ],
  };

  const options = {
    responsive: true,
    scales: {
      y: {
        beginAtZero: true,
      },
    },
  };

  chartInstance = new Chart(ctx, {
    type: 'bar',
    data: data,
    options: options,
  });
};

onMounted(() => {
  renderChart();
});

watch(() => props.chartData, () => {
  renderChart();
});
</script>

<style scoped>
canvas {
  max-width: 100%;
  height: auto;
}

.label-list {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  align-items: start;
}

.label-list div {
  margin-bottom: 5px;
}
</style>
