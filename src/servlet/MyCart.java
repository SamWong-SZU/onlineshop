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

import bean.CartBean;
import bean.ProductBean;

import dao.CartDao;
import dao.ProductDao;

public class MyCart extends HttpServlet {

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
		if(session.getAttribute("uid")==null||session.getAttribute("uid")=="") 
			response.sendRedirect("index.jsp");
		else 
		{
			int	 uid=(Integer) session.getAttribute("uid");
			
			//CartDao dao=new CartDao();
			//ArrayList<CartBean> clist=new ArrayList<CartBean>();
			//clist=dao.getAllCart(uid);
			//session.setAttribute("clist",clist);
	//
			
			CartDao dao=new CartDao();
			ProductDao pDao=new ProductDao();
			ArrayList<CartBean> clist=dao.getAllCart(uid);
			ArrayList<ProductBean> pd=new ArrayList<ProductBean>();
			if(clist!=null){
			for(int i=0;i<clist.size();i++){
				pd.add(pDao.getProductByPid(clist.get(i).getPid()));
			}
			session.setAttribute("pd", pd);
			session.setAttribute("clist", clist);
			}
			else{
				session.setAttribute("pd",null);
				session.setAttribute("clist",null);
			}
			request.getRequestDispatcher("viewMyCart.jsp").forward(request, response);
			out.flush();
			out.close();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	

}
