<template>
  <!-- 컬러는 수요일날 회의 후 바꿀 예정 -->
  <v-app style="background-color: #5b5656;">
    <!-- 왼쪽 상단 로고 픽스 -->
    <div
      style="position: fixed; height:10%;
            margin: 0; padding: 0; width: 100px;
            top: 30px; left: 40px; z-index:100"
    >
      <span style="color:#eeeeee; cursor:default;">N U </span>
      <br>
      <span style="color:#eeeeee; cursor:default;">V O</span>
    </div>
    <!-- 왼쪽 중간 A to Z (menu) 픽스 -->
    <div
      style="position: fixed; height:80px;
            margin: 0; padding: 0; width: 80px;
            top: 44%; left: 30px; z-index:100;"
    >
      <div style="width=100%; text-align:center;">
        <div class="navi-button" @click="gotoMenuPage">
          N U V O
          A to Z
        </div>
      </div>
    </div>
    <!-- 랩핑하는 방식으로 A to Z (menu) 화면 렌더링 -->
    <Menu
      v-if="isMenuPageOpen"
      :isMenuPageOpen="isMenuPageOpen"
      @closeMenuPage="closeMenuPage"
    />
    <router-view/>
  </v-app>
  
</template>

<script lang="ts">
import Vue from "vue"
import Menu from "@/components/Menu.vue";


export default Vue.extend({
  name: "App",
  components: {
    Menu
  },
  data: function () {
    return {
      isMenuPageOpen: false
    }
  },
  methods: {
    // 메뉴 페이지 이동
    gotoMenuPage: function() {
      this.isMenuPageOpen = true
    },
    closeMenuPage: function () {
      this.isMenuPageOpen = false
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

/* 네비게이션 버튼 후버 처리 및 3D 형식 */
.navi-button {
  display: inline-block;
  padding: 1.5em 1.5em;
  border-radius: 0;
  color: #b2876f;
  margin-top:0.51rem;
  cursor: pointer;
  font-weight: bold;
  font-size: 0.7rem;
  letter-spacing: 1px;
  text-transform: uppercase;
  text-decoration: none;
  transition: all 300ms ease;
  z-index: 200;
}

.navi-button:before,
.navi-button:after{
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  border: 2px solid;
  transition: .3s;
}
.navi-button:before{
  transform: translateX(-.25em) translateY(.25em);
}
.navi-button:after{
  transform: translateX(.25em) translateY(-.25em);
}

.navi-button:hover:before,
.navi-button:hover:after{
  transform: translateX(0) translateY(0);
  color: #ffffff;
}

.navi-button:hover{
  transition-delay: .1s;
  transition-duration: .4s;
  color: #ffffff;
}

.navi-button:not(:hover){
  transition-delay: .2s;
  transition-duration: .4s;
  color: #b2876f;
}



</style>