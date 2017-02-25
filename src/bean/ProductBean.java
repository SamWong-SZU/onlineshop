package bean;

public class ProductBean {
	private int pid;
	private String img;
	private String pname;
	private double price;
	private double discount;
	private int stock;
	private int sales;
	private String detail;
	
	
	
	public int getPid(){return pid;}
	public String getImg(){return img;}
	public String getPname(){return pname;}
	public double getPrice(){return price;}
	public double getDiscount(){return discount;}
	public int getStock(){return stock;}
	public int getSales(){return sales;}
	public String getDetail(){return detail;}
	
	public void setPid(int a){ this.pid=a;}
	public void setImg(String a){ this.img=a;}
	public void setPname(String a){ this.pname=a;}
	public void setPrice(double a){ this.price=a;}
	public void setDiscount(double a){ this.discount=a;}
	public void setStock(int a){ this.stock=a;}
	public void setSales(int a){ this.sales=a;}
	public void setDetail(String a){ detail=a;}
	public void setAll(int a,String b,String c,double d,double e,int f,int g,String h){
		setPid(a);
		setImg(b);
		setPname(c);
		setPrice(d);
		setDiscount(e);
		setStock(f);
		setSales(g);
		setDetail(h);
		
	}
}
