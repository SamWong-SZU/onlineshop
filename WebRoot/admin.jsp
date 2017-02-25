<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link rel="stylesheet" href="css/lanrenzhijia.css">
	<link rel="stylesheet" href="css/bo_table.css"/>
	<script src="js/jquery-latest.js"></script>
	<script src="js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
<style>
body{background-color:#778495}
</style>
  </head>
  
  <body >
  <div >
    <h1 align="center" style="color: black" >后台管理</h1>
  </div>
  <hr>
  	<div  >
	  <form name="loginform" id="loginform" method="post" action="AdminVerify">
	  <table style="width: 269px;position: absolute;left:41% ">
	  <tr><td>管理员：</td><td><input type="text" name="user" id="user" style="width: 157px; "></td></tr>  
	  <tr><td>密码：</td><td><input type="password" name="pwd" id="pwd" style="width: 157px; "></td>
	  <td><input type="submit"  value="登录"></td>
	  </tr>
	  
	  
	   </table>
	  
	  </form>
    </div>
  </body>
</html>
