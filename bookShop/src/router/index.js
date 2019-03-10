import Vue from 'vue'
import Router from 'vue-router'
import shop from '@/components/shop'
import cart from '@/components/cart'
import mypage from '@/components/mypage'
import result from '@/components/result'
import detail from '@/components/detail'
import record from '@/components/record'
import order from '@/components/order'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      redirect:'shop'
    },
    {
      path:'/shop',
      name:'shop',
      component: shop
    },
    {
      path:'/cart',
      name:'cart',
      component: cart
    },
    {
      path:'/mypage',
      name:'mypage',
      component: mypage
    },
    {
      path:'/result',
      name:'result',
      component: result
    },
    {
      path:'/detail',
      name:'detail',
      component: detail
    },
    {
      path:'/record',
      name:'record',
      component: record
    },
    {
      path:'/order',
      name:'order',
      component: order
    }
  ]
})
