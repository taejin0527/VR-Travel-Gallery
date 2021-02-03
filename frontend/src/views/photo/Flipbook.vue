<template>
  <div class="container">
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
    <div class="profile">
      <MetaCard
        :exhibitionImage="exhibitionImage"
        :exhibitionTitle="exhibitionTitle"
        :exhibitionContent="exhibitionContent"
        :exhibitionLocation="exhibitionLocation"
        :exhibitionAuthor="exhibitionAuthor"
        :likeCount="likeCount"
      />
    </div>
  </div>
</template>

<script>
import Flipbook from "flipbook-vue";
import MetaCard from "@/components/photo/MetaCard.vue";

export default {
  components: {
    Flipbook,
    MetaCard,
  },
  data: function() {
    return {
      pages: [],
      pagesHiRes: [],
      hasMouse: true,
      pageNum: null,
      singlePage: true,

      exhibitionImage: "https://cdn.vuetifyjs.com/images/cards/cooking.png",
      exhibitionTitle: "Test용",
      exhibitionContent: ["태그 1", "태그 2"],
      exhibitionLocation: "",
      exhibitionAuthor: "ssafy",
      likeCount: 168,
    };
  },
  mounted() {
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
        (this.pages = [
          null,
          require("@/assets/images/example/1.jpg"),
          require("@/assets/images/example/2.jpg"),
          require("@/assets/images/example/3.jpg"),
          require("@/assets/images/example/4.jpg"),
          require("@/assets/images/example/5.jpg"),
          require("@/assets/images/example/6.jpg"),
        ]),
          (this.pagesHiRes = [
            null,
            require("@/assets/images/example/1.jpg"),
            require("@/assets/images/example/2.jpg"),
            require("@/assets/images/example/3.jpg"),
            require("@/assets/images/example/4.jpg"),
            require("@/assets/images/example/5.jpg"),
          ]);
      }, 1),
      window.addEventListener("hashchange", this.setPageFromHash);
    this.setPageFromHash();
  },
  methods: {
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
  background: #333;
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
</style>
