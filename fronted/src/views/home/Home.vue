<template>
  <div class="home">
    <el-container class="firstContainer">
<!--      左侧导航-->
      <NavMenu></NavMenu>
      <el-container>
<!--        右侧主体-->
<!--        头部-->
        <el-header>
          <BreadCrumb />
          <el-button class="exit" type="primary" @click="goBack()"  style="text-align:center">退出系统</el-button>
        </el-header>
<!--        主体-->
        <el-main><router-view/></el-main>
      </el-container>
    </el-container>

  </div>
</template>

<script>
import NavMenu from "./NavMenu.vue";
import BreadCrumb from "./BreadCrumb.vue";

export default {
  components: {BreadCrumb, NavMenu},
  data(){
    return {

    }
  },
  methods:{
    goBack() {
      this.$router.push("/login");
    }

  }
}

</script>

<style scoped>
.firstContainer{
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
.el-header{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 40px;
  height: 50%;
  padding: 10px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 100vh;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 60px;
}
body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 60px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 100%;
}
.exit {
  float: right;
  width: 8%;
}
</style>
