package com.megacab.controller;

import com.google.protobuf.Value;
import com.megacab.Dao.DbConnectionFactory;
import com.megacab.Dao.LoginDao;
import com.megacab.model.*;
import com.megacab.service.BookingService;
import com.megacab.service.DriverService;
import com.megacab.service.LoginService;
import com.megacab.service.VehicleService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminController
 */

public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehicleService vehicleService;
	private  DriverService driverService;
	private  BookingService bookingService;
	private LoginService loginService;

	public void init() throws ServletException {

		vehicleService = VehicleService.getInstance();
		driverService = DriverService.getInstance();
		bookingService = BookingService.getInstance();
		loginService = LoginService.getInstance();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("vehicle"))
		{
			displayvehicle(request , response);
		}
		else if (action.equals("driver"))
		{
			List<Driver> DriverList = new ArrayList<>();

			try {
				DriverList = DriverService.getAllDrivers();
				request.setAttribute("AdminController", DriverList);
			} catch (SQLException e) {
				request.setAttribute("errorMessage", e.getMessage());
			}
			request.getRequestDispatcher("WEB-INF/view/Admin/Admindriver.jsp").forward(request , response);
		}
		else if (action.equals("book"))
		{
			List<AdminBook> BookingList = new ArrayList<>();
			BookingList = BookingService.getAllBookingdetails();
			request.setAttribute("AdminController", BookingList);
			request.getRequestDispatcher("WEB-INF/view/Admin/Adminbooking.jsp").forward(request , response);
		}
		else if (action.equals("pay"))
		{
			request.getRequestDispatcher("WEB-INF/view/Admin/Adminpayment.jsp").forward(request , response);
		}
		else if (action.equals("adminview"))
		{
			List<Login> AdminList = new ArrayList<>();
			AdminList = LoginService.getAdminDetails();
			request.setAttribute("AdminController",AdminList);
			request.getRequestDispatcher("WEB-INF/view/Admin/Admindetail.jsp").forward(request , response);
		}
		else if (action.equals("editadmin"))
		{
			String id = request.getParameter("adminid");
			List<Login> AdminList = new ArrayList<>();
			AdminList = LoginService.getupdateadmin(id);
			request.setAttribute("AdminController",AdminList);
			request.getRequestDispatcher("WEB-INF/view/Admin/UpdateAdmin.jsp").forward(request , response);
		}


		else if (action.equals("admin"))
		{
			List<Booking> BookingList = new ArrayList<>();
			BookingList = BookingService.getAllBooking();
			request.setAttribute("LoginController", BookingList);


			request.getRequestDispatcher("WEB-INF/view/Admin/AdminHome.jsp").forward(request, response);

		}
		if (action.equals("edit"))
		{
			String editid =request.getParameter("editid");

			List<Vehicle> VehicledetailList = new ArrayList<>();
			VehicledetailList  = VehicleService.getvehicleDetail(editid);
			request.setAttribute("AdminController", VehicledetailList );

			request.getRequestDispatcher("WEB-INF/view/Admin/updatevehicle.jsp").forward(request , response);
		} else if (action.equals("delete"))
		{
			String deleteid =request.getParameter("deleteid");
			String query = "DELETE FROM vehicle WHERE vehicleid = ?";

			try (Connection connection = DbConnectionFactory.getConnection();
				 PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setString(1, deleteid);
				int rowsAffected = statement.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("Vehicle with ID " + deleteid + " deleted successfully.");
				} else {
					System.out.println("No vehicle found with ID " + deleteid);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("WEB-INF/view/Admin/Adminvehicle.jsp").forward(request , response);
		} else if (action.equals("dredit"))
		{
			String deditid =request.getParameter("dreditid");
			List<Driver> DriverListSelect = new ArrayList<>();

            DriverListSelect = DriverService.getAllDriversDetails(deditid);
            request.setAttribute("AdminController", DriverListSelect);
            request.getRequestDispatcher("WEB-INF/view/Admin/Updatedriver.jsp").forward(request , response);


		} else if (action.equals("drdelete")) {
			String ddeleteid = request.getParameter("drdeleteid");
			String query = "UPDATE driver SET status = ? WHERE id = ?";

			try (Connection connection = DbConnectionFactory.getConnection();
				 PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setString(1, "Deactive");
				statement.setString(2, ddeleteid);
				int rowsAffected = statement.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("Vehicle with ID " + ddeleteid + " deleted successfully.");
				} else {
					System.out.println("No vehicle found with ID " + ddeleteid);
				}
				displaydriver(request , response);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		else if (action.equals("active"))
		{
			String activeid =request.getParameter("activeid");
			String query = "UPDATE driver SET status = ? WHERE id = ?";

			try (Connection connection = DbConnectionFactory.getConnection();
				 PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setString(1, "Active");
				statement.setString(2, activeid);
				int rowsAffected = statement.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("Vehicle with ID " + activeid + " active successfully.");
				} else {
					System.out.println("No vehicle found with ID " + activeid);
				}
				displaydriver(request , response);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}else if (action.equals("see"))
		{
			String seeid =request.getParameter("seeid");

			List<AdminBook> bookList = new ArrayList<>();

			bookList= BookingService.getbookdetails(seeid);
			request.setAttribute("AdminController", bookList);
			request.getRequestDispatcher("WEB-INF/view/Admin/Showbooking.jsp").forward(request , response);


		}

//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		if(action.equals("addadmin"))
		{
			addadmin(request,response);
		} else if (action.equals("updateadmin"))
		{
			String id =request.getParameter("id");
			String name= request.getParameter("name");
			String mobile = request.getParameter("phone");
			String email =request.getParameter("email");
			String nic = request.getParameter("nic");
			System.out.println("name is4"+name);
			Login login = new Login(id,name,email,mobile,nic);
			login.setId(Integer.parseInt(id));
			login.setName(name);
			login.setMobile(mobile);
			login.setEmail(email);
			login.setNic(nic);
			LoginDao.updateAdmin(login);
		}




		request.getRequestDispatcher("WEB-INF/view/Admin/Admindetail.jsp").forward(request , response);

		doGet(request, response);
	}

	private void displayvehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Vehicle> VehicleLists = new ArrayList<>();

		VehicleLists = VehicleService.getdisplayvehicle();
		request.setAttribute("AdminController", VehicleLists);
		request.getRequestDispatcher("WEB-INF/view/Admin/Adminvehicle.jsp").forward(request , response);
	}
	private void displaydriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/view/Admin/Admindriver.jsp").forward(request , response);
	}
	private void addadmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Aname= request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String Aemail =request.getParameter("email");
		String Anic = request.getParameter("nic");
		String Apassword = request.getParameter("adpassword");
		String Aconpassword = request.getParameter("conpassword");
		String status="Admin";
//System.out.println("pasword is "+Apassword);
		if (Apassword == null || Aconpassword == null || !Apassword.equals(Aconpassword)) {
			request.setAttribute("errorMessage", "Passwords do not match! Please enter the same password.");
			request.getRequestDispatcher("WEB-INF/view/Admin/Admindriver.jsp").forward(request, response);
			return; // Stop further execution
		}

			Login login = new Login(Aname,mobile,Aemail,Anic,Apassword,status);
			login.setName(Aname);
			login.setMobile(String.valueOf(mobile));
			login.setEmail(Aemail);
			login.setNic(Anic);
			login.setPassword(Apassword);
			login.setStatus(status);

			LoginDao.addaccount(login);



		request.getRequestDispatcher("WEB-INF/view/Admin/Admindriver.jsp").forward(request , response);
	}



}
