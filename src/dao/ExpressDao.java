package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bean.ExpressBean;
import bean.OrderBean;

public class ExpressDao {
	public void ExpressNewOrder(int orderno){
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="insert into express value(?,?)";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setNull(1, Types.INTEGER);ps.setInt(2, orderno);
		 	ps.execute();
		 	
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
	}
	public ArrayList<ExpressBean> getAllExpressByUid(int uid){
		ArrayList<ExpressBean> expresslist=new ArrayList<ExpressBean>();
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);


		 	//获取 该用户的所有订单号对应的快递单号 
	 		String sql="select * from express where orderno in(select orderno from userorder where uid=?)";

		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, uid);
		 	ResultSet rs=ps.executeQuery();
		 	while(rs.next())
		 		{
			 		ExpressBean e=new ExpressBean();
			 		e.setExpressno(rs.getInt("expressno"));
			 		e.setOrderno(rs.getInt("Orderno"));
			 		expresslist.add(e);
		 		}
		 	
		 	
		 	
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		return expresslist;
	}
}
