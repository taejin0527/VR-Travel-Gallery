<template>
  <div class="container d-flex justify-center">
    <!-- 오른쪽 상단 Tips 픽스 -->
    <div
      style="
              position: fixed;
              height: 10%;
              margin: 0;
              padding: 0;
              width: 100px;
              top: 15px;
              right: 50px;
              z-index: 101;
            "
    >
      <img
        src="@/assets/3DHelp3.png"
        alt=""
        width="120px"
        :class="{ 'select-tips-transition': isSelectTips }"
        @mouseover="isSelectTips = true"
        @mouseleave="isSelectTips = false"
      />
    </div>

    <!-- 좋아요 버튼 -->
    <div
      style="
              position: fixed;
              height: 10%;
              margin: 0;
              padding: 0;
              top: 365px;
              right: 73px;
              z-index: 101;
            "
    >
      <v-icon
        size="30px"
        :class="{
          'like-hover-event': true,
          'select-like-transition': isSelectLike
        }"
        @click="likeThisArticle"
      >
        mdi-heart
      </v-icon>
    </div>

    <!-- Flipbook 페이지로 가는 버튼 -->
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:60px; top:120px; color:white;"
      @click="clickGotoFlipbook"
    >
      <v-icon>
        mdi-book-open-page-variant
      </v-icon>
    </v-btn>

    <!-- VR 페이지로 가는 버튼 -->
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:60px; top:200px; color:white;"
      @click="clickGotoVR"
    >
      <span style="font-size:22px">VR</span>
    </v-btn>

    <!-- 뒤로가기 버튼 -->
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:60px; top:280px; color:white;"
      @click="clickGoBack"
    >
      <v-icon size="38px">
        mdi-arrow-left-bold-circle
      </v-icon>
    </v-btn>

    <div class="flux">
      <vue-flux
        :options="vfOptions"
        :images="vfImages"
        :transitions="vfTransitions"
        class="adjust-grid-image"
        ref="slider"
      >
        <template v-slot:preloader>
          <flux-preloader />
        </template>

        <template v-slot:controls>
          <flux-controls />
        </template>

        <template v-slot:pagination>
          <flux-pagination />
        </template>

        <template v-slot:index>
          <flux-index />
        </template>
      </vue-flux>

      <!-- 이 코드는 사진 밑에 넣는 코드 근데.. 음 왼쪽이 나은 거 같긴함. -->
      <!-- <div class="d-flex justify-end align-center"> <pre class="profile">snapped by  </pre>
      <span class="user-hover-event-goto-profile" style="color:#DDA288; font-size:33px; font-family:'SDSamliphopangche_Outline';">{{author}}</span><pre>  </pre></div> -->
    </div>
    <!-- 작성자 버튼 및 작성자 일때 삭제 버튼 추가 -->
    <div style="position:fixed; left:40px; top:45%;">
      <div class="profile d-flex justify-center">snapped by</div>
      <span
        class="user-hover-event-goto-profile d-flex justify-center"
        style="color:#DDA288; text-align:center; font-size:35px; font-family:'SDSamliphopangche_Outline';"
        @click="gotoProfilePage"
      >
        {{ author }}
      </span>
      <br>
      <div
        class="profile d-flex justify-center"
        v-if="this.$store.state.Auth.authToken.username == this.author"
        @click="deleteArticle"
      >
        <v-btn
          color="#DDA288"
          style="color:white; font-weight:bold;"
        >
          <v-progress-linear
            v-if="fab"
            indeterminate
            color="red"
            style="width: 70px"
          ></v-progress-linear>
          <div
            v-else
          >
            게시글 삭제
          </div>
        </v-btn>
      </div>
    </div>
  </div>
</template>

<script>
import {
  VueFlux,
  FluxControls,
  FluxIndex,
  FluxPagination,
  FluxPreloader
} from "vue-flux";
import axios from "axios";
import SERVER from "@/apis/UrlMapper.ts";

