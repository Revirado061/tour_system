<template>
  <div>
    <div class="image">
      <el-image :src="url" :preview-src-list="srcList"></el-image>
    </div>
    <div class="panel">
      <div class="setBorder">
        <p class="title">游学路线规划</p>
        <el-form :model="Form" ref="Form" label-width="100px" class="demo-dynamic">
          <el-form-item label="线路规划">
            <el-select v-model="option" filterable placeholder="请选择" style="float: left;width: 200px">
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
              prop="start"
              label="出发景点"
          >
            <el-autocomplete
                v-model="Form.start"
                :fetch-suggestions="querySearchAsync"
                placeholder="请输入出发景点"
                @select="handleSelect"
                style="width: 200px;float: left"
            ></el-autocomplete>
          </el-form-item>
          <el-form-item
              v-for="(domain, index) in Form.domains"
              :label="'目标景点' + (index+1)"
              :key="domain.key"
              :prop="'domains.' + index + '.value'"
          >
            <el-autocomplete
                v-model="domain.value"
                :fetch-suggestions="querySearchAsync"
                placeholder="请输入目标景点"
                @select="handleSelect"
                style="width: 200px;float: left;margin-right: 20px"
            ></el-autocomplete>
            <el-button @click.prevent="removeDomain(domain)" style="float: left;margin-left: 20px">删除</el-button>
          </el-form-item>
          <el-form-item>
            <el-card  v-if="showResult" class="box-card" style="float: left;width: 400px">
              <div class="text item">
                <p style="float: left;margin: 2px">游学路线规划为:</p>
                <p style="float: left;margin: 2px">{{ resultHandle }}</p>
              </div>
            </el-card>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm(Form,option)" style="float: left">提交</el-button>
            <el-button @click="addDomain" style="float: left">新增目标景点</el-button>
            <el-button @click="resetForm('Form')" style="float: left">重置</el-button>
          </el-form-item>
        </el-form>

      </div>
    </div>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: "RouterPlan",
  data() {
    return {
      restaurants: [],
      timeout:  null,
      dialogVisible: false,
      url: "/image/00.jpg",
      srcList:['/image/00.jpg'],
      routerplan: "这是路线规划",
      Form: {
        domains: [{
          value: ''
        }],
        start: ''
      },
      option:'',
      options: [
        {
          value: 'distance',
          label: '最短路径策略'
        }, {
          value: 'time',
          label: '最短时间策略'
        }
      ],
      value: '',
      showResult:false,
      result:[],
    }
  },
  computed:{
    resultHandle(){
      return this.result.map(item => item.name).join('->');
    }
  },
  methods:{
    submitForm(formName,option) {
      const terminal = formName.domains.map(item => item.value)
      this.showResult = true
      alert('submit!');
      if(option === 'distance') {
        axios.post('/api/graph/shortestLength',{start: formName.start, terminal:terminal})
            .then(response => {
              this.result = response.data.data
              console.log(this.result)
            })
      }
      else if(option === 'time') {
        axios.post('/api/graph/shortestTime',{start: formName.start, terminal: terminal})
            .then(response => {
              this.result = response.data.data
            })
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.showResult = false
    },
    removeDomain(item) {
      let index = this.Form.domains.indexOf(item)
      if (index !== -1 && index !== 0) {
        this.Form.domains.splice(index, 1)
      }
      if(index === 0){
        alert('目前只有一个目标景点，不能删除！！')
      }
    },
    addDomain() {
      this.Form.domains.push({
        value: '',
        key: Date.now()
      });
    },
    loadAll() {
      axios.get('/api/graph/allBuildings')
          .then(response => {
            // 假设 response.data.data 是一个包含多个对象的数组
            this.restaurants = response.data.data.map(item => {
              return { value: item.name };  // 只保留 name 属性，并重命名为 value
            });
            this.restaurants.shift();
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
  },
  mounted() {
    this.loadAll();
  }

}
</script>


<style scoped>
.image {
  float: left;
  width: 36%;
  margin-left: 35px;
  margin-right: 20px;
  margin-top: 0;
}
.panel {
  float: right;
  width: 50%;
  margin-top: 40px;
  margin-right: 60px;
}
.setBorder {
  border:1px solid #000;
  padding-top: 0;
}
.title {
  font-size: 40px;
  font-family: '晴圆等宽';
  margin: 15px;
}
</style>
