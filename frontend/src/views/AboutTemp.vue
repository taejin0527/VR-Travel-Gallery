<template>
  <div style="height: 100vh">
    <SideNavBar />
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:95px; top:20px; color:white;"
      @click="clickGotoWorldmap"
    >
      <v-icon>
        mdi-map-marker-radius
      </v-icon>
    </v-btn>

    <v-container
      fluid
      ma-0
      pa-0
      fill-height
      :style="{
        'background-image':
          'url(' + require('@/assets/main/NUVOchangeColorShallow.png') + ')',
        'background-position': 'center',
      }"
    >
      <!--=============================== Contents[FIXED] =============================== -->
      <!-- SOCIAL MEDIA(fixed)   =============================== -->
      <div class="media">
        <ul>
          <li>
            <a
              style="color: inherit;"
              href="https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp"
              >SSAFY</a
            >
          </li>
          <li>
            <a
              style="color: inherit;"
              href="https://lab.ssafy.com/s04-webmobile2-sub3/s04p13d110"
              >Github</a
            >
          </li>
          <li>@2021</li>
        </ul>
      </div>
      <!-- SCROLLDOWN(fixed)    =============================== -->
      <div class="scrolldown">scroll down</div>

      <!--=============================== Contents[SCROLL] =============================== -->
      <v-row align="center" justify="center" style="height:100%;">
        <!-- ===============================  TEXT -->
        <v-col md="auto" class="text">
          <h1>
            <span class="hidetext">The Greatest Gallery</span>
          </h1>
          <p>
            <span class="hidetext">Share your memories with NUVO</span>
          </p>
        </v-col>
      </v-row>

      <!-- ===============================  IMAGES -->
      <v-row justify="center">
        <div class="imgs">
          <div class="img-wrapper01">
            <div class="img01"></div>
            <div class="block"></div>
          </div>
          <div class="img-wrapper02">
            <div class="img02"></div>
            <div class="block"></div>
          </div>
        </div>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import SideNavBar from "@/components/navigation/SideNavBar.vue";

export default {
  components: {
    SideNavBar,
  },
  data() {
    return {
      section: true,
    };
  },
  methods: {
    clickGotoWorldmap: function() {
      this.$router.push({ name: "WorldMap" });
    },
  },
};
</script>

<style scoped>
* {
  color: white;
}
ul {
  list-style: none;
}

/* SOCIAL MEDIA
=========================== */
.media {
  position: fixed;
  top: 350px;
  right: -90px;
  transform: rotate(-90deg);
}

.media ul li {
  display: inline-block;
  padding: 0 20px;
  font-size: 13px;
  font-weight: 500;
  letter-spacing: 1px;
  cursor: pointer;
}

/* SCROLLDOWN
=========================== */
.scrolldown {
  font-size: 10px;
  font-weight: 500;
  text-transform: uppercase;
  letter-spacing: 5px;

  position: fixed;
  bottom: 110px;
  left: -60px;
  transform: rotate(-90deg);
}

.scrolldown::before {
  content: "";
  border: 1px solid #fff;
  width: 100px;
  margin: 0 20px 0 0;
  transform: translateY(-3px);
  opacity: 0.5;
  display: inline-block;
}

/* TEXT
=========================== */
.text h1 {
  font-size: 120px;
  font-family: "Tinos";
  font-weight: lighter;
  letter-spacing: 5px;

  height: 150px;
  width: 100%;
}

.text p {
  font-size: 40px;
  font-family: "Tinos";
  text-transform: uppercase;
  letter-spacing: 5px;
  font-weight: lighter;

  height: 50px;
  width: 100%;
}

/* IMAGES
=========================== */
.img-wrapper01 .img01 {
  background: url("~@/assets/worldmap/worldmap.svg") no-repeat;
  background-position: 50%;
  background-size: cover;
  height: 30vh;
  width: 20vw;
}

.img-wrapper02 .img02 {
  background: url("~@/assets/main/VRIcon.png") no-repeat;
  background-position: 50%;
  background-size: cover;
  height: 30vh;
  width: 20vw;
}
</style>
