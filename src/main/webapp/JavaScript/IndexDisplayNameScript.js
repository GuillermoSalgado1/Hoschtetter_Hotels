import {initializeApp} from "https://www.gstatic.com/firebasejs/11.0.1/firebase-app.js";
import {getAuth, onAuthStateChanged, signOut} from "https://www.gstatic.com/firebasejs/11.0.1/firebase-auth.js";
import {getFirestore, getDoc, doc} from "https://www.gstatic.com/firebasejs/11.0.1/firebase-firestore.js";

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
const auth = getAuth();
const db = getFirestore();

onAuthStateChanged(auth, (user) => {
    const loggedInUserId = localStorage.getItem("loggedInUserId");
    const reservationsLink = document.querySelector(".btn"); // Enlace "Mis Reservaciones"

    if (loggedInUserId) {
        const docRef = doc(db, "users", loggedInUserId);

        getDoc(docRef)
            .then((docSnap) => {
                if (docSnap.exists()) {
                    const userData = docSnap.data();
                    document.getElementById("loggedUserFName").innerText = "Bienvenido " + userData.firstName;

                    let loginLink = document.getElementById("loginLink");
                    loginLink.innerText = "Cerrar sesión";
                    loginLink.addEventListener("click", logoutUser);

                    reservationsLink.style.display = "block";
                } else {
                    console.error("No se encontró el documento del usuario.");
                }
            })
            .catch((error) => {
                console.error("Error al obtener los datos del usuario:", error);
            });
    } else {

        console.log("No hay usuario autenticado.");
        document.getElementById("loggedUserFName").innerText = "";

        reservationsLink.style.display = "none";
    }
});

function logoutUser(event) {
    event.preventDefault();
    localStorage.removeItem("loggedInUserId");
    signOut(auth)
        .then(() => {
            console.log("Sesión cerrada exitosamente.");
            window.location.href = "index.jsp";
        })
        .catch((error) => {
            console.error("Error al cerrar sesión:", error);
        });
}