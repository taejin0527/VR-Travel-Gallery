<template>
  <div class="services section bg-grey" id="services">
    <div class="container">
      <div class="title-section">
        <p>SERVICES</p>
        <h3>Choose what</h3>
        <h3>you like our services</h3>
      </div>
      <div class="row">
        <div class="col-md-4">
          <div class="content">
            <i class="lni lni-wordpress-filled"></i>
            <h5>WordPress</h5>
            <p class="mb-0">
              Lorem ipsum dolor sit amet consectetur adipisicing elit Ea
              laudantium empore nobis quisquam.
            </p>
          </div>
        </div>
        <div class="col-md-4">
          <div class="content">
            <i class="lni lni-vector"></i>
            <h5>Logo Design</h5>
            <p class="mb-0">
              Lorem ipsum dolor sit amet consectetur adipisicing elit Ea
              laudantium empore nobis quisquam.
            </p>
          </div>
        </div>
        <div class="col-md-4">
          <div class="content">
            <i class="lni lni-keyword-research"></i>
            <h5>Online Seo</h5>
            <p class="mb-0">
              Lorem ipsum dolor sit amet consectetur adipisicing elit Ea
              laudantium empore nobis quisquam.
            </p>
          </div>
        </div>
        <div class="col-md-4">
          <div class="content">
            <i class="lni lni-mobile"></i>
            <h5>Mobile Apps</h5>
            <p class="mb-0">
              Lorem ipsum dolor sit amet consectetur adipisicing elit Ea
              laudantium empore nobis quisquam.
            </p>
          </div>
        </div>
        <div class="col-md-4">
          <div class="content">
            <i class="lni lni-list"></i>
            <h5>Copywriting</h5>
            <p class="mb-0">
              Lorem ipsum dolor sit amet consectetur adipisicing elit Ea
              laudantium empore nobis quisquam.
            </p>
          </div>
        </div>
        <div class="col-md-4">
          <div class="content">
            <i class="lni lni-bulb"></i>
            <h5>Brand Strategy</h5>
            <p class="mb-0">
              Lorem ipsum dolor sit amet consectetur adipisicing elit Ea
              laudantium empore nobis quisquam.
            </p>
          </div>
        </div>
      </div>
      <div class="completed">
        <div class="row">
          <div class="col-md-4">
            <h4>540</h4>
            <span>Clients</span>
          </div>
          <div class="col-md-4">
            <h4>831</h4>
            <span>Project</span>
          </div>
          <div class="col-md-4">
            <h4>65</h4>
            <span>Branch</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
/* section */
.section {
  padding: 90px 0 100px;
}

.title-section {
  text-align: center;
  margin-bottom: 50px;
}

.title-section p {
  color: #dda288;
  font-size: 2rem;
  font-weight: 800;
  margin-bottom: 15px;
  display: block;
}

.title-section h3 {
  color: white;
  font-size: 32px;
  font-weight: 600;
  margin-bottom: 6px;
}

.title-section h3 span {
  -webkit-text-fill-color: transparent;
  -webkit-text-stroke-width: 1px;
  -webkit-text-stroke-color: #614cab;
}

/* services */
.services .content {
  background: #fff;
  border-radius: 15px;
  text-align: center;
  padding: 30px;
  cursor: default;
  margin-bottom: 30px;
}

.services .content i {
  font-size: 56px;
  color: #614cab;
}

.services .content h5 {
  margin-top: 25px;
  margin-bottom: 15px;
  font-size: 20px;
}

.services .content:hover {
  background: #614cab;
  color: #fff;
  transition: 0.7s ease-out;
  box-shadow: 0 12px 30px -15px rgba(0, 0, 0, 0.2);
}

.services .content:hover i {
  color: #fff;
}

.services .content:hover h5 {
  color: #fff;
}

.services .completed {
  background: #fff;
  text-align: center;
  padding: 60px 0;
  border-radius: 15px;
  margin-top: 62px;
  margin-bottom: 10px;
}

.services .completed h4 {
  font-size: 58px;
  font-weight: 600;
  color: #614cab;
}

.services .completed span {
  font-size: 24px;
  font-weight: 500;
}
</style>
