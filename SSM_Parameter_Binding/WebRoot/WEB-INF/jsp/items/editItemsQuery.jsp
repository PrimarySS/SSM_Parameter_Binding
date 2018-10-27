<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title></title>

<script type="text/javascript">
	function editItemsAllSubmit() {
		document.itemsForm.action="${PageContext.request.ContextPath }/items/editItemsAllSubmit.action";
		document.itemsForm.submit();
	}
</script>

</head>

<body>
	<form name="itemsForm" action="${PageContext.request.ContextPath }/items/queryItems.action" method="post">
		<table border="1">
			<tr>
				<td><input type="button" value="批量修改" onclick="editItemsAllSubmit()"></td>
			</tr>
		</table>

		<h3>商品列表</h3>
		<table border="1" width="100%">
			<tr>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>生成时间</td>
				<td>商品描述</td>
			</tr>
			<c:forEach items="${itemsList}" var="item" varStatus="status">
				<tr>
					<td>
						<input type="text"  name="itemsList[${status.index }].items_name" value="${item.items_name }">
					</td>
					<td>
						<input type="text" name="itemsList[${status.index }].items_price" value="${item.items_price }">
					</td>
					<td>
						<input type="text" name="itemsList[${status.index }].items_creattime" value="<fmt:formatDate value="${item.items_creattime }" pattern="yyyy:MM:dd HH:mm:ss"/>">
					</td>
					<td>
						<input type="text" name="itemsList[${status.index }].items_detail" value="${item.items_detail }">
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
