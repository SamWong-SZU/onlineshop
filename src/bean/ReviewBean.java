package bean;

public class ReviewBean {
	private int rid;
	private String user;
	private int pid;
	private String review;

	public int getRid(){return rid;}
	public int getPid(){return pid;}
	public String getUser(){return user;}
	public String getReview(){return review;}

	public void setRid(int r){rid=r;}
	public void setUser(String string){user=string;}
	public void setPid(int p){pid=p;}
	public void setReview(String r){review=r;}
	
}
