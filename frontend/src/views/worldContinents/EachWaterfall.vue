<template>
  <v-main>
    <SideNavBar/>
    <!-- 게시물 작성 페이지로 가는 버튼 -->
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

    <!-- 각 대륙맵 및 인기 전시 카드 페이지로 돌아가는 버튼 -->
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
      <Oceania
        :images = "images"
        :tags = "tags"
      />
    </div>
    <div v-else-if="this.getContinentName == 'asia'">
      <Asia
        :images = "images"
        :tags = "tags"
      />
    </div>
    <div v-else-if="this.getContinentName == 'northAmerica'">
      <NorthAmerica
        :images = "images"
        :tags = "tags"
      />
    </div>
    <div v-else-if="this.getContinentName == 'southAmerica'">
      <SouthAmerica
        :images = "images"
        :tags = "tags"
      />
    </div>
    <div v-else-if="this.getContinentName == 'europe'">
      <Europe
        :images = "images"
        :tags = "tags"
      />
    </div>
    <div v-else>
      <Africa
        :images = "images"
        :tags = "tags"
      />
    </div>
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
import SERVER from "@/apis/UrlMapper.ts"
import SideNavBar from "@/components/navigation/SideNavBar.vue";

export default {
  name:"EachWaterfall",
  data: function () {
    return {
      getContinentName: localStorage.getItem('continent'), // 대륙별로 나누는 변수
      popularExhibition: false, // 버튼 바꾸기 변수
      images: [], // 이미지 데이터 리스트
      tags: [], // 태그 데이터 리스트
    }
  },
  // 아예 처음 이 페이지가 생성될 때부터 데이터를 가져옴.
  // 마찬가지로 Blob 디코딩과 더보기 버튼으로 몇개만 가져오게 끔, 수정해야됨.
  created:function(){
    localStorage.setItem('page', "EachWaterfall")
    const location = localStorage.getItem('continent');
    console.log(location)
    axios.get(`${SERVER.BOARD_BASE_URL}allview?location=${location}`).then(response => {
          for (let index = 0; index < response.data.length; index++) {
            this.images.push(response.data[index].filePath);
            this.tags.push(response.data[index].tags)
            console.log(response.data)
          }
        }).catch(function(){
          console.log(`${location} DB 이미지 및 태그 불러오기 실패`);
        });
  },
  // 대륙 컴포넌트
  components: {
    Oceania,
    SouthAmerica,
    NorthAmerica,
    Asia,
    Africa,
    Europe,
    SideNavBar
  },
  methods: {
    // 각 대륙으로 이동
    clickChangeContinentViewButton: function () {
      this.popularExhibition = !this.popularExhibition
      this.$router.push({name:"EachContinent"})
    },
    // 게시물 작성 페이지로 이동
    clickGotoCreate: function () {
      this.$router.push({name:"Create"})
    },
  }
}
</script>

<style>

</style>