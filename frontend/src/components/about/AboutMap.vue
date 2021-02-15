<template>
  <div class="wrapper">
    <!-- NAVBAR
    =============================== -->
    <div class="navbar">
      <div class="title">{{ this.navbar.title }}</div>
    </div>

    <!-- SOCIAL MEDIA
    =============================== -->
    <div class="media">
      <ul>
        <li>SSAFY</li>
        <li>Github</li>
        <li>@2021</li>
      </ul>
    </div>

    <!-- IMAGES
    =============================== -->
    <div class="img-wrapper01">
      <div class="img01" @click="viewWorldmap"></div>
      <div class="block"></div>
    </div>
    <div class="img-wrapper02">
      <div class="img02" @click="viewVR"></div>
      <div class="block"></div>
    </div>

    <!-- TEXT
    =============================== -->
    <div class="text">
      <h1>
        <span class="hidetext">The Great Gallery</span>
      </h1>
      <p>
        <span class="hidetext">Share your memories here</span>
      </p>
    </div>

    <!-- DESC
    =============================== -->
    <div class="desc">
      <ul>
        <li>Feature 01</li>
        <li>Feature 02</li>
      </ul>
      <ul>
        <p>첫 번째 기능</p>
        <p>두 번째 기능</p>
      </ul>
    </div>

    <!-- SCROLLDOWN
    =============================== -->
    <div class="scrolldown">scroll down</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      navbar: {
        title: "About us",
        section: "",
      },
      images: {
        wrapper1: "img-wrapper01",
        wrapper2: "img-wrapper02",
        img1: "img01",
        img2: "img02",
      },
    };
  },
  methods: {
    viewWorldmap() {
      this.images.wrapper1 = "img-wrapper01";
      this.images.img1 = "img01";
      this.images.wrapper2 = "img-wrapper02";
      this.images.img2 = "img02";
    },
    viewVR() {
      this.images.wrapper2 = "img-wrapper01";
      this.images.img2 = "img01";
      this.images.wrapper1 = "img-wrapper02";
      this.images.img1 = "img02";
    },
  },
};
</script>

<style scoped>
ul {
  list-style: none;
}

/* NAVBAR
=========================== */
.navbar .title {
  margin-left: 150px;
  text-transform: uppercase;
  letter-spacing: 2px;
  font-weight: 600;
}

/* SOCIAL MEDIA
=========================== */
.media {
  position: absolute;
  top: 350px;
  right: -90px;
  transform: rotate(-90deg);
}

.media ul li {
  display: inline-block;
  padding: 0 20px;
  font-size: 13px;
  font-weight: 500;
  letter-spacing: 1px;
  cursor: pointer;
}

/* IMAGES
=========================== */
.img-wrapper01 {
  position: absolute;
  top: 120px;
  left: 200px;
}

.img-wrapper01 .img01 {
  background: url("~@/assets/worldmap/worldmap.svg") no-repeat;
  background-position: 50%;
  background-size: cover;
  height: 30vh;
  width: 20vw;

  opacity: 0;
  animation: image-appear 0.0001s linear forwards;
  animation-delay: 2.2s;
}

.img-wrapper02 {
  position: absolute;
  top: 120px;
  left: 650px;
}

.img-wrapper02 .img02 {
  background: url("~@/assets/main/VRIcon.png") no-repeat;
  background-position: 50%;
  background-size: cover;
  height: 80vh;
  width: 50vw;

  opacity: 0;
  animation: image-appear 0.0001s linear forwards;
  animation-delay: 2.4s;
}

@keyframes image-appear {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

/* BLOCKS
=========================== */
.block {
  position: absolute;
  top: 0;
  left: 0;
  width: 0%;
  height: 100%;
  background: #dda288;
  animation: block-appear 1.5s cubic-bezier(0.19, 1, 0.22, 1) forwards;
  animation-delay: 1.5s;
}

.img-wrapper02 .block {
  animation-delay: 1.8s;
}

@keyframes block-appear {
  0% {
    left: 0;
    width: 0%;
  }
  50% {
    left: 0;
    width: 100%;
  }
  100% {
    left: 100%;
    width: 0%;
  }
}

/* TEXT
=========================== */
.text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  white-space: nowrap;
  width: 1000px;
}

.text h1 {
  font-size: 120px;
  font-family: "Tinos";
  font-weight: lighter;
  letter-spacing: 5px;

  position: relative;
  overflow: hidden;
  height: 150px;
  width: 100%;
}
.text h1 .hidetext {
  position: absolute;
}

.text p {
  font-size: 40px;
  font-family: "Tinos";
  text-transform: uppercase;
  letter-spacing: 5px;
  font-weight: lighter;

  position: relative;
  overflow: hidden;
  height: 50px;
  width: 100%;
}
.text p .hidetext {
  position: absolute;
}

/* DESC
=========================== */
.desc {
  position: absolute;
  left: 150px;
  bottom: 70px;
  display: flex;
}

.desc ul {
  margin-right: 50px;
}

.desc ul li {
  font-size: 13px;
  letter-spacing: 1px;
  font-weight: 500;
  border-bottom: 1px solid #fff;
  margin-bottom: 10px;
}

.desc p {
  width: 250px;
  font-size: 10px;
  font-weight: 300;
  line-height: 2;
}

/* SCROLLDOWN
=========================== */
.scrolldown {
  font-size: 10px;
  font-weight: 500;
  text-transform: uppercase;
  letter-spacing: 5px;

  position: absolute;
  bottom: 110px;
  left: -60px;
  transform: rotate(-90deg);
}

.scrolldown::before {
  content: "";
  border: 1px solid #fff;
  width: 100px;
  margin: 0 20px 0 0;
  transform: translateY(-3px);
  opacity: 0.5;
  display: inline-block;
}
</style>
