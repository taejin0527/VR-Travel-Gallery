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

    <!-- 월드맵으로 돌아가는 버튼 -->
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

    <!-- 전체 사진 불러오기. -->
    <!-- 단, 모든 사진을 불러오기 때문에 더 보기 버튼을 만들어서 15개씩 불러오는 방향을 잡아야 할 듯. -->
    <v-container
      class="adjust-grid-container"
    >
      <v-row>
        <v-col
          v-for="(image, idx) in images"
          :key="idx"
          cols="12"
          sm="6"
          md="4"
        >
          <!-- 이미지 가져오는 코드 -->
          <!-- Blob 처리로 URL을 가져와 이미지를 보여줄 예정 -->
          <img
            :src="`${image}`" alt="image error"
            :class="{'adjust-grid-image':true, 'opacity-event-for-waterfall':true}"
            style="cursor:pointer;"
            @click="gotoSelectArticle(idx)"
          >
          <br>
          <!-- 태그 보여주는 코드 -->
          <v-chip-group
            class="accent-4 white--text"
            style="text-algin:center;"
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
  </v-main>
  
</template>

<script>
import axios from "axios";
import SERVER from "@/apis/UrlMapper.ts"
import SideNavBar from "@/components/navigation/SideNavBar.vue";

export default {
  name:"EachWaterfall",
  components: {
    SideNavBar,
  },
  data: function () {
    return {
      exhibitionContent: ['뉴욕', 'hi'], // 샘플 데이터, 태그가 정상적으로 동작하면 이 데이터는 지울 예정
      popularExhibition: false, // 버튼 바꾸기 데이터
      images: [], // 이미지 데이터 리스트
      tags: [], // 태그 데이터 리스트
      indexs: [], // id 데이터 리스트
    }
  },
  // 아예 처음 이 페이지가 생성될 때부터 데이터를 가져옴.
  // 마찬가지로 Blob 디코딩과 더보기 버튼으로 몇개만 가져오게 끔, 수정해야됨.
  created:function(){
    localStorage.setItem('page', "AllWaterfall")
    const locations = ['northAmerica', 'southAmerica', 'europe', 'asia', 'oceania', 'africa']
    for (let index = 0; index < locations.length; index++) {
      axios.get(`${SERVER.BOARD_BASE_URL}allview?location=${locations[index]}`).then(response => {
          for (let index = 0; index < response.data.length; index++) {
            this.images.push(response.data[index].filePath);
            this.tags.push(response.data[index].tags)
            this.indexs.push(response.data[index].board.id)
          }
        }).catch(function(){
          console.log(`이미지 및 태그 불러오기 실패`);
        });
    }
    
  },
  methods: {
    // 월드맵 페이지로 이동
    clickChangeContinentViewButton: function () {
      this.popularExhibition = !this.popularExhibition
      this.$router.push({name:"WorldMap"})
    },
    // 게시물 작성 페이지로 이동
    clickGotoCreate: function () {
      this.$router.push({name:"Create"})
    },
    // 게시물 사진 보기
    gotoSelectArticle: function (idx) {
      localStorage.setItem("articleId", this.indexs[idx])
      this.$router.push({name:"PhotoView"})
    }
  }
}
</script>


<style scoped>

/* 후버 효과 */
.opacity-event-for-waterfall:hover {
  transition: 0.5s;
  transform: scale(1.03);
  opacity: 0.4;
}

/* 이미지 반응형으로 모든 기기에서 사용가능하게 만듬 */
.adjust-grid-container {
  padding: 80px 0px 0px 140px;
}
.adjust-grid-image {
  width:250px;
}

@media (min-width: 600px) {
  .adjust-grid-image {
    width:230px;
  }
}

@media (min-width: 800px) {
  .adjust-grid-image {
    width:270px;
  }
}

@media (min-width: 1000px) {
  .adjust-grid-container {
    padding: 80px 0px 0px 100px;
  }
}

/* 1264px 부터 css코드 */
@media (min-width: 1264px) {
  .adjust-grid-image {
    width:350px;
  }
}

/* 1600px 부터 css코드 */
@media (min-width: 1500px) {
  .adjust-grid-container {
    padding: 80px 0px 0px 0px;
  }
  .adjust-grid-image {
    width:400px;
  }
}

/* 1904px 부터 css코드 */
@media (min-width: 1904px) {
  .adjust-grid-container {
    padding: 80px 0px 0px 130px;
  }
  .adjust-grid-image {
    width:450px;
  }
}

</style>