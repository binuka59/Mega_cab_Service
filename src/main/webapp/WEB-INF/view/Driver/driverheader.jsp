        <%@ page import="java.util.List" %>
        <%@ page import="com.megacab.model.Header" %>
    <header id="headers" class="headers fixed-top d-flex align-items-center">


        <nav class="header-nav ms-auto">
          <ul class="d-flex align-items-center">

            <li class="nav-item dropdown">

              <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
                <i class="bi bi-bell"></i>
                <span class="badge bg-primary badge-number">4</span>
              </a><!-- End Notification Icon -->

              <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow notifications">
                <li class="dropdown-header">
                  You have 4 new notifications
                  <a href="#"><span class="badge rounded-pill bg-primary p-2 ms-2">View all</span></a>
                </li>
                <li>
                  <hr class="dropdown-divider">
                </li>


                <li class="notification-item">
                  <i class="bi bi-x-circle text-danger"></i>
                  <div>
                    <h4>Atque rerum nesciunt</h4>
                    <p>Quae dolorem earum veritatis oditseno</p>
                    <p>1 hr. ago</p>
                  </div>
                </li>

                <li>
                  <hr class="dropdown-divider">
                </li>

                <li class="notification-item">
                  <i class="bi bi-check-circle text-success"></i>
                  <div>
                    <h4>Sit rerum fuga</h4>
                    <p>Quae dolorem earum veritatis oditseno</p>
                    <p>2 hrs. ago</p>
                  </div>
                </li>

                <li>
                  <hr class="dropdown-divider">
                </li>

                <li class="dropdown-footer">
                  <a href="#">Show all notifications</a>
                </li>

              </ul><!-- End Notification Dropdown Items -->

            </li>

                <%
                    String userName = (String) session.getAttribute("name");
                    Integer userId = (Integer) session.getAttribute("userId");

                %>




            <li class="nav-item dropdown pe-3">
                  <%

                        if (userName == null) {
                            response.sendRedirect("Login?action=logout");
                            return;
                        }
                    %>

              <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                <%= userName %>
              </a>

              <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                <li class="dropdown-header">
                  <h6><%= userName %><%= userId %></h6>
                </li>

                <li>
                  <a class="dropdown-item d-flex align-items-center" href="Login?action=logout">
                    <i class="bi bi-box-arrow-right"></i>
                    <span>Log_Out</span>
                  </a>
                </li>

              </ul>


            </li>

          </ul>
        </nav>

      </header>

          <header id="header" class="d-flex align-items-center">
             <div class="container d-flex align-items-center justify-content-between">

               <a class="navbar-brand" href="index.html">
                 <img src="assets/user/img/cab1.png" class="logo img-fluid" alt="logo">
                 <span>
                     <span1>m</span1>ega <span1> C</span1>ab <span1>S</span1>ervice
                     <small>S E N K A D A G A L A</small>
                 </span>
              </a>

               <nav id="navbar" class="navbar">
                 <ul>
                   <li><a class="nav-link scrollto" href="Login?action=DriverH">Home</a></li>
                   <li><a class="nav-link scrollto" href="DriverController?action=DVehicle">Vehicle</a></li>
                   <li><a class="nav-link scrollto" href="#">Payment</a></li>



                 </ul>
                 <i class="bi bi-list mobile-nav-toggle"></i>
               </nav>

             </div>
          </header>