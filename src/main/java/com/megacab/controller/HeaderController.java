package com.megacab.controller;

import com.megacab.model.Header;
import com.megacab.model.Vehicle;
import com.megacab.service.HeaderService;
import com.megacab.service.VehicleService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HeaderController
 */

public class HeaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HeaderService headerService;

	public void init() throws ServletException {

		headerService = HeaderService.getInstance();
	}



	/**
     * @see HttpServlet#HttpServlet()
     */
    public HeaderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");

		if (userId != null) {
			request.setAttribute("userId", userId);
		}
		System.out.println(userId);


	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

}
