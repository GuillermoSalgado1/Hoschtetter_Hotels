document.addEventListener("DOMContentLoaded", () => {
    const cardBtn = document.getElementById("card-btn");
    const paypalBtn = document.getElementById("paypal-btn");
    const creditCardForm = document.getElementById("credit-card-form");
    const paymentBtn = document.getElementById("payment-btn");
    const paymentMethodText = document.getElementById("payment-method-text");

    function showCardForm() {
        creditCardForm.style.display = "block";
        paymentBtn.textContent = "Pagar";
        paymentBtn.style.marginTop = "";
        paymentMethodText.textContent = "Paga usando tarjeta de crédito";
    }
    function showPaypalOption() {
        creditCardForm.style.display = "none";
        paymentBtn.textContent = "Ir a PayPal";
        paymentBtn.style.marginTop = "60px";
        paymentMethodText.textContent = "Paga en el portal de PayPal";

    }
    showCardForm();

    cardBtn.addEventListener("click", showCardForm);
    paypalBtn.addEventListener("click", showPaypalOption);
});

const urlParams = new URLSearchParams(window.location.search);
const montoTotal = urlParams.get('amount');

if (montoTotal) {
    document.getElementById('montoTotal').innerText = montoTotal;
    document.getElementById('hiddenMontoTotal').value = montoTotal;
}

document.getElementById('card-btn').addEventListener('click', () => {
    document.getElementById('payment-method-text').innerText = "Paga usando tarjeta de crédito";
    document.getElementById('credit-card-form').style.display = 'block';
});

document.getElementById('paypal-btn').addEventListener('click', () => {
    document.getElementById('payment-method-text').innerText = "Paga usando PayPal";
    document.getElementById('credit-card-form').style.display = 'none';
});