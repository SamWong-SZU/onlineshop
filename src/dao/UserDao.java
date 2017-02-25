package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;



import bean.UserBean;
public class UserDao {
	
	public ArrayList<UserBean> getAllUser(){
		ArrayList<UserBean> Ulist=new ArrayList<UserBean>();
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select user.*,uiduser.uid from user,uiduser where user.user=uiduser.user";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	
		 	ResultSet rs=ps.executeQuery();
		 	
		 	while(rs.next()){
		 		UserBean u=new UserBean();
		 		u.setall(rs.getInt("uid"), rs.getString("user"),rs.getString("pwd"),rs.getString("phone"), rs.getString("address"), rs.getString("name"), rs.getString("authority"),rs.getInt("stat"), rs.getString("code"),rs.getInt("consumption"));
		 		Ulist.add(u);
		 	}
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		return Ulist;
	}
	
	public boolean LoginVerify(UserBean u){
		
		boolean flag=false;
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select * from user where user=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setString(1, u.getUser());
		 	ResultSet rs=ps.executeQuery();
		 	
		 	if(rs.next()){
		 		if(rs.getInt("stat")==1 && u.getPwd().equals(rs.getString("pwd"))){
			 		flag=true;
			 		u.setAuthority(rs.getString("authority"));
		 		}
		 		else
		 			flag=false;
		 	 }
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		return flag;
	}
	public boolean NewUser(UserBean u){
		boolean flag=false;
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String  uuser="";
		 	int uid=0;
		 	// add in uiduser
		 	String sql="insert into uiduser value(?,?)";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setNull(1,Types.INTEGER);
		 	ps.setString(2, u.getUser());
		 	ps.execute();
		 	 	
		 	
		 	String sql3="insert into user values(?,?,?,?,?,?,?,?,?)";
		 	PreparedStatement ps3=connection.prepareStatement(sql3);
		 	ps3.setString(1, u.getUser());
		 	ps3.setString(2, u.getPwd());
		 	ps3.setString(3, u.getPhone());
		 	ps3.setString(4, u.getAddress());
		 	ps3.setString(5, u.getName());
		 	ps3.setString(6, "普通会员");
		 	ps3.setInt(7, 0);
		 	ps3.setString(8,u.getCode());
		 	ps3.setInt(9,0);
		 	
		 	ps3.execute();
		 		flag=true;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		return flag;
		
	}
	
	public boolean ifUserExist(String name){
		boolean flag=false;
		try {
			ArrayList<String> userlist=new ArrayList<String>();
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select user from uiduser ";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	
		 	ResultSet rs=ps.executeQuery();

		 	while(rs.next())
		 		userlist.add(rs.getString(1));
		 	
		 	if(userlist.contains(name))
		 		flag=true;
		 	else
		 		flag=false;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		return flag;
	}
	
	public boolean activateUser(String code){
		boolean flag=false;
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="update user set  stat=1 where code=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	
		 	ps.setString(1, code);	 	
		 	int i=ps.executeUpdate();
		 	if(i==1)
		 		flag=true;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		return flag;
	}
	public UserBean getUserByUid(int id){
		UserBean u=null;
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select user.*,uiduser.uid from user,uiduser where uiduser.uid=? and user.user=uiduser.user";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, id);
		 	ResultSet rs=ps.executeQuery();
		 	
		 	if(rs.next()){
		 		u=new UserBean();
		 		u.setall(rs.getInt("uid"), rs.getString("user"),rs.getString("pwd"),rs.getString("phone"), rs.getString("address"), rs.getString("name"), rs.getString("authority"),rs.getInt("stat"), rs.getString("code"),rs.getInt("consumption"));
		 	 }
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		return u;
	}
	public UserBean getUserByUser(String user){
		UserBean u=null;
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="select user.*,uiduser.uid from user,uiduser where user.user=? and user.user=uiduser.user";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setString(1,user);
		 	ResultSet rs=ps.executeQuery();
		 	
		 	if(rs.next()){
		 		u=new UserBean();
		 		u.setall(rs.getInt("uid"), rs.getString("user"),rs.getString("pwd"),rs.getString("phone"), rs.getString("address"), rs.getString("name"), rs.getString("authority"),rs.getInt("stat"), rs.getString("code"),rs.getInt("consumption"));
		 	 }
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		return u;
	}
	
	//修改消费金额 并 查看受否能升级
	public boolean updateconsumption(int uid,double consumption){
		boolean flag=false;
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="update user set  consumption=consumption+? where user=(select user from uiduser where uid=?)";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	
		 	ps.setDouble(1, consumption);
		 	ps.setInt(2, uid);
		 	ps.execute();
		 	UserBean u=getUserByUid(uid);
		 	if(u.getConsumption()>10000){
		 		String sql2="update user set authority='钻石会员'  where user=(select user from uiduser where uid=?)";
			 	PreparedStatement ps2=connection.prepareStatement(sql2);
			 	ps2.setInt(1, uid);
			 	ps2.execute();			 	
		 	}
		 	flag=true;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		return flag;
	}
	public boolean DelUserByUid(int uid){
		boolean flag=false;
		try {
			String driverName="sun.jdbc.odbc.JdbcOdbcDriver";
		 	String connectionString="jdbc:odbc:SAMWEB";
		 	Connection connection=null;
		 	Class.forName(driverName);
		 	connection=(Connection) DriverManager.getConnection(connectionString);
		 	String sql="delete from uiduser where uid=?";
		 	PreparedStatement ps=connection.prepareStatement(sql);
		 	ps.setInt(1, uid);
		 	ps.execute();
		 	flag=true;
		 	connection.close();

		} catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) {e.printStackTrace();}
		return flag;
	}
}
