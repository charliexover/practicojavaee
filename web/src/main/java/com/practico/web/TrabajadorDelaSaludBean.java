package com.practico.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.practico.entidad.TrabajadorDeLaSalud;
import com.practico.excepcion.ExcepcionDeNegocio;
import com.practico.servicios.TrabajadorDeLaSaludServiceLocal;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("trabajadorDelaSaludBean")
@SessionScoped
public class TrabajadorDelaSaludBean implements Serializable {
    @EJB
    private TrabajadorDeLaSaludServiceLocal service;

    private TrabajadorDeLaSalud nuevo = new TrabajadorDeLaSalud();
    private Integer matriculaBusqueda;
    private TrabajadorDeLaSalud resultadoBusqueda;
    private List<TrabajadorDeLaSalud> resultado;

    // Listar todos
    public List<TrabajadorDeLaSalud> getTodos() {
        return service.listar();
    }

    // Registrar
    public String registrar() {
        nuevo.setFechaRegistro(LocalDate.now());
        try {
            service.agregarTrabajador(nuevo);
        } catch (ExcepcionDeNegocio e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
        nuevo = new TrabajadorDeLaSalud(); // limpiar formulario
        return null;
    }

    public void buscar() {
        resultadoBusqueda = service.buscarPorMatricula(matriculaBusqueda);
    }

    // Getters y Setters
    public TrabajadorDeLaSalud getNuevo() { return nuevo; }
    public void setNuevo(TrabajadorDeLaSalud nuevo) { this.nuevo = nuevo; }

    public Integer getMatriculaBusqueda() { return matriculaBusqueda; }
    public void setMatriculaBusqueda(Integer matriculaBusqueda) { this.matriculaBusqueda = matriculaBusqueda; }
    public TrabajadorDeLaSalud getResultadoBusqueda() { return resultadoBusqueda; }
    public List<TrabajadorDeLaSalud> getResultado() { return resultado; }
}
