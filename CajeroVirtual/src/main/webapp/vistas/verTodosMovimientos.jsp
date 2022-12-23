<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="http://localhost:8083/css/estilos.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<h3>Zona Extraer | Conectado : ${cuenta.id }</h3>  

<h3>El saldo es : ${cuenta.saldo }</h3>

 <br/>  

<table class="table">
<tr>
	<th>Id movimiento</th>
	<th>Cantidad</th>
	<th>Tipo de operacion</th>
</tr>	
<c:forEach var="ele" items="${listaTodosMovimientos}" >
<tr>
	<td>${ele.id }</td>
	<td>${ele.cantidad }</td>
	<td>${ele.operacion }</td>
</tr>

</c:forEach>
</table>
	
<h3>${mensaje }</h3>
<hr/>

<p><a href="/cuentas/menu" class="newEvento">Volver atras</a></p>
<hr />

</body>
</html>