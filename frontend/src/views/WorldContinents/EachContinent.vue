<template>
  <v-main>
    <!-- Refactoring 해야 됨 -->
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
import Oceania from "@/components/Continents/Oceania.vue";
import SouthAmerica from "@/components/Continents/SouthAmerica.vue";
import NorthAmerica from "@/components/Continents/NorthAmerica.vue";
import Asia from "@/components/Continents/Asia.vue";
import Africa from "@/components/Continents/Africa.vue";
import Europe from "@/components/Continents/Europe.vue";

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