<template>
  <!-- App.vue -->
  <div>
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
  </div>
</template>

<script>
import WorldMapDivision from "@/components/WorldMapDivision.vue";

export default {
  name:"WorldMap",
  components: {
    WorldMapDivision
  },
}
</script>

<style>

</style>