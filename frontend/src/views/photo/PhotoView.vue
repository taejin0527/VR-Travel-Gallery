<template>
  <div class="container d-flex justify-center">
    <div class="flux">
      <vue-flux
        :options="vfOptions"
        :images="vfImages"
        :transitions="vfTransitions"
        class="adjust-grid-image"
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
      .get(`${SERVER.BOARD_BASE_URL}getposts?id=15`)
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

/* 반응형으로 만듬 */
.adjust-grid-image {
  width: 400px;
  height: 400px;
}

@media (min-width: 800px) {
  .adjust-grid-image {
    width: 600px;
    height: 500px;
  }
}

@media (min-width: 1000px) {
  .adjust-grid-image {
    width: 800px;
    height: 700px;
  }
}

/* 1264px 부터 css코드 */
@media (min-width: 1264px) {
  .adjust-grid-image {
    width: 1000px;
    height: 700px;
  }
}

/* 1600px 부터 css코드 */
@media (min-width: 1500px) {
  .adjust-grid-image {
    width:1200px;
    height: 700px;
  }
}

/* 1904px 부터 css코드 */
@media (min-width: 1904px) {
  .adjust-grid-image {
    width:1500px;
    height: 800px;
  }
}

</style>
