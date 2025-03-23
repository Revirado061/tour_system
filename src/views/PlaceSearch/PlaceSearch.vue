<template>
<div>
  <div class="image">
    <el-image :src="url" :preview-src-list="srcList"></el-image>
  </div>
  <div class="panel">
      <fieldset>
        <el-row>
          <p class="head">场所查询</p>
        </el-row>
        <div class="search">
          <el-form :inline="true" style="height: 60px">
            <el-form-item class="custom-form-item" style="float:left; margin-left: 10px;margin: 0">
              <el-autocomplete
                  v-model="spot"
                  :fetch-suggestions="querySearchAsync"
                  placeholder="请输入选择地点"
                  @select="handleSelect"
                  style="width: 180px;float:left;"
              ></el-autocomplete>
              <el-input placeholder="请输入距离半径" v-model="radius" style="width: 150px;margin-left: 10px" clearable></el-input>
              <el-select v-model="category" placeholder="请选择类别" style="width: 120px;margin-left: 10px" filterable>
                <el-option
                    v-for="item in categoryFilters"
                    :key="item.value"
                    :label="item.text"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item  class="custom-form-item" style="width: 170px;margin: 0">
              <el-button type="success" @click="searchHandle(spot,radius,category)" style="width: 60px" size="small">搜索</el-button>
              <el-button type="success" @click="showAll" style="width: 100px" size="small">显示所有设施</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="form">
          <el-table
              :data="items"
              height="400"
              border
              style="width: 100%;text-align: center;margin: 0">
            <el-table-column
                prop="id"
                label="序号"
                width="80"
                align="center">
            </el-table-column>
            <el-table-column
                prop="category"
                label="地点类别"
                align="center"
                :filters="categoryFilters"
                :filter-method="filterCategory"
                filter-placement="bottom-end">
              <template slot-scope="scope">
                <el-tag
                    :type="tagColor(scope.row.category)"
                    disable-transitions>{{scope.row.category}}</el-tag>
              </template>
            </el-table-column>
            <el-table-column
                prop="x"
                label="坐标位置(x)"
                align="center">
            </el-table-column>
            <el-table-column
                prop="y"
                label="坐标位置(y)"
                align="center">
            </el-table-column>
            <el-table-column
                prop="distance"
                label="距离(m)"
                align="center">
            </el-table-column>
          </el-table>
        </div>
      </fieldset>
  </div>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "PlaceSearch",
  mounted() {
    this.showAll();
    this.loadAll();
  },
  data() {
    return {
      url: "/image/00.jpg",
      srcList:['/image/00.jpg'],
      form: {},
      spot:'',// 双向数据绑定的值
      radius: null,
      restaurants: [],
      timeout:  null,
      items: [],
      category:'',
      categoryFilters: [
        { text: '全部', value: '全部' },
        { text: '卫生间', value: '卫生间' },
        { text: '自动贩卖机', value: '自动贩卖机' },
        { text: '校园咖啡角', value: '校园咖啡角' },
        { text: '实验室', value: '实验室' },
        { text: '打印处', value: '打印处' },
        { text: '医务室', value: '医务室' },
        { text: '地图导览', value: '地图导览' },
        { text: '服务咨询', value: '服务咨询' },
        { text: '校园文创', value: '校园文创' },
        { text: '自习室', value: '自习室' },
      ],
    }
  },
  methods: {
    showAll() {
      axios.get('/api/graph/allFacilities')
          .then(response => {
            this.items = response.data.data // 给每个数据增加index属性，且从1开始
          })
    },
    searchHandle(spot,radius,category) {
      if(category === '全部'){
        axios.get('/api/graph/nearbyFacilities',{
          params:{building:spot, category:'all',radius:radius}
        }).then(response => {
          this.items = response.data.data.map((item, index) => {
            return {...item, index: index + 1}
          });  // 给每个数据增加index属性，且从1开始
        })
      }
      else{
        axios.get('/api/graph/nearbyFacilities',{
          params:{building:spot, category:category,radius:radius}
        }).then(response => {
          this.items = response.data.data.map((item, index) => {
            return {...item, index: index + 1}
          });  // 给每个数据增加index属性，且从1开始
        })
      }
    },
    filterCategory(value, row) {
      return row.category === value;
    },
    tagColor(category) {
      if(category === '卫生间' || category === '实验室' || category === '打印处' )
        return 'primary';
      else if(category === '自动贩卖机' || category === '校园咖啡角' || category === '校园文创')
        return 'warning';
      else if(category === '医务室' || category === '地图导览')
        return 'success';
      else if(category === '服务咨询' || category === '自习室')
        return 'info';
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
  }
}
</script>


<style scoped>
.custom-form-item {
  margin-bottom: 0;
}
.search {
  margin: 0;
}
.image{
  float: left;
  width: 36%;
  margin-left: 35px;
  margin-right: 20px;
  margin-top: 0;
}
.panel{
  float:right;
  border: #333333 5px;
  width:54%;
  margin-top: 40px;
  margin-right: 60px;
}
.head {
  font-family: '晴圆等宽';
  font-size: 40px;
  margin: 5px;
}
</style>
