package com.megacab.controller;

import com.google.protobuf.Value;
import com.megacab.Dao.DbConnectionFactory;
import com.megacab.Dao.LoginDao;
import com.megacab.Dao.PaymentDao;
import com.megacab.Dao.VehicleDao;
import com.megacab.model.*;
import com.megacab.service.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig( // Enables file upload handling
		fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
		maxFileSize = 1024 * 1024 * 10,       // 10MB
		maxRequestSize = 1024 * 1024 * 50     // 50MB
)
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
		List<String> vehicleList = new ArrayList<>();

		try (Connection conn = DbConnectionFactory.getConnection();
			 PreparedStatement stmt = conn.prepareStatement("SELECT v_name FROM vehicle");
			 ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				vehicleList.add(rs.getString("v_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("vehicleList", vehicleList);

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
			response.sendRedirect(request.getContextPath() + "/AdminController?action=vehicle");
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
				response.sendRedirect(request.getContextPath() + "/AdminController?action=driver");
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
				response.sendRedirect(request.getContextPath() + "/AdminController?action=driver");
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


		}else if (action.equals("cancel"))
		{
			Integer  cid = Integer.valueOf(request.getParameter("cancelid"));
			Vehicle vehicle1 = new Vehicle(cid);
			vehicle1.setId(Integer.valueOf(cid));
			vehicle1.setStatus("Admin Cancel");
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
			response.sendRedirect(request.getContextPath() + "/AdminController?action=book");

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
		}

		else if (action.equals("updateadmin"))
		{
			String id =request.getParameter("id");
			String name= request.getParameter("name");
			String mobile = request.getParameter("mobile");
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
			response.sendRedirect(request.getContextPath() + "/AdminController?action=adminview");
     }


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
			return;
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
	private void adddata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Part filePart = request.getPart("image");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

		String uploadPath = getServletContext().getRealPath("") + File.separator + "assets/user/img";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) uploadDir.mkdir();

		String filePath = uploadPath + File.separator + fileName;
		filePart.write(filePath);



		String name = request.getParameter("name");
		String pri = request.getParameter("price");
		String additionprice = request.getParameter("addinprice");
		String estimate = request.getParameter("estimate");
		String vehicletype = request.getParameter("vehicle");

		double priceValue = Double.parseDouble(pri);
		double addinPriceValue = Double.parseDouble(additionprice);
		String price = String.format("%.2f", priceValue);
		String addinprice = String.format("%.2f", addinPriceValue);
		String status = "Allow";

		Vehicle vehicle = new Vehicle(fileName, name, price, addinprice, estimate, vehicletype, status);
		vehicle.setFilename(fileName);
		vehicle.setName(name);
		vehicle.setPrice(Double.parseDouble(price));
		vehicle.setAdditionalprice(addinprice);
		vehicle.setEstimate(estimate);
		vehicle.setStatus(status);
		vehicle.setType(vehicletype);
		VehicleDao.addData(vehicle);

		// ✅ Use Redirect - Make Sure No Prior Output is Sent
		response.sendRedirect(request.getContextPath() + "/AdminController?action=vehicle");
	}




}
