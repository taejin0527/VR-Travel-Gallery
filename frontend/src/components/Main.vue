<template>
  <!-- 월드맵 배경 추가 -->
  <v-container
    fluid
    ma-0
    pa-0
    fill-height
    :style="{
      'background-image':
        'url(' + require('../assets/worldmap_change_color.png') + ')',
      'background-position': 'center',
    }"
  >
    <v-row align="center" justify="center">
      <!-- 타이틀 문구 -->
      <v-col cols="12">
        <div class="d-flex justify-center mb-6">
          <h1 style="font-family:arial; color:#cccccc">Travel</h1>
        </div>
      </v-col>
      <!-- 내용 문구 -->
      <v-col cols="12" v-for="(item, idx) in mainPageWords" :key="idx">
        <div class="d-flex justify-center mb-6">
          <span
            class="font-change-tmoneyroundwindregular"
            style="color:#cccccc"
          >
            {{ item }}
          </span>
        </div>
      </v-col>
      <!-- 월드 맵으로 가는 버튼 -->
      <v-col>
        <br />
        <div class="d-flex justify-center mb-6">
          <v-btn
            elevation="2"
            class="font-change-tmoneyroundwindregular"
            @click="gotoWorldMap"
            >지금 시작하기</v-btn
          >
        </div>
        <br />
        <br />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "Main",
  data: function() {
    return {
      // 배경 문구 내용
      mainPageWords: [
        "추억을 쌓는 여행, 직접 가야만 여행일까요?",
        "가지 못 하더라도 지금까지 쌓은 추억을 즐기세요!",
      ],
    };
  },
  methods: {
    // 월드 맵으로 가는 버튼 액션
    gotoWorldMap: function() {
      this.$router.push({ name: "WorldMap" });
    },
  },
};
</script>

<style scoped>
/* 티머니 폰트체인지 CSS */
.font-change-tmoneyroundwindregular {
  font-family: "TmoneyRoundWindRegular";
}
</style>
