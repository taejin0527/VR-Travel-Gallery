<template>
  <!-- 컬러는 수요일날 회의 후 바꿀 예정 -->
  <v-app style="background-color: #5A4E4D;">
    <!-- 왼쪽 상단 로고 픽스 -->
    <div
      style="
                position: fixed;
                height: 10%;
                margin: 0;
                padding: 0;
                width: 100px;
                top: 30px;
                left: 40px;
                z-index: 100;
            "
    >
      <span style="color: #eeeeee; cursor: default">N U </span>
      <br />
      <span style="color: #eeeeee; cursor: default">V O</span>
    </div>

    
    <router-view/>
  </v-app>
</template>

<script lang="ts">
import Vue from "vue";


export default Vue.extend({
  name: "App",
});
</script>

<style lang="scss">
@import "../src/assets/style.scss";

/* scoped를 쓰지 않은 이유는 폰트를 여기서 저장하고 모든 곳에서 쓰기 위해서 */
/* 다른 곳은 scoped를 쓰셔야 합니다. 아니면 CSS 이름 중복 에러날 수 있습니다.. */

/* 눈누에서 폰트 가져옴 */
@font-face {
  font-family: "TmoneyRoundWindRegular";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindRegular.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
* {
  box-sizing: border-box;
}

/* 버튼 후버 처리 */
/* 쓰실 후버가 있다면 쓰시면 됩니다. */
.hoverevent-Button:hover {
  opacity: 0.5;
  transform: scale(1.07);
  transition: 150ms;
  cursor: pointer;
}
</style>
