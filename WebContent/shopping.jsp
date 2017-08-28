<%@page import="com.cn.easybuy.entity.Product"%>
<%@page import="com.cn.easybuy.dao.impl.ProductDaoImpl"%>
<%@page import="com.cn.easybuy.dao.ProductDao"%>
<%@page import="com.cn.easybuy.entity.Cart"%>
<%@page import="com.cn.easybuy.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.cn.easybuy.dao.impl.CartDaoImpl"%>
<%@page import="com.cn.easybuy.dao.CartDao"%>
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
<script type="text/javascript">
function name() {
	var data= $("#number").val();
	document.getElementById("number2").value = data;
}
</script>
</head>
<body>
<%
	User user = (User)session.getAttribute("user");	
if(user==null){
	response.sendRedirect("login.jsp?error=Please login first");
}else{
%>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.png" /></div>
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
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<form action="address.html">
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<%
					CartDao cDao = new CartDaoImpl();
					ProductDao pDao = new ProductDaoImpl();
					List<Cart> carts = cDao.getAllCartByUser(user);
					double sum = 0;
					for(Cart cart: carts){
						Product p = pDao.getProductByID(cart.getEpID());
						sum += p.getPrice();
						%>
					  <tr id="product_id_0">
	                    <td class="thumb"><img width="10%" src="<%=p.getFileName() %>" /><a href="product-view.jsp?productID=<%=p.getId() %>"><%=p.getName() %></a></td>
	                    <td class="price" id="price_id_1">
	                        <span>￥<%=p.getPrice() %></span>
	                        <input type="hidden" id="price" value="<%=p.getPrice() %>" />
	                    </td>
	                    <td class="number">
	                        <input id="number_id_1" type="text" name="number" value="<%=cart.getEpQuantity() %>" />
	                    </td>
	                    <td class="delete"><a href="updateCartPrice?cartid=<%=cart.getId()%>&number=1">+</a></td>
	                    <%if(cart.getEpQuantity()!=1){ %>
	                    <td class="delete"><a href="updateCartPrice?cartid=<%=cart.getId()%>&number=2">-</a></td>
	                    <%}else{ %>
	                    <td class="delete"><a href="#">-</a></td>
	                    <%} %>
	                    <td class="delete"><a href="javascript:void(0)">删除</a></td>
	                </tr>
				  <% }%>
				  	</table>
            <div class="total"><span >总计：￥<%=sum %></span></div>
			<div class="button"><input type="submit" value="" /></div>
		</form>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大 All Rights Reserved. 京ICP证1000001号
</div>
<%} %>
</body>
</html>
