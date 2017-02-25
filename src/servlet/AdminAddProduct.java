package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import dao.ProductDao;

import bean.ProductBean;

public class AdminAddProduct extends HttpServlet {

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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = null;
		String detail= null;
		double price= -1;
		double discount= -1;
		int stock= -1;
		String img=null;
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<?> items = null;
		try {
			items = upload.parseRequest(request);
			} 
		catch (FileUploadException e) {
			e.printStackTrace();
			} // 解析request请求
		Iterator<?> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (item.isFormField()) {  // 如果是表单域 ，就是非文件上传元素
				if (item.getFieldName().equals("name")) {
					name=new String(item.getString().getBytes("ISO-8859-1"),"utf-8");
				}if (item.getFieldName().equals("detail")) {
					detail=item.getString();
				}if (item.getFieldName().equals("price")) {
					price=Double.parseDouble(item.getString());
				}if (item.getFieldName().equals("discount")) {
					discount=Double.parseDouble(item.getString());
				}if (item.getFieldName().equals("stock")) {
					stock=Integer.parseInt(item.getString());
				}
			} 
			else {
				String fileName = item.getName(); // 文件的全路径，绝对路径名加文件名
				File saveFile = new File("C:/Users/HoiSum/Workspaces/MyEclipse Professional/final/WebRoot/images/"+fileName); // 定义一个file指向一个具体的文件
				img="images/"+fileName;
				try {
					item.write(saveFile);// 把上传的内容写到一个文件中
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
		}
		
		ProductDao pDao=new ProductDao();
		ProductBean p=new ProductBean();
		p.setAll(0,img, name, price, discount, stock, 0, detail);
		pDao.AddProduct(p);
		response.sendRedirect("adminX.jsp");
		
		out.flush();
		out.close();
	}

}
