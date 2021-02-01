<template>
  <div>
       <div v-for="(file,index) in files" :key="index" class="file-preview-wrapper">
            
            <img :src="file.filePath"/>

        </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
    data(){
        return{
            files: [],
            listNum : 0,
            index : 0
        }
    },
    created:function(){
         
         axios.get('http://localhost:8080/board/getposts').then(response => {
                this.files = response.data;
                this.index = response.data.length;
                console.log(this.files);
                console.log(this.index);
        }).catch(function(){
             console.log("안됨");
         });

    }
}

</script>

<style>

</style>