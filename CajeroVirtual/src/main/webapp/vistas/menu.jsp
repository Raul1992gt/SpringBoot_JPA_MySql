<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestión Eventos</title>
<!--  
	Cambiar el puerto, seguido de localhost: 
	por el puerto que necesites, en este caso está configuaro
	para el 8083
 -->
<link href="http://localhost:8083/css/estilos.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div id="logotipo">
	
	
	<h1>Bienvenido al Banco RoBoToDo</h1>
	<h3>Está conectado ${cuenta.id }</h3>
	<h3>${mensaje }</h3>
	</div>

<hr/>
	
	<h4><a href="/cuentas/ingresar" class="enlaces-prinipales">Ingresar</a></h4>
	<h4><a href="/cuentas/extraer" class="enlaces-prinipales">Extraer</a></h4>
	<h4><a href="/movimientos/verMovimientos" class="enlaces-prinipales">Ver movimientos</a> </h4>
	<h4><a href="/cuentas/transferencia" class="enlaces-prinipales">Transferencia</a></h4>
	
<br><br>
<hr/>
	<p><a href="/inicio" class="newEvento">ir a login</a></p>
</body>
</html>