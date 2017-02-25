package bean;

public class UserBean {
	private int uid;
	private String user;
	private String pwd;
	private String phone;
	private String address;
	private String name;
	private String authority;
	private int stat;
	private String code;
	private double consumption;
	
	public UserBean(){}
	public void setall(int uid,String user,String pwd,String phone,String address,String name,String authority,int stat,String code,int consumption){
		setUid(uid);
		setUser(user);
		setPwd(pwd);
		setPhone(phone);
		setAddress(address);
		setName(name);
		setAuthority(authority);
		setStat(stat);
		setCode(code);
		setConsumption(consumption);
	}
	
	public void setuser(String user,String pwd,String phone,String address,String name,String code){
		setUser(user);
		setPwd(pwd);
		setPhone(phone);
		setAddress(address);
		setName(name);
		setCode(code);
		consumption=0;
	}

	public void setUid(int uid){this.uid=uid;}
	public int getUid(){return uid;}
	
	public void setStat(int stat){this.stat=stat;}
	public int getStat(){return stat;}
	
	public void setUser(String user){this.user=user;}
	public String getUser(){return user;}
	
	public void setCode(String code){this.code=code;}
	public String getCode(){return code;}

	public void setPwd(String pwd){this.pwd=pwd;}
	public String getPwd(){return pwd;}

	public void setPhone(String phone){this.phone=phone;}
	public String getPhone(){return phone;}

	public void setAddress(String address){this.address=address;}
	public String getAddress(){return address;}

	public void setName(String name){this.name=name;}
	public String getName(){return name;}

	public void setAuthority(String authority){this.authority=authority;}
	public String getAuthority(){return authority;}
	
	public void setConsumption(double i){consumption=i;}
	public double getConsumption(){return consumption;}



	
	
	

}
