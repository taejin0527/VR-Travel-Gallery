<template>
  <v-container>
    <br /><br />
    <v-row>
      <v-col offset-xl="2" xl="4" offset="1" cols="5">
        <h1 style="color:#eeeeee">메인 사진</h1>
        <hr />
      </v-col>
    </v-row>
    <v-row>
      <v-col offset-xl="2" xl="4" offset="1" cols="5">
        <!-- 메인 사진 -->
        <div class="file-upload-wrapper">
          <div v-if="!main.length" class="file-upload-example-container">
            <div class="file-image-example">
              <div class="file-image-example-alert">
                등록된 메인 이미지가 없습니다.
              </div>
            </div>

            <!-- 메인 사진 등록 버튼 -->
            <v-btn class="mx-2 image-box" fab dark color="#dda288">
              <label for="mains">
                <v-icon dark>
                  mdi-plus
                </v-icon>
              </label>
              <input type="file" id="mains" ref="mains" @change="mainUpload" />
            </v-btn>
          </div>
          <div v-else class="file-preview-content-container">
            <div class="file-preview-container">
              <div
                v-for="(m, index) in main"
                :key="index"
                class="file-preview-wrapper"
              >
                <div
                  class="file-close-button"
                  @click="mainDeleteButton"
                  :name="m.number"
                >
                  x
                </div>
                <img :src="m.preview" />
              </div>
            </div>
          </div>
        </div>
      </v-col>

      <v-col offset="1" cols="3">
        <!-- selectContinent가 대륙 선택이야 처음 선택은 선택한 로컬 스토리지의 데이터를 따라서 만들어져. -->
        <v-select
          :items="continentsNames"
          label="Solo field"
          v-model="selectContinent"
          solo
        ></v-select>

        <!-- 장소(국가) 선택 : nation -->
        <v-text-field
          label="장소(국가)"
          prepend-icon="mdi-map-marker"
          v-model="nation"
        ></v-text-field>

        <!-- tags는 리스트로 되어 있슴 -->
        <v-row>
          <v-col cols="6" v-for="(item, idx) in tags" :key="idx">
            <v-text-field label="#Tag입력" v-model="tags[idx]"></v-text-field>
          </v-col>
          <v-col cols="6">
            <v-btn
              class="btn"
              color="#dda288"
              style="color:white;"
              @click="addOneTagMore"
            >
              태그 추가
            </v-btn>
          </v-col>
        </v-row>

        <!-- submit 버튼 별로긴한데 담에 바꿀게 -->
        <v-btn color="blue-grey" class="ma-2 white--text" @click="submitFile">
          Upload
          <v-icon right dark>
            mdi-cloud-upload
          </v-icon>
        </v-btn>
      </v-col>
      <v-col offset="2" cols="8">
        <h1 style="color:#eeeeee">추가 사진</h1>
        <hr />
      </v-col>
    </v-row>

    <v-row>
      <v-col offset-xl="2" xl="9" offset="1" cols="10">
        <!-- 서브 사진 -->
        <div class="file-upload-wrapper">
          <div v-if="!files.length" class="file-upload-example-container">
            <div class="file-image-example">
              <div class="file-image-example-alert">
                추가로 등록할 사진을 올려주세요.
              </div>
            </div>

            <!-- 서브 사진 등록 버튼 -->
            <v-btn class="mx-2 image-box" fab dark color="#dda288">
              <label for="file">
                <v-icon dark>
                  mdi-plus
                </v-icon>
              </label>
              <input
                type="file"
                id="file"
                ref="files"
                @change="imageUpload"
                multiple
              />
            </v-btn>
          </div>

          <div v-else class="file-preview-content-container">
            <div class="file-preview-container">
              <div
                v-for="(file, index) in files"
                :key="index"
                class="file-sub-preview-wrapper"
              >
                <div
                  class="file-close-button"
                  @click="fileDeleteButton"
                  :name="file.number"
                >
                  x
                </div>
                <img :src="file.preview" />
              </div>

              <!-- 추가 사진 등록 버튼 -->
              <div style="line-height:200px">
                <v-btn class="mx-2 image-box" fab dark color="#dda288">
                  <label for="file">
                    <v-icon dark>
                      mdi-plus
                    </v-icon>
                  </label>
                  <input
                    type="file"
                    id="file"
                    ref="files"
                    @change="imageAddUpload"
                    multiple
                  />
                </v-btn>
              </div>
            </div>
          </div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import SERVER from "@/apis/UrlMapper.ts";

