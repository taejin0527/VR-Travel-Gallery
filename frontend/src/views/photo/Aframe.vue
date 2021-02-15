<template>
  <a-scene>
    <a-assets>
      <template v-for="(image, idx) in images">
        <img
          :src="image.imgSrc"
          :id="idx"
          :key="idx + 10"
          crossorigin="anonymous"
        />
        <img
          :id="mergeId('', idx)"
          :key="idx"
          crossorigin="anonymous"
          :src="image.thumbSrc"
        />
      </template>
      <audio
        id="click-sound"
        crossorigin="anonymous"
        src="https://cdn.aframe.io/360-image-gallery-boilerplate/audio/click.ogg"
      ></audio>
    </a-assets>

    <v-btn
      elevation="3"
      fab
      color="#DDA288"
      style="position:fixed; right:60px; top:280px; color:white;"
      @click="clickGoBack"
    >
      <v-icon size="38px">
        mdi-arrow-left-bold-circle
      </v-icon>
    </v-btn>
    <!-- 360도 이미지를 보여주는 엔티티. -->
    <a-sky
      id="image-360"
      radius="10"
      :src="currId"
      animation__fade="property: components.material.material.color; type: color; from: #FFF; to: #000; dur: 300; startEvents: fade"
      animation__fadeback="property: components.material.material.color; type: color; from: #000; to: #FFF; dur: 300; startEvents: animationcomplete__fade"
    ></a-sky>

    <!-- 이미지 링크가 담기는 엔티티. -->
    <a-entity
      v-for="(image, idx) in images"
      :key="idx"
      id="links"
      layout="type: line; margin: 1.5"
      position="0 -1 -4"
    >
      <a-entity
        class="link"
        v-on:click="changeScene(idx)"
        geometry="primitive: plane; height: 1; width: 1"
        :material="'shader: flat; src: ' + mergeId('#', idx)"
        event-set__mouseenter="scale: 1.2 1.2 1"
        event-set__mouseleave="scale: 1 1 1"
        :event-set__click="
          '_target: #image-360; _delay: 300; material.src: ' + (idx + 10)
        "
        proxy-event="event: click; to: #image-360; as: fade"
        sound="on: click; src: #click-sound"
      ></a-entity>
    </a-entity>

    <!-- Camera + cursor. -->
    <a-entity camera look-controls>
      <a-cursor
        id="cursor"
        animation__click="property: scale; startEvents: click; from: 0.1 0.1 0.1; to: 1 1 1; dur: 150"
        animation__fusing="property: fusing; startEvents: fusing; from: 1 1 1; to: 0.1 0.1 0.1; dur: 1500"
        event-set__mouseenter="_event: mouseenter; color: springgreen"
        event-set__mouseleave="_event: mouseleave; color: black"
        raycaster="objects: .link"
      ></a-cursor>
    </a-entity>
  </a-scene>
</template>

<script>
export default {
  name: "GalleryVR",
  data: function() {
    return {
      currId: "#0",
      images: [
        {
          // https://medium.com/bom-i/binding-image-src-from-object-in-vue-js-76b782eb19a7
          imgSrc: require("@/assets/photo/PANO_freezerwarehouse.jpg"),
          thumbSrc:
            "https://cdn.aframe.io/360-image-gallery-boilerplate/img/thumb-city.jpg"
        },
        {
          imgSrc: require("@/assets/photo/PANO_HomesJungle1.jpg"),
          thumbSrc:
            "https://cdn.aframe.io/360-image-gallery-boilerplate/img/thumb-cubes.jpg"
        }
        // {
        //   imgSrc:
        //     "https://cdn.aframe.io/360-image-gallery-boilerplate/img/sechelt.jpg",
        //   thumbSrc:
        //     "https://cdn.aframe.io/360-image-gallery-boilerplate/img/thumb-sechelt.jpg"
        // }
      ]
    };
  },
  methods: {
    mergeId(prefix, idx) {
      return prefix + "thumb-" + idx;
    },
    clickGoBack: function() {
      this.$router.push({ name: localStorage.getItem("page") });
    },
    changeScene(idx) {
      this.currId = "#" + (idx + 1);
      console.log(this.currId);
      return;
    }
  }
};
</script>
