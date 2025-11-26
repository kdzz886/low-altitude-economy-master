<template>
  <div style="padding: 20px; background: #f5f7fa; min-height: 100vh;">
    <!-- 头部区域 -->
    <div style="background: linear-gradient(135deg, #0a1929 0%, #1e3a5f 100%); padding: 30px; border-radius: 12px; margin-bottom: 30px; box-shadow: 0 4px 20px rgba(0, 191, 255, 0.2);">
      <div style="display: flex; justify-content: space-between; align-items: center;">
        <div>
          <h1 style="margin: 0; color: white; font-size: 28px; display: flex; align-items: center; gap: 10px;">
            🚁 需求大厅
          </h1>
          <p style="margin: 10px 0 0 0; color: #b3e5fc; font-size: 14px;">浏览行业需求，发现商机</p>
        </div>
        <el-button type="primary" size="large" @click="$router.push('/demands/publish')" style="background: linear-gradient(135deg, #00bfff 0%, #1e90ff 100%); border: none; padding: 12px 32px; font-size: 16px; box-shadow: 0 4px 15px rgba(0, 191, 255, 0.4);">
          ➕ 发布需求
        </el-button>
      </div>
    </div>

    <!-- 搜索和筛选区域 -->
    <el-card shadow="hover" style="margin-bottom: 20px;">
      <el-row :gutter="20">
        <el-col :xs="24" :sm="12" :md="8">
          <el-input v-model="searchKeyword" placeholder="搜索需求标题、企业名称..." clearable prefix-icon="Search" @input="handleSearch" />
        </el-col>
        <el-col :xs="24" :sm="12" :md="8">
          <el-select v-model="statusFilter" placeholder="筛选状态" clearable style="width: 100%;">
            <el-option label="全部状态" value="" />
            <el-option label="待匹配" value="pending" />
            <el-option label="进行中" value="processing" />
            <el-option label="已完成" value="completed" />
          </el-select>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8">
          <el-select v-model="sortBy" placeholder="排序方式" style="width: 100%;">
            <el-option label="最新发布" value="time" />
            <el-option label="最多浏览" value="views" />
            <el-option label="截止时间" value="deadline" />
          </el-select>
        </el-col>
      </el-row>
    </el-card>

    <!-- 需求列表（卡片视图） -->
    <el-row :gutter="20">
      <el-col :xs="24" :sm="12" :lg="8" v-for="demand in filteredData" :key="demand.id" style="margin-bottom: 20px;">
        <el-card shadow="hover" class="demand-card" @click="viewDetail(demand.id)">
          <template #header>
            <div style="display: flex; justify-content: space-between; align-items: center;">
              <span style="font-weight: 600; font-size: 16px; color: #0d47a1;">{{ demand.title || '未命名需求' }}</span>
              <el-tag :type="getStatusType(demand.status)" size="small">{{ getStatusText(demand.status) }}</el-tag>
            </div>
          </template>
          <div>
            <p style="margin: 0 0 10px 0; color: #666; font-size: 14px; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden;">
              {{ demand.description || '暂无描述' }}
            </p>
            <div style="margin-top: 15px; padding-top: 15px; border-top: 1px solid #eee;">
              <div style="display: flex; justify-content: space-between; font-size: 13px; color: #999;">
                <span>🏢 {{ demand.company || '匿名企业' }}</span>
                <span>📅 {{ formatTime(demand.time) }}</span>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 空状态 -->
    <el-empty v-if="filteredData.length === 0" description="暂无需求数据" style="background: white; padding: 60px; border-radius: 12px;" />
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from '../api/axios'

const route = useRoute()
const router = useRouter()
const allData = ref([])
const searchKeyword = ref('')
const statusFilter = ref('')
const sortBy = ref('time')

onMounted(async () => {
  searchKeyword.value = route.query.q || ''
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

const handleSearch = () => {
  // 可以在这里添加搜索逻辑
}

const filteredData = computed(() => {
  let result = [...allData.value]
  
  // 关键词搜索
  if (searchKeyword.value) {
    const kw = searchKeyword.value.toLowerCase()
    result = result.filter((item) => {
      const title = (item.title || '').toString().toLowerCase()
      const company = (item.company || '').toString().toLowerCase()
      const desc = (item.description || '').toString().toLowerCase()
      return title.includes(kw) || company.includes(kw) || desc.includes(kw)
    })
  }
  
  // 状态筛选
  if (statusFilter.value) {
    result = result.filter(item => item.status === statusFilter.value)
  }
  
  return result
})

const getStatusType = (status) => {
  const map = {
    'pending': 'warning',
    'processing': 'primary',
    'completed': 'success'
  }
  return map[status] || 'info'
}

const getStatusText = (status) => {
  const map = {
    'pending': '待匹配',
    'processing': '进行中',
    'completed': '已完成'
  }
  return map[status] || '未知'
}

const formatTime = (time) => {
  if (!time) return '未知时间'
  return new Date(time).toLocaleDateString('zh-CN')
}

const viewDetail = (id) => {
  router.push(`/demands/${id}`)
}
</script>

<style scoped>
.demand-card {
  cursor: pointer;
  transition: all 0.3s;
  border: 1px solid rgba(0, 191, 255, 0.2);
}

.demand-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 25px rgba(0, 191, 255, 0.3) !important;
}
</style>
