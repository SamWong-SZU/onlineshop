package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;

import dao.ExpressDao;
import dao.OrderDao;
import dao.ProductDao;
import dao.UserDao;

public class PayOrder extends HttpServlet {

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
		String str=request.getParameter("orderno");
		int orderno=Integer.parseInt(str);
		String str2=request.getParameter("consumption");
		double consumption=Double.parseDouble(str2);
		
		
		//�޸�����
		ProductDao pDao=new ProductDao();
		pDao.orderpay_changesales(orderno);
		//�޸Ķ���״̬
		OrderDao oDao=new OrderDao();
		oDao.UpdateStatByOrderNo(orderno, 1);
		//�޸��û����ѽ�� �鿴�ܷ�����
		UserDao uDao=new UserDao();
		uDao.updateconsumption(oDao.GetUidByOrderno(orderno), consumption);
		//���������Ϣ
		ExpressDao eDao=new ExpressDao();
		eDao.ExpressNewOrder(orderno);
		
		UserBean u=uDao.getUserByUid(oDao.GetUidByOrderno(orderno));
		HttpSession session=request.getSession();
		session.setAttribute("consumption", u.getConsumption());
		session.setAttribute("authority",u.getAuthority());
		out.flush();
		out.close();
	}

}
