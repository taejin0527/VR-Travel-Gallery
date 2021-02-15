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

    <!-- 검색 버튼 및 입력창 -->
    <v-lazy
      min-height="200"
      transition="slide-x-reverse-transition"
      v-if="isSelectSearch"
      style="
              position: fixed;
              height: 10%;
              margin: 0;
              padding: 0;
              width: 300px;
              top: 23px;
              right: 170px;
              z-index: 101;
              transition:0.5s;
            "
    >
      <v-card
        color="#DDA288"
        height="50px"
        width="300px"
        dark
      >
        <v-card-text>
          <v-text-field
            v-model="searchData"
            color="white"
            placeholder="장소나 태그를 입력하세요."
            append-outer-icon="mdi-airplane-takeoff"
            @keydown.enter="searchKeyword"
            @click:append-outer="searchKeyword"
            style="position:relative; bottom:24px;"
          ></v-text-field>
        </v-card-text>
      </v-card>
    </v-lazy>
    
    <v-btn
      v-else
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:170px; top:20px; color:white; transition:0.5s;"
      @click="isSelectSearch = true"
    >
      <v-icon>
        mdi-image-search
      </v-icon>
    </v-btn>

    <!-- 전체 사진 불러오기. -->
    <!-- 단, 모든 사진을 불러오기 때문에 더 보기 버튼을 만들어서 15개씩 불러오는 방향을 잡아야 할 듯. -->
    <v-container class="adjust-grid-container">
      <div style="width:100%; height: 20px;"></div>
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
              v-for="(item, i) in tags[idx]"
              :key="i"
              style="background-color:#DD6288; color:white;"
            >
              {{ item.tag }}
            </v-chip>
          </v-chip-group>
        </v-col>
      </v-row>
    </v-container>
    <br><br>
    <div
      class="d-flex justify-center"
    >
      <div
        v-if='(this.endPage[0]==="더 이상 게시물이 없습니다." && this.endPage[1]==="더 이상 게시물이 없습니다." &&
              this.endPage[2]==="더 이상 게시물이 없습니다." && this.endPage[3]==="더 이상 게시물이 없습니다." &&
              this.endPage[4]==="더 이상 게시물이 없습니다." && this.endPage[5]==="더 이상 게시물이 없습니다.")'
        class="d-flex justify-center change-font-more-articles align-center"
        style="width:100%; height:100px; color:#eeeeee;"
      >
        더 이상 게시물이 없습니다.
      </div> 
      <v-btn
        class="ma-2 change-font-more-articles"
        :loading="loading"
        :disabled="loading"
        color="#DDA288"
        style="color:white;"
        @click="moreArticles"
        v-else
      >
        More
      </v-btn>
      <br><br><br><br><br><br>
    </div>
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
      loader:null,
      loading: false,
      popularExhibition: false, // 버튼 바꾸기 데이터
      images: [], // 이미지 데이터 리스트
      tags: [], // 태그 데이터 리스트
      indexs: [], // id 데이터 리스트
      searchData:"",
      isSelectSearch:false,
      pagingIndex:0,
      endPage: ['', '', '', '', '', ''],
    }
  },
  // 로딩
  watch: {
    loader () {
      const l = this.loader
      this[l] = !this[l]

      setTimeout(() => (this[l] = false), 3000)

      this.loader = null
    },
  },
  // 아예 처음 이 페이지가 생성될 때부터 데이터를 가져옴.
  created:function(){
    localStorage.setItem('page', "AllWaterfall")
    const locations = ['northAmerica', 'southAmerica', 'europe', 'asia', 'oceania', 'africa']
    const realPage = this.pagingIndex/6
    for (let index = 0; index < locations.length; index++) {
      axios
      .get(`${SERVER.BOARD_BASE_URL}paging?location=${locations[index]}&num=${realPage}`)
      .then(response => {
        for (let i = 0; i < response.data.length; i++) {
          this.images.push(response.data[i].filePath);
          this.tags.push(response.data[i].tags)
          this.indexs.push(response.data[i].board.id)
        }
        this.pagingIndex = this.pagingIndex + 1
      })
      .catch(err => {
        console.log(err)
        this.endPage[index] = "게시물이 없습니다."
        this.pagingIndex = this.pagingIndex + 1
        }
      );

    }
  },
  methods: {
    // 월드맵 페이지로 이동
    clickChangeContinentViewButton: function() {
      this.popularExhibition = !this.popularExhibition;
      this.$router.push({ name: "WorldMap" });
    },
    // 게시물 작성 페이지로 이동
    clickGotoCreate: function () {
      this.$router.push({name:"Create"})
    },
    // 게시물 사진 보기
    gotoSelectArticle: function (idx) {
      localStorage.setItem("articleId", this.indexs[idx])
      this.$router.push({name:"PhotoView"})
    },
    // 6개씩 더 가져오기
    moreArticles: function () {
      this.loader = 'loading'
      const locations = ['northAmerica', 'southAmerica', 'europe', 'asia', 'oceania', 'africa']
      const realPage = this.pagingIndex/6
      console.log(this.endPage)
      for (let index = 0; index < locations.length; index++) {
        axios
          .get(`${SERVER.BOARD_BASE_URL}paging?location=${locations[index]}&num=${realPage}`)
          .then(response => {
            console.log(response)
            for (let i = 0; i < response.data.length; i++) {
              this.images.push(response.data[i].filePath);
              this.tags.push(response.data[i].tags)
              this.indexs.push(response.data[i].board.id)
            }
            this.pagingIndex = this.pagingIndex + 1
          })
          .catch(err => {
            console.log(err)
            this.endPage[index] = "더 이상 게시물이 없습니다."
            this.pagingIndex = this.pagingIndex + 1
            }
          );
      }
    },


    // 검색
    searchKeyword: function() {
      if (this.searchData === "") {
        alert('검색어를 입력해주세요.')
      }
      else {
        alert(`검색어 : ${this.searchData} -> 백엔드 이으면 댐당`)
      }
    }
  }
}
</script>

<style scoped>

/* 눈누에서 폰트 가져옴 */
@font-face {
  font-family: "TmoneyRoundWindRegular";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindRegular.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

.change-font-more-articles {
  font-family: "TmoneyRoundWindRegular";
  font-size: 20px;
}

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
  width: 250px;
}

@media (min-width: 600px) {
  .adjust-grid-image {
    width: 230px;
  }
}

@media (min-width: 800px) {
  .adjust-grid-image {
    width: 270px;
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
    width: 350px;
  }
}

/* 1600px 부터 css코드 */
@media (min-width: 1500px) {
  .adjust-grid-container {
    padding: 80px 0px 0px 0px;
  }
  .adjust-grid-image {
    width: 400px;
  }
}

/* 1904px 부터 css코드 */
@media (min-width: 1904px) {
  .adjust-grid-container {
    padding: 80px 0px 0px 130px;
  }
  .adjust-grid-image {
    width: 450px;
  }
}

.custom-loader {
  animation: loader 1s infinite;
  display: flex;
}
@-moz-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
@-webkit-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
@-o-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
@keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>