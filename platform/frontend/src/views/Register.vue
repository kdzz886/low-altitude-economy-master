<template>
  <div class="register-container">
    <el-card class="register-card">
      <h2 class="title">注册账号</h2>

      <el-form :model="form" :rules="rules" ref="formRef" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>

        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>

        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input v-model="form.confirmPassword" type="password" placeholder="请再次输入密码"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm">注册</el-button>
          <el-button @click="goLogin">返回登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import axios from "@/api/axios";
import { useRouter } from "vue-router";

const router = useRouter();

const form = ref({
  username: "",
  email: "",
  password: "",
  confirmPassword: "",
});

const formRef = ref();

const rules = {
  username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
  email: [
    { required: true, message: "请输入邮箱", trigger: "blur" },
    { type: "email", message: "请输入正确的邮箱格式", trigger: "blur" },
  ],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
  confirmPassword: [
    { required: true, message: "请确认密码", trigger: "blur" },
    {
      validator(_, value, callback) {
        if (value !== form.value.password) {
          callback(new Error("两次密码不一致"));
        } else callback();
      },
      trigger: "blur",
    },
  ],
};

const submitForm = () => {
  formRef.value.validate(async (valid) => {
    if (!valid) return;

    try {
      const res = await axios.post("/api/user/register", form.value);

      if (res.data.success) {
        ElMessage.success("注册成功，请登录！");
        router.push("/login");
      } else {
        ElMessage.error(res.data.message || "注册失败");
      }
    } catch (error) {
      console.error("注册错误:", error);
      ElMessage.error("注册失败，请检查网络连接");
    }
  });
};

const goLogin = () => {
  router.push("/login");
};
</script>

<style scoped>
.register-container {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.register-card {
  width: 400px;
  padding: 20px;
}

.title {
  text-align: center;
  margin-bottom: 20px;
}
</style>
