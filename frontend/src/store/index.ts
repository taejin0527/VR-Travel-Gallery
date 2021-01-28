import Vue from "vue";
import Vuex from "vuex";

import Auth from "./modules/auth";
import Signup from "./modules/signup";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    Auth,
    Signup,
  },
});
