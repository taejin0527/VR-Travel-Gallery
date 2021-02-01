<template>
    <div class="main-container">
        <div class = "deal-container">
            <div class="title">사진 등록</div>
            <div class = "picture-notice">
                <ul class = "write-wrapper">
                    <li>
                        사진은 ~~~ 권장
                    </li>
                    <li>사진 용량은 사진 한 장당 ~MB까지 등록 가능합니다.</li>
                </ul>
            </div>
            <div class = "file-upload-wrapper">
                <div v-if="!main.length" class="file-upload-example-container">
                    <div class="file-image-example">
                        <div class="file-image-example-alert">
                            등록된 메인 이미지가 없습니다.
                        </div>
                    </div>
                    <div class="file-upload-button">
                        <div class="image-box">
                            <label for ="mains">사진 등록</label>
                            <input type="file" id = "mains" ref="mains" @change="mainUpload"/>
                        </div>

                    </div>
                </div>
                <div v-else class="file-preview-content-container">
                    <div class="file-preview-container">
                        <div v-for="(m,index) in main" :key="index" class="file-preview-wrapper">
                            <div class = "file-close-button" @click="mainDeleteButton" :name="m.number">
                                x
                            </div>
                            <img :src="m.preview"/>

                        </div>
                        
                    </div>
                </div>
            </div>
            <div class = "file-upload-wrapper">
                <div v-if="!files.length" class="file-upload-example-container">
                    <div class="file-image-example">
                        <div class="file-image-example-alert">
                            등록된 이미지가 없습니다.
                        </div>
                    </div>
                    <div class="file-upload-button">
                        <div class="image-box">
                            <label for ="file">사진 등록</label>
                            <input type="file" id = "file" ref="files" @change="imageUpload" multiple/>
                        </div>

                    </div>
                </div>
                <div v-else class="file-preview-content-container">
                    <div class="file-preview-container">
                        <div v-for="(file,index) in files" :key="index" class="file-preview-wrapper">
                            <div class = "file-close-button" @click="fileDeleteButton" :name="file.number">
                                x
                            </div>
                            <img :src="file.preview"/>

                        </div>
                        <div class="file-preview-wrapper-upload">
                            <div class="image-box">
                                <label for="file">추가 사진 등록</label>
                                <input type="file" id = "file" ref="files" @change="imageAddUpload" multiple/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <button v-on:click="submitFile">저장하기</button>
        </div>
        

       



        
    </div>
</template>

<script>
import axios from "axios";
export default {

  data(){
      return{
          main: [],
          files: [], //업로드용 파일
          filesPreview : [], //보여줄 파일
          uploadImageIndex:0 //이미지 업로드를 위한 변수
      }
  },
  methods:{
      mainUpload(){
          console.log("aaa");
          console.log(this.$refs.mains.files[0]);
          this.main = [
              ...this.main,{
                  file: this.$refs.mains.files[0],
                  preview: URL.createObjectURL(this.$refs.mains.files[0]),
                  number : 0
              }
          ];
       
      },
      imageUpload(){
          console.log(this.$refs.files.files);
          //하나의 배열로 넣기
          let num = -1;
          for(let i = 0;i<this.$refs.files.files.length;i++){
              this.files = [
                  ...this.files,
                  //이미지 업로드
                  {
                      //실제파일
                      file: this.$refs.files.files[i],
                      //이미지 프리뷰
                      preview: URL.createObjectURL(this.$refs.files.files[i]),
                      //삭제 및 관리를 위한 number
                      number : i
                  }
              ];
              num = i;
          }
          this.uploadImageIndex = num +1; //이미지 index의 마지막 값 +1
          console.log(this.files);
      },
      imageAddUpload(){
          console.log(this.$refs.files.files);
          //하나의 배열로 넣기
          let num =-1;
          for(let i =0; i< this.$refs.files.files.length;i++){
              console.log(this.uploadImageIndex);
              this.files = [
                  ...this.files,
                //이미지 업로드
                {
                    //실제 파일
                    file : this.$refs.files.files[i],
                    preview: URL.createObjectURL(this.$refs.files.files[i]),
                    // 삭제 및 관리를 위한 number
                    number : i + this.uploadImageIndex
                }
              ];
              num = i;
          }
          this.uploadImageIndex = this.uploadImageIndex + num + 1;
          console.log(this.files);
      },
      mainDeleteButton(e){
          const name = e.target.getAttribute("name");
          console.log(name);
          this.main = this.main.filter(data => data.number !==Number(name));
      },
      fileDeleteButton(e){
          const name = e.target.getAttribute("name");
          this.files = this.files.filter(data => data.number !==Number(name));
      },
      submitFile(){
          console.log(this.files.length);
          const formData = new FormData();
          formData.append('main',this.main[0].file);
          formData.append('writer',"ssafy");
          formData.append('location',"서울");
          for(let i =0;i< this.files.length;i++){
              
              console.log(this.files[i].file);
              
              formData.append('file',this.files[i].file);
              
              

          }
          axios.post('http://localhost:8080/board/requestupload',
              formData,{
                  headers:{
                      'Authorization': 'Bearer ' + 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlaGRyamYiLCJpYXQiOjE2MTIwODEwNTMsImV4cCI6MTYxMjE2NzQ1M30.YipqqNrw_PpswmLAKXP7IAj9a20FPXOaWIcqhAB2JPZkiCq8X2Uth1gc_3l-CplTK3TEzOV3IHNZdfiW0mrn7w',
                      'Content-Type' : 'multipart/form-data'
                  }
              }).then(response=>{
                  this.$router.push("/view");
              }).catch(function(){
                  console.log("FAILURE");
              });
      },
  }
}
</script>

<style>
.main-container {
            width: 1200px;
            height: 400px;
            margin: 300px auto;
        }
        
        .deal-container {
            margin-top: 50px;
            color: #222222;
            border: 1px solid #dddddd;
        }
        
        .title {
            text-align: center;
            font-size: 18px;
            line-height: 60px;
            border-bottom: 1px solid #dddddd;
        }
        
       
        
     
        
    
    
        
    
        
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
            border: 1px solid #dddddd;
            background-color: #f4f4f4;
            min-height: 350px;
            font-size: 15px;
            color: #888888;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100%;
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
            color: #ef4351;
        }
        
        
        
        
        .image-box {
            margin-top: 30px;
            padding-bottom: 20px;
            text-align: center;
        }
        
        .image-box input[type='file'] {
            position: absolute;
            width: 0;
            height: 0;
            padding: 0;
            overflow: hidden;
            border: 0;
        }
        
        .image-box label {
            display: inline-block;
            padding: 10px 20px;
            background-color: #232d4a;
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
        
        .file-preview-wrapper>img {
            position: relative;
            width: 190px;
            height: 130px;
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
            width: 190px;
            height: 130px;
        }
        
       
        
   
</style>