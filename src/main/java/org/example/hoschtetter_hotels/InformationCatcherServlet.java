package org.example.hoschtetter_hotels;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@WebServlet("/InformationCatcherServlet")
public class InformationCatcherServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fechaInicioStr = request.getParameter("fechaInicio");
        String fechaTerminoStr = request.getParameter("fechaTermino");
        String precioPorNocheStr = request.getParameter("precioPorNoche");
        String nombreHabitacion = request.getParameter("nombreHabitacion");
        String habitacionId = request.getParameter("habitacionId");

        if (fechaInicioStr == null || fechaTerminoStr == null || precioPorNocheStr == null) {
            response.sendRedirect("index.jsp?error=Datos incompletos");
            return;
        }

        LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
        LocalDate fechaTermino = LocalDate.parse(fechaTerminoStr);

        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaTermino);
        if (dias <= 0) {
            response.sendRedirect("index.jsp?error=Rango de fechas inválido");
            return;
        }

        int precioPorNoche = Integer.parseInt(precioPorNocheStr);
        int montoTotal = (int) dias * precioPorNoche;

        request.setAttribute("montoTotal", montoTotal);
        request.setAttribute("nombreHabitacion", nombreHabitacion);
        request.setAttribute("fechaInicio", fechaInicioStr);
        request.setAttribute("fechaTermino", fechaTerminoStr);
        request.setAttribute("habitacionId", habitacionId);

        request.getRequestDispatcher("payment.jsp").forward(request, response);
    }
}

