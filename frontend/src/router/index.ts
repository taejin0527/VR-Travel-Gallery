import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";

import Home from "../views/Home.vue";
import Login from "../views/member/Login.vue";
import WorldMap from "../views/WorldContinents/WorldMap.vue";
import EachContinent from "../views/WorldContinents/EachContinent.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/signup",
    name: "Signup",
    component: () => import(/* webpackChunkName: "about" */ "@/views/member/Signup.vue")
  },
  {
    path: "/profile",
    name: "Profile",
    component: () => import(/* webpackChunkName: "about" */ "@/views/member/Profile.vue")
  },
  {
    path: "/worldmap/",
    name: "WorldMap",
    component: WorldMap
  },
  {
    path: "/eachcontinent/",
    name: "EachContinent",
    component: EachContinent
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
