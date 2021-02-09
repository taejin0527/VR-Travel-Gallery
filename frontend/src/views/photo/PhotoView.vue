<template>
  <div class="container d-flex justify-center">
    <!-- 오른쪽 상단 Tips 픽스 -->
    <div
      style="
              position: fixed;
              height: 10%;
              margin: 0;
              padding: 0;
              width: 100px;
              top: 15px;
              right: 50px;
              z-index: 101;
            "
    >
      <img
        src="@/assets/3DHelp3.png" alt="" width="120px"
        :class="{'select-tips-transition': isSelectTips}"
        @mouseover="isSelectTips = true"
        @mouseleave="isSelectTips = false"
      >
    </div>

    <!-- Flipbook 페이지로 가는 버튼 -->
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:60px; top:120px; color:white;"
      @click="clickGotoFlipbook"
    >
      <v-icon>
        mdi-book-open-page-variant
      </v-icon>
    </v-btn>

    <!-- 뒤로가기 버튼 -->
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:60px; top:200px; color:white;"
      @click="clickGoBack"
    >
      <v-icon size="38px">
        mdi-arrow-left-bold-circle
      </v-icon>
    </v-btn>

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
    isSelectTips: false,
  }),
  computed: {
    user() {
      return this.$store.state.Auth.authToken;
    },
  },
  mounted() {
    axios
      .get(`${SERVER.BOARD_BASE_URL}getposts?id=${localStorage.getItem('articleId')}`)
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
  methods: {
    clickGotoFlipbook: function () {
      this.$router.push({name:"Flipbook"})
    },
    clickGoBack: function () {
      this.$router.push({name:localStorage.getItem("page")})
    }
  }
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

/* TIPS 애니메이션 */
@keyframes tipsbeat {
  from {
    transform: scale(0.95);
  }

  to {
    transform: scale(1.1);
  }
}

.select-tips-transition {
  animation-duration: 0.8s;
  animation-name: tipsbeat;
  animation-iteration-count: infinite;
  animation-direction: alternate;
  cursor: pointer;
}

</style>
