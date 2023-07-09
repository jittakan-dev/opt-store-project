<jsp:include page="layout/header.jsp" />

<h3>${title}</h3>

<ul class="list-group">
    <li class="list-group-item active">Option menu</li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/products">display products</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/login.html">login</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/logout">logout</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/cart/view">view cart</a></li>
</ul>

<jsp:include page="layout/footer.jsp" />