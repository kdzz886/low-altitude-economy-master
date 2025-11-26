<template>
  <div class="login-container">
    <el-card class="card">
      <h2>用户登录</h2>
      <el-form :model="form" :rules="rules" ref="formRef" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" />
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" type="password" />
        </el-form-item>

        <el-button type="primary" @click="login">登录</el-button>
        <el-button link @click="$router.push('/register')">没有账号？去注册</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from '../api/axios'
import { useUserStore } from '../store/user'


const form = ref({ username: '', password: '' })
const formRef = ref()
const store = useUserStore()

const rules = {
  username: [{ required: true, message: '请输入用户名' }],
  password: [{ required: true, message: '请输入密码' }]
}

const login = () => {
  formRef.value.validate(async valid => {
    if (!valid) return
    try {
      const res = await axios.post('/api/user/login', form.value)
      if (res.data.success) {
        // 保存用户信息和 token（从后端返回的数据）
        const userData = res.data.data || {}
        store.setUser(String(userData.id || ''), form.value.username)
        window.location.href = '/'
      } else {
        alert(res.data.message || '登录失败')
      }
    } catch (error) {
      console.error('登录错误:', error)
      alert('登录失败，请检查网络连接')
    }
  })
}
</script>

<style>
.login-container {
  width: 400px;
  margin: 100px auto;
}
.card { padding: 20px; }
</style>
