<template>
  <v-container fulid class="home-intro" id="home">
    <v-container>
      <v-row>
        <v-col xs="12" sm="12" md="6" cols="12">
          <div class="content">
            <h3><span class="line"></span>WELCOME TO NUVO</h3>
            <h2>Art</h2>
            <h2>Nouvueau</h2>
            <ul>
              <li><a href="" class="button">Android</a></li>
              <li><a href="" class="button">iOS</a></li>
            </ul>
          </div>
        </v-col>
        <v-col xs="12" sm="12" md="6" cols="12">
          <div class="content-image">
            <img src="@/assets/main/NUVO.png" alt="" />
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
export default {};
</script>

<style scoped>
*:focus {
  outline: none;
}
a {
  color: #111;
}
a:hover {
  color: #614cab;
  text-decoration: none;
  transition: 0.5s ease-out;
}
ul li {
  list-style-type: none;
}

/* home intro =========================== */
.home-intro {
  padding: 130px 0 150px !important;
  background-size: 58%;
  background-position: top right;
}

.home-intro .content-image {
  padding-left: 50px;
}

.home-intro .content-image img {
  height: auto;
  max-width: 100%;
  display: inline-block;
  vertical-align: middle;
  float: right;
  margin-top: 100px;
  border-radius: 40px;
  box-shadow: 0 12px 30px -15px #dda288;
}

.home-intro ul li {
  display: inline-block;
  margin-right: 20px;
}

.home-intro .content {
  padding-top: 20px;
}

.home-intro h3 {
  letter-spacing: 1px;
  color: white;
}

.home-intro h3 .line {
  height: 3px;
  background: #dda288;
  width: 60px;
  display: inline-block;
  position: relative;
  bottom: 5px;
  margin-right: 15px;
}

.home-intro h2 {
  font-size: 102px;
  font-weight: 700;
  margin: 60px 0;
  line-height: 54px;
  letter-spacing: 1px;
  -webkit-text-fill-color: transparent;
  -webkit-text-stroke-width: 2px;
  -webkit-text-stroke-color: #dda288;
}

.home-intro h2 span {
  color: #dda288;
}

.home-intro h6 {
  color: #444;
  line-height: 28px;
  font-size: 17px;
  margin-bottom: 15px;
}

/* button */
.button {
  background: #ffb005;
  padding: 12px 30px;
  font-weight: 500;
  font-size: 15px;
  border-radius: 5px;
  color: #fff;
  letter-spacing: 1px;
  margin-top: 22px;
  display: inline-block;
  border: 2px solid transparent;
}

.button:hover {
  border-color: #ffb005;
  color: #222;
  background: transparent;
  transition: 0.5s ease-out;
}
</style>
