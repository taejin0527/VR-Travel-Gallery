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
                        <v-icon>mdi-shield-account</v-icon>
                        {{ authToken.username }}
                      </v-card-subtitle>
                      <v-card-actions>
                        <EditPassword />
                      </v-card-actions>
                    </div>
                    <v-avatar class="ma-3" height="120" width="200" tile>
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
                <v-card color="amber" dark>
                  <div class="d-flex flex-no-wrap justify-space-between">
                    <div>
                      <v-card-title class="headline">My Credit</v-card-title>

                      <v-card-text>
                        <v-icon>mdi-cash-multiple</v-icon>
                        <span
                          style="margin: 10px;background:#fff; color:#FFC107; font-size: 22px; border-radius: 10px"
                        >
                          {{ coinWallet }}
                        </span>
                        N-Coin
                      </v-card-text>
                      <v-card-actions>
                        <v-btn
                          @click="goEditCard"
                          class="ml-2"
                          outlined
                          rounded
                          small
                        >
                          Charge Credit
                        </v-btn>
                      </v-card-actions>
                    </div>
                    <v-avatar class="ma-3" height="120" width="200" tile>
                      <v-img
                        :src="
                          `https://bankmeister.com/assets/images/card/693.png`
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
import axios from "axios";
import SERVER from "@/apis/UrlMapper.ts";
import { mapState } from "vuex";

import EditPassword from "@/components/member/profile/EditPassword.vue";

export default {
  components: {
    EditPassword,
  },
  data() {
    return {
      coinWallet: 0,
      cards: [
        {
          title: "나의 게시물",
          src: require("@/assets/images/unsplash/noiseporn-JNuKyKXLh8U-unsplash.jpg"),
          flex: 12,
        },
      ],
      worldmapImg: require("@/assets/continents/worldmap.png"),
    };
  },
  created() {
    axios
      .get(
        `${SERVER.BASE_URL}auth/getuser?username=${this.$store.state.Auth.authToken.username}`
      )
      .then((res) => {
        this.coinWallet = res.data.money;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  computed: {
    ...mapState("Auth", ["authToken"]),
  },
  methods: {
    goEditCard() {
      this.$router.push({ name: "Pay" });
    },
  },
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
