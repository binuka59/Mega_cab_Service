<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ page import="java.util.List" %>
        <%@ page import="com.megacab.model.Driver" %>
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
                        <h1 class="display">Update Driver Details</h1>
                        <h1 class="message">
                              <% if (request.getAttribute("Message") != null) { %>
                                  <%= request.getAttribute("Message") %>
                              <% } %>
                        </h1>

                    </div>
                </div>
            <div class="top booking">
                <div class="container-fluid py-6">
                                <%
                                       List<Driver> DriverListSelect = (List<Driver>) request.getAttribute("AdminController");
                                       if (DriverListSelect != null && !DriverListSelect.isEmpty()) {
                                           for (Driver driver : DriverListSelect) {
                                  %>

                    <form action="DriverController?action=update&id=<%= driver.getId() %>" method="post" enctype="multipart/form-data">
                    <div class="container">
                         <div class="row">


                                <div class="col-lg-12">
                                    <div class="menu-item d-flex align-items-center">
                                        <img class="flex-shrink-0 img-fluid rounded-circle" src="<%= driver.getFilename() %>" alt="Current Image">
                                        <div class="w-100 d-flex flex-column text-start ps-4">
                                            <div class="d-flex justify-content-between border-bottom border-primary pb-2 mb-2">
                                             <div class=" col-md-6 form-group">
                                                <label>Update Image :</label>
                                                <input type="hidden" name="id"  value="<%= driver.getId() %>" class="form-control"></input>
                                                <input type="file" name="image"  class="form-control"></input>
                                              </div>

                                            </div>

                                        </div>
                                    </div>
                                </div>


                             <div class=" col-md-6 form-group">
                                <label>Vehicle Name :</label>
                                <input type="text" name="name" class="form-control" id="name" value="<%= driver.getName() %>" placeholder="vehicle Name" data-rule="minlen:4" data-msg="Please enter at least 4 chars">
                                  <div class="validate"></div>
                                      <div class="valid-feedback">Valid.</div>
                                      <div class="invalid-feedback">Please fill out this field.</div>
                                </div>

                             <div class="col-md-6 form-group mt-3 mt-md-0">
                                <label>E-mail Address:</label>
                                <input type="email" class="form-control" name="email"  value="<%= driver.getEmail() %>"  placeholder="Enter E-mail Address" >
                                <div class="validate"></div>
                             </div>

                             <div class="col-md-6 form-group mt-3 mt-md-0">
                                <label>Mobile Number:</label>
                                <input type="number" class="form-control" name="mobile" value="<%= driver.getPhone() %>" placeholder="Enter Mobile Number" >
                                <div class="validate"></div>
                             </div>

                             <div class="col-md-6 form-group mt-3 mt-md-0">
                                <label>N.I.C. Number:</label>
                                <input type="text" class="form-control" name="nic" value="<%= driver.getNic() %>"  placeholder="Enter NIC Number " >
                                <div class="validate"></div>
                             </div>
                         </div>
                             <div class = "col-lg-6 col-md-6 form-group mt-3 mt-md-0">
                                <button type="submit" class="bookbtn">Update Now</button>
                             </div>

                        </form>
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