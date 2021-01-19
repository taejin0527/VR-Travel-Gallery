<template>
  <!-- App.vue -->
  <v-main>
    <br>
    <v-container>
      <v-row>
        <v-col
          cols="12"
        >
          <!-- 월드 맵 이미지 -->
          <div style="text-align: center;">
            <img
              src="../../assets/worldMap.png"
              alt="image error"
              @click="gotoEachContinent"
              class="hoverevent-Button"
            >
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name:"WorldMap",
  methods:{
    gotoEachContinent: function () {
      this.$router.push({name:"EachContinent"})
    }
  }
}
</script>

<style>

</style>