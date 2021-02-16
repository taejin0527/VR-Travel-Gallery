<template>
  <div class="portfolio section" id="portfolio">
    <div class="container">
      <div class="title-section">
        <p>PORTFOLIO</p>
        <h3>See our <span>amazing</span></h3>
        <h3><span>portfolio</span> collection</h3>
      </div>
      <div class="box-content">
        <div class="portfolio-filter-menu">
          <ul>
            <li data-filter="all" class="active">
              <span>Show All</span>
            </li>
            <li data-filter="1">
              <span>Graphic</span>
            </li>
            <li data-filter="2">
              <span>Landing</span>
            </li>
            <li data-filter="3">
              <span>Art Design</span>
            </li>
          </ul>
        </div>
        <div class="row no-gutters filtr-container">
          <div class="col-md-4 col-sm-6 col-xs-12 filtr-item" data-category="3">
            <div class="content-image">
              <a href="images/portfolio1.jpg" class="portfolio-popup">
                <img src="images/portfolio1.jpg" alt="" />
                <div class="image-overlay"></div>
                <div class="portfolio-caption">
                  <div class="title">
                    <h4>Modern Design</h4>
                    <span>Graphic Design</span>
                  </div>
                </div>
              </a>
            </div>
          </div>
          <div class="col-md-4 col-sm-6 col-xs-12 filtr-item" data-category="1">
            <div class="content-image">
              <a href="images/portfolio2.jpg" class="portfolio-popup">
                <img src="images/portfolio2.jpg" alt="" />
                <div class="image-overlay"></div>
                <div class="portfolio-caption">
                  <div class="title">
                    <h4>Modern Design</h4>
                    <span>Graphic Design</span>
                  </div>
                </div>
              </a>
            </div>
          </div>
          <div
            class="col-md-4 col-sm-6 col-xs-12 filtr-item"
            data-category="2, 1"
          >
            <div class="content-image">
              <a href="images/portfolio3.jpg" class="portfolio-popup">
                <img src="images/portfolio3.jpg" alt="" />
                <div class="image-overlay"></div>
                <div class="portfolio-caption">
                  <div class="title">
                    <h4>Modern Design</h4>
                    <span>Graphic Design</span>
                  </div>
                </div>
              </a>
            </div>
          </div>
          <div class="col-md-4 col-sm-6 col-xs-12 filtr-item" data-category="3">
            <div class="content-image">
              <a href="images/portfolio4.jpg" class="portfolio-popup">
                <img src="images/portfolio4.jpg" alt="" />
                <div class="image-overlay"></div>
                <div class="portfolio-caption">
                  <div class="title">
                    <h4>Modern Design</h4>
                    <span>Graphic Design</span>
                  </div>
                </div>
              </a>
            </div>
          </div>
          <div
            class="col-md-4 col-sm-6 col-xs-12 filtr-item"
            data-category="1, 2"
          >
            <div class="content-image">
              <a href="images/portfolio5.jpg" class="portfolio-popup">
                <img src="images/portfolio5.jpg" alt="" />
                <div class="image-overlay"></div>
                <div class="portfolio-caption">
                  <div class="title">
                    <h4>Modern Design</h4>
                    <span>Graphic Design</span>
                  </div>
                </div>
              </a>
            </div>
          </div>
          <div
            class="col-md-4 col-sm-6 col-xs-12 filtr-item"
            data-category="3, 1"
          >
            <div class="content-image">
              <a href="images/portfolio6.jpg" class="portfolio-popup">
                <img src="images/portfolio6.jpg" alt="" />
                <div class="image-overlay"></div>
                <div class="portfolio-caption">
                  <div class="title">
                    <h4>Modern Design</h4>
                    <span>Graphic Design</span>
                  </div>
                </div>
              </a>
            </div>
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

/*portfolio*/
.portfolio .row .col-md-4 {
  padding: 15px;
}

.portfolio .portfolio-filter-menu {
  margin-bottom: 30px;
  text-align: center;
}

.portfolio .portfolio-filter-menu ul li {
  display: inline-block;
  margin: 0 10px;
  border: 1px solid #614cab;
  cursor: pointer;
  font-size: 15px;
  font-weight: 500;
  color: #444;
  letter-spacing: 1px;
  transition: 0.4s ease-in-out;
  padding: 10px 20px;
  text-transform: uppercase;
  font-weight: 600;
  border-radius: 6px;
}

.portfolio .portfolio-filter-menu ul li span {
  z-index: 99;
  position: relative;
}

.portfolio .portfolio-filter-menu ul li.active {
  background: #614cab;
  color: #fff;
}

.portfolio .content-image {
  position: relative;
}

.portfolio .content-image:hover .image-overlay {
  opacity: 0.7;
}

.portfolio .content-image:hover .portfolio-caption {
  opacity: 1;
}

.portfolio .content-image img {
  width: 100%;
  border-radius: 15px;
}

.portfolio .content-image img:visited {
  outline: none;
}

.portfolio .content-image img:focus {
  outline: none;
}

.portfolio .content-image .portfolio-caption {
  position: absolute;
  left: 30px;
  right: 30px;
  top: 40%;
  color: #333;
  text-align: center;
  opacity: 0;
  transition: all 0.5s ease;
  color: #fff;
}

.portfolio .content-image .portfolio-caption h4 {
  font-size: 20px;
  color: #333;
  font-weight: 600;
  color: #fff;
}

.portfolio .content-image .portfolio-caption .subtitle {
  margin-top: 22px;
  top: 52px;
  position: relative;
  text-align: right;
}

.portfolio .content-image .portfolio-caption ul li {
  display: inline-block;
  margin: 0 5px;
}

.portfolio .content-image .portfolio-caption i {
  width: 30px;
  height: 30px;
  background: #333;
  font-size: 12px;
  line-height: 30px;
  text-align: center;
  color: #fff;
}

.portfolio .content-image .image-overlay {
  background: #614cab;
  opacity: 0.5;
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  border-radius: 15px;
  opacity: 0;
  transition: all 0.5s ease;
  margin: auto;
}
</style>
