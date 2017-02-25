<%@ page language="java" import="java.util.*,bean.CartBean" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>查看我的购物车</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link rel="stylesheet" href="css/bo_table.css"/>
	<script src="js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="css/lanrenzhijia.css">
	<script src="js/jquery-latest.js"></script>
	
  </head>
  
  <body>
  <section style="background:#778495;height:50px"> 
  <div id="info" style="font-weight: bold" >
  <span style="float:right;font-size:18px;"><a href="javascript:void(0)" id="out_log" style="color: black;">退出登录</a></span>
  
  <span style="float:right;font-size:18px"><a href="javascript:void(0)" id="btn_order" style="color: black;">查看订单&nbsp;</a></span>
 
  <span style="float:right;font-size:18px"><a href="index.jsp" id="view_cart" style="color: black;">回到首页&nbsp;</a></span>
   
   欢迎回来,<%=session.getAttribute("user")%>
  ! 会员级别:<%=session.getAttribute("authority")%>
  </div>
  
    
  </section>
   <section style="background:#9BA398">
   <div style="text-align:center;font-weight: bold">我的购物车</div>
   </section>
 <table class="bordered" >
 <tr><th>图片</th><th>名称</th><th>价格</th><th>数量</th><th>小计</th></tr>
 
 <c:if test="${clist.size()>0 }">
 <c:forEach var="i" begin="0" end="${clist.size()-1}" step="1">
 
 	 
	 <tr>
	 <td><img src="${pd[i].img}" width="150px" height="100px"></td>
	 <td>${pd[i].pname }</td>
	 	 
	 <c:if test="${authority=='普通会员'}">
	 	<td >￥${pd[i].price}</td><input id="price_${i}" type="hidden" value="${pd[i].price}">
	 </c:if>
	 <c:if test="${authority=='钻石会员'}">
	 	<td>￥${pd[i].price *(1-pd[i].discount)}</td><input id="price_${i}" type="hidden" id="price_${i}" value="${pd[i].price *(1-pd[i].discount)}">
	 </c:if>
	 
	 <td>
	 <input type="hidden" value="${clist[i].cid}" class="hiddencid">
	 <input type="hidden" value="${pd[i].pid}" class="hiddenpid">
	 <input type="text" id="num_${i}" class="num" value="${clist[i].quantity}" style="width:40px;text-align:center">
	 </td>
	 <td id="subtotal_${i}" >0</td>
	 </tr>
  </c:forEach>
  </c:if>
   </table>
   
   <div style="text-align:center">
   <span id="total">总共:￥</span>
   <span style="float:right"><input type="button" value="下单" id="order_btn"></span>
   </div>
   <script>
   $().ready(function(){
		$("#out_log").click(function(){
			window.location.href="LogOut";
		});
		
		$("#btn_order").click(function(){
			window.location.href="ShowAllOrder";
		});
		
		
	   $("#order_btn").click(function(){
		   var uid="${uid}";
		   //先判断存货量是否足够
		   
		   $.ajax({
				type:"GET",
				dataType:"text",
				url: "OrderfromCart",
				data :{"uid":uid },
				success:function(data){

					if(data=="true"){
						alert("下单成功");
						location.reload();
					}
					else{
						alert("超出库存量,请重新输入数量");
						location.reload();
					}
						
				}});
	   });
	
	   $(".num").keyup(function(){
		   var s=$(this);
		   var thispid=$(this).prev(".hiddenpid").val();
		   
		   var cid=$(this).prev(".hiddenpid").prev(".hiddencid").val();
		   //先检测库存
		   //再 更新数据库的quantity		  
		   $.ajax({
				type:"GET",
				dataType:"text",
				url: "CheckStock",
				data :{"pid":thispid },
				success:function(data){
					if(s.val()>Number(data)) 
						s.val(data);
					subtotal();
					 $.ajax({
							type:"GET",
							dataType:"text",
							url: "UpdateQuantityfromCart",
							data :{"cid":cid,"quantity":s.val()}
					   });
				}});
		  	if(s.val()==0&&s.val()!=""){
		  		
			   if(confirm("是否移除该物品")){
				   s.blur();
				   $.ajax({
						type:"GET",
						dataType:"text",
						url: "DelformCart",
						data :{"cid":cid},
						success:function(data){
							
							location.reload();
						}});
			   }
		   }
	   });
	   
	   subtotal();
	  function subtotal(){
		  var total=0;
		  for(var i=0;i<"${clist.size()}";i++){
			  var k=Math.round($("#price_"+i).val()*$("#num_"+i).val()*10)/10;
			  $("#subtotal_"+i).html(k);
			  total+=k;
		  }
		  $("#total").html("总共:￥"+Math.round(total*10)/10);
	  }
	  
	  
	 
	  
	  
   });
   </script>
  </body>
</html>
