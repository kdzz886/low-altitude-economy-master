<template>
  <div style="padding: 20px; background: #f5f7fa;">
    <h1 style="margin-bottom: 30px; font-size: 28px; color: #333; display: flex; align-items: center;">
      <span style="display: inline-block; width: 4px; height: 28px; background: #667eea; margin-right: 10px;"></span>
      企业数据可视化大屏
    </h1>

    <!-- 关键指标卡片 -->
    <el-row :gutter="20" style="margin-bottom: 30px;">
      <el-col :xs="24" :sm="12" :md="6" v-for="(stat, index) in stats" :key="index">
        <div style="background: white; padding: 25px; border-radius: 8px; box-shadow: 0 2px 12px rgba(0,0,0,0.1); border-left: 4px solid;" :style="{borderLeftColor: stat.color}">
          <div style="font-size: 12px; color: #999; margin-bottom: 8px;">{{ stat.label }}</div>
          <div style="font-size: 32px; font-weight: bold; color: #333;">{{ stat.value }}</div>
          <div style="font-size: 12px; color: #999; margin-top: 8px;">📈 {{ stat.trend }}</div>
        </div>
      </el-col>
    </el-row>

    <!-- 图表区域 -->
    <el-row :gutter="20">
      <el-col :xs="24" :md="12" style="margin-bottom: 20px;">
        <div style="background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 12px rgba(0,0,0,0.1);">
          <h3 style="margin: 0 0 20px 0; font-size: 16px; font-weight: bold;">📍 企业地域分布</h3>
          <div id="map" style="height: 300px;"></div>
        </div>
      </el-col>
      
      <el-col :xs="24" :md="12" style="margin-bottom: 20px;">
        <div style="background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 12px rgba(0,0,0,0.1);">
          <h3 style="margin: 0 0 20px 0; font-size: 16px; font-weight: bold;">🍰 产业链企业占比</h3>
          <div id="pie" style="height: 300px;"></div>
        </div>
      </el-col>
    </el-row>

    <!-- 趋势图 -->
    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :xs="24">
        <div style="background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 12px rgba(0,0,0,0.1);">
          <h3 style="margin: 0 0 20px 0; font-size: 16px; font-weight: bold;">📊 近30天需求趋势</h3>
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
  { label: '总企业数', value: 287, trend: '环比增长 12%', color: '#667eea' },
  { label: '总需求数', value: 1523, trend: '环比增长 8%', color: '#764ba2' },
  { label: '成功匹配', value: 356, trend: '环比增长 15%', color: '#f56c6c' },
  { label: '平均响应时间', value: '2.5h', trend: '环比下降 8%', color: '#409eff' }
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
      itemStyle: { color: '#667eea' },
      smooth: true
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
          { value: 320, name: '无人机整机制造', itemStyle: { color: '#667eea' } },
          { value: 280, name: '零部件配套', itemStyle: { color: '#764ba2' } },
          { value: 200, name: '服务与应用', itemStyle: { color: '#f56c6c' } },
          { value: 150, name: '物流与运输', itemStyle: { color: '#409eff' } },
          { value: 120, name: '其他', itemStyle: { color: '#85ce61' } }
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
      itemStyle: { color: '#667eea' },
      areaStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          { offset: 0, color: 'rgba(102, 126, 234, 0.3)' },
          { offset: 1, color: 'rgba(102, 126, 234, 0.1)' }
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
