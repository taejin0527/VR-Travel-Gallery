<template>
  <v-container class="profileform">
    <!-- Basic profile -->
    <v-card>
      <v-container fluid>
        <v-row dense>
          <v-col cols="6" class="mt-3">
            <v-row no-gutters>
              <v-col cols="12">
                <MyProfile :user="authToken" />
              </v-col>
            </v-row>

            <v-spacer>
              <br />
            </v-spacer>

            <v-row no-gutters>
              <v-col cols="12">
                <MyCredit :user="authToken" />
              </v-col>
            </v-row>
          </v-col>
          <v-col cols="6">
            <Bookmark />
          </v-col>
        </v-row>
      </v-container>

      <MyPosts :user="authToken" />
    </v-card>
  </v-container>
</template>

<script>
import { mapState } from "vuex";

import MyProfile from "@/components/member/profile/MyProfile.vue";
import MyCredit from "@/components/member/profile/MyCredit.vue";
import Bookmark from "@/components/member/profile/Bookmark.vue";
import MyPosts from "@/components/member/profile/MyPosts.vue";

export default {
  components: {
    MyProfile,
    MyCredit,
    Bookmark,
    MyPosts,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState("Auth", ["authToken"]),
  },
};
</script>

<style scoped>
.container,
.row,
.col {
  background: #5a4e4d;
}
.profileform {
  width: 80%;
}
</style>
