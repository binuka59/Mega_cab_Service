package com.megacab.controller;

import com.megacab.Dao.*;
import com.megacab.model.*;
import com.megacab.model.Driver;
import com.megacab.service.DriverService;
import com.megacab.service.LoginService;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class DriverController
 */
@MultipartConfig( // Enables file upload handling
		fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
		maxFileSize = 1024 * 1024 * 10,       // 10MB
		maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class DriverController<JSONArray, JSONObject> extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DriverService driverService;

	public void init() throws ServletException {
		driverService = DriverService.getInstance();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null || action.equals("driver")) {
			showdriverForm(request, response);
		}
		else if (action.equals("DVehicle")) {
			Showdrivervehicle(request , response);
		}
		else if (action.equals("DPayment")) {
			request.getRequestDispatcher("WEB-INF/view/Driver/DPayment.jsp").forward(request, response);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("enter"))
		{
			adddriver(request , response);
		}

		else if (action.equals("update"))
		{
			String id = request.getParameter("id");

			System.out.println("id is"+id);
			String fileName = "";


			Part filePart = request.getPart("image");
			System.out.println("filePart is"+filePart);
			if (filePart == null || filePart.getSize() == 0) {
				String imageQuery = "SELECT Dimage FROM driver WHERE id=?";

				try (Connection connection = DbConnectionFactory.getConnection();
					 PreparedStatement statement1 = connection.prepareStatement(imageQuery)) {
					statement1.setString(1, id);
					ResultSet rs1 = statement1.executeQuery();

					if (rs1.next()) {
						fileName = rs1.getString("Dimage");

					}
				} catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
				fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

				String uploadPath = getServletContext().getRealPath("") + File.separator + "assets/user/img/driver/";
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) uploadDir.mkdir();

				String filePath = uploadPath + File.separator + fileName;
				filePart.write(filePath);

//				response.getWriter().println("File uploaded successfully: " + fileName);
			}
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			String nic = request.getParameter("nic");
			String vname = request.getParameter("vehicle");


			Driver driver = new Driver(id,fileName,name,email,mobile,nic,vname);
			driver.setId(Integer.parseInt(id));
			driver.setFilename(fileName);
			driver.setName(name);
			driver.setEmail(email);
			driver.setPhone(mobile);
			driver.setNic(nic);
			DriverDao.updateData(driver);

			request.setAttribute("message", "Driver Details updated successfully!");
			response.sendRedirect(request.getContextPath() + "/AdminController?action=driver");

		}

	}
	public void showdriverForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Driver> DriverList = new ArrayList<>();

		try {
			DriverList = DriverService.getAllDrivers();
			request.setAttribute("DriverController", DriverList);
		} catch (SQLException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}

		// Always forward to the JSP page
		request.getRequestDispatcher("WEB-INF/view/User/driver.jsp").forward(request, response);
	}
	public void adddriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Part filePart = request.getPart("image");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();


		String uploadPath = getServletContext().getRealPath("") + File.separator + "assets/user/img/driver/";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) uploadDir.mkdir();


		String filePath = uploadPath + File.separator + fileName;
		filePart.write(filePath);

		response.getWriter().println("File uploaded successfully: " + fileName);

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String vehicle = request.getParameter("vehicle");
		String nic = request.getParameter("nic");
		String password = request.getParameter("password");
		String repassword = request.getParameter("conpassword");

		if (!password.equals(repassword)) {
			request.setAttribute("errorMessage", "Passwords do not match! Please enter the same password.");
		}


		AdminDriver driver = new AdminDriver(fileName,name,email,mobile,vehicle,nic,password);
		driver.setFilename(fileName);

		driver.setName(name);
		driver.setEmail(email);
		driver.setPhone(mobile);
		driver.setVehicle(vehicle);
		driver.setNic(nic);
		driver.setPassword(password);

		DriverDao.addData(driver);

		request.setAttribute("errorMessage", "Driver details added successfully!");
		request.getRequestDispatcher("AdminController?action=driver").forward(request, response);

	}
	private void Showdrivervehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DriverDao driverDao = new DriverDao();

		Integer userId = (Integer) request.getSession().getAttribute("userId");

		List<Vehicle> DriverList = DriverDao.getdrivervehicle(userId);
		request.setAttribute("DriverController", DriverList);


		request.getRequestDispatcher("WEB-INF/view/Driver/Drivervehicle.jsp").forward(request, response);
	}




	}
