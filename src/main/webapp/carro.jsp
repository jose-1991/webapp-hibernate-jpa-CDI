<%@page contentType="text/html" pageEncoding="UTF-8" import="org.jflores.apiservlet.webapp.headers.models.*"%>
<%
Carro carro =((Carro)session.getAttribute("carro"));
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de Compras</title>
</head>
<body>
<h1>Carro de Compras</h1>
<%if(carro == null || carro.getItems().isEmpty()){%>
<p>Lo sentimos no hay productos en el carro de compras!</p>
<%}else{%>
<table>
    <tr>
        <th>id</th>
        <th>nombre</th>
        <th>precio</th>
        <th>cantidad</th>
        <th>total</th>
    </tr>
    <%for(ItemCarro item: carro.getItems()){%>
    <tr>
        <td><%=item.getProducto().getId()%></td>
        <td><%=item.getProducto().getNombre()%></td>
        <td><%=item.getProducto().getPrecio()%></td>
        <td><%=item.getCantidad()%></td>
        <td style="text-align: right"><%=item.getImporte()%></td>
    </tr>
    <%}%>
    <tr>
        <td colspan="4" style="text-align: right">Total:</td>
        <td><%=carro.getTotal()%></td>
    </tr>
</table>
<%}%>
<p><a href="<%=request.getContextPath()%>/productos">Seguir Comprando</a></p>
<p><a href="<%=request.getContextPath()%>/index.html">volver</a></p>
</body>
</html>