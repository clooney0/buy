<%@page import="com.cn.easybuy.entity.News"%>
<%@page import="java.util.List"%>
<%@page import="com.cn.easybuy.util.Pages"%>
<%@page import="com.cn.easybuy.dao.impl.NewsDaoImpl"%>
<%@page import="com.cn.easybuy.dao.NewsDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../scripts/function.js"></script>
<script  type="text/javascript">
function confirmDel(){

	if(!confirm("确定删除吗？"))
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
			<li><a href="product.jsp">商品</a></li>
			<li><a href="order.jsp">订单</a></li>
			<li><a href="guestbook.jsp">留言</a></li>
			<li class="current"><a href="news.jsp">新闻</a></li>
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
		<h2>新闻管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>新闻标题</th>
					<th>操作</th>
					<th>时间</th>
					<th>更改</th>
				</tr>
			  	<%			  	
			  	  Pages pages = (Pages)session.getAttribute("pages1");
				if(pages==null)
				{
					response.sendRedirect("page.jsp");
					return ;
				}else{
					int pageIndex=pages.getPageIndex();
					int pageTotal=pages.getPageTotal();
					List<News> list=(List<News>)session.getAttribute("list1");  
			  	
				//NewsDao newsdao=new  NewsDaoImpl();
				// List<News> news=newsdao.getAllNews();
				for(News n :list){ %>
				<tr>
					<td><%=n.getId() %></td>
               		<td><%=n.getTitle() %></td>
               		<td><%=n.getContent() %></td>
               		<td><%=n.getCreateTime() %></td>
               		<td class="w1 c"><a href="news-modify.jsp?id=<%=n.getId()%>">修改</a> <a onclick='confirmDel()' href="NewsDel?id=<%=n.getId()%> ">删除</a></td>
					
				<%} }%>
				
				
				</tr>
			</table>
			   [当前：<%=pages.getPageIndex() %>页/共<%=pages.getPageTotal() %>页]
			<% if(pages.getPageIndex()>1){%>
			<a href="page.jsp?pageIndex=1">首页</a>
			<a href="page.jsp?pageIndex=<%=pages.getPageIndex()-1 %>">上一页</a>
			<%}if(pages.getPageIndex()<pages.getPageTotal()){ %>
			<a href="page.jsp?pageIndex=<%=pages.getPageIndex()+1 %>">下一页</a>
			<a href="page.jsp?pageIndex=<%=pages.getPageTotal() %>">尾页</a>
			<% } %>   
		</div>		
	</div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
