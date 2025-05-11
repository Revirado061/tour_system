<template>
  <div>
    <div class="top-panel">
      <el-form label-width="50px" style="">
        <el-row>
          <el-col :span="5">
            <el-form-item label="地点">
              <el-autocomplete
                  v-model="nameStr"
                  :fetch-suggestions="querySearchAsync"
                  placeholder="请输入名称"
                  @select="handleSelect"
                  style="width: 200px;float: left"
              ></el-autocomplete>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="类别">
              <el-select v-model="typeStr" filterable placeholder="请选择">
                <el-option
                    v-for="item in typeOptions"
                    :key="item"
                    :label="item"
                    :value="item">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="排序">
              <el-select v-model="value" placeholder="请选择排序方式">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
            <el-col :span="8">
              <el-form-item style="width: 450px;float: left">
                <div class="button-group" >
                  <el-button type="success" @click="SearchHandle(value,nameStr,typeStr)">搜索</el-button>
                  <el-button type="success" @click="showAll">显示所有数据</el-button>
                  <el-button type="success" @click="fetchDataSystem">显示系统默认排序</el-button>
                </div>
              </el-form-item>
            </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="main-panel">
      <el-table
          :data="items"
          max-height="600"
          border
          @row-click="handleRowClick"
          style="width: 100%">
        <el-table-column
            prop="index"
            label="排名"
            min-width="10%"
            align="center">
        </el-table-column>
        <el-table-column
            prop="name"
            label="游学地"
            min-width="40%"
            align="center">
        </el-table-column>
        <el-table-column
            prop="heat"
            label="热度"
            min-width="15%"
            align="center">
        </el-table-column>
        <el-table-column
            prop="grade"
            label="评分"
            min-width="15%"
            align="center">
        </el-table-column>
        <el-table-column
            width="150px"
            prop="type"
            label="地点类别"
            align="center"
            :filters="categoryFilters"
            :filter-method="filterCategory"
            filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag
                :type="tagColor(scope.row.type)"
                disable-transitions>{{scope.row.type}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
            label="查看美食推荐"
            width="140px">
            <el-button type="warning" plain @click="changeToFood">查看详情</el-button>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>


<script>
import axios from "axios";
export default {
  mounted() {
    this.fetchData();
    this.loadAll();
  },
  data() {
    return {
      value:'',
      filterDate: [], // 筛选的动态数据
      restaurants: [],
      timeout:  null,
      items: [],
      options:[
          {label:'热度',value:'heat'},
          {label:'评分',value:'grade'}],
      typeOptions:['全部','自然景观','人文景观','历史文化','主题公园','红色革命','展览展馆'],
      nameStr:'',
      typeStr:'',
      categoryFilters:[
        {text:'自然景观',value:'自然景观'},
        {text:'人文景观',value:'人文景观'},
        {text:'历史文化',value:'历史文化'},
        {text:'主题公园',value:'主题公园'},
        {text:'红色革命',value:'红色革命'},
        {text:'展览展馆',value:'展览展馆'},
      ]
    }
  },
  methods: {
    fetchData() {
      axios.get('/api/spot/sortByHeat')
          .then(response => {
            this.items = response.data.data.map((item,index) => {
              return{...item,index:index + 1}
            });  // 给每个数据增加index属性，且从1开始
          })
    },
    fetchDataSystem() {
        axios.get('/api/spot/sortByHeat')
            .then(response => {
              this.items = response.data.data.map((item,index) => {
                return{...item,index:index + 1}
              });  // 给每个数据增加index属性，且从1开始
            })
      },
    showAll() {
      axios.get('/api/spot/showAll')
          .then(response => {
            this.items = response.data.data.map((item, index) => {
              return {...item, index: index + 1}
            });  // 给每个数据增加index属性，且从1开始
          })
    },
    SearchHandle(value,name,type) {
      if(type === '全部')
        type = ''
      if(name === '' && type === ''){
        if(value === 'heat'){
          axios.get('/api/spot/sortByHeat')  //获取数据
              .then(response => {
                this.items = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
        else if(value === 'grade'){
          axios.get('/api/spot/sortByGrade')  //获取数据
              .then(response => {
                this.items = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
      }
      else if(name === '' && type !== ''){
        if(value === 'heat'){
          axios.get('/api/spot/search/nameOrType',{
            params:{choice:'type', keyword:type, rule:'heat'}
          })  //获取数据
              .then(response => {
                this.items = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
        else if(value === 'grade'){
          axios.get('/api/spot/search/nameOrType',{
            params:{choice:'type', keyword:type, rule:'grade'}
          })  //获取数据
              .then(response => {
                this.items = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }

      }
      else if(name !== '' && type === '') {
        if(value === 'heat'){
          axios.get('/api/spot/search/nameOrType',{
            params:{choice:'name', keyword:name, rule:'heat'}
          })  //获取数据
              .then(response => {
                this.items = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
        else if(value === 'grade'){
          axios.get('/api/spot/search/nameOrType',{
            params:{choice:'name', keyword:name, rule:'grade'}
          })  //获取数据
              .then(response => {
                this.items = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
      }
      else{
        if(value === 'grade'){
          axios.get('/api/spot/search/nameAndType',{
            params:{nameKeyword:name, typeKeyword:type, rule:'grade'}
          })  //获取数据
              .then(response => {
                this.items = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
        else if(value === 'heat'){
          axios.get('/api/spot/search/nameAndType',{
            params:{nameKeyword:name, typeKeyword:type, rule:'heat'}
          })  //获取数据
              .then(response => {
                this.items = response.data.data.map((item,index) => {
                  return{...item,index:index + 1}
                });  // 给每个数据增加index属性，且从1开始
              })
        }
      }
    },
    handleRowClick(){
      this.$router.push('/placesearch')
    },
    filterCategory(value, row) {
      return row.type === value;
    },
    tagColor(type){
      if(type === '自然景观')
        return 'success'
      else if(type === '人文景观' || type === '展览展馆')
        return 'primary'
      else if(type === '历史文化')
        return 'warning'
      else if(type === '主题公园' || type === '红色革命')
        return 'danger'
    },
    changeToFood(){
      this.$router.push('/food')
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

</style>
