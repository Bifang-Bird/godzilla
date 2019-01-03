import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/index', 
      name:'index', 
      component:async(resolve) => resolve(await import('../view/index'))
    },
    {
      path:'/main', 
      name:'main', 
      component:async(resolve) => resolve(await import('../view/main'))
    },
    {
      path:'/taskmain', 
      name:'taskmain', 
      component:async(resolve) => resolve(await import('../view/taskmain'))
    },
    {
      path:'/music', 
      name:'music', 
      component:async(resolve) => resolve(await import('../view/music'))
    },
  ]
})
