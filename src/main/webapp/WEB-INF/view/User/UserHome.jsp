<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="java.util.List" %>
        <%@ page import="com.megacab.model.Vehicle" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mega_Cab_Home</title>
<link rel="shortcut icon" href="assets/images/cbackground.png">


  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Inter:wght@100;200;300;400;500;600;700;800;900&family=Amatic+SC:wght@400;700&display=swap" rel="stylesheet">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />


    <link rel="stylesheet" href="assets/user/css/user.css">
    <link rel="stylesheet" href="assets/user/css/style.css">
    <link rel="stylesheet" href="assets/user/css/driver.css">
<link rel="stylesheet" href="assets/admin/css/menu.css">
    <link rel="stylesheet" href="assets/user/css/footer.css">

   <link href="assets/user/css/bootstrap.min.css" rel="stylesheet">
   <link href="assets/user/css/bootstrap-icons.css" rel="stylesheet">



</head>
<body>


    <section id="hero" class="hero">

      <div class="container">
        <div class="row gy-4 justify-content-center justify-content-lg-between">
          <div class="col-lg-5 order-2 order-lg-1 d-flex flex-column justify-content-center">
            <h1 data-aos="fade-up">Enjoy Your Journey..<br>Join With Us.</h1>
            <p data-aos="fade-up" data-aos-delay="100">Experience hassle-free travel with our Mega cab services.</p>

          </div>
          <div class="col-lg-5 order-1 order-lg-2 hero-img" data-aos="zoom-out">
            <img src="assets/user/img/c1.png" class="img-fluid animated" alt="">
          </div>
        </div>
      </div>

    </section>
