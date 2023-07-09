<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />

<h3>${title}</h3>
<c:if test="${username.present}">
   <div class="alert alert-info">Hello ${username.get()}, welcome!</div>
   <a class="btn btn-primary my-2" href="${pageContext.request.contextPath}/products/form">create [+]</a>
</c:if>
<table class="table table-hover table-striped">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>type</th>
        <c:if test="${username.present}">
        <th>price</th>
        <th>add</th>
        <th>edit</th>
        <th>delete</th>
        </c:if>
    </tr>
    <c:forEach items="${productos}" var="p">
    <tr>
        <td>${p.id}</td>
        <td>${p.name}</td>
        <td>${p.category.name}</td>
        <c:if test="${username.present}">
        <td>${p.price}</td>
        <td><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/cart/add?id=${p.id}">add to cart</a></td>
        <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/products/form?id=${p.id}">edit</a></td>
        <td><a class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete?');"
        href="${pageContext.request.contextPath}/products/delete?id=${p.id}">delete</a></td>
        </c:if>
    </tr>
    </c:forEach>
</table>
<p>${applicationScope.message}</p>
<p>${requestScope.message}</p>
<jsp:include page="layout/footer.jsp" />