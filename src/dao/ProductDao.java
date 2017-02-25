package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import bean.CartBean;
import bean.ProductBean;

public class ProductDao {
	private int perpage=3;
	public void setperpage(int i){perpage=i;}
	public int getperpage(){return perpage;}
	public ArrayList<ProductBean> getAllProduct(){
		
		ArrayList<ProductBean> plist=new ArrayList<ProductBean>();
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select * from product ";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ResultSet rs=ps.executeQuery();
		 	
		 	while(rs.next()){
		 		ProductBean p=new ProductBean();
		 		p.setAll(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getDouble(4),rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
		 		plist.add(p);
		 	}
		 	connection.close();
		 	
		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		if(plist.size()==0) plist=null;
		return plist;
	}
	
	public ArrayList<ProductBean> getProduct(int page){
		
		ArrayList<ProductBean> plist=new ArrayList<ProductBean>();
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
			
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select * from product limit "+((page-1)*perpage)+","+perpage;
		 	
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ResultSet rs=ps.executeQuery();
		 	
		 	while(rs.next()){
		 		ProductBean p=new ProductBean();
		 		p.setAll(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getDouble(4),rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
		 		plist.add(p);
		 	}
		 	
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		if(plist.size()==0) plist=null;
		return plist;
	}
	
	public ArrayList<ProductBean> getProduct(int page,double min,double max,String word){
		
		ArrayList<ProductBean> plist=new ArrayList<ProductBean>();
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
			
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select * from product where price>"+min+" and price<"+max+
		 			" and (pname like \"%"+word+"%\" or detail like \"%"+word+"%\") limit "+
		 			((page-1)*perpage)+","+perpage;
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ResultSet rs=ps.executeQuery();
		 	while(rs.next()){
		 		ProductBean p=new ProductBean();
		 		p.setAll(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getDouble(4),rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
		 		plist.add(p);
		 	}
		 	
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return plist;
	}
	
	public ArrayList<ProductBean> getAllProduct(double min,double max,String word){
		
		ArrayList<ProductBean> plist=new ArrayList<ProductBean>();
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
			
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select * from product where price>"+min+" and price<"+max+" and (pname like \"%"+word+"%\" or detail like \"%"+word+"%\") " ;
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ResultSet rs=ps.executeQuery();

		 	while(rs.next()){
		 		ProductBean p=new ProductBean();
		 		p.setAll(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getDouble(4),rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
		 		plist.add(p);
		 	}
		 	
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
	
		return plist;
	}

	public ProductBean getProductByPid(int pid){
		ProductBean p=null;
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
			
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select * from product where pid=?";
		 	
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1,pid);
		 	ResultSet rs=ps.executeQuery();
		 	
		 	if(rs.next()){
		 		p=new ProductBean();
		 		p.setAll(rs.getInt("pid"), rs.getString("img"),rs.getString("pname"),rs.getDouble("price"), rs.getDouble("discount"),rs.getInt("stock"), rs.getInt("sales"), rs.getString("detail"));
		 	}
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return p;
		
	}
	
	public boolean changeStock(int orderno){
		boolean flag=false;
		try {
			
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	
		 	//用户下单 修改商品库存量
	
		 	String sql="update  product,orderdetail set product.stock=product.stock-orderdetail.quantity where " +
		 			"orderdetail.orderno=? and orderdetail.pid=product.pid";
		 	
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, orderno);
		 	ps.executeUpdate();	 	
		 	
		 	
		 	flag=true;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}

	public boolean checkStock(int uid){
		boolean flag=false;
		try {
			
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	//获取该用户购物车中列表
		 	CartDao Cdao=new CartDao();
			ArrayList<CartBean> allcart=Cdao.gelAllCidByUid(uid);
			flag=true;
			//查看库存是否满足要求
			for(int i=0;i<allcart.size();i++){
				String sql="select * from product where pid=?";
				PreparedStatement ps=connection.prepareStatement(sql);
			 	ps.setInt(1, allcart.get(i).getPid());
			 	ResultSet rs=ps.executeQuery();
			 	if(rs.next()){
			 		//库存小于需求 设置购物车数量为0 重新输入
			 		if(rs.getInt("stock")<allcart.get(i).getQuantity()){
			 			String sql2="update cart set quantity=0 where cid=?";
						PreparedStatement ps2=connection.prepareStatement(sql2);
					 	ps2.setInt(1, allcart.get(i).getCid());
					 	ps2.execute();
					 	flag=false;
			 		}
			 	}
			}
		 
		 
		 	
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}
	
	public boolean Delorder_backtostock(int orderno){
		boolean flag=false;
		try {
			
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	//用户删除订单 商品库存量返还
		 	String sql="update product,orderdetail set product.stock=product.stock+orderdetail.quantity  " +
		 			"where product.pid=orderdetail.pid and orderdetail.orderno=?";
		 	
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, orderno);
		 	ps.execute();
		 	
		 	
		 	
		 	flag=true;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}

	public boolean orderpay_changesales(int orderno){
		boolean flag=false;
		try {
			
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	//用户支付订单 商品销售量增加
		 	String sql="update product,orderdetail set product.sales=product.sales+orderdetail.quantity  " +
		 			"where product.pid=orderdetail.pid and orderdetail.orderno=?";
		 	
		 
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, orderno);
		 	ps.execute();
		 	
		 
		 
		 	flag=true;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}
	
	//admin 
	public boolean admin_changename(int pid,String pname){
		boolean flag=false;
		try {
			
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	//获取所有该订单号的商品&数量
		 	String sql="update product set pname=? where pid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setString(1, pname);
		 	ps.setInt(2, pid);
		 	ps.execute();
		 	flag=true;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}
	public boolean admin_changeprice(int pid,double price){
		boolean flag=false;
		try {
			
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	//获取所有该订单号的商品&数量
		 	String sql="update product set price=? where pid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setDouble(1, price);
		 	ps.setInt(2, pid);
		 	ps.execute();
		 	flag=true;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}
	public boolean admin_changediscount(int pid,double discount){
		boolean flag=false;
		try {
			
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	//获取所有该订单号的商品&数量
		 	String sql="update product set discount=? where pid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setDouble(1, discount);
		 	ps.setInt(2, pid);
		 	ps.execute();
		 	flag=true;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}
	public boolean admin_changedetail(int pid,String detail){
		boolean flag=false;
		try {
			
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	//获取所有该订单号的商品&数量
		 	String sql="update product set detail=? where pid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setString(1, detail);
		 	ps.setInt(2, pid);
		 	ps.execute();
		 	flag=true;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}
	public boolean admin_changestock(int pid,int stock){
		boolean flag=false;
		try {
			
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	//获取所有该订单号的商品&数量
		 	String sql="update product set stock=? where pid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, stock);
		 	ps.setInt(2, pid);
		 	ps.execute();
		 	flag=true;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}
	public boolean AddProduct(ProductBean p){
		boolean flag=false;
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="insert into product values(?,?,?,?,?,?,?,?)";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setNull(1, Types.INTEGER);
		 	ps.setString(2, p.getImg());
		 	ps.setString(3, p.getPname());
		 	ps.setDouble(4, p.getPrice());
		 	ps.setDouble(5, p.getDiscount());
		 	ps.setInt(6, p.getStock());
		 	ps.setInt(7, p.getSales());
		 	ps.setString(8, p.getDetail());
		 	flag=ps.execute();
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
		
	}
	public boolean DelProductByPid(int pid){
		boolean flag=false;
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="delete from product where pid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1,pid);
		 	flag=ps.execute();
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}
}
