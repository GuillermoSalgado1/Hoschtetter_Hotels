<%--
  Created by IntelliJ IDEA.
  User: guill
  Date: 21/11/2024
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
        rel="stylesheet">
  <link rel="stylesheet" href="paymentStyle.css">
</head>

<body>
<div class="modal">
  <div class="modal-content">
    <div class="resumen">
      <h3>Resumen de Pago</h3>
      <p>Habitación: ${nombreHabitacion}</p>
      <p>Precio: ${montoTotal}</p>
      <a href="index.jsp" class="btn">Volver</a>
    </div>
    <input type="hidden" id="hiddenMontoTotal" name="montoTotal" value="">
    <form class="form">
      <div class="payment-options">
        <button id="card-btn" name="card" type="button">
          <img src="images/1156753_finance_payment_visa_icon.png">
          <img src="images/1156750_finance_mastercard_payment_icon.png">
        </button>
        <button id="paypal-btn" name="paypal" type="button">
          <img src="images/1156729_finance_payment_paypal_icon.png">
        </button>
      </div>
      <div class="separador">
        <hr class="line">
        <p id="payment-method-text">Paga usando tarjeta de credito</p>
        <hr class="line">
      </div>
      <div id="credit-card-form" class="credit-card-info-form">
        <div class="input_container">
          <label for="name_field" class="input_label">Nombre titular de la tarjeta</label>
          <input id="name_field" class="input_field" type="text" name="input-name" title="Inpit title"
                 placeholder="Ingresa tu nombre completo">
        </div>
        <div class="input_container">
          <label for="password_field" class="input_label">Número de tarjeta</label>
          <input id="password_field" class="input_field" type="number" name="input-name" title="Inpit title"
                 placeholder="0000 0000 0000 0000">
        </div>
        <div class="input_container">
          <label for="expire_field" class="input_label">Fecha expiración / CVV</label>
          <div class="split">
            <input id="expire_field" class="input_field" type="text" name="input-name" title="Expiry Date"
                   placeholder="01/23">
            <input id="cvv_field" class="input_field" type="number" name="cvv" title="CVV" placeholder="CVV">
          </div>
        </div>
      </div>
      <button id="payment-btn" class="purchase-btn">Pagar</button>
    </form>
  </div>
</div>
<script src="JavaScript/paymentScript.js"></script>
</body>

</html>