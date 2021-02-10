<template>
  <div class="body">
    <SideNavBar />

    <transition name="">
      <AboutMap v-if="section" />
      <AboutVR v-else />
    </transition>
  </div>
</template>

<script>
import SideNavBar from "@/components/navigation/SideNavBar.vue";
import AboutMap from "@/components/about/AboutMap.vue";
import AboutVR from "@/components/about/AboutVR.vue";

export default {
  components: {
    SideNavBar,
    AboutMap,
    AboutVR,
  },
  data() {
    return {
      section: true,
    };
  },
};
</script>

<style scoped>
.body {
  background: #8c6c79;
  color: white;
  width: 100vw;
  height: 100vh;
}
</style>
