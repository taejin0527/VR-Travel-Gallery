<template>
  <v-container>
    hi
  </v-container>
</template>

<script>
import axios from "axios";
import SERVER from "@/apis/UrlMapper.ts";


export default {
  name:"GetUserArticles",
  data: function () {

  },
  methods: {
    getArticles: function () {
      axios
        .get(
          `${SERVER.BOARD_BASE_URL}paging?location=${locations[index]}&num=${realPage}`
        )
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.error(err)
        })
    }
  }
}
</script>

<style>

</style>