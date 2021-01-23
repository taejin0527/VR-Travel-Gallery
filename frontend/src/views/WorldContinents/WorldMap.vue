<template>
  <!-- App.vue -->
  <v-main>
    <br>
    <v-container>
      <v-row>
        <v-col
          cols="12"
        >
          <!-- 월드 맵 나누기 -->
          <WorldMapDivision/>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import WorldMapDivision from "@/components/WorldMapDivision.vue";

export default {
  name:"WorldMap",
  components: {
    WorldMapDivision
  } 
}
</script>

<style>

</style>