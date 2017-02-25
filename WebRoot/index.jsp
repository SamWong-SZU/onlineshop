<%@ page language="java" import="java.util.*,bean.*,dao.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>SAM's SHOP</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<script src="js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="css/lanrenzhijia.css">
	<script src="js/jquery-latest.js"></script>
	
	
  </head>
  <body>
  <%
	Cookie cookie=new Cookie("log",(String)session.getAttribute("log"));
	response.addCookie(cookie);
	ArrayList<ProductBean> plist=new ProductDao().getAllProduct();
	application.setAttribute("plist",plist);
%>
 <section style="background:#778495;"> 
  <div id="log_div" style="text-align:center">
  <span ><a href="javascript:void(0)" id="click_log" style="color: black;font-weight: bold">登录</a></span>
  <span ><a href="javascript:void(0)" id="click_reg" style="color: black;font-weight: bold">注册</a></span>
  </div>
  
  <!-- 隐藏的用户信息  登录后显示start -->
  <div id="info" style="display:none;font-weight: bold" >
   欢迎回来,<%=session.getAttribute("user")%>
  ! 会员级别:<%=session.getAttribute("authority")%>
    <%
    
    if("普通会员".equals(session.getAttribute("authority")))
    { 
    	String a=session.getAttribute("consumption").toString();
    	double b=Double.parseDouble(a);
    out.print("	升级钻石会员还需￥:"+(10000-b));
    }
    %> 
  
  <span style="float:right;font-size:18px;"><a href="javascript:void(0)" id="out_log" style="color: black;">退出登录</a></span>
  
  <span style="float:right;font-size:18px"><a href="javascript:void(0)" id="btn_order" style="color: black;">查看订单&nbsp;</a></span>
 
  <span style="float:right;font-size:18px"><a href="MyCart" id="view_cart" style="color: black;">查看购物车&nbsp;</a></span>
  
  </div>
  
  
  <!-- 隐藏的用户信息  登录后显示 end-->
  
  
  <!-- 隐藏的登录表单 start -->
  <div style="display: none;margin-left: 40%" id="logindiv" >
  <form name="loginform" id="loginform" method="post" action="LoginVerify">
  <table style="width: 269px; ">
  <tr><td>用户名：</td><td><input type="text" name="user" id="user" style="width: 157px; "></td></tr>
  <tr><td>密码：</td><td><input type="password" name="pwd" id="pwd" style="width: 157px; "></td></tr>
   </table>
  <div>
  <input type="checkbox" name="remeberuser"  value="1" />记住用户名
  <input type="checkbox" name="autolog"  value="1" />自动登录
   </div>
   <div  style="width: 260px;text-align: right"> <input type="submit"  value="登录"></div>
  </form>
  </div>
  <!-- 隐藏的登录表单 end -->

<!--  隐藏的注册表单start -->
 <div style="display:none;margin-left: 40%" id="regdiv" >
  <form name="regform" id="regform" method="post" action="Register">
  <table style="width: 450px; ">
  <tr><td>用户名：</td><td><input type="text" name="reguser" id="reguser" style="width: 157px; "></td><td style="width:100px" id="regu"></td></tr>
  <tr><td>密码：</td><td><input type="password" name="regpwd" id="regpwd" style="width: 157px; "></td></tr>
  <tr><td>确认密码：</td><td><input type="password" name="repwd" id="repwd" style="width: 157px; "></td><td style="width:100px" id="regp"></td></tr>
  <tr><td>手机号码：</td><td><input type="text" name="regphone" id="regphone" style="width: 157px; "></td></tr>
  <tr><td>邮箱(需验证)：</td><td><input type="email" name="regemail" id="regemail" style="width: 157px; "></td><td style="width:100px" id="rege"></td></tr>
  <tr><td>收货人姓名：</td><td><input type="text" name="regname" id="regname" style="width: 157px; "></td></tr>
  <tr><td>地址：</td><td><input type="text" name="regaddress" id="regaddress" style="width: 157px; "></td></tr>
  <tr><td>验证码：</td><td><input type="text" name="regcode" id="regcode" style="width: 157px; "></td><td style="width:100px" id="regc"></td></tr>
  
  
   </table>
   
   <div  style="width: 220px;text-align: right;"> <input type="button"  id="reg_btn" value="注册"></div>
  </form>
  </div>
