<template>
  <el-aside width="200px">
    <el-menu
        default-active="$router.path" exact
        class="el-menu-vertical-demo" router>
<!--      精确匹配exact-->
      <NavItem v-for="v in items" :key="v.url" :item="v" :basePath="v.url"/>
    </el-menu>
  </el-aside>
</template>

<script>
import NavItem from "@/views/home/NavItem.vue";//子组件
export default {
  name: "NavMenu",
  data(){
    return {
      items:[
        {name:'个人主页',url:'/homepage'},
        {name:'游学推荐',url:'/recommendation'},
        {name:'美食推荐',url:'/food'},
        {name:'游学路线规划',url:'/routerplan'},
        {name:'场所查询',url:'/placesearch'},
        {
          name:'游学日记管理',
          url:'/diaryplan',
          child:[
            {name:'所有日记',url:'/diaryplan/alldiary'},
            {name:'新增日记',url:'/diaryplan/mydiary'},
          ]},
      ]
    }
  },
  components:{NavItem}
}
</script>

<style scoped>

</style>
