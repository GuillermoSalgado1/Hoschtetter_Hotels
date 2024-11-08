package org.example.hoschtetter_hotels;
import java.io.*;

import com.google.firebase.database.DatabaseReference;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "SVregister", value = "/SVregister")
public class SVregister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Usuario nuevoUsuario = new Usuario(name, email, password);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            DatabaseConnection.inicializar();
            DatabaseReference dbref = DatabaseConnection.getDatabaseReference("usuarios");
            String userId = dbref.push().getKey();
            if (userId != null) {
                dbref.child(userId).setValueAsync(nuevoUsuario);
                resp.sendRedirect("index.jsp");
            } else {
                resp.getWriter().println("Usuario no registrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Error al registrar al usuario" + e.getMessage());
        }

    }
}