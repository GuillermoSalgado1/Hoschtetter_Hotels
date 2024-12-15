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
        <form class="form" method="post" action="PaymentProcessServlet">
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
                    <input id="name_field" class="input_field" type="text" name="cardHolderName"
                           placeholder="Ingresa tu nombre completo">
                </div>
                <div class="input_container">
                    <label for="cardNumber_field" class="input_label">Número de tarjeta</label>
                    <input id="cardNumber_field" class="input_field" type="number" name="cardNumber"
                           placeholder="0000 0000 0000 0000">
                </div>
                <div class="input_container">
                    <label for="expire_field" class="input_label">Fecha expiración / CVV</label>
                    <div class="split">
                        <input id="expire_field" class="input_field" type="text" name="expiryDate" placeholder="01/23">
                        <input id="cvv_field" class="input_field" type="number" name="cvv" placeholder="CVV">
                    </div>
                </div>
            </div>
            <input type="hidden" name="montoTotal" value="${montoTotal}">
            <input type="hidden" name="nombreHabitacion" value="${nombreHabitacion}">
            <input type="hidden" name="loggedInUserId" id="loggedInUserId">
            <input type="hidden" name="fechaInicio" value="${fechaInicio}">
            <input type="hidden" name="fechaTermino" value="${fechaTermino}">
            <input type="hidden" name="habitacionId" value="${habitacionId}">
            <button id="payment-btn" class="purchase-btn">Pagar</button>
            <!-- Mueve el resumen aquí -->
            <div class="resumen" style="margin: 0 auto">
                <p><strong>SUBTOTAL:</strong> $${montoTotal}</p>
            </div>
        </form>
    </div>
</div>
<script src="JavaScript/PaymentSwapScript.js"></script>
<script>
    //Este script solo recupera el id del usuario logeado para poder enviarlo correctamente del form c:
    const loggedInUserIdFromLocalStorage = localStorage.getItem('loggedInUserId');

    if (loggedInUserIdFromLocalStorage) {
        document.getElementById('loggedInUserId').value = loggedInUserIdFromLocalStorage;
    }
</script>
</body>

</html>