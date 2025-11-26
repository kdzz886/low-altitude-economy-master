<template>
  <div class="home" style="padding: 20px;">
    <!-- 轮播图区域 -->
    <el-carousel height="400px" style="margin-bottom: 30px; border-radius: 12px; overflow: hidden; box-shadow: 0 8px 32px rgba(0, 191, 255, 0.2);">
      <el-carousel-item v-for="(item, index) in carouselItems" :key="index">
        <div :style="{
          height: '100%',
          background: item.gradient,
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          alignItems: 'center',
          color: 'white',
          position: 'relative',
          overflow: 'hidden'
        }">
          <div style="position: absolute; top: 10%; right: 5%; font-size: 120px; opacity: 0.1;">{{ item.icon }}</div>
          <h2 style="font-size: 42px; margin: 0; text-shadow: 0 4px 15px rgba(0,0,0,0.3); z-index: 1;">{{ item.title }}</h2>
          <p style="font-size: 18px; margin-top: 20px; opacity: 0.95; z-index: 1;">{{ item.description }}</p>
        </div>
      </el-carousel-item>
    </el-carousel>

    <!-- 搜索区域 -->
    <div style="background: linear-gradient(135deg, #0a1929 0%, #1e3a5f 50%, #0d2847 100%); padding: 50px; border-radius: 12px; color: white; text-align: center; box-shadow: 0 8px 32px rgba(0, 191, 255, 0.2); border: 1px solid rgba(0, 191, 255, 0.3); position: relative; overflow: hidden;">
      <div style="position: absolute; top: 10%; right: 5%; font-size: 80px; opacity: 0.1;">🛸</div>
      <div style="position: absolute; bottom: 15%; left: 8%; font-size: 60px; opacity: 0.1;">✈️</div>
      <h1 style="margin-top: 0; font-size: 36px; font-weight: 700; text-shadow: 0 2px 10px rgba(0, 191, 255, 0.5);">🛩️ 低空经济产业链需求协同平台</h1>
      <p style="font-size: 16px; opacity: 0.95; margin-bottom: 25px; color: #b3e5fc;">连接低空产业需求与创新方案 • 驱动空中经济高效协同</p>

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
        <div style="background: linear-gradient(135deg, #e3f2fd 0%, #bbdefb 100%); padding: 24px; border-radius: 12px; text-align: center; box-shadow: 0 4px 15px rgba(33, 150, 243, 0.2); border: 1px solid rgba(33, 150, 243, 0.3); transition: transform 0.3s;" onmouseover="this.style.transform='translateY(-5px)'" onmouseout="this.style.transform='translateY(0)'">
          <div style="font-size: 32px; color: #1976d2; font-weight: bold; text-shadow: 0 2px 4px rgba(25, 118, 210, 0.2);">{{ totalDemands }}</div>
          <div style="color: #1565c0; margin-top: 10px; font-weight: 500;">🚁 发布需求</div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6">
        <div style="background: linear-gradient(135deg, #e1f5fe 0%, #b3e5fc 100%); padding: 24px; border-radius: 12px; text-align: center; box-shadow: 0 4px 15px rgba(3, 169, 244, 0.2); border: 1px solid rgba(3, 169, 244, 0.3); transition: transform 0.3s;" onmouseover="this.style.transform='translateY(-5px)'" onmouseout="this.style.transform='translateY(0)'">
          <div style="font-size: 32px; color: #0288d1; font-weight: bold; text-shadow: 0 2px 4px rgba(2, 136, 209, 0.2);">{{ totalEnterprises }}</div>
          <div style="color: #01579b; margin-top: 10px; font-weight: 500;">🏭 合作企业</div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6">
        <div style="background: linear-gradient(135deg, #e0f7fa 0%, #b2ebf2 100%); padding: 24px; border-radius: 12px; text-align: center; box-shadow: 0 4px 15px rgba(0, 188, 212, 0.2); border: 1px solid rgba(0, 188, 212, 0.3); transition: transform 0.3s;" onmouseover="this.style.transform='translateY(-5px)'" onmouseout="this.style.transform='translateY(0)'">
          <div style="font-size: 32px; color: #0097a7; font-weight: bold; text-shadow: 0 2px 4px rgba(0, 151, 167, 0.2);">{{ totalMatches }}</div>
          <div style="color: #006064; margin-top: 10px; font-weight: 500;">🤝 成功匹配</div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6">
        <div style="background: linear-gradient(135deg, #e8f5e9 0%, #c8e6c9 100%); padding: 24px; border-radius: 12px; text-align: center; box-shadow: 0 4px 15px rgba(76, 175, 80, 0.2); border: 1px solid rgba(76, 175, 80, 0.3); transition: transform 0.3s;" onmouseover="this.style.transform='translateY(-5px)'" onmouseout="this.style.transform='translateY(0)'">
          <div style="font-size: 32px; color: #388e3c; font-weight: bold; text-shadow: 0 2px 4px rgba(56, 142, 60, 0.2);">{{ totalUsers }}</div>
          <div style="color: #2e7d32; margin-top: 10px; font-weight: 500;">👥 注册用户</div>
        </div>
      </el-col>
    </el-row>

    <!-- 最新需求 -->
    <h2 style="margin-bottom: 20px; font-size: 22px; font-weight: bold; display: flex; align-items: center; color: #0d47a1;">
      <span style="display: inline-block; width: 5px; height: 24px; background: linear-gradient(180deg, #00bfff 0%, #1e90ff 100%); margin-right: 12px; border-radius: 2px; box-shadow: 0 0 10px rgba(0, 191, 255, 0.5);"></span>
      🛸 最新需求动态
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

const carouselItems = ref([
  {
    title: '🚁 低空经济新时代',
    description: '连接产业链上下游，构建高效协同生态',
    gradient: 'linear-gradient(135deg, #0a1929 0%, #1e3a5f 50%, #0d2847 100%)',
    icon: '🛸'
  },
  {
    title: '✈️ 智能需求匹配',
    description: 'AI驱动精准对接，让合作更高效',
    gradient: 'linear-gradient(135deg, #1e3a5f 0%, #2c5f8d 50%, #1e3a5f 100%)',
    icon: '🛩️'
  },
  {
    title: '🌐 产业数据大屏',
    description: '实时监控行业动态，把握市场脉搏',
    gradient: 'linear-gradient(135deg, #0d2847 0%, #1a4d6f 50%, #0d2847 100%)',
    icon: '📊'
  }
])

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
