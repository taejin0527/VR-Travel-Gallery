<template>
  <div>
    <div class="navi">
      <div style="width=100%; text-align:center;">
        <div class="navi-button" @click="toggle">N U V O A to Z</div>

        <div class="social" v-show="isMenuPageOpen">
          <router-link
            v-if="!isLoggedIn"
            tag="li"
            @click.native="isMenuPageOpen = !isMenuPageOpen"
            to="/login"
          >
            <v-icon large color="white"> mdi-account-key </v-icon>
            <p style="color:#fff">Login</p></router-link
          >
          <a
            v-if="isLoggedIn"
            tag="li"
            @click="[(isMenuPageOpen = !isMenuPageOpen), signOut()]"
            to="/"
          >
            <v-icon large color="white"> mdi-exit-to-app </v-icon>
            <p style="color:#fff">Logout</p>
          </a>
        </div>
      </div>
    </div>
    <!-- 랩핑하는 방식으로 A to Z (menu) 화면 렌더링 -->
    <transition name="slide">
      <Menu
        v-if="isMenuPageOpen"
        :isMenuPageOpen="isMenuPageOpen"
        @closeMenuPage="closeMenuPage"
      />
    </transition>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import { mapActions, mapGetters } from "vuex";

import Menu from "@/components/navigation/Menu.vue";

export default Vue.extend({
  components: {
    Menu,
  },
  data: function() {
    return {
      isMenuPageOpen: false,
    };
  },
  computed: {
    ...mapGetters("Auth", ["isLoggedIn"]),
  },
  methods: {
    ...mapActions("Auth", ["signOut"]),

    // 메뉴 페이지 이동
    toggle: function() {
      this.isMenuPageOpen = !this.isMenuPageOpen;
    },
    closeMenuPage: function() {
      this.isMenuPageOpen = false;
    },
  },
});
</script>

<style scoped>
.navi {
  position: fixed;
  height: 80px;
  margin: 0;
  padding: 0;
  width: 80px;
  top: 44%;
  left: 30px;
  z-index: 100;
}
/* 네비게이션 버튼 후버 처리 및 3D 형식 */
.navi-button {
  display: inline-block;
  padding: 1.5em 1.5em;
  border-radius: 0;
  color: #7e675e;
  margin-top: 0.51rem;
  cursor: pointer;
  font-weight: bold;
  font-size: 0.7rem;
  letter-spacing: 1px;
  text-transform: uppercase;
  text-decoration: none;
  transition: all 300ms ease;
  z-index: 2;
}

.navi-button:before,
.navi-button:after {
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  border: 2px solid;
  transition: 0.6s;
}
.navi-button:before {
  transform: translateX(-0.25em) translateY(0.25em);
}
.navi-button:after {
  transform: translateX(0.25em) translateY(-0.25em);
}

.navi-button:hover:before,
.navi-button:hover:after {
  transform: translateX(0) translateY(0);
  color: #ffffff;
}

.navi-button:hover {
  transition-delay: 0.1s;
  transition-duration: 0.4s;
  color: #ffffff;
}

.navi-button:not(:hover) {
  transition-delay: 0.2s;
  transition-duration: 0.4s;
  color: #7e675e;
}

/* transition slide 효과 */
.slide-leave-active,
.slide-enter-active {
  transition: 0.3s;
}
.slide-enter {
  transform: translateX(-100%);
}
.slide-leave-to {
  transform: translateX(-100%);
}

.social {
  padding: 10px;
}
.social li {
  text-decoration: none;
  list-style: none;
  margin: 20px 0;
  background: transparent;
}
</style>
