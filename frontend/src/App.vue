<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center">
        주말은 쉽니다.
      </div>

      <v-spacer></v-spacer>
      <router-link style="color : white" to="/login">Login</router-link>
      <!-- <v-btn
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
        text
      >
        <span class="mr-2">Login</span>
      </v-btn> -->
    </v-app-bar>

    <Home />
  </v-app>
</template>

<script lang="ts">
import Vue from "vue";
import Home from "@/views/Home.vue";
// import Login from "@/views/Login.vue";


export default Vue.extend({
  name: "App",
  components: {
    Home
    // Login
  },
  data: () => ({
    //
  })
});
</script>
