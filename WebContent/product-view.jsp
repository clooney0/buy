<%@page import="java.util.ArrayList"%>
<%@page import="com.cn.easybuy.dao.*"%>
<%@page import="com.cn.easybuy.dao.impl.*"%>
<%@page import="com.cn.easybuy.entity.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.png" /></div>
	<%
		User user = (User)session.getAttribute("user");
		if(user!=null){
		%>
		<div align="right" id="welcome">欢迎回来！亲爱的${user.getName()}</div>
		<%}%>
<div class="help">
	<a href="shopping.jsp" id="shoppingBag" class="shopping">购物车X件</a><a href="login.jsp">登录</a><a href="loginOut.jsp">注销</a><a href="register.jsp">注册</a><a href="guestbook.jsp">留言</a><a href="manage/index.jsp">后台管理</a>
	</div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="#">首页</a></li>
			<li><a href="#">图书</a></li>
			<li><a href="#">百货</a></li>
			<li><a href="#">品牌</a></li>
			<li><a href="#">促销</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			<li class="first"><a href="#">音乐</a></li>
			<li><a href="#">影视</a></li>
			<li><a href="#">少儿</a></li>
			<li><a href="#">动漫</a></li>
			<li><a href="#">小说</a></li>
			<li><a href="#">外语</a></li>
			<li><a href="#">数码相机</a></li>
			<li><a href="#">笔记本</a></li>
			<li><a href="#">羽绒服</a></li>
			<li><a href="#">秋冬靴</a></li>
			<li><a href="#">运动鞋</a></li>
			<li><a href="#">美容护肤</a></li>
			<li><a href="#">家纺用品</a></li>
			<li><a href="#">婴幼奶粉</a></li>
			<li><a href="#">饰品</a></li>
			<li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; <a href="product-list.jsp">图书音像</a> &gt; 图书
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<dl>
				<%
					ProductParentDao parentDao = new ProductParentDaoImpl();
					ProductCategoryDao caregoryDao = new ProductCategoryDaoImpl();
					List<ProductParent> parents = parentDao.getAllParent();
					List<ProductCategory> categorys = caregoryDao.getAllCategory();
					
					for(ProductParent parent : parents){
				%>
				<dt><%=parent.getParentName() %></dt>
					<%
						for(ProductCategory category : categorys){
							if(parent.getParentID() == category.getParentID()){
					%>
					<dd><a href="product-list.jsp?category=<%=category.getId()%>"><%=category.getName() %></a></dd>
				<%
							}
						}
					}
				%>
			</dl>
		</div>
	</div>
	<div id="product" class="main">
		<%
			String productID = request.getParameter("productID");
			int id = 0;
			if(!productID.equals("")){
				id = Integer.valueOf(productID);
			}
			ProductDao pDao = new ProductDaoImpl();
			Product product = pDao.getProductByID(id);
			List<Product> pros = (List<Product>)session.getAttribute("pros");
			if(pros == null){
				pros = new ArrayList<Product>();
			}
			if(pros.size()>2){
				pros.remove(0);
			}
			pros.add(product);
			session.setAttribute("pros", pros);
		%>
		<h1><%=product.getName() %></h1>
		<div class="infos">
			<div class="thumb"><img src="<%=product.getFileName() %>" width="110" height="106" /></div>
			<div class="buy">
				商城价：<span class="price">￥<%=product.getPrice() %></span><br />
				库　存：<%=product.getStock() %>
			  <div class="button"><input type="button" name="button" value="" onclick="location.href = 'addCart?epid=<%=product.getId() %>'" /><a href="shopping.html">放入购物车</a></div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="introduce">
			<h2><strong>商品详情</strong></h2>
			<div class="text">
				<%=product.getName() %><br />
				<%=product.getDescription() %><br />
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
