<template>
  <div class="navbar-wrapper">
    <div class="logo-area" @click="$router.push('/')">
      <span class="logo-icon">✈️</span>
      <span class="logo-text">云翼互联</span>
    </div>
    
    <el-menu mode="horizontal" router class="navbar">
      <el-menu-item index="/">
        <span class="menu-text">🏢 首页</span>
      </el-menu-item>
      <el-menu-item index="/demands">
        <span class="menu-text">🚁 需求大厅</span>
      </el-menu-item>
      <el-menu-item index="/dashboard">
        <span class="menu-text">🛩️ 数据大屏</span>
      </el-menu-item>

    <div class="navbar-actions">
      <template v-if="!user.token">
        <el-button 
          class="login-btn" 
          @click="$router.push('/login')"
          size="default"
        >
          登录
        </el-button>
        <el-button 
          type="primary" 
          class="register-btn"
          @click="$router.push('/register')"
          size="default"
        >
          注册
        </el-button>
      </template>

      <template v-else>
        <el-button class="user-btn" @click="$router.push('/profile')">
          👤 {{ user.username }}
        </el-button>
        <el-dropdown>
          <span class="settings-icon">⚙️</span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="$router.push('/profile')">个人资料</el-dropdown-item>
              <el-dropdown-item divided @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </template>
    </div>
  </el-menu>
  </div>
</template>

<script setup>
import { useUserStore } from '../store/user'
const user = useUserStore()

const logout = () => {
  user.logout()
  window.location.href = '/'
}
</script>

<style scoped>
.navbar-wrapper {
  display: flex;
  align-items: center;
  background: linear-gradient(90deg, #0a1929 0%, #1a2f4a 50%, #0a1929 100%);
  box-shadow: 0 2px 12px rgba(0, 191, 255, 0.3);
  border-bottom: 2px solid rgba(0, 191, 255, 0.5);
  padding: 0 30px;
}

.navbar {
  flex: 1;
  background: transparent !important;
  border: none !important;
}

.logo-area {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-right: 40px;
  padding: 12px 0;
  cursor: pointer;
  transition: transform 0.3s;
}

.logo-area:hover {
  transform: scale(1.05);
}

.logo-icon {
  font-size: 24px;
  animation: float 3s ease-in-out infinite;
}

@keyframes float {
  0%, 100% { transform: translateY(0px); }
  50% { transform: translateY(-5px); }
}

.logo-text {
  font-size: 18px;
  font-weight: 700;
  background: linear-gradient(135deg, #00bfff 0%, #1e90ff 50%, #00bfff 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  letter-spacing: 1px;
}

.menu-text {
  font-size: 15px;
  font-weight: 500;
  color: #e3f2fd;
}

.navbar-actions {
  margin-left: auto;
  margin-right: 20px;
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 10px 0;
}

.login-btn {
  border: 2px solid #00bfff;
  color: #00bfff;
  background: rgba(0, 191, 255, 0.1);
  font-weight: 600;
  padding: 9px 28px;
  transition: all 0.3s;
  backdrop-filter: blur(10px);
}

.login-btn:hover {
  background: rgba(0, 191, 255, 0.2);
  border-color: #1e90ff;
  color: #1e90ff;
  box-shadow: 0 0 20px rgba(0, 191, 255, 0.4);
}

.register-btn {
  font-weight: 600;
  padding: 9px 28px;
  background: linear-gradient(135deg, #00bfff 0%, #1e90ff 50%, #4169e1 100%);
  border: none;
  box-shadow: 0 4px 15px rgba(0, 191, 255, 0.4);
  transition: all 0.3s;
  position: relative;
  overflow: hidden;
}

.register-btn::before {
  content: '';
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.3), transparent);
  transition: left 0.5s;
}

.register-btn:hover::before {
  left: 100%;
}

.register-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(0, 191, 255, 0.6);
}

.user-btn {
  border: 1px solid #e4e7ed;
  background: white;
  color: #606266;
  font-weight: 500;
  padding: 8px 20px;
  transition: all 0.3s;
}

.user-btn:hover {
  background: #f5f7fa;
  border-color: #c0c4cc;
}

.settings-icon {
  cursor: pointer;
  font-size: 18px;
  padding: 4px 8px;
  display: inline-block;
  transition: transform 0.3s;
}

.settings-icon:hover {
  transform: rotate(90deg);
}
</style>