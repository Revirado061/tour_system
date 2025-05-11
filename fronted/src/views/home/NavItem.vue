<template>
  <div>
<!-- 无子集     游学推荐-->
      <el-menu-item :index="basePath" v-if="!item.child">
        <span slot="title">{{ item.name }}</span>
      </el-menu-item>

<!--      有子集 游学日记管理-->
      <el-submenu :index="basePath" v-else>
        <template slot="title">{{ item.name }}
        </template>
        <NavItem v-for="sub in item.child" :key="sub.url" :item="sub" :basePath="sub.url" />
      </el-submenu>
  </div>
</template>

<script>
export default {
  name: "NavItem",
  data(){
    return {
    }
  },
  props:['item','basePath'], // 父传给子的具体数据
}
</script>

<style scoped>

</style>
