<template>
  <div
    style="width:100%; height:100%;"
    class="d-flex justify-center align-center"
  >
    <transition name="fade">
      <img
        v-if="show"
        :src="`${vfImages[imageIndex]}`"
        alt=""
        width="90%"
      >
    </transition>
    <div
      style="position:fixed; left:0px; height:100%; width:50%;"
      @click="lefttoImage"
    ></div>
    <div
      style="position:fixed; right:0px; height:100%; width:50%;"
      @click="righttoImage"
    ></div>
    <v-overlay
      :absolute="absolute"
      :value="firstOverlay"
    >
      <v-btn
        color="#DDA288"
        @click="firstOverlay = false"
      >
        첫번째 사진입니다
      </v-btn>
    </v-overlay>
    <v-overlay
      :absolute="absolute"
      :value="lastOverlay"
    >
      <v-btn
        color="#DDA288"
        @click="lastOverlay = false"
      >
        마지막 사진입니다
      </v-btn>
    </v-overlay>
    <!-- 사진 목록으로 가는 버튼 -->
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; bottom:43px; right:43px; color:white;"
      @click="clickGotoBack"
    >
      <v-icon>
        mdi-view-carousel-outline
      </v-icon>
    </v-btn>
  </div>

</template>

<script>
export default {
  name:"MobileView",
  data: function () {
    return {
      imageIndex: 0,
      absolute: true,
      firstOverlay: false,
      lastOverlay: false,
      show:true,
    }
  },
  props: {
    vfImages: [Array],
  },
  methods: {
    righttoImage: function () {
      if (this.imageIndex < this.vfImages.length-1) {
        this.imageIndex = this.imageIndex + 1
        this.show = !this.show
        setTimeout(() => {this.show = !this.show}, 500)
      }
      else {
        this.lastOverlay = true
      }
    },
    lefttoImage: function () {
      if (this.imageIndex > 0) {
        this.imageIndex = this.imageIndex - 1
        this.show = !this.show
        setTimeout(() => {this.show = !this.show}, 500)
      }
      else {
        this.firstOverlay = true
      }
    },
    clickGotoBack: function () {
      this.$router.push({name:"EachWaterfall"})
    }
  }
}
</script>

<style scoped>

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}

</style>