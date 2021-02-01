<template>
  <v-container>
    <br>
    <br>
    <v-row>
      <!-- 반응형 점찍기 // 밑에서 좌표만 설정해주면 찍어줌(백에서 받아오면 됨.) -->
      <v-col
        cols="2"
      >
        <div
          class="continent-scale"
          style="position:relative; left:100%; "
        >
          <v-icon
            color="#ffffff"
            size="15px"
            v-for="(item, idx) in popularDistrict"
            :key="idx"
            :class="{'adjust-location':true, 'transition-circle-icon':overCircleIcon[idx]}"
            :style="'top:' + popularLocationY[idx] +'%;' + 'left:' + adjustLocationX[idx] + '%; cursor: pointer;'"
            @mouseover="selectLocation(idx)"
            @mouseleave="leaveCircleIcon(idx)"
          >
            mdi-checkbox-blank-circle
          </v-icon>
        </div>
      </v-col>

      <!-- 지도 보여주기 -->
      <v-col
        cols="6"
      >
        <div>
          <img
            src="@/assets/continents/north_america.svg"
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
          :exhibitionTitle = "exhibitionTitle"
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
  name: "NorthAmerica",
  components: {
    ContinentCard
  },
  data: function () {
    return {
      sample: 100,
      popularDistrict: [0, -2, -4, -6, -8],
      // 여기에 X, Y축의 크기만 안다면 지도에 표시 가능.
      // 데이터를 받아올 예정
      popularLocationX: [90, 50, 50, 65, 10],
      popularLocationY: [20, 50, 80, 70, 55],
      // 여기로 데이터 가져오기 - 배열형식으로 가져와야 함. 아니면 딕셔너리형태로
      exhibitionImage: "https://cdn.vuetifyjs.com/images/cards/cooking.png",
      exhibitionTitle: "장소를 선택해주세요",
      exhibitionContent: ["태그 1", "태그 2"],
      exhibitionLocation: "",
      likeCount: 168,
      // 고른곳 확인
      locationIdx: 0,
      overCircleIcon: [false, false, false, false, false],
    }
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
      this.exhibitionImage= "https://images.unsplash.com/photo-1548182880-8b7b2af2caa2?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80"
      this.exhibitionTitle= "뉴욕의 하루"
      this.exhibitionLocation= "NewYork"
      this.exhibitionContent= ["스타벅스", "월가"]
      this.likeCount= 123
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
  transform: scale(1.8);
}

/* 지도의 크기와 위치 반응형으로 만듬 */
.adjust-location {
  position:relative;
  width: 11px;
}
.continent-scale {
  height: 500px;
  width: 500px;
}

@media (min-width: 1264px) {
  .adjust-location {
    position: relative;
    width: 13px;
  }
  .continent-scale {
    height: 600px;
    width: 600px;
  }
}

@media (min-width: 1904px) {
  .adjust-location {
    position:relative;
    width: 15px;
  }
  .continent-scale {
    height: 800px;
    width: 800px;
  }
}

</style>