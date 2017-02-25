package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bean.CartBean;

public class CartDao {
	public ArrayList<CartBean> getAllCart(int uid){
		
		ArrayList<CartBean> clist=new ArrayList<CartBean>();
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select * from cart where uid=? ";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, uid);
		 	ResultSet rs=ps.executeQuery();
		 	
		 	while(rs.next()){
		 		CartBean c=new CartBean();
		 		c.setCid(rs.getInt("cid"));
		 		c.setUid(rs.getInt("uid"));
		 		c.setPid(rs.getInt("pid"));
		 		c.setQuantity(rs.getInt("quantity"));
		 		clist.add(c);
		 	}
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		if(clist.size()==0) clist=null;
		return clist;
	}
	
	public boolean AddtoCart(int uid,int pid,int quantity){
		boolean flag=false;
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select * from cart where uid=? and pid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, uid);ps.setInt(2,pid);
		 	ResultSet rs=ps.executeQuery();
		 	
		 	if(rs.next()){
		 		String sql2="update cart set quantity=quantity+"+quantity+" where uid=? and pid=?";
		 		PreparedStatement ps2=connection.prepareStatement(sql2);
		 		ps2.setInt(1, uid);ps2.setInt(2,pid);
		 		ps2.executeUpdate();
		 		flag=true;
		 	}
		 	else{
		 		String sql3="insert into cart value(?,?,?,?)";
		 		PreparedStatement ps3=connection.prepareStatement(sql3);
		 		ps3.setNull(1, Types.INTEGER);ps3.setInt(2,pid);ps3.setInt(3,uid);ps3.setInt(4,quantity);
		 		ps3.execute();
		 		flag=true;
		 	}
		 	
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}
	public boolean delcid(int cid){
		boolean flag=false;
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="delete  from cart where cid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1,cid);
		 	ps.execute();
		 	flag=true;		
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}
	public boolean delAllCidByUid(int uid){
		boolean flag=false;
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="delete  from cart where uid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1,uid);
		 	ps.execute();
		 	flag=true;			
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return flag;
	}
	public ArrayList<CartBean> gelAllCidByUid(int uid){
		ArrayList<CartBean> cartlist=new ArrayList<CartBean>();
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select *  from cart where uid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1,uid);
		 	ResultSet rs=ps.executeQuery();
		 	while(rs.next()){
		 		CartBean c=new CartBean();
		 		c.setCid(rs.getInt("cid"));
		 		c.setPid(rs.getInt("pid"));
		 		c.setQuantity(rs.getInt("quantity"));
		 		c.setUid(rs.getInt("uid"));
		 		cartlist.add(c);
		 	}		
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		return cartlist;
	}
}
