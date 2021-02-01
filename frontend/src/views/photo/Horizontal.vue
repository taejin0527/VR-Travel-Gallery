<template>
  <div class="layered-body">
    <div class="layered-guard">
      <div class="eyes">
        <div class="upper-pupil"></div>
        <div class="iris"></div>
        <div class="lower-pupil"></div>
      </div>
      <div class="eyes">
        <div class="upper-pupil"></div>
        <div class="iris"></div>
        <div class="lower-pupil"></div>
      </div>
    </div>

    <div class="layered-img">
      <div class="container">
        <img src="~@/assets/images/example/1.jpg" />
        <img src="~@/assets/images/example/2.jpg" />
        <img src="~@/assets/images/example/3.jpg" />
        <img src="~@/assets/images/example/4.jpg" />
        <img src="~@/assets/images/example/5.jpg" />
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.layered-body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100vh;
  display: flex;
  z-index: 100;
}

.layered-guard {
  position: absolute;
  margin: 0;
  padding: 20px;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.eyes {
  width: 50px;
  height: 50px;
  background: #fffdf9;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 1rem;
  clip-path: circle(50% at center);
  position: relative;
}
.iris {
  width: 50%;
  height: 50%;
  background: #343434;
  border-radius: 50%;
  animation: movement 6s ease-in-out infinite;
}
.upper-pupil,
.lower-pupil {
  z-index: 5;
  width: 100%;
  height: 25px;
  background: #5a4e4d;
  position: absolute;
  animation: blink 6s ease-in-out infinite;
}
.upper-pupil {
  top: 0;
}
.lower-pupil {
  bottom: 0;
}

@keyframes movement {
  0%,
  10% {
    transform: translate(0, 50%);
  }
  20% {
    transform: translateX(-80%);
  }
  30%,
  70% {
    transform: translate(0, 50%);
  }
  80% {
    transform: translateX(80%);
  }
  100% {
    transform: translate(0, 50%);
  }
}

@keyframes blink {
  0%,
  30% {
    height: 20%;
  }
  45%,
  50% {
    height: 50%;
  }
  55% {
    height: 10%;
  }
  60%,
  70% {
    height: 30%;
  }
  80%,
  100% {
    height: 20%;
  }
}

.layered-img {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
.container {
  position: relative;
  width: 50%;
  height: 50%;
  transform: rotate(10) skewY(-10deg) scale(0.8);
  transition: 0.5s;
}
.container img {
  position: absolute;
  width: 100%;
  height: 100%;
  transition: 0.5s;
}
.container:hover img:nth-child(1) {
  transform: translateX(-250px) scale(0.5);
  opacity: 0.2;
  z-index: 3;
}
.container:hover img:nth-child(2) {
  transform: translateX(-150px) scale(0.6);
  opacity: 0.4;
  z-index: 4;
}
.container:hover img:nth-child(3) {
  transform: translateX(-50px) scale(0.7);
  opacity: 0.6;
  z-index: 5;
}
.container:hover img:nth-child(4) {
  transform: translateX(50px) scale(0.8);
  opacity: 0.8;
  z-index: 6;
}
.container:hover img:nth-child(5) {
  transform: translateX(150px) scale(0.9);
  opacity: 1;
  z-index: 7;
}
</style>
