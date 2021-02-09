<template>
  <div class="container">
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

    <!-- PhotoView 페이지로 가는 버튼 -->
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:60px; top:120px; color:white;"
      @click="clickGotoPhotoView"
    >
      <v-icon>
        mdi-view-carousel-outline
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


    <div
      class="container-center"
      :class="{ 'has-mouse': hasMouse }"
      @touchstart="hasMouse = false"
    >
      <Flipbook
        class="flipbook"
        :pages="pages"
        :pagesHiRes="pagesHiRes"
        :startPage="pageNum"
        :singlePage="singlePage"
        ref="flipbook"
        @flip-left-start="onFlipLeftStart"
        @flip-left-end="onFlipLeftEnd"
        @flip-right-start="onFlipRightStart"
        @flip-right-end="onFlipRightEnd"
        @zoom-start="onZoomStart"
        @zoom-end="onZoomEnd"
      >
      </Flipbook>
    </div>
  </div>
</template>

<script>
import Flipbook from "flipbook-vue";
import axios from "axios";
import SERVER from "@/apis/UrlMapper.ts"

export default {
  components: {
    Flipbook,
  },
  data: function() {
    return {
      pages: [],
      pagesHiRes: [],
      hasMouse: true,
      pageNum: null,
      singlePage: true,
      isSelectTips: false,
      vfImages: [null],
    };
  },
  mounted() {
    axios
      .get(`${SERVER.BOARD_BASE_URL}getposts?id=${localStorage.getItem('articleId')}`)
      .then((response) => {
        response.data.forEach((e) => {
          this.vfImages.push(e.filepath);
        });
      })
      .catch(() => {
        console.log("subImg 불러오기 실패");
      });
    window.addEventListener("keydown", (ev) => {
      const { flipbook } = this.$refs;
      if (!flipbook) {
        return;
      }
      if (ev.keyCode === 37 && flipbook.canFlipLeft) {
        flipbook.flipLeft();
      }
      if (ev.keyCode === 39 && flipbook.canFlipRight) {
        return flipbook.flipRight();
      }
    }),
      setTimeout(() => {
        (this.pages = this.vfImages),
        (this.pagesHiRes = this.vfImages);
      }, 1),
      window.addEventListener("hashchange", this.setPageFromHash);
    this.setPageFromHash();
  },
  methods: {
    clickGotoPhotoView () {
      this.$router.push({name:"PhotoView"})
    },
    onFlipLeftStart(page) {
      return console.log("flip-left-start", page);
    },
    onFlipLeftEnd(page) {
      console.log("flip-left-end", page);
      return (window.location.hash = "#" + page);
    },
    onFlipRightStart(page) {
      return console.log("flip-right-start", page);
    },
    onFlipRightEnd(page) {
      console.log("flip-right-end", page);
      return (window.location.hash = "#" + page);
    },
    onZoomStart(zoom) {
      return console.log("zoom-start", zoom);
    },
    onZoomEnd(zoom) {
      return console.log("zoom-end", zoom);
    },
    setPageFromHash() {
      const n = parseInt(window.location.hash.slice(1), 10);
      if (isFinite(n)) {
        return (this.pageNum = n);
      }
    },
    clickGoBack: function () {
      this.$$router.push({name:localStorage.getItem("beforePage")})
    }
  },
};
</script>

<style scoped>
/* Flex */
.container {
  display: flex;
  height: 100vh;
  width: 100vw;
  max-width: none;
  position: absolute;
  justify-content: center;
  overflow: hidden;
  z-index: 1;
}
.container-center {
  display: flex;
  margin: 0;
  padding: 0;
  width: 80%;
  justify-content: center;
  align-items: center;
}
.profile {
  position: absolute;
  height: 40vh;
  width: 20vw;
  left: 0;
  bottom: 0;
}

.flipbook {
  height: 100%;
  width: 100%;
}
.flipbook .viewport {
  width: 100%;
  height: 100%;
}
.flipbook .bounding-box {
  box-shadow: 0 0 20px #000;
}
.credit {
  font-size: 12px;
  line-height: 20px;
  margin: 10px;
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
