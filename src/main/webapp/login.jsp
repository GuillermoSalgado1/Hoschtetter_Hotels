<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Iniciar Sesión</title>
</head>
<body>
<form action="SVLogin" method="post">
    <label for="email">Correo</label>
    <input type="email" name="email" id="email" required>
    <label for="password">Contraseña</label>
    <input type="password" name="password" id="password" required>
    <input type="submit" value="Iniciar sesión">
</form>
<p>¿Aún no te has registrado? <a href="registro.jsp">Regístrate aquí</a></p>
</body>
</html>
