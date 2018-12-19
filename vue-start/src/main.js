// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router';
import router from './router'
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import axios from 'axios';
import * as apiTypes from './config/apiTypes';

Vue.use(VueRouter);
Vue.use(iView);

Vue.config.productionTip = false

Vue.prototype.$axios=axios;
Vue.prototype.$apiTypes=apiTypes;

window.EventBus=new Vue();

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
