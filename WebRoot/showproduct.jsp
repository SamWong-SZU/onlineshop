<%@ page language="java" import="java.util.*,bean.ProductBean,dao.ProductDao" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showproduct.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/mall.css">
	<link href="css/mall.css" rel="stylesheet">
	<script src="js/jquery-latest.js"></script>
 </head>


  <body>
 
<c:choose>
<c:when test="${plist.size()!=0 }">
<div class="mainsection_right fl " >
  <ul>
  <c:forEach items="${plist}" var="p" >
  <li>
    <div class="border">
       <div class="section_img"><a href="Productdetail?pid=${p.pid}" title="${p.detail }" target="_blank" class="imgclick"><img src="${p.img }"><span class="bright" style="left: -280px;">&nbsp;</span></a></div>
          <div class="section_txt">
             <a href="Productdetail?pid=${p.pid}" title="${p.pname }" target="_blank" class="listword">${p.pname }</a>
                <span class="section_button"><i class="iconfont fontcart colorbrown"></i><a href="Productdetail?pid=${p.pid}" title="${p.pname }" target="_blank">买买买!</a></span>
                   <span class="real_price">￥${p.price }
                   <c:if test="${p.discount!=0 }">
                   <br>钻石价:￥${(1-p.discount)*p.price}</span>
                   </c:if>
          </div>
        </div>
  </li>

  </c:forEach>
  </ul></div>

  <div style="text-align:center;font-size:20px">
  <c:if test="${thispage !=1 }">
  <span><a href="GetProduct?smin=${min}&smax=${max}&sword=${word}&page=${thispage-1}">上一页</a></span>&nbsp;&nbsp;&nbsp;
  </c:if>

  <c:if test="${thispage !=endpage }">
  <span><a href="GetProduct?smin=${min}&smax=${max}&sword=${word}&page=${thispage+1}" >下一页</a></span></div>
  </c:if>
</c:when>
<c:otherwise>
<div style="test-align:center;font-size:20px">无相关商品</div>
</c:otherwise>
</c:choose>


  </body>
</html>
