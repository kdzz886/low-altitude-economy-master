<template>
  <el-card style="height: 100%; cursor: pointer; transition: all 0.3s;" @mouseenter="isHover = true" @mouseleave="isHover = false" :class="{hovering: isHover}">
    <template #header>
      <div style="display: flex; justify-content: space-between; align-items: center;">
        <span style="font-weight: bold; color: #333;">{{ demand.title }}</span>
        <el-tag type="info" effect="light">{{ demand.status === 'pending' ? '待处理' : '已匹配' }}</el-tag>
      </div>
    </template>
    
    <p style="color: #666; margin: 10px 0; line-height: 1.6; text-overflow: ellipsis; overflow: hidden; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical;">
      {{ demand.description || '暂无描述' }}
    </p>
    
    <div style="margin-top: 15px; padding-top: 15px; border-top: 1px solid #eee; display: flex; justify-content: space-between; align-items: center;">
      <span style="font-size: 12px; color: #999;">ID: {{ demand.id }}</span>
      <el-button type="primary" link size="small">查看详情 →</el-button>
    </div>
  </el-card>
</template>

<script setup>
import { ref } from 'vue'

const props = defineProps({
  demand: {
    type: Object,
    default: () => ({
      id: 1,
      title: '需求标题',
      description: '这里是需求的简介',
      status: 'pending'
    })
  }
})

const isHover = ref(false)
</script>

<style scoped>
:deep(.el-card) {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

:deep(.el-card.hovering) {
  box-shadow: 0 4px 20px 0 rgba(0, 0, 0, 0.15);
  transform: translateY(-5px);
}
</style>
