import Vue from 'vue'
import Router from 'vue-router'
import Maps from '@/components/Maps'
import Panel from '@/components/Panel'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'panel',
      component: Panel
    },
    {
      path: '/maps',
      name: 'maps',
      component: Maps
    }
  ]
})
