<template>
  <v-card :loading="loading" class="mx-auto my-16" max-width="374">
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-img height="250" :src="exhibitionImage"></v-img>

    <v-card-title>{{ exhibitionTitle }}</v-card-title>

    <v-card-text>
      <v-row align="center" class="mx-0">
        <v-icon size="20px" color="#DDA288" class="animate-bounce">
          mdi-heart
        </v-icon>

        <div class="grey--text ml-4">
          {{ likeCount }}
        </div>
      </v-row>

      <div class="my-4 subtitle-1">
        {{ exhibitionLocation }}
      </div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-text>
      <v-chip-group class="accent-4 white--text" column>
        <v-chip
          v-for="(item, idx) in exhibitionContent"
          :key="idx"
          style="background-color:#DD6288; color:white;"
        >
          {{ item }}
        </v-chip>
      </v-chip-group>
    </v-card-text>
    <v-card-actions class="d-flex justify-center">
      <v-btn
        color="#DD6288"
        text
        large
        @click="goPhotoViewer"
        class="mb-1"
        style="font-size: 16px"
      >
        GO
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "ContinentCard",
  data: () => ({
    loading: false,
    selection: 1,
  }),
  props: {
    exhibitionImage: [String],
    exhibitionTitle: [String],
    exhibitionContent: [Array, String],
    exhibitionLocation: [String],
    likeCount: [Number, String],
  },
  methods: {
    goPhotoViewer() {
      this.$router.push({ name: "PhotoView" });
    },
  },
  mounted: function() {
    console.log(this.exhibitionImage);
  },
};
</script>

<style>
@keyframes lighttextparty {
  from {
    transform: scale(0.9);
  }

  to {
    transform: scale(1.1);
  }
}

.animate-bounce {
  animation-duration: 0.8s;
  animation-name: lighttextparty;
  animation-iteration-count: infinite;
  animation-direction: alternate;
}
</style>
