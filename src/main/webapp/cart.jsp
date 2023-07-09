<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />

<h3>${title}</h3>

<c:choose>
<c:when test="${cart.items.isEmpty()}">
<div class="alert alert-warning">Sorry, there are no products in the shopping cart.!</div>
</c:when>
<c:otherwise>
<form name="formcart" action="${pageContext.request.contextPath}/cart/update" method="post">
<table class="table table-hover table-striped">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>price</th>
        <th>amount</th>
        <th>total</th>
        <th>delete</th>
    </tr>
    <c:forEach items="${cart.items}" var="item">
    <tr>
        <td>${item.product.id}</td>
        <td>${item.product.name}</td>
        <td>${item.product.price}</td>
        <td><input type="text" size="4" name="cant_${item.product.id}" value="${item.amount}" /></td>
        <td>${item.import}</td>
        <td><input type="checkbox" value="${item.product.id}" name="deleteProducts" /></td>
    </tr>
    </c:forEach>
    <tr>
        <td colspan="5" style="text-align: right">Total:</td>
        <td>${cart.total}</td>
    </tr>
</table>
<a class="btn btn-primary" href="javascript:document.formcart.submit();">Update</a>
</form>
</c:otherwise>
</c:choose>
<div class="my-2">
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/index.jsp">return</a>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/products">keep buying</a>
</div>

<jsp:include page="layout/footer.jsp" />