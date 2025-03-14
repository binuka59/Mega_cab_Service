package com.megacab.controller;

import com.megacab.Dao.BookingDao;
import com.megacab.Dao.DbConnectionFactory;
import com.megacab.Dao.PaymentDao;
import com.megacab.model.Booking;
import com.megacab.model.Payment;
import com.megacab.service.BookingService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentController
 */

public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookingService bookingService;

	public void init() throws ServletException {
		bookingService = BookingService.getInstance();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action =request.getParameter("action");
		if (action.equals("payuser"))
		{

			pay(request , response);

		}
		else if (action.equals("driverpay"))
		{

			driverpay(request , response);

		}


		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String action =request.getParameter("action");
		if (action.equals("payment"))
		{
			successpay(request,response);
		}
		else if (action.equals("payied"))
		{
			completepay(request,response);
		}
		else if (action.equals("updatepayment"))
		{
			withoutpay(request,response);
		}

	}
	private void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String payid = request.getParameter("payid");
		String lastquery = "SELECT * FROM booking WHERE bid=?";
		try (Connection conn = DbConnectionFactory.getConnection();
			PreparedStatement stlast = conn.prepareStatement(lastquery)){
			stlast.setString(1, payid);
			ResultSet resultSet = stlast.executeQuery();
			if (resultSet.next()) {
				String destination = resultSet.getString("Destination");
				if(destination==null)
				{
					request.setAttribute("errorMessage", "Your payment process not Complete.pay End of the journey.");
					request.getRequestDispatcher("/Login?action=booking").forward(request, response);
				}else
				{

					BookingDao bookingDao = new BookingDao();

					List<Payment> paymentList = BookingDao.getuserpaybooking(Integer.valueOf(payid));
					request.setAttribute("PaymentController", paymentList);

					request.getRequestDispatcher("WEB-INF/view/User/Paymentsheet.jsp").forward(request, response);
				}

			}

		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        request.getRequestDispatcher("/Login?action=booking").forward(request, response);
	}

	private void driverpay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer  dpid = Integer.valueOf(request.getParameter("dpid"));

		BookingDao bookingDao = new BookingDao();

		List<Payment> BookingList = BookingDao.getpaybooking(dpid);
		request.setAttribute("PaymentController", BookingList);
		request.getRequestDispatcher("WEB-INF/view/Driver/Payment.jsp").forward(request , response);
	}
	private void successpay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer payid = Integer.valueOf(request.getParameter("payid"));
		String totalKM = request.getParameter("totkm");
		Booking booking =new Booking(payid,totalKM);
		BookingDao.updatepaymnet(booking);
		String amount = request.getParameter("amount");
		Payment payment=new Payment(payid,amount);
		PaymentDao.updatepayment(payment);
		//		request.setAttribute("errorMessage", "Payment Successfully.");
		response.sendRedirect(request.getContextPath() + "/Login?action=DriverH");

	}

	private void completepay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer payiedid = Integer.valueOf(request.getParameter("payiedid"));

		Booking booking =new Booking(payiedid);
		BookingDao.updatepaymnetdetail(booking);


		PaymentDao.updatepaymentdetails(payiedid);

		request.setAttribute("errorMessage", "Payment Successfully.");
		response.sendRedirect(request.getContextPath() + "/Login?action=payment");

	}

	private void withoutpay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer payid = Integer.valueOf(request.getParameter("upid"));
		String totalKM = request.getParameter("totkm");
		System.out.println("totl"+totalKM);
		Booking booking =new Booking(payid,totalKM);

		BookingDao.updatepaymnet(booking);
		String amount = request.getParameter("amount");
		Payment payment=new Payment(payid,amount);
		PaymentDao.updatepayment(payment);
		//		request.setAttribute("errorMessage", "Payment Successfully.");
		response.sendRedirect(request.getContextPath() + "/AdminController?action=admin");

	}



}
