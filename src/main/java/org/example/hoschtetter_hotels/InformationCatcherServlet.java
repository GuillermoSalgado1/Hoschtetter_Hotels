package org.example.hoschtetter_hotels;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

@WebServlet("/InformationCatcherServlet")
public class InformationCatcherServlet extends HttpServlet {

    private static final String ERROR_PAGE = "index.jsp";
    private static final String SUCCESS_PAGE = "payment.jsp";
    private static final String ERROR_PARAM = "error";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            ReservationData reservationData = extractAndValidateData(request);

            int montoTotal = calculateTotal(reservationData);

            setRequestAttributes(request, reservationData, montoTotal);

            request.getRequestDispatcher(SUCCESS_PAGE).forward(request, response);

        } catch (IllegalArgumentException e) {
            response.sendRedirect(ERROR_PAGE + "?" + ERROR_PARAM + "=" + e.getMessage());
        } catch (Exception e) {
            response.sendRedirect(ERROR_PAGE + "?" + ERROR_PARAM + "=Ocurrió un error inesperado");
        }
    }

    private ReservationData extractAndValidateData(HttpServletRequest request) {
        String fechaInicioStr = request.getParameter("fechaInicio");
        String fechaTerminoStr = request.getParameter("fechaTermino");
        String precioPorNocheStr = request.getParameter("precioPorNoche");
        String nombreHabitacion = request.getParameter("nombreHabitacion");
        String habitacionId = request.getParameter("habitacionId");

        if (fechaInicioStr == null || fechaTerminoStr == null || precioPorNocheStr == null) {
            throw new IllegalArgumentException("Datos incompletos");
        }

        LocalDate fechaInicio;
        LocalDate fechaTermino;

        int precioPorNoche;

        try {
            fechaInicio = LocalDate.parse(fechaInicioStr);
            fechaTermino = LocalDate.parse(fechaTerminoStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fechas inválidas");
        }

        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaTermino);
        if (dias <= 0) {
            throw new IllegalArgumentException("Rango de fechas inválido");
        }

        try {
            precioPorNoche = Integer.parseInt(precioPorNocheStr);
            if (precioPorNoche <= 0) {
                throw new IllegalArgumentException("El precio por noche debe ser positivo");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El precio por noche no es válido");
        }

        return new ReservationData(fechaInicioStr, fechaTerminoStr, precioPorNoche, nombreHabitacion, habitacionId, dias);
    }

    private int calculateTotal(ReservationData data) {
        return (int) data.getDias() * data.getPrecioPorNoche();
    }

    private void setRequestAttributes(HttpServletRequest request, ReservationData data, int montoTotal) {
        request.setAttribute("montoTotal", montoTotal);
        request.setAttribute("nombreHabitacion", data.getNombreHabitacion());
        request.setAttribute("fechaInicio", data.getFechaInicio());
        request.setAttribute("fechaTermino", data.getFechaTermino());
        request.setAttribute("habitacionId", data.getHabitacionId());
    }
}
