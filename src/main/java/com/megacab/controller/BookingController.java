package com.megacab.controller;

import com.megacab.Dao.*;
import com.megacab.model.*;
import com.megacab.service.BookingService;
import com.megacab.service.EmailService;
import com.megacab.service.VehicleService;

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
		String action = request.getParameter("action");
		String vehicleId = request.getParameter("id");
		if (action.equals("add")) {

//			System.out.println("inside"+vehicleId);
			showhomeForm(request, response);
		}
		else if (action.equals("cancel"))
		{

			cancelbook(request , response);

		}

//		response.getWriter().append("Served at: ").append(request.getContextPath());

//		List<Booking> BookingList = new ArrayList<>();
//		BookingList = BookingService.getAllBooking();
//		request.setAttribute("BookingController", BookingList);
//
//		LocalDate today = LocalDate.now();
//		Date todayDate = Date.valueOf(today);
////		System.out.println("date is"+todayDate);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String vehicleId = request.getParameter("id");
		if (action.equals("add")) {

			showhomeForm(request, response);
		}


	}

	private void showhomeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {Connection conn = DbConnectionFactory.getConnection();
			String vehicleId = request.getParameter("id");
			System.out.println("outside"+vehicleId);

				String nameQuery = "SELECT * FROM vehicle WHERE vehicleid = ?";
			try (PreparedStatement nameStmt = conn.prepareStatement(nameQuery)){
				nameStmt.setString(1, vehicleId);
				ResultSet nameRs = nameStmt.executeQuery();
				if (nameRs.next()) {
					String vehiclename = nameRs.getString("v_name");
					request.getSession().setAttribute("vehiclename", vehiclename);


				}

			}
			String userId = request.getParameter("id");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String mobile = request.getParameter("phone");
			String nic = request.getParameter("nic");
			String vehicle = request.getParameter("vehicle");
			String driver = request.getParameter("driver");
			String pickdate = request.getParameter("date");
			String picktime = request.getParameter("formattedTime");
			String pickaddress = request.getParameter("pickaddress");
			String dropaddress = request.getParameter("dropaddress");


			Booking booking = new Booking(vehicle, driver,pickdate, picktime, pickaddress, dropaddress);

			booking.setId(Integer.parseInt(userId));


			Login login = new Login(userId, name, email, mobile, nic);
//			System.out.println("before s"+ login);
			login.setId(Integer.parseInt(userId));
			login.setMobile(mobile);

			Vehicle vehicle1 = new Vehicle(vehicle);

			vehicle1.setType(vehicle);
			LoginDao.updateData(login);

			String vehicleid = "";
			String vehicleprice= "";
			String vehicleaddition ="";
			String vehicleestimate ="";
			Integer detailid =0;

			String vehicleQuery = "SELECT * FROM vehicle WHERE v_name = ?";
			try(PreparedStatement vehicleStmt = conn.prepareStatement(vehicleQuery)){
				vehicleStmt.setString(1, vehicle);
				ResultSet vehicleRs = vehicleStmt.executeQuery();
				if (vehicleRs.next()) {
					vehicleid = vehicleRs.getString("vehicleid");
					vehicleprice = vehicleRs.getString("v_price");
					vehicleaddition = vehicleRs.getString("v_addtionalprice");
					vehicleestimate = vehicleRs.getString("v_estimation");
					detailid = vehicleRs.getInt("details_id");



				} else {

					request.getRequestDispatcher("WEB-INF/view/User/bookingdetails.jsp").forward(request, response);
					return;
				}

			}

			String lastid="";
			String lastquery = "SELECT * FROM booking WHERE uid=? ORDER BY bid DESC LIMIT 1";
			try (PreparedStatement stlast = conn.prepareStatement(lastquery)){
				stlast.setString(1, userId);
				ResultSet resultSet = stlast.executeQuery();
				if (resultSet.next()) {
					lastid = resultSet.getString("bid");

				}

			}

			Double balance=0.0;
			Integer did=0;
			String detailQuery = "SELECT * FROM vehicledetail WHERE id = ?";
			try(PreparedStatement detailStmt = conn.prepareStatement(detailQuery)){
				detailStmt.setInt(1,detailid);
				ResultSet detailRs = detailStmt.executeQuery();
				if (detailRs.next()) {
					Double initial = detailRs.getDouble("initial");
					Double fee = detailRs.getDouble("fee");
					Double driverfee = detailRs.getDouble("driver");
					if (driver.equals("with"))
					{
						String payQuery = "SELECT l.Name AS name, l.email AS email " +
								"FROM login l " +
								"JOIN driver d ON l.id = d.Did " +
								"WHERE d.vehicle_id = ?";
						System.out.println("vehiclename"+vehicleid);
						try(PreparedStatement payStmt = conn.prepareStatement(payQuery)){
							payStmt.setInt(1, Integer.parseInt(vehicleid));
							ResultSet payRs = payStmt.executeQuery();
							if (payRs.next()) {
//								did = payRs.getInt("id");
								String dname= payRs.getString("name");
								String demail = payRs.getString("email");
								System.out.println("driv mail"+demail);

								String messageBody = "Book Details. \n\n";
								messageBody += "MR. "+dname+"\n\n";
								messageBody += "Customer Booking Number:- MCS00"+lastid + " \n\n";
								messageBody += "Pickup Date :-"+pickdate+ " \n\n";
								messageBody += "Pickup Time :-"+picktime+ " \n\n";
								messageBody += "Journey :-"+pickaddress+ " - "+dropaddress+"  \n\n";
								messageBody += "...Mega Cab Service...\n";
								String subject = "Mega_CAB_Service Booking Details";

								EmailService EmailService = new EmailService();
								EmailService.sendEmail(demail,  messageBody, subject);

							}

						}

						balance = initial + fee + driverfee;
					}
					else
					{
						balance = initial + fee ;
					}

				}

			}
			Payment payment =new Payment(balance,userId);


			boolean isBooked = false;

				String query = "SELECT * FROM booking WHERE vid = ? AND pickdate = ?";
			try(PreparedStatement stmt = conn.prepareStatement(query)){
				stmt.setString(1, vehicleid);
				stmt.setString(2, pickdate);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					isBooked = true;
				}
			}

			if (isBooked) {
				request.setAttribute("errorMessage", "Sorry, this vehicle is already booked on the " + pickdate + " date.");
				request.getRequestDispatcher("WEB-INF/view/User/bookingdetails.jsp").forward(request, response);
			}
			else {

				request.getSession().setAttribute("vehicleprice", vehicleprice);
				request.getSession().setAttribute("vehicleaddition", vehicleaddition);
				request.getSession().setAttribute("vehicleestimate", vehicleestimate);
//				request.setAttribute("login", login);
//				request.setAttribute("booking", booking);
//				request.setAttribute("vehicle1", vehicle1);
//				display(request ,response);


				BookingDao.addData(booking);


				LocalDate today = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate selectedDate = LocalDate.parse(pickdate, formatter);

				if (selectedDate.equals(today)) {
					VehicleDao.updateData(vehicle1);

				}
				PaymentDao.addpayment(payment);



				String messageBody = "Your Booking Successfully. \n\n";
				messageBody += "Your Booking Number:- MCS00"+lastid + " \n\n";
				messageBody += "Pickup Date :-"+pickdate+ " \n\n";
				messageBody += "Pickup Time :-"+picktime+ " \n\n";
				messageBody += "Journey :-"+pickaddress+ " - "+dropaddress+"  \n\n";
				messageBody += "Thank You Selected Our CAB service \n";
				messageBody += " for your Journey \n";
				messageBody += "...Mega Cab Service...\n";
				String subject = "Mega_CAB_Service Booking Details";

				EmailService EmailService = new EmailService();
				EmailService.sendEmail(email,  messageBody, subject);

				request.getRequestDispatcher("WEB-INF/view/User/Showbooking.jsp").forward(request, response);
			}




		} catch (Exception e) {
			throw new ServletException("Error processing booking", e);
		}
	}


		private void success(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.getRequestDispatcher("/Login?action=booking").forward(request, response);
		}

	private void cancelbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer  cid = Integer.valueOf(request.getParameter("cancelid"));

		Vehicle vehicle1 = new Vehicle(cid);
		vehicle1.setId(Integer.valueOf(cid));
		VehicleDao.cancelbook(vehicle1);

		Payment payment = new Payment(cid);
		payment.setId(cid);
		PaymentDao.deletebook(payment);
		String email="";
		String cancelquery = "SELECT L.email " +
				"FROM booking B " +
				"JOIN login L ON B.uid = L.id " +
				"WHERE B.bid = ?";
		try ( Connection connection = DbConnectionFactory.getConnection();
			  PreparedStatement stlast = connection.prepareStatement(cancelquery)) {
			stlast.setInt(1, cid);
			vehicle1.setStatus("Cutomer Cancel");
			ResultSet resultSet = stlast.executeQuery();
			if (resultSet.next()) {
				email = resultSet.getString("email");

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String messageBody = "Your Booking Cancel. \n\n";
		messageBody += "Booking Number:- MCS00"+cid+"\n";
		messageBody += "...Mega Cab Service...\n";
		String subject = "Mega_CAB_Service Booking Details";

		EmailService EmailService = new EmailService();
		EmailService.sendEmail(email,  messageBody, subject);
		response.sendRedirect(request.getContextPath() + "/Login?action=booking");

	}


	}
