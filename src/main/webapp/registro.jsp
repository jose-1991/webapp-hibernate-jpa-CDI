<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp"/>

<h3>${tittle}</h3>
<form action="${pageContext.request.contextPath}/registro/form" method="post">
<div class="row mb-2">
  <label for="username" class="col-form-label col-sm-2">Nombre</label>
  <div class="col-sm-4">
    <input type="text" name="username" id="username" value="${usuario.username != null? producto.username: ""}" class="form-control">
  </div>

</div>
<input type="hidden" name="id" value="${producto.id}">
</form>

<jsp:include page="layout/footer.jsp"/>