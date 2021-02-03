<template>
  <div class="wrapper">
    <div class="wrapper-center">
      <!-- Header -->
      <ProfileForm />

      <!-- Follower -->
    </div>
  </div>
</template>

<script>
import ProfileForm from "@/components/member/profile/ProfileForm";

export default {
  name: "scale",
  components: {
    ProfileForm
  },
  data() {
    return {};
  },
  methods: {}
};
</script>

<style scoped>
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  width: 100%;
}
.wrapper-center {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 80%;
  width: 100%;
  background: #5a4e4d;
}
</style>
