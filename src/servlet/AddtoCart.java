package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CartBean;

import dao.CartDao;

public class AddtoCart extends HttpServlet {

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
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String uidstr=request.getParameter("uid");
		String pidstr=request.getParameter("pid");
		String quantitystr=request.getParameter("quantity");

		int uid=Integer.parseInt(uidstr);
		int pid=Integer.parseInt(pidstr);
		int quantity=Integer.parseInt(quantitystr);
		CartDao dao=new CartDao();
		if(dao.AddtoCart(uid, pid, quantity))	out.print("true");
		else out.print("false");
		
		
		out.flush();
		out.close();
	}

}
