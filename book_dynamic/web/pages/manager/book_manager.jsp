<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">

        $(function () {
            $("a.del").click(function () {
                return confirm("确定要删除《" + $(this).parent().parent().find("td:first").text() + "》?")
            });
        });
    </script>
</head>

<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%@include file="/pages/common/manage_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=getBook&id=${book.id}&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a class="del" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageNo}">添加图书</a></td>
        </tr>
    </table>

   <%@include file="/pages/common/page.jsp"%>
</div>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>