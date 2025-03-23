<template>
  <div>
    <div class="top-panel">
      <el-form label-width="50px">
        <el-row>
          <el-col :span="5">
            <el-form-item label="地点">
              <el-autocomplete
                  v-model="spotStr"
                  :fetch-suggestions="querySearchAsync"
                  placeholder="请输入游学目的地"
                  @select="handleSelect"
                  style="width: 200px;float: left"
              ></el-autocomplete>
<!--              <el-input placeholder="请输入游学目的地" v-model="spotStr"></el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="标题">
              <el-input placeholder="请输入日记标题" v-model="titleStr"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="分类">
              <el-select v-model="value" placeholder="请选择">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="2">
            <el-form-item>
              <el-button type="success" @click="searchDiary(spotStr,titleStr,value)">搜索</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="2">
            <el-form-item>
              <el-button type="success" @click="newDiary">新增个人日记</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="5" style="float: right">
            <el-form-item>
              <span class="amountStyle">日记总数: {{ amount }}</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="main-panel">
      <el-table
          :data="tableData"
          height="1000"
          border
          style="width: 100%">
        <el-table-column
            prop="index"
            label="排名"
            width="60">
        </el-table-column>
        <el-table-column
            prop="title"
            label="日记名称"
            width="400">
        </el-table-column>
        <el-table-column
            prop="spot"
            label="游学地"
            width="200">
        </el-table-column>
        <el-table-column
            prop="heat"
            label="热度">
        </el-table-column>
        <el-table-column
            prop="grade"
            label="评分">
        </el-table-column>
        <el-table-column
            prop="author"
            label="作者">
        </el-table-column>
        <el-table-column
            prop="date"
            label="日期">
        </el-table-column>
        <el-table-column
            label="查看日记内容">
          <template v-slot:default="scope">
            <el-button type="primary" @click="changeToDiary(scope.row)">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "AllDiary",
  mounted() {
    this.fetchData();
    this.loadAll();
    // 监听日记更新事件
    this.$bus.$on('diary-updated', this.handleDiaryUpdated);
  },
  data() {
    return {
      restaurants: [],
      timeout:  null,
      amount:0,
      options: [{
        value: 'heat',
        label: '热度'
      }, {
        value: 'grade',
        label: '评价'
      }],
      spotStr:'',
      titleStr:'',
      value: '',
      tableData: [],
      nextDiary: ''
    }
  },
  methods: {
    newDiary() {
      this.$router.push("/diaryplan/mydiary");
    },
    fetchData() {
      axios.get('/api/diary/heat')
          .then(response => {
            this.tableData = response.data.data.map((item, index) => {
              return { ...item, index: index + 1 }
            }) // 给每个数据加上排名
          })
      axios.get('/api/diary/number')
          .then(response => {
            this.amount = response.data.data
          })
    },
    handleDiaryUpdated(updatedDiary) {
      // 查找并更新日记数据
      const index = this.tableData.findIndex(diary => diary.id === updatedDiary.id);
      if (index !== -1) {
        this.$set(this.tableData, index, updatedDiary);
      }
      axios.get('/api/diary/number')
          .then(response => {
            this.amount = response.data.data
          })
    },
    searchDiary(spot,title, value) {
      console.log(spot)
      console.log(title)
      if(spot === '' && title === ''){
        if(value === 'heat'){
          axios.get('/api/diary/heat')  //获取数据
              .then(response => {
                this.tableData = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
        else if(value === 'grade'){
          axios.get('/api/diary/grade')  //获取数据
              .then(response => {
                this.tableData = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
      }
      else if(spot !== '' && title === ''){
        if(value === 'heat'){
          axios.get('/api/diary/search/spotOrTitle',{
            params:{choice:'spot', keyword:spot, rule:'heat'}
          })  //获取数据
              .then(response => {
                this.tableData= response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
        else if(value === 'grade'){
          axios.get('/api/diary/search/spotOrTitle',{
            params:{choice:'spot', keyword:spot, rule:'grade'}
          })  //获取数据
              .then(response => {
                this.tableData = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }

      }
      else if(spot === '' && title !== '') {
        if(value === 'heat'){
          axios.get('/api/diary/search/spotOrTitle',{
            params:{choice:'title', keyword:title, rule:'heat'}
          })  //获取数据
              .then(response => {
                this.tableData = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
        else if(value === 'grade'){
          axios.get('/api/diary/search/spotOrTitle',{
            params:{choice:'title', keyword:title, rule:'grade'}
          })  //获取数据
              .then(response => {
                this.tableData = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
      }
      else{
        if(value === 'grade'){
          axios.get('/api/diary/search/spotAndTitle',{
            params:{spotKeyword:spot, titleKeyword:title, rule:'grade'}
          })  //获取数据
              .then(response => {
                this.tableData = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
        else if(value === 'heat'){
          axios.get('/api/diary/search/spotAndTitle',{
            params:{spotKeyword:spot, titleKeyword:title, rule:'heat'}
          })  //获取数据
              .then(response => {
                this.tableData = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
      }
    },
    changeToDiary(row) {
      axios.post('/api/diary/click',null,{params:{id:row.id}})
          .then(response => {
            console.log(response.data.data.grade);
            // 更新表格的数据
            this.tableData = this.tableData.map(item => {
              if (item.id === row.id) {
                return { ...item, heat: response.data.data.heat };
              }
              return item;
            });
          })
            // 处理增加热度后的逻辑，例如更新前端显示的热度值)
      this.$router.push({
        path: "/diaryplan/diarytext",
        query: {
          id:row.id
        }
      })
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
}
</script>

<style scoped>
.amountStyle{
  font-size: 20px;

}
/* 样式 */
</style>

