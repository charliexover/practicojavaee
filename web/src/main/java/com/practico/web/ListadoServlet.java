package com.practico.web;

import java.io.IOException;
import java.util.List;

import com.practico.entidad.TrabajadorDeLaSalud;
import com.practico.servicios.TrabajadorDeLaSaludServiceLocal;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listado")
public class ListadoServlet extends HttpServlet {
    @EJB
    private TrabajadorDeLaSaludServiceLocal service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<TrabajadorDeLaSalud> trabajadores = service.listar();
        request.setAttribute("trabajadores", trabajadores);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
