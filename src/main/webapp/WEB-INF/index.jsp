<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido a AlkeWalletJSP</title>
</head>
<body>
    <h2>Bienvenido a AlkeWalletJSP</h2>
    <p>Estás siendo redireccionado a la pagina de login...</p>
    <script type="text/javascript">
        window.location.href = "${pageContext.request.contextPath}/dispatcher?action=login";
    </script>
</body>
</html>