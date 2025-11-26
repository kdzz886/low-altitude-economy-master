<template>
  <el-menu mode="horizontal" router>
    <el-menu-item index="/">首页</el-menu-item>
    <el-menu-item index="/demands">需求大厅</el-menu-item>
    <el-menu-item index="/dashboard">数据大屏</el-menu-item>

    <div style="margin-left:auto; margin-right:20px; display:flex; align-items:center; gap:8px;">
      <template v-if="!user.token">
        <el-button text @click="$router.push('/login')">登录</el-button>
        <el-button text @click="$router.push('/register')">注册</el-button>
      </template>

      <template v-else>
        <el-button text @click="$router.push('/profile')">👤 {{ user.username }}</el-button>
        <el-dropdown>
          <span style="cursor: pointer; margin-left: 6px;">⚙️</span>
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
</template>

<script setup>
import { useUserStore } from '../store/user'
const user = useUserStore()

const logout = () => {
  user.logout()
  window.location.href = '/'
}
</script>
