<template>
  
  <v-container
    style="padding: 100px 100px 100px 100px;"
  >
    <v-row>
      <v-col
        v-for="(image, idx) in images"
        :key="idx"
        class=""
        cols="4"
      >
        <img :src="`${image}`" alt="image error" width="500px">
        
        <br>
        <v-chip-group
          class="accent-4 white--text"
          column
        >
          <v-chip
            v-for="(item, idx) in exhibitionContent"
            :key="idx"
            style="background-color:#DD6288; color:white;"
          >
            {{item}}
          </v-chip>
        </v-chip-group>        
      </v-col>
    </v-row>
  </v-container>
  
</template>

<script>
export default {
  name:"NorthAmerica",
  data: function () {
    return {
      exhibitionContent: ['뉴욕', 'hi']
    }
  },
  props: {
    images:[Array],
    tags:[Array],
  }
}
</script>

<style>

</style>