package com.practico.web;

import java.io.IOException;
import java.time.LocalDate;

import com.practico.entidad.TrabajadorDeLaSalud;
import com.practico.excepcion.ExcepcionDeNegocio;
import com.practico.servicios.TrabajadorDeLaSaludServiceLocal;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registrar")
public class RegistrarServlet extends HttpServlet {
    @EJB
    private TrabajadorDeLaSaludServiceLocal service;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String matriculaStr = request.getParameter("matricula");

        if (nombre != null && matriculaStr != null) {
            try {
                int matricula = Integer.parseInt(matriculaStr);

                TrabajadorDeLaSalud t = new TrabajadorDeLaSalud();
                t.setNombre(nombre);
                t.setMatricula(matricula);
                t.setFechaRegistro(LocalDate.now());

                service.agregarTrabajador(t);
            } catch (NumberFormatException | ExcepcionDeNegocio e) {
                request.setAttribute("error", "La matrícula debe ser un número válido.");
            }
        }

        
        response.sendRedirect("listado");
    }
}
