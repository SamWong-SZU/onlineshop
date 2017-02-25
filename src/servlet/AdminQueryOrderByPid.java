package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDao;
import bean.*;

public class AdminQueryOrderByPid extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		request.setCharacterEncoding("utf-8");
		
		HttpSession session=request.getSession();
		if(!"admin".equals(session.getAttribute("auth"))){
			response.sendRedirect("admin.jsp");
		}
		else{
			int pid=Integer.parseInt(request.getParameter("pid"));
			OrderDao oDao=new OrderDao();
			ArrayList<Integer> stat=new ArrayList<Integer>();
			ArrayList<Integer> uid=new ArrayList<Integer>();
			ArrayList<OrderBean> AllOrderofProduct=oDao.GetAllOrderByPid(pid,stat,uid);
			session.setAttribute("Order", AllOrderofProduct);
			session.setAttribute("Orderstat", stat);
			session.setAttribute("Orderuid", uid);
			session.setAttribute("thispid", pid);
			
			request.getRequestDispatcher("adminShowOrder.jsp").forward(request, response);
		}
		out.flush();
		out.close();
	}

}
