<template>
  <div style="padding: 20px; background: linear-gradient(to bottom, #0a1929 0%, #f5f7fa 300px); min-height: 100vh;">
    <div style="background: linear-gradient(135deg, #0a1929 0%, #1e3a5f 100%); padding: 30px; border-radius: 12px; margin-bottom: 30px; box-shadow: 0 8px 32px rgba(0, 191, 255, 0.3); border: 1px solid rgba(0, 191, 255, 0.3);">
      <h1 style="margin: 0; font-size: 32px; color: white; display: flex; align-items: center; gap: 12px; text-shadow: 0 2px 10px rgba(0, 191, 255, 0.5);">
        🛩️ 低空经济数据大屏
      </h1>
      <p style="margin: 10px 0 0 0; color: #b3e5fc; font-size: 14px;">实时监控产业链动态与市场趋势</p>
    </div>

    <!-- 关键指标卡片 -->
    <el-row :gutter="20" style="margin-bottom: 30px;">
      <el-col :xs="24" :sm="12" :md="6" v-for="(stat, index) in stats" :key="index">
        <div class="stat-card" :style="{background: stat.gradient, borderColor: stat.borderColor}">
          <div style="font-size: 13px; color: rgba(255,255,255,0.8); margin-bottom: 12px; font-weight: 500;">{{ stat.icon }} {{ stat.label }}</div>
          <div style="font-size: 36px; font-weight: bold; color: white; text-shadow: 0 2px 8px rgba(0,0,0,0.3);">{{ stat.value }}</div>
          <div style="font-size: 12px; color: rgba(255,255,255,0.7); margin-top: 10px;">📈 {{ stat.trend }}</div>
        </div>
      </el-col>
    </el-row>

    <!-- 图表区域 -->
    <el-row :gutter="20">
      <el-col :xs="24" :md="12" style="margin-bottom: 20px;">
        <div class="chart-card">
          <h3 class="chart-title">📍 企业地域分布</h3>
          <div id="map" style="height: 300px;"></div>
        </div>
      </el-col>
      
      <el-col :xs="24" :md="12" style="margin-bottom: 20px;">
        <div class="chart-card">
          <h3 class="chart-title">🍰 产业链企业占比</h3>
          <div id="pie" style="height: 300px;"></div>
        </div>
      </el-col>
    </el-row>

    <!-- 趋势图 -->
    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :xs="24">
        <div class="chart-card">
          <h3 class="chart-title">📊 近30天需求趋势</h3>
          <div id="line" style="height: 300px;"></div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import * as echarts from 'echarts'
import { onMounted, ref } from 'vue'

const stats = ref([
  { 
    label: '总企业数', 
    value: 287, 
    trend: '环比增长 12%', 
    icon: '🏢',
    gradient: 'linear-gradient(135deg, #1976d2 0%, #1565c0 100%)',
    borderColor: 'rgba(25, 118, 210, 0.5)'
  },
  { 
    label: '总需求数', 
    value: 1523, 
    trend: '环比增长 8%', 
    icon: '🚁',
    gradient: 'linear-gradient(135deg, #0288d1 0%, #01579b 100%)',
    borderColor: 'rgba(2, 136, 209, 0.5)'
  },
  { 
    label: '成功匹配', 
    value: 356, 
    trend: '环比增长 15%', 
    icon: '🤝',
    gradient: 'linear-gradient(135deg, #0097a7 0%, #006064 100%)',
    borderColor: 'rgba(0, 151, 167, 0.5)'
  },
  { 
    label: '平均响应时间', 
    value: '2.5h', 
    trend: '环比下降 8%', 
    icon: '⚡',
    gradient: 'linear-gradient(135deg, #00acc1 0%, #00838f 100%)',
    borderColor: 'rgba(0, 172, 193, 0.5)'
  }
])

onMounted(() => {
  // 企业地域分布 - 柱状图
  const mapChart = echarts.init(document.getElementById('map'))
  mapChart.setOption({
    tooltip: { trigger: 'axis' },
    grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
    xAxis: { 
      type: 'category', 
      data: ['华东', '华北', '华南', '华中', '西部', '东北']
    },
    yAxis: { type: 'value' },
    series: [{ 
      type: 'bar', 
      data: [120, 95, 85, 70, 55, 45],
      itemStyle: { 
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          { offset: 0, color: '#1e90ff' },
          { offset: 1, color: '#00bfff' }
        ])
      },
      barWidth: '50%'
    }]
  })

  // 产业链企业占比 - 饼图
  const pieChart = echarts.init(document.getElementById('pie'))
  pieChart.setOption({
    tooltip: { trigger: 'item' },
    legend: { orient: 'vertical', left: 'left' },
    series: [
      {
        type: 'pie',
        radius: '50%',
        data: [
          { value: 320, name: '无人机整机制造', itemStyle: { color: '#1976d2' } },
          { value: 280, name: '零部件配套', itemStyle: { color: '#0288d1' } },
          { value: 200, name: '服务与应用', itemStyle: { color: '#0097a7' } },
          { value: 150, name: '物流与运输', itemStyle: { color: '#00acc1' } },
          { value: 120, name: '其他', itemStyle: { color: '#26c6da' } }
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  })

  // 近30天需求趋势 - 折线图
  const lineChart = echarts.init(document.getElementById('line'))
  const days = Array.from({length: 30}, (_, i) => `${i+1}日`)
  const data = Array.from({length: 30}, () => Math.floor(Math.random() * 100) + 50)
  
  lineChart.setOption({
    tooltip: { trigger: 'axis' },
    grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
    xAxis: { 
      type: 'category', 
      data: days,
      boundaryGap: false
    },
    yAxis: { type: 'value' },
    series: [{ 
      type: 'line', 
      data: data,
      smooth: true,
      itemStyle: { color: '#1e90ff' },
      lineStyle: { width: 3 },
      areaStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          { offset: 0, color: 'rgba(30, 144, 255, 0.4)' },
          { offset: 1, color: 'rgba(0, 191, 255, 0.1)' }
        ])
      }
    }]
  })

  // 窗口调整时重新绘制
  window.addEventListener('resize', () => {
    mapChart.resize()
    pieChart.resize()
    lineChart.resize()
  })
})
</script>

<style scoped>
.stat-card {
  padding: 25px;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0, 191, 255, 0.2);
  border: 1px solid;
  transition: all 0.3s;
  cursor: pointer;
}

.stat-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 30px rgba(0, 191, 255, 0.4);
}

.chart-card {
  background: white;
  padding: 25px;
  border-radius: 12px;
  box-shadow: 0 4px 15px rgba(0, 191, 255, 0.15);
  border: 1px solid rgba(0, 191, 255, 0.2);
  transition: all 0.3s;
}

.chart-card:hover {
  box-shadow: 0 6px 25px rgba(0, 191, 255, 0.25);
}

.chart-title {
  margin: 0 0 20px 0;
  font-size: 17px;
  font-weight: bold;
  color: #0d47a1;
  padding-bottom: 10px;
  border-bottom: 2px solid rgba(0, 191, 255, 0.3);
}
</style>
