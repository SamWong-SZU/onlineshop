package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import bean.UserBean;
import dao.UserDao;
public class LoginVerify extends HttpServlet {

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
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String user=request.getParameter("user");
		String pwd =request.getParameter("pwd");
		String autolog=request.getParameter("autolog");
		String remeberuser=request.getParameter("remeberuser");
		if(autolog==null) autolog="0";
		if(remeberuser==null) remeberuser="0";
		UserBean u=new UserBean();
		u.setUser(user);u.setPwd(pwd);
		UserDao dao=new UserDao();
		HttpSession session=request.getSession();
//验证登陆
		if(dao.LoginVerify(u)){
			u=dao.getUserByUser(user);
			//保存cookies
			int time=-1;
			int time2=-1;
			if("1".equals(remeberuser))
				time=60*60*24*30;
			if("1".equals(autolog)){
				time=60*60*24*30;
				time2=60*60*24*30;
			}
			Cookie cookie=new Cookie("user",user);
			cookie.setMaxAge(time);
			response.addCookie(cookie);
			cookie=new Cookie("pwd",pwd);
			cookie.setMaxAge(time2);
			response.addCookie(cookie);
			
			cookie=new Cookie("log","in");
			cookie.setMaxAge(time2);
			response.addCookie(cookie);
			
			session.setAttribute("consumption", u.getConsumption());
			session.setAttribute("log","in");
			session.setAttribute("user", user);
			session.setAttribute("uid",u.getUid());
			session.setAttribute("authority",u.getAuthority());
			response.addHeader("refresh", "0;URL=index.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
			
			
		}
		else {
			
			session.setAttribute("log", "wrong");
			out.print("<script>window.location.href='index.jsp';alert('登录失败，原因可能如下\\n1.用户名不存在\\n2.密码错误\\n3.账户未通过邮箱激活');</script>");
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
