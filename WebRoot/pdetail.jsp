<%@ page language="java" import="java.util.*,bean.ProductBean,dao.ProductDao" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <script src="js/jquery-latest.js"></script>
    <base href="<%=basePath%>">
    
    <title>${product.pname }</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <section style="height:80%">
    <c:if test="${product.pid>0}">
    <input type="hidden" value=${product.pid } id="pid">
    <h1 style="text-align: center">${product.pname }</h1>
    <div>
    <span style="float: left"><img src="${product.img}" height="416px" width="816px"></span>
    <span style="text-align:center;font-size: 18px">
         
   	详细信息:<br>${product.detail}<br>
   	 价格:￥${product.price }<br><br>
   	VIP会员价:￥${product.price *(1-product.discount) }<br><br>
   	库存量:${product.stock}<br><br>
   	销售量:${product.sales }<br><br>
   	数量:<input type="text" value="1" name="buy_num" id="buy_num" style="width:40px;text-align:center">
   	<input type="button" value="加入到购物车" id="addtocart">
    </span>
    </div>
    </c:if>
    </section>
    
    
    <section>
    <h2 style="text-align:center">评论区</h2><hr>
   	
    <c:if test="${reviewlist.size()>0}">
    <c:forEach items="${reviewlist}" var="thisr">
    <div style="text-align:center"><span>${thisr.user}：</span><span>${thisr.review}</span></div>
    </c:forEach>
    </c:if>
    <c:if test="${reviewlist.size()<=0}">
          <h3 style="text-align:center">暂无评论</h3>
    </c:if>
    
    <c:if test="${uid!=null&&uid!=''}">
    <div style="text-align:center">
    <hr>
    <h5 style="text-align:center">发表评论</h5>
    <textarea rows="10%" cols="70%" id="newreview"></textarea>
    <button id="sendreview_btn">提交</button>
    </div>
    </c:if>
    </section>
    
    <script>
    $().ready(function(){
    	
    	$("#buy_num").keyup(function(){
    		$.ajax({
				type:"GET",
				dataType:"text",
				url: "CheckStock",
				data :{"pid":$("#pid").val() },
				success:function(data){
					
					if($("#buy_num").val()>Number(data))
						$("#buy_num").val(data);
				}});
     	    });
		$("#addtocart").click(function(){
    		if("${log}"!="in") 
    			alert("请回到首页登录");
    		else{
    			$.ajax({
    				type:"GET",
    				dataType:"text",
    				url: "AddtoCart",
    				data :{"uid":"${uid}","pid":$("#pid").val(),"quantity":$("#buy_num").val()},
    				success:function(data){
	    				if("true"==data){
	    					alert("添加成功");
	    					window.location.reload();
	    				}
    				}});
    		}
    	});
		$("#sendreview_btn").click(function(){
			var review=$("#newreview").val();
			var pid=$("#pid").val();
			var uid="${uid}";
			$.ajax({
				type:"POST",
				dataType:"text",
				url: "PostReview",
				data :{"uid":uid,"pid":pid,"review":review},
				success:function(data){
    				window.location.reload();
				}});
		});
		
    });
    </script>
  </body>
</html>
