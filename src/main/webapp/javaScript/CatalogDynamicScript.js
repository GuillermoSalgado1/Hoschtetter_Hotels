import {initializeApp} from "https://www.gstatic.com/firebasejs/11.0.1/firebase-app.js";
import {getDatabase, ref, get} from "https://www.gstatic.com/firebasejs/11.0.1/firebase-database.js";

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

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const db = getDatabase();

let habitacionesGlobal = [];

async function fetchRooms() {
    const habitacionesRef = ref(db, "habitaciones");
    try {
        const snapshot = await get(habitacionesRef);
        if (snapshot.exists()) {
            habitacionesGlobal = Object.values(snapshot.val());
            displayRooms(habitacionesGlobal);
        } else {
            console.error("No se encontraron habitaciones en la base de datos.");
        }
    } catch (error) {
        console.error("Error al obtener habitaciones de Firebase:", error);
    }
}

function displayRooms(Habitaciones) {
    const catalogo = document.querySelector(".catalogo-habitaciones");
    catalogo.innerHTML = "";

    Habitaciones.forEach((habitacion) => {
        const Card = document.createElement("div");
        Card.classList.add("habitacion-card");

        Card.innerHTML = `
        <img src="${habitacion.imagen}" alt="${habitacion.nombre}">
        <div class="info">
            <h3 class="title-card">${habitacion.nombre}</h3>
            <p class="precio">$${parseInt(habitacion.precio).toLocaleString()}</p>
        </div>
        `;

        Card.addEventListener("click", () => openModal(habitacion));

        catalogo.appendChild(Card);
    });
}

function openModal(habitacion) {
    const overlay = document.querySelector(".modal-overlay");
    overlay.style.display = "flex";

    document.querySelector(".modal-imagen").src = habitacion.imagen;
    document.querySelector(".modal-titulo").textContent = habitacion.nombre;
    document.querySelector(".modal-descripcion").textContent = habitacion.descripcion;
    document.querySelector(".modal-capacidad").textContent = `${habitacion.capacidad}`;
    document.querySelector(".modal-banos").textContent = `${habitacion.banos}`;
    document.querySelector(".modal-precio").textContent = `$${parseInt(habitacion.precio).toLocaleString()} por noche`;
    document.getElementById("habitacionPrecio").value = habitacion.precio;
    document.getElementById("habitacionNombre").value = habitacion.nombre;
    document.getElementById("habitacionId").value = habitacion.id;
}

function closeModal() {
    document.querySelector(".modal-overlay").style.display = "none";
}

function applyFilters() {
    const capacidad = document.getElementById("capacidad").value;
    const precio = document.getElementById("precio").value;

    const [Preciomin, Preciomax] = precio ? precio.split('-').map(num => parseInt(num) * 1000) : [null, null];
    const HabitacionesFiltro = habitacionesGlobal.filter(habitacion => {
        return (capacidad === "" || habitacion.capacidad === capacidad) &&
            (!Preciomin || (habitacion.precio >= Preciomin && habitacion.precio <= Preciomax));
    });

    displayRooms(HabitacionesFiltro);
}

document.addEventListener("DOMContentLoaded", () => {
    fetchRooms();

    document.getElementById("applyFiltersBtn").addEventListener("click", applyFilters);

    const modalOverlay = document.querySelector(".modal-overlay");
    modalOverlay.addEventListener("click", (e) => {
        if (e.target === modalOverlay) closeModal();
    });
    document.querySelector(".modal-cerrar").addEventListener("click", closeModal);
});
