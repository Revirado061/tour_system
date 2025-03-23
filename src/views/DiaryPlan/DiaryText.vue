<template>
  <div class="app-container">
    <div class="mainPanel">
      <el-container class="main-content">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span class="searchHandle">
              <el-button @click="handleReturn(post.grade,post.id)" style="float: right;margin: 10px" type="info" plain>返回</el-button>
              <el-input
                  class="searchInput"
                  placeholder="请输入查询内容"
                  v-model="searchText"
                  clearable
                  @input="handleSearch"
                  style="width: 250px; float: right; margin-right: 20px">
              </el-input>

            </span>
            <span class="head">{{ post.title }}</span>
            <span class="rating-container">
              <el-rate
                  v-model="post.grade"
                  :max="10"
                  allow-half>
              </el-rate>
              <span class="rating-num">{{ formattedGrade }}</span>
            </span>

          </div>
          <div>
            <span class="detail">作者: {{ post.author }}</span>
            <span class="detail">游学地: {{ post.spot }}</span>
            <p ref="content" style="text-align: left; line-height: 2;" class="formatted-content">{{ post.text }}</p>
          </div>
        </el-card>
      </el-container>
    </div>
  </div>
</template>

<script>
import Mark from 'mark.js';
import axios from 'axios';
export default {
  data() {
    return {
      searchText: '',
      post: {
        id: 1,
        title: "",
        author: "",
        spot: "",
        grade: 0, // 初始值设置为 0
        text: ``
      },
    };
  },
  computed: {
    formattedGrade() {
      return this.post.grade.toFixed(1);
    }
  },
  watch: {
    '$route.query': {
      handler: 'updatePostFromRoute',
      immediate: true
    }
  },
  methods: {
    updatePostFromRoute() {
      this.post.id = this.$route.query.id || 0;
      console.log(this.post.id)
      this.$nextTick(() => {
        this.post.grade = parseFloat(this.$route.query.grade) || 0; // 确保在 nextTick 之后更新 grade
      });
    },
    readDiary() {
      axios.post('/api/diary/read',null,{params:{id:this.post.id}})
          .then(response => {
            this.post = response.data.data;
          })
    },
    handleSearch() {
      const instance = new Mark(this.$refs.content);
      instance.unmark({
        done: () => {
          instance.mark(this.searchText);
        }
      });
    },
    // DiaryText 组件
    handleReturn(grade, id) {
      axios.post('/api/diary/star', null, { params: { id: id, star: grade } })
          .then(response => {
            // 触发事件，携带更新后的日记数据
            this.$bus.$emit('diary-updated', response.data.data);
            this.$router.push('/diaryplan/alldiary'); // 返回到日记列表页面
          })
          .catch(error => {
            console.error('Error updating grade:', error);
          });
    }

  },
  mounted() {
    this.updatePostFromRoute();
    this.readDiary();
  }
};
</script>

<style scoped>
/* 重置默认样式 */
html, body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}

.app-container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.mainPanel {
  flex-grow: 1;
  display: flex;
  flex-direction: column;
}

.main-content {
  flex-grow: 1;
  margin: 0;
  display: flex;
  flex-direction: column;
}

.box-card {
  flex-grow: 1;
  min-height: 100vh; /* 确保卡片组件至少充满屏幕高度 */
  overflow-y: auto; /* 仅垂直方向显示滚动条 */
  overflow-x: hidden; /* 水平方向隐藏滚动条 */
}

.detail {
  margin-right: 20px;
}

.rating-container {
  float: left;
  display: flex;
  align-items: center;
}

.rating-num {
  margin-left: 10px;
}

.formatted-content {
  word-wrap: break-word; /* 自动换行 */
  white-space: pre-wrap; /* 保留空白符和换行符 */
  text-indent: 2em; /* 首行缩进 */
  line-height: 1.6; /* 设置行高 */
}

.head {
  font-size: 40px;
  vertical-align: middle;
}
</style>

<!--<template>-->
<!--  <div class="app-container">-->
<!--    <div class="mainPanel">-->
<!--      <el-container class="main-content">-->
<!--        <el-card class="box-card">-->
<!--          <div slot="header" class="clearfix">-->
<!--            <div class="searchHandle">-->
<!--              <el-input-->
<!--                  class="searchInput"-->
<!--                  placeholder="请输入查询内容"-->
<!--                  v-model="searchText"-->
<!--                  clearable-->
<!--                  @input="handleSearch"-->
<!--                  style="width: 250px;float: right">-->
<!--              </el-input>-->
<!--            </div>-->
<!--            <span class="head">{{ post.title }}</span>-->
<!--            <div class="rating-container">-->
<!--              <el-rate-->
<!--                  v-model="post.grade"-->
<!--                  :max="10"-->
<!--                  allow-half-->
<!--                  @change="handleRatingChange">-->
<!--              </el-rate>-->
<!--              <span class="rating-num">{{ post.grade }}</span>-->
<!--            </div>-->
<!--          </div>-->
<!--          <div>-->
<!--            <span class="detail">作者: {{ post.author }}</span>-->
<!--            <span class="detail">游学地: {{ post.spot }}</span>-->
<!--            <p ref="content" style="text-align: left;line-height: 2" class="formatted-content">{{ post.text }}</p>-->
<!--          </div>-->
<!--        </el-card>-->
<!--      </el-container>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import Mark from 'mark.js';-->