export default {
  components: {
    VueFlux,
    FluxControls,
    FluxIndex,
    FluxPagination,
    FluxPreloader
  },
  data: () => ({
    fab: false,
    vfOptions: {
      autoplay: false
    },
    vfImages: [],
    author: "",
    vfTransitions: ["fade", "cube", "book", "wave", "camera"],
    isSelectTips: false,
    isSelectLike: false, // 좋아요는 손봐야 합니다.

  }),
  computed: {
    user() {
      return this.$store.state.Auth.authToken;
    }
  },
  mounted() {
    axios
      .get(`${SERVER.BOARD_BASE_URL}getposts?id=${localStorage.getItem('articleId')}&username=${this.$store.state.Auth.authToken.username}`)
      .then((response) => {
        if (response.data.like === "false") {
          this.isSelectLike = false
        }
        else {
          this.isSelectLike = true
        }
        this.author = response.data.board.author
        this.vfImages.push(response.data.filePath)
        for (let i = 0; i < response.data.subPath.length; i++) {
          this.vfImages.push(response.data.subPath[i])
        }
      })
      .catch((err) => {
        console.error(err);
      });
  },
  methods: {
    clickGotoFlipbook: function() {
      this.$router.push({ name: "Flipbook" });
    },
    clickGoBack: function() {
      this.$router.push({ name: localStorage.getItem("page") });
    },
    gotoProfilePage: function() {
      localStorage.setItem("setUserforProfile", this.author);
      this.$router.push({ name: "Profile" });
    },
    // 좋아요는 손볼게 많음. 서로 연동해야 되는 부분이 있어서
    likeThisArticle: function () {
      axios
        .get(`${SERVER.BOARD_BASE_URL}fixlike?curr=${this.isSelectLike}&id=${localStorage.getItem('articleId')}&username=${this.$store.state.Auth.authToken.username}`)
        .then((response) => {
          if (response.data === "false" || response.data === false) {
            this.isSelectLike = false
          }
          else {
            this.isSelectLike = true
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
    // 여기에 라우터 페이지 이동 하심 댐당
    clickGotoVR: function () {
      this.$router.push({name:""})
    },
    // 게시글 삭제
    deleteArticle: function () {
      if (this.fab == true) {
        this.fab = false
        return
      }
      // 이거 id로 바꾼 후, 다시 프로필의 id를 받아와서 보안성 높이기.
      if (this.$store.state.Auth.authToken.username != this.author) {
        console.log(this.$store.state.Auth)
        alert('인증되지 않은 사용자 입니다.')
      }
      else {
        this.fab = true
        axios.delete (
          `${SERVER.BOARD_BASE_URL}delpost?id=${localStorage.getItem('articleId')}`,
          {
            headers: {
              Authorization: "Bearer " + this.$store.state.Auth.authToken.token
            }
          },
        )
        .then(() => {
          this.$router.push({name:localStorage.getItem('page')})
        })
        .catch(err => {
          console.error(err)
        })
      }
    }
  }
};
</script>

<style scoped>
@font-face {
  font-family: "SDSamliphopangche_Outline";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/SDSamliphopangche_Outline.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "S-CoreDream-3Light";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

/* 좋아요 css */
@keyframes likebeat {
  from {
    transform: scale(1);
    color: #fda288;
  }

  to {
    transform: scale(1.3);
    color: #ff8288;
  }
}

.select-like-transition {
  animation-duration: 0.8s;
  animation-name: likebeat;
  animation-iteration-count: infinite;
  animation-direction: alternate;
  cursor: pointer;
}

.like-hover-event:hover {
  color: #ff8288;
  transition: 0.5s;
  transform: scale(1.3);
  cursor: pointer;
}
/* 여기 까지 좋아요 css */

.container {
  position: relative;
  height: 100vh;
  z-index: 1;
}
.container .flux {
  position: relative;
  top: 80px;
}

.user-hover-event-goto-profile:hover {
  color: aliceblue !important;
  transition: 0.5s;
  transform: scale(1.1);
  cursor: pointer;
}

.profile {
  font-family: "S-CoreDream-3Light", Arial, Helvetica, sans-serif;
  font-size: 18px;
  color: #111111;
  font-weight: bold;
  cursor: default;
}

/* 반응형으로 만듬 */
.adjust-grid-image {
  width: 400px;
  height: 400px;
}

@media (min-width: 800px) {
  .adjust-grid-image {
    width: 600px;
    height: 500px;
  }
}

@media (min-width: 1000px) {
  .adjust-grid-image {
    width: 800px;
    height: 700px;
  }
}

/* 1264px 부터 css코드 */
@media (min-width: 1264px) {
  .adjust-grid-image {
    width: 1000px;
    height: 700px;
  }
}

/* 1600px 부터 css코드 */
@media (min-width: 1500px) {
  .adjust-grid-image {
    width: 1200px;
    height: 700px;
  }
}

/* 1904px 부터 css코드 */
@media (min-width: 1904px) {
  .adjust-grid-image {
    width: 1400px;
    height: 750px;
  }
}

/* TIPS 애니메이션 */
@keyframes tipsbeat {
  from {
    transform: scale(0.95);
  }

  to {
    transform: scale(1.1);
  }
}

.select-tips-transition {
  animation-duration: 0.8s;
  animation-name: tipsbeat;
  animation-iteration-count: infinite;
  animation-direction: alternate;
  cursor: pointer;
}
</style>
