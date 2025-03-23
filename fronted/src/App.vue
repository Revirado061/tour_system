<template>
  <div class="container">
    <header class="header">
      <h1 class="logo"><i class="fas fa-water"></i>NeuroWarp</h1>
      <p>TextSeal智能多比特文本水印系统</p>
    </header>

    <!-- Tab controls -->
    <div class="tab-control">
      <button
        class="tab-btn"
        :class="{ active: activeTab === 'watermark' }"
        @click="switchTab('watermark')"
      >
        <i class="fas fa-fingerprint"></i>嵌入水印
      </button>
      <button
        class="tab-btn"
        :class="{ active: activeTab === 'check' }"
        @click="switchTab('check')"
      >
        <i class="fas fa-search"></i>水印检测
      </button>
    </div>

    <!-- 嵌入水印模块 -->
    <div v-if="activeTab === 'watermark'" class="card-container">
      <div class="card">
        <h2><i class="fas fa-lock"></i> 版权保护模式</h2>
        <textarea v-model="originalText" placeholder="请输入需要添加水印的原始文本..."></textarea>
        <button class="btn" @click="generateWatermark">
          <i class="fas fa-shield-alt"></i>生成安全水印
        </button>
        <div v-if="encryptedResult" class="result-box">
          <h3>加密水印标识符</h3>
          <div class="status-indicator success">
            <i class="fas fa-check-circle"></i>水印嵌入成功
          </div>
          <pre>{{ encryptedResult }}</pre>
        </div>
      </div>
    </div>

    <!-- 水印检测模块 -->
    <div v-if="activeTab === 'check'" class="card-container">
      <div class="card">
        <h2><i class="fas fa-search-lock"></i> 版权检测模式</h2>
        <textarea v-model="checkText" placeholder="请输入需要检测的文本内容..."></textarea>
        <input
          type="text"
          v-model="identifier"
          class="result-box"
          placeholder="在此粘贴加密标识符..."
        />
        <button class="btn" @click="checkWatermark">
          <i class="fas fa-user-secret"></i>执行版权验证
        </button>
        <div v-if="checkResult" class="result-box">
          <div class="status-indicator success">
            <i class="fas fa-check-circle"></i>水印验证通过
          </div>
          <p>位置序列一致性: <span v-html="consistencyStatus"></span></p>
          <h4>解密位置序列:</h4>
          <pre>{{ positionOutput }}</pre>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeTab: "watermark",  // 当前激活的Tab，默认是"watermark"
      originalText: "",        // 原始文本，用于嵌入水印
      checkText: "",           // 用于水印验证的文本
      identifier: "",          // 加密标识符，用于验证水印
      encryptedResult: null,   // 存储加密的水印标识符
      checkResult: null,       // 存储水印检测的结果
      consistencyStatus: "",   // 存储水印一致性检查的结果
      positionOutput: "",      // 存储解密的水印位置序列
    };
  },
  methods: {
    // 切换Tab
    switchTab(tabId) {
      this.activeTab = tabId;
    },

    // 生成水印
    async generateWatermark() {
      try {
        const response = await fetch("http://10.110.15.79:5000/api/watermark/generate", {
          method: "POST",
          headers: {"Content-Type": "application/json"},
          body: JSON.stringify({text: this.originalText}),
          mode: "cors",
        });
        if (!response.ok) throw new Error("请求失败");
        const data = await response.json();
        this.encryptedResult = data.identifier;
      } catch (error) {
        this.encryptedResult = `Error: ${error.message}`;
      }
    },

    // 检查水印
    async checkWatermark() {
      try {
        const response = await fetch("http://10.110.15.79:5000/api/watermark/check", {
          method: "POST",
          headers: {"Content-Type": "application/json"},
          body: JSON.stringify({text: this.checkText, identifier: this.identifier}),
          mode: "cors",
        });
        if (!response.ok) throw new Error("请求失败");
        const data = await response.json();
        this.checkResult = data;
        this.consistencyStatus = data.is_consistent
            ? '<span style="color: #4ade80">✓ 一致</span>'
            : '<span style="color: #f87171">✗ 不一致</span>';
        this.positionOutput = data.positions;
      } catch (error) {
        this.checkResult = {error: `Error: ${error.message}`};
      }
    },
  },

  // 在组件加载时自动选择“嵌入水印”Tab
  mounted() {
    this.switchTab("watermark");
  },
};
</script>
<style>
/* 全局样式 */
body {
  background-color: #000000;  /* 设置背景为黑色 */
  color: #f8fafc;  /* 确保文字颜色与背景有足够对比 */
  font-family: "Segoe UI", system-ui;
  line-height: 1.6;
  min-height: 100vh;
  padding: 2rem;
}
</style>

<style scoped>
/* 这里包含了样式，与上面提供的相同 */
:root {
  --primary: #7c3aed;
  --background: #0f172a;
  --surface: #1e293b;
  --on-surface: #f8fafc;
}
page {
  background: #0f172a;
}
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
.container {
  max-width: 1200px;
  margin: 0 auto;
}

.header {
  text-align: center;
  margin-bottom: 3rem;
}

.logo {
  font-size: 2.5rem;
  background: linear-gradient(45deg, #7c3aed, #a855f7);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.card-container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(400px, 1fr));
  gap: 2rem;
  backdrop-filter: blur(10px);
}

.card {
  background: rgba(30, 41, 59, 0.7);
  border-radius: 1rem;
  padding: 2rem;
  border: 1px solid rgba(255, 255, 255, 0.1);
  transition: transform 0.3s ease;
}

.card:hover {
  transform: translateY(-5px);
}

.tab-control {
  display: flex;
  gap: 1rem;
  margin-bottom: 2rem;
}

.tab-btn {
  padding: 0.8rem 1.5rem;
  border: none;
  border-radius: 0.5rem;
  background: var(--surface);
  color: var(--on-surface);
  cursor: pointer;
  transition: all 0.3s ease;
}

.tab-btn.active {
  background: var(--primary);
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
}

textarea {
  width: 100%;
  background: var(--surface);
  border: 1px solid rgba(255, 255, 255, 0.1);
  color: var(--on-surface);
  padding: 1rem;
  border-radius: 0.5rem;
  margin: 1rem 0;
  min-height: 200px;
  resize: vertical;
}

.btn {
  background: var(--primary);
  color: white;
  padding: 0.8rem 1.5rem;
  border: none;
  border-radius: 0.5rem;
  cursor: pointer;
  transition: transform 0.2s ease;
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.btn:hover {
  transform: scale(1.05);
}

.result-box {
  background: var(--surface);
  padding: 1rem;
  border-radius: 0.5rem;
  margin-top: 1rem;
  word-break: break-all;
}

.status-indicator {
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.5rem 1rem;
  border-radius: 2rem;
}

.success {
  background: rgba(74, 222, 128, 0.2);
  color: #4ade80;
}

.error {
  background: rgba(248, 113, 113, 0.2);
  color: #f87171;
}
</style>
