package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ExpressBean;

import dao.ExpressDao;

public class CheckExpress extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
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
		if(session.getAttribute("uid")==null||session.getAttribute("uid")=="") 
			response.sendRedirect("index.jsp");
		else 
		{
			int	 uid=(Integer) session.getAttribute("uid");
			ExpressDao eDao=new ExpressDao();
			ArrayList<ExpressBean> expresslist=eDao.getAllExpressByUid(uid);
			if(expresslist.size()>0)
				for(int i=0;i<expresslist.size();i++){
					out.print("订单号:"+expresslist.get(i).getOrderno()+"   快递单号:"+expresslist.get(i).getExpressno()+"<br>");
				}
			else
				out.print("暂无物流信息");
			
		}
		out.flush();
		out.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
