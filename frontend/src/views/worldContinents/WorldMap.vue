<template>
  <!-- App.vue -->
  <div>
    <SideNavBar />
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:95px; top:20px; color:white;"
      @click="clickGotoCreate"
    >
      <v-icon
        v-intro="'새 게시글 작성'"
        v-intro-position="'left'"
        v-intro-step="4"
      >
        mdi-plus
      </v-icon>
    </v-btn>
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:20px; top:20px; color:white;"
      @click="clickChangeContinentViewButton"
      v-intro="'전체 게시글 보기'"
      v-intro-position="'top'"
      v-intro-step="5"
    >
      <span v-if="popularExhibition"> ALL </span>
      <v-icon v-else>
        mdi-star
      </v-icon>
    </v-btn>
    <!-- 검색 버튼 및 입력창 -->
    <v-lazy
      min-height="200"
      transition="slide-x-reverse-transition"
      v-if="isSelectSearch"
      style="
              position: fixed;
              height: 10%;
              margin: 0;
              padding: 0;
              width: 300px;
              top: 23px;
              right: 170px;
              z-index: 101;
              transition:0.5s;
            "
    >
      <v-card color="#DDA288" height="50px" width="300px" dark>
        <v-card-text>
          <v-text-field
            v-model="searchData"
            color="white"
            placeholder="장소나 태그를 입력하세요."
            append-outer-icon="mdi-airplane-takeoff"
            @keydown.enter="searchKeyword"
            @click:append-outer="searchKeyword"
            style="position:relative; bottom:24px;"
          ></v-text-field>
        </v-card-text>
      </v-card>
    </v-lazy>

    <v-btn
      v-else
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:170px; top:20px; color:white; transition:0.5s;"
      @click="isSelectSearch = true"
    >
      <v-icon
        v-intro="'게시글 검색(태그)'"
        v-intro-position="'left'"
        v-intro-step="3"
      >
        mdi-image-search
      </v-icon>
    </v-btn>

    <v-container>
      <v-row>
        <v-col cols="12">
          <!-- 월드 맵 나누기 -->
          <WorldMapDivision />
        </v-col>
      </v-row>
    </v-container>

    <!-- 오른쪽 상단 Tips 픽스 -->
    <div class="tipBtn">
      <img
        src="@/assets/3DHelp3.png"
        alt=""
        width="80px"
        :class="{ 'select-tips-transition': isSelectTips }"
        @mouseover="isSelectTips = true"
        @mouseleave="isSelectTips = false"
        @click="activeIntro"
      />
    </div>
  </div>
</template>

<script>
import WorldMapDivision from "@/components/WorldMapDivision.vue";
import SideNavBar from "@/components/navigation/SideNavBar.vue";

export default {
  name: "WorldMap",
  data: function() {
    return {
      popularExhibition: true,
      isSelectTips: false,
      searchData: "",
      isSelectSearch: false,
    };
  },
  components: {
    WorldMapDivision,
    SideNavBar,
  },
  created: function() {
    localStorage.setItem("page", "WorldMap");
  },
  methods: {
    activeIntro: function() {
      this.$intro().start(); // start the guide
      this.$intro().showHints(); // show hints
    },
    clickChangeContinentViewButton: function() {
      this.popularExhibition = !this.popularExhibition;
      this.$router.push({ name: "AllWaterfall" });
    },
    clickGotoCreate: function() {
      this.$router.push({ name: "Create" });
    },
    searchKeyword: function() {
      if (this.searchData === "") {
        alert("검색어를 입력해주세요.");
      } else {
        alert(`검색어 : ${this.searchData} -> 백엔드 이으면 댐당`);
      }
    },
  },
};
</script>

<style scoped>
.tipBtn {
  display: flex;
  justify-content: center;
  align-items: center;
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
