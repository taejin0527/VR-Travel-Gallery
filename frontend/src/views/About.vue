<template>
  <v-container class="body" grid-list-xs>
    <div class="wrapper">
      <div class="bg"></div>

      <!-- NAVBAR
    =============================== -->
      <nav class="navbar">
        <div class="title">lookbook</div>

        <div class="categories">
          <ul>
            <li>man</li>
            <li>women</li>
          </ul>
        </div>

        <div class="menu">
          <ion-icon name="ios-menu"></ion-icon>
        </div>

        <div class="search">
          <ion-icon name="ios-search"></ion-icon>
        </div>
      </nav>

      <!-- SOCIAL MEDIA
    =============================== -->
      <div class="media">
        <ul>
          <li>Facebook</li>
          <li>Twitter</li>
          <li>Instagram</li>
        </ul>
      </div>

      <!-- IMAGES
    =============================== -->
      <div class="img-wrapper01">
        <div class="img01"></div>
        <div class="block"></div>
      </div>
      <div class="img-wrapper02">
        <div class="img02"></div>
        <div class="block"></div>
      </div>

      <!-- TEXT
    =============================== -->
      <div class="text">
        <h1>
          <span class="hidetext">The Great Escape</span>
        </h1>
        <p>
          <span class="hidetext">Issue two out now</span>
        </p>
      </div>

      <!-- DESC
    =============================== -->
      <div class="desc">
        <ul>
          <li>Volume 01</li>
          <li>Volume 02</li>
        </ul>
        <p>
          A fashion insight into leading summer styles, travel destinations to
          may never of thought of, a beginners guide to S&M and much much more.
        </p>
      </div>

      <!-- SCROLLDOWN
    =============================== -->
      <div class="scrolldown">scroll down</div>

      <!-- BOTTOMNAV
    =============================== -->
      <div class="bottomnav">
        <div class="next">up next</div>

        <div class="nav">
          <ul>
            <li>
              <ion-icon name="ios-arrow-up"></ion-icon>
            </li>
            <li>
              <ion-icon name="ios-arrow-down"></ion-icon>
            </li>
          </ul>

          <div class="bottomnav-img"></div>
        </div>
      </div>
    </div>
  </v-container>
</template>

<script>
export default {};
</script>

<style scoped>
.body {
  background: #8c6c79;
  color: white;
  width: 85%;
  height: 100vh;
}

ul {
  list-style: none;
}

.bg {
  position: absolute;
  left: 0%;
  width: 0%;
  height: 100%;
  background: #8c6c79;
  z-index: -1;
}

/* NAVBAR
=========================== */
.navbar {
  display: flex;
  justify-content: space-between;
  height: 80px;
  align-items: center;
  padding: 0 50px;
}

.navbar .title {
  margin-right: 100px;
  font-size: 14px;
  text-transform: uppercase;
  letter-spacing: 2px;
  font-weight: 600;
}

.navbar .categories {
  margin-right: auto;
}

.navbar .categories ul li {
  display: inline-block;
  padding: 0 30px;
  font-size: 10px;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: 500;
}

.navbar .menu {
  margin-right: 50px;
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
  font-size: 10px;
  font-weight: 500;
  letter-spacing: 1px;
}

/* IMAGES
=========================== */
.img-wrapper01 {
  position: absolute;
  top: 120px;
  left: 200px;
}

.img-wrapper01 .img01 {
  background: url("~@/assets/images/example/1.jpg") no-repeat;
  background-position: 50%;
  background-size: cover;
  height: 500px;
  width: 350px;

  opacity: 0;
  animation: image-appear 0.0001s linear forwards;
  animation-delay: 2.2s;
}

.img-wrapper02 {
  position: absolute;
  top: 0;
  left: 650px;
}

.img-wrapper02 .img02 {
  background: url("~@/assets/images/example/2.jpg") no-repeat;
  background-position: 50%;
  background-size: cover;
  height: 100vh;
  width: 800px;

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
  background: rgb(163, 124, 140);
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
  left: 200px;
  bottom: 70px;
  display: flex;
}

.desc ul {
  margin-right: 50px;
}

.desc ul li {
  font-size: 10px;
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

/* BOTTOMNAV
=========================== */
.bottomnav {
  position: absolute;
  bottom: 30px;
  right: 50px;
}

.bottomnav .next {
  font-size: 10px;
  letter-spacing: 8px;
  font-weight: 500;
  text-transform: uppercase;
  margin-bottom: 30px;
}

.bottomnav .next::after {
  content: "";
  display: inline-block;
  border: 1px solid #fff;
  width: 148px;
  margin: 0 0 0 20px;
  transform: translateY(-3px);
}

.bottomnav .nav {
  display: flex;
}

.bottomnav ul {
  background: #fff;
  color: #000;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.bottomnav ul li {
  padding: 30px 20px;
  font-size: 25px;
}

.bottomnav-img {
  background: url("~@/assets/images/example/3.jpg") no-repeat;
  background-position: 50%;
  background-size: cover;
  height: 200px;
  width: 300px;
}
</style>
