package bean;

public class OrderBean {
	private int oid;
	private int pid;
	private int orderno;
	private int quantity;

	public void setOid(int c){oid=c;}
	public void setOrderno(int u){orderno=u;}
	public void setPid(int p){pid=p;}
	public void setQuantity(int q){quantity=q;}

	public int getOid(){return oid;}
	public int getOrderno(){return orderno;}
	public int getPid(){return pid;}
	public int getQuantity(){return quantity;}
}
