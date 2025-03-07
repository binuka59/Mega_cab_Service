<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>


    <link rel="shortcut icon" href="images/logo.png">
    <meta name="description" content="" />
    <meta name="keywords" content="" />

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700,900" rel="stylesheet">
    <link rel="stylesheet" href="assets/login/fonts/icomoon/style.css">

    <link rel="stylesheet" href="assets/login/CSS/bootstrap.min.css">

    <link rel="stylesheet" href="assets/login/CSS/login.css">


    <link rel="stylesheet" href="assets/login/CSS/nav.css">

    <link rel="stylesheet" href="assets/login/CSS/form.css">

    <link rel="shortcut icon" href="assets/login/images/cab1.png">




    <title>Mega Cab Service</title>
</head>
<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
    <div class="Background-images">
        <%@ include file="header.jsp" %>

      <section class="vh-100">

          <div class="container py-5 h-100">

            <div class="row d-flex align-items-center justify-content-center h-100">
<h1>         New Password </h1>
              <div class="col-md-8 col-lg-7 col-xl-6">
                <img src="assets/images/second.png"class="img-fluid" alt="Background  image">
              </div>

              <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">

                <form  action="ForgetController?action=change" method="post">
                          <h3 style="color: red;padding-left:4rem;font-size:1.3rem;font-weight:bold;font-family:Georgia, 'Times New Roman', Times, serif;">
                              <% if (request.getAttribute("errorMessage") != null) { %>
                                  <%= request.getAttribute("errorMessage") %>
                              <% } %>
                          </h3>
                  <!-- Password input -->
                  <div class="form">
                  <label class="form-label" for="form1Example23">Password</label>
                  <input type="password" id="password-field" name="newpassword" class="form-control form-control-lg"  placeholder="Enter password Here" required minlength="8">
                   <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                  </div>


                  <!-- re-enter Password input -->
                  <div class="form">
                  <label class="form-label" for="form1Example23">Confirm Password</label>
                  <input type="password" id="password-field1" name="newrepassword" class="form-control form-control-lg" placeholder="Re-Enter password Here" required minlength="8"/>
                   <span toggle="#password-field1" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                  </div>
                  <script>

                  document.querySelector("form").addEventListener("submit", function (event) {
                      let password = document.querySelector("[name='password']").value.trim();
                      let repassword = document.querySelector("[name='repassword']").value.trim();

                      // Check password length
                      if (password.length < 8) {
                          alert("Password must be at least 8 characters long!");
                          event.preventDefault(); // Stop form submission
                          return;
                      }

                      // Check if passwords match
                      if (password !== repassword) {
                          alert("Passwords do not match. Please enter the same password!");
                          event.preventDefault(); // Stop form submission
                      }
                  });

                  </script>

                <div class="divider d-flex align-items-center my-4">
                  <p class="text-center fw-bold mx-3 mb-0 text-muted"></p>
                </div>


                  <div class=""> </div>
                  <!-- Submit button -->
                  <button type="submit" class="sigbtn">Create a Password</button>
                  <!-- ------------------------------------------------------link registation page----------------------------------- -->


                    <div class="account">
                      <label>Already Have an Account? </label>
                        <a href="Login?action=lognow">Login</a>
                  </div>
                  <!-- -------------------------------------------------------------------------------------------------- -->

                </form>
              </div>
              <div class="container">
                <div class="copyright">
                    &copy; Copyright <span id="year"></span> <strong><span>Mega Cab Service</span></strong>

                </div>
                <div class="credits">
                  Designed by<span> Binuka Lakshan </span>All Rights Reserved
                </div>
              </div>
            </div>
          </div>

        </section>
        <!-- ----------------------------------------------------------------------------------------------------------------------------- -->

    </div>
    <div id="preloader"></div>

    <!-- ========================================================js============================================================== -->
     <script>
         document.getElementById("year").textContent = new Date().getFullYear();
     </script>
      <script src="assets/login/js/bootstrap.min.js"></script>
      <script src="assets/login/js/script1.js"></script>
      <script src="assets/login/js/jquery.min.js"></script>
      <script src="assets/login/js/main.js"></script>
      <script src="assets/login/js/loard.js"></script>
      <!-- ============================================================================================================================ -->

  </div>

</body>
</html>