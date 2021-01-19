<template>
  <!-- App.vue -->
  <v-main>
    <!-- 랜딩 페이지 -->
    <Main />
  </v-main>
</template>

<script lang="ts">
import Main from "@/components/Main.vue";

export default {
  name:"Home",
  components: {
    Main
  }
}
</script>