<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      searchText: '',-->
<!--      post: {-->
<!--        title: "学习之旅：从东京到京都",-->
<!--        author: "Jane Doe",-->
<!--        spot: "日本，京都",-->
<!--        grade: 4.5,-->
<!--        text: `这是一篇关于在日本京都的游学体验的文章。这里的文化与教育给我留下了深刻的印象，我体验了传统茶道、参观了历史悠久的寺庙，并且与当地学者进行了深入的交流。这是一篇关于在日本京都的游学体验的文章。这里的文化与教育给我留下了深刻的印象，我体验了传统茶道、参观了历史悠久的寺庙，并且与当地学者进行了深入的交流。-->
<!--          这是一篇关于在日本京都的游学体验的文章。这里的文化与教育给我留下了深刻的印象，我体验了传统茶道、参观了历史悠久的寺庙，并且与当地学者进行了深入的交流。-->
<!--          这是一篇关于在日本京都的游学体验的文章。这里的文化与教育给我留下了深刻的印象，我体验了传统茶道、参观了历史悠久的寺庙，并且与当地学者进行了深入的交流。-->
<!--          这是一篇关于在日本京都的游学体验的文章。这里的文化与教育给我留下了深刻的印象，我体验了传统茶道、参观了历史悠久的寺庙，并且与当地学者进行了深入的交流。-->
<!--          这是一篇关于在日本京都的游学体验的文章。这里的文化与教育给我留下了深刻的印象，我体验了传统茶道、参观了历史悠久的寺庙，并且与当地学者进行了深入的交流。`-->
<!--      },-->
<!--    };-->
<!--  },-->
<!--  watch: {-->
<!--    '$route.query': {-->
<!--      handler: 'updatePostFromRoute',-->
<!--      immediate: true-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    updatePostFromRoute() {-->
<!--      this.post.title = this.$route.query.title || "默认标题";-->
<!--      this.post.author = this.$route.query.author || "默认作者";-->
<!--      this.post.spot = this.$route.query.spot || "默认位置";-->
<!--      this.post.grade = parseFloat(this.$route.query.grade) || 0;-->
<!--      this.post.text = this.$route.query.text || "默认内容";-->
<!--    },-->
<!--    handleSearch() {-->
<!--      const instance = new Mark(this.$refs.content);-->
<!--      instance.unmark({-->
<!--        done: () => {-->
<!--          instance.mark(this.searchText);-->
<!--        }-->
<!--      });-->
<!--    },-->
<!--    handleRatingChange(newRating) {-->
<!--      console.log("New rating:", newRating);-->
<!--      // 更新评分的代码，可以根据需要添加-->
<!--    }-->
<!--  },-->
<!--  mounted() {-->
<!--    this.updatePostFromRoute();-->
<!--  }-->
<!--};-->
<!--</script>-->

<!--<style>-->
<!--/* 重置默认样式 */-->
<!--html, body {-->
<!--  margin: 0;-->
<!--  padding: 0;-->
<!--  width: 100%;-->
<!--  height: 100%;-->
<!--}-->

<!--.app-container {-->
<!--  width: 100%;-->
<!--  height: 100%;-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--}-->

<!--.mainPanel {-->
<!--  flex-grow: 1;-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--}-->

<!--.main-content {-->
<!--  flex-grow: 1;-->
<!--  margin: 0;-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--}-->

<!--.box-card {-->
<!--  flex-grow: 1;-->
<!--  min-height: 100vh; /* 确保卡片组件至少充满屏幕高度 */-->
<!--  overflow-y: auto; /* 仅垂直方向显示滚动条 */-->
<!--  overflow-x: hidden; /* 水平方向隐藏滚动条 */-->
<!--}-->

<!--.detail {-->
<!--  margin-right: 20px;-->
<!--}-->

<!--.rating-container {-->
<!--  float: left;-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--}-->

<!--.rating-num {-->
<!--  margin-left: 10px;-->
<!--  -->
<!--}-->

<!--.formatted-content {-->
<!--  word-wrap: break-word; /* 自动换行 */-->
<!--  white-space: pre-wrap; /* 保留空白符和换行符 */-->
<!--  text-indent: 2em; /* 首行缩进 */-->
<!--  line-height: 1.6; /* 设置行高 */-->
<!--}-->

<!--.head {-->
<!--  font-size: 40px;-->
<!--  vertical-align: middle;-->
<!--}-->
<!--</style>-->

