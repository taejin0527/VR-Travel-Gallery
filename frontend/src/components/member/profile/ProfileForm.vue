<template>
  <v-container class="profileform">
    <!-- Basic profile -->
    <v-card>
      <v-container fluid>
        <v-row dense>
          <v-col cols="6" class="mt-3">
            <v-row no-gutters>
              <v-col cols="12">
                <v-card color="#8593ae" dark>
                  <div class="d-flex flex-no-wrap justify-space-between">
                    <div>
                      <v-card-title class="headline">My Profile</v-card-title>

                      <v-card-subtitle>
                        profile
                      </v-card-subtitle>
                      <v-card-actions>
                        <v-btn
                          @click="goEditProfile"
                          class="ml-2 mt-5"
                          outlined
                          rounded
                          small
                        >
                          Edit profile
                        </v-btn>
                      </v-card-actions>
                    </div>
                    <v-avatar class="ma-3" height="120" width="200" tile>
                      <v-img
                        src="data:image/png;base64,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"
                      >
                      </v-img>
                    </v-avatar>
                  </div>
                </v-card>
              </v-col>
            </v-row>

            <v-spacer>
              <br />
            </v-spacer>

            <v-row no-gutters>
              <v-col cols="12">
                <v-card color="#8593ae" dark>
                  <div class="d-flex flex-no-wrap justify-space-between">
                    <div>
                      <v-card-title class="headline">My Credit</v-card-title>

                      <v-card-subtitle>
                        cardnumber
                      </v-card-subtitle>
                      <v-card-actions>
                        <v-btn
                          @click="goEditCard"
                          class="ml-2 mt-5"
                          outlined
                          rounded
                          small
                        >
                          Edit card
                        </v-btn>
                      </v-card-actions>
                    </div>
                    <v-avatar class="ma-3" height="120" width="200" tile>
                      <v-img
                        :src="
                          `https://www.shinhancard.com/pconts/images/contents/card/plate/cdCreditAXFBZE.png`
                        "
                      ></v-img>
                    </v-avatar>
                  </div>
                </v-card>
              </v-col>
            </v-row>
          </v-col>
          <v-col cols="6">
            <v-card color="#7e675e" dark>
              <div>
                <v-card-title class="headline">Worldmap</v-card-title>

                <v-card-subtitle>
                  worldmap
                </v-card-subtitle>

                <div>
                  <v-img
                    style="margin: auto"
                    :src="worldmapImg"
                    max-width="500px"
                  ></v-img>
                </div>
              </div>
            </v-card>
          </v-col>
        </v-row>
      </v-container>

      <!-- chart -->
      <v-container fluid>
        <v-row dense>
          <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon>mdi-bookmark</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon>mdi-share-variant</v-icon>
              </v-btn>
            </v-card-actions>
            <v-card>
              <v-img
                :src="card.src"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="200px"
              >
                <v-card-title v-text="card.title"></v-card-title>
              </v-img>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    cards: [
      {
        title: "나의 게시물",
        src: require("@/assets/images/unsplash/noiseporn-JNuKyKXLh8U-unsplash.jpg"),
        flex: 12
      },
      {
        title: "Follow",
        src: require("@/assets/images/unsplash/freestocks-Y9mWkERHYCU-unsplash.jpg"),
        flex: 6
      },
      {
        title: "Follwer",
        src: require("@/assets/images/unsplash/possessed-photography-ChiM2OuX5JQ-unsplash.jpg"),
        flex: 6
      }
    ],
    items: [
      {
        color: "#1F7087",
        src: "https://cdn.vuetifyjs.com/images/cards/foster.jpg",
        title: "Supermodel",
        artist: "Foster the People"
      },
      {
        color: "#952175",
        src: "https://cdn.vuetifyjs.com/images/cards/halcyon.png",
        title: "Halcyon Days",
        artist: "Ellie Goulding"
      }
    ],
    worldmapImg: require("@/assets/continents/worldmap.png")
  }),
  methods: {
    goEditProfile() {
      this.$router.push({ name: "ProfileEdit" });
    },
    goEditCard() {
      this.$router.push({ name: "Paycard" });
    }
  }
};
</script>

<style scoped>
.container,
.row,
.col {
  background: #5a4e4d;
}
.profileform {
  width: 80%;
}
</style>
