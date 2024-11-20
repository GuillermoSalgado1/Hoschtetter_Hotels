const ListaHabitaciones = [
    { nombre: "Suite Junior", capacidad: "1",  precio: "45000", imagen: "images/suite-junior.jpg", descripcion: "Una suite diseñada para viajeros solitarios que buscan comodidad y funcionalidad. Cuenta con un ambiente acogedor, servicios básicos y un espacio perfecto para descansar después de un día lleno de actividades.", banos: "1"},
    { nombre: "Suite Ejecutivo", capacidad: "2", precio: "70000", imagen: "images/suite-ejecutivo.jpg", descripcion: "La elección ideal para quienes necesitan un equilibrio entre trabajo y descanso. Esta suite ofrece un diseño moderno, espacios optimizados para trabajar cómodamente y detalles que garantizan una estancia relajante..", banos: "2"},
    { nombre: "Suite Presidencial", capacidad: "4", precio: "150000", imagen: "images/suite-presidencial.jpg", descripcion: "El símbolo de lujo y exclusividad. Espaciosa y equipada con servicios premium, es perfecta para familias o grupos que buscan disfrutar de una experiencia única, con vistas espectaculares y atención personalizada.", banos: "2 (uno principal con jacuzzi y uno de visitas)"},
    { nombre: "Habitación doble", capacidad: "2", precio: "30000", imagen: "images/habitacion-doble.jpg", descripcion: "Diseñada para parejas o compañeros de viaje, esta habitación ofrece un espacio práctico y acogedor. Con todas las comodidades esenciales, es ideal para quienes buscan calidad a un precio accesible.", banos: "1"},
    { nombre: "Habitación individual", capacidad: "1",  precio: "20000", imagen: "images/habitacion-doble.jpg", descripcion: "Un espacio pensado para quienes viajan solos. Esta habitación combina simplicidad y confort, asegurando una estancia relajante y funcional para descansar o trabajar.", banos: "1"},
    { nombre: "Habitación doble deluxe", capacidad: "2", precio: "50000", imagen: "images/habitacion-doble.jpg", descripcion: "Una habitación amplia con detalles de lujo, perfecta para dos personas. Ofrece acabados modernos, muebles elegantes y un ambiente diseñado para garantizar el máximo confort y estilo.", banos: "2"},
    { nombre: "Habitación individual deluxe", capacidad: "1", precio: "35000", imagen: "images/habitacion-doble.jpg", descripcion: "La opción perfecta para una persona que desea una experiencia exclusiva. Con un diseño sofisticado y servicios de calidad superior, esta habitación garantiza una estancia placentera y memorable.", banos: "1"},
];

function displayRooms(Habitaciones) {
    const catalogo = document.querySelector(".catalogo-habitaciones");
    catalogo.innerHTML = "";

    Habitaciones.forEach((habitacion) => {
        const Card = document.createElement("div");
        Card.classList.add("habitacion-card");

        Card.innerHTML = `
        <img src="${habitacion.imagen}" alt="${habitacion.nombre}">
        <div class="info">
            <h3>${habitacion.nombre}</h3>
            <p class="descripcion">${habitacion.descripcion}</p>
            <p class="precio">$${parseInt(habitacion.precio).toLocaleString()} por noche</p>
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
    document.querySelector(".modal-capacidad").textContent = `Capacidad: ${habitacion.capacidad} personas`;
    document.querySelector(".modal-banos").textContent = `Baños: ${habitacion.banos}`;
    document.querySelector(".modal-precio").textContent = `Precio: $${parseInt(habitacion.precio).toLocaleString()} por noche`;
}

function closeModal() {
    document.querySelector(".modal-overlay").style.display = "none";
}

function applyFilters() {
    const capacidad = document.getElementById("capacidad").value;
    const precio = document.getElementById("precio").value;

    const [Preciomin, Preciomax] = precio ? precio.split('-').map(num => parseInt(num) * 1000) : [null, null];
    const HabitacionesFiltro = ListaHabitaciones.filter(habitacion => {
        return (capacidad === "" || habitacion.capacidad === capacidad) &&
            (!Preciomin || (habitacion.precio >= Preciomin && habitacion.precio <= Preciomax));
    });

    displayRooms(HabitacionesFiltro);
}

document.addEventListener("DOMContentLoaded", () => {
    displayRooms(ListaHabitaciones);
    document.getElementById("applyFiltersBtn").addEventListener("click", applyFilters);
    const modalOverlay = document.querySelector(".modal-overlay");
    modalOverlay.addEventListener("click", (e) => {
        if (e.target === modalOverlay) closeModal();
    });
    document.querySelector(".modal-cerrar").addEventListener("click", closeModal);
});
