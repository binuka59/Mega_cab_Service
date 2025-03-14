<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ page import="java.util.List" %>
        <%@ page import="com.megacab.model.AdminBook" %>
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
    <link rel="stylesheet" href="assets/admin/css/dashboard.css">
    <link rel="stylesheet" href="assets/admin/css/menu.css">
    <link rel="stylesheet" href="assets/user/css/footer.css">

   <link href="assets/user/css/bootstrap.min.css" rel="stylesheet">
   <link href="assets/user/css/bootstrap-icons.css" rel="stylesheet">

   <link href="assets/user/swiper/swiper-bundle.min.css" rel="stylesheet">

</head>
<body>

        <%@ include file="adminheader.jsp"%>
                <div class="container-fluid bg-light  ">
                    <div class="container text-center animated bounceInDown">
                        <h1 class="display">Booking Details</h1>
                        <h1 class="message">
                              <% if (request.getAttribute("Message") != null) { %>
                                  <%= request.getAttribute("Message") %>
                              <% } %>
                        </h1>

                    </div>
                </div>
                <%
                List<AdminBook> BookSelect = (List<AdminBook>) request.getAttribute("AdminController");
                if (BookSelect != null && !BookSelect.isEmpty()) {
                    for (AdminBook adminbook : BookSelect) {
                %>

            <div class="top booking">
                <div class="container-fluid py-6">

                <form action="PaymentController?action=updatepayment&upid=<%= adminbook.getId() %>" method="post" >


                <div class="container">
                         <div class="row">

                             <div class=" col-md-3 form-group mt-3 mt-md-0">

                                <label>Book number :</label>
                                <input type="text" class="form-control" name="id"  value="MCS00<%= adminbook.getId() %>" >
                                <label ></label>
                             </div>
                             <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label>Customer Name:</label>
                                <input type="name" class="form-control" name="name"  value="<%= adminbook.getName() %>" >

                             </div>

                             <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label>Mobile Number:</label>
                                <input type="number" class="form-control" name="mobile" value="<%= adminbook.getMobile() %>">
                                <div class="validate"></div>
                             </div>

                             <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label>N.I.C. Number:</label>
                                <input type="text" class="form-control" name="nic" value="<%= adminbook.getNic() %>">
                                <div class="validate"></div>
                             </div>

                             <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label>E-mail Address:</label>
                                <input type="text" class="form-control" value="<%= adminbook.getEmail() %>">
                                <div class="validate"></div>
                             </div>

                             <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label>Vehicle:</label>
                                <input type="text" class="form-control"  value="<%= adminbook.getVehicletype() %>" >
                                <div class="validate"></div>
                             </div>

                             <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label>Driver Status:</label>
                                <input type="text" class="form-control"  value="<%= adminbook.getDriver() %>" Driver >
                                <div class="validate"></div>
                             </div>

                             <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label>Allowcated number of  km :</label>
                                <input type="text" class="form-control" id="estimate" name="estimate" value="<%= adminbook.getEstimate() %>" >
                                <div class="validate"></div>
                             </div>

                             <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label>Date:</label>
                                <input type="text" class="form-control"  value="<%= adminbook.getPickupdate() %>" >
                                <div class="validate"></div>
                             </div>


                             <div class="col-md-6 form-group mt-3 mt-md-0">
                                <label>Journey:</label>
                                <input type="text" class="form-control"  value="<%= adminbook.getPickaddress() %> - <%= adminbook.getDropaddress() %>" >
                                <div class="validate"></div>
                             </div>

                             <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label>Time:</label>
                                <input type="text" class="form-control"  value="<%= adminbook.getPicktime() %>" >
                                <div class="validate"></div>
                             </div>

                         <% String drive = adminbook.getDriver();
                         if(drive.equals("without")){%>


                             <div class="col-md-7 form-group mt-3 mt-md-0">

                             </div>

                             <div class="col-md-2 form-group mt-3 mt-md-0">
                                <label>Fee of 1KM</label>

                             </div>

                            <div class="col-md-3 form-group mt-3 mt-md-0">
                                <input type="text" class="form-control" name="price" id="price" value="RS: <%= adminbook.getPrice() %>0" readonly>
                                <div class="validate"></div>
                            </div>

                             <div class="col-md-5 form-group mt-3 mt-md-0">
                             </div>

                             <div class="col-md-4 form-group mt-3 mt-md-0">
                                <label>Additional <%= adminbook.getEstimate() %> per 1km charge:</label>

                             </div>
                            <div class="col-md-3 form-group mt-3 mt-md-0">
                                <input type="text" class="form-control" name="addition" id="addition" value="RS: <%= adminbook.getAdditional() %>0" readonly>
                                <div class="validate"></div>
                            </div>

                            <div class="col-md-7 form-group mt-3 mt-md-0">
                             </div>
                             <div class="col-md-2 form-group mt-3 mt-md-0">
                                <label>Initial fee</label>
                             </div>
                            <div class="col-md-3 form-group mt-3 mt-md-0">
                                <input type="text" class="form-control" name="initial" id="initial" value="RS: <%= adminbook.getInitialfee()%>0" readonly>
                                <div class="validate"></div>
                            </div>

                            <div class="col-md-7 form-group mt-3 mt-md-0">
                             </div>
                             <div class="col-md-2 form-group mt-3 mt-md-0">
                                <label> Booking fee</label>
                             </div>
                            <div class="col-md-3 form-group mt-3 mt-md-0">
                                <input type="text" class="form-control" name="fee" id="fee" value="RS: <%= adminbook.getFee() %>0" readonly>
                                <div class="validate"></div>
                            </div>




                             <div class="col-md-7 form-group mt-3 mt-md-0">

                             </div>

                             <div class="col-md-2 form-group mt-3 mt-md-0">
                                <label>Total Of KM:</label>

                             </div>

                            <div class="col-md-3 form-group mt-3 mt-md-0">
                                <input type="text" class="form-control" name="totkm" id="totkm" oninput="calculateAmount()">
                                <div class="validate"></div>
                            </div>

                             <div class="col-md-7 form-group mt-3 mt-md-0">

                             </div>

                             <div class="col-md-2 form-group mt-3 mt-md-0">
                                <label>Amount:</label>

                             </div>

                            <div class="col-md-3 form-group mt-3 mt-md-0">
                                <input type="text" class="form-control" name="amount" id="amount" readonly>
                                <div class="validate"></div>
                            </div>

                             <div class = "col-lg-6 col-md-6 form-group mt-3 mt-md-0">
                                <button type="submit" class="bookbtn">Update Now</button>
                             </div>
                         <%}
                          %>
                         </div>


                        </form>
                        <script>
                            function calculateAmount() {
                                // Get user input values
                                let totkm = parseFloat(document.getElementById("totkm").value) || 0;
                                let additions = document.getElementById("addition").value;
                                let addition = parseFloat(additions.replace("RS: ", "")) || 0;
                                let estimate = parseFloat(document.getElementById("estimate").value) || 0;

                                // Get price from the input field (assuming the price format is "RS: 100")
                                let priceText = document.getElementById("price").value;
                                let price = parseFloat(priceText.replace("RS: ", "")) || 0;

                                let initials =  document.getElementById("initial").value;
                                let initial = parseFloat(initials.replace("RS: ", "")) || 0;

                                let fees =  document.getElementById("fee").value;
                                let fee = parseFloat(fees.replace("RS: ", "")) || 0;

                                let esprice = 0;
                                let amount = 0;
                                let total = 0;

                                if (totkm >=estimate) {
                                    esprice = (totkm - estimate ) * addition;
                                    amount = ((estimate  * price) + esprice);
                                } else {

                                    amount = (totkm * price);
                                }
                                if (totkm === 0 || isNaN(totkm)) {
                                    total = 0.0;
                                } else {
                                    total = amount + initial + fee;
                                }
                                document.getElementById("amount").value = "RS: " + total.toFixed(2);
                            }
                        </script>
                               <%
                                        }
                                    } else {
                                %>

                                <%
                                    }
                                %>
                    </div>
                </div>
             </div>


    <section  class="banner section dark-background">

      <div class="container" data-aos="fade-up" data-aos-delay="100">

        <div class="swiper init-swiper">
          <div class="swiper-wrapper">

            <div class="swiper-slide">
              <div class="testimonial-item">

                <h3> Google Map</h3>
                  <iframe
                    width="100%"
                    height="300"
                    style="border:0; border-radius: 10px; margin-top: 10px;"
                    loading="lazy"
                    allowfullscreen
                    referrerpolicy="no-referrer-when-downgrade"
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1018.3819746773321!2d80.63310070117343!3d7.290514529817207!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3ae366266498acd3%3A0x411a3818a1e03c35!2sKandy!5e0!3m2!1sen!2slk!4v1740501085995!5m2!1sen!2slk" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade">
                  </iframe>

              </div>
            </div>

          </div>
          <div class="swiper-pagination"></div>
        </div>

      </div>

    </section><!-- /Testimonials Section -->

            <%@ include file="adminfooter.jsp" %>
                 <script>
                     document.getElementById("year").textContent = new Date().getFullYear();
                 </script>

            <script src="assets/user/js/main.js"></script>
            <script src="assets/user/js/about.js"></script>
            <script src="assets/user/swiper/swiper-bundle.min.js"></script>
            <script src="assets/user/js/js/bootstrap.bundle.min.js"></script>
</body>
</html>