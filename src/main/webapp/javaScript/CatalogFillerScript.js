import {initializeApp} from "firebase/app";
import {getFirestore, collection, doc, setDoc} from "firebase/firestore";

const firebaseConfig = {
    apiKey: "AIzaSyDsq6hpTf2TyyxsdP-vCAa0ZfEjfvoXUmQ",
    authDomain: "testproyectpoo.firebaseapp.com",
    projectId: "testproyectpoo",
    storageBucket: "testproyectpoo.appspot.com",
    messagingSenderId: "815265158671",
    appId: "1:815265158671:web:3b31c4f3988ce9cdda757a",
    measurementId: "G-8W3H69G975"
};
//ESTE SCRIPT SE EJECUTÓ CON NODE.JS PARA RELLENAR LAS HABITACIONES INICIALES.
// Initialize Firebase
const app = initializeApp(firebaseConfig);
const db = getFirestore(app);

const ListaHabitaciones = [
    {
        nombre: "Suite Junior",
        id: "habitacion_1",
        capacidad: "1",
        precio: "45000",
        imagen: "images/suite-junior.jpg",
        descripcion: "Una suite diseñada para viajeros solitarios que buscan comodidad y funcionalidad. Cuenta con un ambiente acogedor, servicios básicos y un espacio perfecto para descansar después de un día lleno de actividades.",
        banos: "1",
        estado: "disponible",
    },
    {
        nombre: "Suite Ejecutivo",
        id: "habitacion_2",
        capacidad: "2",
        precio: "70000",
        imagen: "images/suite-ejecutivo.jpg",
        descripcion: "La elección ideal para quienes necesitan un equilibrio entre trabajo y descanso. Esta suite ofrece un diseño moderno, espacios optimizados para trabajar cómodamente y detalles que garantizan una estancia relajante..",
        banos: "2",
        estado: "disponible",
    },
    {
        nombre: "Suite Presidencial",
        id: "habitacion_3",
        capacidad: "4",
        precio: "150000",
        imagen: "images/suite-presidencial.jpg",
        descripcion: "El símbolo de lujo y exclusividad. Espaciosa y equipada con servicios premium, es perfecta para familias o grupos que buscan disfrutar de una experiencia única, con vistas espectaculares y atención personalizada.",
        banos: "2",
        estado: "disponible",
    },
    {
        nombre: "Habitación doble",
        id: "habitacion_4",
        capacidad: "2",
        precio: "30000",
        imagen: "images/habitacion-doble.jpg",
        descripcion: "Diseñada para parejas o compañeros de viaje, esta habitación ofrece un espacio práctico y acogedor. Con todas las comodidades esenciales, es ideal para quienes buscan calidad a un precio accesible.",
        banos: "1",
        estado: "disponible",
    },
    {
        nombre: "Habitación individual",
        id: "habitacion_5",
        capacidad: "1",
        precio: "20000",
        imagen: "images/habitacion-doble.jpg",
        descripcion: "Un espacio pensado para quienes viajan solos. Esta habitación combina simplicidad y confort, asegurando una estancia relajante y funcional para descansar o trabajar.",
        banos: "1",
        estado: "disponible",
    },
    {
        nombre: "Habitación doble deluxe",
        id: "habitacion_6",
        capacidad: "2", precio: "50000",
        imagen: "images/habitacion-doble.jpg",
        descripcion: "Una habitación amplia con detalles de lujo, perfecta para dos personas. Ofrece acabados modernos, muebles elegantes y un ambiente diseñado para garantizar el máximo confort y estilo.",
        banos: "2",
        estado: "disponible",
    },
    {
        nombre: "Habitación individual deluxe",
        id: "habitacion_7",
        capacidad: "1",
        precio: "35000",
        imagen: "images/habitacion-doble.jpg",
        descripcion: "La opción perfecta para una persona que desea una experiencia exclusiva. Con un diseño sofisticado y servicios de calidad superior, esta habitación garantiza una estancia placentera y memorable.",
        banos: "1",
        estado: "disponible",
    },
    {
        nombre: "Suite Junior",
        id: "habitacion_8",
        capacidad: "1",
        precio: "45000",
        imagen: "images/suite-junior.jpg",
        descripcion: "Una suite diseñada para viajeros solitarios que buscan comodidad y funcionalidad. Cuenta con un ambiente acogedor, servicios básicos y un espacio perfecto para descansar después de un día lleno de actividades.",
        banos: "1",
        estado: "disponible",
    },

];

ListaHabitaciones.forEach(async (habitacion) => {
    try {
        await setDoc(doc(db, "habitaciones", habitacion.id), habitacion);
        console.log(`Habitación ${habitacion.nombre} guardada correctamente.`);
    } catch (error) {
        console.error(`Error al guardar la habitación ${habitacion.nombre}:`, error);
    }
});