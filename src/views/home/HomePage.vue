<template>
  <div>
    <h2 style="margin: 5px;font-family: '晴圆等宽'">所有游学景点</h2>
    <el-table
        :data="tableData"
        height="550"
        border
        style="width: 100%">
      <el-table-column
          prop="index"
          label="序号"
          width="180"
          align="center">
      </el-table-column>
      <el-table-column
          prop="name"
          label="景点名称"
          width="600"
          align="center">
      </el-table-column>
      <el-table-column
          prop="heat"
          label="热度"
          align="center">
      </el-table-column>
      <el-table-column
          prop="grade"
          label="评分"
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
    </el-table>
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
      tableData: [],
      categoryFilters:[
        {text:'自然景观',value:'自然景观'},
        {text:'人文景观',value:'人文景观'},
        {text:'历史文化',value:'历史文化'},
        {text:'主题公园',value:'主题公园'},
        {text:'红色革命',value:'红色革命'},
      ]
    };
  },
  methods: {
    fetchData() {
      axios.get('/api/spot/showAll')
          .then(response => {
            this.tableData = response.data.data.map((item, index) => {
              return {...item, index: index + 1}
            });  // 给每个数据增加index属性，且从1开始
          })
    },
    filterCategory(value, row) {
      return row.type === value;
    },
    tagColor(type){
      if(type === '自然景观')
        return 'success'
      else if(type === '人文景观')
        return 'primary'
      else if(type === '历史文化')
        return 'warning'
      else if(type === '主题公园' || type === '红色革命')
        return 'danger'
    }
  }
}
</script>
