package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bean.ReviewBean;

public class ReviewDao {
	public ArrayList<ReviewBean> getAllReviewByPid(int pid){
		ArrayList<ReviewBean> rlist=new ArrayList<ReviewBean>();
		try {
			
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select * from review where pid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, pid);
		 	ResultSet rs=ps.executeQuery();
		 	UserDao uDao=new UserDao();
		 	while(rs.next()){
		 		ReviewBean r=new ReviewBean();
		 		r.setPid(pid);
		 		r.setReview(rs.getString(4));
		 		r.setRid(rs.getInt("rid"));
			 	r.setUser(uDao.getUserByUid(rs.getInt("uid")).getUser());
			 	rlist.add(r);
			 	rs.clearWarnings();
		 	}
		 	rs.close();
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return rlist;
	}

	public void AddReview(ReviewBean r){
		UserDao uDao=new UserDao();
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="insert into review value(?,?,?,?)";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setNull(1,Types.INTEGER);
		 	ps.setInt(2,uDao.getUserByUser(r.getUser()).getUid());
		 	ps.setInt(3,r.getPid());
		 	ps.setString(4,r.getReview());
		 	ps.execute();
		 	
		 	ps.close();
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
	}
}
