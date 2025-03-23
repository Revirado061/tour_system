// 此文件专门负责项目的路由

import VueRouter from "vue-router"

// 引入组件
import Login from '../views/login/Login'
import Register from "../views/register/Register";
import Recommendation from "../views/recommendation/Recommendation";
import Home from '../views/home/Home';
import DiaryPlan from '../views/DiaryPlan/index'
import HomePage from '../views/home/HomePage'
import AllDiary from "../views/DiaryPlan/AllDiary.vue";
import Mydiary from "../views/DiaryPlan/Mydiary.vue";
import RouterPlan from "@/views/RouterPlan/RouterPlan.vue";
import PlaceSearch from "@/views/PlaceSearch/PlaceSearch.vue";
import DiaryText from "@/views/DiaryPlan/DiaryText.vue";
import Food from "@/views/food/food.vue"
// 创建并暴露一个路由器
const router = new VueRouter({
    mode: 'history',    // 路由模式，该模式不会在地址中显示井号#
    routes: [
        {
            path: '/',          // 登录页
            redirect: '/login'  // 重定向
        },
        {
            path: '/login',     // 登录页
            component: Login    // 跳转到的组件
        },
        {
            path: '/register', // 注册页
            component: Register
        },
        {
            path: '/home', // 布局页
            component: Home,
            meta:{title:'首页'},
            redirect:'/homepage',
            children:[
                {
                    path: '/homepage',
                    meta:{title:'个人主页'},
                    component: HomePage
                },
                {
                    path: '/recommendation', // 推荐模块
                    meta:{title:'推荐模块'},
                    component: Recommendation
                },
                {
                    path:'/routerplan',// 路线规划
                    meta:{title:'路线规划'},
                    component:RouterPlan
                },
                {
                    path:'/placesearch', // 场所查询
                    meta:{title:'场所查询'},
                    component:PlaceSearch
                },
                {
                    path:'/food', // 美食推荐
                    meta:{title:'美食推荐'},
                    component:Food
                },
                {
                    path: '/diaryplan', //游学日记管理
                    meta:{title: '游学日记管理'},
                    component: DiaryPlan,
                    children:[
                        {
                            path: '/diaryplan/alldiary',
                            meta:{title:'所有日记'},
                            component: AllDiary
                        },
                        {
                            path: '/diaryplan/mydiary', // 我的日记
                            meta:{title:'我的日记'},
                            component: Mydiary
                        },
                        {
                            path: '/diaryplan/diarytext', // 我的日记
                            meta:{title:'日记内容'},
                            component: DiaryText
                        },
                    ]
                }
            ]
        }

    ]
})
// // 导航守卫，前置处理
// router.beforeEach((to, from, next) => {
//     let isAuthenticated = !!sessionStorage.getItem('userInfo')
//     // 如果路由要跳转到除了登录和注册的界面的话就判断是否已经登录，如果没有登录就强制跳到登录界面
//     if (to.path !== '/login' && to.path !== '/register' && !isAuthenticated) {
//         next({ path: '/login' })
//         Message({
//             message: '请先登录！',
//             type: "warning",
//         });
//     } else next()
// })


export default router;
