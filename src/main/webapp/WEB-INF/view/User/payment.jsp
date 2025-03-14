<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.megacab.model.Payment" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mega_Cab_Service</title>
 <link href="assets/images/cab1.png" rel="icon">
  <link href="https://fonts.googleapis.com" rel="preconnect">
  <link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Inter:wght@100;200;300;400;500;600;700;800;900&family=Amatic+SC:wght@400;700&display=swap" rel="stylesheet">
<link rel="shortcut icon" href="assets/images/cbackground.png">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Playball&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="assets/user/css/style.css">
    <link rel="stylesheet" href="assets/user/css/user.css">
    <link rel="stylesheet" href="assets/user/css/payment.css">
    <link rel="stylesheet" href="assets/user/css/footer.css">

<link href="assets/user/css/bootstrap.min.css" rel="stylesheet">
   <link href="assets/user/css/bootstrap-icons.css" rel="stylesheet">

</head>
<body>


        <%@ include file="header.jsp"%>
                <div class="container-fluid bg-light  ">
                    <div class="container text-center animated bounceInDown">
                        <h1 class="display">Payment</h1>

                    </div>
                </div>


                        <%
                             List<Payment> paymentList = (List<Payment>) request.getAttribute("LoginController");
                             if (paymentList != null && !paymentList.isEmpty()) {
                                 for (Payment payment : paymentList) {
                         %>
          <section class="payment_section layout_padding-bottom">
            <div class="payment_container">
                <div class="container ">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="box ">

                        <div class="detail-box">
                            <h3>MCS00<%=payment.getId()%></h3>
                          <h6>
                            <span><%=payment.getStatus()%></span>
                          </h6>
                          <h6>
                            <span><%=payment.getDate()%></span>
                          </h6>
                          <h6>
                            <span><%=payment.getPickaddress()%> - <%=payment.getDropadress()%> </span>
                          </h6>

                          <h6>
                            <span><%=payment.getAmount()%></span>
                          </h6>

                        </div>

                      </div>
                    </div>
                  </div>
                                  </div>
                                </div>
                              </section>
                     <%
                        }
                        } else {
                       %>

                        <p>No details</p>

                      <%
                        }
                      %>






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
            <script src="assets/user/js/about.js"></script>
            <script src="assets/user/js/js/bootstrap.bundle.min.js"></script>





</body>
</html>