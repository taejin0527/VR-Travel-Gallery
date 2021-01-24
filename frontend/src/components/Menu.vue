<template>
  <div id="menu">
    <!-- 임시 버튼 -->
    <button @click="closeMenuPage">일단 여기 눌러서 꺼주세요!</button>
  </div>
</template>

<script>
export default {
  name:"Menu",
  props: {
    isMenuPageOpen:[Boolean, String]
  },
  methods: {
    // 메뉴 끄기
    closeMenuPage: function () {
      this.$emit('closeMenuPage')
    }
  }
}
</script>

<style scoped>
/* 화면 전체 랩핑 */
#menu {
  position: fixed;
  margin: 0;
  padding: 0;
  z-index:200;
  background-color: #b2876f;
  height: 100%;
  width: 100%;
}

</style>