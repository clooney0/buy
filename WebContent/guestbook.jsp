<%@page import="com.cn.easybuy.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.cn.easybuy.dao.impl.*"%>
<%@page import="com.cn.easybuy.dao.*"%>
<%@page import="com.cn.easybuy.util.Pages"%>
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
<%
	User user = (User)session.getAttribute("user");
	if(user==null){
		response.sendRedirect("login.jsp?error=Please login first");
	}
%>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.png" /></div>
	<%
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
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 在线留言
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
	<div class="main">
		<div class="guestbook">
			<h2>全部留言</h2>
			<ul>
			<%
					Pages p = new Pages();
					CommentDao cDao = new CommentDaoImpl();
					String current = request.getParameter("current");
					String error = request.getParameter("error");
					int pc = 1;
					if(current!=null && !current.equals("")){
						pc = Integer.valueOf(current);
					}
					p.setPageSize(5);
					p.setPageIndex(pc);
					List<Comment> comments = cDao.getCommentLimitPage(p);
					for(Comment comment : comments){
				%>
				<li>
					<dl>
						<dt><%=comment.getContent() %></dt>
						<dd class="author">网友：<%=comment.getNickName() %> <span class="timer"><%=comment.getCreateTime() %></span></dd>
						<dd>回复内容: <%=comment.getReply() %></dd>
					</dl>
				</li>
				<li>
					<dl>---------------------------------------------------</dl>
				</li>
			<%} %>
			</ul>
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
				<li>[当前:<%=p.getPageIndex() %>页/共 <%=p.getPageTotal() %>页]</li>
				<%
				     if(p.getPageIndex()>1){
				%>
				<li><a href="guestbook.jsp?current=1">首页</a></li>
				<li><a href="guestbook.jsp?current=<%=p.getPageIndex()-1 %>">上一页</a></li>
				<%} 
				if(p.getPageIndex()<p.getPageTotal()){ %>
				<li><a href="guestbook.jsp?current=<%=p.getPageIndex()+1 %>">下一页</a></li>
				<li><a href="guestbook.jsp?current=<%=p.getPageTotal() %>">尾页</a></li>
				<% }%>
				</ul>
			</div>
			<div id="reply-box">
				<form id="guestBook" action="addGuestBook" method="post">
				<input type="hidden" name="name" value="${user.getName() }"/>
					<table>
						<tr>
							<td class="field">昵称：</td>
							<td><input class="text" type="text" name="guestName" disabled="disabled" value="${user.getName()} "/></td>
						</tr>						
						<tr>
							<td class="field">留言内容：</td>
							<td><textarea name="guestContent"></textarea><span></span></td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-blue"><input type="submit" name="submit" value="提交留言" /></label></td>
						</tr>
					</table>
				</form>
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
