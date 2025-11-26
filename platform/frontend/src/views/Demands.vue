<template>
  <div class="page">
    <h2>需求大厅</h2>

    <el-button type="primary" @click="$router.push('/demands/publish')">
      发布需求
    </el-button>

    <el-table :data="filteredData" style="margin-top:20px;">
      <el-table-column prop="title" label="需求标题" />
      <el-table-column prop="company" label="发布企业" />
      <el-table-column prop="time" label="发布时间" />
      <el-table-column prop="status" label="状态" />
    </el-table>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRoute } from 'vue-router'
import axios from '../api/axios'

const route = useRoute()
const allData = ref([])

onMounted(async () => {
  try {
    const res = await axios.get('/api/demand/list')
    if (Array.isArray(res.data)) {
      allData.value = res.data
    } else if (res.data && res.data.success) {
      allData.value = res.data.data || []
    } else if (res.data && res.data.data) {
      allData.value = res.data.data || []
    } else {
      console.error('获取需求列表返回未知格式：', res)
      allData.value = []
    }
  } catch (error) {
    console.error('获取需求列表失败:', error)
    allData.value = []
  }
})

const query = computed(() => (route.query.q || '').toString().trim().toLowerCase())

const filteredData = computed(() => {
  if (!query.value) return allData.value
  return allData.value.filter((item) => {
    const title = (item.title || '').toString().toLowerCase()
    const company = (item.company || '').toString().toLowerCase()
    const desc = (item.description || '').toString().toLowerCase()
    return title.includes(query.value) || company.includes(query.value) || desc.includes(query.value)
  })
})
</script>
