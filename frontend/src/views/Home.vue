<template>
  <!-- App.vue -->
  <!-- Sizes your content based upon application components -->
  <v-main>
    <!-- Provides the application the proper gutter -->
    <v-container fluid ma-0 pa-0 fill-height>
      <v-row
        align="center" justify="center"
      >
          <v-col>
            <div class="d-flex justify-center mb-6">
              <v-btn
              elevation="2"
            >Get Started</v-btn>
            </div>
            
          </v-col>
        </v-row>
      <v-layout row wrap align-center justify-center>
        
        <v-flex lg6>
          
        </v-flex>
      </v-layout>
      
      <!-- <Main /> -->
      <!-- If using vue-router -->
      <!-- <router-view></router-view> -->
    </v-container>
  </v-main>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Main from "@/components/Main.vue"; // @ is an alias to /src

@Component({
  components: {
    Main
  }
})
export default class Home extends Vue {}
</script>
