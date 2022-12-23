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
<hr/>  
  
<h3>Login Ingreso</h3>  

 <br/>  
	<form action="/login" method="post">  
		Introduce tu cuenta:<input type="text" name="id"/><br/><br/>  
				<input type="submit" value="login" class="newEvento">	
	</form> 
<h3>${mensaje }</h3>
</br>
<hr />
<table class="table">
<h3>Usuarios para probar funcionalidad</h3>
	<tr>
		<th>id</th>
	</tr>
	<c:forEach var="ele" items="${listaCuentas}">
		<tr>
			<td>${ele.id }</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>