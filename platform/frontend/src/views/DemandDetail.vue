<template>
  <div class="page">
    <h2>{{ detail.title }}</h2>
    <p>{{ detail.description }}</p>

    <h3 style="margin-top:40px;">智能匹配推荐企业</h3>

    <el-row :gutter="20">
      <el-col :span="8" v-for="c in matches" :key="c.id">
        <el-card>
          <h4>{{ c.name }}</h4>
          <p>{{ c.field }}</p>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from '../api/axios'

const route = useRoute()
const detail = ref({})
const matches = ref([])

onMounted(async () => {
  detail.value = await axios.get(`/demand/${route.params.id}`)
  matches.value = await axios.get(`/demand/${route.params.id}/matches`)
})
</script>