export default {
  data() {
    return {
      continentsNames: [
        "northAmerica",
        "southAmerica",
        "europe",
        "asia",
        "oceania",
        "africa"
      ],
      selectContinent: localStorage.getItem("continent"), // 선택된 대륙
      main: [],
      files: [], //업로드용 파일
      filesPreview: [], //보여줄 파일
      uploadImageIndex: 0, //이미지 업로드를 위한 변수
      tags: [], // 태그들
      nation: "", // 장소(국가)
      num: 0,
      mainImageData: null
    };
  },
  created: function() {
    console.log(this.$store.state.Auth.authToken);
  },
  methods: {
    addOneTagMore(e) {
      this.tags.push("");
      console.log(this.tags);
    },
    mainUpload() {
      console.log(this.$refs.mains.files[0]);
      this.main = [
        ...this.main,
        {
          file: this.$refs.mains.files[0],
          preview: URL.createObjectURL(this.$refs.mains.files[0]),
          number: 0
        }
      ];
    },
    imageUpload() {
      console.log(this.$refs.files.files);
      //하나의 배열로 넣기
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제 및 관리를 위한 number
            number: i
          }
        ];
        num = i;
      }
      this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 +1
      console.log(this.files);
      console.log(this.uploadImageIndex);
    },
    imageAddUpload() {
      console.log(this.$refs.files.files);
      //하나의 배열로 넣기
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        console.log(this.uploadImageIndex);
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            // 삭제 및 관리를 위한 number
            number: i + this.uploadImageIndex
          }
        ];
        num = i;
      }
      this.uploadImageIndex = this.uploadImageIndex + num + 1;
      console.log(this.files);
      console.log(this.uploadImageIndex);
    },
    mainDeleteButton(e) {
      const name = e.target.getAttribute("name");
      console.log(name);
      this.main = this.main.filter(data => data.number !== Number(name));
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute("name");
      this.files = this.files.filter(data => data.number !== Number(name));
    },
    submitFile() {
      console.log(this.files.length);
      const formData = new FormData();
      formData.append("main", this.main[0].file);
      formData.append("writer", this.$store.state.Auth.authToken.username);
      formData.append("location", this.selectContinent);
      formData.append("nation", this.nation);
      formData.append("tags", this.tags);
      for (let i = 0; i < this.files.length; i++) {
        console.log(this.files[i].file);
        formData.append("file", this.files[i].file);
      }
      axios
        .post(`${SERVER.BOARD_BASE_URL}requestupload`, formData, {
          headers: {
            Authorization: "Bearer " + this.$store.state.Auth.authToken.token,
            "Content-Type": "multipart/form-data"
          }
        })
        .then(response => {
          localStorage.setItem("continent", this.selectContinent);
          this.$router.push({ name: "EachWaterfall" });
          console.log("succes");
        })
        .catch(function() {
          console.log("FAILURE");
        });
    }
  }
};
</script>

<style>
.file-image-example {
  height: 100%;
}

.picture-notice {
  margin: 20px;
  padding: 20px 40px;
  border: 1px solid #dddddd;
}

.file-preview-content-container {
  height: 100%;
}

.file-upload-wrapper {
  margin: 20px;
  background-color: #5a4e4d;
  min-height: 350px;
  font-size: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #888888;
  height: 40%;
}

.file-upload-example-container {
  display: flex;
  align-items: center;
  justify-content: center;
  /* height: 100%;
width: 100%; */
}

.file-image-example-alert {
  margin-top: 5px;
  text-align: center;
  color: #dda288;
}

.image-box {
  text-align: center;
}

.image-box input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}

.image-box label {
  display: inline-block;
  padding: 10px 10px;
  color: #fff;
  vertical-align: middle;
  font-size: 15px;
  cursor: pointer;
  border-radius: 5px;
}

.file-preview-wrapper {
  padding: 10px;
  position: relative;
}

.file-sub-preview-wrapper {
  padding: 10px;
  position: relative;
}

.file-preview-wrapper > img {
  position: relative;
  width: 400px;
  z-index: 10;
}

.file-sub-preview-wrapper > img {
  position: relative;
  width: 200px;
  z-index: 10;
}

.file-close-button {
  position: absolute;
  /* align-items: center; */
  line-height: 18px;
  z-index: 99;
  font-size: 18px;
  right: 5px;
  top: 10px;
  color: #fff;
  font-weight: bold;
  background-color: #666666;
  width: 20px;
  height: 20px;
  text-align: center;
  cursor: pointer;
}

.file-preview-container {
  height: 100%;
  display: flex;
  flex-wrap: wrap;
}

.file-preview-wrapper-upload {
  margin: 10px;
  padding-top: 20px;
  background-color: #888888;
  width: 200px;
  height: 500px;
}
</style>
