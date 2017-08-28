<%@page import="com.cn.easybuy.entity.ProductParent"%>
<%@page import="com.cn.easybuy.dao.impl.ProductParentDaoImpl"%>
<%@page import="com.cn.easybuy.dao.ProductParentDao"%>
<%@page import="com.cn.easybuy.entity.ProductCategory"%>
<%@page import="java.util.List"%>
<%@page import="com.cn.easybuy.dao.impl.ProductCategoryDaoImpl"%>
<%@page import="com.cn.easybuy.dao.ProductCategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../scripts/function.js"></script>
<script type="text/javascript">
	function del() {
		 if(!confirm("确认删除?"))
			window.event.returnValue=false;  

	}
	
</script>

</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="../images/logo.png" /></div>
	<div class="help"><a href="../index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="index.jsp">首页</a></li>
			<li><a href="user.jsp">用户</a></li>
			<li class="current"><a href="product.jsp">商品</a></li>
			<li><a href="order.jsp">订单</a></li>
			<li><a href="guestbook.jsp">留言</a></li>
			<li><a href="news.jsp">新闻</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<jsp:include page="date.jsp" />
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><a href="user.jsp">用户管理</a></dd>
			  <dt>商品信息</dt>
				<dd><em><a href="productClass-add.jsp">新增</a></em><a href="productClass.jsp">分类管理</a></dd>
				<dd><em><a href="product-add.jsp">新增</a></em><a href="product.jsp">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="order.jsp">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="guestbook.jsp">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="news-add.jsp">新增</a></em><a href="news.jsp">新闻管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>分类管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>编号</th>
					<th>分类名称</th>
					<th>操作</th>
				</tr>
				<%
					ProductParentDao ppd = new ProductParentDaoImpl();
					List<ProductParent> li = ppd.getAllParent();
				    ProductCategoryDao pcd = new ProductCategoryDaoImpl();
					List<ProductCategory> list = pcd.getAllCategory();
					
					for(ProductParent lpp : li ) {		
				%>
				
				<tr>
					<td class="first w4 c"><%=lpp.getParentID() %></td>
					<td><%=lpp.getParentName() %></td>
					<td class="w1 c"><a href="productClass-modify.jsp?pid=<%=lpp.getParentID()%>">修改</a> 
					<a  class="manageDel"onclick='del()' href="deleteProductParent?delepid=<%=lpp.getParentID()%>">删除</a></td>
				</tr>
				<%
					for(ProductCategory lpc : list ) {
						if(lpc.getParentID()==lpp.getParentID()) {
				%>
				<tr>
					<td class="first w4 c"><%=lpc.getId() %></td>
					<td class="childClass"><%=lpc.getName() %></td>
					<td class="w1 c"><a href="productClass-modify2.jsp?cid=<%=lpc.getId() %>">修改</a> 
					<a  class="manageDel" onclick='del()' href="deleteProductCategory?delecid=<%=lpc.getId()%>">删除</a></td>
				</tr>
				
				<% 	  }	
					}
				}	
				%>
			</table>
		</div>
	</div>
	<div class="clear"></div>
    
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
