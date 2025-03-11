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
    <link rel="stylesheet" href="assets/admin/css/new.css">
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
                        <h1 class="display">Driver Details</h1>
                       <h3 style="color: red;padding-left:8rem;font-size:1.3rem;font-weight:bold;font-family:Georgia, 'Times New Roman', Times, serif;">
                              <% if (request.getAttribute("errorMessage") != null) { %>
                                  <%= request.getAttribute("errorMessage") %>
                              <% } %>
                       </h3>

                    </div>
                </div>
            <div class="top booking">
                <div class="container-fluid py-6">

                    <form action="DriverController?action=enter" method="post" enctype="multipart/form-data">
                    <div class="container">
                         <div class="row">

                             <div class=" col-md-6 form-group">
                                <label>Driver Image :</label>
                                <input type="file" name="image" class="form-control"required>
                              </div>

                             <div class=" col-md-6 form-group">
                                <label>Driver Name :</label>
                                <input type="text" name="name" class="form-control" id="name" placeholder="Driver Name" data-rule="minlen:4" data-msg="Please enter at least 4 chars" required>

                                      <div class="valid-feedback">Valid.</div>
                                      <div class="invalid-feedback">Please fill out this Name field.</div>
                             </div>

                            <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label>Mobile Number:</label>
                                <input type="tel" class="form-control" name="mobile" placeholder="Enter Mobile Number" maxlength="10" pattern="\d{10}" required oninput="this.value = this.value.replace(/[^0-9]/g, '').slice(0, 10);">
                                <div class="validate"></div>
                            </div>

                            <div class="col-md-6 form-group mt-3 mt-md-0">
                                <label>E-mail Address:</label>
                                <input type="email" class="form-control" name="email" placeholder="Enter E-mail Address"
                                       pattern="[a-zA-Z0-9._%+-]+@gmail\.com"
                                       title="Please enter a valid Gmail address (e.g., example@gmail.com)" required>
                                <div class="validate"></div>
                            </div>

                            <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label for="vehicle">Select Vehicle:</label>
                                <select id="vehicle" class="form-control" name="vehicle" required>
                                    <option value="">Select Vehicle</option>
                                    <%
                                        List<String> vehicleList = (List<String>) request.getAttribute("vehicleList");
                                        if (vehicleList != null) {
                                            for (String vehicle : vehicleList) {
                                    %>
                                            <option value="<%= vehicle %>"><%= vehicle %></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                                <div class="validate"></div>
                            </div>



                            <div class="col-md-4 form-group mt-3 mt-md-0">
                                <label>N.I.C. Number:</label>
                                <input type="text" class="form-control" name="nic" placeholder="Enter NIC Number"
                                       pattern="(\d{12}|\d{9}[Vv])"
                                       title="Enter a 12-digit number or a 9-digit number ending with 'V' or 'v'" required>
                                <div class="validate"></div>
                            </div>

                             <div class="col-md-4 form-group mt-3 mt-md-0">
                                <label>Password:</label>
                                <input type="text" id="password-field" class="form-control" name="password"  placeholder="Create a password " required minlength="8">

                                <div class="validate"></div>
                             </div>

                             <div class="col-md-4 form-group mt-3 mt-md-0">
                                <label>Confirm Password:</label>
                                <input type="text" class="form-control" name="conpassword"  placeholder="Re-enter Password " >
                                <div class="validate"></div>
                             </div>

                         </div>
                             <div class ="col-lg-6 col-md-6 form-group mt-3 mt-md-0">
                                <button type="submit" class="bookbtn">Add Now</button>
                             </div>



                        </form>
                    </div>
                </div>
             </div>
	<section class="ftco-section">
		<div class="container">
            <h1 class="display">List of Driver Details</h1>
			<div class="row">
				<div class="col-md-12">
					<div class="table-wrap">
						<table class="table table-responsive-xl">

						  <thead>
						    <tr>

						      <th>Driver Name</th>
						      <th>E-mail Address</th>
						      <th>Mobile Number</th>
						      <th>N.I.C. Number</th>
						      <th>Vehicle</th>
						      <th>Status</th>
						      <th>Action</th>
						    </tr>
						  </thead>
                            <tbody>
                              <%
                                  List<Driver> driverList = (List<Driver>) request.getAttribute("AdminController");
                                  if (driverList != null && !driverList.isEmpty()) {
                                      for (Driver driver : driverList) {
                              %>
                                    <tr class="alert" role="alert">
                                      <td>
                                        <%= driver.getName() %>
                                      </td>
                                        <td ><%= driver.getEmail() %></td>
                                        <td><%= driver.getPhone() %></td>
                                        <td> <%= driver.getNic() %></td>
                                        <td> <%= driver.getVehiclename() %></td>
                                        <td class="status">
                                            <span class="active">
                                                <% String st = driver.getStatus();%>
                                                <% if (st.equals("Active")) { %>
                                                    <span2 style="color:#fdb813"><%= driver.getStatus() %></span2>
                                                <% } else { %>
                                                    <span2 style="color:#ff0000"><%= driver.getStatus() %></span2>
                                                <% } %>
                                            </span>

                                        </td>
                                        <td>
                                          <a href="AdminController?action=dredit&dreditid=<%= driver.getId() %>" class="btn btn-info" name="edit" style="width:6rem;">Edit</a>
                                            <% String sta = driver.getStatus();%>
                                            <% if (sta.equals("Active")) { %>

                                            <a href="AdminController?action=drdelete&drdeleteid=<%= driver.getId() %>" class="btn btn-danger" style="width:6rem;">Deactive</a>
                                            <% }else if(sta.equals("Deactive")){%>
                                            <a href="AdminController?action=active&activeid=<%= driver.getId() %>" class="btn btn-success" style="width:6rem;">Active</a>
                                            <%}%>
                                        </td>

                                    </tr>
                                <%
                                        }
                                    } else {
                                %>
                                    <tr>
                                        <td colspan="6" style="text-align: center; font-weight: bold; font-size: 18px;">No Any Driver Details display.</td>
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