<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.megacab.model.Booking" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mega_Cab_Service</title>
<link rel="shortcut icon" href="assets/images/cbackground.png">


  <link href="https://fonts.googleapis.com" rel="preconnect">
  <link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Inter:wght@100;200;300;400;500;600;700;800;900&family=Amatic+SC:wght@400;700&display=swap" rel="stylesheet">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Playball&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="assets/user/css/style.css">
    <link rel="stylesheet" href="assets/user/css/user.css">
    <link rel="stylesheet" href="assets/user/css/dash.css">
    <link rel="stylesheet" href="assets/admin/css/menu.css">
    <link rel="stylesheet" href="assets/user/css/footer.css">

   <link href="assets/user/css/bootstrap.min.css" rel="stylesheet">
   <link href="assets/user/css/bootstrap-icons.css" rel="stylesheet">

   <link href="assets/user/swiper/swiper-bundle.min.css" rel="stylesheet">

</head>
<body>


        <%@ include file="header.jsp"%>
                <div class="container-fluid bg-light  ">
                    <div class="container text-center animated bounceInDown">
                        <h1 class="display">Booking</h1>
                       <h3 style="color: red;padding-left:8rem;font-size:1.3rem;font-weight:bold;font-family:Georgia, 'Times New Roman', Times, serif;">
                              <% if (request.getAttribute("errorMessage") != null) { %>
                                  <%= request.getAttribute("errorMessage") %>
                              <% } %>
                       </h3>

                    </div>
                </div>





                    <%
                    Integer Id = (Integer) session.getAttribute("userId");
                    String Username = (String) session.getAttribute("name");
                    String Email = (String) session.getAttribute("email");
                    String Mobile = (String) session.getAttribute("mobile");
                    String Nic = (String) session.getAttribute("nic");
                    String vehicleprice  = (String) session.getAttribute("vehicleprice");
                    String vehicleaddition = (String) session.getAttribute("vehicleaddition");
                    String vehicleestimate = (String) session.getAttribute("vehicleestimate");
                    String vehiclename = (String) session.getAttribute("vehiclename");

                    %>

      <div class="top booking">
        <div class="container-fluid py-6">

            <form action="BookingController?action=add" method="post">
                <div class="container ">
                  <div class="row ">

                    <input type="hidden" name="id" value="<%= Id %>">

                     <div class="col-md-6 form-group ">
                        <label>Your Name :</label>
                        <input type="text" name="name" class="form-control" id="name" placeholder="Your Name" value="<%= Username != null ? Username : "" %>" data-rule="minlen:4" data-msg="Please enter at least 4 chars" required>
                          <div class="validate""></div>
                     </div>


                     <div class="col-md-6 form-group mt-3 mt-md-0 ">
                        <label>Your Email Address :</label>
                        <input type="email" class="form-control" name="email" id="email" placeholder=" Email Address" value="<%= Email != null ? Email : "" %>" data-rule="email" data-msg="Please enter a valid email" required>
                        <div class="validate"></div>
                     </div>

                     <div class="col-md-6 form-group mt-3 mt-md-0">
                        <label>Your Mobile number :</label>
                        <input type="text" class="form-control" name="phone" id="phone" placeholder=" contact number" data-rule="minlen:4" value="<%= Mobile != null ? Mobile : "" %>" data-msg="Please enter at least 4 chars" required>
                        <div class="validate"></div>
                      </div>

                      <div class="col-md-6 form-group mt-3 mt-md-0">
                        <label>N.I.C. Number:</label>
                        <input type="text" name="nic" class="form-control" id="nic" value="<%= Nic != null ? Nic : "" %>" placeholder="Insert your Nic Number" required>
                        <div class="validate"></div>
                      </div>

                      <div class="col-md-6 form-group mt-3 mt-md-0">
                        <label>Selected Vehicle :</label>
                        <input type="text" class="form-control"  name="vehicle" id="vehicle" value="<%= vehiclename %>" readonly>
                        <div class="validate"></div>
                      </div>



                      <div class="col-lg-6 col-md-6 form-group mt-3 mt-md-0">
                        <label>Select Driver :</label>
                        <select type="text" class="form-control" name="driver" id="driver" placeholder="" required >
                          <option>Driver Type</option>
                          <option value ="with">With a Driver</option>
                          <option value ="without">Without a Driver </option>

                            <div class="validate"></div>
                        </select>
                      </div>

                      <div class="col-md-6 form-group mt-3 mt-md-0">
                        <label>Select pickup Date :</label>
                        <input type="date" name="date" class="form-control" id="date" placeholder="Pickup Date" data-rule="minlen:4" data-msg="Please enter at least 4 chars" required>
                        <div class="validate"></div>
                      </div>

                        <script>
                            let today = new Date();
                            today.setDate(today.getDate() +1);
                            let yesterday = today.toISOString().split('T')[0];

                            document.getElementById("date").setAttribute("min", yesterday);
                        </script>


                      <div class="col-md-6 form-group mt-3 mt-md-0">
                        <label>Select Pickup Time :</label>
                         <input type="time" class="form-control" name="time" id="time" placeholder="Pickup time" data-rule="minlen:4" data-msg="Please enter at least 4 chars" required>
                        <input type="hidden" name="formattedTime" id="formattedTime">
                        <div class="validate"></div>
                      </div>

                        <script>
                            document.getElementById("time").addEventListener("change", function () {
                                let timeInput = this.value; // Get the time (HH:mm format)
                                let formattedTimeInput = document.getElementById("formattedTime");

                                if (timeInput) {
                                    let [hours, minutes] = timeInput.split(":");
                                    let ampm = hours >= 12 ? "PM" : "AM";
                                    hours = hours % 12 || 12; // Convert 0 to 12 for AM
                                    formattedTimeInput.value = hours + ":" + minutes + " " + ampm;
                                }
                            });
                        </script>

                      <div class="col-md-6 form-group mt-3 mt-md-0">
                        <label>Pickup Address:</label>
                        <input type="text" name="pickaddress" class="form-control" id="pickad" placeholder="Start Destination" required>
                        <div class="validate"></div>
                      </div>

                    <div class="col-md-6 form-group mt-3 mt-md-0">
                        <label>Drop off Address:</label>
                        <input type="text" name="dropaddress" class="form-control" id="dropad" placeholder="End Destination" required>
                        <div class="validate"  style="color:red;"></div>
                    </div>



                    </div>
                             <div  class="col-12 d-flex justify-content-center mt-3">

                                <button type="submit" class="bookbtn" >Confirm & Next</button>
                             </div>
                    <script>
                                // Get the checkbox and button elements
                                const termsCheckbox = document.getElementById("termsCheckbox");
                                const updateBtn = document.getElementById("updateBtn");

                                // Add an event listener to the checkbox
                                termsCheckbox.addEventListener("change", function () {
                                    // Enable the button if checkbox is checked, otherwise disable it
                                    updateBtn.disabled = !termsCheckbox.checked;
                                });
                        </script>

                </div>


            </form>

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
            <script src="assets/user/js/js/bootstrap.bundle.min.js"></script>
</body>
</html>