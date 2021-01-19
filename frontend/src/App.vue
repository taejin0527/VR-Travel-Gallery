<template>
  <v-app>
    <v-app-bar app style = "box-shadow: none;" color="#ffffff">
      <!-- 여기 로고를 박으면 됩니다. -->
      <div class="d-flex align-center">
        <v-icon
          large
          color="#8b00ff"
        >
          mdi-leaf
        </v-icon>
        <v-icon
          large
          color="#8b00ff"
        >
          mdi-leaf
        </v-icon>
        <v-icon
          large
          color="#8b00ff"
        >
          mdi-leaf
        </v-icon>
      </div>
      <v-spacer></v-spacer>
      <!-- 로그인 페이지 이동 버튼 -->
      <img
        src="./assets/loginButton.png"
        alt="image_error"
        style="height:90%;"
        @click="gotoLoginPage"
        class="hoverevent-Button"
      >
    </v-app-bar>
    <router-view/>
  </v-app>
  
</template>

<script lang="ts">
import Vue from "vue"


export default Vue.extend({
  name: "App",
  components: {
  },
  data: () => ({
  }),
  methods: {
    // 로그인 페이지로 이동
    gotoLoginPage: function() {
      this.$router.push({name:"Login"})
    }
  }
});
</script>

<style>
/* scoped를 쓰지 않은 이유는 폰트를 여기서 저장하고 모든 곳에서 쓰기 위해서 */
/* 다른 곳은 scoped를 쓰셔야 합니다. 아니면 CSS 이름 중복 에러날 수 있습니다.. */

/* 눈누에서 폰트 가져옴 */
@font-face {
    font-family: 'TmoneyRoundWindRegular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindRegular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

/* 버튼 후버 처리 */
/* 쓰실 후버가 있다면 쓰시면 됩니다. */
.hoverevent-Button:hover {
  opacity: 0.5;
  transform:scale(1.07);
  transition: 150ms;
  cursor: pointer;
}

</style>