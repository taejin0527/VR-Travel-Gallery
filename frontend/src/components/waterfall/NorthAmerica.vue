<template>
  <!-- 각 대륙의 전체 사진 불러오기. -->
  <!-- 단, 각 대륙의 모든 사진을 불러오기 때문에 더 보기 버튼을 만들어서 15개씩 불러오는 방향을 잡아야 할 듯. -->
  <v-container
    class="adjust-grid-container"
  >
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
        <img :src="`${image}`" alt="image error" class="adjust-grid-image">
        <br>

        <!-- 태그 보여주는 코드 -->
        <v-chip-group
          class="accent-4 white--text"
          style="text-algin:center;"
          column
        >
          <v-chip
            v-for="(item, idx) in exhibitionContent"
            :key="idx"
            style="background-color:#DD6288; color:white;"
          >
            {{item}}
          </v-chip>
        </v-chip-group>        
      </v-col>
    </v-row>
  </v-container>
  
</template>

<script>
export default {
  name:"NorthAmerica",
  data: function () {
    return {
      exhibitionContent: ['뉴욕', 'hi'] // 샘플 태그, 나중에 지울 예정
    }
  },
  props: {
    images:[Array], // EachWaterfall.vue router에서 받아온 데이터들
    tags:[Array], // EachWaterfall.vue router에서 받아온 데이터들
  }
}
</script>

<style scoped>

/* 이미지 반응형으로 모든 기기에서 사용가능하게 만듬 */
.adjust-grid-container {
  padding: 80px 0px 0px 140px;
}
.adjust-grid-image {
  width:250px;
}

@media (min-width: 600px) {
  .adjust-grid-image {
    width:230px;
  }
}

@media (min-width: 800px) {
  .adjust-grid-image {
    width:270px;
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
    width:350px;
  }
}

/* 1600px 부터 css코드 */
@media (min-width: 1500px) {
  .adjust-grid-container {
    padding: 80px 0px 0px 0px;
  }
  .adjust-grid-image {
    width:400px;
  }
}

/* 1904px 부터 css코드 */
@media (min-width: 1904px) {
  .adjust-grid-container {
    padding: 80px 0px 0px 130px;
  }
  .adjust-grid-image {
    width:450px;
  }
}

</style>