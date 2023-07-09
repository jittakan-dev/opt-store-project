<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />

<h3>${title}</h3>

<form action="${pageContext.request.contextPath}/products/form" method="post">
    <div class="row mb-2">
        <label for="name" class="col-form-label col-sm-2">Name</label>
        <div class="col-sm-4">
            <input type="text" name="name" id="name" value="${products.name}" class="form-control">
        </div>
        <c:if test="${errors != null && errors.containsKey('name')}">
             <div style="color:red;">${errors.name}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="price" class="col-form-label col-sm-2">Price</label>
        <div class="col-sm-4">
            <input type="number" name="price" id="price" value="${products.price > 0? products.price: ""}" class="form-control">
        </div>
        <c:if test="${errors != null && not empty errors.price}">
                     <div style="color:red;">${errors.price}</div>
                </c:if>
    </div>

    <div class="row mb-2">
        <label for="sku" class="col-form-label col-sm-2">Sku</label>
        <div class="col-sm-4">
            <input type="text" name="sku" id="sku" value="${products.sku}" class="form-control">
        </div>
        <c:if test="${errors != null && not empty errors.sku}">
             <div style="color:red;">${errors.sku}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="registration_date" class="col-form-label col-sm-2">Registration date</label>
        <div class="col-sm-4">
            <input class="form-control" type="date" name="dateRegistration" id="dateRegistration" value="${products.dateRegistration != null? products.dateRegistration.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")): ""}">
        </div>
        <c:if test="${errors != null && not empty errors.dateRegistration}">
             <div style="color:red;">${errors.dateRegistration}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="category" class="col-form-label col-sm-2">Category</label>
        <div class="col-sm-4">
            <select name="category" id="category" class="form-select">
                <option value="">--- select ---</option>
                <c:forEach items="${categories}" var="c">
                <option value="${c.id}" ${c.id.equals(products.category.id)? "selected": ""}>${c.name}</option>
                </c:forEach>
            </select>
        </div>
        <c:if test="${errors != null && not empty errors.category}">
              <div style="color:red;">${errors.category}</div>
        </c:if>
    </div>

    <div class="row mb-2">
      <div>
         <input class="btn btn-primary" type="submit" value="${products.id!=null && products.id>0? "Edit": "Clear"}">
      </div>
    </div>
    <input type="hidden" name="id" value="${products.id}">
</form>

<jsp:include page="layout/footer.jsp" />