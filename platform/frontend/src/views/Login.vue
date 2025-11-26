<template>
  <div class="login-container">
    <!-- 背景装饰 -->
    <div class="background-decoration">
      <div class="plane plane-1">✈️</div>
      <div class="plane plane-2">🛸</div>
      <div class="plane plane-3">🛩️</div>
    </div>

    <!-- 左侧宣传区 -->
    <div class="left-section">
      <div class="brand-content">
        <div class="brand-icon">✈️</div>
        <h1 class="brand-title">云翼互联</h1>
        <p class="brand-subtitle">低空经济产业链协同平台</p>
        <div class="features">
          <div class="feature-item">
            <span class="feature-icon">✅</span>
            <span>智能需求匹配</span>
          </div>
          <div class="feature-item">
            <span class="feature-icon">✅</span>
            <span>实时数据分析</span>
          </div>
          <div class="feature-item">
            <span class="feature-icon">✅</span>
            <span>高效协同生态</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 右侧登录表单 -->
    <div class="right-section">
      <el-card class="login-card">
        <div class="card-header">
          <h2 class="card-title">欢迎回来</h2>
          <p class="card-subtitle">登录您的账户继续使用</p>
        </div>

        <el-form :model="form" :rules="rules" ref="formRef" class="login-form">
          <el-form-item prop="username">
            <el-input 
              v-model="form.username" 
              placeholder="请输入用户名"
              size="large"
              prefix-icon="User"
              clearable
            />
          </el-form-item>

          <el-form-item prop="password">
            <el-input 
              v-model="form.password" 
              type="password"
              placeholder="请输入密码"
              size="large"
              prefix-icon="Lock"
              show-password
            />
          </el-form-item>

          <div class="form-footer">
            <el-checkbox>7天内免登录</el-checkbox>
            <el-link type="primary" :underline="false">忘记密码？</el-link>
          </div>

          <el-button 
            type="primary" 
            size="large" 
            class="login-btn"
            @click="login"
            :loading="loading"
          >
            登录
          </el-button>

          <div class="register-link">
            还没有账户？
            <el-link type="primary" @click="$router.push('/register')" :underline="false">
              立即注册
            </el-link>
          </div>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import axios from '../api/axios'
import { useUserStore } from '../store/user'

const form = ref({ username: '', password: '' })
const formRef = ref()
const store = useUserStore()
const loading = ref(false)

const rules = {
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
}

const login = () => {
  formRef.value.validate(async valid => {
    if (!valid) return
    loading.value = true
    try {
      const res = await axios.post('/api/user/login', form.value)
      if (res.data.success) {
        const userData = res.data.data || {}
        store.setUser(String(userData.id || ''), form.value.username)
        ElMessage.success('登录成功！')
        setTimeout(() => {
          window.location.href = '/'
        }, 500)
      } else {
        ElMessage.error(res.data.message || '登录失败')
      }
    } catch (error) {
      console.error('登录错误:', error)
      ElMessage.error('登录失败，请检查网络连接')
    } finally {
      loading.value = false
    }
  })
}
</script>

<style scoped>
.login-container {
  width: 100vw;
  height: 100vh;
  display: flex;
  position: relative;
  overflow: hidden;
  background: linear-gradient(135deg, #0a1929 0%, #1e3a5f 50%, #0d2847 100%);
}

.background-decoration {
  position: absolute;
  width: 100%;
  height: 100%;
  overflow: hidden;
  pointer-events: none;
}

.plane {
  position: absolute;
  font-size: 60px;
  opacity: 0.1;
  animation: float-plane 20s infinite;
}

.plane-1 {
  top: 10%;
  left: 10%;
  animation-delay: 0s;
}

.plane-2 {
  top: 60%;
  left: 70%;
  animation-delay: 7s;
}

.plane-3 {
  top: 40%;
  left: 30%;
  animation-delay: 14s;
}

@keyframes float-plane {
  0%, 100% { transform: translate(0, 0) rotate(0deg); }
  25% { transform: translate(50px, -30px) rotate(5deg); }
  50% { transform: translate(100px, 20px) rotate(-5deg); }
  75% { transform: translate(50px, 50px) rotate(3deg); }
}

.left-section {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 60px;
  position: relative;
  z-index: 1;
}

.brand-content {
  max-width: 500px;
  color: white;
}

.brand-icon {
  font-size: 80px;
  margin-bottom: 20px;
  animation: float 3s ease-in-out infinite;
}

.brand-title {
  font-size: 52px;
  font-weight: 700;
  margin: 0 0 15px 0;
  background: linear-gradient(135deg, #00bfff 0%, #1e90ff 50%, #00bfff 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.brand-subtitle {
  font-size: 20px;
  margin: 0 0 40px 0;
  color: #b3e5fc;
  opacity: 0.95;
}

.features {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.feature-item {
  display: flex;
  align-items: center;
  gap: 12px;
  font-size: 16px;
  color: #e3f2fd;
}

.feature-icon {
  font-size: 20px;
}

.right-section {
  width: 500px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px;
  background: rgba(255, 255, 255, 0.03);
  backdrop-filter: blur(10px);
  border-left: 1px solid rgba(0, 191, 255, 0.2);
  position: relative;
  z-index: 1;
}

.login-card {
  width: 100%;
  padding: 40px;
  background: rgba(255, 255, 255, 0.98);
  border-radius: 16px;
  box-shadow: 0 8px 32px rgba(0, 191, 255, 0.3);
  border: 1px solid rgba(0, 191, 255, 0.2);
}

.card-header {
  text-align: center;
  margin-bottom: 35px;
}

.card-title {
  font-size: 28px;
  font-weight: 700;
  color: #0d47a1;
  margin: 0 0 10px 0;
}

.card-subtitle {
  font-size: 14px;
  color: #666;
  margin: 0;
}

.login-form {
  margin-top: 30px;
}

.login-form :deep(.el-input__wrapper) {
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 191, 255, 0.1);
}

.login-form :deep(.el-input__wrapper:hover) {
  box-shadow: 0 2px 12px rgba(0, 191, 255, 0.2);
}

.form-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 25px;
  font-size: 14px;
}

.login-btn {
  width: 100%;
  height: 45px;
  font-size: 16px;
  font-weight: 600;
  background: linear-gradient(135deg, #00bfff 0%, #1e90ff 100%);
  border: none;
  border-radius: 8px;
  box-shadow: 0 4px 15px rgba(0, 191, 255, 0.4);
  transition: all 0.3s;
}

.login-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(0, 191, 255, 0.5);
}

.register-link {
  text-align: center;
  margin-top: 25px;
  font-size: 14px;
  color: #666;
}

@keyframes float {
  0%, 100% { transform: translateY(0px); }
  50% { transform: translateY(-10px); }
}

@media (max-width: 768px) {
  .left-section {
    display: none;
  }
  
  .right-section {
    width: 100%;
    border-left: none;
  }
}
</style>
