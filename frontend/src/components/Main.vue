<template>
  <!-- 월드맵 배경 추가 -->
  <v-container
    fluid
    ma-0
    pa-0
    fill-height
    :style="{'background-image': 'url(' + require('../assets/main/worldmap_change_color.png') + ')', 'background-position': 'center'}"
  >
    <v-row
      align="center" justify="center"
    >
      <!-- 타이틀 문구 -->
      <v-col
        cols="12"
      >
        <div class="d-flex justify-center mb-6">
          <h1
            style="font-family:arial; color:#cccccc; font-size: 100px"
          > N U V O </h1>
        </div>
      </v-col>
      <!-- 월드 맵으로 가는 버튼 -->
      <v-col
        offset="2"
        cols='4'
      >
        <img
          src="@/assets/main/earth_globe_icon.png"
          alt="image error"
          style="height: 200px; width: 200px; z-index: 100;"
          ref="earthGlobeIcon"
          @mouseover="disappearEarthIcon"
        >
        <img
          src="@/assets/main/earth_spinning.gif"
          alt="image error"
          style="height: 200px; width: 200px; position: relative; right: 200px"
          class="disappeared-earth-spinning"
          ref="earthGlobeSpinning"
        >
        
      </v-col>
      <v-col
        cols='4'
      >
        <h1
          style="font-family:arial; color:#000000; font-size: 100px"
        > VR </h1>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'Main',
  data: function () {
    return {
    }
  },
  methods:{
    // 월드 맵으로 가는 버튼 액션
    gotoWorldMap: function () {
      this.$router.push({name:'WorldMap'})
    },
    disappearEarthIcon: function () {
      this.$refs.earthGlobeIcon.setAttribute("class", "disappeared-earth-icon")
      this.$refs.earthGlobeSpinning.setAttribute("class", "show-earth-spinning")
      this.$refs.earthGlobeSpinning.removeAttribute("class", "disappeared-earth-spinning")
      
    }
  }
}
</script>

<style scoped>
/* 티머니 폰트체인지 CSS */
.font-change-tmoneyroundwindregular {
  font-family: 'TmoneyRoundWindRegular'; 
} 

.disappeared-earth-spinning {
  opacity: 0;
  z-index: -3;
  cursor: pointer;
}

.show-earth-spinning {
  opacity: 0.9;
  transition-delay: 0.3s;
  transition-duration: 0.5s;
  scale: 2;
  cursor: pointer;
}

.disappeared-earth-icon {
  opacity: 0;
  transition-delay: 0;
  transition-duration: 0.5s;
  cursor: pointer;
}
</style>