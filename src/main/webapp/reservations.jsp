<%--
  Created by IntelliJ IDEA.
  User: guill
  Date: 14/12/2024
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mis Reservaciones</title>
    <link rel="stylesheet" href="styles/reservationStyle.css">
</head>
<body>
<div class="container">
    <table class="reservations-table">
        <thead>
        <tr>
            <th>ID de Reservación</th>
            <th>Habitación</th>
            <th>Fecha Inicio</th>
            <th>Fecha Fin</th>
            <th>Total</th>
        </tr>
        </thead>
        <tbody id="reservations-list">
        </tbody>
    </table>

    <a href="index.jsp" class="back-btn">Volver al Inicio</a>
</div>

<script type="module" src="JavaScript/EnlistReservationScript.js"></script>
</body>
</html>
