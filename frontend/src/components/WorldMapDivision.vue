<template>
  <v-containter>
    <v-row>
      <v-col
        class="d-flex justify-center"
        cols="12"
      >
        <img
          src="../assets/worldmap_change_color.png"
          alt="image error"
        >
      </v-col>
      <v-col
        cols="2"
      >
        <img
          @mouseover="changeContinentColor"
          src="../assets/europe.png"
          alt="image error"
          @click="gotoEachContinent"
          class="hoverevent-Continent continent-opacity"
          id="europe-setting-location"
        >
      </v-col>
      <v-col
        cols="2"
      >
        <img
          @mouseover="changeContinentColor"
          src="../assets/africa.png"
          alt="image error"
          @click="gotoEachContinent"
          class="hoverevent-Continent continent-opacity"
          id="africa-setting-location"
        >
      </v-col>
      <v-col
        cols="2"
      >
        <img
          @mouseover="changeContinentColor"
          src="../assets/north_america.png"
          alt="image error"
          @click="gotoEachContinent"
          class="hoverevent-Continent continent-opacity"
          id="north_america-setting-location"
        >
      </v-col>
      <v-col
        cols="2"
      >
        <img
          @mouseover="changeContinentColor"
          src="../assets/oceania.png"
          alt="image error"
          @click="gotoEachContinent"
          class="hoverevent-Continent continent-opacity"
          id="oceania-setting-location"
        >
      </v-col>
      <v-col
        cols="2"
      >
        <img
          @mouseover="changeContinentColor"
          src="../assets/south_america.png"
          alt="image error"
          @click="gotoEachContinent"
          class="hoverevent-Continent continent-opacity"
          id="south_america-setting-location"
        >
      </v-col>
      <v-col
        cols="2"
      >
        <img
          @mouseover="changeContinentColor"
          src="../assets/asia.png"
          alt="image error"
          @click="gotoEachContinent"
          class="hoverevent-Continent continent-opacity boxes"
          id="asia-setting-location"
        >
      </v-col>
    </v-row>
  </v-containter>
  
</template>

<script>
export default {
  name: "WorldMapDivision",
  methods:{
    gotoEachContinent: function () {
      this.$router.push({name:"EachContinent"})
    },
    changeContinentColor: function () {
      this.$refs.eachContinent.removeChild("continent-opacity")
    }
  }
}
</script>

<style scoped>
.continent-opacity {
  opacity: 0;
}

.hoverevent-Continent:hover {
  opacity: 1;
  transform:scale(1.1);
  transition: 250ms;
  cursor: pointer;
}

/* 960px부터 클릭 가능 밑으로는 새로 더 작성해야 된다. */
#oceania-setting-location {
  position: relative;
  bottom: 308px;
  right: -314px;
}

#europe-setting-location {
  position: relative;
  bottom: 676px;
  right: -347px;
}

#asia-setting-location {
  position: relative;
  bottom: 680px;
  right: 281px;
}
#north_america-setting-location {
  position: relative;
  bottom: 700px;
  right: 484px;
}
#south_america-setting-location {
  position: relative;
  bottom: 373px;
  right: 532px;
}
#africa-setting-location {
  position: relative;
  bottom: 448px;
  right: -171px;
}

/* 1264px 부터 css코드 */
@media (min-width: 1264px) {
  #oceania-setting-location {
    position: relative;
    bottom: 308px;
    right: -314px;
  }

  #europe-setting-location {
    position: relative;
    bottom: 676px;
    right: -487px;
  }

  #asia-setting-location {
    position: relative;
    bottom: 680px;
    right: 378px;
  }
  #north_america-setting-location {
    position: relative;
    bottom: 700px;
    right: 436px;
  }
  #south_america-setting-location {
    position: relative;
    bottom: 373px;
    right: 580px;
  }
  #africa-setting-location {
    position: relative;
    bottom: 448px;
    right: -267px;
  }
}

/* 1904px 부터 css코드 */
@media (min-width: 1904px) {
  #oceania-setting-location {
    position: relative;
    bottom: 308px;
    right: -314px;
  }

  #europe-setting-location {
    position: relative;
    bottom: 676px;
    right: -791px;
  }

  #asia-setting-location {
    position: relative;
    bottom: 680px;
    right: 574px;
  }
  #north_america-setting-location {
    position: relative;
    bottom: 700px;
    right: 335px;
  }
  #south_america-setting-location {
    position: relative;
    bottom: 373px;
    right: 679px;
  }
  #africa-setting-location {
    position: relative;
    bottom: 448px;
    right: -466px;
  }
}



</style>