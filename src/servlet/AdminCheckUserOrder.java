package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderBean;
import bean.ProductBean;
import dao.OrderDao;
import dao.ProductDao;
import dao.UserDao;

public class AdminCheckUserOrder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		int uid=Integer.parseInt(request.getParameter("uid"));

		OrderDao odao=new OrderDao();
		ArrayList<Integer> key=new ArrayList<Integer>();
		Map<Integer,ArrayList<OrderBean>> map=odao.GetAllOrderByUid(key,uid);
		ProductDao pdao=new ProductDao();
		ArrayList<ArrayList<ProductBean>> ppp=new ArrayList<ArrayList<ProductBean>>();
		ArrayList<Integer> stat=new ArrayList<Integer>();
		
		for(int i=0;i<map.size();i++){
			ArrayList<ProductBean> pp=new ArrayList<ProductBean>();
			for(int j=0;j<map.get(key.get(i)).size() ;j++){
				ProductBean p=pdao.getProductByPid(map.get(key.get(i)).get(j).getPid());
				pp.add(p);
			}
			ppp.add(pp);
			stat.add(odao.returnStatByOrderNo(key.get(i)));
		}
		UserDao uDao=new UserDao();
		
		session.setAttribute("thisuser",uDao.getUserByUid(uid).getUser());
		session.setAttribute("authority",uDao.getUserByUid(uid).getAuthority());
		session.setAttribute("stat",stat);
		session.setAttribute("ppp",ppp);
		session.setAttribute("map",map);
		session.setAttribute("key",key);
		
		out.flush();
		out.close();
	}

}
