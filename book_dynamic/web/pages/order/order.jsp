<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function (){
            $("a.receive_order").click(function (){
                var orderId = $(this).parent().parent().find("td:first").text();
                alert(orderId);
                return confirm("确定将"+orderId+"收货吗");
            })
        })
    </script>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <%--静态包含登陆成功后的菜单--%>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>订单号</td>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>


        <c:forEach items="${requestScope.order}" var="order">
            <tr>
                <td>${order.orderId}</td>
                <td>${order.createTime}</td>
                <td>${order.price}</td>
                <td>
                    <c:if test="${order.status==0}">
                        商家未发货
                    </c:if>
                    <c:if test="${order.status==1}">
                        <a class="receive_order" href="orderServlet?action=receiveOrder&orderId=${order.orderId}">点击确认收货</a>
                    </c:if>
                    <c:if test="${order.status==2}">
                        用户已签收
                    </c:if>
                </td>
                <td><a class="order_details" href="orderServlet?action=queryOrderItems&orderId=${order.orderId}">查看详情</a></td>
            </tr>
        </c:forEach>

    </table>


</div>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>