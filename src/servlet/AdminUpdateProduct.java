package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ProductBean;

import dao.ProductDao;

public class AdminUpdateProduct extends HttpServlet {

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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		ProductDao pDao=new ProductDao();
		String change=request.getParameter("change");
		if("add".equals(change)){
			String img=request.getParameter("img");
			String name=request.getParameter("name");
			double price=Double.parseDouble(request.getParameter("price"));
			double discount=Double.parseDouble(request.getParameter("discount"));
			String detail=request.getParameter("detail");
			int stock=Integer.parseInt(request.getParameter("stock"));
			ProductBean p=new ProductBean();
			p.setAll(0,img, name, price, discount, stock, 0, detail);
			pDao.AddProduct(p);
			response.sendRedirect("adminX.jsp");
		}
		else if("del".equals(change)){
			int pid=Integer.parseInt(request.getParameter("pid"));
			pDao.DelProductByPid(pid);
		}
		else{
			int pid=Integer.parseInt(request.getParameter("pid"));
			
			if("name".equals(change)){
				String pname=request.getParameter("name");
				pDao.admin_changename(pid, pname);
			}
			else if("price".equals(change)){
				double v=Double.parseDouble(request.getParameter("price"));
				pDao.admin_changeprice(pid, v);
			}
			else if("discount".equals(change)){
				double v=Double.parseDouble(request.getParameter("discount"));
				pDao.admin_changediscount(pid, v);
			}
			else if("detail".equals(change)){
				String v=request.getParameter("detail");
				pDao.admin_changedetail(pid, v);
			}
			else if("stock".equals(change)){
				int v=Integer.parseInt(request.getParameter("stock"));
				pDao.admin_changestock(pid, v);
			}
		}

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
