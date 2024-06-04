<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="modelos.Usuario"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>Dashboard</h1>
		<p>
			Bienvenido al sistema,
			<%= ((Usuario) session.getAttribute("usuario")).getUsuario()%>!
		</p>

		<%-- Mensajes de éxito --%>
		<c:if test="${not empty param.exito}">
			<div class="alert alert-success mt-3">
				<%-- Mostrar mensaje de éxito solo si la operación fue exitosa --%>
				<c:choose>
					<c:when test="${param.exito == 1}">
                            Depósito de fondos realizado exitosamente!
                        </c:when>
					<c:when test="${param.exito == 2}">
                            Retiro de fondos realizado exitosamente!
                        </c:when>
				</c:choose>
			</div>
		</c:if>

		<%-- Mostrar saldo --%>
		<c:if test="${not empty saldo}">
			<div class="alert alert-info mt-3">Saldo actual: ${saldo}</div>
		</c:if>

		<%-- Mensajes de error --%>
		<c:if test="${not empty param.error}">
			<div class="alert alert-danger mt-3">
				<%-- Mostrar mensaje de error solo si la operación falló --%>
				<c:choose>
					<c:when test="${param.error == 1}">
                            Ocurrió un error. Inténtalo de nuevo.
                        </c:when>
					<c:when test="${param.error == 2}">
                            La transferencia falló. Por favor verifique el nombre de usuario del destinatario y su saldo.
                        </c:when>
				</c:choose>
			</div>
		</c:if>

		<h4 class="mt-5">Consultar Saldo</h4>
		<form action="${pageContext.request.contextPath}/dashboard"
			method="get">
			<button type="submit" class="btn btn-primary">Consultar
				Saldo</button>
		</form>

		<h4 class="mt-5">Depositar Fondos</h4>
		<form action="${pageContext.request.contextPath}/deposit"
			method="post">
			<div class="form-group">
				<label for="monto">Monto:</label> <input type="number" step="0.01"
					class="form-control" id="monto" name="monto" required>
			</div>
			<button type="submit" class="btn btn-primary">Depositar</button>
		</form>

		<h4 class="mt-5">Retirar Fondos</h4>
		<form action="${pageContext.request.contextPath}/withdraw"
			method="post">
			<div class="form-group">
				<label for="monto">Monto:</label> <input type="number" step="0.01"
					class="form-control" id="monto" name="monto" required>
			</div>
			<button type="submit" class="btn btn-primary">Retirar</button>
		</form>





		<%-- *****Se deja para configurar a futuro*****
            
            <h4 class="mt-5">Transferir Fondos</h4>
            <form action="${pageContext.request.contextPath}/transfer" method="post">
                <div class="form-group">
                    <label for="destinatario">Destinatario:</label>
                    <input type="text" class="form-control" id="destinatario" name="destinatario" required>
                </div>
                <div class="form-group">
                    <label for="monto_transferencia">Monto:</label>
                    <input type="number" step="0.01" class="form-control" id="monto_transferencia" name="monto_transferencia" required>
                </div>
                <button type="submit" class="btn btn-primary">Transferir</button>
            </form>

    <h4 class="mt-5">Convertir Saldo</h4>
    <form action="${pageContext.request.contextPath}/convert" method="post">
        <div class="form-group">
            <label for="divisa">Divisa:</label>
            <select class="form-control" id="divisa" name="divisa">
                <option value="USD">USD</option>
                <option value="EUR">EUR</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Convertir</button>
    </form>

    //  Mostrar monto convertido
    <c:if test="${not empty requestScope.saldoConvertido}">
        <div class="mt-3">
            <p>Saldo Convertido: ${requestScope.saldoConvertido} ${requestScope.divisa}</p>
        </div>
    </c:if>
            --%>
	</div>
</body>
</html>
