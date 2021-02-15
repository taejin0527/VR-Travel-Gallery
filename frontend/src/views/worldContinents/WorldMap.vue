<template>
  <!-- App.vue -->
  <div>
    <MobileWorldMap
      v-if="windowWidth < 500 || windowHeight < 450"
    />
    <div
      v-else
    >
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
      <!-- 검색 버튼 및 입력창 -->
      <div
        v-if="isSelectSearch && windowWidth > 500"
        style="
                position: fixed;
                height: 50px;
                margin: 0;
                padding: 0;
                width: 300px;
                top: 23px;
                right: 170px;
                transition:0.5s;
                z-index: 1;
                background-color:#DDA288;
                border-radius: 3px;
              "
      >
        </div>
        <div
          v-if="isSelectSearch && windowWidth > 500"
          style="position:fixed;
                  width: 270px;
                  top: 16px;
                  right: 185px;
                  z-index: 2;
                  color:white;
                "   
        >
          <div
            class="d-flex align-start justify-center"
          >
            <v-text-field
              v-model="searchData"
              color="white"
              placeholder="장소나 태그를 입력하세요."
              append-outer-icon="mdi-airplane-takeoff"
              @keydown.enter="searchKeyword"
              @click:append-outer="searchKeyword"
              style=""
              dark
            ></v-text-field>
          </div>
        </div>
        
        <v-btn
          v-if="windowWidth > 500 && windowHeight > 450 && !isSelectSearch"
          elevation="3"
          fab
          color="#DDA288"
          style="position:fixed; right:170px; top:20px; color:white; transition:0.5s; z-index: 2;"
          @click="isSelectSearch = true"
        >
          <v-icon>
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
    </div>
  </div>
</template>

<script>
import WorldMapDivision from "@/components/WorldMapDivision.vue";
import SideNavBar from "@/components/navigation/SideNavBar.vue";
import MobileWorldMap from "@/components/mobile/WorldMap.vue";

export default {
  name: "WorldMap",
  data: function() {
    return {
      popularExhibition: true,
      isSelectTips: false,
      searchData:"",
      isSelectSearch:false,
      windowWidth: window.innerWidth,
      windowHeight: window.innerHeight,
    }
  },
  components: {
    WorldMapDivision,
    SideNavBar,
    MobileWorldMap
  },
  created: function () {
    localStorage.setItem('page', "WorldMap")
  },
  methods: {
    clickChangeContinentViewButton: function() {
      this.popularExhibition = !this.popularExhibition;
      this.$router.push({ name: "AllWaterfall" });
    },
    clickGotoCreate: function() {
      this.$router.push({ name: "Create" });
    },
    searchKeyword: function() {
      if (this.searchData === "") {
        alert('검색어를 입력해주세요.')
      }
      else {
        this.$router.replace("/AllWaterfall?searchData=" + this.searchData);
      }
    }
  }
};
</script>

<style scoped>

</style>
