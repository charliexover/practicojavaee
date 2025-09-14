package com.practico.web;

import java.io.IOException;

import com.practico.entidad.TrabajadorDeLaSalud;
import com.practico.servicios.TrabajadorDeLaSaludServiceLocal;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/buscar")
public class BuscarServlet extends HttpServlet {
    @EJB
    private TrabajadorDeLaSaludServiceLocal service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String matriculaStr = request.getParameter("matricula");

        if (matriculaStr != null) {
            try {
                int matricula = Integer.parseInt(matriculaStr);
                TrabajadorDeLaSalud encontrado = service.buscarPorMatricula(matricula);

                if (encontrado != null) {
                    request.setAttribute("resultado", encontrado);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "La matrícula debe ser un número válido.");
            }
        }

        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
