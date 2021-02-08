<template>
  <div class="card-body bg-white mt-0 shadow">
                <p style="font-weight: bold">카카오페이 현재 사용가능</p>
                <label class="box-radio-input"><input type="radio" name="cp_item" value="5000"><span>5,000원</span></label>
                <label class="box-radio-input"><input type="radio" name="cp_item" value="10000"><span>10,000원</span></label>
                <label class="box-radio-input"><input type="radio" name="cp_item" value="15000"><span>15,000원</span></label>
                <label class="box-radio-input"><input type="radio" name="cp_item" value="20000"><span>20,000원</span></label>
                <label class="box-radio-input"><input type="radio" name="cp_item" value="25000"><span>25,000원</span></label>
                <label class="box-radio-input"><input type="radio" name="cp_item" value="30000"><span>30,000원</span></label>
                <label class="box-radio-input"><input type="radio" name="cp_item" value="35000"><span>35,000원</span></label>
                <label class="box-radio-input"><input type="radio" name="cp_item" value="40000"><span>40,000원</span></label>
                <label class="box-radio-input"><input type="radio" name="cp_item" value="50000"><span>50,000원</span></label>
                <p  style="color: #ac2925; margin-top: 30px">카카오페이의 최소 충전금액은 5,000원이며 <br/>최대 충전금액은 50,000원 입니다.</p>
                <button type="button" class="btn btn-lg btn-block  btn-custom" id="charge_kakao" @click="charge">충 전 하 기</button>
 </div>
</template>


<script>
import axios from "axios";
export default {
    
    methods:{
        charge(){
            
            const money = $('input[name="cp_item"]:checked').val();
            const formData = new FormData();
            formData.append('cost',money);
            // 수정부분
            axios.post('http://localhost:8080/kakao',
          formData,{
              headers:{
                  
                  'Authorization': 'Bearer ' + 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FmeSIsImlhdCI6MTYxMjYwMDM5NywiZXhwIjoxNjEyNjg2Nzk3fQ.VjY6C_SwlqfzreDrKA_CqHvMhWs65kqQ6xeMNOr7i-lFJgCHqHoKWlRWaYAxAHz53uBX-m3tjodkDyUqsOZjBg',
                  'Content-Type' : 'multipart/form-data'
              }
          }).then(response=>{
            //   this.$router.push("/view");
            console.log("succes");
            console.log(response);
            try{
                window.location.href = response.data;
                
            }catch{
                alert('결제 실패');
            }
            
            
          }).catch(function(){
              console.log("FAILURE");
          });
        }
    }
}
</script>

<style>

</style>