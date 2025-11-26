<template>
  <div style="padding: 20px;">
    <h2>个人资料</h2>
    <el-card style="max-width:600px;">
      <div style="display:flex; justify-content:space-between; align-items:center;">
        <div>
          <div style="font-weight: bold; font-size: 18px;">{{ user.username || '未登录' }}</div>
          <div style="color:#666; margin-top:8px;">ID: {{ user.userId || '-' }}</div>
          <div style="color:#666; margin-top:6px;">邮箱: {{ user.email || '-' }}</div>
        </div>
        <el-button type="primary" @click="editProfile">编辑资料</el-button>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { useUserStore } from '../store/user'
import { useRouter } from 'vue-router'
const user = useUserStore()
const router = useRouter()

const editProfile = () => {
  router.push('/profile/edit')
}
</script>
