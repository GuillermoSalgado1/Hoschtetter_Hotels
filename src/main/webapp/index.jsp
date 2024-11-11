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
    <script type="module" src="interactiveIndex/indexDisplayName.js"></script>
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
cursor: default"
                ></span></li>
                <li><a href="login.jsp" id="loginLink">Iniciar sesión</a></li>
                <li><a href="catalogo.jsp">Ver catálogo</a></li>
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
                <label>Fecha inicio</label>
                <input type="date" name="fechaInicio">
            </div>
            <div class="search-item">
                <label>Fecha termino</label>
                <input type="date" name="fechaTermino">
            </div>
            <div class="search-item">
                <label>Personas</label>
                <select name="person">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="3">4</option>
                </select>
            </div>
            <button type="submit" class="search-button">Reservar</button>
        </form>

    </div>
</header>

<main class="services">

    <div class="services-content container">
        <h2>lorem</h2>

        <div class="servicios-group">

            <div class="services-1">
                <img src="#" alt="">
                <h3>Servicio 1</h3>
            </div>
            <div class="services-1">
                <img src="#" alt="">
                <h3>Servicio 2</h3>
            </div>
            <div class="services-1">
                <img src="#" alt="">
                <h3>Servicio 3</h3>
            </div>

        </div>
        <p>
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsa non laborum aspernatur saepe
            voluptas doloribus obcaecati sequi ad, blanditiis aliquam,
            quisquam nisi, est animi quas magnam. Harum eos earum eveniet.
        </p>
        <a href="#" class="btn-1">Informacion</a>
    </div>
</main>

<section class="general">

    <div class="general-1">
        <h2>Lorem ipsum dolor</h2>
        <p>
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsa non laborum aspernatur saepe
            voluptas doloribus obcaecati sequi ad, blanditiis aliquam,
            quisquam nisi, est animi quas magnam. Harum eos earum eveniet.
        </p>
        <a href="#" class="btn-1">Informacion</a>
    </div>
    <div class="general-2"></div>

</section>

<section class="general">

    <div class="general-3"></div>

    <div class="general-1">
        <h2>Lorem ipsum dolor</h2>
        <p>
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsa non laborum aspernatur saepe
            voluptas doloribus obcaecati sequi ad, blanditiis aliquam,
            quisquam nisi, est animi quas magnam. Harum eos earum eveniet.
        </p>
        <a href="#" class="btn-1">Informacion</a>
    </div>
</section>

<section class="blog container">

    <h2>Blog</h2>
    <p>voluptas doloribus obcaecati sequi ad, blanditiis aliquam</p>
    <div class="blog-content">
        <div class="blog-1">
            <img src="#">
            <h3>Blog 1</h3>
            <p>
                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsa non laborum aspernatur saepe
                voluptas doloribus obcaecati sequi ad, blanditiis aliquam,
                quisquam nisi, est animi quas magnam. Harum eos earum eveniet.
            </p>

        </div>
        <div class="blog-1">
            <img src="#">
            <h3>Blog 2</h3>
            <p>
                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsa non laborum aspernatur saepe
                voluptas doloribus obcaecati sequi ad, blanditiis aliquam,
                quisquam nisi, est animi quas magnam. Harum eos earum eveniet.
            </p>

        </div>
        <div class="blog-1">
            <img src="#">
            <h3>Blog 3</h3>
            <p>
                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsa non laborum aspernatur saepe
                voluptas doloribus obcaecati sequi ad, blanditiis aliquam,
                quisquam nisi, est animi quas magnam. Harum eos earum eveniet.
            </p>

        </div>
    </div>

    <a href="#" class="btn-1">Informacion</a>

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
                <li><a href="#">lorem</a></li>
            </ul>
        </div>
    </div>
</footer>

</body>

</html>