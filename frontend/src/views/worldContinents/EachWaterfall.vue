<template>
  <v-main>
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:95px; top:20px; color:white;"
      @click="clickGotoCreate"
    >
      <v-icon>
        mdi-plus
      </v-icon>
    </v-btn>
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
      <NorthAmerica
        :images = "images"
        :tags = "tags"
      />
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
import Oceania from "@/components/waterfall/Oceania.vue";
import SouthAmerica from "@/components/waterfall/SouthAmerica.vue";
import NorthAmerica from "@/components/waterfall/NorthAmerica.vue";
import Asia from "@/components/waterfall/Asia.vue";
import Africa from "@/components/waterfall/Africa.vue";
import Europe from "@/components/waterfall/Europe.vue";
import axios from "axios";

export default {
  name:"EachWaterfall",
  data: function () {
    return {
      "getContinentName": localStorage.getItem('continent'),
      "popularExhibition": false,
       images: [],
       tags: [],
    }
  },
  created:function(){
    const location = localStorage.getItem('continent');
    axios.get('http://localhost:8080/board/allview?location='+location).then(response => {
          this.files = []
          this.tags = []
          for (let index = 0; index < response.data.length; index++) {
            this.images.push(response.data[index].filePath);
            // 동걸 이거 보니까 태그가 있는데 데이터가 안들어간다. 수정해야될듯함돵.
            this.tags.push(response.data[index].tags)
          }
          console.log(this.tags)
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
      this.$router.push({name:"EachContinent"})
    },
    clickGotoCreate: function () {
      this.$router.push({name:"Create"})
    },
     // 해당 페이지로 이동
        // moveToDetail(e){
        //     const no = e.target.getAttribute("name");
        //     console.log(no);
        //     this.$router.push("/detail?id="+no);
        // }
  }
}
</script>

<style>

</style>