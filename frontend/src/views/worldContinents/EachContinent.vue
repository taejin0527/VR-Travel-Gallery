<template>
  <v-main>
    <!-- 각 대륙별로 이미지 가져오기 -->
    <div v-if="this.getContinentName == 'oceania'">
      <Oceania/>
    </div>
    <div v-else-if="this.getContinentName == 'asia'">
      <Asia/>
    </div>
    <div v-else-if="this.getContinentName == 'northAmerica'">
      <NorthAmerica/>
    </div>
    <div v-else-if="this.getContinentName == 'southAmerica'">
      <SouthAmerica/>
    </div>
    <div v-else-if="this.getContinentName == 'europe'">
      <Europe/>
    </div>
    <div v-else>
      <Africa/>
    </div>
  </v-main>
</template>

<script>
import Oceania from "@/components/continents/Oceania.vue";
import SouthAmerica from "@/components/continents/SouthAmerica.vue";
import NorthAmerica from "@/components/continents/NorthAmerica.vue";
import Asia from "@/components/continents/Asia.vue";
import Africa from "@/components/continents/Africa.vue";
import Europe from "@/components/continents/Europe.vue";

export default {
  name: 'EachContinent',
  data: function () {
    return {
      "getContinentName": localStorage.getItem('continent')
    }
  },
  components: {
    Oceania,
    SouthAmerica,
    NorthAmerica,
    Asia,
    Africa,
    Europe
  }

}
</script>

<style scoped>

</style>