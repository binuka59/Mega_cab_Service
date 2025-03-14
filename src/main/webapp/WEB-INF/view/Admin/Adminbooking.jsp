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
                        <h1 class="display">Admin</h1>
                        <h1 class="message" id="MESSAGE" name="MESSAGE">
                              <% if (request.getAttribute("Message") != null) { %>
                                  <%= request.getAttribute("Message") %>
                              <% } %>
                        </h1>

                    </div>
                </div>
	<section class="ftco-section">
		<div class="container">
            <h1 class="display">All Booking</h1>
			<div class="row">
				<div class="col-md-12">
					<div class="table-wrap">
						<table class="table table-responsive-xl">
						  <thead>
						    <tr>

						    	<th>Customer</th>
						      <th>Vehicle</th>
						      <th>PickTime</th>
						      <th>PickDate</th>
						      <th>Journey</th>
						      <th>Status</th>
						      <th>Action</th>
						    </tr>
						  </thead>
                            <tbody>
                                <%
                                    List<AdminBook> BookingList = (List<AdminBook>) request.getAttribute("AdminController");

                                    if (BookingList != null && !BookingList.isEmpty()) {
                                        for (AdminBook booking : BookingList) {
                                %>
                                    <tr class="alert" role="alert">

                                        <td ><%= booking.getName() %></td>
                                        <td><%= booking.getVehicletype() %></td>
                                        <td><span class="active"><%= booking.getPicktime()%></span></td>
                                        <td><%= booking.getPickupdate()%></span></td>
                                        <td class="status"><span class="active"><%= booking.getPickaddress()%> - <%= booking.getDropaddress()%></span></td>
                                        <%String status = booking.getStatus();
                                            if(status.equals("Accept"))
                                            {
                                        %>
                                            <td style="font-weight:bold;"><%= booking.getStatus()%></span></td>
                                            <td>
                                                 <a href="AdminController?action=see&seeid=<%= booking.getId() %>" class="btn btn-info">See more</a>
                                                 <a href="AdminController?action=cancel&cancelid=<%= booking.getId() %>" class="btn btn-danger">Cancel</button>
                                            </td>
                                        <%}else{%>
                                             <td style="color:red;font-weight:bold;"><%= booking.getStatus()%></span></td>
                                                <td>
                                                    <a href="#" class="btn btn-info" style="filter: blur(1px); pointer-events: none;">See more</a>

                                                    <a href="#" class="btn btn-danger" style="filter: blur(1px);pointer-events: none;">Cancel</a>
                                                </td>

                                        <%}%>

                                    </tr>
                                <%
                                        }
                                    } else {
                                %>
                                    <tr>
                                        <td colspan="6" style="text-align: center; font-weight: bold; font-size: 18px;">No Any Booking display.</td>
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