<!--  隐藏的注册表单end -->
</section>

<section class="home">
	<div class="intro">
		<div id="home" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#home" data-slide-to="0" class="active"></li>
				<li data-target="#home" data-slide-to="1"></li>
				<li data-target="#home" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<div class="container">
						<div class="row">
							<div class="col-sm-7">
								<div class="intro-content">
									<h1>SAMSUNG NOTEBOOK </h1>
									<h2> <span class="highlight">￥3???.99</span></h2>
									<p>SAMSUNG NOTEBOOK i5四核CPU 双十二震撼价,准备发售!</p>	
								</div>
							</div>
							<div class="col-sm-5">
								<img class="img-responsive" src="images/notebook.png" height="742" alt="" />
							</div>	
						</div>	
				    </div>	
				</div>
				<div class="item">
					<div class="container">
						<div class="row">
							<div class="col-sm-7">
								<div class="intro-content">
									<h1>SURFACE PRO4 </h1>
									<h2> <span class="highlight">￥4?88.99</span></h2>
									<p>MICROSOFT SURFACE PRO4 M3处理器64G 双十二震撼价,准备发售!</p>
								</div>
							</div>
							<div class="col-sm-5">
								<img class="img-responsive" src="images/surface.png" alt="" />
							</div>	
						</div>	
					</div>	
				</div>
				<div class="item">
					<div class="container">
						<div class="row">
							<div class="col-sm-7">
								<div class="intro-content">
									<h1>APPLE IPHONE6 </h1>
									<h2> <span class="highlight">￥4?99.00</span></h2>
									<p>APPLE IPHONE6 128G 双十二震撼价,准备发售!</p>				
								</div>
							</div>
							<div class="col-sm-5">
								<img class="img-responsive" src="images/iphone.png" style="height:300px;width:260px" alt="" />
							</div>	
						</div>	
					</div>	
				</div>						
<!-- Controls -->
			<a class="left carousel-control" href="#home" role="button" data-slide="prev"><i class="fa fa-angle-left"></i></a>
			<a class="right carousel-control" href="#home" role="button" data-slide="next"><i class="fa fa-angle-right"></i></a>
			</div>
		</div>
	</div>
</section>	
		
<!-- 搜索栏 -->
<section style="background:#9BA398">
<div style="text-align:center;margin: 5px">
<form name="searchform" method="get" action="GetProduct" target="showarea">
搜索商品：
<input type="text" name="sword" value="" placeholder="请输入关键词"  id="sword" style="height:25px;font-size:14px"> 
价格区间：
<input type="text" name="smin" value="" style="width:45px;height:25px;font-size:14px">
- <input type="text" name="smax" value="" style="width:45px;height:25px;font-size:14px">
&nbsp;<input type="submit" value="搜索">
</form>
</div>

</section>
<!-- 商品展示区 -->
<iframe name="showarea" src="GetProduct" width="100%" height="100%" border="0">

</iframe>


