<template>
  <v-container>
    <br><br><br><br>
    <v-row>
      <v-col
        cols="6"
      >
        <v-card
          color="#DDA288"
          dark
          elevation="10"
          @click="gotoWorldmap"
        >
          <div class="d-flex flex-no-wrap justify-space-between">
            <div>
              <v-card-title
                class="headline"
              >North <pre></pre> America</v-card-title>
              <v-divider dark></v-divider>
              <v-card-subtitle>
                미국, 그린란드, 캐나다 등
              </v-card-subtitle>
            </div>
          </div>
        </v-card>
      </v-col>
      <v-col
        cols="6"
      >
        <v-card
          color="#DDA288"
          dark
          elevation="10"
          @click="gotoWorldmap"
        >
          <div class="d-flex flex-no-wrap justify-space-between">
            <div>
              <v-card-title
                class="headline"
              >South <pre></pre> America</v-card-title>
              <v-divider dark></v-divider>
              <v-card-subtitle>
                페루, 볼리비아, 아르헨티나, 브라질 등
              </v-card-subtitle>
            </div>
          </div>
        </v-card>
      </v-col>
      <v-col
        cols="6"
      >
        <v-card
          color="#DDA288"
          dark
          elevation="10"
          @click="gotoWorldmap"
        >
          <div class="d-flex flex-no-wrap justify-space-between">
            <div>
              <v-card-title
                class="headline"
              >Africa</v-card-title>
              <v-divider dark></v-divider>
              <v-card-subtitle>
                페루, 볼리비아, 아르헨티나, 브라질 등
              </v-card-subtitle>
            </div>
          </div>
        </v-card>
      </v-col>
      <v-col
        cols="6"
      >
        <v-card
          color="#DDA288"
          dark
          elevation="10"
          @click="gotoWorldmap"
        >
          <div class="d-flex flex-no-wrap justify-space-between">
            <div>
              <v-card-title
                class="headline"
              >Asia</v-card-title>
              <v-divider dark></v-divider>
              <v-card-subtitle>
                페루, 볼리비아, 아르헨티나, 브라질 등
              </v-card-subtitle>
            </div>
          </div>
        </v-card>
      </v-col>
      <v-col
        cols="6"
      >
        <v-card
          color="#DDA288"
          dark
          elevation="10"
          @click="gotoWorldmap"
        >
          <div class="d-flex flex-no-wrap justify-space-between">
            <div>
              <v-card-title
                class="headline"
              >Europe</v-card-title>
              <v-divider dark></v-divider>
              <v-card-subtitle>
                페루, 볼리비아, 아르헨티나, 브라질 등
              </v-card-subtitle>
            </div>
          </div>
        </v-card>
      </v-col>
      <v-col
        cols="6"
      >
        <v-card
          color="#DDA288"
          dark
          elevation="10"
          @click="gotoWorldmap"
        >
          <div class="d-flex flex-no-wrap justify-space-between">
            <div>
              <v-card-title
                class="headline"
              >Oceania</v-card-title>
              <v-divider dark></v-divider>
              <v-card-subtitle>
                페루, 볼리비아, 아르헨티나, 브라질 등
              </v-card-subtitle>
            </div>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name:"MobileWorldMap",
  data: function () {
    return {
    }
  }
}
</script>

<style>

</style>