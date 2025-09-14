package com.practico.dao;

import java.util.List;

import com.practico.entidad.TrabajadorDeLaSalud;

import jakarta.ejb.Local;

@Local
public interface TrabajadorDeLaSaludDAOLocal {
    void agregarTrabajador(TrabajadorDeLaSalud t);
    List<TrabajadorDeLaSalud> listar();
    List<TrabajadorDeLaSalud> buscarPorNombre(String nombre);
    TrabajadorDeLaSalud buscarPorMatricula(Integer matricula);
    void eliminarTodos();
}
