import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import ArticleList from '@/components/ArticleList'
import CateMana from '@/components/CateMana'
import DataCharts from '@/components/DataCharts'
import PostArticle from '@/components/PostArticle'
import UserMana from '@/components/UserMana'
import BlogDetail from '@/components/BlogDetail'
import ActivityList from '@/components/ActivityList'
import MyCanlendar from '@/components/MyCanlendar'
import PrizeManage from '@/components/PrizeManage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/prizemanage',
      name: '个人日历',
      hidden: true,
      component: PrizeManage
    },
    {
      path: '/mycanlendar',
      name: '个人日历',
      hidden: true,
      component: MyCanlendar
    },
    {
      path: '/',
      name: '登录',
      hidden: true,
      component: Login
    }, {
      path: '/home',
      name: '',
      component: Home,
      hidden: true
    }, {
      path: '/home',
      component: Home,
      name: '活动管理',
      iconCls: 'fa fa-file-text-o',
      children: [
        {
          path: '/ActivityList',
          name: '活动列表',
          component: ActivityList,
          meta: {
            keepAlive: true
          }
        }, {
          path: '/postArticle',
          name: '发起活动',
          component: PostArticle,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/blogDetail',
          name: '活动详情',
          component: BlogDetail,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/editBlog',
          name: '编辑活动',
          component: PostArticle,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '奖励管理',
      children: [
        {
          path: '/user',
          iconCls: 'fa fa-user-o',
          name: '奖励管理',
          component: UserMana
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '抽奖管理',
      children: [
        {
          path: '/cateMana',
          iconCls: 'fa fa-reorder',
          name: '抽奖管理',
          component: CateMana
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '信息管理',
      iconCls: 'fa fa-bar-chart',
      children: [
        {
          path: '/charts',
          iconCls: 'fa fa-bar-chart',
          name: '信息管理',
          component: DataCharts
        }
      ]
    }
  ]
})
