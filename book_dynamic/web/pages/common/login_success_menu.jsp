<%--
  Created by IntelliJ IDEA.
  User: luoya
  Date: 2022/7/4
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="utf-8"  %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚书城</span>
    <a href="orderServlet?action=queryAllOrders&userId=${sessionScope.user.id}">我的订单</a>
    <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
</body>
</html>
