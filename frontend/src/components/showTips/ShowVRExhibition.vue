<template>
  <v-overlay :absolute="absolute" :opacity="opacity" :value="overlay">
    <div style="text-align:center;">
      <v-btn color="#DDA288" @click="overlay = false" style="color:white">
        첫 번째 전시관
      </v-btn>
    </div>
    <div style="text-align:center;">
      <v-btn color="#DDA288" @click="overlay = false" style="color:white">
        두 번째 전시관
      </v-btn>
    </div>
  </v-overlay>
</template>

<script>
export default {
  name: "ShowVRExhibition",
  methods: {
    gotoFirstExhibition: function () {
      console.log('에러 나서 만듬. 함수 쓰면 지워욤')
    },
    gotoSecondExhibition: function () {
      console.log('에러 나서 만듬. 함수 쓰면 지워욤')
    }
  }
}
</script>

<style>

</style>