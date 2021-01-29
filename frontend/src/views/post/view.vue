<template>
  <div>
      ㅎㅇ
  </div>
</template>

<script>
import axios from "axios";
export default {
    data(){
        return{
            files: [],
            listNum : 0
        }
    },
    created:function(){
         
         axios.get('http://localhost:8080/board/getposts').then(response => {
                this.files = response.data;
                console.log(this.files[0].filename);
        }).catch(function(){
             console.log("안됨");
         });

    }
}
</script>

<style>

</style>