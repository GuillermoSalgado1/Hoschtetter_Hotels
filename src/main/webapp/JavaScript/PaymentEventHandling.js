import {initializeApp} from "https://www.gstatic.com/firebasejs/11.0.1/firebase-app.js";
import {getFirestore, setDoc, doc} from "https://www.gstatic.com/firebasejs/11.0.1/firebase-firestore.js";

const firebaseConfig = {
    apiKey: "AIzaSyDsq6hpTf2TyyxsdP-vCAa0ZfEjfvoXUmQ",
    authDomain: "testproyectpoo.firebaseapp.com",
    databaseURL: "https://testproyectpoo-default-rtdb.firebaseio.com",
    projectId: "testproyectpoo",
    storageBucket: "testproyectpoo.firebasestorage.app",
    messagingSenderId: "815265158671",
    appId: "1:815265158671:web:3b31c4f3988ce9cdda757a",
    measurementId: "G-8W3H69G975"
};

const app = initializeApp(firebaseConfig);

document.getElementById('payment-btn').addEventListener('click', async (event) => {
    event.preventDefault();

    const userId = localStorage.getItem('loggedInUserId');
    const habitacionNombre = document.getElementById('habitacionNombre').value;
    const fechaInicio = document.getElementById('fechaInicio').value;
    const fechaTermino = document.getElementById('fechaTermino').value;
    const montoTotal = document.getElementById('hiddenMontoTotal').value;

    const db = getFirestore();
    const reservasRef = doc(db, "reservas", `${userId}_${new Date().getTime()}`);

    await setDoc(reservasRef, {
        usuarioId: userId,
        habitacionNombre,
        fechaInicio,
        fechaTermino,
        montoTotal
    });

    const facturasRef = doc(db, "facturas", `${userId}_${new Date().getTime()}`);
    await setDoc(facturasRef, {
        usuarioId: userId,
        reservaId: reservasRef.id,
        montoTotal,
        fechaFactura: new Date().toISOString()
    });
    alert("Reserva y factura creadas exitosamente.");
});