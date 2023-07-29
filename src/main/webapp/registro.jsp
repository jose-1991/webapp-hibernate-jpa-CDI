<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp"/>

<h3>${tittle}</h3>
<form action="${pageContext.request.contextPath}/registro/form" method="post">
<div class="row mb-2">
  <label for="username" class="col-form-label col-sm-2">Nombre</label>
  <div>
    <input class="col-sm-4" type="text" name="username" id="username" value="${usuario.username != null? usuario.username: ""}" class="form-control">
  </div>
   <c:if test="${erroresRegistro != null && !empty erroresRegistro.username}">
                      <div style="color:red;">${erroresRegistro.username}</div>
              </c:if>
  </div>
<div class="row mb-2">
  <label for="password" class="col-form-label col-sm-2">Password</label>
    <div>
      <input class="col-sm-4" type="password" name="password" id="password" value="${usuario.password != null? usuario.password: ""}" class="form-control">
    </div>
</div>
    <div class="row mb-2">
      <label for="email" class="col-form-label col-sm-2">Email</label>
      <div>
        <input class="col-sm-4" type="email" name="email" id="email" value="${usuario.email != null? usuario.email: ""}" class="form-control">
      </div>
       <c:if test="${erroresRegistro != null && !empty erroresRegistro.email}">
                          <div style="color:red;">${erroresRegistro.email}</div>
                  </c:if>
      </div>
    <div class="row my-2">
             <div class="my-2">
                    <input type="submit" value="registrarse" class="btn btn-primary">
             </div>
         </div>

</div>
<input type="hidden" name="id" value="${usuario.id}">
</form>

<jsp:include page="layout/footer.jsp"/>