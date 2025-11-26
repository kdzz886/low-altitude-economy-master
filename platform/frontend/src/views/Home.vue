<template>
  <div class="home" style="padding: 20px;">
    <!-- 搜索区域 -->
    <div style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); padding: 40px; border-radius: 8px; color: white; text-align: center;">
      <h1 style="margin-top: 0; font-size: 32px;">🚀 低空经济产业链需求协同平台</h1>
      <p style="font-size: 14px; opacity: 0.9; margin-bottom: 20px;">连接企业需求与解决方案，实现产业链高效协同</p>

      <div style="max-width: 600px; margin: 0 auto; display: flex; gap: 8px; justify-content: center;">
        <el-input
          v-model="keyword"
          placeholder="搜索需求、企业或服务…"
          clearable
          @keyup.enter="handleSearch"
          style="height: 40px; flex: 1;"
        />
        <el-button type="primary" @click="handleSearch" style="height: 40px;">搜索</el-button>
      </div>
    </div>

    <!-- 统计信息 -->
    <el-row :gutter="20" style="margin-top: 30px; margin-bottom: 40px;">
      <el-col :xs="24" :sm="12" :md="6">
        <div style="background: white; padding: 20px; border-radius: 8px; text-align: center; box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);">
          <div style="font-size: 28px; color: #667eea; font-weight: bold;">{{ totalDemands }}</div>
          <div style="color: #999; margin-top: 8px;">发布需求</div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6">
        <div style="background: white; padding: 20px; border-radius: 8px; text-align: center; box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);">
          <div style="font-size: 28px; color: #764ba2; font-weight: bold;">{{ totalEnterprises }}</div>
          <div style="color: #999; margin-top: 8px;">合作企业</div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6">
        <div style="background: white; padding: 20px; border-radius: 8px; text-align: center; box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);">
          <div style="font-size: 28px; color: #f56c6c; font-weight: bold;">{{ totalMatches }}</div>
          <div style="color: #999; margin-top: 8px;">成功匹配</div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6">
        <div style="background: white; padding: 20px; border-radius: 8px; text-align: center; box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);">
          <div style="font-size: 28px; color: #409eff; font-weight: bold;">{{ totalUsers }}</div>
          <div style="color: #999; margin-top: 8px;">注册用户</div>
        </div>
      </el-col>
    </el-row>

    <!-- 最新需求 -->
    <h2 style="margin-bottom: 20px; font-size: 20px; font-weight: bold; display: flex; align-items: center;">
      <span style="display: inline-block; width: 4px; height: 20px; background: #667eea; margin-right: 10px;"></span>
      最新需求
    </h2>
    
    <el-row :gutter="20">
      <el-col :xs="24" :sm="12" :md="8" v-for="demand in latestDemands" :key="demand.id" style="margin-bottom: 20px;">
        <DemandCard :demand="demand" />
      </el-col>
    </el-row>

    <div v-if="latestDemands.length === 0" style="text-align: center; padding: 40px; color: #999;">
      <p>暂无需求数据</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import axios from '../api/axios'
import DemandCard from '../components/DemandCard.vue'

const keyword = ref('')
const latestDemands = ref([])
const totalDemands = ref(0)
const totalEnterprises = ref(0)
const totalMatches = ref(0)
const totalUsers = ref(0)

onMounted(async () => {
  await fetchDemands()
  // 模拟统计数据
  totalDemands.value = latestDemands.value.length + Math.floor(Math.random() * 50)
  totalEnterprises.value = Math.floor(Math.random() * 200) + 50
  totalMatches.value = Math.floor(Math.random() * 100) + 20
  totalUsers.value = Math.floor(Math.random() * 500) + 100
})

const fetchDemands = async () => {
  try {
    const res = await axios.get('/api/demand/list')
    // 兼容后端直接返回数组或 { success, data } 格式
    if (Array.isArray(res.data)) {
      latestDemands.value = res.data.slice(0, 3)
    } else if (res.data && res.data.success) {
      latestDemands.value = (res.data.data || []).slice(0, 3)
    } else if (res.data && res.data.data) {
      latestDemands.value = (res.data.data || []).slice(0, 3)
    } else {
      console.error('获取需求列表返回未知格式：', res)
    }
  } catch (error) {
    console.error('获取需求列表失败:', error)
  }
}

const router = useRouter()

const handleSearch = async () => {
  const kw = keyword.value.trim()
  if (!kw) {
    ElMessage.warning('请输入搜索关键词')
    return
  }
  // 将搜索关键字传递到需求页，由需求页进行客户端过滤或后端搜索
  router.push({ path: '/demands', query: { q: kw } })
}
</script>
