<template>
  <div class="container">
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
        src="@/assets/3DHelp3.png" alt="" width="120px"
        :class="{'select-tips-transition': isSelectTips}"
        @mouseover="isSelectTips = true"
        @mouseleave="isSelectTips = false"
      >
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
      <v-icon size="30px" :class="{'like-hover-event':true, 'select-like-transition':isSelectLike}"
        @click="likeThisArticle"
      >
          mdi-heart
      </v-icon>
    </div>
        
    <!-- PhotoView 페이지로 가는 버튼 -->
    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:60px; top:120px; color:white;"
      @click="clickGotoPhotoView"
    >
      <v-icon>
        mdi-view-carousel-outline
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


    <div
      class="container-center"
      :class="{ 'has-mouse': hasMouse }"
      @touchstart="hasMouse = false"
    >
      <Flipbook
        class="flipbook"
        :pages="pages"
        :pagesHiRes="pagesHiRes"
        :startPage="pageNum"
        :singlePage="singlePage"
        :zooms = "zooms"
        ref="flipbook"
        @flip-left-start="onFlipLeftStart"
        @flip-left-end="onFlipLeftEnd"
        @flip-right-start="onFlipRightStart"
        @flip-right-end="onFlipRightEnd"
      >
      </Flipbook>
      
      <!-- 작성자 버튼 및 작성자 일때 삭제 버튼 추가 -->
      <div style="position:fixed; left:40px; top:45%;">
        <div class="profile d-flex justify-center" > snapped by</div>
        <span
          class="user-hover-event-goto-profile d-flex justify-center"
          style="color:#DDA288; text-align:center; font-size:35px; font-family:'SDSamliphopangche_Outline';"
          @click="gotoProfilePage"
        >
        {{author}}
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
  </div>
</template>

<script>
import Flipbook from "flipbook-vue";
import axios from "axios";
import SERVER from "@/apis/UrlMapper.ts"

export default {
  components: {
    Flipbook,
  },
  data: function() {
    return {
      fab: false,
      pages: [],
      pagesHiRes: [],
      hasMouse: true,
      pageNum: null,
      singlePage: true,
      isSelectTips: false,
      author:"",
      vfImages: [null, require('@/assets/photo/flipbookHelp.jpg')],
      zooms:[1],
      isSelectLike:false, // 좋아요는 손봐야 합니다.
    };
  },
  mounted() {
    axios
      .get(`${SERVER.BOARD_BASE_URL}getposts?id=${localStorage.getItem('articleId')}&username=${this.$store.state.Auth.authToken.username}`)
      .then((response) => {
        if (response.data[response.data.length-1].like === "false") {
          this.isSelectLike = false
        }
        else {
          this.isSelectLike = true
        }
        this.author = response.data[0].author
        response.data.forEach((e) => {
          this.vfImages.push(e.filepath);
        });
      })
      .catch((err) => {
        console.error(err);
      });
    window.addEventListener("keydown", (ev) => {
      const { flipbook } = this.$refs;
      if (!flipbook) {
        return;
      }
      if (ev.keyCode === 37 && flipbook.canFlipLeft) {
        flipbook.flipLeft();
      }
      if (ev.keyCode === 39 && flipbook.canFlipRight) {
        return flipbook.flipRight();
      }
    }),
      setTimeout(() => {
        (this.pages = this.vfImages),
        (this.pagesHiRes = this.vfImages);
      }, 1),
      window.addEventListener("hashchange", this.setPageFromHash);
    this.setPageFromHash();
  },
  methods: {
    clickGotoPhotoView () {
      this.$router.push({name:"PhotoView"})
    },
    onFlipLeftStart(page) {
      return console.log("flip-left-start", page);
    },
    onFlipLeftEnd(page) {
      console.log("flip-left-end", page);
      return (window.location.hash = "#" + page);
    },
    onFlipRightStart(page) {
      return console.log("flip-right-start", page);
    },
    onFlipRightEnd(page) {
      console.log("flip-right-end", page);
      return (window.location.hash = "#" + page);
    },
    setPageFromHash() {
      const n = parseInt(window.location.hash.slice(1), 10);
      if (isFinite(n)) {
        return (this.pageNum = n);
      }
    },
    clickGoBack: function () {
      this.$router.push({name:localStorage.getItem("page")})
    },
    gotoProfilePage: function () {
      localStorage.setItem('setUserforProfile', this.author)
      this.$router.push({name:"Profile"})
    },
    // 좋아요는 손볼게 많음. 서로 연동해야 되는 부분이 있어서
    likeThisArticle: function () {
      this.isSelectLike = !this.isSelectLike

    },
    // 여기에 라우터 페이지 이동 하심 댐당
    clickGotoVR: function () {
      // this.$router.push({name:""})
      console.log("그냥두면 에러나서 이렇게 나중에 지우십셔")
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
  },
};
</script>

<style scoped>
@font-face {
    font-family: 'SDSamliphopangche_Outline';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/SDSamliphopangche_Outline.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
     font-family: 'S-CoreDream-3Light';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}

/* Flex */
.container {
  display: flex;
  height: 100vh;
  width: 100vw;
  max-width: none;
  position: absolute;
  justify-content: center;
  overflow: hidden;
  z-index: 1;
}

.container-center {
  display: flex;
  margin: 0;
  padding: 0;
  width: 80%;
  justify-content: center;
  align-items: center;
}

.flipbook {
  width: 90%;
  height: 80%;
}

.flipbook .viewport {
  width: 100%;
  height: 100%;
}
.flipbook .bounding-box {
  box-shadow: 0 0 20px #000;
}
.credit {
  font-size: 12px;
  line-height: 20px;
  margin: 10px;
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

/* 좋아요 css */
@keyframes likebeat {
  from {
    transform: scale(1);
    color: #FDA288;
  }

  to {
    transform: scale(1.3);
    color: #FF8288;
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
  color: #FF8288;
  transition: 0.5s;
  transform: scale(1.3);
  cursor: pointer;
}
/* 여기 까지 좋아요 css*/

.user-hover-event-goto-profile:hover {
  color: aliceblue !important;
  transition: 0.5s;
  transform: scale(1.1);
  cursor: pointer;
}

.profile {
  font-family: 'S-CoreDream-3Light', Arial, Helvetica, sans-serif;
  font-size: 18px;
  color: #111111;
  font-weight: bold;
  cursor: default;
}

</style>
