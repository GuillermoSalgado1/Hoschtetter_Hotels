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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@WebServlet("/PaymentProcessServlet")
public class PaymentProcessServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuarioId = request.getParameter("loggedInUserId");
        String nombreHabitacion = request.getParameter("nombreHabitacion");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaTermino");
        String montoTotal = request.getParameter("montoTotal");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (usuarioId == null || usuarioId.trim().isEmpty()) {
            out.println("<h2>Error: Debes iniciar sesión para realizar una reserva.</h2>");
            out.println("<a href='index.jsp'>Volver al inicio</a>");
            return;
        }

        if (fechaInicio == null || fechaInicio.isEmpty() || fechaFin == null || fechaFin.isEmpty()) {
            out.println("<h2>Error: Las fechas de inicio y fin deben proporcionarse y no deben estar vacías.</h2>");
            out.println("<a href='index.jsp'>Volver al inicio</a>");
            return;
        }

        try {
            java.sql.Date inicio = java.sql.Date.valueOf(fechaInicio);
            java.sql.Date fin = java.sql.Date.valueOf(fechaFin);

            List<String> availableRoomIds = findAvailableRoomIds(nombreHabitacion, inicio, fin);
            if (availableRoomIds.isEmpty()) {
                out.println("<h2>No hay habitaciones disponibles de este tipo para las fechas seleccionadas.</h2>");
                out.println("<a href='index.jsp'>Volver al inicio</a>");
                return;
            }

            String habitacionId = availableRoomIds.get(0); // Esto selecciona la primera habitación disponible.
            if (saveReservationAndInvoiceToFirestore(usuarioId, habitacionId, nombreHabitacion, inicio, fin, montoTotal)) {
                out.println("<h2>Reservación exitosa y factura generada.</h2>");
            } else {
                out.println("<h2>Error al guardar la reservación y generar la factura.</h2>");
            }

        } catch (Exception e) {
            out.println("<h2>Error al procesar las fechas. Formato incorrecto o problema al procesar.</h2>");
            out.println("<a href='index.jsp'>Volver al inicio</a>");
        }
        out.println("<a href='index.jsp'>Volver al inicio</a>");
    }

    private List<String> findAvailableRoomIds(String nombreHabitacion, Date fechaInicio, Date fechaFin) {
        Firestore db = FirestoreClient.getFirestore();
        List<String> availableRoomIds = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("habitaciones")
                    .whereEqualTo("nombre", nombreHabitacion)
                    .get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot doc : documents) {
                String roomId = doc.getId();
                if (areDatesAvailable(roomId, fechaInicio, fechaFin)) {
                    availableRoomIds.add(roomId);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return availableRoomIds;
    }

    private boolean areDatesAvailable(String roomId, Date fechaInicio, Date fechaFin) {
        Firestore db = FirestoreClient.getFirestore();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("reservaciones")
                    .whereEqualTo("habitacionId", roomId)
                    .get();

            List<QueryDocumentSnapshot> reservations = future.get().getDocuments();
            for (QueryDocumentSnapshot reservation : reservations) {
                Date existingFechaInicio = reservation.getTimestamp("fechaInicio").toDate();
                Date existingFechaFin = reservation.getTimestamp("fechaFin").toDate();

                if ((fechaInicio.before(existingFechaFin) && fechaFin.after(existingFechaInicio)) ||
                        (fechaInicio.equals(existingFechaInicio)) || (fechaFin.equals(existingFechaFin))) {
                    return false; // Hay colisión de fechas.
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return true; // No hay colisiones de fechas.
    }

    private boolean saveReservationAndInvoiceToFirestore(String usuarioId, String habitacionId, String nombreHabitacion, Date fechaInicio, Date fechaFin, String montoTotal) {
        Firestore db = FirestoreClient.getFirestore();
        try {
            Map<String, Object> reservation = new HashMap<>();
            reservation.put("usuarioId", usuarioId);
            reservation.put("habitacionId", habitacionId);
            reservation.put("nombreHabitacion", nombreHabitacion);
            reservation.put("fechaInicio", fechaInicio);
            reservation.put("fechaFin", fechaFin);
            reservation.put("montoTotal", montoTotal);

            ApiFuture<DocumentReference> reservationFuture = db.collection("reservaciones").add(reservation);
            reservationFuture.get();

            Map<String, Object> invoice = new HashMap<>();
            invoice.put("usuarioId", usuarioId);
            invoice.put("habitacionId", habitacionId);
            invoice.put("nombreHabitacion", nombreHabitacion);
            invoice.put("montoTotal", montoTotal);
            invoice.put("fechaEmision", new java.sql.Date(System.currentTimeMillis()));

            ApiFuture<DocumentReference> invoiceFuture = db.collection("facturas").add(invoice);
            invoiceFuture.get();

            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }
}