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
                        <h1 class="display">Vehicle Details</h1>
                        <h1 class="message">
                              <% if (request.getAttribute("Message") != null) { %>
                                  <%= request.getAttribute("Message") %>
                              <% } %>
                        </h1>

                    </div>
                </div>
            <div class="top booking">
                <div class="container-fluid py-6">

                    <form action="VehicleController?action=add" method="post" enctype="multipart/form-data">
                    <div class="container">
                         <div class="row">

                             <div class=" col-md-3 form-group">
                                <label>Vehicle Image :</label>
                                <input type="file" name="image" class="form-control"required>
                              </div>

                             <div class=" col-md-6 form-group">
                                <label>Vehicle Name :</label>
                                <input type="text" name="name" class="form-control" id="name" placeholder="vehicle Name" data-rule="minlen:4" data-msg="Please enter at least 4 chars">
                                  <div class="validate"></div>
                                      <div class="valid-feedback">Valid.</div>
                                      <div class="invalid-feedback">Please fill out this field.</div>
                                </div>


                             <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label>Price of 1KM:</label>
                                <input type="text" class="form-control" name="price"  placeholder="Enter price of 1KM " >
                                <div class="validate"></div>
                             </div>

                             <div class="col-md-4 form-group mt-3 mt-md-0">
                                <label>Price of Additional 1KM:</label>
                                <input type="text" class="form-control" name="addinprice" placeholder="Enter Additional 1KM price" >
                                <div class="validate"></div>
                             </div>

                             <div class="col-md-4 form-group mt-3 mt-md-0">
                                <label>Estimated count of KM:</label>
                                <input type="Number" class="form-control" name="estimate"  placeholder="Enter Estimated of KM  count" >
                                <div class="validate"></div>
                             </div>

                               <div class="col-lg-4 col-md-6 form-group mt-3 mt-md-0">
                                <label>Select Vehicle Type :</label>
                                <select type="text" class="form-control" name="vehicle" id="vehicle" placeholder="" >
                                  <option value =" ">Vehicle Type</option>
                                  <option value ="car">Car</option>
                                  <option value ="van">Van </option>

                                    <div class="validate"></div>
                                </select>
                              </div>
                         </div>
                             <div class = "col-lg-6 col-md-6 form-group mt-3 mt-md-0">
                                <button type="submit" class="bookbtn">Add Now</button>
                             </div>



                        </form>
                    </div>
                </div>
             </div>
	<section class="ftco-section">
		<div class="container">
            <h1 class="display">List of vehicle</h1>
			<div class="row">
				<div class="col-md-12">
					<div class="table-wrap">
						<table class="table table-responsive-xl">

						  <thead>
						    <tr>

						      <th>Vehicle</th>
						      <th>Price</th>
						      <th>Additional fee(1KM)</th>
						      <th>Estimated </th>
						      <th>Status</th>
						      <th>Action</th>
						    </tr>
						  </thead>
                            <tbody>
                                 <%
                                       List<Vehicle> VehicleLists = (List<Vehicle>) request.getAttribute("AdminController");
                                       if (VehicleLists != null && !VehicleLists.isEmpty()) {
                                           for (Vehicle vehicle : VehicleLists) {
                                  %>
                                    <tr class="alert" role="alert">
                                      <td>
                                        <%= vehicle.getName() %>
                                      </td>
                                        <td >RS: <%= vehicle.getPrice() %>0</td>
                                        <td><%= vehicle.getEstimate() %>KM</td>
                                        <td>RS: <%= vehicle.getAdditionalprice()%>0</td>
                                        <td class="status">
                                        <span class="active">
                                            <% String st = vehicle.getStatus(); %>
                                            <% if (st.equals("Allow")) { %>
                                                <span2 style="color:#fdb813"><%= vehicle.getStatus() %></span2>
                                            <% } else { %>
                                                <span2 style="color:#ff0000"><%= vehicle.getStatus() %></span2>
                                            <% } %>
                                        </span>
                                        </td>

                                        <td>
                                            <a href="AdminController?action=edit&editid=<%= vehicle.getId() %>" class="btn btn-info" name="edit">Edit</a>
                                            <a href="AdminController?action=delete&deleteid=<%= vehicle.getId() %>" class="btn btn-danger">Delete</a>
                                        </td>
                                    </tr>
                                <%
                                        }
                                    } else {
                                %>
                                    <tr>
                                        <td colspan="6" style="text-align: center; font-weight: bold; font-size: 18px;">No Any Vehicle display.</td>
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