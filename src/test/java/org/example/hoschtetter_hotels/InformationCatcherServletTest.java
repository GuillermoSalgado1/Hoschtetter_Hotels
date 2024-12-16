package org.example.hoschtetter_hotels;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.mockito.Mockito.*;

class InformationCatcherServletTest {

    private static final String SUCCESS_PAGE = "payment.jsp";
    private static final String ERROR_PAGE = "index.jsp";

    @Test
    void pruebaReservaExitosa() throws ServletException, IOException {
        InformationCatcherServlet servlet = new InformationCatcherServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        TestReservationData.configurarReservaExitosa(request, dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("montoTotal", 1350); // 9 días * 150
        verify(request).setAttribute("nombreHabitacion", "Suite");
        verify(request).setAttribute("fechaInicio", "2023-12-01");
        verify(request).setAttribute("fechaTermino", "2023-12-10");
        verify(request).setAttribute("habitacionId", "H001");
        verify(dispatcher).forward(request, response);
    }

    @Test
    void pruebaDatosIncompletos() throws ServletException, IOException {
        InformationCatcherServlet servlet = new InformationCatcherServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        TestReservationData.configurarDatosIncompletos(request);

        servlet.doPost(request, response);

        verify(response).sendRedirect(ERROR_PAGE + "?error=Datos incompletos");
    }

    @Test
    void pruebaFechasInvalidas() throws ServletException, IOException {
        InformationCatcherServlet servlet = new InformationCatcherServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        TestReservationData.configurarFechasInvalidas(request);

        servlet.doPost(request, response);

        verify(response).sendRedirect(ERROR_PAGE + "?error=Fechas inválidas");
    }

    @Test
    void pruebaPrecioNegativo() throws ServletException, IOException {
        InformationCatcherServlet servlet = new InformationCatcherServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        TestReservationData.configurarPrecioNegativo(request);

        servlet.doPost(request, response);

        verify(response).sendRedirect(ERROR_PAGE + "?error=El precio por noche debe ser positivo");
    }

    @Test
    void pruebaErrorRangoDeFechas() throws ServletException, IOException {
        InformationCatcherServlet servlet = new InformationCatcherServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        TestReservationData.configurarRangoDeFechasInvalido(request);

        servlet.doPost(request, response);

        verify(response).sendRedirect(ERROR_PAGE + "?error=Rango de fechas inválido");
    }

    @Test
    void pruebaErrorInesperado() throws ServletException, IOException {
        InformationCatcherServlet servlet = new InformationCatcherServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("fechaInicio")).thenThrow(new RuntimeException("Unexpected Error"));

        servlet.doPost(request, response);

        verify(response).sendRedirect(ERROR_PAGE + "?error=Ocurrió un error inesperado");
    }
}

class TestReservationData {

    static void configurarReservaExitosa(HttpServletRequest request, RequestDispatcher dispatcher) {
        when(request.getParameter("fechaInicio")).thenReturn("2023-12-01");
        when(request.getParameter("fechaTermino")).thenReturn("2023-12-10");
        when(request.getParameter("precioPorNoche")).thenReturn("150");
        when(request.getParameter("nombreHabitacion")).thenReturn("Suite");
        when(request.getParameter("habitacionId")).thenReturn("H001");
        when(request.getRequestDispatcher("payment.jsp")).thenReturn(dispatcher);
    }

    static void configurarDatosIncompletos(HttpServletRequest request) {
        when(request.getParameter("fechaInicio")).thenReturn(null);
        when(request.getParameter("fechaTermino")).thenReturn("2023-12-10");
        when(request.getParameter("precioPorNoche")).thenReturn("150");
    }

    static void configurarFechasInvalidas(HttpServletRequest request) {
        when(request.getParameter("fechaInicio")).thenReturn("2023-12-01");
        when(request.getParameter("fechaTermino")).thenReturn("invalid-date");
        when(request.getParameter("precioPorNoche")).thenReturn("150");
    }

    static void configurarPrecioNegativo(HttpServletRequest request) {
        when(request.getParameter("fechaInicio")).thenReturn("2023-12-01");
        when(request.getParameter("fechaTermino")).thenReturn("2023-12-10");
        when(request.getParameter("precioPorNoche")).thenReturn("-500");
    }

    static void configurarRangoDeFechasInvalido(HttpServletRequest request) {
        when(request.getParameter("fechaInicio")).thenReturn("2023-12-10");
        when(request.getParameter("fechaTermino")).thenReturn("2023-12-01");
        when(request.getParameter("precioPorNoche")).thenReturn("150");
    }
}