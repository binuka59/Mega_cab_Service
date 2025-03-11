package com.megacab.controller;

import com.megacab.Dao.BookingDao;
import com.megacab.Dao.DbConnectionFactory;
import com.megacab.Dao.LoginDao;
import com.megacab.Dao.PaymentDao;
import com.megacab.model.Booking;
import com.megacab.model.Login;
import com.megacab.model.Payment;
import com.megacab.model.Vehicle;
import com.megacab.service.BookingService;
import com.megacab.service.HeaderService;
import com.megacab.service.LoginService;
import com.megacab.service.VehicleService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;
	private  VehicleService vehicleService;
	private HeaderService headerService;
	private  BookingService bookingService;



	public void init() throws ServletException {
		loginService = LoginService.getInstance();
		vehicleService = VehicleService.getInstance();
		headerService = HeaderService.getInstance();
		bookingService = BookingService.getInstance();

	}


	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();

        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		 if (action.equals("show")) {
			 showregisterForm(request, response);
		 }
		 else if (action.equals("lognow"))
		 {
			 showloginForm(request, response);
		 }
		 else if (action.equals("booking"))
		 {
			 showbookForm(request, response);
		 }
		 else if (action.equals("home"))
		 {
			 showhomeForm(request, response);
		 }
		 else if (action.equals("driver"))
		 {
			 request.getRequestDispatcher("WEB-INF/view/User/driver.jsp").forward(request, response);
		 }
		 else if (action.equals("payment"))
		 {
			 PaymentDao paymentDao = new PaymentDao();

			 Integer userId = (Integer) request.getSession().getAttribute("userId");

			 List<Payment> payment = paymentDao.getmypayment(userId);
			 request.setAttribute("LoginController", payment);
			 request.getRequestDispatcher("WEB-INF/view/User/payment.jsp").forward(request, response);
		 }
		 else if (action.equals("contact"))
		 {
			 request.getRequestDispatcher("WEB-INF/view/User/contact.jsp").forward(request, response);
		 }
		 else if (action.equals("logout")) {
			 HttpSession session = request.getSession(false);
			 if (session != null) {
				 session.invalidate();
			 }
			 response.sendRedirect(request.getContextPath() + "/index.jsp");
		 } else if (action.equals("vehicle")) {
			 displayvehicle(request , response);

		 }else if (action.equals("DriverH"))
		 {
			 showdriverForm(request ,response);
		 }


		response.getWriter().write("Login Servlet Working!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action.equals("add")) {
			addData(request, response);
		} else if (action.equals("correct")) {

			String email = request.getParameter("email");
			String password = request.getParameter("password");

			try {
				String Query = "SELECT * FROM vehicle WHERE status='allow'";
				Connection connection1 = DbConnectionFactory.getConnection();
				PreparedStatement statement1 = connection1.prepareStatement(Query);

				ResultSet rs1 = statement1.executeQuery();

				List<String> vehicleList = new ArrayList<>();
				while (rs1.next()) {
					vehicleList.add(rs1.getString("v_name"));
				}

				String query = "SELECT * FROM login WHERE email=? AND password=?";
				Connection connection = DbConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, email);
				statement.setString(2, password);


				ResultSet rs = statement.executeQuery();

				if (rs.next()) {
					String status = rs.getString("status");
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String mobile = rs.getString("mobile");
					String nic = rs.getString("nic");




					HttpSession session = request.getSession();
					session.setAttribute("userId", id);
					session.setAttribute("name", name);
					session.setAttribute("email", email);
					session.setAttribute("mobile", mobile);
					session.setAttribute("nic", nic);
					session.setAttribute("vehicles", vehicleList);




					if (status.equals("User")) {

						showhomeForm(request ,response);

					}


					else if (status.equals("Admin"))
					{

						List<Booking> BookingList = new ArrayList<>();
						BookingList = BookingService.getAllBooking();
						request.setAttribute("LoginController", BookingList);


						request.getRequestDispatcher("WEB-INF/view/Admin/AdminHome.jsp").forward(request, response);
					}
					else if(status.equals("Driver"))
					{
						showdriverForm(request ,response);
					}
					else {
						request.setAttribute("errorMessage", "Invalid credentials");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}



				} else {

					request.setAttribute("errorMessage", "Login Failed! Please try again.");


					RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
					dispatcher.forward(request, response);
				}



			} catch (SQLException e) {
                throw new RuntimeException(e);
            }
		}
		else if (action.equals("accept")) {

			showhomeForm(request ,response);


        }


	}	private void displayvehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Vehicle> VehicleLists = new ArrayList<>();

		VehicleLists = VehicleService.getdisplayvehicle();
		request.setAttribute("AdminController", VehicleLists);
		request.getRequestDispatcher("WEB-INF/view/Admin/Adminvehicle.jsp").forward(request , response);
	}

	private void showhomeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			try {
				List<Vehicle> VehicleList = new ArrayList<>();
				VehicleList = VehicleService.getAllVehicle();
				request.setAttribute("LoginController", VehicleList);


			} catch (SQLException e) {
				request.setAttribute("errorMessage", "Database error: " + e.getMessage());
			}


        request.getRequestDispatcher("WEB-INF/view/User/UserHome.jsp").forward(request, response);
	}
	private void showadminForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	private void showdriverForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BookingDao bookingDao = new BookingDao();

		Integer userId = (Integer) request.getSession().getAttribute("userId");

		List<Booking> DriverList = BookingDao.getdriverbook(userId);
		request.setAttribute("LoginController", DriverList);


        request.getRequestDispatcher("WEB-INF/view/Driver/DriverHome.jsp").forward(request, response);
	}


	private void showregisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/view/Login/Register.jsp").forward(request, response);
	}

	private void showbookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BookingDao bookingDao = new BookingDao();

		Integer userId = (Integer) request.getSession().getAttribute("userId");

		List<Booking> bookings = bookingDao.getmybook(userId);
		request.setAttribute("LoginController", bookings);
		request.getRequestDispatcher("WEB-INF/view/User/booking.jsp").forward(request, response);
	}

	private void showloginForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}



	private void addData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String status = "User";

		// Query to check if the email already exists
		String checkEmailQuery = "SELECT COUNT(*) FROM login WHERE email = ?";
		String insertQuery = "INSERT INTO login (name, email, password, status) VALUES (?, ?, ?, ?)";

		try (Connection connection = DbConnectionFactory.getConnection()) {

			// Check if email already exists
			try (PreparedStatement checkStmt = connection.prepareStatement(checkEmailQuery)) {
				checkStmt.setString(1, email);
				try (ResultSet rs = checkStmt.executeQuery()) {
					if (rs.next() && rs.getInt(1) > 0) {
						// Email already exists, return error message
						request.setAttribute("errorMessage", "Email Already Exists. Try another email!");
						request.getRequestDispatcher("WEB-INF/view/Login/Register.jsp").forward(request, response);
						return;
					}
				}
			}

			// If email does not exist, insert the new user
			try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
				insertStmt.setString(1, name);
				insertStmt.setString(2, email);
				insertStmt.setString(3, password);
				insertStmt.setString(4, status);

				int rowsAffected = insertStmt.executeUpdate();
				if (rowsAffected > 0) {
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				} else {
					request.setAttribute("errorMessage", "Error creating account. Please try again.");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Database error occurred. Please try again.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}




}
