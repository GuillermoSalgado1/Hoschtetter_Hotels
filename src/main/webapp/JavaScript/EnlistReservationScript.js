import {initializeApp} from "https://www.gstatic.com/firebasejs/11.0.1/firebase-app.js";
import {
    getFirestore,
    query,
    where,
    getDocs,
    collection
} from "https://www.gstatic.com/firebasejs/11.0.1/firebase-firestore.js";

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
const db = getFirestore(app);

const loggedInUserId = localStorage.getItem("loggedInUserId");

const reservationsList = document.getElementById("reservations-list");
const logoutButton = document.getElementById("logout-btn");

async function loadReservations() {
    if (!loggedInUserId) {
        alert("Por favor, inicia sesión primero.");
        window.location.href = "index.html";
        return;
    }

    try {

        const reservationsRef = collection(db, "reservaciones");
        const q = query(reservationsRef, where("usuarioId", "==", loggedInUserId));
        const querySnapshot = await getDocs(q);

        reservationsList.innerHTML = "";

        if (querySnapshot.empty) {

            reservationsList.innerHTML = `<tr><td colspan="5">No se encontraron reservaciones.</td></tr>`;
        } else {

            querySnapshot.forEach((doc) => {
                const reservation = doc.data();
                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${doc.id}</td>
                    <td>${reservation.nombreHabitacion}</td>
                    <td>${formatDate(reservation.fechaInicio.toDate())}</td>
                    <td>${formatDate(reservation.fechaFin.toDate())}</td>
                    <td>$${reservation.montoTotal}</td>
                `;

                reservationsList.appendChild(row);
            });
        }
    } catch (error) {
        console.error("Error al cargar reservaciones:", error);
        alert("Hubo un error al cargar las reservaciones.");
    }
}


function formatDate(date) {
    const yyyy = date.getFullYear();
    const mm = String(date.getMonth() + 1).padStart(2, "0");
    const dd = String(date.getDate()).padStart(2, "0");
    return `${yyyy}-${mm}-${dd}`;
}

document.addEventListener("DOMContentLoaded", loadReservations);