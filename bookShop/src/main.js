// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/store'
import MuseUI from 'muse-ui';
import iView from 'iview'
import 'muse-ui/dist/muse-ui.css'
import Vuex from 'vuex'
import 'iview/dist/styles/iview.css';
import VueCookies from 'vue-cookies';

Vue.config.productionTip = false
Vue.use(Vuex)
Vue.use(iView)
Vue.use(MuseUI);
Vue.use(VueCookies)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
