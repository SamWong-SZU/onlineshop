<%@ page language="java" import="java.util.*,bean.CartBean" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(!"admin".equals(session.getAttribute("auth")))
	response.sendRedirect("admin.jsp");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/bo_table.css"/>
  </head>
  
  <body>
  
  商品  ${thispid } 销售订单如下:<br>
  <table class="bordered">
<c:if test="${Order.size()>0 }">
  <tr><th>订单号</th><th>用户id</th><th>数量</th><th>订单状态</th></tr>
    <c:forEach begin="0" end="${Order.size()-1}" step="1" var="i">
    <tr><td>${Order[i].orderno }</td>
    <td>${Orderuid[i] } </td>
    <td>${Order[i].quantity }</td>
    <td>
    <c:if test="${Orderstat[i]==0}">已取消</c:if>
    <c:if test="${Orderstat[i]==1}">未支付</c:if>
    <c:if test="${Orderstat[i]==2}">已支付</c:if>
    </td></tr>
    </c:forEach>
  </c:if>
  </table>
  </body>
</html>
