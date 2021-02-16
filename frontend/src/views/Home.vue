<template>
  <!-- App.vue -->
  <v-main>
    <MobileMain
      v-if="windowWidth < 500 || windowHeight < 450"
    />
    <Main
      v-else
    />
  </v-main>
</template>

<script lang="ts">
import Main from "@/components/Main.vue";
import MobileMain from "@/components/mobile/Main.vue";

export default {
<<<<<<< HEAD
  name: "Home",
=======
  name:"Home",
  data: function () {
    return {
      windowWidth: window.innerWidth,
      windowHeight: window.innerHeight,
    }
  },
>>>>>>> 80e730a617a70943bcc31f95d8720234991550ce
  components: {
    Main,
    MobileMain
  }
};
</script>
