package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ProductBean;

import dao.ProductDao;

public class GetProduct extends HttpServlet {

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
		String minstr=request.getParameter("smin");
		String maxstr=request.getParameter("smax");
		String wordstr="";
		if(request.getParameter("sword")!=null)
		 wordstr=new String(request.getParameter("sword").getBytes("ISO-8859-1"),"utf-8");
		String pagestr=request.getParameter("page");


  		int thispage=1;
		double min=0;
		double max=Double.MAX_VALUE;
		String word="";
		if (pagestr!=null&&pagestr!="") thispage=Integer.parseInt(pagestr);
		if(minstr!=null&&minstr!="") min=Double.parseDouble(minstr);
		if(maxstr!=null&&maxstr!="") max=Double.parseDouble(maxstr);
  		if(wordstr!=null&&wordstr!="") word=wordstr;
		ProductDao dao=new ProductDao();
		ArrayList<ProductBean> plist=new ArrayList<ProductBean>();
		plist=dao.getProduct(thispage,min,max,word);
		
		//获取总页数
		ArrayList<ProductBean> alllist=dao.getAllProduct(min,max,word);
  		double perpage=(double)dao.getperpage();
  		double endpage=Math.ceil((double)alllist.size()/perpage);
  		//end
  		
  		//存入,转发
		HttpSession session=request.getSession();
		ServletContext application = this.getServletContext();
  		application.setAttribute("plist", plist);
  		session.setAttribute("thispage",thispage);
  		session.setAttribute("endpage",endpage);
  		session.setAttribute("min",min);
  		session.setAttribute("max",max);
  		session.setAttribute("word",word);
  		request.getRequestDispatcher("showproduct.jsp").forward(request, response);
  		
		out.flush();
		out.close();
	}

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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
