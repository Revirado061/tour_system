<template>
  <div>
    <div class="header">
      <el-row>
      <span style="font-size: 30px;align-content: center;margin-top: 5px;margin-bottom: 15px;font-family: '晴圆等宽'">新增个人日记</span>
      <el-button type="success" class="submit"  @click="newDiary(title,spot,text,userName)">提交</el-button>
      </el-row>
      <el-row>
        <el-form label-width="100px">
          <el-col :span="10">
            <el-form-item label="标题">
              <el-input  placeholder="请输入日记标题" v-model="title"></el-input>
            </el-form-item>
          </el-col>
            <el-col :span="8">
              <el-form-item label="游学地">
                <el-autocomplete
                    v-model="spot"
                    :fetch-suggestions="querySearchAsync"
                    placeholder="请输入游学地"
                    @select="handleSelect"
                    style="width: 200px;float: left"
                ></el-autocomplete>
              </el-form-item>
            </el-col>
        </el-form>
      </el-row>
    </div>
    <div class="main">
      <v-md-editor v-model="text" height="600px"></v-md-editor>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      title:'',
      spot:'',
      author:'',
      text:'',
      input: '',
      userName:'',
      restaurants: [],
      timeout:  null,
    };
  },
  created() {
    this.fetchUserInfo(); // 在组件创建时获取用户信息
  },
  mounted() {
    this.loadAll();
  },
  methods: {
    fetchUserInfo() {
      // 从sessionStorage中获取用户信息
      const userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
      if (userInfo && userInfo.uname) {
        this.userName = userInfo.uname;  // 设置用户名
      }
    },
    newDiary(title,spot,text,author) {
      axios.post('/api/diary/write',null,{
        params: { title: title, spot: spot,text:text,author:author}
      }).then(response => {
        // 触发事件，携带更新后的日记数据
        this.$bus.$emit('diary-updated', response.data.data);
      })
      this.$notify({
        title: '成功',
        message: '你已成功提交日记',
        type: 'success'
      });
      setTimeout(() => {
        this.$router.push('/diaryplan/alldiary'); // 切换到日记列表界面
      }, 2500); // 等待3秒后跳转
    },
    loadAll() {
      axios.get('/api/spot/showAll')
          .then(response => {
            // 假设 response.data.data 是一个包含多个对象的数组
            this.restaurants = response.data.data.map(item => {
              return { value: item.name };  // 只保留 name 属性，并重命名为 value
            });
          })
    },
    querySearchAsync(queryString, cb) {
      var restaurants = this.restaurants;
      var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;

      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        cb(results);
      }, 500 * Math.random());
    },
    createStateFilter(queryString) {
      return (state) => {
        return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item) {
      console.log(item);
    }

  }
};
</script>

<style scoped>
v-md-edtior {
  margin: 40px;
}
.submit{
  float: right;
  margin-right: 10px;
  margin-bottom: 10px;
  margin-top: 10px;
}
</style>
