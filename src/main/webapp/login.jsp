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
    <title>Login</title>
    <script type="module" src="auth/firebaseauth.js"></script>
</head>
<body>
<div class="container" id="signup" style="display:none;">
    <h1 class="form-title">Registro</h1>
    <form method="post" action="">
        <div id="signUpMessage" class="messageDiv" style="display:none;"></div>
        <div class="input-group">
            <i class="fas fa-user"></i>
            <input type="text" id="fName" placeholder="Nombre" required>
            <label for="fname">Nombre</label>
        </div>
        <div class="input-group">
            <i class="fas fa-user"></i>
            <input type="text" id="lName" placeholder="Apellido" required>
            <label for="lName">Apellido</label>
        </div>
        <div class="input-group">
            <i class="fas fa-envelope"></i>
            <input type="email" id="rEmail" placeholder="Correo" required>
            <label for="rEmail">Correo</label>
        </div>
        <div class="input-group">
            <i class="fas fa-lock"></i>
            <input type="password" id="rPassword" placeholder="Contraseña" required>
            <label for="rPassword">Contraseña</label>
        </div>
        <button class="btn" id="submitSignUp">Registrarse</button>
    </form>
    <div class="links">
        <p>Ya posees una cuenta?</p>
        <button id="signInButton">Iniciar sesión</button>
    </div>
</div>

<%--Login--%>

<div class="container" id="signIn">
    <h1 class="form-title">Iniciar sesión</h1>
    <form method="post" action="">
        <div id="signInMessage" class="messageDiv" style="display:none;"></div>

        <div class="input-group">
            <i class="fas fa-envelope"></i>
            <input type="email" id="email" placeholder="Correo" required>
            <label for="email">Correo</label>
        </div>
        <div class="input-group">
            <i class="fas fa-lock"></i>
            <input type="password" id="password" placeholder="Contraseña" required>
            <label for="password">Contraseña</label>
        </div>

        <button class="btn" id="submitSignIn">Iniciar sesión</button>
    </form>
    <div class="links">
        <p>Aún no posees una cuenta?</p>
        <button id="signUpButton">Registrarse</button>
    </div>
</div>
<script src="interactiveIndex/script.js"></script>

</body>
</html>