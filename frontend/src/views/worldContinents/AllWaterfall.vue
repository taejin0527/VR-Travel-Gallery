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
    <v-container
      style="padding: 100px 100px 100px 100px;"
    >
      <v-row>
        <v-col
          v-for="n in 9"
          :key="n"
          class=""
          cols="4"
        >
          <img :src="`https://picsum.photos/500/300?image=${n * 5 + 10}`" alt="image error">
          
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
    <!-- 이미지 가져와서 사용방법 -->
    <!-- <div v-for="(file,index) in files" :key="index" class="file-preview-wrapper">
            
            <img :src="file.filepath" :name="file.id" @click="moveToDetail"/> 

        </div> -->
  </v-main>
  
</template>

<script>
import axios from "axios";
import SERVER from "@/apis/UrlMapper.ts"

export default {
  name:"EachWaterfall",
  data: function () {
    return {
      exhibitionContent: ['뉴욕', 'hi'],
      getContinentName: localStorage.getItem('continent'),
      popularExhibition: false,
       files: []
    }
  },
  created:function(){
    const location = localStorage.getItem('continent');
    
    axios.get(`${SERVER.BOARD_BASE_URL}allview?location=${location}`).then(response => {
                this.files = response.data;
                this.index = response.data.length;
                // files를 통해서 데이터 접근해야함
                console.log(this.files);
                console.log(this.index);
        }).catch(function(){
             console.log("안됨");
         });
  },
  methods: {
    clickChangeContinentViewButton: function () {
      this.popularExhibition = !this.popularExhibition
      this.$router.push({name:"WorldMap"})
    },
    clickGotoCreate: function () {
      this.$router.push({name:"Create"})
    }
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