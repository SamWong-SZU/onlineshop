package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bean.CartBean;
import bean.OrderBean;
import bean.UserBean;

public class OrderDao {
	
	//从购物车下单,返回order单号
	public int OrderFromCart(int uid){
		int orderno=0;
		try {
			//先检测库存是否足够
			
			CartDao cdao=new CartDao();
			ArrayList<CartBean> cartlist=cdao.gelAllCidByUid(uid);
			orderno=2016*1000000+((int)(Math.random()*1000000));
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	//添加 语句 user-order关系
		 	String sql="insert into userorder value(?,?,?)";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, orderno);ps.setInt(2, uid);ps.setInt(3, 0);
		 	ps.execute();
		 	
		 	//将购物车中数据 放入order detail
		 	for(int i=0;i<cartlist.size();i++){
		 		CartBean c=cartlist.get(i);
		 		String sql2="insert into orderdetail value(?,?,?,?)";
			 	PreparedStatement ps2=connection.prepareStatement(sql2);
			 	ps2.setNull(1, Types.INTEGER);
			 	ps2.setInt(2, orderno);
			 	ps2.setInt(3, c.getPid());
			 	ps2.setInt(4, c.getQuantity());
			 	ps2.execute();
		 	}
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return orderno;
	}
	//获取订单状态
	public int returnStatByOrderNo(int orderno){
		int stat=-1;
		try {
			
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	//添加 语句 user-order关系
		 	String sql="select * from userorder where orderno=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, orderno);
		 	ResultSet rs=ps.executeQuery();
		 	if(rs.next()){
		 		stat=rs.getInt("stat");
		 	}
		 
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return stat;
	}
	
	//获取某一用户所有订单明细
	public Map<Integer,ArrayList<OrderBean>> GetAllOrderByUid( ArrayList<Integer> key,int uid){
		Map<Integer,ArrayList<OrderBean>> map=new HashMap<Integer, ArrayList<OrderBean>>();
		
		try {
				
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	
		 	//用户查看自己所有订单以及订单明细 根据订单状态排序
		 	String sql="" +
		 	"select orderdetail.* , userorder.stat from orderdetail,userorder where " +
		 	"(userorder.orderno=orderdetail.orderno and userorder.orderno in" +
		 	"(select userorder.orderno from userorder where uid=?)) order by stat";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, uid);
		 	ResultSet rs=ps.executeQuery();
		 	ArrayList<OrderBean> orderlist=new ArrayList<OrderBean>();
		 
		 	//获取所有order内容
		 	while(rs.next()){
		 		int orderno=rs.getInt("orderno");
		 		if(!key.contains(orderno)){
		 			key.add(orderno);
		 			orderlist=new ArrayList<OrderBean>();
		 		}
		 		OrderBean o=new OrderBean();
		 		o.setOid(rs.getInt("oid"));
		 		o.setOrderno(rs.getInt("orderno"));
		 		o.setPid(rs.getInt("pid"));
		 		o.setQuantity(rs.getInt("quantity"));
		 		orderlist.add(o);
		 		map.put(orderno, orderlist);
		 	}
		 		 
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return map;
	}

	//获取某一product 所有订单明细
	public ArrayList<OrderBean> GetAllOrderByPid(int pid,ArrayList<Integer> statlist,ArrayList<Integer> userlist){
		ArrayList<OrderBean> Olist=new  ArrayList<OrderBean>();
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select * from orderdetail where pid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, pid);
		 	ResultSet rs=ps.executeQuery();
		 	while(rs.next()){
		 		OrderBean o=new OrderBean();
		 		o.setOid(rs.getInt("oid"));
		 		o.setOrderno(rs.getInt("orderno"));
		 		o.setPid(rs.getInt("pid"));
		 		o.setQuantity(rs.getInt("quantity"));
		 		Olist.add(o);
		 		String sql2="select * from userorder where orderno=?";
			 	PreparedStatement ps2=connection.prepareStatement(sql2);
			 	ps2.setInt(1, o.getOrderno());
			 	ResultSet rs2=ps2.executeQuery();
			 	if(rs2.next()){
				 	statlist.add(rs2.getInt("stat"));
				 	userlist.add(rs2.getInt("uid"));
			 	}
		 	} 	
		  	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return Olist;
	}
			 	
			 
		
	//获取所有用户订单、状态(user order  )
	public ArrayList<OrderBean> getAllOrder(ArrayList<Integer> statlist){
		ArrayList<OrderBean> Olist=new  ArrayList<OrderBean>();
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	//添加 语句 user-order关系
		 	String sql="select * from orderdetail ";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ResultSet rs=ps.executeQuery();
		 	while(rs.next()){
		 		OrderBean o=new OrderBean();
		 		o.setOid(rs.getInt("oid"));
		 		o.setOrderno(rs.getInt("orderno"));
		 		o.setPid(rs.getInt("pid"));
		 		o.setQuantity(rs.getInt("quantity"));
		 		Olist.add(o);
		 		String sql2="select * from userorder where orderno=? ";
			 	PreparedStatement ps2=connection.prepareStatement(sql2);
			 	ps2.setInt(1, o.getOrderno());
			 	ResultSet rs2=ps2.executeQuery();
			 	if(rs2.next()){
			 		statlist.add(rs2.getInt("stat"));
			 	}
		 	}
		 	
		 
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return Olist;
	}
	
	//修改订单状态为 updatestat
	public void UpdateStatByOrderNo(int orderno,int updatestat){
		try {		
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 
		 	String sql="update userorder set stat=? where orderno=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, updatestat);
		 	ps.setInt(2, orderno);
		 	ps.execute();
		 
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
	}
	//通过订单获取uid
	public int GetUidByOrderno(int orderno){
		int uid=-1;
		try {
			
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 
		 	String sql="select * from userorder where orderno=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, orderno);
		 	ResultSet rs=ps.executeQuery();

		 	if(rs.next()){
		 		uid=rs.getInt("uid");
		 	}
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return uid;
	}
	//修改cid的数量
	public boolean UpdateCidQuantity(int quantity,int cid){
		boolean flag=false;
		try {
		
			
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 
		 	String sql="update cart set quantity=? where cid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, quantity);
		 	ps.setInt(2, cid);
		 	ps.execute();
		 	flag=true;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}
	
}
