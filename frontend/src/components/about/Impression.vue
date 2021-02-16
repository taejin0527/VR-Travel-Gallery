<template>
  <div class="impression section bg-grey" id="impression">
    <v-container>
      <v-carousel
        cycle
        :show-arrows="false"
        delimiter-icon="mdi-minus"
        height="400"
        hide-delimiter-background
        show-arrows-on-hover
      >
        <v-carousel-item v-for="(slide, i) in slides" :key="i">
          <v-sheet color="transparent" height="100%">
            <v-row class="fill-height" align="center" justify="center">
              <v-row>
                <v-icon>{{ slide.icon }}</v-icon>
              </v-row>
              <v-row>
                <div class="display-1">{{ slide.text }}</div></v-row
              >
              <v-row
                ><div class="display-1">{{ slide.name }}</div></v-row
              >
              <v-row
                ><div>{{ slide.team }}</div></v-row
              >
            </v-row>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      slides: [
        {
          icon: "mdi-comment-quote",
          text: "팀원들이 말을 안 들어서 힘들었습니다ㅠㅠ",
          name: "Donggeol Kim",
          team: "Backend",
        },
        {
          icon: "mdi-comment-quote",
          text: "모델링... 랜더링... 죽는줄 알았습니다...",
          name: "Minho Jang",
          team: "Specialist",
        },
      ],
    };
  },
};
</script>

<style scoped>
/* section */
.section {
  padding: 40px 0 100px;
}

/* impression */
.impression {
  text-align: center;
  padding-left: 20%;
  padding-right: 20%;
  padding-bottom: 145px;
}
</style>
