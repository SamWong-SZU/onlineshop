<%@ page language="java" import="java.util.*,dao.*,bean.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(!"admin".equals(session.getAttribute("auth")))
		response.sendRedirect("admin.jsp");
ProductDao pDao=new ProductDao();
UserDao uDao=new UserDao();
OrderDao oDao=new OrderDao();
ArrayList<Integer> statlist=new ArrayList<Integer>();
ArrayList<ProductBean> Plist=pDao.getAllProduct();
ArrayList<UserBean> Ulist=uDao.getAllUser();
ArrayList<OrderBean> Olist=oDao.getAllOrder(statlist);

session.setAttribute("statlist",statlist);
session.setAttribute("Plist",Plist);
session.setAttribute("Ulist",Ulist);
session.setAttribute("Olist",Olist);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link rel="stylesheet" href="css/lanrenzhijia.css">
	<link rel="stylesheet" href="css/bo_table.css"/>
	
	
	<script src="js/jquery-latest.js"></script>
	<script src="js/jquery.js"></script>
	

  </head>
  
  <body>
   
   <section style="background:#9BA398;text-align:center"> 
   
	<h2>后台数据管理 </h2>
	<br>
	<div style="text-align:center">
	<button id="product_btn">商品管理</button>
	<button id="user_btn">会员管理</button>
	<div align="right" ><a href="LogOut" style="color: black;font-weight:bold">Exit</a></div>
	</div>
	</section>
    <div class="box">
   
	<center>
	<!-- 商品管理section -->
	<section style="display:none" id="section_product">
	 <!-- 添加商品表单 hidden -->
		<section style="display:none" id="addproduct_form">
		<form id="addp_form" action="AdminAddProduct" method="post" enctype="multipart/form-data">
		<table style="text-align:center" >
		<tr><td>图片地址:</td><td style="height: 26px;width:199px"><input type="file" name="img" value="浏览"></td></tr>
		<tr><td>商品名称:</td><td><input type="text" name="name"></td></tr>
		<tr><td>商品价格:</td><td><input type="text" name="price"></td></tr>
		<tr><td>折扣率:</td><td><input type="text" name="discount"></td></tr>
		<tr><td>商品描述:</td><td><input type="text" name="detail"></td></tr>
		<tr><td>库存量:</td><td><input type="text" name="stock"></td><td><button id="add_product_btn">添加</button></td></tr>
		
		</table>
		</form>	
		</section>
	<!--商品表单 hidden end  -->
	
		<table class="bordered">
		<tr><th id="add_product">+添加新商品</th></tr>
		</table>
		
		
		
		<table  class="bordered" >
		<tr>
		<th>商品编号</th>
		<th>图片</th>
		<th>商品名称</th>
		<th>商品价格</th>
		<th>钻石会员折扣率</th>
		<th>商品描述</th>
		<th>库存量</th>
		<th>销售量</th>
		<th>删除</th>
		</tr>
		
		<c:forEach items="${Plist}" var="p">
		<tr>
		<td>${p.pid}</td>
		<td><img src="${p.img}" height="50px" width="100px"></td>
		<td class="t_name" style="width:200px" >${p.pname}</td>
		<td class="t_price" >${p.price}</td>
		<td class="t_discount" >${p.discount}</td>
		<td class="t_detail" >${p.detail}</td>
		<td class="t_stock" >${p.stock }</td>
		<td > ${p.sales }</td>
		<td><button class="t_del">删除</button><button class="t_query">查单</button></td>
		</tr>
		</c:forEach>
		</table >
	</section>
   <!-- 商品管理section end -->
   
   <!-- 会员管理section  -->
   <section id="section_user" style="display:none">
   	<button id="vip_btn">查看钻石会员</button> <button id="nonevip_btn">查看普通会员</button> <button id="all_btn">查看所有普通会员</button>
   	<section id="vip" style="display:none">
    <table class="bordered">
    <tr><th>uID</th><th>user</th><th>phone</th><th>address</th><th>name</th><th>authority</th><th>stat</th><th>consumption</th><th></th></del></tr>
    <c:forEach items="${Ulist}" var="u"> 
    <c:if test="${u.authority=='钻石会员'}">
    <tr > <td>${u.uid}</td> <td>${u.user}</td> <td>${u.phone}</td> <td>${u.address}</td> <td>${u.name}</td>  <td>${u.authority}</td>
    <td>
    <c:if test="${u.stat==1}">已激活</c:if><c:if test="${u.stat==0}">未激活</c:if>
    </td>  
    <td>${u.consumption}</td> 
    <td><button class="checkuser_btn">订单</button><button class="deluser_btn">删除</button></td></tr>
    </c:if>
    </c:forEach>
    </table>
    </section>
    
    <section id="nonevip" style="display:none">
    <table class="bordered">
    <tr><th>uID</th><th>user</th><th>phone</th><th>address</th><th>name</th><th>authority</th><th>stat</th><th>consumption</th><th></th></del></tr>
    <c:forEach items="${Ulist}" var="u"> 
    <c:if test="${u.authority!='钻石会员'}">
    <tr > <td>${u.uid}</td> <td>${u.user}</td> <td>${u.phone}</td> <td>${u.address}</td> <td>${u.name}</td>  <td>${u.authority}</td>
    <td>
    <c:if test="${u.stat==1}">已激活</c:if><c:if test="${u.stat==0}">未激活</c:if>
    </td>  
    <td>${u.consumption}</td> 
    <td><button class="checkuser_btn">订单</button><button class="deluser_btn">删除</button></td></tr>
    </c:if>
    </c:forEach>
    </table>
    </section>
    
    <section id="all" style="display:none">
    <table class="bordered">
    <tr><th>uID</th><th>user</th><th>phone</th><th>address</th><th>name</th><th>authority</th><th>stat</th><th>consumption</th><th></th></del></tr>
    <c:forEach items="${Ulist}" var="u"> 
    
    <tr > <td>${u.uid}</td> <td>${u.user}</td> <td>${u.phone}</td> <td>${u.address}</td> <td>${u.name}</td>  <td>${u.authority}</td>
    <td>
    <c:if test="${u.stat==1}">已激活</c:if><c:if test="${u.stat==0}">未激活</c:if>
    </td>  
    <td>${u.consumption}</td> 
    <td><button class="checkuser_btn">订单</button> <button class="deluser_btn">删除</button></td></tr>
    
    </c:forEach>
    </table>
    </section>
    
   </section>
    <!-- 会员管理section end -->
   
   
