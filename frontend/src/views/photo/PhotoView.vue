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
import axios from "axios";
import SERVER from "@/apis/UrlMapper.ts"

export default {
  components: {
    VueFlux,
    FluxControls,
    FluxIndex,
    FluxPagination,
    FluxPreloader,
  },
  data: () => ({
    vfOptions: {
      autoplay: false,
    },
    vfImages: [],
    vfTransitions: ["fade", "cube", "book", "wave", "camera"],
  }),
  computed: {
    user() {
      return this.$store.state.Auth.authToken;
    },
  },
  mounted() {
    axios
      .get(`${SERVER.BOARD_BASE_URL}getposts?id=2`)
      .then((response) => {
        response.data.forEach((e) => {
          this.vfImages.push(e.filepath);
          console.log(e.filepath);
        });
      })
      .catch(() => {
        console.log("subImg 불러오기 실패");
      });
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
