package com.practico.servicios;

import java.util.List;
import java.util.Objects;

import com.practico.dao.TrabajadorDeLaSaludDAOLocal;
import com.practico.entidad.TrabajadorDeLaSalud;
import com.practico.excepcion.ExcepcionDeNegocio;

import jakarta.ejb.EJB;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;

@SuppressWarnings("unused")
@Stateless
@Local(TrabajadorDeLaSaludServiceLocal.class)
@Remote(TrabajadorDeLaSaludServiceRemote.class)
public class TrabajadorDeLaSaludServiceBean implements TrabajadorDeLaSaludServiceLocal, TrabajadorDeLaSaludServiceRemote {
    @EJB
    private TrabajadorDeLaSaludDAOLocal dao;

    @Override
    public void agregarTrabajador(TrabajadorDeLaSalud t) throws ExcepcionDeNegocio {

        if(t.getNombre() == null || t.getNombre().isBlank()) {
            throw new ExcepcionDeNegocio("El nombre no puede estar vacío");
        }
        
        if(Objects.isNull(t.getMatricula()) || t.getMatricula() <= 0) {
            throw new ExcepcionDeNegocio("La matrícula debe ser un número positivo");
        }

        TrabajadorDeLaSalud existente = dao.buscarPorMatricula(t.getMatricula());
        if(existente != null) {
            throw new ExcepcionDeNegocio("Ya existe un trabajador con la matrícula " + t.getMatricula());
        }

        dao.agregarTrabajador(t);
    }

    @Override
    public List<TrabajadorDeLaSalud> listar() {
        return dao.listar();
    }
    @Override
    public List<TrabajadorDeLaSalud> buscarPorNombre(String nombre) {
        return dao.buscarPorNombre(nombre);
    }
    @Override
    public TrabajadorDeLaSalud buscarPorMatricula(Integer matricula) {
        return dao.buscarPorMatricula(matricula);
    }
}
