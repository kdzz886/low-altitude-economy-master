<template>
  <div class="register-container">
    <!-- 背景装饰 -->
    <div class="background-decoration">
      <div class="plane plane-1">🛸</div>
      <div class="plane plane-2">✈️</div>
      <div class="plane plane-3">🛩️</div>
    </div>

    <!-- 左侧宣传区 -->
    <div class="left-section">
      <div class="brand-content">
        <div class="brand-icon">🚁</div>
        <h1 class="brand-title">加入云翼互联</h1>
        <p class="brand-subtitle">开启低空经济协同新篇章</p>
        <div class="features">
          <div class="feature-item">
            <span class="feature-icon">🚀</span>
            <div>
              <div class="feature-title">快速入驻</div>
              <div class="feature-desc">简单几步即可完成注册</div>
            </div>
          </div>
          <div class="feature-item">
            <span class="feature-icon">🤝</span>
            <div>
              <div class="feature-title">精准匹配</div>
              <div class="feature-desc">智能推荐合适的合作伙伴</div>
            </div>
          </div>
          <div class="feature-item">
            <span class="feature-icon">📊</span>
            <div>
              <div class="feature-title">数据透明</div>
              <div class="feature-desc">实时掌握行业动态与趋势</div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 右侧注册表单 -->
    <div class="right-section">
      <el-card class="register-card">
        <div class="card-header">
          <h2 class="card-title">创建账户</h2>
          <p class="card-subtitle">填写以下信息开始使用</p>
        </div>

        <el-form :model="form" :rules="rules" ref="formRef" class="register-form">
          <el-form-item prop="username">
            <el-input 
              v-model="form.username" 
              placeholder="请输入用户名"
              size="large"
              prefix-icon="User"
              clearable
            />
          </el-form-item>

          <el-form-item prop="email">
            <el-input 
              v-model="form.email" 
              placeholder="请输入邮箱"
              size="large"
              prefix-icon="Message"
              clearable
            />
          </el-form-item>

          <el-form-item prop="password">
            <el-input 
              v-model="form.password" 
              type="password"
              placeholder="请输入密码（至少6位）"
              size="large"
              prefix-icon="Lock"
              show-password
            />
          </el-form-item>

          <el-form-item prop="confirmPassword">
            <el-input 
              v-model="form.confirmPassword" 
              type="password"
              placeholder="请确认密码"
              size="large"
              prefix-icon="Lock"
              show-password
            />
          </el-form-item>

          <div class="agreement">
            <el-checkbox v-model="agreed">
              我已阅读并同意
              <el-link type="primary" :underline="false">《用户协议》</el-link>
              和
              <el-link type="primary" :underline="false">《隐私政策》</el-link>
            </el-checkbox>
          </div>

          <el-button 
            type="primary" 
            size="large" 
            class="register-btn"
            @click="submitForm"
            :loading="loading"
            :disabled="!agreed"
          >
            立即注册
          </el-button>

          <div class="login-link">
            已有账户？
            <el-link type="primary" @click="goLogin" :underline="false">
              直接登录
            </el-link>
          </div>
        </el-form>
      </el-card>
    </div>
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
const loading = ref(false);
const agreed = ref(false);

const rules = {
  username: [
    { required: true, message: "请输入用户名", trigger: "blur" },
    { min: 3, max: 20, message: "用户名长度在3-20个字符", trigger: "blur" }
  ],
  email: [
    { required: true, message: "请输入邮箱", trigger: "blur" },
    { type: "email", message: "请输入正确的邮箱格式", trigger: "blur" },
  ],
  password: [
    { required: true, message: "请输入密码", trigger: "blur" },
    { min: 6, message: "密码长度不少于6位", trigger: "blur" }
  ],
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
  if (!agreed.value) {
    ElMessage.warning("请阅读并同意用户协议和隐私政策");
    return;
  }

  formRef.value.validate(async (valid) => {
    if (!valid) return;

    loading.value = true;
    try {
      const res = await axios.post("/api/user/register", form.value);

      if (res.data.success) {
        ElMessage.success("注册成功，即将跳转到登录页...");
        setTimeout(() => {
          router.push("/login");
        }, 1500);
      } else {
        ElMessage.error(res.data.message || "注册失败");
      }
    } catch (error) {
      console.error("注册错误:", error);
      ElMessage.error("注册失败，请检查网络连接");
    } finally {
      loading.value = false;
    }
  });
};

const goLogin = () => {
  router.push("/login");
};
</script>

<style scoped>
.register-container {
  width: 100vw;
  height: 100vh;
  display: flex;
  position: relative;
  overflow: hidden;
  background: linear-gradient(135deg, #0d2847 0%, #1e3a5f 50%, #0a1929 100%);
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
  top: 15%;
  left: 15%;
  animation-delay: 0s;
}

.plane-2 {
  top: 65%;
  left: 75%;
  animation-delay: 7s;
}

.plane-3 {
  top: 45%;
  left: 35%;
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
  margin: 0 0 50px 0;
  color: #b3e5fc;
  opacity: 0.95;
}

.features {
  display: flex;
  flex-direction: column;
  gap: 25px;
}

.feature-item {
  display: flex;
  align-items: flex-start;
  gap: 15px;
  color: #e3f2fd;
}

.feature-icon {
  font-size: 32px;
  flex-shrink: 0;
}

.feature-title {
  font-size: 18px;
  font-weight: 600;
  margin-bottom: 5px;
}

.feature-desc {
  font-size: 14px;
  opacity: 0.8;
}

.right-section {
  width: 520px;
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

.register-card {
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

.register-form {
  margin-top: 30px;
}

.register-form :deep(.el-form-item) {
  margin-bottom: 20px;
}

.register-form :deep(.el-input__wrapper) {
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 191, 255, 0.1);
}

.register-form :deep(.el-input__wrapper:hover) {
  box-shadow: 0 2px 12px rgba(0, 191, 255, 0.2);
}

.agreement {
  margin-bottom: 25px;
  font-size: 13px;
  color: #666;
}

.register-btn {
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

.register-btn:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(0, 191, 255, 0.5);
}

.register-btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.login-link {
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
