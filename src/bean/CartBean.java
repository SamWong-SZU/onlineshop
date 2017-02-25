package bean;

public class CartBean {

	private int cid;
	private int pid;
	private int uid;
	private int quantity;

	public void setCid(int c){cid=c;}
	public void setUid(int u){uid=u;}
	public void setPid(int p){pid=p;}
	public void setQuantity(int q){quantity=q;}

	public int getCid(){return cid;}
	public int getUid(){return uid;}
	public int getPid(){return pid;}
	public int getQuantity(){return quantity;}
}
