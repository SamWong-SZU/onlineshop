package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.UserBean;
import dao.UserDao;

public class Register extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String myEmailAccount = "samtechtest@163.com";
    public static String myEmailPassword = "javamailtest1995";
    public static String myEmailSMTPHost = "smtp.163.com";
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
    public static MimeMessage createMimeMessage1(Session session, String sendMail, String receiveMail,String reguser,String code) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "hks_web", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, reguser+"用户", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("hks_web_帐号激活", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(reguser+"用户你好，请点击下面的链接激活你的账户: http://localhost:8080/final/Activate?code="+code, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }
   
    public  String getRandomString(int length) { //length表示生成字符串的长度  
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
        Random random = new Random();     
        StringBuffer sb = new StringBuffer();     
        for (int i = 0; i < length; i++) {     
            int number = random.nextInt(base.length());     
            sb.append(base.charAt(number));     
        }     
        return sb.toString();     
     } 
    
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//生成15位的随机码
		 String code=getRandomString(15);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String receiveMailAccount =request.getParameter("regemail");
		String user=request.getParameter("reguser");
		String pwd=request.getParameter("regpwd");
		String phone=request.getParameter("regphone");
		String name=request.getParameter("regname");
		String address=request.getParameter("regaddress");
		UserBean u=new UserBean();
		UserDao dao=new UserDao();
		
		u.setuser( user, pwd, phone, address, name , code);
		if(dao.NewUser(u)){
		
			
			Properties props = new Properties();                    // 参数配置
		    props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
		    props.setProperty("mail.host", myEmailSMTPHost);        // 发件人的邮箱的 SMTP 服务器地址
		    props.setProperty("mail.smtp.auth", "true");            // 请求认证，参数名称与具体实现有关
			     // 2. 根据配置创建会话对象, 用于和邮件服务器交互
		    Session session = Session.getDefaultInstance(props);
		    session.setDebug(false);                              // 设置为debug模式, 可以查看详细的发送 log
		    
		    MimeMessage message=null;
			try {
				message = createMimeMessage1(session, myEmailAccount, receiveMailAccount,user,code);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    // 4. 根据 Session 获取邮件传输对象
	       Transport transport;
			try {
				transport = session.getTransport();
				 // 5. 使用 邮箱账号 和 密码 连接邮件服务器
		        //    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
		        transport.connect(myEmailAccount, myEmailPassword);
	
		        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
		        transport.sendMessage(message, message.getAllRecipients());
		        transport.close();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.println("已成功注册，请到所填邮箱激活帐号,将在5秒后回到主页");
			
			out.println("  </BODY>");
			out.println("</HTML>");
		}
		else
			out.print("连接错误");
		response.addHeader("refresh", "5;URL=index.jsp");
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
