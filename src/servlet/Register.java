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
        // 1. ����һ���ʼ�
        MimeMessage message = new MimeMessage(session);

        // 2. From: ������
        message.setFrom(new InternetAddress(sendMail, "hks_web", "UTF-8"));

        // 3. To: �ռ��ˣ��������Ӷ���ռ��ˡ����͡����ͣ�
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, reguser+"�û�", "UTF-8"));

        // 4. Subject: �ʼ�����
        message.setSubject("hks_web_�ʺż���", "UTF-8");

        // 5. Content: �ʼ����ģ�����ʹ��html��ǩ��
        message.setContent(reguser+"�û���ã�������������Ӽ�������˻�: http://localhost:8080/final/Activate?code="+code, "text/html;charset=UTF-8");

        // 6. ���÷���ʱ��
        message.setSentDate(new Date());

        // 7. ��������
        message.saveChanges();

        return message;
    }
   
    public  String getRandomString(int length) { //length��ʾ�����ַ����ĳ���  
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
		//����15λ�������
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
		
			
			Properties props = new Properties();                    // ��������
		    props.setProperty("mail.transport.protocol", "smtp");   // ʹ�õ�Э�飨JavaMail�淶Ҫ��
		    props.setProperty("mail.host", myEmailSMTPHost);        // �����˵������ SMTP ��������ַ
		    props.setProperty("mail.smtp.auth", "true");            // ������֤���������������ʵ���й�
			     // 2. �������ô����Ự����, ���ں��ʼ�����������
		    Session session = Session.getDefaultInstance(props);
		    session.setDebug(false);                              // ����Ϊdebugģʽ, ���Բ鿴��ϸ�ķ��� log
		    
		    MimeMessage message=null;
			try {
				message = createMimeMessage1(session, myEmailAccount, receiveMailAccount,user,code);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    // 4. ���� Session ��ȡ�ʼ��������
	       Transport transport;
			try {
				transport = session.getTransport();
				 // 5. ʹ�� �����˺� �� ���� �����ʼ�������
		        //    ������֤����������� message �еķ���������һ�£����򱨴�
		        transport.connect(myEmailAccount, myEmailPassword);
	
		        // 6. �����ʼ�, �������е��ռ���ַ, message.getAllRecipients() ��ȡ�������ڴ����ʼ�����ʱ��ӵ������ռ���, ������, ������
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
			out.println("�ѳɹ�ע�ᣬ�뵽�������伤���ʺ�,����5���ص���ҳ");
			
			out.println("  </BODY>");
			out.println("</HTML>");
		}
		else
			out.print("���Ӵ���");
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
