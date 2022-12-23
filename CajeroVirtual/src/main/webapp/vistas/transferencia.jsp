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

<h4>Recuerde que su saldo es de : ${cuenta.saldo }</h4>

 <br/>  
	<form action="/cuentas/transferencia" method="post">  
		Introduce cantidad :<input type="text" name="cantidad"/><br/><br/>  
		Introduce cuenta de destino :<input type="text" name="destino"/><br/><br/>  
				<input type="submit" value="transferencia" class="newEvento">	
</form> 
<h3>${mensaje }</h3>
<hr/>

<p><a href="/cuentas/menu" class="newEvento">Volver atrás</a></p>
<hr />

<table class="table">
<h3>Tabla para comprobar las transferencias</h3>
<tr>
	<th>id</th>
	<th>saldo</th>
	<th>tipo de cuenta</th>
</tr>	
<c:forEach var="ele" items="${listaCuentas}" >
<tr>
	<td>${ele.id }</td>
	<td>${ele.saldo }</td>
	<td>${ele.tipo }</td>
</tr>

</c:forEach>

</body>
</html>