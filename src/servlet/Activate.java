package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class Activate extends HttpServlet {

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
		String code=request.getParameter("code");
		UserDao dao=new UserDao();
		if(dao.activateUser(code))
			out.print("<script type='text/javascript'>window.location.href=\"index.jsp\";alert(\"激活成功\");</script>");
		else
			out.print("<script type='text/javascript'>window.location.href=\"index.jsp\";alert(\"激活失败\");</script>");
		out.flush();
		out.close();
	}

	
	

}
