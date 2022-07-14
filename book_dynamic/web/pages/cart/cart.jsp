<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="utf-8"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%></head>
<script type="text/javascript">
	$(function (){
		$("input.updateCount").change(function (){
			//获取商品名称
			var name = $(this).parent().parent().find("td:first").text();
			//获取商品数量
			var count = this.value;
			//获取商品id
			var id= $(this).attr('bookId');
			if (confirm("你确定要将【"+name+"】商品修改数量为："+count+"吗？")){
				location.href ="carServlet?action=updateCount&count="+count+"&id="+id;
			}
			else{
				this.value = this.defaultValue;
			}
		})

		$("#clearCar").click(function (){
			return confirm("你确定要清空购物车吗？")
		})
		$("a.deleteItem").click(function (){
			return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗？")
		})
	})
</script>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%--静态包含登陆成功后的菜单--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<c:forEach items="${sessionScope.car.items}" var = "entry">
				<tr>
					<td>${entry.value.name}</td>
					<td>
						<input class="updateCount" bookId="${entry.value.id}"
							   style="width:60px" type="text" value="${entry.value.count}">
					</td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totalPrice}</td>
					<td><a class="deleteItem" href="carServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty sessionScope.car.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.car.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.car.totalPrice}</span>元</span>
				<span class="cart_span" id="clearCar"><a href="carServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
		<c:if test="${empty sessionScope.car.items}">
			<h1 align="center">当前购物车为空！</h1>
			<div class="cart_info">
				<span class="cart_span"><a href="index.jsp">去购物</a></span>
			</div>
		</c:if>
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>