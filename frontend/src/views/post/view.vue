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
         
         axios.get('http://localhost:8080/board/getposts',
            {
              headers:{
                      'Authorization': 'Bearer ' + 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FmeSIsImlhdCI6MTYxMTgzNTY2MCwiZXhwIjoxNjExOTIyMDYwfQ.Fx55z-74k_YM1S-MhGEHeIXNSkCWvzRO25DYvOKSjFxhBl3iY7_3mSnj_a8Yokur8dSJpLSi92H-1oij4bP5MA'
              
                  }
            }).then(response => {
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