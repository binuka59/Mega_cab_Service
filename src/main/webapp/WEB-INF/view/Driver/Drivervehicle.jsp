<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.megacab.model.Vehicle" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mega_Cab_Service</title>


 <link href="assets/images/cab1.png" rel="icon">
  <link href="https://fonts.googleapis.com" rel="preconnect">
  <link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Inter:wght@100;200;300;400;500;600;700;800;900&family=Amatic+SC:wght@400;700&display=swap" rel="stylesheet">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Playball&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="assets/user/css/style.css">
    <link rel="stylesheet" href="assets/user/css/user.css">
    <link rel="stylesheet" href="assets/user/css/payment.css">
    <link rel="stylesheet" href="assets/user/css/footer.css">
    <link rel="stylesheet" href="assets/admin/css/menu.css">
   <link href="assets/user/css/bootstrap.min.css" rel="stylesheet">
   <link href="assets/user/css/bootstrap-icons.css" rel="stylesheet">

   <link href="assets/user/swiper/swiper-bundle.min.css" rel="stylesheet">

</head>
<body>

        <%@ include file="driverheader.jsp"%>
                <div class="container-fluid bg-light  ">
                    <div class="container text-center animated bounceInDown">
                        <h1 class="display">Driver</h1>
                       <h3 style="color: red;padding-left:8rem;font-size:1.3rem;font-weight:bold;font-family:Georgia, 'Times New Roman', Times, serif;">
                              <% if (request.getAttribute("errorMessage") != null) { %>
                                  <%= request.getAttribute("errorMessage") %>
                              <% } %>
                       </h3>

                    </div>
                </div>
                        <section class="ftco-section">
                            <h1>Book Information</h1>
                            <div class="container">

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="table-wrap">
                                            <table class="table table-responsive-xl">
                                              <thead>
                                                <tr>

                                                  <th> No</th>
                                                  <th>Vehicle Name</th>
                                                </tr>
                                              </thead>
                                        <tbody>
                                          <%
                                               List<Vehicle> DriverList = (List<Vehicle>) request.getAttribute("DriverController");
                                               if (DriverList != null && !DriverList.isEmpty()) {
                                                   for (Vehicle vehicle : DriverList) {
                                          %>

                                                <tr class="alert" role="alert">

                                                    <td ><%=vehicle.getId()%></td>
                                                    <td><%=vehicle.getName()%></td>

                                                </tr>
                                              <%
                                                 }
                                                 } else {
                                              %>
                                              <tr>
                                                  <td colspan="6">No booking data available</td>
                                              </tr>
                                              <%
                                                  }
                                              %>

                                        </tbody>

                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>


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

                <h3> 24-Hour Service </h3>
                <h2>Your service <br>
                our strength.</h2>

                <p>
                  <span>Thank you Join with us</span>
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
            <script src="assets/user/js/js/bootstrap.bundle.min.js"></script>
</body>
</html>