<%@ include file="header.jsp"%>
          <section class="offer_section layout_padding-bottom">
              <div class="offer_container">
                <div class="container ">
                  <div class="row">
                  <%
                       List<Vehicle> VehicleList = (List<Vehicle>) request.getAttribute("LoginController");
                       if (VehicleList != null && !VehicleList.isEmpty()) {
                           for (Vehicle vehicle : VehicleList) {
                  %>
                    <div class="col-md-6 d-flex justify-content-center align-items-center">
                      <div class="box">

                        <div class="img-box">
                        <img src="<%=vehicle.getFilename() %> " class="img-fluid animated" alt="">

                        </div>
                        <div class="detail-box">

                          <h3> <%= vehicle.getName() %> </h3>
                          <h6>
                            <span>Initial Charge: </span> Rs<span1 class="enter" ><%= vehicle.getInicharge() %>/</span1>
                          </h6>

                          <h6>
                            <span>Booking Fee: </span> <span1 class="enter" ><%=vehicle.getBookfee()%></span1>

                          </h6>
                          <h5>
                            <span> (Please note that if each vehicle drives more than our recommended mileage, an additional 1 km will be charged.)</span>
                          </h5>

                        </div>
                        <a href="VehicleController?action=showvehicle&vehicleName=<%= vehicle.getName() %>" class="btn">View More</a>
                    </div>
            </div>
            <%
               }
               } else {
            %>
                <p>No vehicle  display.</p>
            <%
                }
            %>


                    </div>
                  </div>
                </div>
              </div>
            </section>

                         <div class="text-center">
                             <h1 class="rules-heading py-5">Bookin Information</h1>
                         </div>
            <section class="ftco-section">

                    <div class="container">

                        <div class="row">
                            <div class="col-md-12">
                                <div class="table-wrap">
                                    <table class="table table-responsive-xl">
                                      <thead>
                                        <tr>

                                          <th>Book No</th>
                                          <th>Vehicle</th>
                                          <th>PickTime</th>
                                          <th>PickDate</th>
                                          <th>Journey</th>
                                          <th>Status</th>
                                        </tr>
                                      </thead>
                                        <tbody>

                                                <tr class="alert" role="alert">

                                                    <td ></td>
                                                    <td></td>
                                                    <td><span class="active"></span></td>
                                                    <td></span></td>
                                                    <td class="status"><span class="active"></span></td>
                                                    <td>
                                                         <a href="AdminController?action=see" class="btn btn-info">See more</a>
                                                        <button type="button" class="btn btn-danger">Reject</button>
                                                    </td>
                                                </tr>

                                        </tbody>

                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

            <div class="container py-5 wow fadeInUp" data-wow-delay="0.1s">

               <div class="text-center">
                   <h1 class="rules-heading py-5">Rules & Regulations</h1>
               </div>

                         <section class="rules_section layout_padding-bottom">
                             <div class="ruls-container">

                                 <div class="row">
                                    <div class="col-md-12 d-flex justify-content-center align-items-center">
                                     <div class="box">
                                    <h2>1)The fuel capacity of the vehicle is filled and supplied before each destination.</h2>
                                    <h2>2)Please note that if each vehicle drives more than our recommended mileage, an additional 1 km will be charged.</h2>
                                     <h2>3)A charge will be made for damages that occur inside the vehicle on the way to the destination..</h2>

                                    </div>

                                 </div>

                             <div>
                         </section>

            </div>


       <div class="container py-5 wow fadeInUp" data-wow-delay="0.1s">
           <div class="container">
               <div class="text-center">

                   <h1 class="rules-heading py-5">Happy Customers</h1>
               </div>

               <!-- Swiper Container -->
               <div class="swipers mySwiper">
                   <div class="swiper-wrapper ">
                       <div class="swiper-slide   border rounded p-4" >


                           <div class="d-flex align-items-center">
                               <img class="img-fluid flex-shrink-0 rounded-circle" src="assets/user/img/car.png" style="width: 50px; height: 50px;">
                               <div class="ps-3">
                                   <h5 class="mb-1">Client Name</h5>
                                   <small>Profession</small>
                               </div>
                           </div>
                           <p>Dolor et eos labore, stet justo sed est sed. Diam sed sed dolor stet amet eirmod eos labore diam</p>
       <center>
                           <i class="fa fa-star fa-2x text-warning mb-3" aria-hidden="true"></i>
                           <i class="fa fa-star fa-2x text-warning mb-3" aria-hidden="true"></i>
                           <i class="fa fa-star fa-2x text-warning mb-3" aria-hidden="true"></i>
                           <i class="fa fa-star fa-2x text-warning mb-3" aria-hidden="true"></i>
                           <i class="fa fa-star fa-2x text-warning mb-3" aria-hidden="true"></i>
                           </center>
                       </div>

                       <div class="swiper-slide   border rounded p-4">
                           <i class="fa fa-quote-left fa-2x text-primary mb-3"></i>
                           <p>Dolor et eos labore, stet justo sed est sed. Diam sed sed dolor stet amet eirmod eos labore diam</p>
                           <div class="d-flex align-items-center">
                               <img class="img-fluid flex-shrink-0 rounded-circle" src="img/testimonial-2.jpg" style="width: 50px; height: 50px;">
                               <div class="ps-3">
                                   <h5 class="mb-1">Client Name</h5>
                                   <small>Profession</small>
                               </div>
                           </div>
                       </div>

                       <div class="swiper-slide   border rounded p-4">
                           <i class="fa fa-quote-left fa-2x text-primary mb-3"></i>
                           <p>Dolor et eos labore, stet justo sed est sed. Diam sed sed dolor stet amet eirmod eos labore diam</p>
                           <div class="d-flex align-items-center">
                               <img class="img-fluid flex-shrink-0 rounded-circle" src="img/testimonial-3.jpg" style="width: 50px; height: 50px;">
                               <div class="ps-3">
                                   <h5 class="mb-1">Client Name</h5>
                                   <small>Profession</small>
                               </div>
                           </div>
                       </div>

                       <div class="swiper-slide   border rounded p-4">
                           <i class="fa fa-quote-left fa-2x text-primary mb-3"></i>
                           <p>Dolor et eos labore, stet justo sed est sed. Diam sed sed dolor stet amet eirmod eos labore diam</p>
                           <div class="d-flex align-items-center">
                               <img class="img-fluid flex-shrink-0 rounded-circle" src="img/testimonial-4.jpg" style="width: 50px; height: 50px;">
                               <div class="ps-3">
                                   <h5 class="mb-1">Client Name</h5>
                                   <small>Profession</small>
                               </div>
                           </div>
                       </div>
                   </div>
               </div>
           </div>
       </div>

        <section class="service layout_padding-bottom">

                     <div class="service_container">
                       <div class="container ">
                         <div class="row">
                           <div class="col-md-3">
                             <div class="box ">
                               <div class="detail-box">

                                      <i class="bi bi-geo-fill"></i>
                                      <span>QUICK RIDER</span>
                                      <p class="separator"> island Wide Travelling </p>
                               </div>
                             </div>
                           </div>
                           <div class="col-md-3">
                             <div class="box">

                               <div class="detail-box">
                                     <div class="services-block">
                                       <i class="bi bi-card-checklist"></i>
                                       <span>Safety Travelling </span>
                                       <p class="separator"> Your Safety is Our Priority! </p>
                                </div>
                               </div>
                             </div>
                           </div>
                           <div class="col-md-3">
                             <div class="box">

                               <div class="detail-box">
                                     <div class="services-block">
                                       <i class="bi bi-calendar-check"></i>
                                       <span> 100% Digital</span>
                                       <p class="separator"> Seamless & Smart Solutions! </p>
                                </div>
                               </div>
                             </div>
                           </div>
                           <div class="col-md-3">
                             <div class="box">

                               <div class="detail-box">
                                     <div class="services-block">
                                       <i class="bi bi-credit-card-fill"></i>
                                       <span>Easy Payment</span>
                                       <p class="separator">  Fast, Secure & Hassle-Free! </p>
                                </div>
                               </div>
                             </div>
                           </div>

                         </div>
                       </div>
                     </div>
                   </section>

    <section  class="banner section dark-background">

      <div class="container" data-aos="fade-up" data-aos-delay="100">

        <div class="swiper init-swiper">
          <div class="swiper-wrapper">

            <div class="swiper-slide">
              <div class="testimonial-item">

                <h3>Call 24-Hour Service Available</h3>
                <h2>Call Now & Book Your Taxi<br>
                For Your Next Ride.</h2>

                <p>
                  <span>(081) 7 776 666</span>
                </p>
              </div>
            </div>







          </div>
          <div class="swiper-pagination"></div>
        </div>

      </div>

    </section><!-- /Testimonials Section -->

            <%@ include file="footer.jsp" %>
                 <script>
                     document.getElementById("year").textContent = new Date().getFullYear();
                 </script>
            <script src="assets/user/js/main.js"></script>
            <script src="assets/user/js/about.js"></script>
            <script src="assets/user/swiper/swiper-bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
            <script src="assets/user/js/script.js"></script>
            <script src="assets/user/js/js/bootstrap.bundle.min.js"></script>


</body>
</html>