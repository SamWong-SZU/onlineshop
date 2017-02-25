package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import dao.OrderDao;
import dao.ProductDao;

public class OrderfromCart extends HttpServlet {

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
		int uid=Integer.parseInt(uidstr);
		//查看是否超出库存
		ProductDao pDao=new ProductDao();
		if(pDao.checkStock(uid)){
			OrderDao Odao=new OrderDao();
		//添加进订单
			int orderno;
			orderno=Odao.OrderFromCart(uid);
		//清空购物车
			CartDao cDao=new CartDao();
			cDao.delAllCidByUid(uid);
			pDao.changeStock(orderno);
			out.print("true");
		}
		else{
			out.print("false");
		}
		out.flush();
		out.close();
	}

}
