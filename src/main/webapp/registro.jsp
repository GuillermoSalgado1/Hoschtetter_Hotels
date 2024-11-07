<%--
  Created by IntelliJ IDEA.
  User: guill
  Date: 06/11/2024
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro</title>
</head>
<body>
<form action="SVregister" method="post">
    <label for="nombre">Nombre</label>
    <input type="text" name="name" id="nombre" required>
    <label for="email">Correo</label>
    <input type="email" name="email" id="email" required>
    <label for="password">Contraseña</label>
    <input type="password" name="password" id="password" required>
    <input type="submit">
</form>

</body>
</html>