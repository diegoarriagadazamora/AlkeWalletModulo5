<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<h2 class="mt-5">Login</h2>
		<form
			action="${pageContext.request.contextPath}/dispatcher?action=login"
			method="post">
			<div class="form-group">
				<label for="usuario">Usuario:</label> <input type="text"
					class="form-control" id="usuario" name="usuario" required>
			</div>
			<div class="form-group">
				<label for="password">Contraseña:</label> <input type="password"
					class="form-control" id="password" name="password" required>
			</div>
			<button type="submit" class="btn btn-primary">Login</button>
			<% if (request.getParameter("error") != null) { %>
			<div class="alert alert-danger mt-3">¡Usuario o contraseña
				inválidos!</div>
			<% } %>
		</form>
	</div>
</body>
</html>
