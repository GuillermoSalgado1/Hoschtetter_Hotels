<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap">
    <title>Login</title>
    <script type="module" src="auth/FirebaseAuthScript.js"></script>
    <link rel="stylesheet" href="loginStyle.css">
</head>
<body>
<div class="container" id="signup" style="display:none;">
    <h1 class="form-title">Registro</h1>
    <form method="post" action="">
        <div id="signUpMessage" class="messageDiv" style="display:none;"></div>
        <div class="input-group">
            <i class="fas fa-user"></i>
            <input type="text" id="fName" required>
            <label for="fname">Nombre</label>
        </div>
        <div class="input-group">
            <i class="fas fa-user"></i>
            <input type="text" id="lName" required>
            <label for="lName">Apellido</label>
        </div>
        <div class="input-group">
            <i class="fas fa-envelope"></i>
            <input type="email" id="rEmail" required>
            <label for="rEmail">Correo</label>
        </div>
        <div class="input-group">
            <i class="fas fa-lock"></i>
            <input type="password" id="rPassword" required>
            <label for="rPassword">Contraseña</label>
        </div>
        <button class="btn" id="submitSignUp">Registrarse</button>
    </form>
    <div class="links">
        <p>Ya posees una cuenta?</p>
        <button id="signInButton">Iniciar sesión</button>
    </div>
</div>

<div class="container" id="signIn">
    <h1 class="form-title">Iniciar sesión</h1>
    <form method="post" action="">
        <div id="signInMessage" class="messageDiv" style="display:none;"></div>

        <div class="input-group">
            <i class="fas fa-envelope"></i>
            <input type="email" id="email" required>
            <label for="email">Correo</label>
        </div>
        <div class="input-group">
            <i class="fas fa-lock"></i>
            <input type="password" id="password" required>
            <label for="password">Contraseña</label>
        </div>

        <button class="btn" id="submitSignIn">Iniciar sesión</button>
    </form>
    <div class="links">
        <p>Aún no posees una cuenta?</p>
        <button id="signUpButton">Registrarse</button>
    </div>
</div>
<script src="JavaScript/LoginModalSwapScript.js"></script>

</body>
</html>