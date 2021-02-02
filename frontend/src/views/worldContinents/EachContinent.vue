<template>
  <v-main>
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:20px; top:20px; color:white;"
      @click="clickChangeContinentViewButton"
    >
      <span v-if="popularExhibition"> ALL </span>
      <v-icon v-else>
        mdi-star
      </v-icon>
    </v-btn>
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
    <!-- 이미지 가져와서 사용방법 -->
    <!-- <div v-for="(file,index) in files" :key="index" class="file-preview-wrapper">
            
            <img :src="file.filepath" :name="file.id" @click="moveToDetail"/> 

        </div> -->
  </v-main>
</template>

<script>
import Oceania from "@/components/continents/Oceania.vue";
import SouthAmerica from "@/components/continents/SouthAmerica.vue";
import NorthAmerica from "@/components/continents/NorthAmerica.vue";
import Asia from "@/components/continents/Asia.vue";
import Africa from "@/components/continents/Africa.vue";
import Europe from "@/components/continents/Europe.vue";
import axios from "axios";
export default {
  name: 'EachContinent',
  data: function () {
    return {
      "getContinentName": localStorage.getItem('continent'),
      "popularExhibition": true,
      files: []
    }
  },
  created:function(){
    const location = localStorage.getItem('continent');
    axios.get('http://localhost:8080/board/allview?location='+location).then(response => {
                this.files = response.data;
                this.index = response.data.length;
                // 
                console.log(this.files[0].board);
                console.log(this.index);
        }).catch(function(){
             console.log("안됨");
         });
  },
  components: {
    Oceania,
    SouthAmerica,
    NorthAmerica,
    Asia,
    Africa,
    Europe
  },
  methods: {
    clickChangeContinentViewButton: function () {
      this.popularExhibition = !this.popularExhibition
      this.$router.push({name:"EachWaterfall"})
    }
  },
  // 해당 페이지로 이동
        // moveToDetail(e){
        //     const no = e.target.getAttribute("name");
        //     console.log(no);
        //     this.$router.push("/detail?id="+no);
        // }

}
</script>

<style scoped>

</style>