<%@ page language="java" import="java.util.*,bean.CartBean" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>查看我的订单</title>
    
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
  
 
 <span style="float:right;font-size:18px"><a href="MyCart"  style="color: black;">购物车&nbsp;</a></span>
  <span style="float:right;font-size:18px"><a href="index.jsp" id="view_cart" style="color: black;">回到首页&nbsp;</a></span>
  
   
   欢迎回来,<%=session.getAttribute("user")%>
  ! 会员级别:<%=session.getAttribute("authority")%>
  </div>
  
    
  </section>
   <section style="background:#9BA398">
   <div style="text-align:center;font-weight: bold">我的订单</div>
   </section>
   <button style="float:right" id="express_btn">查看物流</button>
   <br>
	<br>
	<c:if test="${map.size()>0}">
   <c:forEach begin="0" end="${map.size()-1}" step="1" var="i">
   订单号:${key[i]} &nbsp;&nbsp;&nbsp;状态:
   <input type="hidden" value="${key[i]}" id="hidden_no_${i}">
   <c:if test="${stat[i]==0}">
   未支付
   <span style="float:right;padding-left: 10px"><button class="del_order" value="${key[i]}">删除订单</button></span>
   <span style="float:right"><button class="pay_now" value="${key[i]}">马上支付</button></span>
   </c:if>
   <c:if test="${stat[i]==1}">已支付</c:if>
   <c:if test="${stat[i]==2}">已删除</c:if>
   &nbsp;&nbsp;&nbsp;
   <span >总价:￥<span id="total_${i}"></span></span>
   <input type="hidden" id="hiddentotal_${key[i]}" value="">
   <br>
   
   
   <table class="bordered" id="table_${i}">
   		<th>商品</th><th>单价</th><th>数量</th><th>小计</th>
   		<c:if test="${ppp[i].size()>0 }">
   		<c:forEach begin="0" end="${ppp[i].size()-1}" step="1" var="j">
		   	 <tr>
			 <td><a href="Productdetail?pid=${ppp[i][j].pid}" target="_blank">${ppp[i][j].pname}</a></td>
			 
			 <c:if test="${authority=='普通会员'}">
	 			<td >￥${ppp[i][j].price}</td><input id="price_${i}" type="hidden" value="${ppp[i][j].price}">
	 		</c:if>
	 		<c:if test="${authority=='钻石会员'}">
	 			<td>￥${ppp[i][j].price *(1-ppp[i][j].discount)}</td><input id="price_${i}" type="hidden" id="price_${i}" value="${ppp[i][j].price *(1-ppp[i][j].discount)}">
	 		</c:if>
			 
			 <td>${map[key[i]][j].quantity}</td> 
			 
			  <c:if test="${authority=='普通会员'}">
	 			<td id="subtotal_${i}${j}">${ppp[i][j].price *map[key[i]][j].quantity}</td>
	 		</c:if>
	 		<c:if test="${authority=='钻石会员'}">
	 			<td id="subtotal_${i}${j}">${ppp[i][j].price *(1-ppp[i][j].discount)*map[key[i]][j].quantity}</td>
	 		</c:if>
	 		
			 </tr>
   		</c:forEach>
   		</c:if>
   </table><br><br>
   </c:forEach>
   </c:if>
   
   <script>
   $().ready(function(){
		$("#out_log").click(function(){
			window.location.href="LogOut";
		});
		$("#express_btn").click(function(){
			window.open("CheckExpress");
		});
		
		
		$(".del_order").click(function(){
			
			var orderno=Number($(this).val());
			$.ajax({
			type:"GET",
			dataType:"text",
			url: "DelOrder",
			data :{"orderno":orderno} ,
			success:function(data){
				location.reload();
		   }});		 
	
		});

		$(".pay_now").click(function(){

			var orderno=Number($(this).val());
			$.ajax({
			type:"GET",
			dataType:"text",
			url: "PayOrder",
			data :{"orderno":orderno,consumption:$("#hiddentotal_"+orderno).val()} ,
			success:function(data){
				location.reload();
		   }});		 
	
		});
		
	   subtotal();
	   for(var i=0;i<Number("${map.size()}");i++){
		  $("#hiddentotal_"+$("#hidden_no_"+i).val()).val($("#total_"+i).html());
	   }
	  function subtotal(){
		 
		  for(var i=0;i<Number("${map.size()}");i++){
			  var total=0;
			  for(var j=0;j<$("#table_"+i).find("tr").length-1;j++){
				  k=Number($("#subtotal_"+i+j).html());
				  total+=k;	
				 }
			
			  $("#total_"+i).html(total);
			  
			 
		  }
	  }
	  
	  
	 
	  
	  
   });
   </script>
  </body>
</html>
