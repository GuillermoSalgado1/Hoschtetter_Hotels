package org.example.hoschtetter_hotels;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentMethod = request.getParameter("paymentMethod");
        String cardNumber = request.getParameter("cardNumber");
        String cvv = request.getParameter("cvv");

        boolean isValid = verifyCard(paymentMethod, cardNumber, cvv);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (isValid) {
            out.println("<h2>Pago realizado exitosamente con " + paymentMethod + ".</h2>");
        } else {
            out.println("<h2>Error en la verificación de la tarjeta. Por favor intente nuevamente.</h2>");
        }
        out.println("<a href='index.jsp'>Volver al inicio</a>");
    }
    //Esto es solo una prueba sencilla de validación.
    private boolean verifyCard(String method, String cardNumber, String cvv) {
        if ("credito".equals(method) && cardNumber.startsWith("4")) {
            return true;
        }
        if ("debito".equals(method) && cardNumber.startsWith("5")) {
            return true;
        }
        return false;
    }
}