<script type="text/javascript">
	$().ready(function(){
		//删除商品按钮
		$(".t_del").click(function(){
			var pid=$(this).parent().parent("tr").find("td:first").html();
			if(confirm("是否确认删除编号为 "+pid+" 的商品"))
				$.ajax({
					type:"POST",
					dataType:"text",
					url: "AdminUpdateProduct",
					data :{"change":"del","pid":pid},
					success:function(data){
						
						location.reload();
					}});
			
		});
		//查询按钮
		$(".t_query").click(function(){
			var pid=$(this).parent().parent("tr").find("td:first").html();
			window.open("AdminQueryOrderByPid?pid="+pid);
		});
		
		//商品修改
		$(".t_name").dblclick(function(){
			var pid=$(this).parent("tr").find("td:first").html();
			var thisname=$(this).html();
			var name;
			if(name=prompt("修改名称为:",thisname)){
			$.ajax({
				type:"POST",
				dataType:"text",
				url: "AdminUpdateProduct",
				data :{"change":"name","pid":pid,"name":name},
				success:function(data){
					
					location.reload();
				}});
			}
		});
		
		$(".t_price").dblclick(function(){
			var pid=$(this).parent("tr").find("td:first").html();
			var thisprice=$(this).html();
			var v;
			if(v=prompt("修改 价格为:",thisprice)){
			$.ajax({
				type:"POST",
				dataType:"text",
				url: "AdminUpdateProduct",
				data :{"change":"price","pid":pid,"price":v},
				success:function(data){
					location.reload();
				}});
			}	
		});
		$(".t_discount").dblclick(function(){
			var pid=$(this).parent("tr").find("td:first").html();
			var t=$(this).html();
			var v;
			if(v=prompt("修改 折扣率为:",t)){
			$.ajax({
				type:"POST",
				dataType:"text",
				url: "AdminUpdateProduct",
				data :{"change":"discount","pid":pid,"discount":v},
				success:function(data){
					location.reload();
				}});
			}	
		});
		
		$(".t_detail").dblclick(function(){
			var pid=$(this).parent("tr").find("td:first").html();
			var t=$(this).html();
			var v;
			if(v=prompt("修改 描述为:",t)){
			$.ajax({
				type:"POST",
				dataType:"text",
				url: "AdminUpdateProduct",
				data :{"change":"detail","pid":pid,"detail":v},
				success:function(data){
					location.reload();
				}});
			}	
		});
		
		$(".t_stock").dblclick(function(){
			var pid=$(this).parent("tr").find("td:first").html();
			var t=$(this).html();
			var v;
			if(v=prompt("修改 库存量为:",t)){
			$.ajax({
				type:"POST",
				dataType:"text",
				url: "AdminUpdateProduct",
				data :{"change":"stock","pid":pid,"stock":v},
				success:function(data){
					location.reload();
				}});
			}	
		});
		//添加商品
		$("#add_product").click(function(){
			if($("#addproduct_form").css("display")=="block")
				$("#addproduct_form").css("display","none");
			else
				$("#addproduct_form").css("display","block");
		});
		
		$("#add_product_btn").click(function(){
			$("#addp_form").submit();
		});
		$("#user_btn").click(function(){
			$("#section_product").css("display","none");
			$("section_order").css("display","none");
			$("#section_user").css("display","block");
		});
		$("#product_btn").click(function(){
			$("#section_user").css("display","none");
			$("section_order").css("display","none");
			$("#section_product").css("display","block");
		});
		$(".deluser_btn").click(function(){
			var uid=$(this).parent().parent("tr").find("td:first").html();
			
			$.ajax({
				type:"POST",
				dataType:"text",
				url: "AdminDelUser",
				data :{"uid":uid},
				success:function(data){
					location.reload();
				}});
				
		});
		$(".checkuser_btn").click(function(){
			var uid=$(this).parent().parent("tr").find("td:first").html();
			
			$.ajax({
				type:"POST",
				dataType:"text",
				url: "AdminCheckUserOrder",
				data :{"uid":uid},
				success:function(data){
					window.open("AdminviewUSERorder.jsp");
				}});
				
		});
		$("#vip_btn").click(function(){
			$("#vip").css("display","block");
			$("#nonevip").css("display","none");
			$("#all").css("display","none");
		});
		$("#nonevip_btn").click(function(){
			$("#vip").css("display","none");
			$("#nonevip").css("display","block");
			$("#all").css("display","none");
		});
		$("#all_btn").click(function(){
			$("#vip").css("display","none");
			$("#nonevip").css("display","none");
			$("#all").css("display","block");
		});
		
	});

	
	
</script>
</center>
		
            
		
</div>
</body>
</html> 