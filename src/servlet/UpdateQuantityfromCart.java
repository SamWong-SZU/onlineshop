package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;

public class UpdateQuantityfromCart extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String cidstr=request.getParameter("cid");
		String quantitystr=request.getParameter("quantity");
		int cid=Integer.parseInt(cidstr);
		int quantity=Integer.parseInt(quantitystr);
		
		OrderDao oDao=new OrderDao();
		oDao.UpdateCidQuantity(quantity, cid);
		out.flush();
		out.close();
	}

}
