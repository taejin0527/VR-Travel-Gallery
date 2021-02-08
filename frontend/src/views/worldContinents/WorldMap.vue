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
      "popularExhibition": true,
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

<style></style>
