<template>
  <!-- App.vue -->
  <div>
    <SideNavBar/>
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:95px; top:20px; color:white;"
      @click="clickGotoCreate"
    >
      <v-icon>
        mdi-plus
      </v-icon>
    </v-btn>
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:20px; top:20px; color:white;"
      @click="clickChangeContinentViewButton"
    >
      <span v-if="popularExhibition"> ALL </span>
      <v-icon v-else>
        mdi-star
      </v-icon>
    </v-btn>
    <!-- 오른쪽 상단 Tips 픽스 -->
    <div
      style="
              position: fixed;
              height: 10%;
              margin: 0;
              padding: 0;
              width: 100px;
              top: 15px;
              right: 185px;
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
    <v-container>

      <v-row>
        <v-col cols="12">
          <!-- 월드 맵 나누기 -->
          <WorldMapDivision />
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import WorldMapDivision from "@/components/WorldMapDivision.vue";
import SideNavBar from "@/components/navigation/SideNavBar.vue";

export default {
  name:"WorldMap",
  data: function () {
    return {
      popularExhibition: true,
      isSelectTips: false
    }
  },
  components: {
    WorldMapDivision,
    SideNavBar
  },
  created: function () {
    localStorage.setItem('page', "WorldMap")
  },
  methods: {
    clickChangeContinentViewButton: function () {
      this.popularExhibition = !this.popularExhibition
      this.$router.push({name:"AllWaterfall"})
    },
    clickGotoCreate: function () {
      this.$router.push({name:"Create"})
    }
  }
};
</script>

<style scoped>
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
