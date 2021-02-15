<template>
  <v-card
    max-width="100%"
    height="100%"
    class="mx-auto"
    style="background-color:#5a4e4d;"
  >

    <br><br><br><br><br><br>
    <v-container>
      <v-row dense>
        <v-col
          cols="12"
        >
          <v-card
            color="#DDA288"
            dark
            elevation="10"
            style="position:fixed; left:3%; top:20%; width:94%;"
            @click="gotoVR"
          >
            <div class="d-flex flex-no-wrap justify-space-between">
              <div>
                <v-card-title
                  class="headline"
                >VR</v-card-title>
                <v-divider dark></v-divider>
                <v-card-subtitle
                  style="width:160px;"
                >
                  VR 전시관 감상
                  <pre></pre>
                  3D 전시관 감상
                </v-card-subtitle>

                <v-card-actions>
                  <v-btn
                    class="ml-2"
                    outlined
                    rounded
                    small
                  >
                    GOto VR Expo
                  </v-btn>
                </v-card-actions>
              </div>

              <v-avatar
                class="ma-3"
                size="160"
                tile
              >
                <v-img src="@/assets/main/VRIcon.png"></v-img>
              </v-avatar>
            </div>
          </v-card>
          <br><br><br><br><br>
        </v-col>
        <v-col
          cols="12"
        >
          <v-card
            color="#DDA288"
            style="position:fixed; left:3%; top:55%; width:94%;"
            dark
            elevation="10"
            @click="gotoWorldmap"
          >
            <div class="d-flex flex-no-wrap justify-space-between">
              <div>
                <v-card-title
                  class="headline"
                >사진</v-card-title>
                <v-divider dark></v-divider>
                <v-card-subtitle
                  style="width:160px;"
                >
                  여행 사진 감상
                  <pre></pre>
                  여행지별 사진 분류
                </v-card-subtitle>

                <v-card-actions>
                  <v-btn
                    class="ml-2"
                    outlined
                    rounded
                    small
                  >
                    GOto WorldMap
                  </v-btn>
                </v-card-actions>
              </div>

              <v-avatar
                class="ma-3"
                size="160"
                tile
              >
                <v-img src="@/assets/worldmap/worldmap.svg"></v-img>
              </v-avatar>
            </div>
          </v-card>
          <br><br><br><br><br>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
export default {
  name:"MobileMain",
  data: () => ({
  items: [
    {
      color: '#1F7087',
      src: 'https://cdn.vuetifyjs.com/images/cards/foster.jpg',
      title: 'Supermodel',
      artist: 'Foster the People',
    },
    {
      color: '#952175',
      src: 'https://cdn.vuetifyjs.com/images/cards/halcyon.png',
      title: 'Halcyon Days',
      artist: 'Ellie Goulding',
    },
  ],
  }),
  methods: {
    gotoVR: function () {
      console.log('VR URL 넣기')
    },
    gotoWorldmap: function () {
      this.$router.push({name:'WorldMap'})
    },
  }
}
</script>

<style>

</style>