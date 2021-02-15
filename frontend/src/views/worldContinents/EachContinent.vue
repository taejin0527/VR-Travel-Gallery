<template>
  <v-main>
    <SideNavBar />
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
      <v-card color="#DDA288" height="50px" width="300px" dark>
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

    <!-- 각 대륙별로 이미지 가져오기 -->
    <div v-if="this.getContinentName == 'oceania'">
      <Oceania
        :images="images"
        :tags="tags"
        :likes="likes"
        :locations="locations"
        :indexs="indexs"
      />
    </div>
    <div v-else-if="this.getContinentName == 'asia'">
      <Asia
        :images="images"
        :tags="tags"
        :likes="likes"
        :locations="locations"
        :indexs="indexs"
      />
    </div>
    <div v-else-if="this.getContinentName == 'northAmerica'">
      <NorthAmerica
        :images="images"
        :tags="tags"
        :likes="likes"
        :locations="locations"
        :indexs="indexs"
      />
    </div>
    <div v-else-if="this.getContinentName == 'southAmerica'">
      <SouthAmerica
        :images="images"
        :tags="tags"
        :likes="likes"
        :locations="locations"
        :indexs="indexs"
      />
    </div>
    <div v-else-if="this.getContinentName == 'europe'">
      <Europe
        :images="images"
        :tags="tags"
        :likes="likes"
        :locations="locations"
        :indexs="indexs"
      />
    </div>
    <div v-else>
      <Africa
        :images="images"
        :tags="tags"
        :likes="likes"
        :locations="locations"
        :indexs="indexs"
      />
    </div>
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
import SERVER from "@/apis/UrlMapper.ts";
import SideNavBar from "@/components/navigation/SideNavBar.vue";

export default {
  name: "EachContinent",
  data: function() {
    return {
      getContinentName: localStorage.getItem("continent"),
      popularExhibition: true,
      images: [], // 이미지 데이터 리스트
      tags: [], // 태그 데이터 리스트
      likes: [], // 좋아요 수 데이터 리스트
      locations: [], // 장소 데이터 리스트
      indexs: [], // 게시물 id 리스트
      searchData: "",
      isSelectSearch: false
    };
  },
  // 아예 처음 이 페이지가 생성될 때부터 데이터를 가져옴.
  // 마찬가지로 Blob 디코딩과 더보기 버튼으로 몇개만 가져오게 끔, 수정해야됨.
  // 이거는 좋아요 수를 통해서 5개만 가져오게 만들건데, 이걸... 어떻게 해야될까?
  // 일주일마다 바꿔서 나오게끔 만드는게 제일인듯 한데. 일단 5개만 가져오게끔 했슴당.
  created: function() {
    localStorage.setItem("page", "EachContinent");
    const location = localStorage.getItem("continent");
    axios
      .get(`${SERVER.BOARD_BASE_URL}allview?location=${location}`)
      .then(response => {
        for (
          let index = 0;
          index < Math.min(5, response.data.length);
          index++
        ) {
          this.indexs.push(response.data[index].board.id);
          this.images.push(response.data[index].filePath);
          const tmp = [];
          for (let i = 0; i < response.data[index].tags.length; i++) {
            tmp.push(response.data[index].tags[i].tag);
          }
          this.tags.push(tmp);
          this.likes.push(response.data[index].board.good);
          this.locations.push(response.data[index].board.nation);
        }
      })
      .catch(function() {
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
    clickChangeContinentViewButton: function() {
      this.popularExhibition = !this.popularExhibition;
      this.$router.push({ name: "EachWaterfall" });
    },
    // 게시물 작성 페이지로 이동
    clickGotoCreate: function() {
      this.$router.push({ name: "Create" });
    },
    searchKeyword: function() {
      if (this.searchData === "") {
        alert("검색어를 입력해주세요.");
      } else {
        alert(`검색어 : ${this.searchData} -> 백엔드 이으면 댐당`);
      }
    }
  }
};
</script>

<style scoped></style>
