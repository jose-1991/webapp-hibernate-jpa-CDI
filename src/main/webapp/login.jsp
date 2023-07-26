<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp"/>

<h3>${tittle}</h3>
<form action="${pageContext.request.contextPath}/login" method="post">
  <div class="row my-2">
    <label for="username"class="form-label">Username</label>
    <div>
      <input class="col-sm-4" type="text" name="username" id="username" class="form-control">
    </div>
  </div>
  <div class="row my-2">
    <label for="password"class="form-label">Password</label>
    <div>
      <input class="col-sm-4" type="password" name="password" id="password" class="form-control">
    </div>
  </div>
  <div class="row my-2">
    <div class="my-2">
        <input type="submit" value="login" class="btn btn-primary">
    </div>
  </div>
</form>

<jsp:include page="layout/footer.jsp"/>