<!-- 商品展示区end -->
<script type="text/javascript">
	$(document).ready(function(){
		var code1;
		var code2;
		//查看是否已登录(session 和cookies中存入标志值)
		if("in"==getCookie("log")){
			$("#log_div").remove();
			$("#info").css("display","block");
		}
		else
		{
			if("wrong"!=getCookie("log")&&getCookie("log")!=null)
			{
				autolog();
			}
				
		}
		//以上打开网页即运行
//=================================================================================================//
		//cookies有保存帐号密码 则自动登录
		function autolog(){
			var u=getCookie("user");
			var p=getCookie("pwd");
			
			if(u!=null){
				$("#user").val(u);
				if(p!=null){
					$("#pwd").val(p);
					$("#loginform").submit();
				}
			}
		}
		//获取cookie函数
		function getCookie(cookie_name)
		{
		    var allcookies = document.cookie;
		    var cookie_pos = allcookies.indexOf(cookie_name);   //索引的长度
		    var value=null;
		    // 如果找到了索引，就代表cookie存在，
		    // 反之，就说明不存在。
		    if (cookie_pos != -1)
		    {
		    	
		        // 把cookie_pos放在值的开始，只要给值加1即可。
		        cookie_pos += cookie_name.length + 1;      //这里容易出问题，所以请大家参考的时候自己好好研究一下
		        var cookie_end = allcookies.indexOf(";", cookie_pos);
		  
		        if (cookie_end == -1)
		        {
		            cookie_end = allcookies.length;
		        }
		  
		         value = unescape(allcookies.substring(cookie_pos, cookie_end));         //这里就可以得到你想要的cookie的值了。。。
		    }
		    return value;
		}
    	
		$("#click_log").click(function(){
			$("#logindiv").css("display","block");
			$("#regdiv").css("display","none");
		});
		$("#click_reg").click(function(){
			$("#logindiv").css("display","none");
			$("#regdiv").css("display","block");
			code1=Math.ceil(Math.random()*100);
			code2=Math.ceil(Math.random()*100);
			$("#regc").html("<span style='background:#aaaaaa;' onselectstart='return false;'>"+code1+"+"+code2+"=?</span>");
			
		});
		
		$("#out_log").click(function(){
			window.location.href="LogOut";
		});
		$("#btn_order").click(function(){
			window.location.href="ShowAllOrder";
		});
		$("#reguser").keyup(function(){
			$.ajax({
			type:"GET",
			dataType:"text",
			url: "Checkuser",
			data :{"reguser":$("#reguser").val()} ,
			success:function(data){
			
			if(data=="true"){
				$("#regu").html("<font style='font-color:#ff0000;font-weight:bold'>用户名已存在</font>")
				$("#reg_btn").attr("disabled","disabled");
				}
			else{
				$("#regu").text("");
				$("#reg_btn").removeAttr("disabled");
			}
			}});		 
	
		});
		

		$("#repwd,#regpwd").keyup(function(){
			if($("#regpwd").val()!=$("#repwd").val()){
				$("#regp").html("<font style='font-color:#ff0000;font-weight:bold'>密码不相同</font>");
				$("#reg_btn").attr("disabled","disabled");
				}
			else{
				$("#regp").text("");
				$("#reg_btn").removeAttr("disabled");
				}
		});
		$("#reg_btn").click(function(){
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			var f=true;var f2=true;var f3=false;f4=false;
			if($("#reguser").val()==null||$("#reguser").val()==""){
				$("#regu").html("<font style='font-color:#ff0000;font-weight:bold'>用户名不能为空</font>");
				$("#reguser").focus();
				f=false;
			}
			if($("#regpwd").val()==null||$("#regpwd").val()==""){
				$("#regp").html("<font style='font-color:#ff0000;font-weight:bold'>密码不能为空</font>");
				$("#regpwd").focus();
				f2=false;
			}
			if($("#repwd").val()==null||$("#repwd").val()==""){
				$("#regp").html("<font style='font-color:#ff0000;font-weight:bold'>密码不能为空</font>");
				$("#repwd").focus();
				f2=false;
			}
			if($("#regpwd").val()!=$("#repwd").val()){
				$("#regp").html("<font style='font-color:#ff0000;font-weight:bold'>密码不相同</font>");
				f2=false;
			}
			if((code1+code2)==$("#regcode").val())
				f3=true;
			else
				alert("验证码错误");
			if(myreg.test($("#regemail").val())){
				f4=true;
				$("#rege").html("");
				}
			else
				$("#rege").html("<font style='font-color:#ff0000;font-weight:bold'>邮箱错误</font>");
			if(f&&f2&&f3&&f4)
				$("#regform").submit();
		});
		
		
	
	
	});
</script>

  </body>
  
</html>
