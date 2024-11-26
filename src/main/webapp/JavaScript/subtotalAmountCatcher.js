document.querySelector('.modal-reservar-form').addEventListener('submit', async function (event) {
    event.preventDefault();
    const formData = new FormData(event.target);

    try {
        const response = await fetch('CalculatePriceServlet', {
            method: 'POST',
            body: formData
        });

        if (!response.ok) {
            throw new Error('Error al calcular el precio');
        }

        const data = await response.json();
        document.getElementById('precioCalculado').innerText = `Precio Total: $${data.precioTotal}`;
    } catch (error) {
        console.error(error);
        alert('Hubo un problema al calcular el precio. Inténtelo nuevamente.');
    }
});

const reservarBtn = document.querySelector('.reservar-button');
reservarBtn.addEventListener('click', () => {
    const precioTotal = document.getElementById('precioCalculado').innerText;
    if (precioTotal) {
        window.location.href = `payment.jsp?amount=${encodeURIComponent(precioTotal)}`;
    } else {
        alert('Primero calcule el precio.');
    }
});

