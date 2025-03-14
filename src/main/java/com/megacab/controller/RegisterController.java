package com.megacab.controller;


import com.megacab.Dao.DbConnectionFactory;
import com.megacab.Dao.RegisterDao;
import com.megacab.model.Register;
import com.megacab.service.RegisterService;
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




/**
 * Servlet implementation class RegisterController
 */

class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterService registerService;

	public void init() throws ServletException {
		registerService = RegisterService.getInstance();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("add")) {
			addData(request, response);
		}
		response.getWriter().println("Register Successful!");
	}

	private void addData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		

		String Query = "SELECT email  FROM login";

		try (Connection connection = DbConnectionFactory.getConnection();
			 PreparedStatement Stmt = connection.prepareStatement(Query)) {

			ResultSet rs = Stmt.executeQuery();
				while (rs.next())
				{
					String dataemail = rs.getString("email");
					if (dataemail==email)
					{
						request.setAttribute("errorMessage", "Email Already Exists. Try another email!");
						request.getRequestDispatcher("Login?action=show").forward(request, response);
						return;
					}
					else
					{
						Register register = new Register();
						register.setName(name);
						register.setPassword(password);
						register.setEmail(email);

						RegisterDao.addData(register);


						request.getRequestDispatcher("Login?action=lognow").forward(request, response);
					}


				}


		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "An error occurred. Please try again.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}


}
