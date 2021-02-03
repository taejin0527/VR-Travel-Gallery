<template>
  <div class="container">
    <div class="flux">
      <vue-flux
        :options="vfOptions"
        :images="vfImages"
        :transitions="vfTransitions"
        :size="{ width: 640, height: 360 }"
        ref="slider"
      >
        <template v-slot:preloader>
          <flux-preloader />
        </template>

        <template v-slot:controls>
          <flux-controls />
        </template>

        <template v-slot:pagination>
          <flux-pagination />
        </template>

        <template v-slot:index>
          <flux-index />
        </template>
      </vue-flux>
    </div>
    <div class="profile"></div>
  </div>
</template>

<script>
import {
  VueFlux,
  FluxControls,
  FluxIndex,
  FluxPagination,
  FluxPreloader,
} from "vue-flux";

export default {
  components: {
    VueFlux,
    FluxControls,
    FluxIndex,
    FluxPagination,
    FluxPreloader,
  },
  computed: {
    user() {
      return this.$store.state.Auth.authToken;
    },
  },
  data: () => ({
    vfOptions: {
      autoplay: false,
    },
    vfImages: [],
    vfTransitions: ["fade", "cube", "book", "wave", "camera"],
  }),
  mounted() {
    this.vfImages = [
      require("@/assets/images/example/1.jpg"),
      require("@/assets/images/example/2.jpg"),
      require("@/assets/images/example/3.jpg"),
      require("@/assets/images/example/4.jpg"),
      require("@/assets/images/example/5.jpg"),
      require("@/assets/images/example/6.jpg"),
    ];
  },
};
</script>

<style scoped>
.container {
  position: relative;
  height: 100vh;
  z-index: 1;
}
.container .flux {
  position: relative;
  top: 80px;
}
</style>
