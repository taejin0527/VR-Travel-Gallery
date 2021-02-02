<template>
  <div class="container">
    <div class="container-center">
      <carousel @prev="prev" @next="next">
        <transition-group name="slide-list">
          <carousel-slide
            v-for="(slide, idx) in slides"
            :key="slide"
            :idx="idx"
            :visibleSlide="visibleSlide"
            class="slide-list"
          >
            <img :src="slides[idx - 2]" />
            <img :src="slides[idx - 1]" />
            <img :src="slides[idx]" />
            <img :src="slides[idx + 1]" />
            <img :src="slides[idx + 2]" />
          </carousel-slide>
        </transition-group>
      </carousel>
    </div>
  </div>
</template>

<script>
import Carousel from "@/components/photo/Carousel.vue";
import CarouselSlide from "@/components/photo/CarouselSlide.vue";

export default {
  components: {
    Carousel,
    CarouselSlide,
  },
  data: function() {
    return {
      slides: [
        require("@/assets/images/example/1.jpg"),
        require("@/assets/images/example/2.jpg"),
        require("@/assets/images/example/3.jpg"),
        require("@/assets/images/example/4.jpg"),
        require("@/assets/images/example/5.jpg"),
      ],
      visibleSlide: 0,
    };
  },
  computed: {
    slidesLen() {
      return this.slides.length;
    },
  },
  methods: {
    prev() {
      const sound = new Audio(require("@/assets/audio/paper_flip.mp3"));
      sound.play();
      if (this.visibleSlide <= 0) {
        this.visibleSlide = this.slidesLen - 1;
      } else {
        this.visibleSlide--;
      }
    },
    next() {
      const sound = new Audio(require("@/assets/audio/paper_flip.mp3"));
      sound.play();
      if (this.visibleSlide >= this.slidesLen - 1) {
        this.visibleSlide = 0;
      } else {
        this.visibleSlide++;
      }
    },
  },
};
</script>

<style scoped>
.container {
  display: flex;
  height: 100vh;
  width: 100vw;
  max-width: none;
  position: absolute;
  overflow: hidden;
  z-index: 1;
}

.container-center {
  display: flex;
  margin: 0;
  padding: 0;
  width: 100%;
  justify-content: center;
  align-items: center;
}

/* Animation */
.slide-list {
  margin: 0;
  transition: linear 1s;
}
.slide-list-enter,
.slide-list-leave-to {
  opacity: 0;
  transform: translateX(0px);
}
.slide-list-enter-to,
.slide-list-leave {
  opacity: 1;
  transform: translateX(300px);
}

/* Animation end */

.carousel-slide img {
  height: 40vh;
  position: absolute;
}
.carousel-slide img:hover {
  transform: scale(1.6);
}
.carousel-slide img:nth-child(1) {
  transform: rotate(58deg) translate(-330px, -750px);
  opacity: 0.8;
  z-index: 5;
}
.carousel-slide img:nth-child(2) {
  transform: rotate(25deg) translate(-70px, -400px);
  opacity: 0.8;
  z-index: 6;
}
.carousel-slide img:nth-child(3) {
  opacity: 1;
  z-index: 7;
}
.carousel-slide img:nth-child(4) {
  transform: rotate(-25deg) translate(-70px, 400px);
  opacity: 0.8;
  z-index: 6;
}
.carousel-slide img:nth-child(5) {
  transform: rotate(-58deg) translate(-330px, 750px);
  opacity: 0.8;
  z-index: 5;
}
</style>
