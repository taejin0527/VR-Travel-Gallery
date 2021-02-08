import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";

import Home from "../views/Home.vue";
import Login from "../views/member/Login.vue";

import WorldMap from "../views/worldContinents/WorldMap.vue";
import EachContinent from "../views/worldContinents/EachContinent.vue";
import Create from "@/views/post/Create.vue";
import Detail from "@/views/post/Detail.vue";
import View from "@/views/post/View.vue";
import Post from "@/views/photo/Post.vue";
import EachWaterfall from "@/views/worldContinents/EachWaterfall.vue";
import AllWaterfall from "@/views/worldContinents/AllWaterfall.vue";
import Pay from "@/views/pay/Pay.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/signup",
    name: "Signup",
    component: () => import("@/views/member/Signup.vue"),
  },
  {
    path: "/profile",
    name: "Profile",
    component: () => import("@/views/member/Profile.vue"),
  },
  {
    path: "/card",
    name: "Paycard",
    component: () => import("@/views/member/Paycard.vue"),
  },
  {
    path: "/photoview",
    name: "PhotoView",
    component: () => import("@/views/photo/PhotoView.vue"),
  },
  {
    path: "/worldmap/",
    name: "WorldMap",
    component: WorldMap,
  },
  {
    path: "/eachcontinent/",
    name: "EachContinent",
    component: EachContinent,
  },
  {
    path: "/create/",
    name: "Create",
    component: Create,
  },
  {
    path: "/view/",
    name: "View",
    component: View,
  },
  {
    path: "/detail/",
    name: "Detail",
    component: Detail,
  },
  {
    path: "/post/",
    name: "Post",
    component: Post,
  },
  {
    path: "/eachwaterfall/",
    name: "EachWaterfall",
    component: EachWaterfall,
  },
  {
    path: "/allwaterfall/",
    name: "AllWaterfall",
    component: AllWaterfall,
  },
  {
    path: "/pay/",
    name: "Pay",
    component: Pay,
  },
];

const userRoutes: Array<RouteConfig> = [];
const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  const publicPages = [
    "/login",
    "/signup",
    "/",
    "/worldmap",
    "/create",
    "/view",
  ];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem("user");

  // redirect to login page
  if (authRequired && !loggedIn) {
    next("/login");
  } else {
    next();
  }
});

export default router;
