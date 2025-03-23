<template>
<div class="breadcrumb">
  <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-top: 10px">
    <el-breadcrumb-item v-for="v in lists" :key="v.path">
      <router-link :to="v.path">{{ v.meta.title }}</router-link>
    </el-breadcrumb-item>
  </el-breadcrumb>
</div>
</template>

<script>
export default {
  name: "breadcrumb",
  data(){
    return {
      lists:[]
    }
  },
  watch:{
    $route(to){
      this.lists = to.matched;
    }
  },
  methods:{
  },
  mounted(){
    this.lists = this.$route.matched;
    console.log(this.$route.matched)
  }
}
</script>


<style scoped>
.breadcrumb {
  float: left;
  width: 60%;
}
</style>
