package org.example.hoschtetter_hotels;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/PaymentProcessServlet")
public class PaymentProcessServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(PaymentProcessServlet.class.getName());
    private final Firestore database = FirestoreClient.getFirestore();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            String usuarioId = Optional.ofNullable(request.getParameter("loggedInUserId")).orElse("").trim();
            String nombreHabitacion = request.getParameter("nombreHabitacion");
            String fechaInicio = request.getParameter("fechaInicio");
            String fechaFin = request.getParameter("fechaTermino");
            String montoTotalStr = request.getParameter("montoTotal");

            if (usuarioId.isEmpty()) {
                showError(out, "Debes iniciar sesión para realizar una reserva.");
                return;
            }

            if (nombreHabitacion == null || fechaInicio == null || fechaFin == null || montoTotalStr == null) {
                showError(out, "Todos los campos son obligatorios y deben contener valores válidos.");
                return;
            }

            Date inicio = Date.valueOf(fechaInicio);
            Date fin = Date.valueOf(fechaFin);

            if (!inicio.before(fin)) {
                showError(out, "La fecha de inicio debe ser anterior a la fecha de fin.");
                return;
            }

            List<String> availableRoomIds = findAvailableRoomIds(nombreHabitacion, inicio, fin);
            if (availableRoomIds.isEmpty()) {
                showError(out, "No hay habitaciones disponibles para las fechas seleccionadas.");
                return;
            }

            String habitacionId = availableRoomIds.get(0);
            if (saveReservationAndInvoice(usuarioId, habitacionId, nombreHabitacion, inicio, fin, montoTotalStr)) {
                out.println("<h2>Reservación realizada con éxito. ¡Gracias por confiar en nosotros!</h2>");
            } else {
                showError(out, "Ocurrió un error al procesar la reserva. Intente nuevamente.");
            }

        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Formato de fecha inválido.", e);
            showError(out, "El formato de las fechas es inválido. Utilice el formato yyyy-MM-dd.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error inesperado.", e);
            showError(out, "Ocurrió un error inesperado. Intente nuevamente.");
        }
        out.println("<a href='index.jsp'>Volver al inicio</a>");
    }

    private void showError(PrintWriter out, String message) {
        out.println(String.format("<h2>Error: %s</h2>", message));
        out.println("<a href='index.jsp'>Volver al inicio</a>");
    }

    private List<String> findAvailableRoomIds(String roomName, Date startDate, Date endDate) {
        List<String> availableRoomIds = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = database.collection("habitaciones")
                    .whereEqualTo("nombre", roomName)
                    .get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot doc : documents) {
                String roomId = doc.getId();
                if (areDatesAvailable(roomId, startDate, endDate)) {
                    availableRoomIds.add(roomId);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.log(Level.SEVERE, "Error al buscar habitaciones disponibles.", e);
        }
        return availableRoomIds;
    }

    private boolean areDatesAvailable(String roomId, Date startDate, Date endDate) {
        try {
            ApiFuture<QuerySnapshot> future = database.collection("reservaciones")
                    .whereEqualTo("habitacionId", roomId)
                    .get();
            List<QueryDocumentSnapshot> reservations = future.get().getDocuments();
            for (QueryDocumentSnapshot reservation : reservations) {
                Date existingStartDate = new java.sql.Date(reservation.getTimestamp("fechaInicio").toDate().getTime());
                Date existingEndDate = new java.sql.Date(reservation.getTimestamp("fechaFin").toDate().getTime());

                if (!startDate.after(existingEndDate) && !endDate.before(existingStartDate)) {
                    return false; // Colisión de fechas
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.log(Level.SEVERE, "Error al verificar disponibilidad de fechas.", e);
        }
        return true;
    }

    private boolean saveReservationAndInvoice(String userId, String roomId, String roomName, Date startDate, Date endDate, String totalAmount) {
        try {
            Map<String, Object> reservation = new HashMap<>();
            reservation.put("usuarioId", userId);
            reservation.put("habitacionId", roomId);
            reservation.put("nombreHabitacion", roomName);
            reservation.put("fechaInicio", startDate);
            reservation.put("fechaFin", endDate);
            reservation.put("montoTotal", totalAmount);

            database.collection("reservaciones").add(reservation).get();

            Map<String, Object> invoice = new HashMap<>();
            invoice.put("usuarioId", userId);
            invoice.put("habitacionId", roomId);
            invoice.put("nombreHabitacion", roomName);
            invoice.put("montoTotal", totalAmount);
            invoice.put("fechaEmision", new Date(System.currentTimeMillis()));

            database.collection("facturas").add(invoice).get();

            return true;
        } catch (InterruptedException | ExecutionException e) {
            logger.log(Level.SEVERE, "Error al guardar reservación o factura.", e);
            return false;
        }
    }
}