<template>
  <v-container>
    <br />
    <br />
    <v-row>
      <!-- 반응형 점찍기 // 밑에서 좌표만 설정해주면 찍어줌(백에서 받아오면 됨.) -->
      <v-col
        cols="2"
      >
        <div
          class="continent-scale"
          style="position:relative; left:100%; "
        >
          <img 
            src="@/assets/continents/popularFlag.png"
            v-for="(item, idx) in popularDistrict"
            :key="idx"
            :class="{'adjust-location':true, 'transition-circle-icon':overCircleIcon[idx]}"
            :style="'top:' + popularLocationY[idx] +'%;' + 'left:' + adjustLocationX[idx] + '%; cursor: pointer;'"
            @mouseover="selectLocation(idx)"
            @mouseleave="leaveCircleIcon(idx)"
          >
        </div>
      </v-col>

      <!-- 지도 보여주기 -->
      <v-col
        cols="6"
      >
        <div>
          <img
            src="@/assets/continents/europe.svg"
            alt="image error"
            class="continent-scale"
          >
        </div>
      </v-col>
      <v-col
        offset-md="1"
        md="3"
      >
        <ContinentCard
          :exhibitionImage = "exhibitionImage"
          :exhibitionContent = "exhibitionContent"
          :exhibitionLocation = "exhibitionLocation"
          :likeCount = "likeCount"
        />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import ContinentCard from "@/components/continents/ContinentCard"

export default {
  name: "Europe",
  components: {
    ContinentCard
  },
  data: function () {
    return {
      sample: 100,
      popularDistrict: [0, -2.5, -5, -7.5, -10],
      // 여기에 X, Y축의 크기만 안다면 지도에 표시 가능.
      // 데이터를 받아올 예정
      popularLocationX: [90, 40, 40, 50, -3],
      popularLocationY: [15, 45, 75, 65, 50],
      // 여기로 데이터 가져오기 - 배열형식으로 가져와야 함. 아니면 딕셔너리형태로
      exhibitionImage: "https://cdn.vuetifyjs.com/images/cards/cooking.png",
      exhibitionContent: ["태그 1", "태그 2"],
      exhibitionLocation: "장소",
      likeCount: 168,
      // 고른곳 확인
      locationIdx: 0,
      overCircleIcon: [false, false, false, false, false],
    }
  },
  props: {
    images: [Array],
    tags: [Array],
    likes: [Array],
    locations: [Array],
  },

  computed: {
    // Y축 보정하기
    // 재사용을 위한 코드
    adjustLocationX: function () {
      const array = [1, 2, 3, 4, 5];
      for (let index = 0; index < array.length; index++) {
        array[index] = this.popularLocationX[index] + this.popularDistrict[index]
      }
      return array
    }
  },
  methods: {
    // 데이터 통신 해야되지만 일단 샘플 넣기.
    selectLocation: function(idx) {
      this.exhibitionImage= this.images[idx]
      this.exhibitionLocation= this.locations[idx]
      this.exhibitionContent= this.tags[idx]
      this.likeCount= this.likes[idx]
      this.overCircleIcon[idx] = true
    },
    leaveCircleIcon: function (idx) {
      this.overCircleIcon[idx] = false
    }
  }

}
</script>

<style>

.transition-circle-icon {
  transform: scale(1.3);
  transition: 0.3s;
}

/* 지도의 크기와 위치 반응형으로 만듬 */
.adjust-location {
  position:relative;
  width: 25px;
}
.continent-scale {
  height: 500px;
  width: 500px;
}

@media (min-width: 1264px) {
  .adjust-location {
    position: relative;
    width: 30px;
  }
  .continent-scale {
    height: 600px;
    width: 600px;
  }
}

@media (min-width: 1904px) {
  .adjust-location {
    position:relative;
    width: 40px;
  }
  .continent-scale {
    height: 800px;
    width: 800px;
  }
}

</style>
