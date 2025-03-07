package com.megacab.controller;

import com.megacab.Dao.DbConnectionFactory;
import com.megacab.Dao.VehicleDao;
import com.megacab.model.Vehicle;
import com.megacab.service.ForgetService;
import com.megacab.service.LoginService;
import com.megacab.service.VehicleService;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class VehicleController
 */
@MultipartConfig( // Enables file upload handling
		fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
		maxFileSize = 1024 * 1024 * 10,       // 10MB
		maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class VehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehicleService vehicleService;

	public void init() throws ServletException {

		vehicleService = VehicleService.getInstance();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String vehiclename = request.getParameter("vehicleName");


		if(action.equals("showvehicle"))
		{
			List<Vehicle> VehicleList = new ArrayList<>();

            VehicleList = VehicleService.getAllVehicletype(vehiclename);
            request.setAttribute("VehicleController", VehicleList);

            request.getRequestDispatcher("WEB-INF/view/User/vehicle.jsp").forward( request ,response);
		} else if (action.equals("view")) {
			add( request ,response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("add")) {

			adddata(request , response);
		}
		else if (action.equals("update"))
		{
			updateData(request , response);
		}
		show(request , response);
		displayvehicle(request , response);
		doGet(request, response);
	}
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		try {

			String query = "SELECT * FROM vehicle";
			Connection connection = DbConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				String vname = rs.getString("v_name");



				HttpSession session = request.getSession();
				session.setAttribute("VName", vname);
				response.sendRedirect("WEB-INF/view/User/booking.jsp");

				return;

			}

		} catch (SQLException e) {
			e.printStackTrace(); // Fix: Use proper logging in real projects
		}
	}
	private void displayvehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Vehicle> VehicleList = new ArrayList<>();

		VehicleList = VehicleService.getdisplayvehicle();
		request.setAttribute("/VehicleController", VehicleList);
	}

		private void adddata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Part filePart = request.getPart("image");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();


		String uploadPath = getServletContext().getRealPath("") + File.separator + "assets/user/img";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) uploadDir.mkdir();


		String filePath = uploadPath + File.separator + fileName;
		filePart.write(filePath);

		response.getWriter().println("File uploaded successfully: " + fileName);

		String name = request.getParameter("name");
		String pri= request.getParameter("price");
		String additionprice= request.getParameter( "addinprice");
		String estimate= request.getParameter("estimate");
		String vehicletype = request.getParameter("vehicle");
		double priceValue = Double.parseDouble(pri);
		double addinPriceValue = Double.parseDouble(additionprice);
		String price = String.format("%.2f", priceValue);
		String addinprice = String.format("%.2f", addinPriceValue);
		String status = "Allow";

		Vehicle vehicle = new Vehicle(fileName, name, price,addinprice,estimate,vehicletype,status);
		vehicle.setFilename(fileName);
		vehicle.setName(name);
		vehicle.setPrice(Double.parseDouble(price));
		vehicle.setAdditionalprice(addinprice);
		vehicle.setEstimate(estimate);
		vehicle.setStatus(status);
		vehicle.setType(vehicletype);
		VehicleDao.addData(vehicle);


		request.setAttribute("message", "Vehicle added successfully!");
		request.getRequestDispatcher("WEB-INF/view/Admin/Adminvehicle.jsp").forward(request, response);


	}

	private void updateData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("image");
		String name = request.getParameter("name");

		try {
			String query = "SELECT * FROM vehicle WHERE v_name=?";

			try (Connection connection = DbConnectionFactory.getConnection();
				 PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setString(1, name);
				ResultSet rs = statement.executeQuery();

				if (rs.next()) {
					String status = rs.getString("status");
					int id = rs.getInt("vehicleid");
					String fileName = "";

					if (filePart == null || filePart.getSize() == 0) {
						String imageQuery = "SELECT v_image FROM vehicle WHERE v_name=?";

						try (PreparedStatement statement1 = connection.prepareStatement(imageQuery)) {
							statement1.setString(1, name);
							ResultSet rs1 = statement1.executeQuery();

							if (rs1.next()) {
								fileName = rs1.getString("v_image");
							}
						}
					} else {
						fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

						String uploadPath = getServletContext().getRealPath("") + File.separator + "assets/user/img";
						File uploadDir = new File(uploadPath);
						if (!uploadDir.exists()) uploadDir.mkdir();

						String filePath = uploadPath + File.separator + fileName;
						filePart.write(filePath);

						response.getWriter().println("File uploaded successfully: " + fileName);
					}

					String pri = request.getParameter("price");
					String additionprice = request.getParameter("addinprice");
					String estimate = request.getParameter("estimate");
					String vehicletype = request.getParameter("vehicle");
					double priceValue = Double.parseDouble(pri);
					double addinPriceValue = Double.parseDouble(additionprice);
					String price = String.format("%.2f", priceValue);
					String addinprice = String.format("%.2f", addinPriceValue);

					Vehicle vehicle = new Vehicle(id, fileName, name, price, addinprice, estimate, vehicletype, status);
					vehicle.setId(id);
					vehicle.setFilename(fileName);
					vehicle.setName(name);
					vehicle.setPrice(Double.parseDouble(price));
					vehicle.setAdditionalprice(addinprice);
					vehicle.setEstimate(estimate);
					vehicle.setType(vehicletype);
					vehicle.setStatus(status);

					VehicleDao.updateVehicleData(vehicle);

					request.setAttribute("message", "Vehicle Details updated successfully!");
					request.getRequestDispatcher("WEB-INF/view/Admin/Adminvehicle.jsp").forward(request, response);

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/view/User/addvehicle.jsp").forward(request,response);
	}


}
