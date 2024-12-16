package org.example.hoschtetter_hotels;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class RoomStatusScheduler {

    private static final Logger logger = LoggerFactory.getLogger(RoomStatusScheduler.class);
    private final Firestore db;

    @Autowired
    public RoomStatusScheduler(Firestore db) {
        this.db = db;
    }

    @Scheduled(fixedRate = 60000)
    public void updateRoomStatusBasedOnReservationDates() {
        logger.info("Iniciando tarea programada para actualizar el estado de las habitaciones.");

        ApiFuture<QuerySnapshot> future = db.collection("reservaciones").get();

        try {
            List<QueryDocumentSnapshot> reservations = future.get().getDocuments();
            for (QueryDocumentSnapshot reservation : reservations) {
                Timestamp fechaInicioTimestamp = reservation.getTimestamp("fechaInicio");
                Timestamp fechaFinTimestamp = reservation.getTimestamp("fechaFin");
                String habitacionId = reservation.getString("habitacionId");

                if (fechaInicioTimestamp != null && fechaFinTimestamp != null) {
                    Date now = new Date();
                    Date fechaInicio = fechaInicioTimestamp.toDate();
                    Date fechaFin = fechaFinTimestamp.toDate();

                    if (now.equals(fechaInicio) || (now.after(fechaInicio) && now.before(fechaFin))) {
                        changeRoomStatusToOccupied(habitacionId);
                    } else if (now.after(fechaFin)) {
                        changeRoomStatusToAvailable(habitacionId);
                        db.collection("reservaciones").document(reservation.getId()).delete();
                        logger.info("Reserva con ID '{}' eliminada.", reservation.getId());
                    }
                }
            }
        } catch (ExecutionException e) {
            logger.error("Error durante la ejecución al obtener documentos: ", e);
        } catch (InterruptedException e) {
            logger.error("Operación interrumpida: ", e);
            Thread.currentThread().interrupt();
        }
    }

    private void changeRoomStatusToAvailable(String habitacionId) {
        updateRoomStatus(habitacionId, "disponible");
    }

    private void changeRoomStatusToOccupied(String habitacionId) {
        updateRoomStatus(habitacionId, "ocupado");
    }

    private void updateRoomStatus(String habitacionId, String estado) {
        DocumentReference roomDocRef = db.collection("habitaciones").document(habitacionId);

        try {
            ApiFuture<DocumentSnapshot> future = roomDocRef.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                String currentEstado = document.getString("estado");

                if (!estado.equals(currentEstado)) {
                    Map<String, Object> updates = new HashMap<>();
                    updates.put("estado", estado);

                    ApiFuture<WriteResult> writeResult = roomDocRef.update(updates);
                    logger.info("Estado de la habitación '{}' actualizado a '{}'.", habitacionId, estado);
                } else {
                    logger.info("La habitación '{}' ya está en estado '{}'. No se necesita actualización.", habitacionId, estado);
                }
            } else {
                logger.warn("El documento para la habitación '{}' no existe.", habitacionId);
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error al obtener o actualizar el estado de la habitación '{}': ", habitacionId, e);
            Thread.currentThread().interrupt();
        }
    }

}
