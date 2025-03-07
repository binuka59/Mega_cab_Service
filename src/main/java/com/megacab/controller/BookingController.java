package com.megacab.controller;

import com.megacab.Dao.BookingDao;
import com.megacab.Dao.DbConnectionFactory;
import com.megacab.Dao.LoginDao;
import com.megacab.Dao.VehicleDao;
import com.megacab.model.AdminBook;
import com.megacab.model.Booking;
import com.megacab.model.Login;
import com.megacab.model.Vehicle;
import com.megacab.service.BookingService;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookingController
 */

public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookingService bookingService;

	public void init() throws ServletException {
		bookingService = BookingService.getInstance();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		List<Booking> BookingList = new ArrayList<>();
		BookingList = BookingService.getAllBooking();
		request.setAttribute("BookingController", BookingList);

		LocalDate today = LocalDate.now();
		Date todayDate = Date.valueOf(today);
		System.out.println("date is"+todayDate);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equals("add")) {
			showhomeForm(request, response);
		} else if (action.equals("detail")) {

		}


		doGet(request , response);

		}

	private void showhomeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String userId = request.getParameter("id");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String mobile = request.getParameter("phone");
			String nic = request.getParameter("nic");
			String vehicle = request.getParameter("vehicle");
			String driver = request.getParameter("driver");
			String pickdate = request.getParameter("date");
			String picktime = request.getParameter("time");
			String pickaddress = request.getParameter("pickaddress");
			String dropaddress = request.getParameter("dropaddress");


			int userIdInt = 0;
			int mobileInt = 0;
			try {
				userIdInt = Integer.parseInt(userId);
				mobileInt = Integer.parseInt(mobile);
			} catch (NumberFormatException e) {
				request.setAttribute("Message", "Invalid User ID or Mobile Number!");
				request.getRequestDispatcher("WEB-INF/view/User/booking.jsp").forward(request, response);
				return;
			}


			Booking booking = new Booking(vehicle, driver,pickdate, picktime, pickaddress, dropaddress);
			booking.setId(userIdInt);


			Login login = new Login(userId, name, email, mobile, nic);
			login.setId(userIdInt);
			login.setMobile(String.valueOf(mobileInt));

			Vehicle vehicle1 = new Vehicle(vehicle);
			vehicle1.setType(vehicle);


			String vehicleid = "";
			try (Connection conn = DbConnectionFactory.getConnection()) {
				String vehicleQuery = "SELECT vehicleid FROM vehicle WHERE v_name = ?";
				PreparedStatement vehicleStmt = conn.prepareStatement(vehicleQuery);
				vehicleStmt.setString(1, vehicle);
				ResultSet vehicleRs = vehicleStmt.executeQuery();
				if (vehicleRs.next()) {
					vehicleid = vehicleRs.getString("vehicleid");

				} else {
					request.setAttribute("Message", "Invalid vehicle selection!");
					request.getRequestDispatcher("WEB-INF/view/User/booking.jsp").forward(request, response);
					return;
				}

			}


			boolean isBooked = false;
			try (Connection conn = DbConnectionFactory.getConnection()) {
				String query = "SELECT * FROM booking WHERE vid = ? AND pickdate = ?";
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, vehicleid);
				stmt.setString(2, pickdate);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					isBooked = true;
				}
			}

			if (isBooked) {
				request.setAttribute("errorMessage", "Sorry, this vehicle is already booked on the "+pickdate+" date.");
			} else {

				BookingDao.addData(booking);
				LoginDao.updateData(login);

				LocalDate today = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate selectedDate = LocalDate.parse(pickdate, formatter);
				if (selectedDate.equals(today)) {
					VehicleDao.updateData(vehicle1);

				}


				request.setAttribute("Message", "Booking successful!");
			}

			// Forward to booking page
			request.getRequestDispatcher("WEB-INF/view/User/booking.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException("Error processing booking", e);
		}
	}

}
