<template>
  <div>
    <div class="top-panel">
      <el-form label-width="50px">
        <el-row>

            <div style="align-items: center;float: left;margin-top: 0">
              <el-select v-model="select" placeholder="请选择" style="width: 140px;">
                <el-option label="美食名称" value="name"></el-option>
                <el-option label="餐馆名称" value="restaurant"></el-option>
                <el-option label="菜系" value="kind"></el-option>
              </el-select>
              <el-input placeholder="请输入内容" v-model="input" style="width: 400px;"></el-input>
            </div>
          <el-col :span="4" style="margin-top: 10px;margin-left: 20px">
            <el-form-item label="排序">
              <el-select>
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
            <el-col :span="8" style="margin-top: 10px;margin-left: 0">
              <el-form-item style="width: 450px;float: left">
                <div class="button-group">
                  <el-button type="success" @click="SearchHandle(select,input,value)">搜索</el-button>
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
            label="美食名称"
            min-width="30%"
            align="center">
        </el-table-column>
        <el-table-column
            prop="restaurant"
            label="餐馆名称"
            min-width="30%"
            align="center">
        </el-table-column>
        <el-table-column
            width="150px"
            prop="kind"
            label="菜系"
            align="center"
            :filters="categoryFilters"
            :filter-method="filterCategory"
            filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag
                :type="tagColor(scope.row.kind)"
                disable-transitions>{{scope.row.kind}}</el-tag>
          </template>
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
            prop="distance"
            label="距离(km)"
            min-width="15%"
            align="center">
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
  },
  data() {
    return {
      input:'',
      select: '',
      value:'',
      filterDate: [], // 筛选的动态数据
      items: [],
      options:[
          {label:'热度',value:'heat'},
          {label:'评分',value:'grade'},
          {label:'距离',value: 'distance'}],
      typeOptions:['湘菜','川菜','粤菜','云南菜','北京菜'],
      categoryFilters:[
        {text:'湘菜',value:'湘菜'},
        {text:'川菜',value:'川菜'},
        {text:'粤菜',value:'粤菜'},
        {text:'云南菜',value:'云南菜'},
        {text:'北京菜',value:'北京菜'},
      ]
    }
  },
  methods: {
    fetchData() {
      axios.get('/api/food/top10',{params:{rule: 'heat'}})
          .then(response => {
            this.items = response.data.data.map((item, index) => {
              return {...item, index: index + 1}
            });  // 给每个数据增加index属性，且从1开始
          })
    },
    fetchDataSystem() {
        axios.get('/api/food/top10',{params:{rule: 'heat'}})
            .then(response => {
              this.items = response.data.data.map((item,index) => {
                return{...item,index:index + 1}
              });  // 给每个数据增加index属性，且从1开始
            })
      },
    showAll() {
      axios.get('/api/food/showAll')
          .then(response => {
            this.items = response.data.data.map((item, index) => {
              return {...item, index: index + 1}
            });  // 给每个数据增加index属性，且从1开始
          })
    },
    SearchHandle(select,input,value){
      if(input === ''){
        axios.get('/api/food/top10',{params:{rule: value}})
            .then(response => {
              this.items = response.data.data.map((item,index) => {
                return{...item,index:index + 1}
              });  // 给每个数据增加index属性，且从1开始
            })
      }
      else{
        axios.get('/api/food/search',{params:{choice: select,keyword:input,rule:value}})
            .then(response => {
              this.items = response.data.data.map((item,index) => {
                return{...item,index:index + 1}
              });  // 给每个数据增加index属性，且从1开始
            })
      }
    },
    filterCategory(value, row) {
      return row.kind === value;
    },
    tagColor(type){
      if(type === '云南菜')
        return 'success'
      else if(type === '粤菜')
        return 'primary'
      else if(type === '北京菜')
        return 'warning'
      else if(type === '川菜' || type === '湘菜')
        return 'danger'
    },
  }
}
</script>


<style scoped>
</style>
