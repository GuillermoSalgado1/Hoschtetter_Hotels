<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="indexStyle.css">
    <title>Hoschtetter Hotels</title>
    <script type="module" src="JavaScript/indexDynamicNameScript.js"></script>
</head>

<body>
<header class="header">
    <div class="menu container">
        <input type="checkbox" id="menu"/>
        <label for="menu">
            <img src="images/menu.png" class="menu-icono" alt="">
        </label>
        <nav class="navbar">
            <ul>
                <li><span id="loggedUserFName" style="font-size: 18px;
                        padding: 20px;
                        padding-right: 10px;
                        color: #FFFDFC;
                        display: block;
                        font-weight: 600;
                        cursor: default">
                </span></li>
                <li><a href="login.jsp" id="loginLink">Iniciar sesión</a></li>
            </ul>
        </nav>
    </div>
    <div class="header-content container">
        <h1>Hotel Hochstetter</h1>
        <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit.
            Tenetur, dolorem. Sint veniam nostrum officia fuga ex,
            minus, sit rerum quibusdam, autem alias a. Quae illo adipisci
            animi placeat illum aspernatur!
        </p>

        <form class="search-box">
            <div class="search-item">
                <label for="capacidad">Personas</label>
                <select id="capacidad" name="capacidad">
                    <option value="">Seleccionar...</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="4">4</option>
                </select>
            </div>
            <div class="search-item">
                <label for="precio">Precio</label>
                <select id="precio" name="precio">
                    <option value="">Seleccionar...</option>
                    <option value="20-39">$20.000 - $39.999</option>
                    <option value="40-69">$40.000 - $69.999</option>
                    <option value="70-99">$70.000 - $99.999</option>
                    <option value="100-159">$100.000 - $159.999</option>
                </select>
            </div>
            <button type="button" class="search-button" id="applyFiltersBtn">Filtrar</button>
        </form>

    </div>
</header>

<section class="catalogo-group">
    <div class="catalogo-habitaciones"></div>

    <div class="modal-overlay">
        <div class="modal-contenido">
            <span class="modal-cerrar">&times;</span>
            <img src="" alt="" class="modal-imagen">
            <div class="modal-info">
                <h2 class="modal-titulo"></h2>
                <p class="modal-descripcion"></p>
                <p><strong>Capacidad:</strong> <span class="modal-capacidad"></span> persona/s</p>
                <p><strong>Baños:</strong> <span class="modal-banos"></span></p>
                <p><strong>Precio:</strong> <span class="modal-precio"></span></p>
                <form class="modal-reservar-form" method="POST" action="CalculatePriceServlet">
                    <input type="hidden" id="habitacionPrecio" name="precioPorNoche">
                    <input type="hidden" id="habitacionNombre" name="nombreHabitacion">
                    <label for="fechaInicio">Llegada</label>
                    <input type="date" id="fechaInicio" name="fechaInicio" required>
                    <label for="fechaTermino">Salida</label>
                    <input type="date" id="fechaTermino" name="fechaTermino" required>
                    <button type="submit">Calcular Monto</button>
                </form>
                <div id="precioCalculado"></div>
            </div>
        </div>
    </div>
    <script type="module" src="JavaScript/catalogoScript.js"></script>
</section>

<footer class="footer">

    <div class="footer-content container">
        <div class="link">
            <h3>Lorem</h3>
            <ul>
                <li><a href="#">lorem</a></li>
                <li><a href="#">lorem</a></li>
                <li><a href="#">lorem</a></li>
                <li><a href="#">lorem</a></li>
            </ul>
        </div>
        <div class="link">
            <h3>Lorem</h3>
            <ul>
                <li><a href="#">lorem</a></li>
                <li><a href="#">lorem</a></li>
                <li><a href="#">lorem</a></li>
                <li><a href="#">lorem</a></li>
            </ul>
        </div>
        <div class="link">
            <h3>Lorem</h3>
            <ul>
                <li><a href="#">lorem</a></li>
                <li><a href="#">lorem</a></li>
                <li><a href="#">lorem</a></li>
                <li><a href="#">lorem</a></li>
            </ul>
        </div>
        <div class="link">
            <h3>Lorem</h3>
            <ul>
                <li><a href="#">lorem</a></li>
                <li><a href="#">lorem</a></li>
                <li><a href="#">lorem</a></li>
                <li><a href="payment.jsp">Portal de pago test</a></li>
            </ul>
        </div>
    </div>
</footer>

</body>

</html>