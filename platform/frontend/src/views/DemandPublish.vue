<template>
  <div class="page">
    <h2>发布需求</h2>

    <el-form :model="form" label-width="100px">
      <el-form-item label="标题">
        <el-input v-model="form.title" placeholder="请输入需求标题" />
      </el-form-item>

      <el-form-item label="详细描述">
        <el-input v-model="form.description" type="textarea" rows="5" placeholder="请详细描述您的需求" />
      </el-form-item>

      <el-button type="primary" @click="submit">提交</el-button>
    </el-form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from '../api/axios'
import { useUserStore } from '../store/user'

const store = useUserStore()
const form = ref({ title: '', description: '', publisherId: parseInt(store.userId) || 0 })

const submit = async () => {
  if (!form.value.title) {
    alert('请输入标题')
    return
  }
  if (!form.value.description) {
    alert('请输入描述')
    return
  }
  if (!store.userId) {
    alert('请先登录')
    return
  }

  try {
    const res = await axios.post('/api/demand/publish', form.value)
    if (res.data.success) {
      alert('发布成功')
      window.location.href = '/demands'
    } else {
      alert(res.data.message || '发布失败')
    }
  } catch (error) {
    console.error('发布需求失败:', error)
    alert('发布失败，请检查网络连接')
  }
}
</script>
