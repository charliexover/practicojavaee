package com.practico.web;

import java.io.Serializable;
import java.util.List;

import com.practico.entidad.TrabajadorDeLaSalud;
import com.practico.servicios.TrabajadorDeLaSaludServiceLocal;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

@Named("trabajadorDelaSaludBean")
@SessionScoped
public class TrabajadorDelaSaludBean implements Serializable {

    @Resource(lookup = "java:/JmsXA")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/jms/queue/queue_alta_trabajador")
    private Queue queue;
    
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
        try (JMSContext context = connectionFactory.createContext()) {
            String mensaje = nuevo.getNombre() + "|" +
                             nuevo.getMatricula();
            context.createProducer().send(queue, mensaje);
            System.out.println("Mensaje enviado a la cola: " + mensaje);
        } catch (Exception e) {
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
